package com.oil.service.transportation.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.oil.dao.dispath.RoadgateDao;
import com.oil.dao.transportation.TranSportListDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.Roadgate;
import com.oil.service.transportation.TranSportListService;
import com.oil.util.DateConvert;

@Service
@Transactional
public class TranSportListServiceImpl implements TranSportListService {
	
	@Autowired
	private TranSportListDao tranSportListDao;
	@Autowired
	RoadgateDao roadgateDao;
	

	@Override
	public DataTablesResponseInfo getTranSportList(Map<String,Object> map) throws ParseException {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> list =  tranSportListDao.getTranSportList(map);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("rowCount", i+1);
/*			if(!list.get(i).get("type").equals("2")) {
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
			}*/
			if(list.get(i).get("type")!=null && Integer.parseInt(list.get(i).get("type").toString()) == 1) {
				list.get(i).put("factoryTime", DateConvert.changeDate(list.get(i).get("nowCreaterDate").toString()));
			}
			String operate = "";
			if(Integer.parseInt(list.get(i).get("type").toString()) == 99) {
				operate = "<a  onclick=select('"+list.get(i).get("id")+"')><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
			}else {
				operate = "<a  onclick=update('"+list.get(i).get("id")+"')><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
			}
			list.get(i).put("operate", operate);
			info.setData(list);
			
		}
		
