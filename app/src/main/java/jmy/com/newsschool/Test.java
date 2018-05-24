package jmy.com.newsschool;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private static Map<Integer,String>map = new HashMap<>();

    public static void get(int id){
        map.get(id);
    }

    public static String[][]data = new String[][]{
            {"操作终端","1","0"},
            {"操作终端机器如下图","2","1"},
            {"操作终端的台咪配置如下图","2","1"},
            {"软件安装","4","0"},
            {"登录","5","0"},
            {"调度台主界面","6","0"},
            {"值班员基础信息展示","7","1"},
            {"行车调度员基础信息展示","8","1"},
            {"调度台显示终端的在线离线状态","9","1"},
            {"调度台的注册状态","10","1"}
    };
}
