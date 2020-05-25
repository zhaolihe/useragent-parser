package com.tiger.useragent.device;

import com.google.common.base.Strings;
import com.tiger.useragent.enums.DeviceType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tiger.useragent.Constant.*;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/9
 */
public class DevicePattern {
    private final Pattern pattern;
    private final String brandReplacement;
    private final String deviceReplacement;
    private final DeviceType deviceTypeReplacement;
    private final boolean isMobileReplacement;

    private static String[] deviceType;

    /**
     * static code snippet,which is using to initiate variable
     */
    static {
        DeviceType[] deviceTypes = DeviceType.values();
        deviceType = new String[deviceTypes.length];
        /*fill array with all of device-type's name*/
        for (int i = 0, length = deviceTypes.length; i < length; i++) {
            deviceType[i] = deviceTypes[i].name();
        }
    }

    DevicePattern(Pattern pattern, String brandReplacement, String deviceReplacement, DeviceType deviceTypeReplacement,
                  boolean isMobileReplacement) {
        this.pattern = pattern;
        this.brandReplacement = brandReplacement;
        this.deviceReplacement = deviceReplacement;
        this.deviceTypeReplacement = deviceTypeReplacement;
        this.isMobileReplacement = isMobileReplacement;
    }

    public static DevicePattern patternFromMap(Map<String, String> configMap) {
        String regex = configMap.get(REGEX);
        if (Strings.isNullOrEmpty(regex)) {
            throw new IllegalArgumentException("Device is missing regex" + regex);
        }

        String brand = configMap.get(BRAND);
        String family = configMap.get(DEVICE);
        String isMobileString = configMap.get(IS_MOBILE);
        boolean isMobile = true;
        if (isMobileString != null && isMobileString.equalsIgnoreCase("false")) {
            isMobile = false;
        }
        String deviceTypeString = configMap.get(DEVICE_TYPE);
        DeviceType type = DeviceType.parseOf(deviceTypeString);
        ArrayUtils.contains(deviceType, deviceTypeString);
        return new DevicePattern(Pattern.compile(regex), brand, family, type, isMobile);
    }

    public Device match(String agentString) {
        Matcher matcher = pattern.matcher(agentString);
        if (!matcher.find()) {
            return null;
        }
        int groupCount = matcher.groupCount();

        String brand = null, family = null;
        DeviceType deviceType = deviceTypeReplacement;
        boolean isMobile = isMobileReplacement;
        if (deviceType == DeviceType.Other && isMobile) {
            deviceType = DeviceType.Phone;
        }

        if (!Strings.isNullOrEmpty(brandReplacement)) {
            brand = brandReplacement;
        } else if (groupCount > 0) {
            brand = matcher.group(1);
        }

        // Only $1, $2 are supported.
        if (deviceReplacement != null) {
            family = deviceReplacement;
            if (deviceReplacement.contains("$1") && groupCount >= 0 && matcher.group(1) != null) {
                family = family.replaceFirst("\\$1", Matcher.quoteReplacement(matcher.group(1)));
            }

            if (deviceReplacement.contains("$2") && groupCount >= 2 && matcher.group(2) != null) {
                family = family.replaceFirst("\\$2", Matcher.quoteReplacement(matcher.group(2)));
            }
        } else if (groupCount >= 2) {
            family = matcher.group(2);
        }
        String family1 = family;
        if (family1 == null) {
            family1 = DEFAULT_VALUE;
        }
//        String screenSize = parseScreenSize(agentString);
        if (null == family) {
            family = "";
        }
        return brand == null ? null : new Device(brand, family, deviceType, isMobile, DEFAULT_VALUE);
    }

    private String parseScreenSize(String agentString) {
        Matcher matcher = screenSizePattern.matcher(agentString);
        return matcher.find() ? matcher.group(1) : DEFAULT_VALUE;
    }

    private final static Pattern screenSizePattern = Pattern.compile("\\W(\\d{3,4}[x\\*]\\d{3,4})\\W*", Pattern.CASE_INSENSITIVE);
}
