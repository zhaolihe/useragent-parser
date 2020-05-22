package com.tiger.useragent.browser;

import com.google.common.base.Strings;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tiger.useragent.Constant.*;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/5
 */
class BrowserPattern {
    private final Pattern pattern;
    private final String brandReplacement;
    private final String familyReplacement;
    private final String majorReplacement;
    private final String minorReplacement;

    BrowserPattern(Pattern pattern,String brandReplacement,String familyReplacement,String majorReplacement,String minorReplacement){
        this.pattern = pattern;
        this.brandReplacement = brandReplacement;
        this.familyReplacement = familyReplacement;
        this.majorReplacement = majorReplacement;
        this.minorReplacement = minorReplacement;
    }

    public static BrowserPattern patternFromMap(Map<String,String> configMap){
        String regex = configMap.get(REGEX);
        if(regex==null){
            throw  new IllegalArgumentException("browser's regex is lose");
        }

        String brand = configMap.get(BRAND);
        String family = configMap.get(FAMILY);
        String major = configMap.get(MAJOR);
        String minor = configMap.get(MINOR);

        return new BrowserPattern(Pattern.compile(regex),brand,family,major,minor);
    }

    public Browser match(String uaString){
        String family = null,major=null,minor=null;
        Matcher matcher = pattern.matcher(uaString.toLowerCase());
        if (!matcher.find()) {
            return null;
        }

        int groupCount = matcher.groupCount();
        if (familyReplacement != null) {
            family = familyReplacement;
        } else if (groupCount > 0) {
            /**
             * if groupCount equals to one,need to run
             * example:
             *    regex: (360[SE]E)
             *    useragent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)
             **/
            /* this is matcher.group(1) because of matcher.group(0) is the full expression */
            family = matcher.group(1);

        }

        String brand = (Strings.isNullOrEmpty(brandReplacement)) ? family : brandReplacement;
        if (!Strings.isNullOrEmpty(majorReplacement)) {
            major = majorReplacement;
        } else if (groupCount > 1) {
            major = matcher.group(2);
        }

        if (!Strings.isNullOrEmpty(minorReplacement)) {
            minor = minorReplacement;
        } else if (groupCount > 2) {
            minor = matcher.group(3);
        }

        return Strings.isNullOrEmpty(family) ? null : new Browser(brand, family, major, minor);
    }
}
