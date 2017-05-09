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

now, only finish coding to parse useragent, but no much time to tesing,so it maybe have some questions. but i will optimize it using free time. if have any question of using this libary, please let me know, thank you!
