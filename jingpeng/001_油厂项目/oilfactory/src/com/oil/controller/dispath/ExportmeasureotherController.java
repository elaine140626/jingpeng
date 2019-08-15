package com.oil.controller.dispath;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.dispath.ExportmeasureotherEntity;
import com.oil.service.dispath.ExportmeasureotherService;
import com.oil.util.MessageUtil;

@Controller
@RequestMapping("/exportmeasureother")
public class ExportmeasureotherController {

	@Autowired
	ExportmeasureotherService exportmeasureotherService;
	
	// 查询其他出库单信息
	@RequestMapping("/getExportmeasureotherList.action")
	@ResponseBody
	public DataTablesResponseInfo getExportmeasureotherList(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		DataTablesResponseInfo dInfo = new DataTablesResponseInfo();
		List<ExportmeasureotherEntity> dataList = exportmeasureotherService.getExportmeasureotherList(map);
		dInfo.setData(dataList);
		return dInfo;
	};
	
	//获取页面检索list
	@RequestMapping("/getDataList.action")
	@ResponseBody
	public Map<String, Object> getDataList() {
		return exportmeasureotherService.getDataList();
	}
	
	//删除其他出库单信息
	@RequestMapping("/deleteExportmeasureother.action")
	@ResponseBody
	public ResponseInfo deleteExportmeasureother(@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int res = exportmeasureotherService.deleteExportmeasureother(map);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} else {
			// 操作失败
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
		
	//编辑其他出库单信息
	@RequestMapping("/updateExportmeasureother.action")
	@ResponseBody
	public ResponseInfo updateExportmeasureother(@RequestBody ExportmeasureotherEntity exportmeasureotherEntity) {
		ResponseInfo info = new ResponseInfo();
		int res = exportmeasureotherService.updateExportmeasureother(exportmeasureotherEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} else {
			// 操作失败
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
		
	//添加其他出库单信息
	@RequestMapping("/insertExportmeasureother.action")
	@ResponseBody
	public ResponseInfo insertExportmeasureother(@RequestBody ExportmeasureotherEntity exportmeasureotherEntity) {
		ResponseInfo info = new ResponseInfo();
		int res = exportmeasureotherService.insertExportmeasureother(exportmeasureotherEntity);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} else {
			// 操作失败
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}

	//删除司机电子签名
	@RequestMapping("/deleteElectronicsName.action")
	@ResponseBody
	public ResponseInfo deleteElectronicsName(@RequestParam Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
	    int res = exportmeasureotherService.deleteElectronicsName(map);
		if (res>0) {
			// 操作成功
			info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} else {
			// 操作失败
			info.setCode(MessageUtil.SERVER_ERROR);
			info.setMessage(MessageUtil.OPERATION_FAILED);
		}
		return info;
	}
}
