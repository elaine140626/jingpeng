package com.curing.projectSchedule.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.curing.common.model.DataTablesResponseInfo;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;
import com.curing.common.util.Uploadfiles;
import com.curing.projectInfo.model.ProjectInfoSummaryEntity;
import com.curing.projectSchedule.model.SupervisionApplicationEntity;
import com.curing.projectSchedule.service.SupervisionApplicationService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/SupervisionApplication")
public class SupervisionApplicationController {
	@Autowired
	private SupervisionApplicationService supervisionApplicationService;

	// 工程进度（监督申请）List取得
	@RequestMapping("/getSupervisionApplicationList.action")
	@ResponseBody
	public DataTablesResponseInfo getSupervisionApplicationList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<SupervisionApplicationEntity> list = supervisionApplicationService.getSupervisionApplicationList(map,0);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（监督申请）单条获取
	@RequestMapping("/getSupervisionApplicationById.action")
	@ResponseBody
	public List<SupervisionApplicationEntity> getSupervisionApplicationById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<SupervisionApplicationEntity> list = supervisionApplicationService.getSupervisionApplicationList(map,0);
		return list;
	}
	
	// 新增工程进度（监督申请）
	@RequestMapping("/insertSupervisionApplication.action")
	@ResponseBody
	public ResponseInfo insertSupervisionApplication(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ResponseInfo info = new ResponseInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath ="D:\\curing\\image\\"+date; 
        JSONObject jsonObject=JSONObject.fromObject(request.getParameter("params"));
        // 上传文件
        info = Uploadfiles.uploadfiles(myfiles,realPath);
        
        if (info.getCode() != "error") {
        	SupervisionApplicationEntity supervisionApplicationEntity=(SupervisionApplicationEntity)JSONObject.toBean(jsonObject, SupervisionApplicationEntity.class);
        	supervisionApplicationEntity.setUploadPicture(date);
    		int res = supervisionApplicationService.insertSupervisionApplication(supervisionApplicationEntity);
    		if (res>0) {
    			// 操作成功
    			info.setCode(MessageUtil.success);
    			info.setMessage(MessageUtil.successInfo);
    		} else {
    			// 操作失败
    			info.setCode(MessageUtil.error);
    			info.setMessage(MessageUtil.errorInfo);
    		}
        }    
		return info;		
	}
	
	// 更新工程进度（监督申请）
	@RequestMapping("/updateSupervisionApplication.action")
	@ResponseBody
	public ResponseInfo updateSupervisionApplication(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ResponseInfo info = new ResponseInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        JSONObject jsonObject=JSONObject.fromObject(request.getParameter("params"));
        SupervisionApplicationEntity supervisionApplicationEntity=(SupervisionApplicationEntity)JSONObject.toBean(jsonObject, SupervisionApplicationEntity.class);
        Map<String,Object> map = new HashMap<>();
        map.put("id", supervisionApplicationEntity.getId());
        List<SupervisionApplicationEntity> supervisionApplicationEntityList = supervisionApplicationService.getSupervisionApplicationList(map, 1);
        String realPath = "";
        if(!supervisionApplicationEntityList.get(0).getUploadPicture().equals("") && supervisionApplicationEntityList.get(0).getUploadPicture() != null) {
        	realPath ="D:\\curing\\image\\"+supervisionApplicationEntityList.get(0).getUploadPicture();
        	supervisionApplicationEntity.setUploadPicture(supervisionApplicationEntityList.get(0).getUploadPicture());
        }else {
        	realPath = "D:\\curing\\image\\"+date;
        	supervisionApplicationEntity.setUploadPicture(date);
        }
        // 上传文件
        info = Uploadfiles.uploadfiles(myfiles,realPath);
        if (info.getCode() != "error") {
        	int res = supervisionApplicationService.updateSupervisionApplication(supervisionApplicationEntity);
			if (res>0) {
				// 操作成功
				info.setCode(MessageUtil.success);
				info.setMessage(MessageUtil.successInfo);
			} else {
				// 操作失败
				info.setCode(MessageUtil.error);
				info.setMessage(MessageUtil.errorInfo);
			}
        }
        return info;
	}
	
	// 删除工程进度（监督申请）
	@RequestMapping("/deleteSupervisionApplication.action")
	@ResponseBody
	public ResponseInfo deleteSupervisionApplication(@RequestBody SupervisionApplicationEntity supervisionApplicationEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = supervisionApplicationService.deleteSupervisionApplication(supervisionApplicationEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.success);
			info.setMessage(MessageUtil.successInfo);
		} else {
			// 操作失败
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.errorInfo);
		}
		return info;		
	}
	
	// 监理计划上传（监督申请）
	@RequestMapping("/uploadSupervisorPlan.action")
	@ResponseBody
	public ResponseInfo uploadSupervisorPlan(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ResponseInfo info = new ResponseInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
		String id = request.getParameter("id");
		String realPath ="";
		String pix = "";
		if(!id.equals("") && id != null) {
			Map<String,Object> map = new HashMap<>();
			map.put("id", id);
			List<SupervisionApplicationEntity> list = supervisionApplicationService.getSupervisionApplicationList(map,1);
			realPath ="D:\\curing\\engineeringFile\\"+list.get(0).getSupervisorPlan();
			pix = list.get(0).getSupervisorPlan();
		}else {
			realPath ="D:\\curing\\engineeringFile\\"+date;
			pix = date;
		}
        info = Uploadfiles.uploadfiles(myfiles,realPath);
        info.setCode(pix);
		return info;
	}
	
	// 监理细则上传（监督申请）
	@RequestMapping("/uploadSupervisorDetailed.action")
	@ResponseBody
	public ResponseInfo uploadSupervisorDetailed(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ResponseInfo info = new ResponseInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
		String id = request.getParameter("id");
		String realPath ="";
		String pix = "";
		if(!id.equals("") && id != null) {
			Map<String,Object> map = new HashMap<>();
			map.put("id", id);
			List<SupervisionApplicationEntity> list = supervisionApplicationService.getSupervisionApplicationList(map,1);
			realPath ="D:\\curing\\engineeringFile\\"+list.get(0).getSupervisorDetailed();
			pix = list.get(0).getSupervisorDetailed();
		}else {
			realPath ="D:\\curing\\engineeringFile\\"+date+"1";
			pix = date+"1";
		}
        info = Uploadfiles.uploadfiles(myfiles,realPath);
        info.setCode(pix);
		return info;
	}
	
	// 监理计划下载（监督申请）
	@RequestMapping("/downSupervisorPlan.action")
	@ResponseBody
	public ModelAndView downSupervisorPlan(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //模拟文件，myfile.txt为需要下载的文件  
		 ModelAndView mv = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		int id =Integer.valueOf(request.getParameter("id").toString());
		map.put("id", id);
		List<SupervisionApplicationEntity> list = supervisionApplicationService.getSupervisionApplicationList(map,1);
		String pix = list.get(0).getSupervisorPlan();
        String path = "D:\\curing\\engineeringFile\\"+pix;
        File supervisorPlanFile = new File(path);//File类型可以是文件也可以是文件夹
		File[] supervisorPlanFileList = supervisorPlanFile.listFiles();
		InputStream bis = null;
		BufferedOutputStream out = null;
		if(supervisorPlanFileList != null) {
			try {
				String filename = supervisorPlanFileList[0].getName();
				path = "D:\\curing\\engineeringFile\\"+pix+"\\"+filename;
				//获取输入流  
				bis = new BufferedInputStream(new FileInputStream(new File(path)));
				//转码，免得文件名中文乱码  
				filename = URLEncoder.encode(filename,"UTF-8");  
				//设置文件下载头  
				response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
				//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
				response.setContentType("multipart/form-data");   
				out = new BufferedOutputStream(response.getOutputStream());  
				int len = 0;  
				while((len = bis.read()) != -1){  
					out.write(len);  
					out.flush();  
				}  
			} catch (Exception e) {
				mv.setViewName("imageError");
				return mv;
			}finally {
				if(bis != null) {
					bis.close();
				}
				if(out != null) {
					out.close();
				}
			}
		}
		return null;
	}
	
	
	// 监理计划下载（监督申请）
	@RequestMapping("/downSupervisorDetailed.action")
	@ResponseBody
	public ModelAndView  downSupervisorDetailed(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //模拟文件，myfile.txt为需要下载的文件  
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		int id =Integer.valueOf(request.getParameter("id").toString());
		map.put("id", id);
		List<SupervisionApplicationEntity> list = supervisionApplicationService.getSupervisionApplicationList(map,1);
		String pix = list.get(0).getSupervisorDetailed();
        String path = "D:\\curing\\engineeringFile\\"+pix;
        File supervisorDetailedFile = new File(path);//File类型可以是文件也可以是文件夹
		File[] supervisorDetailedFileList = supervisorDetailedFile.listFiles();
		InputStream bis = null;
		BufferedOutputStream out = null;
		if(supervisorDetailedFileList != null) {
			try {
				
				String filename = supervisorDetailedFileList[0].getName();
				path = "D:\\curing\\engineeringFile\\"+pix+"\\"+filename;
				//获取输入流  
				bis = new BufferedInputStream(new FileInputStream(new File(path)));
				//转码，免得文件名中文乱码  
				filename = URLEncoder.encode(filename,"UTF-8");  
				//设置文件下载头  
				response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
				//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
				response.setContentType("multipart/form-data");   
				out = new BufferedOutputStream(response.getOutputStream());  
				int len = 0;  
				while((len = bis.read()) != -1){  
					out.write(len);  
					out.flush();  
				}  
				mv.setViewName("");
			} catch (Exception e) {
				// TODO: handle exception
				mv.setViewName("imageError");
				return mv;
			}finally {
				if(bis != null) {
					bis.close();
				}
				if(out != null) {
					out.close();
				}
			}
		}
		return null;
	}
	
	// 打开文件
	@RequestMapping("/selectFileList.action")
	@ResponseBody
	public DataTablesResponseInfo selectFileList(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws IOException{
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<SupervisionApplicationEntity> list = supervisionApplicationService.getSupervisionApplicationList(map, 1);
		List<Map<String,Object>> fileNameList = new ArrayList<Map<String, Object>>();
		try {
			// 获得指定文件对象  
			File file = new File("D:\\curing\\image\\" + list.get(0).getUploadPicture());
			// 获得该文件夹内的所有文件   
			File[] array = file.listFiles(); 
			 for (int i = 0; i < array.length; i++) {
				 File fs = array[i];
				 Map<String, Object> fileNamemap = new HashMap<String, Object>();
				 fileNamemap.put("name", fs.getName());
				 fileNamemap.put("prefix", list.get(0).getUploadPicture());
				 fileNameList.add(fileNamemap);
			 }
			 dInfo.setData(fileNameList);

		} catch (Exception e) {
			// TODO: handle exception
			dInfo.setData(MessageUtil.error);
		}
		return dInfo;
	}
	
	
	@RequestMapping("/delUploadfile.action")
	@ResponseBody
	public ResponseInfo delUploadfile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return supervisionApplicationService.delUploadfile(map);
	}
	
	@RequestMapping("/downUploadfile.action")
	@ResponseBody
	public void  downUploadfile(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="imageNamePrams")String imageNamePrams,@RequestParam(value="imagePrefix")String imagePrefix) throws IOException  {
			imageNamePrams = imageNamePrams.substring(0,imageNamePrams.length() - 1);
			supervisionApplicationService.downUploadfile(imageNamePrams,imagePrefix,response);
	}
	
	@RequestMapping("/deleteSupervisorPlanFile.action")
	@ResponseBody
	public ResponseInfo deleteSupervisorPlanFile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return supervisionApplicationService.deleteSupervisorPlanFile(map);
	}
	
	@RequestMapping("/deleteSupervisorDetailedFile.action")
	@ResponseBody
	public ResponseInfo deleteSupervisorDetailedFile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return supervisionApplicationService.deleteSupervisorDetailedFile(map);
	}

}
