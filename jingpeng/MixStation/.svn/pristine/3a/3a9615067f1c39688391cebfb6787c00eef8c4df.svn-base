package com.jingpeng.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdt.base.support.springsupport.KDController;

@Controller
@RequestMapping("/log")
public class LogController extends KDController<Object> {

	@RequestMapping("/indexLog")
	public void writeLog (HttpServletRequest request, @RequestParam Map<String, String> map) {
		PrintWriter logPrint = null;
		String path = request.getRealPath("/");
		try {
			logPrint = new PrintWriter(new FileWriter("/file" + "/log.log", true), true);
		} catch (IOException e) {
			(new File("/file")).mkdir();
			try {
				logPrint = new PrintWriter(new FileWriter("/file" + "/log.log", true), true);
			} catch (IOException ex) {

			}
		}
		writerLogInfo(logPrint, map.get("param").toString());
	}

	private synchronized static void writerLogInfo(PrintWriter logPrint, String msg) {
		logPrint.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " " + msg);
	}
}
