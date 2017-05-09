package com.tiger.useragent;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/9
 */
class Device {
    public static final Device DEFAULT_PC_SCREEN = new Device("PC", "-", DeviceType.PC, false, 0);
    public static final Device DEFAULT_PHONE_SCREEN = new Device("-", "-", DeviceType.Phone, true, 0);
    public static final Device DEFAULT_TV = new Device("-", "-", DeviceType.TV, false, 0);

    public final String brand;
    public final String family;
    public final DeviceType deviceType;
    public final boolean isMobile;

    public final float screenSize;

    Device(String brand, String family, DeviceType deviceType, boolean isMobile) {
        this.brand = brand;
        this.family = family;
        this.deviceType = deviceType;
        this.isMobile = isMobile;
        this.screenSize = 0;
    }

    Device(String brand, String family, DeviceType deviceType, boolean isMobile, float screenSize) {
        this.brand = brand;
        this.family = family;
        this.deviceType = deviceType;
        this.isMobile = isMobile;
        this.screenSize = screenSize;
    }
}
