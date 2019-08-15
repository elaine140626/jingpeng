package com.oil.service.sales;

import java.util.List;

import com.oil.model.Datadictionary;
import com.oil.model.sales.CustomerMessage;

public interface CustomerMessageService {

	List<CustomerMessage> getCustomerMessage();
	
	int addCustomerMessage(CustomerMessage customerMessage);
	
	List<Datadictionary> getDatadictionary();
	Datadictionary getDatadictionaryByCode(String code);
	
	int delCustomerMessage(String uuid);
	
	List<CustomerMessage> findCustomerMessage(String Keyword);
}
