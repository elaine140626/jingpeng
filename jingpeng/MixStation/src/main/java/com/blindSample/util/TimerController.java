package com.blindSample.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.blindSample.service.AppJpushService;


@Controller
public class TimerController {
	@Autowired
	AppJpushService appJpushService;
	
	/**
	 * 定时器
	 */
    public void quartzJobTestMethod() {
        System.out.println("使用SpringMVC框架配置定时任务");
        // 获取将要推送的数据
        List<Map<String, Object>> list = appJpushService.getPushInfo();
        if (list!=null && list.size()>0) {     	
        	for(int i = 0;i<list.size();i++) {
        		if(!"".equals(list.get(i).get("grouping").toString())) { 
        			Map<String, Object> map = new HashMap<String, Object>();
        			String grouping = list.get(i).get("grouping").toString();
        			String[] str = grouping.split(",");
        			map.put("UniqueIdentifier", str);
        			//推送消息
        			int result = appJpush(map,list.get(i).get("title").toString(),list.get(i).get("content").toString());
        			if (result == 1) {
        				//修改推送消息状态
            			appJpushService.updatePushState(list.get(i).get("id").toString());
        			}       			
        		}      		    		
        	}
        }      
    }
    
    /**
	 * 推送
	 */
    public int appJpush(Map<String, Object> UniqueIdentifier,String title,String content) {
		System.out.println("1111111111111111推送测试");
		int result = 0;
		List<Map<String, Object>> userList= appJpushService.getAppUser(UniqueIdentifier);
		if (userList.size()>0) {
			Collection<String> collection = new HashSet<String>();
			for(int i= 0;i<userList.size();i++) {
				collection.add(userList.get(i).get("UserCode").toString());
			}
			//调用推送接口
			result = JpushClientUtil.pushMsg(collection,title,content);
		}	
		System.out.println("1111111111111111推送测试");
		return result;
	}
}
