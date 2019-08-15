package com.oil.service.map.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.oil.dao.map.MapDao;
import com.oil.model.map.MapCar;
import com.oil.service.map.MapService;



@Service
@Transactional
public class MapServiceImpl implements MapService{

	@Autowired
	private MapDao mapDao;
	
	@Override
	public List<List<MapCar>> getCarTrajectory(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<MapCar> mapCarListXY = mapDao.getCarTrajectoryXY(param);
		List<MapCar> mapCarListId = mapDao.getCarTrajectoryId(param);
		List<List<MapCar>> MapCList = new ArrayList<>();
		for (int i = 0; i < mapCarListId.size(); i++) {
			List<MapCar> CarXY = new ArrayList<MapCar>();
			for (int j = 0; j < mapCarListXY.size(); j++) {
				if(mapCarListId.get(i).getId() == mapCarListXY.get(j).getId()) {
					CarXY.add(mapCarListXY.get(j));
				}
			}
			MapCList.add(CarXY);
		}
		
/*		List<MapCar> mapCarListX = mapDao.getCarTrajectoryX(param);
		List<MapCar> mapCarListY = mapDao.getCarTrajectoryY(param);
		List<List<MapCar>> MapCList = new ArrayList<>();
		for (int i = 0; i < mapCarListX.size(); i++) {
			List<MapCar> CarXY = new ArrayList<MapCar>();
			String carx = mapCarListX.get(i).getCoordinateXArray();
			String cary = mapCarListY.get(i).getCoordinateYArray();
			String[] mapx = {};
			String[] mapy = {};
			if(carx!=null && !("").equals(carx)) {
				mapx = carx.split(",");
			}
			if(cary!=null && !("").equals(cary)) {
				mapy = cary.split(",");
			}
			for (int j = 0; j < mapy.length; j++) {
				MapCar mcar = new MapCar();
				if(j == 0) {
					mcar.setEndAddressX(mapCarListX.get(i).getEndAddressX());
					mcar.setEndAddressY(mapCarListX.get(i).getEndAddressY());
				}
				mcar.setCoordinateX(Double.valueOf(mapx[j]));
				mcar.setCoordinateY(Double.valueOf(mapy[j]));
				mcar.setPlateNumber(mapCarListX.get(i).getPlateNumber());
				mcar.setGpsNumber(mapCarListX.get(i).getGpsNumber());
				mcar.setReturnState(mapCarListX.get(i).getReturnState());
				mcar.setFleetName(mapCarListX.get(i).getFleetName());
				mcar.setFid(mapCarListX.get(i).getFid());
				mcar.setCarId(mapCarListX.get(i).getCarId());
				CarXY.add(mcar);
			}
			MapCList.add(CarXY);
		}*/
		
		return MapCList;
		
	}

	@Override
	public List<MapCar> getCarName() {
		// TODO Auto-generated method stub
		return mapDao.getCarName();
	}

	@Override
	public List<Map<String, Object>> getFleetCars(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapDao.getFleetCars(param);
	}

	@Override
	public int addCarsupervise(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapDao.addCarsupervise(param);
	}
	
	@Override
	public List<Map<String, Object>> getCustomertransports(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapDao.getCustomertransports(param);
	}

	@Override
	public List<Map<String, Object>> getCarPlateNumber() {
		// TODO Auto-generated method stub
		return mapDao.getCarPlateNumber();
	}

	@Override
	public int addPdacarinfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapDao.addPdacarinfo(param);
	}

	@Override
	public int addPdaCarInfoHistory(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapDao.addPdaCarInfoHistory(param);
	}

	@Override
	public List<Map<String, Object>> getPdaCarInfoHistory(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapDao.getPdaCarInfoHistory(param);
	}

	@Override
	public int updatepdacarinfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		//根据设备编号查询信息
		List<Map<String,Object>> list = this.getPdacarinfo(param);
		int result = 0;
		if(list.size() > 0) {
			//是否绑定GPS 0:是 1:否
			list.get(0).put("isBinding", 1);
			result = mapDao.updateCarinfoIsBinding(list.get(0));		//移除之前车牌号的绑定标识
			if(result<=0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;
			}
			//清除之前车辆绑定GPS的轨迹
			result = mapDao.delCarsupervise(param);
/*			if(result<=0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;
			}*/
			result = mapDao.updatepdacarinfo(param);					//修改设备绑定车牌
			if(result<=0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;
			}
			param.put("isBinding", 0);
			result = mapDao.updateCarinfoIsBinding(param);	//添加车牌号的绑定标识
			if(result<=0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;
			}
			return result;	//添加车牌号的绑定标识
		}else {
			param.put("isBinding", 0);
			result = mapDao.updateCarinfoIsBinding(param);
			if(result<=0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;
			}
			result = this.addPdacarinfo(param);
			if(result<=0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return result;
			}
			return result;
		}
	}

	@Override
	public int updateCarinfoIsBinding(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapDao.updateCarinfoIsBinding(param);
	}

	@Override
	public int delPdacarinfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapDao.delPdacarinfo(param);
	}

	@Override
	public List<Map<String, Object>> getPdacarinfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapDao.getPdacarinfo(param);
	}

	@Override
	public List<Map<String, Object>> GPSlogin(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapDao.GPSlogin(param);
	}


}
