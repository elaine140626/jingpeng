package com.MixStation.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MixStation.model.MaterialProductEntity;
import com.MixStation.service.MaterialProductService;
@Controller
@RequestMapping("/MaterialProduct")
public class MaterialProductController{
	
	@Autowired
	private MaterialProductService  materialProductService;
	/**
	 * 获取混合料名称/型号
	 **/
	@RequestMapping("/getMaterialProduct.action")
	@ResponseBody
	public List<MaterialProductEntity> getMaterialProduct(HttpServletRequest request,@RequestParam Map<String, Object> map) {	
		
		return materialProductService.getMaterialProduct(map);
	}
	
	
}