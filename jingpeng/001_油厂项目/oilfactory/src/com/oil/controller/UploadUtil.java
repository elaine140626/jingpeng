package com.oil.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileUp")
public class UploadUtil {
	
	@RequestMapping("/upload.action")
	@ResponseBody
    public Map<String, String> addUser(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{  
		Map<String, String> map = new HashMap<String, String>();
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath = request.getSession().getServletContext().getRealPath("/upload");  
        String serialID = request.getParameter("serialID");
        System.out.println(serialID);
        //上传文件的原名(即上传前的文件名字)  
        String originalFilename = null;  
        //如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解  
        //如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解  
        //上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件  
        for(MultipartFile myfile : myfiles){  
            if(myfile.isEmpty()){
            	map.put("fileName", "1");
                return map;  
            }else{  
        		// 获取指定时间格式的字符串
        		String uploadTime = UUID.randomUUID().toString().replaceAll("-","");
        		// 获取文件名
        		String name = myfile.getOriginalFilename().substring(0, myfile.getOriginalFilename().indexOf("."));
        		String name2 = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().indexOf("."), myfile.getOriginalFilename().length());
        		originalFilename = new StringBuilder(name).append("_").append(uploadTime).append(name2).toString();
            	
            	
                //originalFilename = myfile.getOriginalFilename();  
                System.out.println("文件原名: " + originalFilename);  
                System.out.println("文件名称: " + myfile.getName());  
                System.out.println("文件长度: " + myfile.getSize());  
                System.out.println("文件类型: " + myfile.getContentType());  
                System.out.println("========================================");  
                try {  
                    //这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉  
                    //此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传  
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));  
                } catch (IOException e) {  
                    System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");  
                    e.printStackTrace();
                    map.put("fileName", "1");
                    return map;  
                }  
            }  
        }  
        //此时在Windows下输出的是[/AjaxFileUpload/upload/愤怒的小鸟.jpg]  
        System.out.println(request.getContextPath() + "/upload/" + originalFilename);
        String uploadAddress = request.getContextPath() + "/upload/" + originalFilename;
        map.put("uploadAddress", uploadAddress);
        map.put("fileName", originalFilename);
        map.put("serialID", serialID);
        return map; 
    } 
	
	@RequestMapping("/fileDownload.action")
	public void download(@RequestParam(value="filename")String filename,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //模拟文件，myfile.txt为需要下载的文件  
        String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+filename;  
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
    }
	
	
	/**
	 * @since 文件删除
	 * @param filename
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/fileDelete.action")
	@ResponseBody
	public Boolean delete(@RequestParam(value="filename")String filename, HttpServletRequest request) throws IOException {
//		filename = URLEncoder.encode(filename,"UTF-8"); 
		String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+filename;  
		Boolean isSuccess =  FileUtils.deleteQuietly(new File(path));
		return isSuccess;
	}
	
	/**
	 * @since 文件删除
	 * @param filename
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/selectFile.action")
	@ResponseBody
	public Boolean selectFile(@RequestParam(value="filename")String filename, HttpServletRequest request) throws IOException {
//		filename = URLEncoder.encode(filename,"UTF-8"); 
		String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+filename;
		 File file=new File(path);
		 if  (!file .exists()  && !file .isDirectory())     
		 {      
		     System.out.println("//不存在"); 
		     return false;
		 } else  
		 { 
		     System.out.println("//目录存在");
		     return true;
		 } 
	}
	
	/**
	 * 获取当前时间的指定格式
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrDate(String format) {
		return dateToString(new Date(), format);
	}
	
	/**
	 * 把日期转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(java.util.Date date, String format) {
		if(date==null)
			return "";
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			// logger.error ( e );
		}
		return result;
	}
	
}


