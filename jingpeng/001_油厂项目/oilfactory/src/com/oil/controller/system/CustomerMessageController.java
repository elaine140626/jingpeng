package com.oil.controller.system;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionary;
import com.oil.model.ResponseInfo;
import com.oil.model.sales.CustomerMessage;
import com.oil.service.sales.CustomerMessageService;
import com.oil.util.MessageUtil;

@Controller
@RequestMapping("/CustomerMessage")
public class CustomerMessageController {

	@Autowired
	private CustomerMessageService customerMessageService;
	
	@RequestMapping("/index.action")
	public String clientHtml() {
		return "/index";

	}
	
	
	@RequestMapping("/getCustomerMessage.action")
	@ResponseBody
	public DataTablesResponseInfo getCustomerMessage(HttpServletRequest request,@RequestParam HashMap<String, Object> map){
		
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		
		List<CustomerMessage> cmList = customerMessageService.getCustomerMessage();
		
		for (int i = 0; i < cmList.size(); i++) {
			String operate = "<span><a href='javascript:void(0)' onclick='update(\""+ cmList.get(i).getUuid()+ "\");'>�޸�</a></span>"
					+ "<span><a href=\"javascript:void(0)\" onclick=\"del('" + cmList.get(i).getUuid()+ "');\">ɾ��</a></span>";
			cmList.get(i).setOperate(operate);
			cmList.get(i).setRownumber(i+1);
			Datadictionary datadictionary = customerMessageService.getDatadictionaryByCode(cmList.get(i).getProvince());
			cmList.get(i).setProvince(datadictionary.getContent());
		}
		
		dInfo.setData(cmList);
	
		return dInfo;
	}
	
	
	@RequestMapping("/getCustomerMessageTree.action")
	@ResponseBody
	public List<CustomerMessage>getCustomerMessageTree(){
		
		
		List<CustomerMessage> cmList = customerMessageService.getCustomerMessage();
	
		return cmList;
	}
	
	
	
	@RequestMapping("/addCustomerMessage.action")
	@ResponseBody
	public ResponseInfo addCustomerMessage(@RequestBody CustomerMessage customerMessage) {
		
		ResponseInfo info = new ResponseInfo();
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		customerMessage.setUuid(uuid);
		int result = customerMessageService.addCustomerMessage(customerMessage);
		
		if(result>0) {
			info.setMessage("��ӳɹ�");
			info.setCode("success");
		}else {
			info.setMessage("���ʧ��");
			info.setCode("error");
		}
		return info;
	}
	
	
	@RequestMapping("/getDatadictionary.action")
	@ResponseBody
	public List<Datadictionary> getDatadictionary() {
		
		List<Datadictionary> province = customerMessageService.getDatadictionary();
		return province;
	}
	
	@RequestMapping("/delCustomerMessage.action")
	public @ResponseBody ResponseInfo delCustomerMessage(HttpServletRequest request,@RequestParam String uuid) {
		ResponseInfo Info = new ResponseInfo();
			int result =  customerMessageService.delCustomerMessage(uuid);
			if(result>0) {
				Info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
				Info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
			}else {
				Info.setCode(MessageUtil.SERVER_ERROR);
				Info.setMessage(MessageUtil.OPERATION_FAILED);
			}
		return Info;
	}
	
	
	@RequestMapping("/findCustomerMessage.action")
	public @ResponseBody DataTablesResponseInfo findCustomerMessage(HttpServletRequest request,@RequestParam HashMap<String, Object> map) throws ParseException {
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		String Keyword = map.get("keyword").toString();
		List<CustomerMessage> findList = customerMessageService.findCustomerMessage(Keyword);
		for (int i = 0; i < findList.size(); i++) {
			String operate = "<span><a href='javascript:void(0)' onclick='update(\""+ findList.get(i).getUuid()+ "\");'>�޸�</a></span>"
					+ "<span><a href=\"javascript:void(0)\" onclick=\"del('" + findList.get(i).getUuid()+ "');\">ɾ��</a></span>";
			findList.get(i).setOperate(operate);
			findList.get(i).setRownumber(i+1);
			Datadictionary datadictionary = customerMessageService.getDatadictionaryByCode(findList.get(i).getProvince());
			findList.get(i).setProvince(datadictionary.getContent());
		}
		dtri.setData(findList);
		return dtri;
	}
	
}
