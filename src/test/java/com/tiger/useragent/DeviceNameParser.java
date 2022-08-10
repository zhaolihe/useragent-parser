package com.tiger.useragent;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeviceNameParser {

  private final List<String> list =
      Lists.newArrayList(
          "行业", "7.", "10.", "8.", "9.", "8 英寸", "7 英寸", "10 倍", "(", "公开", "移动", "联通", "全网", "电信",
          "双", "至尊", "Wi-Fi", "3G", "4G", "5G", "LTE", "印度版", "北美版", "国际版", "欧洲版", "超级", "标准版",
          "梦镜版", "高配", "轻奢版", "增强版", "幻彩版", "青春", "优享版", "透明", "屏幕", "顶配版", "黑色", "全", "尊享版", "美图",
          "巴西版", "台湾", "/", "MTK", "高通", "保时捷", "真皮", "四核", "畅玩版", "真八核", "Prime", "Lite", "通用版",
          "太子妃版", "未知", "精英版", "YunOS", "标配版", "edge", "Star", "Alpha", "极速版", "经典版", "畅享版", "电竞",
          "文艺", "特别");

  public static void main(String[] args) {
    DeviceNameParser parser = new DeviceNameParser();

    Map<String, Pair<String, String>> map = Maps.newHashMap();
    map.put(
        DeviceNameParser.class.getResource("/huawei.txt").getFile(), Pair.of("Phone", "Huawei"));
    map.put(
        DeviceNameParser.class.getResource("/huawei_pad.txt").getFile(), Pair.of("Pad", "Huawei"));
    map.put(DeviceNameParser.class.getResource("/mi.txt").getFile(), Pair.of("Phone", "Xiaomi"));
    map.put(
        DeviceNameParser.class.getResource("/one_plus.txt").getFile(), Pair.of("Phone", "OnePlus"));
    map.put(DeviceNameParser.class.getResource("/oppo.txt").getFile(), Pair.of("Phone", "OPPO"));
    map.put(
        DeviceNameParser.class.getResource("/samsung.txt").getFile(), Pair.of("Phone", "Samsung"));
    map.put(DeviceNameParser.class.getResource("/vivo.txt").getFile(), Pair.of("Phone", "vivo"));
    map.put(
        DeviceNameParser.class.getResource("/lenovo.txt").getFile(), Pair.of("Phone", "Lenovo"));
    map.put(DeviceNameParser.class.getResource("/letv.txt").getFile(), Pair.of("Phone", "Letv"));
    map.put(DeviceNameParser.class.getResource("/meizu.txt").getFile(), Pair.of("Phone", "Meizu"));
    map.put(DeviceNameParser.class.getResource("/mi_tv.txt").getFile(), Pair.of("TV", "Xiaomi"));
    map.put(DeviceNameParser.class.getResource("/nobia.txt").getFile(), Pair.of("Phone", "Nubia"));
    map.put(
        DeviceNameParser.class.getResource("/smartisan.txt").getFile(),
        Pair.of("Phone", "Smartisan"));
    String output = "/Users/zhaolihe/Desktop/output.txt";
    //        parser.execute("/Users/zhaolihe/Desktop/pad.txt",
    // "/Users/zhaolihe/Desktop/output.txt");
    parser.execute(map, output);
  }

  public void execute(Map<String, Pair<String, String>> map, String output) {
    try {
      List<String> list = Lists.newArrayList();
      for (Map.Entry<String, Pair<String, String>> entry : map.entrySet()) {
        List<String> lines = getLines(entry.getKey(), entry.getValue());
        if (lines != null && lines.size() > 0) {
          list.addAll(lines);
        }
      }
      write2File(output, list);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private List<String> getLines(String fileName, Pair<String, String> pair) throws IOException {
    FileReader fileReader = new FileReader(fileName);
    BufferedReader reader = new BufferedReader(fileReader);
    String line;
    List<String> result = Lists.newArrayList();

    Function<String, String> func =
        new Function<String, String>() {
          @Override
          public String apply(String input) {
            int index = 100000000;
            for (String key : list) {
              if (!input.contains(key)) {
                continue;
              }

              int temp = input.indexOf(key);
              if (temp > -1 && temp < index) {
                index = temp;
              }
            }
            if (index == 100000000) {
              return input;
            }
            return input.substring(0, index).trim();
          }
        };

    while ((line = reader.readLine()) != null) {
      if (line.trim().endsWith(":")) {
        continue;
      }

      String[] arr = StringUtils.splitPreserveAllTokens(line, ":");
      if (arr.length < 2) {
        continue;
      }
      result.add(
          String.format(
              "%s,,%s,,%s,,%s,,5.5\n",
              arr[0].trim(), pair.getRight(), func.apply(arr[1]).trim(), pair.getLeft()));
    }

    return result;
  }

  private void write2File(String output, List<String> lines) throws IOException {
    FileWriter writer = new FileWriter(output);
    for (String line : lines) {
      writer.write(line);
    }
    writer.flush();
    writer.close();
  }
}
