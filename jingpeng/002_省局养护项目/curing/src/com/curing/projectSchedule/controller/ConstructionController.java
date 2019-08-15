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
import com.curing.projectSchedule.model.ConstructionEntity;
import com.curing.projectSchedule.model.SupervisionApplicationEntity;
import com.curing.projectSchedule.service.ConstructionService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/Construction")
public class ConstructionController {
	@Autowired
	private ConstructionService constructionService;

	// 工程进度（施工组织设计）List取得
	@RequestMapping("/getConstructionList.action")
	@ResponseBody
	public DataTablesResponseInfo getConstructionList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ConstructionEntity> list = constructionService.getConstructionList(map,0);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程进度（施工组织设计）单条获取
	@RequestMapping("/getConstructionById.action")
	@ResponseBody
	public List<ConstructionEntity> getConstructionById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<ConstructionEntity> list = constructionService.getConstructionList(map,0);
		return list;
	}
	
	// 新增工程进度（施工组织设计）
	@RequestMapping("/insertConstruction.action")
	@ResponseBody
	public ResponseInfo insertConstruction(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ResponseInfo info = new ResponseInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath ="D:\\curing\\image\\"+date; 
        JSONObject jsonObject=JSONObject.fromObject(request.getParameter("params"));
        // 上传文件
        info = Uploadfiles.uploadfiles(myfiles,realPath);
        if (info.getCode() != "error") {
        	ConstructionEntity constructionEntity=(ConstructionEntity)JSONObject.toBean(jsonObject, ConstructionEntity.class);
        	constructionEntity.setUploadPicture(date);
    		int res = constructionService.insertConstruction(constructionEntity);
    		if (res>0) {
    			// 操作成功
    			info.setCode(MessageUtil.success);
    			info.setMessage(MessageUtil.successInfo);
    		} else {
    			// 操作失败
    			info.setCode(MessageUtil.error);
    			info.setMessage(MessageUtil.errorInfo);
    		}
        }else {
        	// 操作失败
    		info.setCode(MessageUtil.error);
    		info.setMessage(MessageUtil.errorInfo);
        }  
		return info;		
	}
	
	// 打开文件
	@RequestMapping("/selectFileList.action")
	@ResponseBody
	public DataTablesResponseInfo selectFileList(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws IOException{
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ConstructionEntity> list =  constructionService.getConstructionList(map, 1);
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
		return constructionService.delUploadfile(map);
	}
	
	@RequestMapping("/downUploadfile.action")
	@ResponseBody
	public void  downUploadfile(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="imageNamePrams")String imageNamePrams,@RequestParam(value="imagePrefix")String imagePrefix) throws IOException  {
			imageNamePrams = imageNamePrams.substring(0,imageNamePrams.length() - 1);
			constructionService.downUploadfile(imageNamePrams,imagePrefix,response);
	}
	
	// 更新工程进度（施工组织设计）
	@RequestMapping("/updateConstruction.action")
	@ResponseBody
	public ResponseInfo updateConstruction(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ResponseInfo info = new ResponseInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath =""; 
        JSONObject jsonObject=JSONObject.fromObject(request.getParameter("params"));
        ConstructionEntity constructionEntity=(ConstructionEntity)JSONObject.toBean(jsonObject, ConstructionEntity.class);
        Map<String,Object> map = new HashMap<>();
        map.put("id", constructionEntity.getId());
        List<ConstructionEntity> constructionEntityList = constructionService.getConstructionList(map, 1);
        if(constructionEntityList.get(0).getUploadPicture() != null && !constructionEntityList.get(0).getUploadPicture().equals("")) {
        	realPath ="D:\\curing\\image\\"+constructionEntityList.get(0).getUploadPicture();
        	constructionEntity.setUploadPicture(constructionEntityList.get(0).getUploadPicture());
        }else {
        	realPath ="D:\\curing\\image\\"+date;
        	constructionEntity.setUploadPicture(date);
        }
        info = Uploadfiles.uploadfiles(myfiles,realPath);
        if (info.getCode() != "error") {
        	int res = constructionService.updateConstruction(constructionEntity);
        	if (res>0) {
        		// 操作成功
        		info.setCode(MessageUtil.success);
        		info.setMessage(MessageUtil.successInfo);
        	} else {
        		// 操作失败
        		info.setCode(MessageUtil.error);
        		info.setMessage(MessageUtil.errorInfo);
        	}
        }else {
        	// 操作失败
    		info.setCode(MessageUtil.error);
    		info.setMessage(MessageUtil.errorInfo);
        }
		return info;		
	}
	
	// 删除工程进度（施工组织设计）
	@RequestMapping("/deleteConstruction.action")
	@ResponseBody
	public ResponseInfo deleteConstruction(@RequestBody ConstructionEntity constructionEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = constructionService.deleteConstruction(constructionEntity);
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
	
	// 组织设计上传（监督申请）
	@RequestMapping("/uploadBuildDesign.action")
	@ResponseBody
	public ResponseInfo uploadBuildDesign(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
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
			List<ConstructionEntity> list = constructionService.getConstructionList(map,1);
			realPath ="D:\\curing\\engineeringFile\\"+list.get(0).getBuildDesign();
			pix = list.get(0).getBuildDesign();
		}else {
			realPath ="D:\\curing\\engineeringFile\\"+date;
			pix = date;
		}
        info = Uploadfiles.uploadfiles(myfiles,realPath);
        info.setCode(pix);
		return info;
	}
	
	
	// 组织设计下载（监督申请）
	@RequestMapping("/downBuildDesign.action")
	@ResponseBody
	public ModelAndView downBuildDesign(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //模拟文件，myfile.txt为需要下载的文件  
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = new HashMap<>();
		int id =Integer.valueOf(request.getParameter("id").toString());
		map.put("id", id);
		List<ConstructionEntity> list = constructionService.getConstructionList(map, 1);
		String pix = list.get(0).getBuildDesign();
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

	@RequestMapping("/deleteBuildDesignFile.action")
	@ResponseBody
	public ResponseInfo deleteBuildDesignFile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return constructionService.deleteBuildDesignFile(map);
	}
	
}
