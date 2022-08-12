package com.tiger.useragent.enums;

import org.apache.commons.lang3.StringUtils;

public enum NetType {
  Other(0, "-"),
  Wifi(1, "wifi"),
  _2G(2, "2g"),
  _3G(3, "3g"),
  _4G(4, "4g"),
  _5G(5, "5g");

  NetType(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public static NetType parseOf(final String name) {
    if (StringUtils.isEmpty(name)) {
      return NetType.Other;
    }
    for (NetType item : NetType.values()) {
      if (name.equalsIgnoreCase(item.name)) {
        return item;
      }
    }

    return NetType.Other;
  }

  public static NetType parseOf(final int value) {
    for (NetType item : NetType.values()) {
      if (value == item.getValue()) {
        return item;
      }
    }
    return NetType.Other;
  }

  public int getValue() {
    return this.value;
  }

  private final int value;
  private final String name;
}
