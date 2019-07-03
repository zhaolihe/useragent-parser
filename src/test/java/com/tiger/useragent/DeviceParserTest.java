package com.tiger.useragent;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.tiger.useragent.DeviceParserTest
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017.05.22
 */
public class DeviceParserTest {

    @Test
    public void testMIUseragent() {
        String uaExpr = "Dalvik/1.6.0+(Linux;+U;+Android+4.4.4;+MI+NOTE+LTE+MIUI/V6.6.7.0.KXECNCF)".replace("+", " ");

        String regex = "\\b(MI)[_\\- ](\\w+)";
        Pattern pattern1 = Pattern.compile(regex);
        Matcher matcher = pattern1.matcher(uaExpr);
        if (matcher.find()) {
            String key = matcher.group(1);
            System.out.println(key);
        }
    }

    @Test
    public void testMeizu() {
        String uaExpr = "Dalvik/2.1.0+(Linux;+U;+Android+6.0;+M570C+Build/MRA58K)".replace("+", " ");
        String regex = "(M570)(C|P|M)\\s\\w+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(uaExpr);
        if (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            System.out.println(key + "&&" + value);
        }
    }

}
