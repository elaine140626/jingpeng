package com.oil.service.client.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.client.Client_backDao;
import com.oil.model.client.Client_back;
import com.oil.model.sales.Customerinfo;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionary;
import com.oil.model.ResponseInfo;
import com.oil.model.Userinfo;
import com.oil.service.client.Client_backService;
import com.oil.util.DateConvert;
@Service
public class Client_backServiceImpl implements Client_backService{
	@Autowired
	private Client_backDao client_backDao;
	//获取回访记录客户ztree树
	@Override
	public List<Map<String,Object>> getClient_back_Tree() {
		return client_backDao.getClient_back_Tree();
	}
	//新增回访记录
	@Override
	public ResponseInfo addClient_back(Client_back client_back) {
		ResponseInfo info = new ResponseInfo();
		int result;
		result = client_backDao.addClient_back(client_back);
		if(result>0) {
			info.setCode("200");
			info.setMessage("添加成功");
		}else {
			info.setCode("500");
			info.setMessage("添加失败");
		}
		return info;
	}
	//客户评价下拉列表内容查询
	@Override
	public DataTablesResponseInfo getVisitContent() {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Datadictionary> dataList = client_backDao.getVisitContent();
		info.setData(dataList);
		return info;
	}
	//客户名称下拉列表内容查询
	@Override
	public DataTablesResponseInfo getCustomerInfo() {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Customerinfo> customerinfoList = client_backDao.getCustomerInfo();
		info.setData(customerinfoList);
		return info;
	}
	//查询用户信息ztree
	@Override
	public DataTablesResponseInfo getUserInfoByCode() {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Userinfo> customerinfoList = client_backDao.getUserInfoByCode();
		info.setData(customerinfoList);
		return info;
	}
	//回访记录列表查询
	@Override
	public DataTablesResponseInfo getClient_back() {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Client_back> clientbackList = client_backDao.getClient_back();
		for (int i = 0; i < clientbackList.size(); i++) {
			clientbackList.get(i).setSerialnumber(i+1);
			//查询客户名
			String name = client_backDao.getCustomerinfoById(clientbackList.get(i).getCustomerId()).getCustomername();
			//查询客户编号
			String customerCode = client_backDao.getCustomerinfoById(clientbackList.get(i).getCustomerId()).getCustomerCode();
			//客户名赋值
			clientbackList.get(i).setCustomerName(name);
			//客户编号赋值
			clientbackList.get(i).setCustomerCode(customerCode);
			//回访内容
			Datadictionary datadictionary = client_backDao.getVisitContentByCode(clientbackList.get(i).getVisitForm());
			//回访内容赋值
			clientbackList.get(i).setVisitForm(datadictionary.getContent());
			try {
				//回访日期格式转换
				clientbackList.get(i).setVisitDate(DateConvert.changeDate(clientbackList.get(i).getVisitDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		info.setData(clientbackList);
		return info;
	}
	//根据id查询回访记录
	@Override
	public DataTablesResponseInfo getClient_backById(int id) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		Client_back clientback = client_backDao.getClient_backById(id);
		try {
			//回访日期格式转换
			clientback.setVisitDate(DateConvert.changeDate(clientback.getVisitDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		info.setData(clientback);
		return info;
	}
	//查询用户信息ztree
	@Override
	public List<Map<String,Object>> getClient_back_List() {
		return client_backDao.getClient_back_List();
		
	}
	//修改回访记录
	@Override
	public ResponseInfo updateClient_back(Client_back client_back) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = client_backDao.updateClient_back(client_back);
		if(result>0) {
			info.setCode("200");
			info.setMessage("修改成功");
		}else {
			info.setCode("500");
			info.setMessage("修改失败");
		}
		return info;
	}
	//删除回访记录
	@Override
	public ResponseInfo delClient_back(int id) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = client_backDao.delClient_back(id);
		if(result>0) {
			info.setCode("200");
			info.setMessage("删除成功");
		}else {
			info.setCode("500");
			info.setMessage("删除失败");
		}
		return info;
	}
	//根据客户名称查询客户信息
	@Override
	public DataTablesResponseInfo findCustomerinfoByName(String customerName) {
				DataTablesResponseInfo info = new DataTablesResponseInfo();
				List<Client_back> clientbackList = client_backDao.findCustomerinfoByName(customerName);
				for (int i = 0; i < clientbackList.size(); i++) {
					clientbackList.get(i).setSerialnumber(i+1);
					//客户名称
					String name = client_backDao.getCustomerinfoById(clientbackList.get(i).getCustomerId()).getCustomername();
					//客户编号
					String customerCode = client_backDao.getCustomerinfoById(clientbackList.get(i).getCustomerId()).getCustomerCode();
					//客户名称赋值
					clientbackList.get(i).setCustomerName(name);
					//客户编号赋值
					clientbackList.get(i).setCustomerCode(customerCode);
					//回访内容
					Datadictionary datadictionary = client_backDao.getVisitContentByCode(clientbackList.get(i).getVisitForm());
					//回访内容赋值
					clientbackList.get(i).setVisitForm(datadictionary.getContent());
					try {
						//回访日期格式转换
						clientbackList.get(i).setVisitDate(DateConvert.changeDate(clientbackList.get(i).getVisitDate()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				info.setData(clientbackList);
				return info;
	}
	//客户菜单根据父子节点查询
	@Override
	public DataTablesResponseInfo findByNameAndId(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Client_back> clientbackList = client_backDao.findByNameAndId(map);
		for (int i = 0; i < clientbackList.size(); i++) {
			clientbackList.get(i).setSerialnumber(i+1);
			//回访内容
			Datadictionary datadictionary = client_backDao.getVisitContentByCode(clientbackList.get(i).getVisitForm());
			//回访内容赋值
			clientbackList.get(i).setVisitForm(datadictionary.getContent());
			try {
				//回访日期格式转换
				clientbackList.get(i).setVisitDate(DateConvert.changeDate(clientbackList.get(i).getVisitDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		info.setData(clientbackList);
		return info;
	}
}
