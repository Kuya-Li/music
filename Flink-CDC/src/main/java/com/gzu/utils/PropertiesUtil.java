package com.gzu.utils;

import java.util.ResourceBundle;

/**
 * Description:读取配置文件的工具类
 * Author: SuWenJay
 * Date: 2023-08-16
 */
public class PropertiesUtil {
    private static ResourceBundle resourceBundle;

    /**
     * 加载resources下的properties文件
     * @param properties 配置文件名
     * @return resourceBundle
     */
    public static ResourceBundle getResourceBundle(String properties){
        // 使用 Resource Bundle 加载配置文件
        resourceBundle = ResourceBundle.getBundle(properties);
        return resourceBundle;
    }

    /**
     * 通过key获取值
     * @param key 键
     * @return 值
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
