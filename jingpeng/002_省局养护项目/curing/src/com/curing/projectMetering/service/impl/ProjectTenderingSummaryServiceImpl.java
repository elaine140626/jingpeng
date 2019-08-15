package com.curing.projectMetering.service.impl;

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
import com.curing.projectMetering.dao.ProjectTenderingSummaryDao;
import com.curing.projectMetering.model.ProjectTenderingSummaryEntity;
import com.curing.projectMetering.service.ProjectTenderingSummaryService;
import com.curing.util.FileBean;
import com.curing.util.ZipUtils;

@Service
@Transactional
public class ProjectTenderingSummaryServiceImpl implements ProjectTenderingSummaryService{

	@Autowired
	private ProjectTenderingSummaryDao projectTenderingSummaryDao;
	
	// 工程招标List获取
	public List<ProjectTenderingSummaryEntity> getProjectTenderingSummaryList(Map<String, Object> map) {
		return projectTenderingSummaryDao.getProjectTenderingSummaryList(map);
	}

	// 更新工程招标
	public int updateProjectTenderingSummary(ProjectTenderingSummaryEntity projectTenderingSummaryEntity) {
		return projectTenderingSummaryDao.updateProjectTenderingSummary(projectTenderingSummaryEntity);
	}

	// 删除工程招标
	public int deleteProjectTenderingSummary(ProjectTenderingSummaryEntity projectTenderingSummaryEntity) {
		return projectTenderingSummaryDao.deleteProjectTenderingSummary(projectTenderingSummaryEntity);
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
