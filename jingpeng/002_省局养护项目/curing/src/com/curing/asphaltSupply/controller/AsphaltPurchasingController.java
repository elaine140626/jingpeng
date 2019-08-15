package com.curing.asphaltSupply.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.curing.asphaltSupply.model.AsphaltPurchasingEntity;
import com.curing.asphaltSupply.service.AsphaltPurchasingService;
import com.curing.common.model.ResponseInfo;
import com.curing.common.util.MessageUtil;

@Controller
@RequestMapping("/AsphaltPurchasing")
public class AsphaltPurchasingController {

	@Autowired
	AsphaltPurchasingService asphaltPurchasingService;
	
	// 获取沥青采购数据
	@RequestMapping("/getAsphaltPurchasing.action")
	@ResponseBody
	public List<AsphaltPurchasingEntity> getAsphaltPurchasing(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		List<AsphaltPurchasingEntity> list = asphaltPurchasingService.getAsphaltPurchasing(map);
		return list;
	}

	// 新增沥青采购
	@RequestMapping("insertAsphaltPurchasing.action")
	@ResponseBody
	public ResponseInfo insertAsphaltPurchasing(@RequestBody AsphaltPurchasingEntity asphaltPurchasingEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = asphaltPurchasingService.insertAsphaltPurchasing(asphaltPurchasingEntity);
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
	
	// 更新沥青采购
	@RequestMapping("/updateAsphaltPurchasing.action")
	@ResponseBody
	public ResponseInfo updateAsphaltPurchasing(@RequestBody AsphaltPurchasingEntity asphaltPurchasingEntity) throws IOException{
		ResponseInfo info = new ResponseInfo();
		int res = asphaltPurchasingService.updateAsphaltPurchasing(asphaltPurchasingEntity);
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
}
