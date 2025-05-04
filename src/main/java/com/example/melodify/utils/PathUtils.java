package com.example.melodify.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class PathUtils {
    public static String getClassLoadRootPath() {
        String path = "";

        URL resourceUrl = PathUtils.class.getClassLoader().getResource("");
        if (resourceUrl == null) {
            throw new IllegalStateException("无法获取类加载路径");
        }

        String prePath = URLDecoder.decode(resourceUrl.getPath(), StandardCharsets.UTF_8)
                .replace("/target/classes", "");
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().startsWith("mac")) {
            // 苹果
            path = prePath.substring(0, prePath.length() - 1);
        } else if (osName.toLowerCase().startsWith("windows")) {
            // windows
            path = prePath.substring(1, prePath.length() - 1);
        } else if(osName.toLowerCase().startsWith("linux") || osName.toLowerCase().startsWith("unix")) {
            // unix or linux
            path = prePath.substring(0, prePath.length() - 1);
        } else {
            path = prePath.substring(1, prePath.length() - 1);
        }
        return path;
    }
}
