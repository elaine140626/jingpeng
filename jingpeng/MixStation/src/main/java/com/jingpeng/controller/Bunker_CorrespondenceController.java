package com.jingpeng.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.dao.DataTablesResponseInfo;
import com.jingpeng.model.Bunker_Correspondence;
import com.jingpeng.model.ResponseInfo;
import com.jingpeng.model.Search;
import com.jingpeng.model.User_Info;
import com.jingpeng.service.Bunker_CorrespondenceService;
import com.jingpeng.service.CommonService;
import com.jingpeng.util.DateConvert;
import com.jingpeng.util.MessageUtil;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

@Controller
@RequestMapping("/cement_Production")
public class Bunker_CorrespondenceController extends KDController<Object>{
	
	@Autowired
	Bunker_CorrespondenceService bunker_CorrespondenceService;
	
	@Autowired
	private CommonService commonService;

	@RequestMapping("/silo_correspondence.html")
	public String getBunker_Correspondences () {
		return "/sn/silo_correspondence";
		
	}
	/**查询 水泥对应仓
	 * @param bunker_Correspondence
	 * i_org_Id
	 * 
	 * @return
	 * @throws ParseException 
	 */
	
	@RequestMapping("/getBunker_Correspondences")
	@ResponseBody
	public DataTablesResponseInfo getBunker_Correspondences (HttpServletRequest request,@RequestParam HashMap<String, Object> map ) throws ParseException {
		
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			int[] org_Ids = new int[1];
			org_Ids[0] = Integer.valueOf(map.get("i_org_Id").toString());
			map.put("org_Ids", org_Ids);
			List<Bunker_Correspondence> list = bunker_CorrespondenceService.getBunker_Correspondences(map);
			for (int i = 0; i < list.size(); i++) {
				
				list.get(i).setSerialNumber(i + 1);
				list.get(i).setStr_create_Date(DateConvert.changeDate(list.get(i).getStr_create_Date()));
			}
				dtri.setData(list);
				return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	@RequestMapping("/getBunker_CorrespondencesByContonid")
	@ResponseBody
	public DataTablesResponseInfo getBunker_CorrespondencesByContonid (HttpServletRequest request,@RequestParam HashMap<String, Object> map ) {
		
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		DataTablesResponseInfo dtri = new DataTablesResponseInfo();
		try {
			int[] org_Ids = commonService.getUserOrgId(user);
			org_Ids = RequestOrgIdUtil.getOrgids(map, org_Ids);
			map.put("org_Ids", org_Ids);
			
			List<Bunker_Correspondence> list = bunker_CorrespondenceService.getBunker_CorrespondencesByContonid(map);
			for (int i = 0; i < list.size(); i++) {
				
				list.get(i).setSerialNumber(i + 1);
			}
				dtri.setData(list);
				return dtri;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**通过对应仓编号查询 判断是否存在
	 * @param bunker_Correspondence
	 * str_bunker_Code
	   i_org_Id
	 * @return
	 */
	@RequestMapping("/getBunker_CorrespondenceByCode")
	@ResponseBody
	public ResponseInfo getBunker_CorrespondenceByCode (@RequestBody Bunker_Correspondence bunker_Correspondence) {
		ResponseInfo info = new ResponseInfo();
		try {
			List<Bunker_Correspondence> list = bunker_CorrespondenceService.getBunker_CorrespondenceByCode(bunker_Correspondence);
		
			for (Bunker_Correspondence bunker_Correspondence2 : list) {
				System.out.println(bunker_Correspondence2.getI_id());
			}
		if(list != null && !list.isEmpty()) {
			//多种选择 300
			info.setCode(MessageUtil.MULTIPLE_CHOICES);
			//对应仓关系编码已存在
			info.setMessage(MessageUtil.RELATIONSHIP_CODING_EXISTENCE);
			
		}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	/**添加 水泥对应仓 
	 * @param bunker_Correspondence
	 * @return
	 */
	
	@RequestMapping("/addBunker_Correspondence")
	@ResponseBody
	public ResponseInfo addBunker_Correspondence (@RequestBody Bunker_Correspondence bunker_Correspondence) {
		
		User_Info user = (User_Info) request.getSession().getAttribute("user");
		
		String  username = user.getStr_user_Name();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bunker_Correspondence.setStr_create_Date(sdf.format(d));

		bunker_Correspondence.setStr_operator(username);
		ResponseInfo Info	=	new  ResponseInfo();
		try {
			bunker_CorrespondenceService.addBunker_Correspondence(bunker_Correspondence);
			//成功处理请求提示 200
			Info.setCode(MessageUtil.SUCCESSFUL_OPERATION_REQUESTS);
			//添加成功提示
			Info.setMessage(MessageUtil.SUCCESSFUL_OPERATION);
		} catch (BusinessException e) {
			//服务器遇到错误 500
			Info.setCode(MessageUtil.SERVER_ERROR);
			//添加失败提示
			Info.setMessage(MessageUtil.OPERATION_FAILED);
			e.printStackTrace();
		}
		
		return Info;
	}
	
	@RequestMapping("/getcementBunkerValue")
	public @ResponseBody Search getValue(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User_Info obj = (User_Info) session.getAttribute("user");
	
		Search search = new Search();
		search.setUserName(obj.getStr_user_Name());
		return search;
	}
	
}
