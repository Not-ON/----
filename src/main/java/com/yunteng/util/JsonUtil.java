package com.yunteng.util;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JsonUtil {
    public static StringBuilder getJson(HttpServletRequest request) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
        StringBuilder str = new StringBuilder();
        int respInt = inputStreamReader.read();
        while (respInt!=-1){
            str.append((char) respInt);
            respInt = inputStreamReader.read();
        }
        return str;
    }
}
