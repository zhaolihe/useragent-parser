package com.tiger.useragent;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.tiger.useragent.browser.Browser;
import com.tiger.useragent.browser.BrowserParser;
import com.tiger.useragent.device.Device;
import com.tiger.useragent.device.DeviceMap;
import com.tiger.useragent.device.DeviceParser;
import com.tiger.useragent.device.DevicePattern;
import com.tiger.useragent.enums.DeviceType;
import com.tiger.useragent.enums.NetType;
import com.tiger.useragent.enums.OsType;
import com.tiger.useragent.os.Os;
import com.tiger.useragent.os.OsParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tiger.useragent.Constant.DEFAULT_VALUE;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/9
 */
public class Parser {
    private OsParser osParser;
    private BrowserParser browserParser;
    private DeviceParser deviceParser;
    private DeviceMap deviceMap;
    protected static final Object LOCK = new Object();
    private static final ConcurrentMap<String, DeviceType> dMap = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, NetType> netMap = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, OsType> osMap = new ConcurrentHashMap<>();

    private final static Pattern pattern = Pattern.compile("\\.net( clr | client )?(?<ver>\\d(\\.\\d)?)(\\.\\d+)*[ce;$) ]", Pattern.CASE_INSENSITIVE);
    private final static Pattern netTypePattern = Pattern.compile("\\W(WIFI|5G|4G|3G|2G)\\W*", Pattern.CASE_INSENSITIVE);
    private final static Pattern deviceIdPattern = Pattern.compile("[\\s&;\"](deviceid|deviceId|sdk_guid|UTDID|GUID|guid|Id|ID|id|udid|UDID|MZ)[\" /:=]+([\\w-]+)", Pattern.CASE_INSENSITIVE);

    public Parser() throws IOException {
        readConfigs();
    }

    private void readConfigs() throws IOException {

        Yaml yaml = new Yaml(new SafeConstructor());
        try (InputStream stream = Parser.class.getResourceAsStream("/OSConfig.yaml")) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> osParserConfigs = (List<Map<String, String>>) yaml.load(stream);
            if (osParserConfigs == null) {
                throw new IllegalArgumentException("OSConfig.yaml loading failed.");
            }
            osParser = OsParser.fromList(osParserConfigs);
        }

