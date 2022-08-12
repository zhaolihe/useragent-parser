package com.tiger.useragent;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceParserTest {

  @Test
  public void testMIUseragent() {
    String uaExpr =
        "Dalvik/1.6.0+(Linux;+U;+Android+4.4.4;+MI+NOTE+LTE+MIUI/V6.6.7.0.KXECNCF)"
            .replace("+", " ");

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
