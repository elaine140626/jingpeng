package com.curing.projectInfo.controller;

import java.io.File;
import java.io.IOException;
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

import com.curing.common.model.DataTablesResponseInfo;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;
import com.curing.common.util.Uploadfiles;
import com.curing.projectInfo.model.ProjectInfoSummaryEntity;
import com.curing.projectInfo.service.ProjectInfoSummaryService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ProjectInfoSummary")
public class ProjectInfoSummaryController {
	@Autowired
	private ProjectInfoSummaryService projectInfoSummaryService;

	// 工程信息List取得
	@RequestMapping("/getProjectInfoList.action")
	@ResponseBody
	public DataTablesResponseInfo getProjectInfoList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ProjectInfoSummaryEntity> list = projectInfoSummaryService.getProjectInfoSummaryList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	// 工程信息 单条获取
	@RequestMapping("/getProjectInfoSummaryById.action")
	@ResponseBody
	public List<ProjectInfoSummaryEntity> getProjectInfoSummaryById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<ProjectInfoSummaryEntity> list = projectInfoSummaryService.getProjectInfoSummaryList(map);
		return list;
	}
	
/*	// 新增工程信息
	@RequestMapping("/insertProjectInfoSummary.action")
	@ResponseBody
	public ResponseInfo insertProjectInfoSummary(@RequestBody ProjectInfoSummaryEntity projectInfoSummaryEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = projectInfoSummaryService.insertProjectInfoSummary(projectInfoSummaryEntity);
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
	*/
