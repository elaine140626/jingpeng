package com.oil.controller.app;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.oil.dao.transportation.TranSportListDao;
import com.oil.service.map.MapService;
import com.oil.service.transportation.TranSportListService;
import com.oil.util.QrCodeUtils;

import sun.misc.BASE64Decoder;


@Controller
@RequestMapping("/oilfactoryApp")
public class oilfactoryApp {
	
	@Autowired
	private TranSportListService tranSportListService;
	
	@Autowired
	private MapService mapService;
	
    //拍照功能加上实际称重数据的保存
    @RequestMapping(value = { "/getTranSportList.json" })//id查询明细 车牌号查询所有
    public ResponseEntity<Map<String, Object>> getTranSportList(@RequestParam(value = "tranSport")String tranSport) throws IOException{
    	    Map<String,Object> map= JSON.parseObject(tranSport);
    		List<Map<String, Object>> tranSportList = tranSportListService.getTranSportListApp(map);
    		Map<String, Object> param = new HashMap<String, Object>();
    		param.put("tranSportList", tranSportList);
/*    		if(tranSportList != null && tranSportList.size()>0 && tranSportList.get(0).get("id")!=null) {
    			param.put("tranSportList", tranSportList);	
    		}else {
    			List<Map<String, Object>> dbTranSportList = tranSportListService.getOutTranSportListApp(map);
    			param.put("tranSportList", dbTranSportList);
    		}*/
    		return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    //图片回显
    @RequestMapping("/preview.json")
	public void showImage(@RequestParam(value="receiptPicture")String receiptPicture,HttpServletRequest request, HttpServletResponse response) throws Exception{
		    response.setContentType("text/html; charset=UTF-8");
		    response.setContentType("image/jpeg");
		    String fname ="D:\\oilfactoryApp\\image\\"+receiptPicture;
//		    String newpath = new String(fname.getBytes("ISO-8859-1"), "UTF-8");
		    FileInputStream fis = new FileInputStream(fname);
		    OutputStream os = response.getOutputStream();
		    try
		    {
		    	int count = 0;
		    	byte[] buffer = new byte[1024 * 1024];
		    	while ((count = fis.read(buffer)) != -1)
		    	os.write(buffer, 0, count);
		    	os.flush();
		    }
		    	catch (IOException e)
		    {
		    	e.printStackTrace();
		    }
		    	finally
		    {
		    	if (os != null)
		    		os.close();
		    	if (fis != null)
		    		fis.close();
		    }
	}
	/**
	 * 获取当前时间的指定格式
	 * @param format
	 * @return
	 */
	public static String getCurrDate(String format) {
		return dateToString(new Date(), format);
	}

	/**
	 * 把日期转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(java.util.Date date, String format) {
		if(date==null)
			return "";
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			// logger.error ( e );
		}
		return result;
	}
	
	
	//查询车牌号与GPS的绑定
	@RequestMapping(value = { "/GPSlogin.json" })
	public ResponseEntity<Map<String, Object>> GPSlogin(HttpServletRequest request,@RequestParam(value="params")String params) throws IOException{
		Map<String,Object> Map= JSON.parseObject(params);
		List<Map<String,Object>> userInfo = mapService.GPSlogin(Map);
		Map<String,Object> isLogin = new HashMap<>();
		if(userInfo.size() > 0) {
			isLogin.put("code", 1);
			isLogin.put("userInfo", userInfo.get(0));
		}else {
			isLogin.put("code", 0);
		}
		return new ResponseEntity<Map<String, Object>>(isLogin,HttpStatus.OK);
	}
		
	
	//添加车辆轨迹
	@RequestMapping(value = { "/addCarsupervise.json" })
	public ResponseEntity<Map<String, Object>> addCarsupervise(HttpServletRequest request,@RequestParam(value="carsuperviseInfo")String carsuperviseInfo) throws IOException{
		Map<String,Object> paramMap= JSON.parseObject(carsuperviseInfo);
		int result = mapService.addCarsupervise(paramMap);
		Map<String,Object> map = new HashMap<>();
		map.put("code", result);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//查询所有车牌号
	@RequestMapping(value = { "/getCarPlateNumber.json" })
	public ResponseEntity<Map<String, Object>> getCarPlateNumber(HttpServletRequest request) throws IOException{
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> plateNumberMap =  mapService.getCarPlateNumber();
		map.put("plateNumberMap", plateNumberMap);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//查询车牌号与GPS的绑定
	@RequestMapping(value = { "/getPdacarinfo.json" })
	public ResponseEntity<Map<String, Object>> getPdacarinfo(HttpServletRequest request,@RequestParam(value="params")String params) throws IOException{
		Map<String,Object> Map= JSON.parseObject(params);
		List<Map<String,Object>> pdacarInfo = mapService.getPdacarinfo(Map);
		Map<String,Object> mapList = new HashMap<>();
		mapList.put("pdacarInfo", pdacarInfo);
		return new ResponseEntity<Map<String, Object>>(mapList,HttpStatus.OK);
	}
	
	//修改车牌号与GPS的绑定
	@RequestMapping(value = { "/updatePdaCarInfo.json" })
	public ResponseEntity<Map<String, Object>> updatePdaCarInfo(HttpServletRequest request,@RequestParam(value="pdacarInfo")String pdacarInfo) throws IOException{
		String param = request.getParameter("pdacarInfo");
		Map<String,Object> paramMap= JSON.parseObject(param);
		int result = mapService.updatepdacarinfo(paramMap);
		Map<String,Object> map = new HashMap<>();
		if(result > 0) {
			result = mapService.addPdaCarInfoHistory(paramMap);
			map.put("code", result);
		}else {
			map.put("code", result);
		}
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	//查询车辆的历史修改记录
	@RequestMapping(value = { "/getPdaCarInfoHistory.json" })
	public ResponseEntity<Map<String, Object>> getPdaCarInfoHistory(HttpServletRequest request,@RequestParam(value="gpsNumber")String gpsNumber) throws IOException{
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> paramMap= JSON.parseObject(gpsNumber);
		List<Map<String,Object>> pdaCarInfoHistoryMap =  mapService.getPdaCarInfoHistory(paramMap);
		map.put("pdaCarInfoHistoryMap", pdaCarInfoHistoryMap);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}
	
	
	//返回二維碼IO流
	@ResponseBody
	@RequestMapping(value = { "/getQrCode.json" })
	public void getQrCode(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "tranSport")String tranSport) throws Exception{
		Map<String,Object> map= JSON.parseObject(tranSport);
				List<Map<String, Object>> dbTranSportList = tranSportListService.getTranSportListApp(map);
				String result = "类型:"+dbTranSportList.get(0).get("ttype")+"\n"+"称重时间:"+dbTranSportList.get(0).get("createrDate")+"\n"+"客户名称:"+dbTranSportList.get(0).get("client")+"\n"+"车牌号码:"+dbTranSportList.get(0).get("plateNumber")+"\n"+
						"产品名称:"+dbTranSportList.get(0).get("materielName")+"\n"+"规格型号:"+dbTranSportList.get(0).get("materielModel")+"\n"+"净重:"+dbTranSportList.get(0).get("weigh");
				QrCodeUtils.qrCodeShow(request, response, result);

	}
	
	//返回打印信息
	@ResponseBody
	@RequestMapping(value = { "/getPrintingInfo.json" }) //id
	public ResponseEntity<Map<String, Object>> getPrintingInfo(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "tranSport")String tranSport) throws Exception{
		 Map<String,Object> map= JSON.parseObject(tranSport);
 		List<Map<String, Object>> tranSportList = tranSportListService.getTranSportListApp(map);
 		Map<String, Object> param = new HashMap<String, Object>();
 		param.put("tranSportList", tranSportList);
 		return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
	}	
	
//    public String printDataForJSON(Object obj){  
//        JSONObject json = JSONObject.fromObject(obj);//将java对象转换为json对象  
//        String str = json.toString();//将json对象转换为字符串  
//          
//        return str;  
//    } 
	//拍照功能加上实际称重数据的保存
	@ResponseBody
	@RequestMapping(value = { "/fileUpload.json" })
	public boolean GenerateImage(@RequestParam(value = "tranSport")String tranSport,@RequestParam(value="imgStr")String imgStr,@RequestParam(value="ImageName")String ImageName,HttpServletRequest request ) { // 对字节数组字符串进行Base64解码并生成图片
		Map<String,Object> Map= JSON.parseObject(tranSport);
        if (imgStr == null) // 图像数据为空
        return false;
       BASE64Decoder decoder = new BASE64Decoder();
        try {
        // Base64解码
        byte[] b = decoder.decodeBuffer(imgStr);
        for (int i = 0; i < b.length; ++i) {
        if (b[i] < 0) {// 调整异常数据
        b[i] += 256;
        }
        }
        // 生成jpeg图片
       // String imgFilePath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"/"+ImageName+".jpg";
        String imgFilePath = "D:\\oilfactoryApp\\image\\"+ImageName;
        Map.put("receiptPicture", ImageName);
		/*int result = */tranSportListService.updateTranSportListApp(Map);
/*		if(result <= 0 ) {
			return false;
		}*/
		OutputStream out = new FileOutputStream(imgFilePath);
        out.write(b);
        out.flush();
        out.close();
        return true;
        } catch (Exception e) {
        return false;
        }
       }
}
