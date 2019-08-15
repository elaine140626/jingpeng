package com.oil.controller.transportation;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
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
import com.oil.model.transportation.TranSportList;
import com.oil.service.transportation.TranSportListService;

@Controller
@RequestMapping("/TranSportList")
public class TranSportListController {

	@Autowired
	private TranSportListService tranSportListService;
	
	//获取集合
	@RequestMapping("/getTranSportList.action")
	@ResponseBody
	public DataTablesResponseInfo getTranSportList(HttpServletRequest request, @RequestParam Map<String,Object> map) throws ParseException  {
		return tranSportListService.getTranSportList(map);
	}
	
	//修改
	@RequestMapping("/updateTranSportList.action")
	@ResponseBody
    public ResponseInfo updateTranSportList(@RequestParam(required = true) MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{  
		ResponseInfo info = new ResponseInfo();
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath = request.getSession().getServletContext().getRealPath("/upload");  
        String param = request.getParameter("param");
        Map<String, Object> mapList = new HashMap<String, Object>();
		mapList.put("param", param);
        String originalFilename = null;  
        Boolean isDown = true;
        for(MultipartFile myfile : myfiles){  
            if(myfile.isEmpty()){
            	isDown = false;
                break; 
            }else{  
        		// 获取文件名
        		String name = myfile.getOriginalFilename().substring(0, myfile.getOriginalFilename().indexOf("."));
        		String name2 = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().indexOf("."), myfile.getOriginalFilename().length());
        		originalFilename = new StringBuilder(name).append(name2).toString();
        		
        		//判断文件名字是否重复存在
        		String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+originalFilename;
    			File file=new File(path);
    			 if  (file.exists() )     
    			 {      
    				 String uuId = UUID.randomUUID().toString().replaceAll("-", "");
    			     //存在
    				 originalFilename = new StringBuilder(name).append(uuId).append(name2).toString();
    			 }
    			 
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
        	mapList.put("serialID", mapList.get("billNumber"));
        	mapList.put("fileName", originalFilename);
        	mapList.put("fileRoute", uploadAddress);
        }
      	info = tranSportListService.updateTranSportList(mapList,request);
        return info; 
	}
	
	//获取出库单
	@RequestMapping("/getExOrStPlateNumber.action")
	@ResponseBody
	public DataTablesResponseInfo getExOrStPlateNumber(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return tranSportListService.getExOrStPlateNumber(map);
	}
	//删除文件
	@RequestMapping("/delUploadfile.action")
	@ResponseBody
	public ResponseInfo delUploadfile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListService.delUploadfile(map,request);
	}
	//判断文件
	@RequestMapping("/isHaveUpload.action")
	@ResponseBody
	public ResponseInfo isHaveUpload(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListService.isHaveUpload(map);
	}
	//删除运输单
	@RequestMapping("/delTranSportList.action")
	@ResponseBody
	public ResponseInfo delTranSportList(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListService.delTranSportList(map);
	}
	//添加运输单
	@RequestMapping("/adduploadfile.action")
	@ResponseBody
	public ResponseInfo adduploadfile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListService.adduploadfile(map);
	}
	
/*	@RequestMapping("/addTranSportList.action")
	@ResponseBody
	public ResponseInfo addTranSportList(HttpServletRequest request, @RequestParam Map<String, Object> map)  {
		return tranSportListService.addTranSportList(map);
	}
	*/
	
/*	@RequestMapping("/getSaleType.action")
	@ResponseBody
	public DataTablesResponseInfo getSaleType()  {
		return tranSportListService.getSaleType();
	}*/
	
/*	@RequestMapping("/getMaterielinfo.action")
	@ResponseBody
	public DataTablesResponseInfo getMaterielinfo()  {
		return tranSportListService.getMaterielinfo();
	}*/
	
	
/*	@RequestMapping("/getIsExAndSt.action")
	@ResponseBody
	public DataTablesResponseInfo getIsExAndSt(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListService.getIsExAndSt(map);
	}*/
	
/*	@RequestMapping("/getPlate.action")
	@ResponseBody
	public DataTablesResponseInfo getPlate()  {
		return tranSportListService.getPlate();
	}*/
	
/*	@RequestMapping("/getTransportsById.action")
	@ResponseBody
	public DataTablesResponseInfo getTransportsById(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListService.getTransportsById(map);
	}*/
	
/*	@RequestMapping("/updateNoWeighOut.action")
	@ResponseBody
	public ResponseInfo updateNoWeighOut(HttpServletRequest request, @RequestParam Map<String,Object> map) throws Exception  {
		return tranSportListService.updateNoWeighOut(map);
	}*/
	
/*	@RequestMapping("/getUploadfile.action")
	@ResponseBody
	public DataTablesResponseInfo getUploadfile(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListService.getUploadfile(map,request);
	}*/
	
/*	@RequestMapping("/getEXById.action")
	@ResponseBody
	public DataTablesResponseInfo getEXById(HttpServletRequest request, @RequestParam Map<String,Object> map)  {
		return tranSportListService.getEXById(map);
	}*/
}
