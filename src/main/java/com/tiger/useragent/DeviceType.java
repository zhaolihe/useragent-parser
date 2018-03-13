package com.tiger.useragent;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/9
 */
public enum  DeviceType {
    PC("pc"),Pad("pad"),Phone("phone"),Spider("spider"),TV("tv");
    /*x-box*/

    DeviceType(String value){
        this.value = value;
    }

    public static DeviceType parseOf(String value){
        for(DeviceType deviceType : DeviceType.values()){
            if(deviceType.value.equalsIgnoreCase(value)){
                return deviceType;
            }
        }

        /*default value*/
        return Phone;
    }

    private final String value;
}
