package com.tiger.useragent;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
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

    private final static Pattern pattern = Pattern.compile("\\.net( clr | client )?(?<ver>\\d(\\.\\d)?)(\\.\\d+)*[ce;$) ]", Pattern.CASE_INSENSITIVE);
    private final static Pattern netTypePattern =Pattern.compile("\\W(WIFI|5G|4G|3G|2G)\\W*",Pattern.CASE_INSENSITIVE);
    private final static Pattern screenSizePattern = Pattern.compile("\\W(\\d{3,4}x\\d{3,4})\\W*",Pattern.CASE_INSENSITIVE);
    private final static Pattern deviceIdPattern = Pattern.compile("[\\s&;\"](deviceid|deviceId|DEVICE|device|sdk_guid|GUID|guid|Id|ID|id|udid|UDID)[\" /:=]+([\\w-]+)",Pattern.CASE_INSENSITIVE);
    public static Map<String, Map<String, String>> mobileParser;

    public Parser() throws IOException {
        readConfigs();
    }

    private void readConfigs() throws IOException {

        mobileParser = MobileParser.mapForFile(Parser.class.getResourceAsStream("/MobileDictionary.txt"));

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
        if (device.deviceType.equals(DeviceType.Spider)) {
            return buildUserAgentInfo(Os.DEFAULT_OS, Browser.DEFAULT_BROWSER, device,DEFAULT_VALUE,DEFAULT_VALUE);
        }
        Os os = parseOS(agentString);
        Browser browser = parseBrowser(agentString);
//        String dotNet = parseDotNet(agentString);
        String netType = parseNetType(agentString);
        String deviceId = parseDeviceId(agentString);
        if (os == null) {
            os = Os.DEFAULT_OS;

        } else if (os.isTv) {
            device = Device.DEFAULT_TV;
        } else if (os.isMobile && !device.isMobile && !(device.deviceType == DeviceType.TV)) {
            device = Device.DEFAULT_PHONE_SCREEN;
        }

        return buildUserAgentInfo(os, browser, device, netType, deviceId);
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

    public String parseNetType(String agentString) {
        Matcher matcher = netTypePattern.matcher(agentString.toUpperCase());
        String result="";
        if(matcher.find()){
            result = matcher.group(1);
        }
        return Strings.isNullOrEmpty(result)? DEFAULT_VALUE:result;
    }

    public String parseDeviceId(String agentString){
        Matcher matcher = deviceIdPattern.matcher(agentString.toLowerCase());
        String result ="";
        if(matcher.find()){
            result=matcher.group(2);
        }
        return Strings.isNullOrEmpty(result) || result.length()<8 ? DEFAULT_VALUE : result;
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

    private UserAgentInfo buildUserAgentInfo(Os os, Browser browser, Device device, String netType,String deviceId) {
        UserAgentInfo userAgentInfo = new UserAgentInfo();
        String detail;

        // OS to OsInfo
        if (StringUtils.isEmpty(os.major)) {
            detail = os.family;
        } else {
            detail = StringUtils.isEmpty(os.minor) ? os.family + " " + os.major
                    : os.family + " " + os.major + "." + os.minor;
        }
        userAgentInfo.setOsName(os.brand);
        userAgentInfo.setOsDetail(detail);

        // Browser to BrowserInfo
        if (StringUtils.isEmpty(browser.major)) {
            detail = browser.family;
        } else {
            detail = StringUtils.isEmpty(browser.minor) ? browser.family + " " + browser.major
                    : browser.family + " " + browser.major + "." + browser.minor;
        }
        userAgentInfo.setBrowserName(browser.brand);
        userAgentInfo.setBrowserDetail(detail);

        // Device to DeviceInfo
        if (!device.brand.equalsIgnoreCase("PC") && !device.brand.equals(DEFAULT_VALUE) && !device.deviceType.equals(DeviceType.Spider)) {
            detail = device.brand + " " + device.family;
        } else {
            detail = device.family;
        }
        userAgentInfo.setDeviceBrand(device.brand);
        userAgentInfo.setDeviceName(detail);
        userAgentInfo.setDeviceType(device.deviceType.toString());
        userAgentInfo.setIsMobile(device.isMobile);

        userAgentInfo.setNetType(netType);
        userAgentInfo.setDeviceId(deviceId);
        return userAgentInfo;
    }
}
