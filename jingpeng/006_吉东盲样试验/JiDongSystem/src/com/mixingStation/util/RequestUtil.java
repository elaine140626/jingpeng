package com.mixingStation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static String getContent(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String str = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }

}
