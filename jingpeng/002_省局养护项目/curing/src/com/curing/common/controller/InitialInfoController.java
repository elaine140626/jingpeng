package com.curing.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curing.common.model.EntryTreeEntity;
import com.curing.common.model.SubheadNumberMoneyEntity;
import com.curing.common.service.InitialInfoService;

@Controller
@RequestMapping("/InitialInfo")
public class InitialInfoController {
	@Autowired
	private InitialInfoService initialInfoService;

	// 左侧市县项目树取得
	@RequestMapping("/getEntryTreeList.action")
	@ResponseBody
	public List<EntryTreeEntity> getEntryTreeList(@RequestParam HashMap<String, Object> map) {
		List<EntryTreeEntity> list = initialInfoService.getEntryTreeList(map);
		return list;
	}
	
	// 数据字典取得
	@RequestMapping("/getDataDictionary.action")
	@ResponseBody
	public List<Map<String,Object>> getDataDictionary(@RequestParam HashMap<String, Object> map){
		List<Map<String,Object>> list = initialInfoService.getDataDictionary(map);
		return list;
	}
	
	// 根据项目id 查询项目信息
	@RequestMapping("/getVEntryTree.action")
	@ResponseBody
	public List<EntryTreeEntity> getVEntryTree(@RequestParam HashMap<String, Object> map) {
		List<EntryTreeEntity> list = initialInfoService.getVEntryTree(map);
		return list;
	}
	
	// 根据项目id 查询子目号金额
	@RequestMapping("/getSubheadNumberMoney.action")
	@ResponseBody
	public List<SubheadNumberMoneyEntity> getSubheadNumberMoney(@RequestParam HashMap<String, Object> map) {
		List<SubheadNumberMoneyEntity> list = initialInfoService.getSubheadNumberMoney(map);
		return list;
	}

}
