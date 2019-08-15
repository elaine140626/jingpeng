package com.highwayPlatform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.highwayPlatform.dao.UserInfoDao;
import com.highwayPlatform.model.IsSameUser;
import com.highwayPlatform.model.PtUserInfo;
import com.highwayPlatform.service.UserInfoService;
import com.mixingStation.model.userInfo.UserInfo;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoDao userInfoDao;

	// 登录验证
	public PtUserInfo getUserLogin(Map<String, Object> map, HttpServletRequest request) {
		// 用户登录验证
		PtUserInfo userInfo = userInfoDao.getUserLogin(map);
		
		// 用户存在,登录成功
		if (userInfo != null) {	
			
			// 用户信息存放在session中
			//request.getSession().setAttribute("user", userInfo);
			
			// 查询当前用户权限
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("user_id", userInfo.getId());
			
			// 查询出关联的试验室名称
			List<Map<String, Object>> userTestDetailed = userInfoDao.getUserTestInfo(param);
			
			// 试验室权限
			String testDetailed = "";
			for(int i=0;i<userTestDetailed.size();i++) {
				testDetailed += userTestDetailed.get(i).get("UniqueIdentifier").toString() + ",";
			}
			
			// 拌合站权限
			List<Map<String, Object>> userOrgDetailed = userInfoDao.getUserOrgInfo(param);
			String orgDetailed = "";
			for(int i=0;i<userOrgDetailed.size();i++) {
				orgDetailed += userOrgDetailed.get(i).get("OrgId").toString() + ",";
			}
			
			// 试验室权限
			request.getSession().setAttribute("userTestDetailed", testDetailed);
			// 拌合站权限
			request.getSession().setAttribute("userOrgDetailed", orgDetailed);
		} 
		
		// TODO Auto-generated method stub
		return userInfo;
	}

	// 获取用户信息
	public List<Map<String, Object>> getUserInfo(Map<String, Object> map) {
		// 获取当前用户信息
		List<PtUserInfo>  userInfo = userInfoDao.getUserInfo(map);

		// 返回结果
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (userInfo != null) {
			for(int i=0; i<userInfo.size(); i++) {
				Map<String, Object> dataList = new HashMap<String, Object>();
				// 查询当前用户权限
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("user_id", userInfo.get(i).getId());
				// 查询出关联的试验室名称
				List<Map<String, Object>> userTestDetailed = userInfoDao.getUserTestInfo(data);
				List<Map<String, Object>> userOrgDetailed = userInfoDao.getUserOrgInfo(data);
				// 用户信息
				dataList.put("userInfo", userInfo.get(i));
				// 试验室权限
				dataList.put("userTestDetailed", userTestDetailed);
				// 拌合站权限
				dataList.put("userOrgDetailed", userOrgDetailed);
				list.add(dataList);
			}
		}
		return list;
	}

	// 用户注册
	public int insertUserInfo(Map<String, Object> map) {
		PtUserInfo userInfo = userInfoDao.getUserLogin(map);
		if(userInfo != null ) {
			// 用户名已经存在
			return 2;
		}
	
		// 判定结果
		int result = 0;

		// 获取画面输入的用户信息
		PtUserInfo ptUserInfo = new PtUserInfo();

		// 用户名
		ptUserInfo.setUserCode(map.get("userCode").toString());

		// 密码
		ptUserInfo.setPassWord(map.get("passWord").toString());

		// 姓名
		ptUserInfo.setName(map.get("name").toString());
		
		//是否配比
		ptUserInfo.setIsMatching(Integer.parseInt(map.get("isMatching").toString()));
		
		//是否预警
		ptUserInfo.setIsWarning(Integer.parseInt(map.get("isWarning").toString()));
		
		//预警级别
		ptUserInfo.setWarningLevel(map.get("warningLevel").toString() == "" ? null : map.get("warningLevel").toString());

//		// 用户试验室权限
//		ptUserInfo.setRoleType(Integer.parseInt(map.get("roleType").toString()));
//		
//		// 试验室收样权限
//		ptUserInfo.setIsCollector(Integer.parseInt(map.get("isCollector").toString()));
//		
//		// 试验室抽样权限
//		ptUserInfo.setIsSamping(Integer.parseInt(map.get("isSamping").toString()));

		// 添加用户，返回用户的id
		result = userInfoDao.insertUserInfo(ptUserInfo);
		if (result > 0) {
			// 获取用户id
			PtUserInfo user = userInfoDao.getUserLogin(map);

			// 查询唯一标识
			Map<String, Object> data = new HashMap<String, Object>();

			// 试验室选中的权限id
			String testId = map.get("testId").toString();
			if(testId != null && !("").equals(testId)) {
				String[] testRoomIds = testId.split(",");
				data.put("testIds", testRoomIds);

				// 查询出唯一标识
				List<Map<String, Object>> uniqueIdentifierList = userInfoDao.getTestRoomList(data);
				if (uniqueIdentifierList != null && uniqueIdentifierList.size() > 0) {
					// 循环加入试验室权限
					for (int i = 0; i < uniqueIdentifierList.size(); i++) {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("platformUserInfoId", user.getId());
						params.put("uniqueIdentifier", uniqueIdentifierList.get(i).get("UniqueIdentifier").toString());
						result = userInfoDao.insertTestSetDetailed(params);
						if(result > 0) {
							// 添加成功
						}
					}
				}
			}
			

			// 拌合站选中的权限id
			String bhzIds = map.get("bhzId").toString();
			if(bhzIds != null && !("").equals(bhzIds)) {
				String[] bhzIdList = bhzIds.split(",");
				if (bhzIdList != null && bhzIdList.length > 0) {
					for (int i = 0; i < bhzIdList.length; i++) {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("platformUserInfoId", user.getId());
						params.put("orgId", bhzIdList[i].toString());
						result = userInfoDao.insertOrgSetDetailed(params);
						if(result > 0) {
							// 添加成功
						}
					}
				}
			}
		}
		// TODO Auto-generated method stub
		return result;
	}

	// 更新用户信息
	public int updateUserInfo(Map<String, Object> map) {	
		// 获取画面输入的用户信息
		PtUserInfo ptUserInfo = new PtUserInfo();
		
		// 判定结果
		int result = 0;
		
		// 用户id
		ptUserInfo.setId(Integer.parseInt(map.get("id").toString()));

		// 用户名
		ptUserInfo.setUserCode(map.get("userCode").toString());

		// 密码
		ptUserInfo.setPassWord(map.get("passWord").toString());

		// 姓名
		ptUserInfo.setName(map.get("name").toString());
		
		//是否配比
		ptUserInfo.setIsMatching(Integer.parseInt(map.get("isMatching").toString()));
		
		//是否预警
		ptUserInfo.setIsWarning(Integer.parseInt(map.get("isWarning").toString()));
		
		//预警级别
		ptUserInfo.setWarningLevel(map.get("warningLevel").toString());

//		// 用户试验室权限
//	    ptUserInfo.setRoleType(Integer.parseInt(map.get("roleType").toString()));
//
//	    // 试验室抽样权限
//	    ptUserInfo.setIsSamping(Integer.parseInt(map.get("isSamping").toString()));
//		
//		// 试验室收样权限
//		ptUserInfo.setIsCollector(Integer.parseInt(map.get("isCollector").toString()));

		// 删除用户的权限
		result = userInfoDao.delTestSetDetailed(map);
		result = userInfoDao.delOrgSetDetailed(map);
	
		// 查询唯一标识
		Map<String, Object> data = new HashMap<String, Object>();

		// 试验室选中的权限id
		String testId = map.get("testId").toString();
		if(testId != null && !("").equals(testId)) {
			String[] testRoomIds = testId.split(",");
			data.put("testIds", testRoomIds);

			// 查询出唯一标识
			List<Map<String, Object>> uniqueIdentifierList = userInfoDao.getTestRoomList(data);
			if (uniqueIdentifierList != null && uniqueIdentifierList.size() > 0) {
				// 循环加入试验室权限
				for (int i = 0; i < uniqueIdentifierList.size(); i++) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("platformUserInfoId", ptUserInfo.getId());
					params.put("uniqueIdentifier", uniqueIdentifierList.get(i).get("UniqueIdentifier").toString());
					result = userInfoDao.insertTestSetDetailed(params);
				}
			}
		}
		

		// 拌合站选中的权限id
		String bhzIds = map.get("bhzId").toString();
		if(bhzIds != null && !("").equals(bhzIds)) {
			String[] bhzIdList = bhzIds.split(",");
			if (bhzIdList != null && bhzIdList.length > 0) {
				for (int i = 0; i < bhzIdList.length; i++) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("platformUserInfoId", ptUserInfo.getId());
					params.put("orgId", bhzIdList[i].toString());
					result = userInfoDao.insertOrgSetDetailed(params);
				}
			}
		}
		
		// 更新用户信息
		result = userInfoDao.updateUserInfo(ptUserInfo);
		// TODO Auto-generated method stub
		return result;
	}

	// 删除用户
	public int deleteUserInfo(Map<String, Object> map) {
		// 删除试验室的
		int result = userInfoDao.deleteUserInfo(map);
		if (result > 0) {
			// 删除用户的权限
			userInfoDao.delTestSetDetailed(map);
			userInfoDao.delOrgSetDetailed(map);
		}
		// TODO Auto-generated method stub
		return result;
	}

	// 获取试验室树形信息
	public List<Map<String, Object>> getTestRoomList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userInfoDao.getTestRoomList(map);
	}

	// 获取拌合站树形信息
	public List<Map<String, Object>> getOrgInfoList() {
		// TODO Auto-generated method stub
		return userInfoDao.getOrgInfoList();
	}

	@Override
	public int addUserSession(PtUserInfo userInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("user_Id", userInfo.getId());
		return userInfoDao.addUserSession(map);
	}

	@Override
	public List<IsSameUser> getUserSession(PtUserInfo userInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("user_Id", userInfo.getId());
		return userInfoDao.getUserSession(map);
	}

	@Override
	public int delUserSession(PtUserInfo userInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("user_Id", userInfo.getId());
		return userInfoDao.delUserSession(map);
	}
	
	@Override
	public int delMixUserSession(UserInfo userInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("user_Id", userInfo.getId());
		return userInfoDao.delUserSession(map);
	}

}
