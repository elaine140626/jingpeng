package com.curing.projectSchedule.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.curing.common.model.ResponseInfo;
import com.curing.projectSchedule.dao.SupervisionApplicationDao;
import com.curing.projectSchedule.model.SupervisionApplicationEntity;
import com.curing.projectSchedule.service.SupervisionApplicationService;
import com.curing.util.FileBean;
import com.curing.util.ZipUtils;

@Service
@Transactional
public class SupervisionApplicationServiceImpl implements SupervisionApplicationService{
	@Autowired
	private SupervisionApplicationDao supervisionApplicationDao;
	
	// 工程进度（监督申请）List取得
	public List<SupervisionApplicationEntity> getSupervisionApplicationList(Map<String, Object> map,int count){
		List<SupervisionApplicationEntity> list = supervisionApplicationDao.getSupervisionApplicationList(map);
		if(count == 0) {
			String path = "";
			for (int i = 0; i < list.size(); i++) {
				path = "D:\\curing\\engineeringFile\\"+list.get(i).getSupervisorPlan();
				File supervisorPlanFile = new File(path);//File类型可以是文件也可以是文件夹
				File[] supervisorPlanFileList = supervisorPlanFile.listFiles();
				if(supervisorPlanFileList != null && supervisorPlanFileList.length > 0) {
					list.get(i).setSupervisorPlan(supervisorPlanFileList[0].getName().substring(0, supervisorPlanFileList[0].getName().length()-37));
				}else {
					list.get(i).setSupervisorPlan("");
				}
				path = "D:\\curing\\engineeringFile\\"+list.get(i).getSupervisorDetailed();
				File supervisorDetailedFile = new File(path);//File类型可以是文件也可以是文件夹
				File[] supervisorDetailedfileList = supervisorDetailedFile.listFiles();
				if(supervisorPlanFileList != null && supervisorDetailedfileList.length > 0) {
					list.get(i).setSupervisorDetailed(supervisorDetailedfileList[0].getName().substring(0, supervisorDetailedfileList[0].getName().length()-37));
				}else {
					list.get(i).setSupervisorDetailed("");
				}
			}
		}
		return list;
	}
	
	// 新增工程进度（监督申请）
	public int insertSupervisionApplication(SupervisionApplicationEntity supervisionApplicationEntity){
		return supervisionApplicationDao.insertSupervisionApplication(supervisionApplicationEntity);
	}
	
	// 更新工程进度（监督申请）
	public int updateSupervisionApplication(SupervisionApplicationEntity supervisionApplicationEntity){
		return supervisionApplicationDao.updateSupervisionApplication(supervisionApplicationEntity);
	}
	
	// 删除工程进度（监督申请）
	public int deleteSupervisionApplication(SupervisionApplicationEntity supervisionApplicationEntity){
		return supervisionApplicationDao.deleteSupervisionApplication(supervisionApplicationEntity);
	}

	@Override
	public ResponseInfo delUploadfile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		Boolean isHave = true;
		String imageNameParams = (String) map.get("imageNameList");
		List<Object> list =JSON.parseArray(imageNameParams);
		String imagePrefix = (String) map.get("imagePrefix");
		for (Object object : list){
			@SuppressWarnings("unchecked")
			Map <String,Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
			String path = "D:\\curing\\image\\"+imagePrefix+"\\"+ret.get("imageName");
			File file=new File(path);
			if  (!file .exists()  && !file .isDirectory())     
			{      
				//不存在
				info.setMessage("操作失败,文件已被删除");
				info.setCode("error");
				isHave = false;
				break;
			}else{
				info.setMessage("操作成功");
				info.setCode("success");
				isHave = true;
			}
		}
		if(isHave) {
			for (Object object : list){
				@SuppressWarnings("unchecked")
				Map <String,Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
				String path = "D:\\curing\\image\\"+imagePrefix+"\\"+ret.get("imageName");
				Boolean isSuccess =  FileUtils.deleteQuietly(new File(path));
				if(isSuccess == false) {
					info.setMessage("文件下载中,请刷新后重试");
					info.setCode("error");
					break;
				}else {
					info.setMessage("操作成功");
					info.setCode("success");
				}
			}
			return info;
		}else {
			return info;
		}
	}

	@Override
	public void downUploadfile(String imageNamePrams, String imagePrefix, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		List<FileBean> fileList = new ArrayList<>();
		String[] imageNameAray = imageNamePrams.split(","); // 用,分割
		for (int i = 0; i < imageNameAray.length; i++) {
			FileBean fileBean = new FileBean();
			fileBean.setFileId(i);
			fileBean.setFileName(imageNameAray[i]);
			fileBean.setFilePath("D:\\curing\\image\\"+imagePrefix+"\\");
			fileList.add(fileBean);
		}
		String zipName = System.currentTimeMillis()+".zip";
		response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for(Iterator<FileBean> it = fileList.iterator();it.hasNext();){
                FileBean file = it.next();
                ZipUtils.doCompress(file.getFilePath()+file.getFileName(), out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
	}

	@Override
	public ResponseInfo deleteSupervisorPlanFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		Boolean isHave = false;
		List<SupervisionApplicationEntity> list = this.getSupervisionApplicationList(map,1);
		String pix = list.get(0).getSupervisorPlan();
        String path = "D:\\curing\\engineeringFile\\"+pix;
        File supervisorPlanFile = new File(path);//File类型可以是文件也可以是文件夹
		File[] supervisorPlanFileList = supervisorPlanFile.listFiles();
		if(supervisorPlanFileList != null) {
			String filename = supervisorPlanFileList[0].getName();
			path = "D:\\curing\\engineeringFile\\"+pix+"\\"+filename;
			File file=new File(path);
			if  (!file .exists()  && !file .isDirectory())     
			{      
				//不存在
				info.setMessage("操作失败,文件已被删除");
				info.setCode("error");
				isHave = false;
			}else{
				info.setMessage("操作成功");
				info.setCode("success");
				isHave = true;
			}
		}
		if(isHave) {
			Boolean isSuccess =  FileUtils.deleteQuietly(new File(path));
			if(isSuccess == false) {
				info.setMessage("文件下载中,请稍后重试");
				info.setCode("error");
			}else {
				info.setMessage("操作成功");
				info.setCode("success");
			}
		}
		return info;
	}

	@Override
	public ResponseInfo deleteSupervisorDetailedFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		Boolean isHave = false;
		List<SupervisionApplicationEntity> list = this.getSupervisionApplicationList(map,1);
		String pix = list.get(0).getSupervisorDetailed();
        String path = "D:\\curing\\engineeringFile\\"+pix;
        File supervisorPlanFile = new File(path);//File类型可以是文件也可以是文件夹
		File[] supervisorPlanFileList = supervisorPlanFile.listFiles();
		if(supervisorPlanFileList != null) {
			String filename = supervisorPlanFileList[0].getName();
			path = "D:\\curing\\engineeringFile\\"+pix+"\\"+filename;
			File file=new File(path);
			if  (!file .exists()  && !file .isDirectory())     
			{      
				//不存在
				info.setMessage("操作失败,文件已被删除");
				info.setCode("error");
				isHave = false;
			}else{
				info.setMessage("操作成功");
				info.setCode("success");
				isHave = true;
			}
		}
		if(isHave) {
			Boolean isSuccess =  FileUtils.deleteQuietly(new File(path));
			if(isSuccess == false) {
				info.setMessage("操作失败,请稍后重试");
				info.setCode("error");
			}else {
				info.setMessage("操作成功");
				info.setCode("success");
			}
		}
		return info;
	}	

}
