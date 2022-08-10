package com.tiger.useragent.os;

import static com.tiger.useragent.Constant.BRAND;
import static com.tiger.useragent.Constant.IS_MOBILE;
import static com.tiger.useragent.Constant.IS_TV;
import static com.tiger.useragent.Constant.MAJOR;
import static com.tiger.useragent.Constant.MINOR;
import static com.tiger.useragent.Constant.OS;
import static com.tiger.useragent.Constant.REGEX;
import static com.tiger.useragent.Constant.TRUE;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class OsPattern {
  private final Pattern pattern;
  private final String brandReplacement;
  private final String osReplacement;
  private final String majorReplacement;
  private final String minorReplacement;
  private final boolean isMobileReplacement;
  private final boolean isTVReplacement;

  OsPattern(
      Pattern pattern,
      String brandReplacement,
      String osReplacement,
      String majorReplacement,
      String minorReplacement,
      boolean isMobileReplacement,
      boolean isTVReplacement) {
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
    if (StringUtils.isEmpty(regex)) {
      throw new IllegalArgumentException("OS is missing regex");
    }
    String brand = configMap.get(BRAND);
    String os = configMap.get(OS);
    String major = configMap.get(MAJOR);
    String minor = configMap.get(MINOR);
    String isMobileExpr = configMap.get(IS_MOBILE);
    String isTVExpr = configMap.get(IS_TV);
    boolean isMobile = TRUE.equalsIgnoreCase(isMobileExpr);
    boolean isTv = TRUE.equalsIgnoreCase(isTVExpr);
    return new OsPattern(Pattern.compile(regex), brand, os, major, minor, isMobile, isTv);
  }

  public Os match(String useragent) {
    String brand, family, major, minor;
    final Matcher matcher = pattern.matcher(useragent);
    if (!matcher.find()) {
      return null;
    }

    final int groupCount = matcher.groupCount();

    BiFunction<String, Integer, String> func =
        (str, index) -> {
          if (StringUtils.isNotBlank(str)) {
            return str;
          } else if (groupCount > index - 1) {
            return matcher.group(index);
          }
          return null;
        };

    family = func.apply(osReplacement, 1);

    brand = StringUtils.isEmpty(brandReplacement) ? family : brandReplacement;

    major = func.apply(majorReplacement, 2);

    minor = func.apply(minorReplacement, 3);

    return StringUtils.isEmpty(family)
        ? null
        : (new Os(brand, family, major, minor, isMobileReplacement, isTVReplacement));
  }
}
