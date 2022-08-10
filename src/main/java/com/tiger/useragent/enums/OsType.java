package com.tiger.useragent.enums;

import org.apache.commons.lang3.StringUtils;

public enum OsType {
  Other(0, "-"),
  iOS(1, "ios"),
  Android(2, "android"),
  WinPhone(3, "winphone"),
  Windows(4, "windows"),
  Linux(5, "linux"),
  Chrome(6, "chrome os");

  OsType(final int value, final String name) {
    this.value = value;
    this.name = name;
  }

  public static OsType parseOf(final String name) {
    if (StringUtils.isEmpty(name)) {
      return OsType.Other;
    }

    for (OsType item : OsType.values()) {
      if (name.equalsIgnoreCase(item.name)) {
        return item;
      }
    }
    return OsType.Other;
  }

  public static OsType parseOf(final int value) {
    for (OsType item : OsType.values()) {
      if (value == item.getValue()) {
        return item;
      }
    }
    return OsType.Other;
  }

  public int getValue() {
    return this.value;
  }

  private final int value;
  private final String name;
}
