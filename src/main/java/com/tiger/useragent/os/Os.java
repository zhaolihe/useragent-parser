package com.tiger.useragent.os;

public class Os {
  public static final Os DEFAULT_OS = new Os("-", "-", "0", "0", false, false);
  final String brand;
  final String family;
  final String major;
  final String minor;
  final boolean isMobile;
  final boolean isTv;

  public Os(
      String brand, String family, String major, String minor, boolean isMobile, boolean isTv) {
    this.brand = brand;
    this.family = family;
    this.major = major;
    this.minor = minor;
    this.isMobile = isMobile;
    this.isTv = isTv;
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

  public boolean isMobile() {
    return isMobile;
  }

  public boolean isTv() {
    return isTv;
  }
}
