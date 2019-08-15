package com.oil.service.transportation.impl;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.oil.dao.transportation.TranSportListStoragemeasureDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.service.transportation.TranSportListService;
import com.oil.service.transportation.TranSportListStoragemeasureService;
import com.oil.util.DateConvert;

@Service
@Transactional
public class TranSportListStoragemeasureServiceImpl implements TranSportListStoragemeasureService {
	
	@Autowired
	private TranSportListStoragemeasureDao tranSportListStoragemeasureDao;

	@Override
	public ResponseInfo addTranSportList(Map<String, Object> map) {
		// TODO Auto-generated method stub
/*		map.put("dispatchOutWarehouseId", Integer.parseInt(map.get("dispatchOutWarehouseId").toString()));
		map.put("materielId", Integer.parseInt(map.get("materielId").toString()));
		map.put("model", Integer.parseInt(map.get("model").toString()));
		map.put("weigh", Double.valueOf(map.get("weigh").toString()));
		map.put("collectWeigh", Double.valueOf(map.get("collectWeigh").toString()));
		map.put("settlementKilometre", Double.valueOf(map.get("settlementKilometre").toString()));
		map.put("freightPrice", Double.valueOf(map.get("freightPrice").toString()));
		map.put("freightMoney", Double.valueOf(map.get("freightMoney").toString()));
		map.put("escortMoney", Double.valueOf(map.get("escortMoney").toString()));
		map.put("escortDays", Integer.parseInt(map.get("escortDays").toString()));*/
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		if(map.get("id")==""||map.get("id")==null) {
			result = tranSportListStoragemeasureDao.addTranSportList(map);
			if(result > 0) {
				/*if(Integer.parseInt(map.get("type").toString())==2) {
					info =  this.updateStoragemeasureIsTransport(map);
				}else {
					info = this.updateExportmeasureIsTransport(map);
				}*/
/*				String id = map.get("Id").toString();
				System.out.println(id);*/
				info.setMessage("操作成功");
				info.setCode("success");
			}else {
				info.setMessage("操作失败");
				info.setCode("error");
			}
		}else {
			map.put("id", Integer.parseInt(map.get("id").toString()));
			info = this.updateTranSportList(map);
		}
		return info;
	}

	@Override
	public DataTablesResponseInfo getExOrStPlateNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> ExOrStList  = tranSportListStoragemeasureDao.getExOrStPlateNumber(map);
/*		Map<String,Object> SerialIdMap = new HashMap<>();
		for (int i = 0; i < ExOrStList.size(); i++) {
			List<Map<String,Object>> exIsHaveNoweighoutwarehouse = new ArrayList<>();
			SerialIdMap.put("SerialId", ExOrStList.get(i).get("SerialId"));
			exIsHaveNoweighoutwarehouse = tranSportListStoragemeasureDao.exIsHaveNoweighoutwarehouse(SerialIdMap);
			if(exIsHaveNoweighoutwarehouse.size()>0 && Integer.parseInt(ExOrStList.get(i).get("outType").toString()) == 0) {
				ExOrStList.get(i).put("outType", 99);
			}
		}*/
		info.setData(ExOrStList);
		return info;
	}

	@Override
	public DataTablesResponseInfo getSaleType() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(tranSportListStoragemeasureDao.getSaleType());
		return info;
	}

	@Override
	public DataTablesResponseInfo getMaterielinfo() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(tranSportListStoragemeasureDao.getMaterielinfo());
		return info;
	}

	@Override
	public DataTablesResponseInfo getFleetInfo() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(tranSportListStoragemeasureDao.getFleetinfo());
		return info;
	}

	@Override
	public DataTablesResponseInfo getTranSportList(Map<String,Object> map) throws ParseException {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> list =  tranSportListStoragemeasureDao.getTranSportList(map);
		Map<String,Object> SerialIdMap = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			if(!list.get(i).get("type").equals("2")) {
				if(list.get(i).get("factoryTime")!=null) {
					list.get(i).put("goOutTime", DateConvert.changeDate(list.get(i).get("factoryTime").toString()));
				}else {
					list.get(i).put("goOutTime", "");
				}
			}else {
				if(list.get(i).get("enterDate")!=null) {
					list.get(i).put("goOutTime", DateConvert.changeDate(list.get(i).get("enterDate").toString()));
				}else {
					list.get(i).put("goOutTime", "");
				}
			}
			//正常出库是否含有未称重判断	
/*			List<Map<String,Object>> exIsHaveNoweighoutwarehouse = new ArrayList<>();
			SerialIdMap.put("SerialId", list.get(i).get("serialID"));
			exIsHaveNoweighoutwarehouse = tranSportListStoragemeasureDao.exIsHaveNoweighoutwarehouse(SerialIdMap);
			if(exIsHaveNoweighoutwarehouse.size()>0 && Integer.parseInt(list.get(i).get("outType").toString()) == 0) {
				list.get(i).put("outType", 99);
			}*/
			
		}
		
		if(list != null) {	
			String cementType = "";
			int count = 0;
			//外层循环 有多少分页 循环几次
			for(int k = 0;k<list.size()/7+1;k++) {
				int end = 7*(k+1);
				if (end>list.size()) {
					end = list.size();
				}
				String inCementType = "";
				//内层循环，按照每个页的个数循环
				for(int i = 7*k; i < end; i++) {				
					if(inCementType.equals(list.get(i).get("billNumber"))) {
						list.get(i).put("merge", 0);
					} else {	
						if (!cementType.equals(list.get(i).get("billNumber"))) {
							count++;
						}									
						int merge = 0;
						for(int j= 7*k; j < end; j++) {
							if (list.get(i).get("billNumber").equals(list.get(j).get("billNumber"))) {
								merge++;
							}
						}
						list.get(i).put("merge", merge);
					}
					list.get(i).put("rowCount", count);
					
					inCementType = (String) list.get(i).get("billNumber");
					String operate = "<a  onclick=update('"+list.get(i).get("billNumber")+"')><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a><a onclick=del('"+list.get(i).get("billNumber")+"')><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a> ";
							//+ "<a style=\"margin: 0 15px;\" onclick=\"copy(\'"+list.get(i).get("billNumber")+"\')\"><img src=\"../../img/common/copy.png\" width=\"20\" height=\"20\" alt=\"复制\" title=\"复制\"></a>";
					list.get(i).put("operate", operate);
				}
				if(!(end-1<0)) {
					cementType = (String) list.get(end-1).get("billNumber");
				}
			}
			info.setData(list);
		}
		
		
		return info;
	}

	@Override
	public ResponseInfo updateTranSportList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = tranSportListStoragemeasureDao.updateTranSportList(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}

	@Override
	public ResponseInfo delTranSportList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		String BillNumberS = map.get("BillNumber").toString();
		map.put("JbillNumber", BillNumberS);
		map.put("BillNumber", "");
		List<Map<String,Object>> tranSportListByBillNumber =  tranSportListStoragemeasureDao.getTranSportList(map);
		map.put("BillNumber", BillNumberS);
		int result = tranSportListStoragemeasureDao.delTranSportList(map);
