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
 * date : 2017/5/5
 */
class BrowserParser {
    private final List<BrowserPattern> patterns;

    BrowserParser(List<BrowserPattern> patterns) {
        this.patterns = patterns;
    }

    public static BrowserParser fromList(List<Map<String, String>> configList) {
        List<BrowserPattern> configPatterns = Lists.newArrayList();
        for (Map<String, String> configMap : configList) {
            configPatterns.add(BrowserPattern.patternFromMap(configMap));
        }
        return new BrowserParser(configPatterns);
    }

    public Browser parse(final String uaString){
        if(Strings.isNullOrEmpty(uaString)){
            return Browser.DEFAULT_BROWSER;
        }

        Browser browser;
        for (BrowserPattern p : patterns) {
            if ((browser = p.match(uaString))!=null) {
                return browser;
            }
        }
        return Browser.DEFAULT_BROWSER;
    }
}
