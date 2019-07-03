package com.tiger.useragent;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.tiger.useragent.Constant.DEFAULT_VALUE;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/9
 */
public class DeviceMap {
    private Map<String, Device> map;

    public static final int DEVICE_TYPE_LENGTH = 5;

    public static final int DEVICE_FAMILY = 0;
    public static final int DEVICE_BRAND = 1;
    public static final int RE_DEVICE_FAMILY = 2;
    public static final int DEVICE_TYPE = 3;
    public static final int DEVICE_SCREEN_SIZE = 4;


    DeviceMap(Map<String, Device> map) {
        this.map = map;
    }

    public static DeviceMap mapFromFile(InputStream stream) throws IOException {
         Map<String, Device> map = new HashMap<>();

        try (InputStream inputStream = Parser.class.getResourceAsStream("/DeviceDictionary_Auto.txt")){
            fillMap(inputStream, map);
        }

        fillMap(stream, map);

        return new DeviceMap(map);
    }

    private static void fillMap(InputStream stream, Map<String, Device> map) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "utf-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!isCommentOrBlank(line)) {
                    String[] items = line.split(",,");
                    if (items.length == DEVICE_TYPE_LENGTH) {
                        String key = items[DEVICE_FAMILY];
                        DeviceType deviceType = DeviceType.parseOf(items[DEVICE_TYPE]);
                        map.put(key, new Device(items[DEVICE_BRAND], items[RE_DEVICE_FAMILY], deviceType, true, items[DEVICE_SCREEN_SIZE]));
                    }
                }
            }
        }
    }

    /**
     * verify input expression to invalid
     *
     * @param line
     * @return
     */
    private static boolean isCommentOrBlank(String line) {
        return line.startsWith("#") || StringUtils.isBlank(line) || Strings.isNullOrEmpty(line);
    }

    public Device parseDevice(Device device) {
        if (device.deviceType.equals(DeviceType.PC) ||
                device.deviceType.equals(DeviceType.Spider) ||
                device.family == null ||
                "-".equals(device.family)) {
            return device;
        }

        String replaceFamily = StringUtils.trimToEmpty(StringUtils.replaceEach(device.family, new String[]{"\u3000"}, new String[]{""}));

        if ("".equals(replaceFamily)) {
            return new Device(device.brand, DEFAULT_VALUE, device.deviceType, device.isMobile, DEFAULT_VALUE);
        }

        if ("".equals(replaceFamily.replaceAll("/", ""))) {
            return device;
        }
        String family = device.family.split("/")[0].replace('_', ' ').toUpperCase();
        if (map.containsKey(family)) {
            return map.get(family);
        }
        return device;
    }
}
