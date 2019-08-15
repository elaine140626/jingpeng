package com.oil.service.sales.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oil.dao.sales.CustomerMessageDao;
import com.oil.model.Datadictionary;
import com.oil.model.sales.CustomerMessage;
import com.oil.service.sales.CustomerMessageService;

@Service
@Transactional
public class CustomerMessageServiceImpl implements CustomerMessageService{
	
	
	@Autowired
	private CustomerMessageDao customerMessageDao;

	@Override
	public List<CustomerMessage> getCustomerMessage() {
		// TODO Auto-generated method stub
		return customerMessageDao.getCustomerMessage();
	}

	@Override
	public int addCustomerMessage(CustomerMessage customerMessage) {
		// TODO Auto-generated method stub
		return customerMessageDao.addCustomerMessage(customerMessage);
	}

	@Override
	public List<Datadictionary> getDatadictionary() {
		// TODO Auto-generated method stub
		return customerMessageDao.getDatadictionary();
	}

	@Override
	public Datadictionary getDatadictionaryByCode(String code) {
		// TODO Auto-generated method stub
		return customerMessageDao.getDatadictionaryByCode(code);
	}

	@Override
	public int delCustomerMessage(String uuid) {
		// TODO Auto-generated method stub
		return customerMessageDao.delCustomerMessage(uuid);
	}

	@Override
	public List<CustomerMessage> findCustomerMessage(String Keyword) {
		// TODO Auto-generated method stub
		return customerMessageDao.findCustomerMessage(Keyword);
	}

	
}
