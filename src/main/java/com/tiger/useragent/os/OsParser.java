package com.tiger.useragent.os;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OsParser {
  private final List<OsPattern> patterns;

  public OsParser(List<OsPattern> patterns) {
    this.patterns = patterns;
  }

  public static OsParser fromList(List<Map<String, String>> configList) {
    List<OsPattern> configPatterns = new ArrayList<>();

    for (Map<String, String> configMap : configList) {
      configPatterns.add(OsPattern.patternFromMap(configMap));
    }
    return new OsParser(configPatterns);
  }

  public Os parse(String agentString) {
    if (StringUtils.isEmpty(agentString)) {
      return Os.DEFAULT_OS;
    }

    Os os;
    for (OsPattern p : patterns) {
      if ((os = p.match(agentString)) != null) {
        return os;
      }
    }
    return Os.DEFAULT_OS;
  }
}
