package com.tiger.useragent;

import com.google.common.base.Function;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/9
 */
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
    public void testUserAgentParser() throws IOException {
        final String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.55 Safari/534.3";
        UserAgentInfo info = parser.getUserAgentInfo(userAgent);
        assertThat(info.getBrowserDetail().toString(), is("chrome 6.0"));
    }

    @Test
    public void testSample(){

        String[] uas = new String[]{
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
       Function<String,UserAgentParser> function = new Function<String,UserAgentParser>(){
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
        List<String> systems = Arrays.asList("Mac","Windows","iOS","Android");
        List<String> browsers = Arrays.asList("Safari","IE","firefox","Chrome","opera","Maxthon","QQ","360","android","Taobao","LieBao","Sogou");
        List<String> deviceTypes = Arrays.asList("PC","Phone","Pad");
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
    public void testNullException(){
        String userAgent ="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)";
        UserAgentInfo info = parser.getUserAgentInfo(userAgent);
        assertNotNull(info);
        assertThat(info.getOsDetail().toString(), is("Windows 7"));
        assertThat(info.getBrowserDetail().toString(),is("IE 8"));
    }
}
