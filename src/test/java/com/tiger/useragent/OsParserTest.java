package com.tiger.useragent;

import com.tiger.useragent.browser.Browser;
import com.tiger.useragent.enums.DeviceType;
import com.tiger.useragent.os.Os;
import com.tiger.useragent.os.OsParser;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OsParserTest {
  private static final String CONFIG_FILE = "/OSConfig.yaml";
  private static OsParser parser;

  @BeforeClass
  public static void init() throws IOException {
    Yaml yaml = new Yaml(new SafeConstructor());
    try (InputStream stream = Browser.class.getResourceAsStream(CONFIG_FILE)) {
      List<Map<String, String>> props = (List<Map<String, String>>) yaml.load(stream);
      parser = OsParser.fromList(props);
    }
  }

  private static Os parse(String ua) {
    if (parser == null) {
      throw new IllegalStateException("init parser error");
    }
    return parser.parse(ua);
  }

  @Test
  public void testBrowser() {
    String uaString =
        "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.55 Safari/534.3";

    Os os = parse(uaString);
    assertThat(os.getBrand(), is("Windows"));
  }

  @Test
  public void testSample() {
    String[] uas =
        new String[] {
          "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
          "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
          "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0",
          "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)",
          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
          "Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
          "Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11",
          "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)",
          "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)",
          "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)",
          "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)",
          "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)",
          "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
          "Mozilla/5.0 (Linux; U; Android 2.3.7; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
          "MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
          "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11",
          "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER",
          "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0"
        };
    for (String ua : uas) {
      Os os = parse(ua);
      System.out.println(
          os.getBrand() + "$$" + os.getFamily() + " " + os.getMajor() + "." + os.getMinor());
    }
  }

  @Test
  public void testEnum() {
    String expr = "Spider";

    DeviceType type = DeviceType.valueOf(expr);
    assertThat(type, is(DeviceType.Spider));
    assertThat(type.name(), is(expr));
  }

  @Test
  public void testNetType() {
    String uaExpr =
        "Mozilla/5.0 (Linux; U; Android 2.3.5; zh-cn; D510 Build/MocorDroid2.3.5) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 V1_AND_SQ_4.6.1_9_YYB_D QQ/5.3.1.660 NetType/WIFI 10000507";
    Pattern netTypePattern = Pattern.compile("nettype[ /](\\w*)", Pattern.CASE_INSENSITIVE);
    Matcher matcher = netTypePattern.matcher(uaExpr);
    if (matcher.find()) {
      String netType = matcher.group(1);
      assertThat(netType, is("WIFI"));
    }
  }
}
