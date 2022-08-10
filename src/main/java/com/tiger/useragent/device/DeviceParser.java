package com.tiger.useragent.device;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeviceParser {
  private final List<DevicePattern> patterns;

  public DeviceParser(List<DevicePattern> patterns) {
    this.patterns = patterns;
  }

  public static List<DevicePattern> patternsFromList(List<Map<String, String>> configList) {
    List<DevicePattern> configPatterns = new ArrayList<>();
    for (Map<String, String> configMap : configList) {
      configPatterns.add(DevicePattern.patternFromMap(configMap));
    }
    return configPatterns;
  }

  public Device parse(String agentString) {
    if (StringUtils.isEmpty(agentString)) {
      return Device.DEFAULT_PC_SCREEN;
    }

    Device device;
    for (DevicePattern p : patterns) {
      if ((device = p.match(agentString)) != null) {
        return device;
      }
    }
    return getDefaultDevice(agentString);
  }

  private Device getDefaultDevice(String input) {
    String lower = input.toLowerCase();

    return lower.contains("ottsdk")
        ? Device.DEFAULT_TV
        : (lower.contains("android") || lower.contains("phone") || lower.contains("mobile")
            ? Device.DEFAULT_PHONE_SCREEN
            : Device.DEFAULT_PC_SCREEN);
  }
}