		return info;
	}
	

	@Override
	public DataTablesResponseInfo getExOrStPlateNumber(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> ExOrStList  = tranSportListDao.getExOrStPlateNumber(map);
		for (int i = 0; i < ExOrStList.size(); i++) {
			ExOrStList.get(i).put("rowCount", i+1);
			String operate = "";
			if(Integer.parseInt(ExOrStList.get(i).get("type").toString()) == 99) {
				operate = "<a  onclick=select('"+ExOrStList.get(i).get("ttId")+"')><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
			}else {
				operate = "<a  onclick=update('"+ExOrStList.get(i).get("ttId")+"')><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
			}
			ExOrStList.get(i).put("operate", operate);
		}
		info.setData(ExOrStList);
		return info;
	}
	
	@Override
	public ResponseInfo updateTranSportList(Map<String,Object> map, HttpServletRequest request) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		Map<String,Object> mapId = new HashMap<>();
		String param = map.get("param").toString();
		Map<String,Object> paramMap= JSON.parseObject(param);
		mapId.put("id", map.get("id"));
		List<Map<String,Object>> TranSportListById = tranSportListDao.getTranSportList(mapId);
		if( map.get("fileName") != null && !map.get("fileName").equals("")) {
			if(TranSportListById.get(0).get("fileName") != null && !TranSportListById.get(0).get("fileName").equals("")) {
				paramMap.put("fileName", map.get("fileName").toString());
				paramMap.put("fileRoute", map.get("fileRoute").toString());
				info = this.delUploadfile(paramMap,request);
				if(info.getCode().equals("success")) {
					info = this.adduploadfile(paramMap);
				}else {
					return info;
				}
			}else {
				paramMap.put("fileName", map.get("fileName").toString());
				paramMap.put("fileRoute", map.get("fileRoute").toString());
				info = this.adduploadfile(paramMap);
			}
		}else {
			info.setCode("success");
		}
		if(info.getCode().equals("success")) {
			int result = tranSportListDao.updateTranSportList(paramMap);
			if(result > 0) {
					/*if(paramMap.get("collectWeigh") != null && !paramMap.get("collectWeigh").equals("")) {
						Roadgate roadgate = new Roadgate();
						roadgate.setCarNumber(paramMap.get("plateNumber").toString());
						result = roadgateDao.deleteRoadgate(roadgate);
						if(result > 0) {
							info.setMessage("操作成功");
							info.setCode("success");
							return info;
						}else {
							info.setMessage("道闸关联删除操作失败");
							info.setCode("error");
							return info;
						}
					}*/
				info.setMessage("操作成功");
				info.setCode("success");
			}else {
				info.setMessage("运输单修改操作失败");
				info.setCode("error");
			}
		}
		return info;
	}


	@Override
	public ResponseInfo delUploadfile(Map<String, Object> map, HttpServletRequest request) {
			ResponseInfo info = new ResponseInfo();
				int result = tranSportListDao.delUploadfile(map);
				if(result > 0) {
					if(isHaveUpload(map).getCode().equals("success")) {
						String path = request.getSession().getServletContext().getRealPath("/upload")+"/"+map.get("fileName");  
						FileUtils.deleteQuietly(new File(path));
					}
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

/*	@Override
	public ResponseInfo addTranSportList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		if(map.get("id")==""||map.get("id")==null) {
			result = tranSportListDao.addTranSportList(map);
			if(result > 0) {
				Roadgate roadgate = new Roadgate();
				roadgate.setOutWarehouseId(Integer.parseInt(map.get("dispatchOutWarehouseId").toString()));
				roadgateDao.deleteRoadgate(roadgate);
				info.setMessage("操作成功");
				info.setCode("success");
			}else {
				info.setMessage("操作失败");
				info.setCode("error");
			}
		}else {
			map.put("id", Integer.parseInt(map.get("id").toString()));
			info = this.updateTranSportList(map,request);
		}
		return info;
	}*/



/*	@Override
	public DataTablesResponseInfo getSaleType() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(tranSportListDao.getSaleType());
		return info;
	}
*/
/*	@Override
	public DataTablesResponseInfo getMaterielinfo() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		info.setData(tranSportListDao.getMaterielinfo());
		return info;
	}*/

	@Override
	public ResponseInfo delTranSportList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		map.put("BillNumber", map.get("BillNumber").toString());
		int result = tranSportListDao.delTranSportList(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}

/*	@Override
	public DataTablesResponseInfo getIsExAndSt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		Map<String, Object> IsExAndSt = tranSportListDao.getIsExAndSt(map);
		info.setData(IsExAndSt);
		return info;
	}*/

/*	@Override
	public DataTablesResponseInfo getPlate() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> plate = tranSportListDao.getPlate();
		info.setData(plate);
		return info;
	}*/

/*	@Override
	public DataTablesResponseInfo getTransportsById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		if(map.get("kid")!=null&&map.get("kid")!="") {
			map.put("kid", Integer.parseInt(map.get("kid").toString()));
		}
		List<Map<String,Object>> transportsList = tranSportListDao.getTransportsById(map);
		info.setData(transportsList);
		return info;
	}*/

/*	@Override
	public ResponseInfo updateNoWeighOut(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		map.put("dispatchOutWarehouseId", Integer.parseInt(map.get("dispatchOutWarehouseId").toString()));
		map.put("settlementWeight", Double.valueOf(map.get("settlementWeight").toString()));
		map.put("distance", Double.valueOf(map.get("distance").toString()));
		map.put("freightPrice", Double.valueOf(map.get("freightPrice").toString()));
		map.put("freightMoney", Double.valueOf(map.get("freightMoney").toString()));
		map.put("escortMoney", Double.valueOf(map.get("escortMoney").toString()));
		map.put("escortDays", Integer.parseInt(map.get("escortDays").toString()));
		ResponseInfo info = new ResponseInfo();
		int result = tranSportListDao.updateNoWeighOut(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
					//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			}
		return info;
	}*/

	@Override
	public ResponseInfo adduploadfile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = tranSportListDao.adduploadfile(map);
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
	public int updateTranSportListApp(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tranSportListDao.updateTranSportListApp(map);
	}


	@Override
	public List<Map<String, Object>> getTranSportListApp(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> tranSportList = tranSportListDao.getTranSportListApp(map);
		for (int i = 0; i < tranSportList.size(); i++) {
			if(tranSportList.get(i).get("tareWeight") != null && tranSportList.get(i).get("weigh") != null) {
				//tranSportList.get(i).put("grossWeight", Double.parseDouble(tranSportList.get(i).get("tareWeight").toString())+Double.parseDouble(tranSportList.get(i).get("weigh").toString()));
				
				BigDecimal bd3 = new BigDecimal(tranSportList.get(i).get("tareWeight").toString());
				BigDecimal bd4 = new BigDecimal(tranSportList.get(i).get("weigh").toString());
				tranSportList.get(i).put("grossWeight",bd3.add(bd4));
			}
			if(tranSportList.get(i).get("type") != null && Integer.parseInt(tranSportList.get(i).get("type") .toString()) == 4) {
				tranSportList.get(i).put("ttype","兑换出库单");
			}
		}
		return tranSportList;
	}


	@Override
	public List<Map<String, Object>> getOutTranSportListApp(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tranSportListDao.getOutTranSportListApp(map);
	}

/*	@Override
	public DataTablesResponseInfo getUploadfile(Map<String,Object> map, HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> fileList = tranSportListDao.getUploadfile(map);
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
				this.delUploadfile(list_remove.get(i),request);
			}
		}
		info.setData(fileList);
		return info;
	}*/

/*	@Override
	public ResponseInfo updateExportmeasureIsTransport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = tranSportListDao.updateExportmeasureIsTransport(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}*/

/*	@Override
	public ResponseInfo updateStoragemeasureIsTransport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = tranSportListDao.updateStoragemeasureIsTransport(map);
			if(result > 0) {
					info.setMessage("操作成功");
					info.setCode("success");
				}else {
					info.setMessage("操作失败");
					info.setCode("error");
			}
		return info;
	}*/


/*	@Override
	public DataTablesResponseInfo getEXById(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Map<String,Object>> EXlist = tranSportListDao.getEXById(map);
		info.setData(EXlist);
		return info;
	}*/

}
