package com.tiger.useragent;

import com.google.common.base.Strings;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tiger.useragent.Constant.*;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/8
 */
class OsPattern {
    private final Pattern pattern;
    private final String brandReplacement;
    private final String osReplacement;
    private final String majorReplacement;
    private final String minorReplacement;
    private final boolean isMobileReplacement;
    private final boolean isTVReplacement;

    OsPattern(Pattern pattern, String brandReplacement, String osReplacement, String majorReplacement, String minorReplacement,
              boolean isMobileReplacement, boolean isTVReplacement) {
        this.pattern = pattern;
        this.brandReplacement = brandReplacement;
        this.osReplacement = osReplacement;
        this.majorReplacement = majorReplacement;
        this.minorReplacement = minorReplacement;
        this.isMobileReplacement = isMobileReplacement;
        this.isTVReplacement = isTVReplacement;
    }

    public static OsPattern patternFromMap(Map<String, String> configMap) {
        String regex = configMap.get(REGEX);
        if (Strings.isNullOrEmpty(regex)) {
            throw new IllegalArgumentException("OS is missing regex");
        }
        String brand = configMap.get(BRAND);
        String os = configMap.get(OS);
        String major = configMap.get(MAJOR);
        String minor = configMap.get(MINOR);
        String isMobileExpr = configMap.get(IS_MOBILE);
        String isTVExpr = configMap.get(IS_TV);
        boolean isMobile = false;
        if (!Strings.isNullOrEmpty(isMobileExpr) && "true".equals(isMobileExpr.toLowerCase())) {
            isMobile = true;
        }
        boolean isTv = false;
        if (!Strings.isNullOrEmpty(isTVExpr) && "true".equals(isTVExpr.toLowerCase())) {
            isTv = true;
        }
        return new OsPattern(Pattern.compile(regex), brand, os, major, minor, isMobile, isTv);

    }

    public Os match(String useragent) {
        String brand, family = null, major = null, minor = null;
        Matcher matcher = pattern.matcher(useragent);
        if (!matcher.find()) {
            return null;
        }

        int groupCount = matcher.groupCount();
        if (!Strings.isNullOrEmpty(osReplacement)) {
            family = this.osReplacement;
        } else if (groupCount > 0) {
            family = matcher.group(1);
        }

        if (!Strings.isNullOrEmpty(brandReplacement)) {
            brand = brandReplacement;
        } else {
            brand = family;
        }

        if (!Strings.isNullOrEmpty(majorReplacement)) {
            major = majorReplacement;
        } else if (groupCount > 2) {
            major = matcher.group(2);
        }

        if (!Strings.isNullOrEmpty(minorReplacement)) {
            minor = minorReplacement;
        } else if (groupCount > 3) {
            minor = matcher.group(3);
        }

        return Strings.isNullOrEmpty(family) ? null : (new Os(brand, family, major, minor, isMobileReplacement, isTVReplacement));
    }
}
