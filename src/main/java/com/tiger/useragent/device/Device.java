package com.tiger.useragent.device;

import com.tiger.useragent.enums.DeviceType;

import static com.tiger.useragent.Constant.DEFAULT_VALUE;

public class Device {
  public static final Device DEFAULT_PC_SCREEN =
      new Device("PC", DEFAULT_VALUE, DeviceType.PC, false, DEFAULT_VALUE);
  public static final Device DEFAULT_PHONE_SCREEN =
      new Device(DEFAULT_VALUE, DEFAULT_VALUE, DeviceType.Phone, true, DEFAULT_VALUE);
  public static final Device DEFAULT_TV =
      new Device(DEFAULT_VALUE, DEFAULT_VALUE, DeviceType.TV, false, DEFAULT_VALUE);

  final String brand;
  final String family;
  final DeviceType deviceType;
  final boolean isMobile;
  final String screenSize;

  public Device(String brand, String family, DeviceType deviceType, boolean isMobile) {
    this.brand = brand;
    this.family = family;
    this.deviceType = deviceType;
    this.isMobile = isMobile;
    this.screenSize = "";
  }

  public Device(
      String brand, String family, DeviceType deviceType, boolean isMobile, String screenSize) {
    this.brand = brand;
    this.family = family;
    this.deviceType = deviceType;
    this.isMobile = isMobile;
    this.screenSize = screenSize;
  }

  public String getBrand() {
    return brand;
  }

  public String getFamily() {
    return family;
  }

  public DeviceType getDeviceType() {
    return deviceType;
  }

  public boolean isMobile() {
    return isMobile;
  }

  public String getScreenSize() {
    return screenSize;
  }
}
