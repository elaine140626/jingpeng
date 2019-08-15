package com.oil.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.system.FleetInfoDao;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.ResponseInfo;
import com.oil.model.system.CarInfo;
import com.oil.model.system.FleetInfo;
import com.oil.service.system.FleetInfoService;

@Service
public class FleetInfoServiceImpl implements FleetInfoService {

	@Autowired
	private FleetInfoDao fleetInfoDao;
	
	
	@Override
	public DataTablesResponseInfo getFleetInfo() {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<FleetInfo> FleetInfoList = fleetInfoDao.getFleetInfo();
		if(FleetInfoList!=null&&FleetInfoList.size()!=0) {
			for (int i = 0; i < FleetInfoList.size(); i++) {
				String operate = "<span><a href='javascript:void(0)' class='updateBtn' onclick='update(\""+ FleetInfoList.get(i).getId()+ "\");'><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a></span>&nbsp;&nbsp;&nbsp;<span><a href=\"javascript:void(0)\" onclick=\"del('" + FleetInfoList.get(i).getId()+ "');\"><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a></span>";
				FleetInfoList.get(i).setOperation(operate);
				FleetInfoList.get(i).setRownumber(i+1);
			}
		}
		info.setData(FleetInfoList);
		return info;
	}


	@Override
	public List<CarInfo> getCarInfo(FleetInfo fleetInfo) {
		// TODO Auto-generated method stub
		return fleetInfoDao.getCarInfo(fleetInfo);
	}


	@Override
	public FleetInfo getFleetInfoById(int id) {
		// TODO Auto-generated method stub
		return fleetInfoDao.getFleetInfoById(id);
	}


	@Override
	public int addFleetInfo(FleetInfo fleetInfo) {
		// TODO Auto-generated method stub
		return fleetInfoDao.addFleetInfo(fleetInfo);
	}


	@Override
	public int addCarInfo(CarInfo carInfo) {
		// TODO Auto-generated method stub
		return fleetInfoDao.addCarInfo(carInfo);
	}

	@Override
	public int updateFleetInfo(FleetInfo fleetInfo) {
		// TODO Auto-generated method stub
		return fleetInfoDao.updateFleetInfo(fleetInfo);
	}


	@Override
	public int updateCarInfo(CarInfo carInfo) {
		// TODO Auto-generated method stub
		return fleetInfoDao.updateCarInfo(carInfo);
	}


	@Override
	public int delFleetInfo(FleetInfo fleetInfo) {
		// TODO Auto-generated method stub
		return fleetInfoDao.delFleetInfo(fleetInfo);
	}


	@Override
	public int delCarInfo(CarInfo carInfo) {
		// TODO Auto-generated method stub
		return fleetInfoDao.delCarInfo(carInfo);
	}


	@Override
	public DataTablesResponseInfo findFleetInfoByName(String fleetName) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<FleetInfo> FleetInfoList = fleetInfoDao.findFleetInfoByName(fleetName);
		if(FleetInfoList!=null&&FleetInfoList.size()!=0) {
			for (int i = 0; i < FleetInfoList.size(); i++) {
				String operate = "<span><a href='javascript:void(0)' class='updateBtn' onclick='update(\""+ FleetInfoList.get(i).getId()+ "\");'><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a></span>&nbsp;&nbsp;&nbsp;<span><a href=\"javascript:void(0)\" onclick=\"del('" + FleetInfoList.get(i).getId()+ "');\"><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a></span>";
				FleetInfoList.get(i).setOperation(operate);
				FleetInfoList.get(i).setRownumber(i+1);
			}
		}
		info.setData(FleetInfoList);
		return info;
	}


	@Override
	public ResponseInfo delCarInfoById(int id) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = fleetInfoDao.delCarInfoById(id);
		if(result>0) {
			info.setCode("200");
			info.setMessage("删除成功");
		}else {
			info.setCode("500");
			info.setMessage("删除失败");
		}
		return info;
	}


	@Override
	public List<Map<String, Object>> getAllCarName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return fleetInfoDao.getAllCarName(map);
	}


	@Override
	public CarInfo getCarInfoById(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return fleetInfoDao.getCarInfoById(map);
	}
	@Override
	public List<CarInfo> getCarInfoByCarNumber(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return fleetInfoDao.getCarInfoByCarNumber(map);
	}
	
	@Override
	public List<Map<String, Object>> getAllCarInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return fleetInfoDao.getAllCarInfo(map);
	}



}
