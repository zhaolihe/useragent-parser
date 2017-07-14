package com.tiger.useragent;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/9
 */
class DeviceParser {
    private List<DevicePattern> patterns;

    DeviceParser(List<DevicePattern> patterns) {
        this.patterns = patterns;
    }

    public static List<DevicePattern> patternsFromList(List<Map<String, String>> configList) {
        List<DevicePattern> configPatterns = Lists.newArrayList();
        for (Map<String, String> configMap : configList) {
            configPatterns.add(DevicePattern.patternFromMap(configMap));
        }
        return configPatterns;
    }

    public Device parse(String agentString) {
        if (Strings.isNullOrEmpty(agentString)) {
            return Device.DEFAULT_PC_SCREEN;
        }

        Device device;
        for (DevicePattern p : patterns) {
            if(p.match(agentString)!=null){
                String aa = "hello";
            }
            if ((device = p.match(agentString)) != null) {
                return device;
            }
        }
        return Device.DEFAULT_PC_SCREEN;
    }
}
