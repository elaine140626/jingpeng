package com.oil.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.login.LoginDao;
import com.oil.dao.system.WareHouseInfoDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionary;
import com.oil.model.ResponseInfo;
import com.oil.model.system.WareHouseInfo;
import com.oil.service.system.WareHouseInfoService;

@Service
public class WareHouseInfoServiceImpl implements WareHouseInfoService {

	@Autowired
	private WareHouseInfoDao wareHouseInfoDao;
	
	//添加仓库信息
	@Override
	public ResponseInfo addWareHouseInfo(WareHouseInfo wareHouseInfo) {
		ResponseInfo info = new ResponseInfo();
		wareHouseInfo.setIsDel(0);
		int result = 0;
		result = wareHouseInfoDao.addWareHouseInfo(wareHouseInfo);
		if(result>0) {
			info.setCode("200");
			info.setMessage("添加成功");
		}else {
			info.setCode("500");
			info.setMessage("添加失败");
		}
		return info;
	}

	//查询仓库信息数据
	@Override
	public DataTablesResponseInfo getWareHouseInfo() {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<WareHouseInfo> wareHouseInfoList = wareHouseInfoDao.getWareHouseInfo();
		if(wareHouseInfoList!=null&&wareHouseInfoList.size()!=0) {
			for (int i = 0; i < wareHouseInfoList.size(); i++) {
				Datadictionary datadictionary = wareHouseInfoDao.getTankTypeById(wareHouseInfoList.get(i).getType());
				if(datadictionary!=null) {
					wareHouseInfoList.get(i).setType(datadictionary.getContent());
				}
				String operate = "<span><a href='javascript:void(0)' class='updateBtn' onclick='update(\""+ wareHouseInfoList.get(i).getId()+ "\");'><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><a href=\"javascript:void(0)\" onclick=\"del('" + wareHouseInfoList.get(i).getId()+ "');\"><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a></span>";
				wareHouseInfoList.get(i).setOperation(operate);
				wareHouseInfoList.get(i).setRownumber(i+1);
			}
		}
		info.setData(wareHouseInfoList);
		return info;
	}

	//修改仓库信息
	@Override
	public ResponseInfo updateWareHouseInfo(WareHouseInfo wareHouseInfo) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = wareHouseInfoDao.updateWareHouseInfo(wareHouseInfo);
		if(result>0) {
			info.setCode("200");
			info.setMessage("修改成功");
		}else {
			info.setCode("500");
			info.setMessage("失败");
		}
		return info;
	}

	//查询全部仓库信息数据
	@Override
	public List<Datadictionary> getLevel() {
		return wareHouseInfoDao.getLevel();
	}

	//通过code查询全部仓库信息数据
	@Override
	public Datadictionary getLevelByid(String code) {
		return wareHouseInfoDao.getLevelByid(code);
	}

	//通过仓库信息id 查询仓库信息
	@Override
	public WareHouseInfo getWareHouseInfoByid(int id) {
		WareHouseInfo wareHouseInfo = wareHouseInfoDao.getWareHouseInfoByid(id);
		return wareHouseInfo;
	}

	//删除仓库信息
	@Override
	public ResponseInfo delWareHouseInfo(WareHouseInfo wareHouseInfo) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = wareHouseInfoDao.delWareHouseInfo(wareHouseInfo);
		if(result>0) {
			info.setCode("200");
			info.setMessage("删除成功");
		}else {
			info.setCode("500");
			info.setMessage("删除失败");
		}
		return info;
	}

	//根据仓库名称模糊查询 
	@Override
	public DataTablesResponseInfo findWareHouseInfoByName(String warehouseName) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<WareHouseInfo> wareHouseInfoList = wareHouseInfoDao.findWareHouseInfoByName(warehouseName);
		if(wareHouseInfoList!=null&&wareHouseInfoList.size()!=0) {
			for (int i = 0; i < wareHouseInfoList.size(); i++) {
				/*Datadictionary datadictionary = wareHouseInfoDao.getLevelByid(wareHouseInfoList.get(i).getWarehouseType());
				wareHouseInfoList.get(i).setWarehouseType(datadictionary.getContent());*/
				String operate = "<span><a href='javascript:void(0)' class='updateBtn' onclick='update(\""+ wareHouseInfoList.get(i).getId()+ "\");'><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a></span>&nbsp;&nbsp;&nbsp;<span><a href=\"javascript:void(0)\" onclick=\"del('" + wareHouseInfoList.get(i).getId()+ "');\"><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a></span>";
				wareHouseInfoList.get(i).setOperation(operate);
				wareHouseInfoList.get(i).setRownumber(i+1);
			}
		}
		info.setData(wareHouseInfoList);
		return info;
	}

	//添加大罐信息数据
	@Override
	public ResponseInfo addWareHouseInfoTank(WareHouseInfo wareHouseInfo) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = wareHouseInfoDao.addWareHouseInfoTank(wareHouseInfo);
		if(result>0) {
			info.setCode("200");
			info.setMessage("添加成功");
		}else {
			info.setCode("500");
			info.setMessage("添加失败");
		}
		return info;
	}

	//通过code查询全部仓库分类信息数据
	@Override
	public Datadictionary getTankTypeById(String code) {
		return wareHouseInfoDao.getTankTypeById(code);
	}

	//修改大罐信息数据
	@Override
	public ResponseInfo updateWareHouseInfoTank(WareHouseInfo wareHouseInfo) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = wareHouseInfoDao.updateWareHouseInfoTank(wareHouseInfo);
		if(result>0) {
			info.setCode("200");
			info.setMessage("修改成功");
		}else {
			info.setCode("500");
			info.setMessage("失败");
		}
		return info;
	}



}
