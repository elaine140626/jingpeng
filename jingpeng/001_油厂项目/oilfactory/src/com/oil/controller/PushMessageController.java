package com.oil.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.service.pushMessage.PushMessageService;

@Controller
@RequestMapping("/PushMessage")
public class PushMessageController {

	@Autowired
	PushMessageService pushMessageService;
	
	@RequestMapping("/getPushMessage.action")
	@ResponseBody
	//获取推送消息
	public Map<String,Object> getPushMessage(@RequestParam Map<String, Object> map) throws IOException{
		return pushMessageService.getPushMessage(map);
	}
}
