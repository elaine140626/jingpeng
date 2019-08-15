package com.curing.projectSchedule.service.impl;

import java.io.File;
import java.io.IOException;
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
import com.curing.projectSchedule.dao.ConstructionDao;
import com.curing.projectSchedule.model.ConstructionEntity;
import com.curing.projectSchedule.model.SupervisionApplicationEntity;
import com.curing.projectSchedule.service.ConstructionService;
import com.curing.util.FileBean;
import com.curing.util.ZipUtils;

@Service
@Transactional
public class ConstructionServiceImpl implements ConstructionService{
	@Autowired
	private ConstructionDao constructionDao;
	
	// 工程进度（施工组织设计）List取得
	public List<ConstructionEntity> getConstructionList(Map<String, Object> map,int count){
		List<ConstructionEntity> list = constructionDao.getConstructionList(map);
		if(count == 0) {
			String path = "";
			for (int i = 0; i < list.size(); i++) {
				path = "D:\\curing\\engineeringFile\\"+list.get(i).getBuildDesign();
				File buildDesignFile = new File(path);//File类型可以是文件也可以是文件夹
				File[] buildDesignFileList = buildDesignFile.listFiles();
				if(buildDesignFileList != null && buildDesignFileList.length > 0) {
					list.get(i).setBuildDesign(buildDesignFileList[0].getName().substring(0, buildDesignFileList[0].getName().length()-37)); 
				}else {
					list.get(i).setBuildDesign("");
				}
			}
		}
		return list;
	}
	
	// 新增工程进度（施工组织设计）
	public int insertConstruction(ConstructionEntity constructionEntity){
		return constructionDao.insertConstruction(constructionEntity);
	}
	
	// 更新工程进度（施工组织设计）
	public int updateConstruction(ConstructionEntity constructionEntity){
		return constructionDao.updateConstruction(constructionEntity);
	}
	
	// 删除工程进度（施工组织设计）
	public int deleteConstruction(ConstructionEntity constructionEntity){
		return constructionDao.deleteConstruction(constructionEntity);
	}

	@Override
	public ResponseInfo deleteBuildDesignFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		List<ConstructionEntity> list = this.getConstructionList(map, 1);
		String pix = list.get(0).getBuildDesign();
        String path = "D:\\curing\\engineeringFile\\"+pix;
        File buildDesignFile = new File(path);//File类型可以是文件也可以是文件夹
		File[] buildDesignFileList = buildDesignFile.listFiles();
		if(buildDesignFileList != null) {
			String filename = buildDesignFileList[0].getName();
			path = "D:\\curing\\engineeringFile\\"+pix+"\\"+filename;
			File file = new File(path);
			if  (!file .exists()  && !file .isDirectory())     
			{      
				//不存在
				info.setMessage("操作失败,文件已被删除");
				info.setCode("error");
			}else{
				Boolean isSuccess =  FileUtils.deleteQuietly(file);
				if(isSuccess == false) {
					info.setMessage("文件下载中,请稍后重试");
					info.setCode("error");
				}else {
					info.setMessage("操作成功");
					info.setCode("success");
				}
			}
		}
		return info;
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
					info.setMessage("操作失败,文件已被删除");
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

}