/*			if(result > 0) {
					for (int i = 0; i < tranSportListByBillNumber.size(); i++) {
						//tranSportListByBillNumber.get(i).put("Id", null);
						if(Integer.parseInt(tranSportListByBillNumber.get(i).get("type").toString())==2) {
							info =  this.updateStoragemeasureIsTransport(tranSportListByBillNumber.get(i));
							if(info.getCode()=="error") {
								return info;
							}
						}else {
							info = this.updateExportmeasureIsTransport(tranSportListByBillNumber.get(i));
							if(info.getCode()=="error") {
								return info;
							}
						}
					}
				}else {
			}*/
			info.setMessage("操作失败");
			info.setCode("error");
		return info;
	}

	@Override
	public DataTablesResponseInfo getIsExAndSt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		Map<String, Object> IsExAndSt = tranSportListStoragemeasureDao.getIsExAndSt(map);
		info.setData(IsExAndSt);
		return info;
	}

	@Override
	public DataTablesResponseInfo getPlate() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> plate = tranSportListStoragemeasureDao.getPlate();
		info.setData(plate);
		return info;
	}

	@Override
	public DataTablesResponseInfo getTransportsById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		if(map.get("kid")!=null&&map.get("kid")!="") {
			map.put("kid", Integer.parseInt(map.get("kid").toString()));
		}
		List<Map<String,Object>> transportsList = tranSportListStoragemeasureDao.getTransportsById(map);
		info.setData(transportsList);
		return info;
	}

	@Override
	public ResponseInfo updateNoWeighOut(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		map.put("dispatchOutWarehouseId", Integer.parseInt(map.get("dispatchOutWarehouseId").toString()));
/*		map.put("settlementWeight", Double.valueOf(map.get("settlementWeight").toString()));
		map.put("distance", Double.valueOf(map.get("distance").toString()));
		map.put("freightPrice", Double.valueOf(map.get("freightPrice").toString()));
		map.put("freightMoney", Double.valueOf(map.get("freightMoney").toString()));
		map.put("escortMoney", Double.valueOf(map.get("escortMoney").toString()));
		map.put("escortDays", Integer.parseInt(map.get("escortDays").toString()));*/
		ResponseInfo info = new ResponseInfo();
		int result = tranSportListStoragemeasureDao.updateNoWeighOut(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
					//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			}
		return info;
	}

	@Override
	public ResponseInfo adduploadfile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = tranSportListStoragemeasureDao.adduploadfile(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}

	@Override
	public DataTablesResponseInfo getUploadfile(Map<String,Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		List<Map<String,Object>> fileList = tranSportListStoragemeasureDao.getUploadfile(map);
		List<Map<String,Object>> list_remove = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < fileList.size(); i++) {
			String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+fileList.get(i).get("fileName");
			File file=new File(path);
			 if  (!file .exists()  && !file .isDirectory())     
			 {      
			     //不存在
				 list_remove.add(fileList.get(i)); //把要移除的统一放在一个集合	
			 }
		}
		if(list_remove.size()>0) {
			fileList.removeAll(list_remove);
			for (int i = 0; i < list_remove.size(); i++) {
				this.delUploadfile(list_remove.get(i));
			}
		}
		info.setData(fileList);
		return info;
	}

	@Override
	public ResponseInfo delUploadfile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = tranSportListStoragemeasureDao.delUploadfile(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}

	@Override
	public ResponseInfo updateExportmeasureIsTransport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = tranSportListStoragemeasureDao.updateExportmeasureIsTransport(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}

	@Override
	public ResponseInfo updateStoragemeasureIsTransport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = tranSportListStoragemeasureDao.updateStoragemeasureIsTransport(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}

	@Override
	public ResponseInfo isHaveUpload(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+map.get("fileName");
			File file=new File(path);
			 if  (!file .exists()  && !file .isDirectory())     
			 {      
			     //不存在
				 info.setMessage("操作失败,文件已被删除");
				 info.setCode("error");
			 }else{
			 		info.setMessage("操作成功");
					info.setCode("success");
			 }
			 
		return info;
	}

	@Override
	public DataTablesResponseInfo getEXById(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> EXlist = tranSportListStoragemeasureDao.getEXById(map);
		info.setData(EXlist);
		return info;
	}

}
