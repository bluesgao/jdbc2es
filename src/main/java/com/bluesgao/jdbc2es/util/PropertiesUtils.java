package com.bluesgao.jdbc2es.util;

import java.io.*;
import java.util.Properties;


/**
 * ClassName: PropertiesUtils
 * Desc: 读取配置文件的工具类
 * Author: gaoxin11
 * Date: 2018/6/21 14:53
 **/
public class PropertiesUtils {
    private String properiesName = "";

    public PropertiesUtils() {

    }

    public PropertiesUtils(String fileName) {
        this.properiesName = fileName;
    }

    public String getProperty(String key) {
        String value = "";
        Properties properties = getProperties();
        if (properties != null) {
            value = properties.getProperty(key);
        }
        return value;
    }

    public Properties getProperties() {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = PropertiesUtils.class.getClassLoader().getResourceAsStream(properiesName);
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public void writeProperty(String key, String value) {
        InputStream is = null;
        OutputStream os = null;
        Properties p = new Properties();
        try {
            is = new FileInputStream(properiesName);
            p.load(is);
            os = new FileOutputStream(PropertiesUtils.class.getClassLoader().getResource(properiesName).getFile());

            p.setProperty(key, value);
            p.store(os, key);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