/*	// 更新工程信息
	@RequestMapping("/updateProjectInfoSummary.action")
	@ResponseBody
	public ResponseInfo updateProjectInfoSummary(@RequestBody ProjectInfoSummaryEntity projectInfoSummaryEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = projectInfoSummaryService.updateProjectInfoSummary(projectInfoSummaryEntity);
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
	}*/
	
	// 删除工程信息
	@RequestMapping("/deleteProjectInfoSummary.action")
	@ResponseBody
	public ResponseInfo deleteProjectInfoSummary(@RequestBody ProjectInfoSummaryEntity projectInfoSummaryEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = projectInfoSummaryService.deleteProjectInfoSummary(projectInfoSummaryEntity);
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
	
	// 新增工程信息
	@RequestMapping("/insertProjectInfoSummary.action")
	@ResponseBody
    public ResponseInfo addUser(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{  
		ResponseInfo info = new ResponseInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath ="D:\\curing\\image\\"+date; /*request.getSession().getServletContext().getRealPath(MessageUtil.path + date);*/ 
        JSONObject jsonObject=JSONObject.fromObject(request.getParameter("params"));
        // 上传文件
        info = Uploadfiles.uploadfiles(myfiles,realPath);
        
        if (info.getCode() != "error") {
        	ProjectInfoSummaryEntity projectInfoSummaryEntity=(ProjectInfoSummaryEntity)JSONObject.toBean(jsonObject, ProjectInfoSummaryEntity.class);
        	projectInfoSummaryEntity.setUploadPicture(date);
    		int res = projectInfoSummaryService.insertProjectInfoSummary(projectInfoSummaryEntity);
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

	// 更新工程信息
	@RequestMapping("/updateProjectInfoSummary.action")
	@ResponseBody
    public ResponseInfo updateProjectInfoSummary(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{  
		ResponseInfo info = new ResponseInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath = "";
        JSONObject jsonObject=JSONObject.fromObject(request.getParameter("params"));
        ProjectInfoSummaryEntity projectInfoSummaryEntity=(ProjectInfoSummaryEntity)JSONObject.toBean(jsonObject, ProjectInfoSummaryEntity.class);

		if(!projectInfoSummaryEntity.getUploadPicture().equals("") && projectInfoSummaryEntity.getUploadPicture() != "") {
			realPath ="D:\\curing\\image\\"+projectInfoSummaryEntity.getUploadPicture(); /* request.getSession().getServletContext().getRealPath(MessageUtil.path + date);  */
		}else {
			realPath ="D:\\curing\\image\\"+date; /* request.getSession().getServletContext().getRealPath(MessageUtil.path + projectInfoSummaryEntity.getUploadPicture());*/
			projectInfoSummaryEntity.setUploadPicture(date);
		}
		// 上传文件
		info = Uploadfiles.uploadfiles(myfiles,realPath);
		
        if (info.getCode() != "error") {
    		int res = projectInfoSummaryService.updateProjectInfoSummary(projectInfoSummaryEntity);
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
	
	// 更新工程信息
	@RequestMapping("/updateProjectTenderingSummary.action")
	@ResponseBody
    public ResponseInfo updateProjectTenderingSummary(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{  
		ResponseInfo info = new ResponseInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath = "";
        JSONObject jsonObject=JSONObject.fromObject(request.getParameter("params"));
        ProjectInfoSummaryEntity projectInfoSummaryEntity=(ProjectInfoSummaryEntity)JSONObject.toBean(jsonObject, ProjectInfoSummaryEntity.class);

		if(!projectInfoSummaryEntity.getUploadPicture2().equals("") && projectInfoSummaryEntity.getUploadPicture2() != "") {
			realPath ="D:\\curing\\image\\"+projectInfoSummaryEntity.getUploadPicture2(); /* request.getSession().getServletContext().getRealPath(MessageUtil.path + date);  */
		}else {
			realPath ="D:\\curing\\image\\"+date; /* request.getSession().getServletContext().getRealPath(MessageUtil.path + projectInfoSummaryEntity.getUploadPicture());*/
			projectInfoSummaryEntity.setUploadPicture2(date);
		}
		// 上传文件
		info = Uploadfiles.uploadfiles(myfiles,realPath);
		
        if (info.getCode() != "error") {
    		int res = projectInfoSummaryService.updateProjectInfoSummary(projectInfoSummaryEntity);
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
	
	// 打开文件
	@RequestMapping("/openFile.action")
	@ResponseBody
	public ResponseInfo openWenJian(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws IOException{
		List<ProjectInfoSummaryEntity> list = projectInfoSummaryService.getProjectInfoSummaryList(map);
		ResponseInfo info = new ResponseInfo();
		try {
			java.awt.Desktop.getDesktop().open(new File(list.get(0).getUploadPicture()));
		} catch (Exception e) {
			info.setCode(MessageUtil.error);
			info.setMessage(MessageUtil.UPLOADFILEERROR);
		}
		return info;
	}
	
	// 打开文件
	@RequestMapping("/selectFileList.action")
	@ResponseBody
	public DataTablesResponseInfo selectFileList(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws IOException{
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ProjectInfoSummaryEntity> list = projectInfoSummaryService.getProjectInfoSummaryList(map);
		List<Map<String,Object>> fileNameList = new ArrayList<Map<String, Object>>();
		try {
			// 获得指定文件对象  
			File file = new File(  "D:\\curing\\image\\" + list.get(0).getUploadPicture()); /*new File(request.getSession().getServletContext().getRealPath(MessageUtil.path + list.get(0).getUploadPicture())); */
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
		return projectInfoSummaryService.delUploadfile(map);
	}
	
	@RequestMapping("/downUploadfile.action")
	@ResponseBody
	public void  downUploadfile(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="imageNamePrams")String imageNamePrams,@RequestParam(value="imagePrefix")String imagePrefix) throws IOException  {
			imageNamePrams = imageNamePrams.substring(0,imageNamePrams.length() - 1);
		  projectInfoSummaryService.downUploadfile(imageNamePrams,imagePrefix,response);
	}
	
	// 打开文件
	@RequestMapping("/selectFileList1.action")
	@ResponseBody
	public DataTablesResponseInfo selectFileList1(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws IOException{
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ProjectInfoSummaryEntity> list = projectInfoSummaryService.getProjectInfoSummaryList(map);
		List<Map<String,Object>> fileNameList = new ArrayList<Map<String, Object>>();
		try {
			// 获得指定文件对象  
			File file = new File(  "D:\\curing\\image\\" + list.get(0).getUploadPicture2()); /*new File(request.getSession().getServletContext().getRealPath(MessageUtil.path + list.get(0).getUploadPicture())); */
			// 获得该文件夹内的所有文件   
			File[] array = file.listFiles(); 
			 for (int i = 0; i < array.length; i++) {
				 File fs = array[i];
				 Map<String, Object> fileNamemap = new HashMap<String, Object>();
				 fileNamemap.put("name", fs.getName());
				 fileNamemap.put("prefix", list.get(0).getUploadPicture2());
				 fileNameList.add(fileNamemap);
			 }
			 dInfo.setData(fileNameList);

		} catch (Exception e) {
			// TODO: handle exception
			dInfo.setData(MessageUtil.error);
		}
		return dInfo;
	}
}
