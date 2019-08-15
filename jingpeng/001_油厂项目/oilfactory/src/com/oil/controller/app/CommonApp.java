package com.oil.controller.app;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oil.dao.dispath.OutboundDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.dispath.ContractEntity;
import com.oil.model.dispath.MaterielinfoEntity;
import com.oil.model.dispath.OrderNumberEntity;
import com.oil.model.dispath.OutboundEntity;
import com.oil.model.system.CarInfo;
import com.oil.model.system.Prefix;
import com.oil.model.system.Purchasecontract;
import com.oil.service.dispath.InstroeService;
import com.oil.service.dispath.OutboundService;
import com.oil.service.repertory.RepertoryService;
import com.oil.service.system.SerialNumberService;

@Controller
@RequestMapping("/commonApp")
public class CommonApp {
	
	@Autowired
	OutboundService outboundService;
	
	@Autowired
	InstroeService instroeService;
	
	@Autowired
	RepertoryService repertoryService;
	
	@Autowired
	private SerialNumberService serialNumberService;
	
	@Autowired
	OutboundDao outboundDao;

	
	// 前缀编号获取
    @RequestMapping(value = { "/getContractInfoPrefix.json" })
    public ResponseEntity<Map<String, Object>> getContractInfoPrefix(@RequestParam(value="types")String types){
    	Prefix prefixs = serialNumberService.getContractInfoPrefix(types);
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("prefixs", prefixs);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    // 前缀编号添加成功后自增
    @RequestMapping(value = { "/updateContractInfoPrefix.json" })
    public ResponseEntity<Map<String, Object>> updateContractInfoPrefix(@RequestParam(value="types")String types){
    	int prefixs = serialNumberService.updateContractInfoPrefix(types);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("prefixs", prefixs);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
    
	// 获取订单编号
    @RequestMapping(value = { "/getOrderNumberList.json" })
    public ResponseEntity<Map<String, Object>> getOrderNumberList(@RequestParam(value="flag")String flag,@RequestParam(value="type")String type,@RequestParam(value="id")String id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	map.put("flag", flag);
    	map.put("type", type);
    	List<OrderNumberEntity> orderNumberEntity = outboundService.getOrderNumberList(map);
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("orderNumberEntity", orderNumberEntity);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
	// 获取车牌号码
    @RequestMapping(value = { "/getAllPlateNumbers.json" })
    public ResponseEntity<Map<String, Object>> getAllPlateNumbers(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<CarInfo> carInfoList = outboundService.getAllPlateNumbers(map);
        map.put("carInfoList", carInfoList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
	// 获取出库单号
    @RequestMapping(value = { "/getAllOutboundList.json" })
    public ResponseEntity<Map<String, Object>> getAllOutboundList(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Map<String, Object>> mapList = outboundService.getAllOutboundList();
        map.put("mapList", mapList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
	// 获取采购合同
    @RequestMapping(value = { "/getPurchasecontractList.json" })
    public ResponseEntity<Map<String, Object>> getPurchasecontractList(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Purchasecontract> purchasecontractList = outboundService.getPurchasecontractList(map);
        map.put("purchasecontractList", purchasecontractList);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }
    
	// 获取合同编号
    @RequestMapping(value = { "/getContractList.json" })
    public ResponseEntity<Map<String, Object>> getContractList(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<ContractEntity> contractEntity = instroeService.getContractList(map);
        map.put("contractEntity", contractEntity);
        return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
    }  
	// 运距，止运地赋值
    @RequestMapping(value = { "/getCustomerTrans.json" })
    public ResponseEntity<Map<String, Object>> getCustomerTrans(@RequestParam(value="id")String id){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", id);
    	List<Map<String, Object>> customerTrans = outboundService.getCustomerTrans(map);
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("customerTrans", customerTrans);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 获取物料规格
    @RequestMapping(value = { "/getMaterielinfoList.json" })
    public ResponseEntity<Map<String, Object>> getMaterielinfoList(@RequestParam(value="contractId")String contractId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("contractId", contractId);
    	List<MaterielinfoEntity> materielinfoEntity = outboundService.getMaterielinfoList(map);
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("materielinfoEntity", materielinfoEntity);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 根据物料id获取规格
    @RequestMapping(value = { "/getMaterielModelList.json" })
    public ResponseEntity<Map<String, Object>> getMaterielModelList(@RequestParam(value="materielNameId")String materielNameId){
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<MaterielinfoEntity> materielinfoEntity = outboundService.getMaterielinfoList(map);
    	Map<String, Object> param = new HashMap<String, Object>();
    	List<MaterielinfoEntity> materielinfoEntityList = new ArrayList<MaterielinfoEntity>();
    	for (int i = 0; i < materielinfoEntity.size(); i++) {
    		if (materielinfoEntity.get(i).getMaterielNameId() == Integer.parseInt(materielNameId)) {
    			materielinfoEntityList.add(materielinfoEntity.get(i));
    		}	
		}
    	param.put("materielinfoEntity", materielinfoEntityList);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    // 需要审批的数据
    @RequestMapping(value = { "/getExamine.json" })
    public ResponseEntity<Map<String, Object>> getExamine(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("type", "out");
    	DataTablesResponseInfo infoList = outboundService.getInfoList(map);
    	map.put("type", "instroe");
    	DataTablesResponseInfo instroeInfoList = instroeService.getInfoList(map);
    	map.put("type", "repertory");
    	DataTablesResponseInfo repertoryInfoList = repertoryService.getInfoList(map);
    	Map<String, Object> param = new HashMap<String, Object>();
    	//出库单
    	param.put("infoList", infoList);
    	//入库单
    	param.put("instroeInfoList", instroeInfoList);
    	//未称重出库单
    	param.put("repertoryInfoList", repertoryInfoList);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    
    // 需要审批的数据
    @RequestMapping(value = { "/updateExamine.json" })
    public ResponseEntity<Map<String, Object>> updateExamine(@RequestParam(value="id")String id,@RequestParam(value="type")String type,@RequestParam(value="isExamine")String isExamine){
    	Map<String, Object> map = new HashMap<String, Object>();
    	int result = 0;
    	map.put("id", id);
    	map.put("isExamine", isExamine);
    	if (type.equals("out")) {
    		result += outboundService.updateExamine(map);
		}else if(type.equals("instroe")) {
			result += instroeService.updateExamine(map);
		}else if(type.equals("repertory")) {
			result += repertoryService.updateExamine(map);
		}
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("result", result);
        return new ResponseEntity<Map<String, Object>>(param,HttpStatus.OK);
    }
    
    @RequestMapping(value = { "/fileUpload.json" })
    public ResponseEntity<Map<String, String>> fileUpload(@RequestParam(value = "upload")MultipartFile file, HttpServletRequest request) throws IOException{		
    		Map<String, String> map = new HashMap<String, String>();
    		// 获取指定时间格式的字符串
    		String uploadTime = this.getCurrDate("yyyyMMddHHmmssmmmm");
    		// 获取文件名
    		String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
    		String name2 = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
    		String fileName = new StringBuilder(name).append("_").append(uploadTime).append(name2).toString();		
    		// 文件存储路径
    		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
    		File dir = new File(path, fileName);	
    		System.out.println("文件已上传到：" + path);
    		// 判断目录是否存在，若不存在则新建
    		if(!dir.exists()){			
    			dir.mkdirs();		
    		}		
    		//MultipartFile自带的解析方法		
    		file.transferTo(dir);		
    		map.put("fileName", fileName);
    		map.put("path", path+"/"+fileName);
    		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
    	}
    
    
//    @RequestMapping(value = { "/fileUpload.json" })
//    public ResponseEntity<List<String>> handleFileUpload(@RequestParam("file") MultipartFile file,
//            HttpServletRequest request, @RequestParam("uid") String uid, @RequestParam("token") String token)
//            throws IOException {
//    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
//        String url = CommonConstants.SERVE_PATH;
//        List<String> list = new ArrayList<String>();
//        // 创建文件
//        File dir = new File(url);
//        if (file.getSize() > 10485760) {
//            list.add(0, "erro");
//            list.add(1, CommonConstants.MEG_IFS_0010);
//        } else {
//            // 验证token
//            if (!loginService.getUserByUid(token, uid)) {
//                list.add(0, "erro1");
//                list.add(1, CommonConstants.MEG_IFS_0011);
//            } else {
//                
//                // 返回原来在客户端的文件系统的文件名
//                String fileName = Util.creatUUID() + file.getOriginalFilename();
//                System.out.println(uid);
//                personalCenterService.handleFileUpload(uid, path+fileName);
//
//                FileOutputStream imgOut = new FileOutputStream(new File(dir, fileName));// 根据 dir 抽象路径名和 img 路径名字符串创建一个新
//                                                                                        // File 实例。
//
//                imgOut.write(file.getBytes());// 返回一个字节数组文件的内容
//                imgOut.close();
//                list.add(0, "OK");
//            }
//        }
//
//        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
//    }

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
}

