package com.jingpeng.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jingpeng.model.Organization_Info;
import com.kdt.base.exception.DataAccessException;
import com.kdt.base.support.mybatis.impl.KDDaoSupport;

@Repository
public class Organization_InfoDao extends KDDaoSupport{

	private final static String NAMESPACE = "organization_Info";
	
	public List<Organization_Info> getOrganization_Info(Organization_Info organization_Info) throws DataAccessException {
		return this.select(NAMESPACE+".getorganization_Info", organization_Info);
	}
	
	public List<Organization_Info> getAsphaltmixingstation(List<Integer> orgids) throws DataAccessException {
		return this.select(NAMESPACE+".getAsphaltmixingstation", orgids);
	}
	
	public List<Organization_Info> getCementmixingstation(List<Integer> orgids) throws DataAccessException {
		return this.select(NAMESPACE+".getCementmixingstation", orgids);
	}
	
	public List<Organization_Info> getWaterstabilitymixingstation(List<Integer> orgids) throws DataAccessException {
		return this.select(NAMESPACE+".getWaterstabilitymixingstation", orgids);
	}

}
