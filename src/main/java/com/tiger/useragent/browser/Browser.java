package com.tiger.useragent.browser;

public class Browser {
  public static final Browser DEFAULT_BROWSER = new Browser("-", "-", null, null);
  // 品牌
  final String brand;
  final String family;
  final String major;
  final String minor;

  public Browser(String brand, String family, String major, String minor) {
    this.brand = brand;
    this.family = family;
    this.major = major;
    this.minor = minor;
  }

  public String getBrand() {
    return brand;
  }

  public String getFamily() {
    return family;
  }

  public String getMajor() {
    return major;
  }

  public String getMinor() {
    return minor;
  }
}
