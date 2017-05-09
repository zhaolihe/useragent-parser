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
class OsParser {
    private final List<OsPattern> patterns;

    OsParser(List<OsPattern> patterns) {
        this.patterns = patterns;
    }

    public static OsParser fromList(List<Map<String, String>> configList) {
        List<OsPattern> configPatterns = Lists.newArrayList();

        for (Map<String, String> configMap : configList) {
            configPatterns.add(OsPattern.patternFromMap(configMap));
        }
        return new OsParser(configPatterns);
    }

    public Os parse(String agentString) {
        if (Strings.isNullOrEmpty(agentString)) {
            return null;
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
