package com.tiger.useragent;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.tiger.useragent.enums.DeviceType;
import com.tiger.useragent.enums.NetType;
import com.tiger.useragent.enums.OsType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserAgentParserTest {

  private static UserAgentParser parser;

  @BeforeClass
  public static void init() {
    try {
      parser = UserAgentParser.getInstance();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testUserAgentParser() {
    final String userAgent =
        "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.55 Safari/534.3";
    UserAgentInfo info = parser.getUserAgentInfo(userAgent);
    assertThat(info.getBrowserDetail().toString(), is("Chrome 6.0"));
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
    Function<String, UserAgentParser> function =
        new Function<String, UserAgentParser>() {
          @Override
          public UserAgentParser apply(String s) {
            try {
              return UserAgentParser.newInstance();
            } catch (IOException e) {
              e.printStackTrace();
              return null;
            }
          }
        };
    List<String> systems = Arrays.asList("Mac", "Windows", "iOS", "Android");
    List<String> browsers =
        Arrays.asList(
            "Safari", "IE", "Firefox", "Chrome", "Opera", "Maxthon", "QQ", "360", "android",
            "Taobao", "LieBao", "Sogou");
    List<String> deviceTypes = Arrays.asList("PC", "Phone", "Pad");
    UserAgentParser ua = function.apply("");
    for (String str : uas) {
      UserAgentInfo info = ua.getUserAgentInfo(str);
      assertNotNull(info);
      assertTrue(systems.contains(info.getOsName()));
      assertTrue(browsers.contains(info.getBrowserName()));
      assertTrue(deviceTypes.contains(info.getDeviceType()));
    }
  }

  @Test
  public void testNullException() {
    String userAgent =
        "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)";
    UserAgentInfo info = parser.getUserAgentInfo(userAgent);
    assertNotNull(info);
    assertThat(info.getOsDetail().toString(), is("Windows 7"));
    assertThat(info.getBrowserDetail().toString(), is("IE 8"));
  }

  @Test
  public void testGIONEE() {
    String uaExpr =
        "Mozilla/5.0 (Linux; U; Android 5.1; zh-cn;GIONEE-GN3003/GN3003 Build/IMM76D) AppleWebKit534.30(KHTML,like Gecko)Version/4.0 Mobile Safari/534.30 Id/25E4601A243673900E601298CB629449 RV/5.0.16";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
  }

  @Test
  public void testXIAOMIMX() {
    String uaExpr =
        "Mozilla/5.0 (Linux; Android 5.0.2; XiaoMi M5 Note Build/RJV90N; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getDeviceBrand().toString(), is("Meizu"));
    assertThat(info.getDeviceName().toString(), is("M5 note"));

    uaExpr =
        "Mozilla/5.0 (Linux; Android 5.1.1; XiaoMi Mi-4c Build/RBL97Q; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome";
    info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getDeviceBrand().toString(), is("Xiaomi"));
    assertThat(info.getDeviceName().toString(), is("4c"));

    uaExpr = "Dalvik/2.1.0 (Linux; U; Android 4.4.2; OnePlus OnePlus A3000 Build/S4CNPU";
    info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getDeviceBrand().toString(), is("OnePlus"));
    assertThat(info.getDeviceName().toString(), is("3T"));

    uaExpr =
        "Mozilla/5.0 (Linux; U; Android 4.0.3; zh-CN; E6883 Build/32.4.A.0.160) AppleWebKit/537.36 (KHTML,like Gecko) Version/4.0 Chrome/40.0.2214.89 UCBrowser/11.4.1.939 Mobile Safari/537.36";
    info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getDeviceBrand().toString(), is("Sony"));
    assertThat(info.getDeviceName().toString(), is("E6883"));

    uaExpr =
        "Mozilla/5.0 (Linux; Android 5.1.1; Mi-4c Build/LMY47V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36 wkbrowser 4.1.93 3113";
    info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getDeviceBrand().toString(), is("Xiaomi"));
    assertThat(info.getDeviceName().toString(), is("4c"));
  }

  @Test
  public void testVivo() {
    String uaExpr =
        "Mozilla%2F5.0%20(Linux%3B%20Android%205.1.1%3B%20vivo%20Xplay5A%20Build%2FLMY47V)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Version%2F4.0%20Chrome%2F39.0.0.0%20Mobile%20Safari%2F537.36";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getDeviceBrand().toString(), is("vivo"));
    assertThat(info.getDeviceName().toString(), is("vivo 20Xplay5A"));
    uaExpr = "OTTSDK;1.1.0.20;Android;4.0.4;ideatv A210";
    info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
  }

  @Test
  public void testVivoX9() {
    String uaExpr =
        "Mozilla/5.0 (Linux; U; Android 4.0.3; zh-CN; X9 Build/KOT49H) AppleWebKit/537.36 (KHTML,like Gecko) Version/4.0 Chrome/40.0.2214.89 UCBrowser/11.5.2.942 Mobile Safari/537.36";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
  }

  @Test
  public void testHTC() {
    String uaExpr =
        "Mozilla/5.0 (Linux; Android 4.3; HTC X920e Build/JSS15J) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.83 Mobile Safari/537.36";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getDeviceBrand().toString(), is("HTC"));
  }

  @Test
  public void testFunshion() {
    String uaExpr = "Funshion/3.2.12.2 (ios/9.0); iphone; iPhone8.2)";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getBrowserName().toString(), is("Funshion"));
  }

  @Test
  public void testHonor8() {
    String uaExpr =
        "Mozilla/5.0(Linux;Android7.0;zh-cn;FRD-DL00Build/FRD-DL00)AppleWebKit/534.30(KHTML,likeGecko)Version/4.0MobileSafari/534.30)";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getDeviceName().toString(), is("荣耀 8"));
    uaExpr =
        "UCWEB/2.0 (MIDP-2.0; U; zh-CN; VKY-AL00) U2/1.0.0 UCBrowser/10.7.2.940  U2/1.0.0 Mobile";
    info = parser.getUserAgentInfo(uaExpr);
    assertThat(info.getDeviceName().toString(), is("P10 Plus"));
  }

  @Test
  public void testQQ() {
    String uaExpr =
        "Mozilla/5.0 (Linux; U; Android 2.3.5; zh-cn; D510 Build/MocorDroid2.3.5) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 V1_AND_SQ_4.6.1_9_YYB_D QQ/5.3.1.660 NetType/WIFI 10000507";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getBrowserName().toString(), is("QQ"));
    assertThat(info.getNetType().toString(), is("WIFI"));
  }

  @Test
  public void testQingtingFM() {
    String uaExpr = "Android-QingtingFM Dalvik/2.1.0 (Linux; U; Android 6.0; M621C Build/MRA58K)";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getBrowserName().toString(), is("QingtingFM"));
  }

  @Test
  public void testFanli() {
    String ua =
        "Fanli/5.6.0.23 (HUAWEI HUAWEI NXT-AL10; Android 6.0; zh_CN; ID:2-32500458-61897855445999-12-11)";
    UserAgentInfo info = parser.getUserAgentInfo(ua);
    assertNotNull(info);
    assertThat(info.getBrowserName().toString(), is("Fanli"));
  }

  @Test
  public void testUnicom() {
    String ua =
        "Mozilla/5.0 (Linux; Android 6.0.1; vivo X9 Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704.81 Mobile Safari/537.36; unicom{version:android@5.31,desmobile:15517273037}";
    UserAgentInfo info = parser.getUserAgentInfo(ua);
    assertNotNull(info);
    assertThat(info.getBrowserName().toString(), is("Unicom"));
  }

  @Test
  public void testWeibo() {
    String ua =
        "Mozilla/5.0 (Linux; Android 4.4.4; vivo Y13iL Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36 Weibo (vivo-vivo Y13iL__weibo__6.6.0__android__android4.4.4)";
    UserAgentInfo info = parser.getUserAgentInfo(ua);
    assertNotNull(info);
    assertThat(info.getBrowserName().toString(), is("Weibo"));
  }

  @Test
  public void testAutohome() {
    String ua =
        "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_3 like Mac OS X) AppleWebKit/603.3.8 (KHTML, like Gecko) Mobile/14G60 auto_iphone/8.3.1 nettype/wifi autohomeapp/1.0 (auto_iphone;8.3.1;tFE3RQtL8w5EuK70bs_KIMRI0sBGRKL-814yPJJI5PAwxsATibJ_SsOlR-6Zwg5qCzsa_51Qz82vIQj8xhS9s4O5XlqezRvQmGueStpiYR6f4cSH7oZmnA;10.3.3;iPhone)";
    UserAgentInfo info = parser.getUserAgentInfo(ua);
    assertNotNull(info);
    assertThat(info.getBrowserName().toString(), is("Autohome"));
  }

  @Test
  public void testNoasin() {
    String uaExpr =
        "Mozilla/5.0 (Linux; Android 5.1; NOAIN X9V Build/LMY47D) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Mobile Safari/537.36 wkbrowser 4.1.82 3102";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);

    assertNotNull(info);
    assertThat(info.getDeviceBrand().toString(), is("NOASIN"));
  }

  @Test
  public void testHonor7() {
    String uaExpr =
        "Mozilla/5.0 (Linux; Android 5.1;PLK-CL00 Build/LMY47D) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Mobile Safari/537.36 SogouSearch Android1.0 version3.0 AppVersion/4961";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getDeviceBrand().toString(), is("Huawei"));
    assertThat(info.getDeviceName().toString(), is("荣耀 7"));
    uaExpr =
        "Mozilla/5.0 (Linux; U; Android 9; zh-cn; MHA-AL00 Build/HUAWEIMHA-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/71.0.3578.99 Mobile Safari/537.36 baiduboxapp/11.2.0.10 (Baidu; P1 8.1.0)";
    info = parser.getUserAgentInfo(uaExpr);

    uaExpr =
        "Mozilla/5.0 (Linux; Android 8.0.0; EVA-TL00 Build/HUAWEIEVA-TL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36";
    info = parser.getUserAgentInfo(uaExpr);
    System.out.println(info.getDeviceName());
  }

  @Test
  public void testDouban() {
    String uaExpr =
        "Mozilla/5.0 (Linux; Android 7.0; MHA-AL00 Build/HUAWEIMHA-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36 udid/8a7b587adcd34ee50a74dbd86dd3b548536cd7d2 com.douban.frodo/4.11.4(90) DoubanApp";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
    assertThat(info.getBrowserName().toString(), is("Douban"));
  }

  @Test
  public void testScreen() {
    String uaExpr =
        "\tMozilla/5.0 (Linux; Android 6.0; DIG-TL10 Build/HUAWEIDIG-TL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36 Fanli/6.5.0.57 (ID:2-56124686-63269809102938-12-3; WVC:WV; SCR:720*1208-2.0)";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertNotNull(info);
  }

  @Test
  public void testIdentityByUdid() {
    String uaExpr =
        "Mozilla/5.0 (Linux; Android 7.0; MHA-AL00 Build/HUAWEIMHA-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36 udid/8a7b587adcd34ee50a74dbd86dd3b548536cd7d2 com.douban.frodo/4.11.4(90) DoubanApp";
    Pattern identityPattern =
        Pattern.compile(
            "\\W(deviceid|deviceId|sdk_guid|UTDID|GUID|guid|Id|ID|id|udid|UDID|MZ)[\" /:=]+([\\w-]+)",
            Pattern.CASE_INSENSITIVE);
    Matcher matcher = identityPattern.matcher(uaExpr);
    assertTrue(matcher.find());
    {
      String key = matcher.group(1);
      String value = matcher.group(2);
      assertThat(key, is("udid"));
      assertThat(value, is("8a7b587adcd34ee50a74dbd86dd3b548536cd7d2"));
    }

    uaExpr =
        "Mozilla/5.0 (Linux; Android 6.0.1;MI NOTE LTE Build/HUAWEIRIO-UL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36source=appmall&ua=jsapp&platform=android&netmode=cmnet&ch=02&time=20170525091654&loginmobile=18761113037&version=6.2.0.1&poi=%E5%AE%89%E5%BE%BD%E7%9C%81%E6%BB%81%E5%B7%9E%E5%B8%82%E6%98%8E%E5%85%89%E5%B8%82&deviceid=76CEA3B5DCF655503FB8412D7E73A3A9&lat=32.7689&channel=sd&lng=118.0291&sign=D3276DA6BBDF03A4755E1AEFD5C59675";
    matcher = identityPattern.matcher(uaExpr);
    assertTrue(matcher.find());
    {
      String key = matcher.group(1);
      String value = matcher.group(2);
      assertThat(key, is("deviceid"));
      assertThat(value, is("76CEA3B5DCF655503FB8412D7E73A3A9"));
    }

    uaExpr =
        "Qing/0.9.54;Android 7.0;HONOR;KNT-AL20;deviceId:2624f5de-3e37-46d2-8348-64f53194b9da;deviceName:HONOR KNT-AL20;clientId:10201;;os:Android 7.0;brand:HONOR;model:KNT-AL20;Mozilla/5.0 (Linux; Android 7.0; KNT-AL20 Build/HUAWEIKNT-AL20; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.49 Mobile MQQBrowser/6.2 TBS/043508 Safari/537.36";
    matcher = identityPattern.matcher(uaExpr);
    assertTrue(matcher.find());
    {
      String key = matcher.group(1);
      String value = matcher.group(2);
      assertThat(key, is("deviceId"));
      assertThat(value, is("2624f5de-3e37-46d2-8348-64f53194b9da"));
    }

    uaExpr =
        "Mozilla/5.0 (Linux; Android 5.1.1; vivo X6S A Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Mobile Safari/537.36 MagicKitchen/3.7.4 NetType/WIFI DEVICE/6e8c5f4fd87dee088d7b95b1174f137f";
    matcher = identityPattern.matcher(uaExpr);
    assertFalse(matcher.find());
    if (matcher.find(0)) {
      String key = matcher.group(1);
      String value = matcher.group(2);
      assertThat(key, is("DEVICE"));
      assertThat(value, is("6e8c5f4fd87dee088d7b95b1174f137f"));
    }

    uaExpr =
        "Mozilla/5.0 (Linux; Android 4.4.4; SM-G5308W Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36 Youku/6.8.1 (Android 4.4.4; Bridge_SDK; GUID 2465b4825dd462925908351442a8586e; UTDID VQ05e8RsBOoDAIvT7gsd8l2";
    matcher = identityPattern.matcher(uaExpr);
    assertTrue(matcher.find());
    {
      String key = matcher.group(1);
      String value = matcher.group(2);
      assertThat(key, is("GUID"));
      assertThat(value, is("2465b4825dd462925908351442a8586e"));
    }
    uaExpr =
        "Mozilla/5.0 (Linux; Android 6.0.1;Redmi Note 2 Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/46.0.2490.76 Mobile Safari/537.36 fanwe_app_sdk sdk_type/android sdk_version_name/2.4.1 sdk_version/2017072102 sdk_guid/864337031189138 screen_width/1080 screen_height/1920";
    matcher = identityPattern.matcher(uaExpr);
    assertTrue(matcher.find());
    {
      String key = matcher.group(1);
      String value = matcher.group(2);
      assertThat(key, is("sdk_guid"));
      assertThat(value, is("864337031189138"));
    }
    uaExpr =
        "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0) (iTools{\"a\":\"x86\",\"c\":1,\"guid\":\"C55CF0B846C066D57F3AD528810E53BD\",\"i\":\"C55CF0B846C066D57F3AD528810E53BD\",\"l\":1,\"p\":21,\"v\":3410}slooTi)";
    matcher = identityPattern.matcher(uaExpr);
    assertTrue(matcher.find());
    {
      String key = matcher.group(1);
      String value = matcher.group(2);
      assertThat(key, is("guid"));
      assertThat(value, is("C55CF0B846C066D57F3AD528810E53BD"));
    }

    uaExpr =
        "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_2 like Mac OS X) AppleWebKit/603.2.4 (KHTML, like Gecko) Mobile/14F89 QQ/6.5.5.0 TIM/2.0.5.404 V1_IPH_SQ_6.5.5_1_TIM_D Pixel/1080 Core/UIWebView Device/Apple(iPhone 6sPlus) NetType/WIFI QBWebViewType/1";
    matcher = identityPattern.matcher(uaExpr);
    assertFalse(matcher.find());

    uaExpr =
        "Mozilla/5.0 (Linux; Android 7.0; HUAWEI CAZ-AL10 Build/HUAWEICAZ-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/55.0.2883.91 Mobile Safari/537.36 X-Tingyun-Id/null;c=2;r=1468719239;";
    matcher = identityPattern.matcher(uaExpr);
    assertTrue(matcher.find());
    if (matcher.find(0)) {
      String key = matcher.group(1);
      String value = matcher.group(2);
      assertThat(key.toLowerCase(), is("id"));
      assertThat(value.toLowerCase(), is("null"));
    }

    uaExpr =
        "Mozilla/5.0 (Linux; Android 5.1.1; vivo Y31A Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Mobile Safari/537.36 Xiaodupi/4.10 nettype/wifi device/vivo Y31A channel/yingyongbao deviceId/15df255a4c9df724 uid/1137883930";
    matcher = identityPattern.matcher(uaExpr);
    assertTrue(matcher.find());
    {
      String key = matcher.group(1);
      String value = matcher.group(2);
      assertThat(key.toLowerCase(), is("deviceid"));
      assertThat(value.toLowerCase(), is("15df255a4c9df724"));
    }

    uaExpr =
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) Fengxing/3.0.6.30 MZ/4F0575225EDC39879698EDAFDDD4995A HTTP/1.1";
    matcher = identityPattern.matcher(uaExpr);
    assertTrue(matcher.find());
    {
      String key = matcher.group(1);
      String value = matcher.group(2);
      assertThat(key.toUpperCase(), is("MZ"));
      assertThat(value.toUpperCase(), is("4F0575225EDC39879698EDAFDDD4995A"));
    }
  }

  @Test
  public void testOppoBrowser() {
    String uaExpr =
        "Mozilla/5.0 (Linux; U; Android 5.1.1; zh-cn; A51 Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OppoBrowser/3.6.0 Mobile Safari/537.36";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertThat(info.getBrowserName().toString(), is("OppoBrowser"));
    uaExpr =
        "Mozilla/5.0 (Linux; Android 8.1.0; PBFM00 Build/OPM1.171019.026; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36";
    info = parser.getUserAgentInfo(uaExpr);
    assertThat(info.getDeviceBrand().toString(), is("OPPO"));
  }

  @Test
  public void testAppWithCFNetwork() {
    String uaExpr = "PopStar!/5.1.18 CFNetwork/889.9 Darwin/17.2.0";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertThat(info.getBrowserName().toString(), is("popstar!"));

    uaExpr = "图片搜索/1.2.201704281739 CFNetwork/894 Darwin/17.4.0";
    info = parser.getUserAgentInfo(uaExpr);
    assertThat(info.getBrowserName().toString(), is("图片搜索"));

    uaExpr = "com.bozhong.Crazy/2018040403 CFNetwork/897.15 Darwin/17.5.0";
    info = parser.getUserAgentInfo(uaExpr);
    assertThat(info.getBrowserName().toString(), is("crazy"));

    uaExpr = "s545_lajitong-mobile/1.69 CFNetwork/897.15 Darwin/17.5.0";
    info = parser.getUserAgentInfo(uaExpr);
    assertThat(info.getBrowserName().toString(), is("s545_lajitong-mobile"));
  }

  @Test
  public void testHuaweiVideo() {
    String uaExpr = "HwVPlayer;2.2.0.306;Android;6.0;PLK-TL00";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    assertThat(info.getBrowserName().toString(), is("华为视频"));

    uaExpr =
        "Mozilla/5.0 (Linux; Android 4.2.2; ATH-AL00 Build/ATH-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/47.0.2526.100 Mobile Safari/537.36";
    info = parser.getUserAgentInfo(uaExpr);
    assertThat(info.getDeviceBrand().toString(), is("Huawei"));
    assertThat(info.getDeviceName().toString(), is("荣耀 7i"));
  }

  @Test
  public void testSmartisan() {
    String uaExpr =
        "Mozilla/5.0 (Linux; U; Android 7.1.1; zh-CN; OS103 Build/NGI77B) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 Quark/2.4.3.987 Mobile Safari/537.36";
    UserAgentInfo info = parser.getUserAgentInfo(uaExpr);
    System.out.println(info.getDeviceBrand());
    uaExpr =
        "Mozilla/5.0 (Linux; Android 5.1.1; eva-tl10 Build/LMY47I) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Safari/537.36";
    info = parser.getUserAgentInfo(uaExpr);
    System.out.println(info.getDeviceBrand());
  }

  @Test
  public void testRegex() {
    Pattern netTypePattern = Pattern.compile("\\W(WIFI|5G|4G|3G|2G)\\W*", Pattern.CASE_INSENSITIVE);
    String expr =
        "Mozilla/5.0 (Linux; Android 5.1; M651CY Build/LMY47D) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Mobile Safari/537.36 X-Tingyun-Id/p35OnrDoP8k;c=2;r=1392755971; hebao/7.0.109 NetType/wifi";
    Matcher matcher = netTypePattern.matcher(expr);
    if (matcher.find()) {
      String value = matcher.group(1);
      System.out.println(value);
    }
  }
}
