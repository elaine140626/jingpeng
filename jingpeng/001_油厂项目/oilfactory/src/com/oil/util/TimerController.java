package com.oil.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.oil.model.DataTablesResponseInfo;
import com.oil.model.Userinfo;
import com.oil.service.dispath.InstroeService;
import com.oil.service.dispath.OutboundService;
import com.oil.service.repertory.RepertoryService;
import com.oil.service.userinfo.UserinfoService;

@Controller
public class TimerController {
//	@Autowired
//	AppJpushService appJpushService;
	@Autowired
	OutboundService outboundService;
	
	@Autowired 
	InstroeService instroeService;
	
	@Autowired 
	RepertoryService repertoryService;
	
	@Autowired
	UserinfoService userinfoService;
	
	/**
	 * 定时器
	 */
    public void quartzJobTestMethod() {
//        System.out.println("使用SpringMVC框架配置定时任务");
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("type", "out");
        DataTablesResponseInfo infoList = outboundService.getInfoList(maps);
        if (!infoList.getData().toString().equals("[]")) {
//            // 获取将要推送的数据
            List<Userinfo> list = userinfoService.getAppUser(maps);
            if (list!=null && list.size()>0) {
            	appJpush(list,"出库单偏差值超差","出库单偏差值超差，需要审核。");
//            	for(int i = 0;i<list.size();i++) {
//            		if(!"".equals(list.get(i).get("grouping").toString())) { 
//            			Map<String, Object> map = new HashMap<String, Object>();
//            			String grouping = list.get(i).get("grouping").toString();
//            			String[] str = grouping.split(",");
//            			map.put("UniqueIdentifier", str);
//            			//推送消息
//            			int result = appJpush(map,list.get(i).get("title").toString(),list.get(i).get("content").toString());
//            			if (result == 1) {
//            				//修改推送消息状态
//                			appJpushService.updatePushState(list.get(i).get("id").toString());
//            			}       			
//            		}      		    		
//            	}
            }	
		}  
        maps.put("type", "instroe");
        DataTablesResponseInfo instroeInfoList = instroeService.getInfoList(maps);
        if (!instroeInfoList.getData().toString().equals("[]")) {
          // 获取将要推送的数据
          List<Userinfo> list = userinfoService.getAppUser(maps);
          if (list!=null && list.size()>0) {
          	appJpush(list,"入库单偏差值超差","入库单偏差值超差，需要审核。");
          }	
		}
        maps.put("type", "repertory");
        DataTablesResponseInfo repertoryInfoList = repertoryService.getInfoList(maps);
        if (!repertoryInfoList.getData().toString().equals("[]")) {
//          // 获取将要推送的数据
          List<Userinfo> list = userinfoService.getAppUser(maps);
          if (list!=null && list.size()>0) {
          	appJpush(list,"未称重出库单偏差值超差","未称重出库单偏差值超差，需要审核。");
          }	
		}
    }
    
    /**
	 * 推送
	 */
    public int appJpush(List<Userinfo> userList,String title,String content) {
//		System.out.println("1111111111111111推送测试");
		int result = 0;
//		List<Map<String, Object>> userList= userinfoService.getAppUser(UniqueIdentifier);
		if (userList.size()>0) {
			Collection<String> collection = new HashSet<String>();
			for(int i= 0;i<userList.size();i++) {
				if(userList.get(i)!=null) {
					collection.add(userList.get(i).getId()+"");
				}			
			}
			//调用推送接口
			result = JpushClientUtil.pushMsg(collection,title,content);
		}	
//		System.out.println("1111111111111111推送测试");
		return result;
	}
}
