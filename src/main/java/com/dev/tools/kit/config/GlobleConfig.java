package com.dev.tools.kit.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:用于项目中的全局配置
 * @Author: zhangjianfeng
 * @Date: 2019-05-15
 */
public class GlobleConfig {

    private static Map<String, String> configs = new HashMap<>();

    public static void config(String key, String val) {
        configs.put(key, val);
    }

    public static String get(String key) {
        return configs.get(key);
    }
}
