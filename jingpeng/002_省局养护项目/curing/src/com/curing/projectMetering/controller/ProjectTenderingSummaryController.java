package com.curing.projectMetering.controller;

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
import com.curing.projectMetering.model.ProjectTenderingSummaryEntity;
import com.curing.projectMetering.service.ProjectTenderingSummaryService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/ProjectTenderingSummary")
public class ProjectTenderingSummaryController {
	@Autowired
	private ProjectTenderingSummaryService ProjectTenderingSummaryService;

	// 工程计划List取得
	@RequestMapping("/getProjectTenderingSummaryList.action")
	@ResponseBody
	public DataTablesResponseInfo getProjectTenderingSummaryList(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ProjectTenderingSummaryEntity> list = ProjectTenderingSummaryService.getProjectTenderingSummaryList(map);
		dInfo.setData(list);
		return dInfo;
	}
	
	
	// 工程计划单条获取
	@RequestMapping("/getProjectTenderingSummaryById.action")
	@ResponseBody
	public List<ProjectTenderingSummaryEntity> getProjectTenderingSummaryById(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<ProjectTenderingSummaryEntity> list = ProjectTenderingSummaryService.getProjectTenderingSummaryList(map);
		return list;
	}
	
	// 更新工程计划
	@RequestMapping("/updateProjectTenderingSummary.action")
	@ResponseBody
	public ResponseInfo updateProjectTenderingSummary(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ResponseInfo info = new ResponseInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath =""; /*request.getSession().getServletContext().getRealPath(MessageUtil.path + date);*/ 
        JSONObject jsonObject=JSONObject.fromObject(request.getParameter("params"));
        // 上传文件
        if (info.getCode() != "error") {
        	ProjectTenderingSummaryEntity projectTenderingSummaryEntity = (ProjectTenderingSummaryEntity) JSONObject.toBean(jsonObject,ProjectTenderingSummaryEntity.class);
        	if(!projectTenderingSummaryEntity.getUploadPicture().equals("") && projectTenderingSummaryEntity.getUploadPicture() != "") {
    			realPath ="D:\\curing\\image\\"+projectTenderingSummaryEntity.getUploadPicture(); /* request.getSession().getServletContext().getRealPath(MessageUtil.path + date);  */
    		}else {
    			realPath ="D:\\curing\\image\\"+date; /* request.getSession().getServletContext().getRealPath(MessageUtil.path + projectInfoSummaryEntity.getUploadPicture());*/
    			projectTenderingSummaryEntity.setUploadPicture(date);
    		}
        	info = Uploadfiles.uploadfiles(myfiles,realPath);
        	int res = ProjectTenderingSummaryService.updateProjectTenderingSummary(projectTenderingSummaryEntity);
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

/*	// 更新工程计划
	@RequestMapping("/updateProjectTenderingSummary.action")
	@ResponseBody
	public ResponseInfo updateProjectTenderingSummary(@RequestBody ProjectTenderingSummaryEntity ProjectTenderingSummaryEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = ProjectTenderingSummaryService.updateProjectTenderingSummary(ProjectTenderingSummaryEntity);
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
	
	// 删除工程计划
	@RequestMapping("/deleteProjectTenderingSummary.action")
	@ResponseBody
	public ResponseInfo deleteProjectTenderingSummary(@RequestBody ProjectTenderingSummaryEntity ProjectTenderingSummaryEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = ProjectTenderingSummaryService.deleteProjectTenderingSummary(ProjectTenderingSummaryEntity);
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
	
	// 打开文件
	@RequestMapping("/selectFileList.action")
	@ResponseBody
	public DataTablesResponseInfo selectFileList(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws IOException{
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ProjectTenderingSummaryEntity> list = ProjectTenderingSummaryService.getProjectTenderingSummaryList(map);
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
		return ProjectTenderingSummaryService.delUploadfile(map);
	}
	
	@RequestMapping("/downUploadfile.action")
	@ResponseBody
	public void  downUploadfile(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="imageNamePrams")String imageNamePrams,@RequestParam(value="imagePrefix")String imagePrefix) throws IOException  {
			imageNamePrams = imageNamePrams.substring(0,imageNamePrams.length() - 1);
			ProjectTenderingSummaryService.downUploadfile(imageNamePrams,imagePrefix,response);
	}
}
