package com.mix.service.login;

import java.util.List;

import com.mix.model.Organization_Info;
import com.mix.model.User_Info;

public interface RegisterService {

	int addUser(User_Info user) ;

	List<User_Info> getUserName(User_Info user) ;

	List<Organization_Info> getOrg() ;

}
