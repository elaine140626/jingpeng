package com.oil.controller.purchase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.service.purchase.PurchaseService;

@Controller
@RequestMapping("/Purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping("/getSupplier.action")
	@ResponseBody
	public DataTablesResponseInfo getSupplier()  {
		return purchaseService.getSupplier();
	}
	
	@RequestMapping("/getPurchaserequisition.action")
	@ResponseBody
	public DataTablesResponseInfo getPurchaserequisition(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return purchaseService.getPurchaserequisition(map);
	}
	
	@RequestMapping("/getMaterielInfo.action")
	@ResponseBody
	public DataTablesResponseInfo getMaterielInfo(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return purchaseService.getMaterielInfo(map);
	}
	
	@RequestMapping("/getPurchasecontractList.action")
	@ResponseBody
	public DataTablesResponseInfo getPurchasecontractList(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return purchaseService.getPurchasecontractList(map);
	}
	
	@RequestMapping("/getPurchasecontractById.action")
	@ResponseBody
	public DataTablesResponseInfo getPurchasecontractById(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return purchaseService.getPurchasecontractById(map);
	}
	
	@RequestMapping("/delPurchasecontractById.action")
	@ResponseBody
	public ResponseInfo delPurchasecontractById(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return purchaseService.delPurchasecontractById(map);
	}
	
	@RequestMapping("/delPurchaseorderinfo.action")
	@ResponseBody
	public ResponseInfo delPurchaseorderinfo(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return purchaseService.delPurchaseorderinfo(map);
	}
	
	@RequestMapping("/ZuoFeiPurchasecontract.action")
	@ResponseBody
	public ResponseInfo ZuoFeiPurchasecontract(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return purchaseService.ZuoFeiPurchasecontract(map);
	}
	
	@RequestMapping("/getExAndstor.action")
	@ResponseBody
	public ResponseInfo getExAndstor(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return purchaseService.getExAndstor(map);
	}
	
	@RequestMapping("/upload.action")
	@ResponseBody
    public ResponseInfo addUser(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{  
		Map<String, String> map = new HashMap<String, String>();
		ResponseInfo info = new ResponseInfo();
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath = request.getSession().getServletContext().getRealPath("/upload");  
        String purchaseorderinfoMap = request.getParameter("purchaseorderinfoParams");
        String purchaseorderinfoListMap = request.getParameter("purchaseorderinfoListParams");
        String contractNumber = request.getParameter("contractNumber");
        String flag = request.getParameter("flag");
        Map<String, Object> mapList = new HashMap<String, Object>();
		mapList.put("purchaseorderinfoMap", purchaseorderinfoMap);
		mapList.put("purchaseorderinfoListMap", purchaseorderinfoListMap);
		mapList.put("flag", flag);
        String originalFilename = null;  
        Boolean isDown = true;
        for(MultipartFile myfile : myfiles){  
            if(myfile.isEmpty()){
            	isDown = false;
                break; 
            }else{  
        		String uploadTime = UUID.randomUUID().toString().replaceAll("-","");
        		// 获取文件名
        		String name = myfile.getOriginalFilename().substring(0, myfile.getOriginalFilename().indexOf("."));
        		String name2 = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().indexOf("."), myfile.getOriginalFilename().length());
        		originalFilename = new StringBuilder(name).append("_").append(uploadTime).append(name2).toString();
                try {  
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));  
                } catch (IOException e) {  
                    System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");  
                    e.printStackTrace();
                    info.setMessage("文件上传失败");
    				info.setCode("error");
                    return info;  
                }  
            }  
        }
        if(isDown) {
        	String uploadAddress = request.getContextPath() + "/upload/" + originalFilename;
        	mapList.put("uploadAddress", uploadAddress);
        	mapList.put("fileName", originalFilename);
        	mapList.put("contractNumber", contractNumber);
        }
        	info = purchaseService.addPurchasecontract(mapList);
        return info; 
    } 
	
}
