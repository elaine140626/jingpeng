package com.blindSample.util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class ServerRoot {

	public void ServerRun() throws IOException {

		//创建批处理命令
		FileOutputStream out = null;
		PrintStream p = null;
        try {
            out=new FileOutputStream("C:/ServerRoot.bat");
            p=new PrintStream(out);
            p.println("@echo off");
            p.println("net use \\\\192.168.1.123\\新建文件夹 jp123 /user:\"administrator\"");
            p.println("exit");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
        	out.close();
        	p.close();
		}
        //执行批处理命令获取服务器权限
        File file = new File("C:/ServerRoot.bat");
        Desktop.getDesktop().open(file);
	}

}
