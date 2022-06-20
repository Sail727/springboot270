package com.example.demo.util;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class JsonUtil {

    /**
     * 读取JSON文件转换为字符串
     * @param path
     * @return
     */
    public static String readJsonFile(String path) {
        String jsonStr = "";
        try {
            ClassPathResource resource = new ClassPathResource("json/menu.json");
            InputStream inputStream= resource.getInputStream();
            Reader reader = new InputStreamReader(inputStream);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            inputStream.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
