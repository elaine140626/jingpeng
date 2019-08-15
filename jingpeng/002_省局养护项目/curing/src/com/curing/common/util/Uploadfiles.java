package com.curing.common.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.curing.common.model.ResponseInfo;

public class Uploadfiles {
	// 上传文件
	public static ResponseInfo uploadfiles(MultipartFile[] myfiles, String realPath) {
		ResponseInfo info = new ResponseInfo();
		String originalFilename = null;
		for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				break;
			} else {
				String uploadTime = UUID.randomUUID().toString().replaceAll("-", "");
				// 获取文件名
				String name = myfile.getOriginalFilename().substring(0, myfile.getOriginalFilename().indexOf("."));
				String name2 = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().indexOf("."),
						myfile.getOriginalFilename().length());
				originalFilename = new StringBuilder(name).append("_").append(uploadTime).append(name2).toString();
				try {
					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
				} catch (IOException e) {
					System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
					e.printStackTrace();
					info.setMessage(MessageUtil.UploadFailure);
					info.setCode("error");
					return info;
				}
			}
		}
		return info;
	}
}
