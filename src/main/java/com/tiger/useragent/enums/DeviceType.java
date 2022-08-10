package com.tiger.useragent.enums;

import org.apache.commons.lang3.StringUtils;

public enum DeviceType {
  Other(0, "-"),
  Phone(1, "phone"),
  Pad(2, "pad"),
  TV(3, "tv"),
  PC(4, "pc"),
  Spider(5, "spider");

  DeviceType(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public static DeviceType parseOf(final String name) {
    if (StringUtils.isEmpty(name)) {
      return DeviceType.Other;
    }

    for (DeviceType item : DeviceType.values()) {
      if (name.equalsIgnoreCase(item.name)) {
        return item;
      }
    }
    return DeviceType.Other;
  }

  public static DeviceType parseOf(final int value) {
    for (DeviceType item : DeviceType.values()) {
      if (value == item.getValue()) {
        return item;
      }
    }
    return DeviceType.Other;
  }

  public int getValue() {
    return this.value;
  }

  private final int value;
  private final String name;
}
