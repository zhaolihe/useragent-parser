package com.tiger.useragent;

import com.tiger.useragent.browser.Browser;
import com.tiger.useragent.browser.BrowserParser;
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
import static org.junit.Assert.assertTrue;

public class BrowserParserTest {
  private static final String CONFIG_FILE = "/BrowserConfig.yaml";
  private static BrowserParser parser;

  @BeforeClass
  public static void init() throws IOException {
    Yaml yaml = new Yaml(new SafeConstructor());
    try (InputStream stream = Browser.class.getResourceAsStream(CONFIG_FILE)) {
      List<Map<String, String>> props = (List<Map<String, String>>) yaml.load(stream);
      parser = BrowserParser.fromList(props);
    }
  }

  private static Browser parse(String ua) {
    if (parser == null) {
      throw new IllegalStateException("init parser error");
    }
    return parser.parse(ua);
  }

  @Test
  public void testBrowser() {
    String uaString =
        "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.55 Safari/534.3";

    Browser browser = parse(uaString);
    assertThat(browser.getBrand(), is("Chrome"));
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
      Browser browser = parse(ua);
      System.out.println(
          browser.getBrand()
              + "$$"
              + browser.getFamily()
              + " "
              + browser.getMajor()
              + "."
              + browser.getMinor());
    }
  }

  @Test
  public void test360Browser() {
    String ua = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)";
    String regex = "(360[SE]E)";

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(ua);
    int groupCount = matcher.groupCount();
    if (matcher.find()) {
      String group = matcher.group(1);
      assertThat(group, is("360SE"));
    }
  }

  @Test
  public void testQQ() {
    String ua =
        "Mozilla/5.0 (Linux; Android 5.1; OPPO A59s Build/LMY47I; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.121 Mobile Safari/537.36 Youku/6.7.1 (Android 5.1; Bridge_SDK; GUID 0bb61e7bf6aa7407dbe522639f1ba6e2; UTDID VoVRAiABneUDAPM9QqSQmvl0;)"
            .toLowerCase();
    String regex = "(qq)[ /]([\\d.]*)";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(ua);
    if (matcher.find()) {
      String group = matcher.group(0);
      assertThat(group, is("QQ"));
    }
    ua =
        "Mozilla/5.0 (Linux; U; Android 2.3.5; zh-cn; D510 Build/MocorDroid2.3.5) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 V1_AND_SQ_4.6.1_9_YYB_D QQ/5.3.1.660 NetType/WIFI 10000507"
            .toLowerCase();
    Pattern p1 = Pattern.compile(regex);
    Matcher matcher1 = p1.matcher(ua);
    if (matcher1.find()) {
      String group = matcher1.group(1);
      assertThat(group, is("qq"));
    }
  }

  @Test
  public void testChrome() {
    String ua =
        "Mozilla/5.0 (Linux; Android 5.1.1; vivo X9 Build/HQU06P; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome";
    Browser browser = parse(ua);
    assertThat(browser.getBrand(), is("Chrome"));
  }

  @Test
  public void testFunshion() {
    String ua = "Funshion/2.7.1.4 (Android/4.2.2; aphone; T200-C)";
    Browser browser = parse(ua);
    assertThat(browser.getBrand(), is("Funshion"));
  }

  @Test
  public void testImgoTV() {
    String ua = "ImgoTV-ipad/4.3.0 (iPad; iOS 8.3; Scale/2.00)";
    Browser browser = parse(ua);
    assertThat(browser.getBrand(), is("ImgoTV"));
  }

  @Test
  public void testQQLive() {
    String ua = "qqlive/4799 CFNetwork/672.0.8 Darwin/14.0.0";
    Browser browser = parse(ua);
    assertThat(browser.getBrand(), is("qqlive"));
  }

  @Test
  public void testDouban() {
    String ua =
        "Mozilla/5.0 (Linux; Android 7.0; MHA-AL00 Build/HUAWEIMHA-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36 udid/8a7b587adcd34ee50a74dbd86dd3b548536cd7d2 com.douban.frodo/4.11.4(90) DoubanApp";
    String regex = "(DoubanApp)";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(ua);
    if (matcher.find()) {
      String group = matcher.group(0);
      assertThat(group, is("DoubanApp"));
    }
  }

  @Test
  public void testApp() {
    String ua = "墨迹天气/5007050402 CFNetwork/711.3.18 Darwin/14.0.0";
    Browser browser = parse(ua);
    String expect = "墨迹天气";
    assertThat(browser.getBrand(), is(expect));
    String regex = "([A-Za-z\\-_\\u4e00-\\u9fa5]+)[/| ]+[\\d|.| |\\w]*(?:CFNetwork/)";
    Matcher matcher = Pattern.compile(regex).matcher(ua);
    assertTrue(matcher.find());
    String actual = matcher.group(1);
    assertThat(actual, is(expect));
  }

  @Test
  public void testApp2() {
    String ua = "live4iphone 5.8.6 rv:20828 (iPhone; iOS 11.4; zh-Hans_AL)";
    String regex = "(([A-Za-z\\d\\!\\-_\u4e00-\u9fa5]+)) (\\d+)[\\.\\d]* (?:rv\\:\\d+)";
    Matcher matcher = Pattern.compile(regex).matcher(ua);
    assertTrue(matcher.find());
    String actual = matcher.group(1);
    assertThat(actual, is("live4iphone"));
  }
}