        try (InputStream stream = Parser.class.getResourceAsStream("/BrowserConfig.yaml")) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> browserParserConfigs = (List<Map<String, String>>) yaml.load(stream);
            if (browserParserConfigs == null) {
                throw new IllegalArgumentException("BrowserConfig.yaml loading failed.");
            }
            browserParser = BrowserParser.fromList(browserParserConfigs);
        }

        try (InputStream configStream = Parser.class.getResourceAsStream("/DeviceConfig.yaml")) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> deviceParserConfigs = (List<Map<String, String>>) yaml.load(configStream);
            if (deviceParserConfigs == null) {
                throw new IllegalArgumentException("DeviceConfig.yaml loading failed.");
            }
            List<DevicePattern> patterns = DeviceParser.patternsFromList(deviceParserConfigs);
            deviceParser = new DeviceParser(patterns);
        }

        try (InputStream dictionaryStream = Parser.class.getResourceAsStream("/DeviceDictionary.txt")) {
            deviceMap = DeviceMap.mapFromFile(dictionaryStream);
        }
    }

    public UserAgentInfo parse(String agentString) {
        if (agentString == null) {
            return null;
        }

        Device device = parseDevice(agentString);
        if (device.getDeviceType().equals(DeviceType.Spider)) {
            return buildUserAgentInfo(Os.DEFAULT_OS, Browser.DEFAULT_BROWSER, device, Pair.of(DEFAULT_VALUE, NetType.Other), DEFAULT_VALUE);
        }
        Os os = parseOS(agentString);
        Browser browser = parseBrowser(agentString);
//        String dotNet = parseDotNet(agentString);
        Pair<String, NetType> pair = parseNetType(agentString);
        String deviceId = parseDeviceId(agentString);
        if (os == null) {
            os = Os.DEFAULT_OS;

        } else if (os.isTv()) {
            device = Device.DEFAULT_TV;
        } else if (os.isMobile() && !device.isMobile() && (device.getDeviceType() != DeviceType.TV)) {
            device = Device.DEFAULT_PHONE_SCREEN;
        }

        return buildUserAgentInfo(os, browser, device, pair, deviceId);
    }

    @Deprecated
    public String parseDotNet(String agentString) {
        String maxVersion = "", version;
        Matcher matcher = pattern.matcher(agentString);
        while (matcher.find()) {
            version = matcher.group("ver");
            if (version.compareTo(maxVersion) > 0) {
                maxVersion = version;
            }
        }
        return maxVersion.equals("") ? DEFAULT_VALUE : maxVersion;
    }

    public Pair<String, NetType> parseNetType(String agentString) {
        Matcher matcher = netTypePattern.matcher(agentString.toUpperCase());
        String result = "";
        if (matcher.find()) {
            result = matcher.group(1);
        }
        String key = Strings.isNullOrEmpty(result) ? DEFAULT_VALUE : result;
        NetType value = getNetType(key);
        return Pair.of(key, value);
    }

    public String parseDeviceId(String agentString) {
        Matcher matcher = deviceIdPattern.matcher(agentString.toLowerCase());
        String result = "";
        if (matcher.find()) {
            result = matcher.group(2);
        }
        return Strings.isNullOrEmpty(result) || result.length() < 8 ? DEFAULT_VALUE : result;
    }

    public Device parseDevice(String agentString) {
        Device device = deviceParser.parse(agentString);
        return deviceMap.parseDevice(device);
    }

    public Browser parseBrowser(String agentString) {
        return browserParser.parse(agentString);
    }

    public Os parseOS(String agentString) {
        return osParser.parse(agentString);
    }

    public DeviceType getDeviceType(String deviceType) {
        if (Strings.isNullOrEmpty(deviceType)) {
            return DeviceType.Other;
        }
        String key = deviceType.trim();
        if (!dMap.containsKey(key)) {
            synchronized (LOCK) {
                if (!dMap.containsKey(key)) {
                    DeviceType item = DeviceType.parseOf(key);
                    dMap.put(key, item);
                }
            }
        }
        return dMap.get(key);
    }

    public NetType getNetType(String netType) {
        if (Strings.isNullOrEmpty(netType)) {
            return NetType.Other;
        }
        String key = netType.trim();
        if (!netMap.containsKey(key)) {
            synchronized (LOCK) {
                if (!netMap.containsKey(key)) {
                    NetType item = NetType.parseOf(key);
                    netMap.put(key, item);
                }
            }
        }
        return netMap.get(key);
    }

    public OsType getOsType(String os) {
        if (Strings.isNullOrEmpty(os)) {
            return OsType.Other;
        }
        String key = os.trim();
        if (!osMap.containsKey(key)) {
            synchronized (LOCK) {
                if (!osMap.containsKey(key)) {
                    OsType item = OsType.parseOf(key);
                    osMap.put(key, item);
                }
            }
        }
        return osMap.get(key);
    }

    private UserAgentInfo buildUserAgentInfo(Os os, Browser browser, Device device, Pair<String, NetType> netTypePair, String deviceId) {
        UserAgentInfo userAgentInfo = new UserAgentInfo();
        String detail;
        String version;

        // OS to OsInfo
        if (StringUtils.isEmpty(os.getMajor())) {
            detail = os.getFamily();
        } else {
            detail = StringUtils.isEmpty(os.getMinor()) ? os.getFamily() + " " + os.getMajor()
                    : os.getFamily() + " " + os.getMajor() + "." + os.getMinor();
        }
        version = detail.replace(os.getBrand(), "").trim();
        userAgentInfo.setOsName(os.getBrand());
        userAgentInfo.setOsDetail(detail);
        userAgentInfo.setOsType(getOsType(os.getBrand()).getValue());
        userAgentInfo.setOsVersion(version);

        // Browser to BrowserInfo
        if (StringUtils.isEmpty(browser.getMajor())) {
            detail = browser.getFamily();
        } else {
            detail = StringUtils.isEmpty(browser.getMinor()) ? browser.getFamily() + " " + browser.getMajor()
                    : browser.getFamily() + " " + browser.getMajor() + "." + browser.getMinor();
        }
        userAgentInfo.setBrowserName(browser.getBrand());
        userAgentInfo.setBrowserDetail(detail);

        String deviceType = device.getDeviceType().toString();
        userAgentInfo.setDeviceBrand(device.getBrand());
        userAgentInfo.setDeviceName(device.getFamily());
        userAgentInfo.setDeviceType(deviceType);
        userAgentInfo.setIsMobile(device.isMobile());
        userAgentInfo.setIntDeviceType(getDeviceType(deviceType).getValue());

        userAgentInfo.setNetType(netTypePair.getKey());
        userAgentInfo.setIntNetType(netTypePair.getValue().getValue());
        return userAgentInfo;
    }
}
