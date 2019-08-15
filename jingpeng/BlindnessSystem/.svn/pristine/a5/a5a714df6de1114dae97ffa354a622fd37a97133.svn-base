package com.jingpeng.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingpeng.model.BlindSampDetailed;
import com.jingpeng.model.BlindSampleInfo;
import com.jingpeng.model.QrCodeInfo;
import com.jingpeng.model.UserInfo;
import com.jingpeng.service.BlindSampleInfoService;
import com.jingpeng.service.OrganizationInfoService;
import com.jingpeng.service.UserInfoService;
import com.jingpeng.util.Consts;
import com.jingpeng.util.DateConvert;
import com.jingpeng.util.JDBCUtil;
import com.jingpeng.util.Message;
import com.jingpeng.util.PageInfoUtil;
import com.jingpeng.util.Urls;
import com.jingpeng.util.RequestOrgIdUtil;
import com.kdt.base.exception.BusinessException;
import com.kdt.base.support.springsupport.KDController;

import net.sf.json.JSONArray;

@Controller
public class AppController extends KDController<Object> {
/*	//返回code key
	public final static String APPCONTROLLER_MESSAGECODE_FILED = "messageCode";
	//返回code key
	public final static String APPCONTROLLER_MESSAGE_FILED = "message";
	//登录返回信息 key
	public final static String APPCONTROLLER_USERINFO_FILED = "userInfo";

	//盲样查询信息list key
	private  final static String APPCONTROLLER_BLINDSAMPLEINFOLIST_FILED = "blindSampleInfoList";
	
	//盲样二维码编号区间
	private  final static String APPCONTROLLER_NUMBERED_INTERVAL_FILED = "0-5000";
	//盲样二维码编号前缀
	private  final static String APPCONTROLLER_PREFIX_FILED = "jingpeng";
	
*/	
	@Autowired
	private OrganizationInfoService organizationInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private BlindSampleInfoService blindSampleInfoService;
	
/*	@RequestMapping("index.html")
	public String index(HttpServletRequest request) {
		return "/lq/index";
	}
	
	
	@RequestMapping("addUser.html")
	public String testUser(HttpServletRequest request) {
		return "/lq/user";
	}
	
	@RequestMapping("login.html")
	public String testUserLogin(HttpServletRequest request) {
		return "/lq/login";
	}
	@RequestMapping("shiyan_5.html")
	public String testUsershiyan_5(HttpServletRequest request) {
		return "/lq/shiyan_5";
	}
	@RequestMapping("jilu_2.html")
	public String testUserjilu_2(HttpServletRequest request) {
		return "/lq/jilu_2";
	}*/
	/*
	 * 登录信息验证
	 * tongn
	 * 2018.7.16
	 
	@RequestMapping(Urls.BLINDSAMPLEDELECTION_APP_LOGIN)
	public @ResponseBody Map<String, Object> login(UserInfo userInfo) {
		
		long startTime=System.currentTimeMillis();   //获取开始时间
		
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> param = new HashMap<String,Object>();
		UserInfo userInfoRe = null;
		
		try {
			
			//参数
			param.put("userCode", userInfo.getUserCode());
			param.put("password", userInfo.getPassword());
			
			//查询用户信息
			userInfoRe = userInfoService.getUserInfo(param);
			
			//返回结果
			if(userInfoRe == null) {
				
				result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SERVER_ERROR_CODE);
				result.put(APPCONTROLLER_MESSAGE_FILED, Message.SERVER_ERROR_OPERATION);
				
			}
			else {
				if(userInfoRe.getRoleType()==1 ||userInfoRe.getRoleType()==2) {
					
					//null 变为 ""
					setUserInfo(userInfoRe);
					result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SUCCESS_CODE);
					result.put(APPCONTROLLER_MESSAGE_FILED, Message.SUCCESSFUL_OPERATION);
					result.put(APPCONTROLLER_USERINFO_FILED, userInfoRe);
					
		
					
				}else {
					
					result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SERVER_ERROR_CODE);
					result.put(APPCONTROLLER_MESSAGE_FILED, Message.SERVER_ERROR_POWER);
				
				}
			}
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long endTime=System.currentTimeMillis(); //获取结束时间
		 
		System.out.println("登录程序运行时间： "+(endTime-startTime)+"ms");				
		return result;

	}
	
	

	
	 * null 变为  “”
	 * tongn
	 * 2018.7.16
	 
	   private void setUserInfo(UserInfo userInfoRe) {
	
	        if(userInfoRe.getUserCode()==null) {
				
	        	userInfoRe.setRemoteuserCode("");
	     	}
	     	        
	        if(userInfoRe.getName()==null) {
				
	        	userInfoRe.setRemotename("");
	     	}
	     	
	        
	        if(userInfoRe.getOperator()==null) {
				
	        	userInfoRe.setRemoteoperator("");
	     	}
	     
	  	  
	       if(userInfoRe.getModifier()==null) {
			
	    	   userInfoRe.setRemotemodifier("");
		   }
		   	  
	      if(userInfoRe.getRemoteOrgName()==null) {
			
	    	  userInfoRe.setRemoteOrgName("");
		  }	      
			   
	}

	
		
		 *   盲样信息表插入
		 * tongn
		 * 2018.7.16
		 
		@RequestMapping(Urls.BLINDSAMPLEDELECTION_APP_ADD_BLINDSAMPLEINFO)
		public @ResponseBody Map<String, Object> addBlindSampleInfo(BlindSampleInfo blindSampleInfo) throws Exception {
			long startTime=System.currentTimeMillis();   //获取开始时间
			Map<String,Object> result = new HashMap<String,Object>();
				
			try {
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("sampeCode", blindSampleInfo.getSampeCode());
				List<Map<String, Object>> ypList = blindSampleInfoService.getypCode(param);
				
				if((Integer)ypList.get(0).get("count1")>0) {
					
					 result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SERVER_ERROR_CODE);
					 result.put(APPCONTROLLER_MESSAGE_FILED, "样品编号不能重复");
				}else {
			
			//检查盲样信息表二维码
			boolean isEnableQrCode =  checkQrCode1(blindSampleInfo.getqRCode(),result);
			
			    //二维码可录入
				if(isEnableQrCode) {
					
					blindSampleInfo.setMoldDate(getNowDate(blindSampleInfo.getRemotemoldDate()));
					blindSampleInfo.setSamplingDate(getNowDate(blindSampleInfo.getRemotesamplingDate()));
					blindSampleInfo.setIsQualifiedSamp(1);
					
					//插入盲样信息表
					 blindSampleInfoService.addBlindSampleInfo(blindSampleInfo);
					 
					 result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SUCCESS_CODE);
					 result.put(APPCONTROLLER_MESSAGE_FILED, Message.SUCCESSFUL_OPERATION);
				}
				
				}
	
			} catch (BusinessException e) {
								
				result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SERVER_ERROR_CODE);
			    result.put(APPCONTROLLER_MESSAGE_FILED, Message.SERVER_ERROR_OPERATION);
			    
				e.printStackTrace();
			}
			
			long endTime=System.currentTimeMillis(); //获取结束时间			 
			System.out.println("盲样信息插入程序运行时间： "+(endTime-startTime)+"ms");
			
			return result;

		}



		private boolean checkQrCode1(String qrCode, Map<String, Object> result) {
			
			//二维码标志
			boolean qrCodeFlag = false;
			boolean prefix = false;
			QrCodeInfo resultQr  = null;
					
			try {
				
				//第一步前缀是否符合	
				
				// 拿出二维码前缀
				String prefixCode = "";
				for(int i=0;i<qrCode.length();i++){
					
					char ch = qrCode.charAt(i);
					
					if((ch>'a' &&ch<'z' )||(ch>'A'&&ch<'Z') ){
						
						prefixCode+=String.valueOf(ch);
					}
				}

				List<QrCodeInfo> QrCodeInfoList = blindSampleInfoService.getQrCodeInfo();
				
				for(QrCodeInfo qrCodeInfo : QrCodeInfoList) {
										
					if(!qrCodeInfo.getPrefix().equals(prefixCode)) {
						
						continue;	
				
					}else {
						
						prefix = true;
						resultQr = qrCodeInfo;

						break;
					}
				}
				
				//前缀名不符合
				if(!prefix) {
					
					result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.QR_ERROR_CODE);
					result.put(APPCONTROLLER_MESSAGE_FILED, Message.QRCODE_FOMATE_ERROR_FAILED);
					return qrCodeFlag;
				}
				
		         //第二步看区间编号
				String code = qrCode.substring(resultQr.getPrefix().length());
				// 正数验证规则
			    String regEx = "[0-9]*";
			    // 编译正则表达式
			    Pattern pattern = Pattern.compile(regEx);
			    // 忽略大小写的写法
			    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
			    Matcher matcher = pattern.matcher(code);
			    // 字符串是否与正则表达式相匹配
			    boolean rs = matcher.matches();
			    //除了前缀之外剩下全是数字
			    if(rs) {
			    	
			      if(code.length()==resultQr.getStartCode().length()) {
			    	  
			    	  Long codeStr = Long.valueOf(code);
			    	  Long startStr = Long.valueOf(resultQr.getStartCode());
			    	  Long endStr = Long.valueOf(resultQr.getEndCode());
			    	  
			    	  if(codeStr.longValue()>=startStr.longValue()&&codeStr.longValue()<endStr.longValue()) {
			  	    	//编码格式所有校验成功  
					    	Map<String,Object> param = new HashMap<String,Object>();
							param.put("QRCode", qrCode);
							
								List<BlindSampleInfo> blindSampleInfoList = blindSampleInfoService.getBlindSampleInfo(param);
								
								if(blindSampleInfoList==null||blindSampleInfoList.size()==0) {
									
									qrCodeFlag = true;
								}else {
									//二维码已存在
									result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.QR_ERROR_CODE);
									result.put(APPCONTROLLER_MESSAGE_FILED, Message.QRCODE_ALEARDY_EXIST_FAILED);
								}
			    	  }
			    	  else {
			    		     result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.QR_ERROR_CODE);
				 			 result.put(APPCONTROLLER_MESSAGE_FILED, Message.QRCODE_BETWEEN_ERROR_FAILED);
				 			 
				 			return qrCodeFlag;
			    	  }
			    	

			       }else {

						result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.QR_ERROR_CODE);
						result.put(APPCONTROLLER_MESSAGE_FILED, Message.QRCODE_FOMATE_ERROR_FAILED);
			       }
			    }
			    else {
			    	
					result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.QR_ERROR_CODE);
					result.put(APPCONTROLLER_MESSAGE_FILED, Message.QRCODE_FOMATE_ERROR_FAILED);
			    }
				
			} catch (BusinessException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return qrCodeFlag;
			
		}
		
		
		   *   盲样信息表查询
		 * tongn
		 * 2018.7.16
		 
		@RequestMapping(Urls.BLINDSAMPLEDELECTION_APP_GET_BLINDSAMPLEINFO)
		public @ResponseBody Map<String,Object>  getBlindSampleInfo(String status,Integer cursor) {
			
			long startTime=System.currentTimeMillis();   //获取开始时间
			Map<String,Object> result = new HashMap<String,Object>();

			try {
			
				Map<String,Object> param = new HashMap<String,Object>();
				if(!("2".equals(status))) {
					
				   param.put("status", status);
				}
				
				List<BlindSampleInfo> blindSampleInfoList = blindSampleInfoService.getBlindSampleInfo(param);
				
				if(blindSampleInfoList!=null&&blindSampleInfoList.size()>0) {
					
					blindSampleInfoList = PageInfoUtil.pagination(cursor, 20, blindSampleInfoList);
					
                    for(BlindSampleInfo blindSampleInfo : blindSampleInfoList) {
                    	
                    	if(blindSampleInfo.getDetailId() == null||blindSampleInfo.getDetailId()==0) {
                    	
                    		blindSampleInfo.setEntrystatus("未录入");
                    	}else {
                    		blindSampleInfo.setEntrystatus("已录入");
                    	}
                    }
					
					result.put(APPCONTROLLER_BLINDSAMPLEINFOLIST_FILED, blindSampleInfoList);
				}
				else {
					
					result.put(APPCONTROLLER_BLINDSAMPLEINFOLIST_FILED, new ArrayList<BlindSampleInfo>());
					
				}
				
				result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SUCCESS_CODE);
				result.put(APPCONTROLLER_MESSAGE_FILED, Message.SUCCESSFUL_OPERATION);
	
			} catch (BusinessException e) {
				
				result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SERVER_ERROR_CODE);
			    result.put(APPCONTROLLER_MESSAGE_FILED, Message.SERVER_ERROR_OPERATION);
			    
				e.printStackTrace();
			}
			long endTime=System.currentTimeMillis(); //获取结束时间			 
			System.out.println("盲样信息查询程序运行时间： "+(endTime-startTime)+"ms");				
			return result;

		}
		
		
		
		
		   *   盲样明细信息表查询
		 * tongn
		 * 2018.7.16
		 
		@RequestMapping(Urls.BLINDSAMPLEDELECTION_APP_BLINDSAMPLEDETAILED)
		public @ResponseBody Map<String,Object>  getBlindSampleInfo(Integer id,String Operator) {
			long startTime=System.currentTimeMillis();   //获取开始时间
			Map<String,Object> result = new HashMap<String,Object>();

			try {
			
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("id", id);
				param.put("Operator", Operator);
				
				List<BlindSampDetailed> blindSampDetailedList = blindSampleInfoService.blindSampDetailed(param);
				
				if(blindSampDetailedList!=null&&blindSampDetailedList.size()>0) {
					
										
					result.put("blindSampDetailedList", blindSampDetailedList);
				}
				else {
					
					result.put("blindSampDetailedList", new ArrayList<BlindSampDetailed>());
					
				}
				
				result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SUCCESS_CODE);
				result.put(APPCONTROLLER_MESSAGE_FILED, Message.SUCCESSFUL_OPERATION);
	
			} catch (BusinessException e) {
				
				result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SERVER_ERROR_CODE);
			    result.put(APPCONTROLLER_MESSAGE_FILED, Message.SERVER_ERROR_OPERATION);
			    
				e.printStackTrace();
			}
			
			long endTime=System.currentTimeMillis(); //获取结束时间			 
			System.out.println("盲样明细信息查询程序运行时间： "+(endTime-startTime)+"ms");	
			return result;

		}
		
		
		
		
		
		
		 * 盲样明细添加
		 * tongn
		 * 2018.7.16
		 
		@RequestMapping(Urls.BLINDSAMPLEDELECTION_APP_ADD_BLINDSAMPLEDETAILED)//@RequestParam Map<String, Object> map
		public @ResponseBody Map<String,Object> addBlindSampDetailed(@RequestParam Map<String, String> map) {
			long startTime=System.currentTimeMillis();   //获取开始时间
			Map<String,Object> result = new HashMap<String,Object>();
			boolean isQualified	= true;
			
			try {
								    
					List<BlindSampDetailed> blindSampDetailedList = JSONArray.toList(JSONArray.fromObject(map.get("list").toString()), BlindSampDetailed.class);
					
					String operator  = map.get("operator");
					String targetDate = map.get("targetDate");
					//List<Integer> indexList = new ArrayList<Integer>();
					//int index = 0;
					List<BlindSampDetailed> resultList = new ArrayList<BlindSampDetailed>();
					
					for(BlindSampDetailed blindSampDetailed:blindSampDetailedList) {
						
						if(blindSampDetailed.getDetectionProject()==null||"".equals(blindSampDetailed.getDetectionProject())) {
							//indexList.add(index);
						}else {
							
							blindSampDetailed.setOperator(operator);
							resultList.add(blindSampDetailed);
						}
						  //index++;
					    }
					blindSampDetailedList = resultList;
					
				    Map<String,Object> param = new HashMap<String,Object>();
				    param.put("id", blindSampDetailedList.get(0).getBlindSampleInfoId());
				    
				    //删除盲样信息id
				    blindSampleInfoService.deleteBlindSampDetailed(param);
				
					//插入盲样信息表
					blindSampleInfoService.addBlindSampDetailed(blindSampDetailedList);
										
					for(BlindSampDetailed blindSampDetailed : blindSampDetailedList) {
						
						if(!"合格".equals(blindSampDetailed.getResultDetermination())) {
							
							  isQualified = false;
							  
							  Map<String,Object> param1 = new HashMap<String,Object>();
							  param1.put("id", blindSampDetailed.getBlindSampleInfoId());
							  param1.put("isQualifiedDate", 0);
							  param1.put("targetDate", targetDate);
							  
						      blindSampleInfoService.updateblindSampleInfo(param1);
						      
						      break;
						}
					}
					
					//全合格
					if(isQualified) {
						
						  Map<String,Object> param1 = new HashMap<String,Object>();
						  param1.put("id", blindSampDetailedList.get(0).getBlindSampleInfoId());
						  param1.put("isQualifiedDate", 1);
						  param1.put("targetDate", targetDate);
						  
					      blindSampleInfoService.updateblindSampleInfo(param1);
					}
									 
					 result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SUCCESS_CODE);
					 result.put(APPCONTROLLER_MESSAGE_FILED, Message.SUCCESSFUL_OPERATION);
	
			} catch (BusinessException e) {
								
				result.put(APPCONTROLLER_MESSAGECODE_FILED, Message.SERVER_ERROR_CODE);
			    result.put(APPCONTROLLER_MESSAGE_FILED, Message.SERVER_ERROR_OPERATION);
			    
				e.printStackTrace();
			}
			
			long endTime=System.currentTimeMillis(); //获取结束时间			 
			System.out.println("管理处程序运行时间： "+(endTime-startTime)+"ms");					
			return result;

		}
		
		
		public static Date getNowDate(String str) throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(str);
			return date;
	}

*/
}
