package com.testRoom.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.highwayPlatform.model.ResponseInfo;
import com.highwayPlatform.util.MessageUtilBlindSample;
import com.testRoom.dao.ConstructionProgressDao;
import com.testRoom.model.ActualCompletionQuantity;
import com.testRoom.model.AppBridgeEntity;
import com.testRoom.model.AppRoadEntity;
import com.testRoom.model.BridgeConstructionDetails;
import com.testRoom.model.ConstructionAuthorization;
import com.testRoom.model.EngineeringDesignContent;
import com.testRoom.service.ConstructionProgressService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class ConstructionProgressServiceImpl implements ConstructionProgressService{
	
	@Autowired 
	private ConstructionProgressDao constructionProgressDao;

	/*
	 * 获取该用户权限标段下的标段信息
	 */
	@Override
	public List<ConstructionAuthorization> getConstructionList(Map<String, Object> map) {
		return constructionProgressDao.getConstructionList(map);
	}

	/*
	 * 查询查询实际工程量
	 */
	public List<ActualCompletionQuantity> getActualCompletionQuantity(Map<String, Object> map) {
		return constructionProgressDao.getActualCompletionQuantity(map);
	}

	/*
	 * 查询工程设计量内容 
	 */
	public List<EngineeringDesignContent> getEngineeringDesignContent(Map<String, Object> map){
		return constructionProgressDao.getEngineeringDesignContent(map);
	}
	
	/*
	 * 新增工程设计量内容
	 */
	public ResponseInfo addEngineeringDesignContent(EngineeringDesignContent engineeringDesignContent) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		
		// 先获取是否存在数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("UniqueIdentifier", engineeringDesignContent.getUniqueIdentifier());
		List<EngineeringDesignContent> dataList = constructionProgressDao.getEngineeringDesignContent(map);
		if(dataList != null && dataList.size() > 0) {
			// 如果存在先删除在新增
			result = constructionProgressDao.delEngineeringDesignContent(map);
			if (result <= 0) {
				// 服务器遇到错误 500
				info.setCode(MessageUtilBlindSample.SERVER_ERROR);
				// 操作失败提示
				info.setMessage(MessageUtilBlindSample.OPERATION_FAILED);
				return info;
			}
		}	
		
		// 添加工程设计量内容
		result = constructionProgressDao.addEngineeringDesignContent(engineeringDesignContent);
		if (result > 0) {
			// 成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			// 操作成功提示
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		} else {
			// 服务器遇到错误 500
			info.setCode(MessageUtilBlindSample.SERVER_ERROR);
			// 操作失败提示
			info.setMessage(MessageUtilBlindSample.OPERATION_FAILED);
		}
		return info;
	}
	
	/*
	 * 新增施工内容信息
	 */
	public ResponseInfo addInfo(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		int result = 0;
		// 新增实际完成量内容
		ActualCompletionQuantity actualCompletionQuantity = new ActualCompletionQuantity();
		
		// 试验室唯一标识
		actualCompletionQuantity.setUniqueIdentifier(map.get("uniqueIdentifier").toString());
	
		// 实际施工量id
		if(map.get("id") != null && "" != map.get("id").toString()) {
			// id 
			actualCompletionQuantity.setId(Integer.parseInt(map.get("id").toString()));
			// 修改人
			actualCompletionQuantity.setReviser(map.get("Creater").toString());
			// 实际完成量信息
			actualCompletionQuantity = setActualCompletionQuantity(map, actualCompletionQuantity);
			
			// 更新实际完成量数据
			result += constructionProgressDao.updateActualCompletionQuantity(actualCompletionQuantity);
			
			// 更新对应桥梁明细数据
			List<BridgeConstructionDetails> bridgeConstructionDetailsList = setBridgeConstructionDetailsList(map, actualCompletionQuantity);
			for(int i= 0; i<bridgeConstructionDetailsList.size(); i++) {
				result += constructionProgressDao.updateBridgeConstructionDetails(bridgeConstructionDetailsList.get(i));
			}
		}else {
			// 创建人
			actualCompletionQuantity.setCreater(map.get("Creater").toString());
			SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
			actualCompletionQuantity.setCreaterDate(format.format(new Date()));
			// 实际完成量信息
			actualCompletionQuantity = setActualCompletionQuantity(map, actualCompletionQuantity);
			// 新增实际完成量数据
			result += constructionProgressDao.addActualCompletionQuantity(actualCompletionQuantity);
			
			// 获取最新插入的id
			int id = constructionProgressDao.getAddId(actualCompletionQuantity);
			actualCompletionQuantity.setId(id);
			
			// 新增对应桥梁明细数据
			List<BridgeConstructionDetails> bridgeConstructionDetailsList = setBridgeConstructionDetailsList(map, actualCompletionQuantity);
			for(int i= 0; i<bridgeConstructionDetailsList.size(); i++) {
				result += constructionProgressDao.addBridgeConstructionDetails(bridgeConstructionDetailsList.get(i));
			}
		}
		if (result > 0) {
			// 成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			// 操作成功提示
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		} else {
			// 服务器遇到错误 500
			info.setCode(MessageUtilBlindSample.SERVER_ERROR);
			// 操作失败提示
			info.setMessage(MessageUtilBlindSample.OPERATION_FAILED);
		}
		return info;
	}
	
	/*
	 * 修改施工内容信息
	 */
	public ResponseInfo updateInfo(Map<String, Object> map) {
		return null;
	}
	
	// 实际完成量对象
	private ActualCompletionQuantity setActualCompletionQuantity(Map<String, Object> map, ActualCompletionQuantity actualCompletionQuantity) {
		if (!"".equals(map.get("subgrade_Fill").toString())) {
			actualCompletionQuantity.setSubgrade_Fill(Double.parseDouble(map.get("subgrade_Fill").toString()));
		}
		if (!"".equals(map.get("subgrade_Excavation").toString())) {
			actualCompletionQuantity.setSubgrade_Excavation(Double.parseDouble(map.get("subgrade_Excavation").toString()));
		}
		if (!"".equals(map.get("subgrade_Total").toString())) {
			actualCompletionQuantity.setSubgrade_Total(Double.parseDouble(map.get("subgrade_Total").toString()));
		}
		if (!"".equals(map.get("cushion_LeftAmplitude").toString())) {
			actualCompletionQuantity.setCushion_LeftAmplitude(Double.parseDouble(map.get("cushion_LeftAmplitude").toString()));
		}
		if (!"".equals(map.get("cushion_RightAmplitude").toString())) {
			actualCompletionQuantity.setCushion_RightAmplitude(Double.parseDouble(map.get("cushion_RightAmplitude").toString()));
		}
		if (!"".equals(map.get("subbase_LeftAmplitude").toString())) {
			actualCompletionQuantity.setSubbase_LeftAmplitude(Double.parseDouble(map.get("subbase_LeftAmplitude").toString()));
		}
		if (!"".equals(map.get("subbase_RightAmplitude").toString())) {
			actualCompletionQuantity.setSubbase_RightAmplitude(Double.parseDouble(map.get("subbase_RightAmplitude").toString()));
		}
		if (!"".equals(map.get("substrate_LeftAmplitude").toString())) {
			actualCompletionQuantity.setSubstrate_LeftAmplitude(Double.parseDouble(map.get("substrate_LeftAmplitude").toString()));
		}
		if (!"".equals(map.get("substrate_RightAmplitude").toString())) {
			actualCompletionQuantity.setSubstrate_RightAmplitude(Double.parseDouble(map.get("substrate_RightAmplitude").toString()));
		}
		if (!"".equals(map.get("lowerLayer_LeftAmplitude").toString())) {
			actualCompletionQuantity.setLowerLayer_LeftAmplitude(Double.parseDouble(map.get("lowerLayer_LeftAmplitude").toString()));
		}
		if (!"".equals(map.get("lowerLayer_RightAmplitude").toString())) {
			actualCompletionQuantity.setLowerLayer_RightAmplitude(Double.parseDouble(map.get("lowerLayer_RightAmplitude").toString()));
		}
		if (!"".equals(map.get("mesosphere_LeftAmplitude").toString())) {
			actualCompletionQuantity.setMesosphere_LeftAmplitude(Double.parseDouble(map.get("mesosphere_LeftAmplitude").toString()));
		}
		if (!"".equals(map.get("mesosphere_RightAmplitude").toString())) {
			actualCompletionQuantity.setMesosphere_RightAmplitude(Double.parseDouble(map.get("mesosphere_RightAmplitude").toString()));
		}
		if (!"".equals(map.get("upperLayer_LeftAmplitude").toString())) {
			actualCompletionQuantity.setUpperLayer_LeftAmplitude(Double.parseDouble(map.get("upperLayer_LeftAmplitude").toString()));
		}
		if (!"".equals(map.get("upperLayer_RightAmplitude").toString())) {
			actualCompletionQuantity.setUpperLayer_RightAmplitude(Double.parseDouble(map.get("upperLayer_RightAmplitude").toString()));
		}
		return actualCompletionQuantity;
	}

	// 桥梁明细数据
	private List<BridgeConstructionDetails> setBridgeConstructionDetailsList(Map<String, Object> map,ActualCompletionQuantity actualCompletionQuantity){
		// 首先把字符串转成 JSONArray  对象
		JSONArray jsonArray=JSONArray.fromObject(map.get("bridgeConstructionDetailsList"));
		List<BridgeConstructionDetails> list = new ArrayList<BridgeConstructionDetails>();
		if(jsonArray.size()>0){
			for(int i=0;i<jsonArray.size();i++){
				BridgeConstructionDetails bridgeConstructionDetails = new BridgeConstructionDetails();
				// 遍历 jsonarray 数组，把每一个对象转成 json 对象
				JSONObject job = jsonArray.getJSONObject(i);
				bridgeConstructionDetails.setUniqueIdentifier(job.get("uniqueIdentifier").toString());
				bridgeConstructionDetails.setBridgeId(Integer.parseInt(job.get("bridgeId").toString()));
				bridgeConstructionDetails.setActualCompletionId(actualCompletionQuantity.getId());
				bridgeConstructionDetails.setPile_Foundation(job.get("pileFoundation").toString());
				bridgeConstructionDetails.setPile_TiedBeam(job.get("pileTiedBeam").toString());
				bridgeConstructionDetails.setAbutment(job.get("abutment").toString());
				bridgeConstructionDetails.setCaps(job.get("caps").toString());
				bridgeConstructionDetails.setPlatform_Body(job.get("platformBoby").toString());
				bridgeConstructionDetails.setPlatform_Cap(job.get("platformCap").toString());
				bridgeConstructionDetails.setEar_BackWall(job.get("earBackWall").toString());
				bridgeConstructionDetails.setBazi_Wall(job.get("baziWall").toString());
				bridgeConstructionDetails.setPier_Shaft(job.get("pierShaft").toString());
				bridgeConstructionDetails.setPier_TiedBeam(job.get("pierTiedBeam").toString());
				bridgeConstructionDetails.setCoping(job.get("coping").toString());
				bridgeConstructionDetails.setSupport_Cushion(job.get("supportCushion").toString());
				bridgeConstructionDetails.setBaffle_Block(job.get("baffleBlock").toString());
				bridgeConstructionDetails.setBeam_SlabPrefabrication(job.get("beamSlabPrefabrication").toString());
				bridgeConstructionDetails.setCastInPlace_Beam(job.get("castInPlaceBeam").toString());
				bridgeConstructionDetails.setBearing(job.get("bearing").toString());
				bridgeConstructionDetails.setDiaphragm(job.get("diaphragm").toString());
				bridgeConstructionDetails.setWet_Joint(job.get("wetJoint").toString());
				bridgeConstructionDetails.setBridgeDeck_System(job.get("bridgeDeckSystem").toString());
				bridgeConstructionDetails.setBridgehead_Slab(job.get("bridgeheadSlab").toString());
				bridgeConstructionDetails.setGuardrail(job.get("guardrail").toString());
				bridgeConstructionDetails.setAntiCollision_Wall(job.get("antiCollisionWall").toString());
				bridgeConstructionDetails.setExpansion_Joint(job.get("expansionJoint").toString());
				bridgeConstructionDetails.setCreater(map.get("Creater").toString());
				list.add(bridgeConstructionDetails);
				}
		}
		return list;
	};
	
	
	/*
	 * 删除施工内容信息
	 */
	public ResponseInfo delInfo(Map<String, Object> map) {
		ResponseInfo info = new ResponseInfo();
		// 删除完成工作量
		int result = 0;
		result += constructionProgressDao.delActualCompletionQuantity(map);
		result += constructionProgressDao.delBridgeConstructionDetails(map);
		if(result > 0) {
			// 成功处理请求提示 200
			info.setCode(MessageUtilBlindSample.SUCCESSFUL_OPERATION_REQUESTS);
			// 操作成功提示
			info.setMessage(MessageUtilBlindSample.SUCCESSFUL_OPERATION);
		}else {
			// 服务器遇到错误 500
			info.setCode(MessageUtilBlindSample.SERVER_ERROR);
			// 操作失败提示
			info.setMessage(MessageUtilBlindSample.OPERATION_FAILED);
		}
		return info;
	}

	/*
	 * 上传图片
	 */
	@Override
	public int uploadPictures(Map<String, String> map) {
		
		return constructionProgressDao.uploadPictures(map);
	}

	/*
	 * 获取路和桥梁信息
	 */
	public Map<String, Object> getInfoById(Map<String, Object> map) {
		Map<String, Object> data = new HashMap<String, Object>();
		// 获取实际完成量（路面）
		data.put("actualCompletionQuantity", constructionProgressDao.getActualCompletionQuantity(map));
		data.put("bridgeConstructionDetails", constructionProgressDao.getBridgeConstructionDetails(map));
		return data;
	}

	// 获取累计值
	public Double getSum(Map<String, Object> map) {
		return constructionProgressDao.getSum(map);
	}
	
	// App接口 路
	public AppRoadEntity getAppRoadByUniqueIdentifier(Map<String, Object> map) {
		return constructionProgressDao.getAppRoadByUniqueIdentifier(map);
	}
	
	// App接口 桥
	public AppBridgeEntity getAppBridgeByRoad(Map<String, Object> map) {
		return constructionProgressDao.getAppBridgeByRoad(map);
	}
}
