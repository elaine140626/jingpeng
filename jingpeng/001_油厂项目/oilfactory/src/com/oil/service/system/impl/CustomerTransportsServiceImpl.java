package com.oil.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.system.CustomerTransportsDao;
import com.oil.model.sales.Customerinfo;
import com.oil.model.system.Startaddress;
import com.oil.model.system.Transports;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionary;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.service.system.CustomerTransportsService;
@Service
public class CustomerTransportsServiceImpl implements CustomerTransportsService{
	@Autowired
	private CustomerTransportsDao customerTransportsDao;
	@Override
	public List<Map<String,Object>> getTransports_Tree() {
		return customerTransportsDao.getTransports_Tree();
	}
	@Override
	public ResponseInfo addTransports(Transports transports) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result;
		result = customerTransportsDao.addTransports(transports);
		if(result>0) {
			info.setCode("200");
			info.setMessage("添加成功");
		}else {
			info.setCode("500");
			info.setMessage("添加失败");
		}
		return info;
	}
	
	@Override
	public ResponseInfo addStartTransports(Startaddress startaddress) {
		ResponseInfo info = new ResponseInfo();
		int result;
		result = customerTransportsDao.addStartTransports(startaddress);
		if(result>0) {
			info.setCode("200");
			info.setMessage("添加成功");
		}else {
			info.setCode("500");
			info.setMessage("添加失败");
		}
		return info;
	}
	
	@Override
	public ResponseInfo updateTransports(Transports transports) {
		// TODO Auto-generated method stub
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = customerTransportsDao.updateTransports(transports);
		if(result>0) {
			info.setCode("200");
			info.setMessage("修改成功");
		}else {
			info.setCode("500");
			info.setMessage("修改失败");
		}
		return info;
	}
	
	@Override
	public ResponseInfo updateStartTransports(Startaddress startaddress) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = customerTransportsDao.updateStartTransports(startaddress);
		if(result>0) {
			info.setCode("200");
			info.setMessage("修改成功");
		}else {
			info.setCode("500");
			info.setMessage("修改失败");
		}
		return info;
	}
	
	@Override
	public DataTablesResponseInfo getTransports(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Transports> transportsList = customerTransportsDao.getTransports(map);
		
		for (int i = 0; i < transportsList.size(); i++) {
			transportsList.get(i).setSerialnumber(i+1);
		}
		info.setData(transportsList);
		return info;
	}
	
	@Override
	public DataTablesResponseInfo getStartTransports(Map<String,Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Startaddress> startaddressList = customerTransportsDao.getStartTransports(map);
		
		for (int i = 0; i < startaddressList.size(); i++) {
			startaddressList.get(i).setSerialnumber(i+1);
		}
		info.setData(startaddressList);
		return info;
	}
	
	@Override
	public ResponseInfo delTransports(int id) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = customerTransportsDao.delTransports(id);
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
	public ResponseInfo delStartTransports(int id) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = customerTransportsDao.delStartTransports(id);
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
	public DataTablesResponseInfo findCustomerinfoByName(String customerName) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Transports> transportsList = customerTransportsDao.findCustomerinfoByName(customerName);
		for (int i = 0; i < transportsList.size(); i++) {
			transportsList.get(i).setSerialnumber(i+1);
		}
		info.setData(transportsList);
		return info;
	}
	
	@Override
	public DataTablesResponseInfo findStartCustomerinfoByName(String customerName) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Startaddress> startaddressList = customerTransportsDao.findStartCustomerinfoByName(customerName);
		for (int i = 0; i < startaddressList.size(); i++) {
			startaddressList.get(i).setSerialnumber(i+1);
		}
		info.setData(startaddressList);
		return info;
	}
	
	@Override
	public DataTablesResponseInfo findByNameAndId(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Transports> transportsList = customerTransportsDao.findByNameAndId(map);
		for (int i = 0; i < transportsList.size(); i++) {
			transportsList.get(i).setSerialnumber(i+1);
		}
		info.setData(transportsList);
		return info;
	}
	@Override
	public DataTablesResponseInfo getTransportsById(int id) {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		Transports transports = customerTransportsDao.getTransportsById(id);
		info.setData(transports);
		return info;
	}
	
	@Override
	public DataTablesResponseInfo getStartTransportsById(int id) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		Startaddress startaddress = customerTransportsDao.getStartTransportsById(id);
		info.setData(startaddress);
		return info;
	}
	
	@Override
	public DataTablesResponseInfo getCustomerInfo() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Customerinfo> customerinfoList = customerTransportsDao.getCustomerInfo();
		info.setData(customerinfoList);
		return info;
	}
	@Override
	public List<Map<String,Object>> getTrchangeInfo(int id) {
		return customerTransportsDao.getTrchangeInfo(id);
	}
	
	@Override
	public DataTablesResponseInfo getVisitContent() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Datadictionary> dataList = customerTransportsDao.getVisitContent();
		info.setData(dataList);
		return info;
	}

	@Override
	public DataTablesResponseInfo getUserInfoByCode() {
		// TODO Auto-generated method stub
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Userinfo> customerinfoList = customerTransportsDao.getUserInfoByCode();
		info.setData(customerinfoList);
		return info;
	}

	@Override
	public List<Map<String,Object>> getClient_back_List() {
		return customerTransportsDao.getClient_back_List();
	}
	
	@Override
	public List<Map<String, Object>> getAllTransports(Map<String, Object> map) {
		return customerTransportsDao.getAllTransports(map);
	}

}
