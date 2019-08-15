package com.oil.service.client.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oil.dao.client.ClientVisitDao;
import com.oil.model.client.Client_back;
import com.oil.model.sales.Customerinfo;
import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Datadictionary;
import com.oil.model.ResponseInfo;
import com.oil.service.client.ClientVisitService;
import com.oil.util.DateConvert;

@Service
public class ClientVisitServiceImpl implements ClientVisitService {

	@Autowired
	private ClientVisitDao clientVisitDao;
	//新增拜访记录
	@Override
	public ResponseInfo addClientVisit(Client_back client_back) {
		ResponseInfo info = new ResponseInfo();
		int result;
		result = clientVisitDao.addClientVisit(client_back);
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
		List<Datadictionary> dataList = clientVisitDao.getVisitContent();
		info.setData(dataList);
		return info;
	}
	//客户名称下拉列表内容查询
	@Override
	public DataTablesResponseInfo getCustomerInfo() {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Customerinfo> customerinfoList = clientVisitDao.getCustomerInfo();
		info.setData(customerinfoList);
		return info;
	}
	//获取拜访记录数据
	@Override
	public DataTablesResponseInfo getClientVisit() {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Client_back> clientbackList = clientVisitDao.getClientVisit();
		for (int i = 0; i < clientbackList.size(); i++) {
			clientbackList.get(i).setSerialnumber(i+1);
			Customerinfo customerinfo = new Customerinfo();
			customerinfo = clientVisitDao.getCustomerinfoById(clientbackList.get(i).getCustomerId());
			String name="";
			String customerCode="";
			if(customerinfo!=null) {
				name = customerinfo.getCustomername();
				//客户编号
				customerCode = customerinfo.getCustomerCode();
				//String name = clientVisitDao.getCustomerinfoById(clientbackList.get(i).getCustomerId()).getCustomername();
				clientbackList.get(i).setCustomerName(name);
				clientbackList.get(i).setCustomerCode(customerCode);
				Datadictionary datadictionary = clientVisitDao.getVisitContentByCode(clientbackList.get(i).getVisitForm());
				clientbackList.get(i).setVisitForm(datadictionary.getContent());
				try {
					clientbackList.get(i).setVisitDate(DateConvert.changeDate(clientbackList.get(i).getVisitDate()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		info.setData(clientbackList);
		return info;
	}
	//根据id查询拜访记录
	@Override
	public DataTablesResponseInfo getClientVisitById(int id) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		Client_back clientback = clientVisitDao.getClientVisitById(id);
		try {
			clientback.setVisitDate(DateConvert.changeDate(clientback.getVisitDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		info.setData(clientback);
		return info;
	}
	//修改拜访记录
	@Override
	public ResponseInfo updateClientVisit(Client_back client_back) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = clientVisitDao.updateClientVisit(client_back);
		if(result>0) {
			info.setCode("200");
			info.setMessage("修改成功");
		}else {
			info.setCode("500");
			info.setMessage("修改失败");
		}
		return info;
	}
	//删除拜访记录
	@Override
	public ResponseInfo delClientVisit(int id) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		result = clientVisitDao.delClientVisit(id);
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
				List<Client_back> clientbackList = clientVisitDao.findCustomerinfoByName(customerName);
				for (int i = 0; i < clientbackList.size(); i++) {
					clientbackList.get(i).setSerialnumber(i+1);
					String name = clientVisitDao.getCustomerinfoById(clientbackList.get(i).getCustomerId()).getCustomername();
					//客户编号
					String customerCode = clientVisitDao.getCustomerinfoById(clientbackList.get(i).getCustomerId()).getCustomerCode();
					clientbackList.get(i).setCustomerName(name);
					clientbackList.get(i).setCustomerCode(customerCode);
					Datadictionary datadictionary = clientVisitDao.getVisitContentByCode(clientbackList.get(i).getVisitForm());
					clientbackList.get(i).setVisitForm(datadictionary.getContent());
					try {
						clientbackList.get(i).setVisitDate(DateConvert.changeDate(clientbackList.get(i).getVisitDate()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				info.setData(clientbackList);
				return info;
	}
	//拜访记录列表查询
	@Override
	public List<Map<String,Object>> getClient_back() {
		return clientVisitDao.getClient_back();
	}
	//客户菜单根据父子节点查询
	@Override
	public DataTablesResponseInfo findByNameAndId(Map<String, Object> map) {
		DataTablesResponseInfo info = new DataTablesResponseInfo();
		List<Client_back> clientbackList = clientVisitDao.findByNameAndId(map);
		for (int i = 0; i < clientbackList.size(); i++) {
			clientbackList.get(i).setSerialnumber(i+1);
			Datadictionary datadictionary = clientVisitDao.getVisitContentByCode(clientbackList.get(i).getVisitForm());
			clientbackList.get(i).setVisitForm(datadictionary.getContent());
			try {
				clientbackList.get(i).setVisitDate(DateConvert.changeDate(clientbackList.get(i).getVisitDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		info.setData(clientbackList);
		return info;
	}

}
