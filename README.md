# useragent-parser

the libary of useragent parser, using to parse infomation of browser,os,device and .net version and so on. this version of parser contains much of special useragent of chinese feature.

### example
``` java
try{
  String uaString ="Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.55 Safari/534.3"
  Parser parser = UserAgentParser.getInstance();
  UserAgentInfo info = parser.getUserAgentInfo(uaString);
  
}catch(IOException e){
  e.printStackTrace();
}

```
the description of  UserAgentInfo is `/main/resource/UserAgentInfo.avsc`

now, this libary has be using, if having any question in using, please let me know. and i will optimize it using free time. 

### support
* 常见浏览器
* 常见移动设备
* 网络类型
* 设备类型，PC/Phone/Pad/TV
* 设备唯一标识
* ~~.net版本信息~~
* ios 上对于CFNetwork，rv:格式的app解析


### 增加的设备
* Huawei P10
* Huawei P10 Plus
* QingtingFM 

