package com.tiger.useragent;


import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/9
 */
public class MobileParser {

    public static Map<String, Map<String, String>> mapForFile(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))) {
            Map<String, Map<String, String>> mapForfile = Maps.newHashMap();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                Map<String, String> stringStringMap;
                if (mapForfile.containsKey(split[0])) {
                    stringStringMap = mapForfile.get(split[0]);
                } else {
                    stringStringMap = Maps.newHashMap();
                }
                stringStringMap.put(split[1], split[2]);
                mapForfile.put(split[0], stringStringMap);
            }
            return mapForfile;
        }
    }

    public static float getScreenSize(String brand, String family) {
        if (family.equalsIgnoreCase("iPhone")) {
            return 0;
        }
        Map<String, Map<String, String>> mobileParser = Parser.mobileParser;
        if (mobileParser.containsKey(brand.toUpperCase())) {
            Map<String, String> stringStringMap = mobileParser.get(brand.toUpperCase());
            if (stringStringMap.containsKey(family.toUpperCase())) {
                return Float.parseFloat(stringStringMap.get(family.toUpperCase()));
            }
        }
        return 0;
    }
}
