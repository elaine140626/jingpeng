package com.oil.dao.sales;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.oil.model.Datadictionary;
import com.oil.model.sales.CustomerMessage;


public interface CustomerMessageDao {

	@Select("select * from customermessage")
	@ResultMap("com.oil.dao.sales.CustomerMessageDao.getAllCustomerMessageMap")
	List<CustomerMessage> getCustomerMessage();
	
	
	@Insert("insert into customermessage (uuid,customernumber,customername,parentnodeId,province,address,contacts,telephone,othernumber,"
			+ "remarks,creater,createrdate,reviser,reviserdate) values \r\n" + 
			"(#{uuid},#{customer_number},#{customer_name},#{parent_nodeId},#{province},#{address},#{contacts},#{telephone},#{other_number},#{remarks},#{creater},"
			+ "#{creater_date},#{reviser},#{reviser_date})")
	@ResultMap("com.oil.dao.sales.CustomerMessageDao.getAllCustomerMessageMap")
	int addCustomerMessage(CustomerMessage customerMessage);
	
	@Select("select * from datadictionary where type='1'")
	@ResultMap("com.oil.dao.sales.CustomerMessageDao.getdatadictionaryMap")
	List<Datadictionary> getDatadictionary();
	
	//List ����
	@Select("select * from datadictionary where type='1' and code=#{code}")
	@ResultMap("com.oil.dao.sales.CustomerMessageDao.getdatadictionaryMap")
	Datadictionary getDatadictionaryByCode(@Param("code") String code);
	
	@Delete("delete from customermessage where Uuid=#{uuid}")
	int delCustomerMessage(@Param("uuid") String uuid);
	
	@Select("select * from customermessage where CustomerName LIKE '%${Keyword}%'")
	@ResultMap("com.oil.dao.sales.CustomerMessageDao.getAllCustomerMessageMap")
	List<CustomerMessage> findCustomerMessage(@Param("Keyword") String Keyword);
}
