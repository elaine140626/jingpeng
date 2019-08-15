package com.truckscale.weighingManagement.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController{
	/**
	 * @since 文件上传
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/fileUpload.action")
	@ResponseBody
	public Map<String, String> test(@RequestParam(value = "upload", required = true)MultipartFile file, HttpServletRequest request) throws IOException{		
		Map<String, String> map = new HashMap<String, String>();
		// 获取指定时间格式的字符串
		String uploadTime = this.getCurrDate("yyyyMMddHHmmssmmmm");
		// 获取文件名
		String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
		String name2 = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
		String fileName = new StringBuilder(name).append("_").append(uploadTime).append(name2).toString();		
		// 文件存储路径
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		File dir = new File(path, fileName);	
		System.out.println("文件已上传到：" + path);
		// 判断目录是否存在，若不存在则新建
		if(!dir.exists()){			
			dir.mkdirs();		
		}		
		//MultipartFile自带的解析方法		
		file.transferTo(dir);		
		map.put("fileName", fileName);
		map.put("path", path+"/"+fileName);
		return map;	
	}
	
	/**
	 * @since 文件下载
	 * @param filename
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/fileDownload.action")
	public void download(@RequestParam(value="filename")String filename, HttpServletRequest request, 
            HttpServletResponse response) throws IOException {
        //模拟文件，myfile.txt为需要下载的文件  
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"/"+filename;  
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
	public void delete(@RequestParam(value="filename")String filename, HttpServletRequest request) throws IOException {
//		filename = URLEncoder.encode(filename,"UTF-8"); 
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"/"+filename;  
		FileUtils.deleteQuietly(new File(path));
	}
	
	@RequestMapping("/preview.action")
	@ResponseBody
	public void showImage(@RequestParam(value="fileName")String fileName,HttpServletRequest request, HttpServletResponse response) throws Exception{
		    response.setContentType("text/html; charset=UTF-8");
		    response.setContentType("image/jpeg");
		    String fname =request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"/"+fileName;
//		    String newpath = new String(fname.getBytes("ISO-8859-1"), "UTF-8");
		    FileInputStream fis = new FileInputStream(fname);
		    OutputStream os = response.getOutputStream();
		    try
		    {
		    	int count = 0;
		    	byte[] buffer = new byte[1024 * 1024];
		    	while ((count = fis.read(buffer)) != -1)
		    	os.write(buffer, 0, count);
		    	os.flush();
		    }
		    	catch (IOException e)
		    {
		    	e.printStackTrace();
		    }
		    	finally
		    {
		    	if (os != null)
		    		os.close();
		    	if (fis != null)
		    		fis.close();
		    }
	}
	
	/**
	 * @since 文件下载前校验文件是否存在
	 * @param filename
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/checkExist.action")
	@ResponseBody
	public Boolean checkExist(@RequestParam(value="filename")String filename, HttpServletRequest request) throws IOException {
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"/"+filename;  
		File file = new File(path);
		// 如果存在，则可以下载
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取当前时间的指定格式
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

