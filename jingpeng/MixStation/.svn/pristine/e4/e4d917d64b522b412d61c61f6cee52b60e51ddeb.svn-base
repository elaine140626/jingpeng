package com.jingpeng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.Equipment_Info;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.service.EquipmentService;
import com.jingpeng.util.MessageUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

@Controller
@RequestMapping("/Equipment")
public class EquipmentController extends KDController<Object> {

	@Autowired
	private EquipmentService equipmentService;

	
	/**跳版和设备页面
	 * @return
	 */
	@RequestMapping("/getEquipments.html")
	public String  getEquipment() {
	
		return "/sn/Equipment_Management";
	}
	
	
	/**
	 * 查询设备信息
	 * 
	 * @param equipment_Info
	 * @return
	 */
	@RequestMapping("/getEquipments")
	@ResponseBody
	public DataTablesResponseInfo getEquipments(Equipment_Info equipment_Info) {
		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			
			List<Equipment_Info> list = equipmentService.getEquipmentInfos(equipment_Info);
			for(int i = 0; i < list.size(); i++) {
				String operate = "<span class='globalLoginBtn'><a href='javascript:void(0)' onclick='update(\""+list.get(i).getI_id()+"\");'><img src='../resources/images/xiu.png' width='17' height='16' alt='修改' title='修改'></a></span>" + 
						"<span><a href=\"javascript:void(0)\" onclick=\"del('"+list.get(i).getI_id()+"');\"><img src=\"../resources/images/del.png\" width=\"18\" height=\"16\" alt=\"删除\" title=\"删除\"></a></span>";
				list.get(i).setOperate(operate);
				list.get(i).setSerialNumber(i+1);
			}
			dtri.setData(list);
			return dtri	;
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 通过id查询设备信息
	 * 
	 * @param equipment_Info
	 * @return
	 */
	@RequestMapping("/getEquipmentById")
	@ResponseBody
	public Equipment_Info getEquipmentById(@RequestBody Equipment_Info equipment_Info) {

		try {
			return equipment_Info = equipmentService.getEquipmentInfoById(equipment_Info);
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/getEquipmentbyNo")
	public @ResponseBody ResponseInfo getEquipmentbyNo(@RequestBody Equipment_Info equipment_Info) {
	
		ResponseInfo info = new ResponseInfo();
		try {
			List<Equipment_Info> list = equipmentService.getEquipmentbyNo(equipment_Info);
			
			System.out.println(list);
			if (list != null && !list.isEmpty()) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//设备编码已存在
				info.setMessage(MessageUtil.CODING_EXISTENCE);
				return info;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
		
	}
	
	@RequestMapping("/getEquipmentbyCode")
	public @ResponseBody ResponseInfo getEquipmentbyCode(@RequestBody Equipment_Info equipment_Info) {
	
		ResponseInfo info = new ResponseInfo();
		try {
			List<Equipment_Info> list = equipmentService.getEquipmentbyCode(equipment_Info);
			if (list != null && !list.isEmpty()) {
				//多种选择 300
				info.setCode(MessageUtil.MULTIPLE_CHOICES);
				//拌和机标识已存在 
				info.setMessage(MessageUtil.IDENTITY_EXISTENCE);
				return info;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
		
	}

	/**
	 * 添加设备信息
	 * 
	 * @param equipment_Info
	 * @return
	 */
	@RequestMapping("/addEquipment.html")
	@Transactional
	public void addEquipment(@RequestBody Equipment_Info equipment_Info) {
			try {
				equipmentService.addEquipmentInfo(equipment_Info);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	/**
	 * 更新设备信息
	 * 
	 * @param equipment_Info
	 * @return
	 */
	@RequestMapping("/updateEquipment.html")
	@Transactional
	@ResponseBody
	public ResponseInfo updateEquipment(@RequestBody Equipment_Info equipment_Info) {
			
		ResponseInfo info = new ResponseInfo();
		try {
			equipmentService.updateEquipmentInfo(equipment_Info);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (Exception e) {
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}

		return info;
	}

	/**
	 * 删除设备信息
	 * 
	 * @param equipment_Info
	 * @return
	 */
	@RequestMapping("/deleteEquipment")
	@Transactional
	@ResponseBody
	public ResponseInfo deleteEquipment(Equipment_Info equipment_Info) {
		ResponseInfo info = new ResponseInfo();
		try {
			equipmentService.deletEquipmentInfo(equipment_Info);
			//成功处理请求提示 200
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//操作成功提示
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (Exception e) {
			e.printStackTrace();
			//服务器遇到错误 500
			info.setCode(MessageUtil.SERVER_ERROR);
			//操作失败提示
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}

}
