var Token;
var baseUrl = "";
var isVideoAdd = new Array();
var videoParams = {};
var videoId;
var dataPanelId;
var showPanelId;
var ua;
var browserType = null;
$(function() {
	ua = navigator.userAgent.toLocaleLowerCase();
	$(".videoContain").hide();
	baseUrl = getContextPath();
	if (ua.match(/msie/) != null || ua.match(/trident/) != null) {
		browserType = "IE";
		browserVersion = ua.match(/msie ([\d.]+)/) != null ? ua
				.match(/msie ([\d.]+)/)[1] : ua.match(/rv:([\d.]+)/)[1];
		// alert("现在是IE -1")
		TestActiveX();
	} else if (ua.match(/chrome/) != null) {
		// alert("现在是chrome -1")
	}
	/*
	 * params = { "appKey": "7310dea0af7d4dceb69b84e1c850205e", "appSecret":
	 * "211e320d80534dcd5cd4574244004530" };
	 */

});

function JpisShow(e) {
	$(e).removeAttr('onclick');

	getToken();
	$(e).find(".getJpNameAll").show();
}

/*"appKey" : "7310dea0af7d4dceb69b84e1c850205e",
"appSecret" : "211e320d80534dcd5cd4574244004530",    //测试

"appKey" : "92f8c6030760434286de235d8e27e2ba",
"appSecret" : "9b0358e51fe7f42b81f79fbdda02064e"   //有钱的


"appKey" : "bb470fb8505c4518b99689c766332ac3",
"appSecret" : "fc59e820f92c42b02d27b167e57096b9"*/   //客户
function getToken() {
	var params = {
		"appKey" : "bb470fb8505c4518b99689c766332ac3",
		"appSecret" : "fc59e820f92c42b02d27b167e57096b9"
	};
	videoParams.AppKey = 'bb470fb8505c4518b99689c766332ac3';
	$.ajax({
		url : "../../FluorescentCloud/getAccessToken.action?random="
				+ new Date().getTime(),
		async : false,
		data : params,
		dataType : "json",
		success : function(data) {
			//var obj = JSON.parse(data);
			var obj = data;
			Token = obj.data.accessToken;
			videoParams.AccessToken = Token;
			showOption(Token);
		},
		error : function(e) {
			alert(e)
		}
	});
}

function showOption(token) {
	$('.getJpName').html('<option value="-1">请选择</option>');
	$.ajax({
		url : "../../FluorescentCloud/getMonitorAll.action?random="
				+ new Date().getTime(),
		async : false,
		data : {
			"accessToken" : token
		},
		dataType : "json",
		success : function(monitordata) {
			//var monitorAll = JSON.parse(monitordata);
			var monitorAll = monitordata;
			if (isVideoAdd.length > 0) {
				$.each(monitorAll.data, function(index, b) {
					var stesdr = $.inArray(b.deviceSerial, isVideoAdd);
					if (stesdr >= 0) {
					} else {
						var str = '<option value="' + b.deviceSerial + '">'
								+ b.deviceName + '</option>';
						$('.getJpName').append(str);
					}
				});
			} else {
				$.each(monitorAll.data, function(i, v) {
					var str = '<option value="' + v.deviceSerial + '">'
							+ v.deviceName + '</option>';
					$('.getJpName').append(str);

				})
			}

		},
		error : function(e) {
			alert("1111111111--------" + e)
		}
	});
}

function addJp(e) {
	var deviceSerialAll = $(".JpList").children();
	var code = $(".getJpName").val();
	if (code != -1) {
		var deviceSerial = code + ":1";
		var addJpParams = {
			"accessToken" : Token,
			"source" : deviceSerial
		};
		$
				.ajax({
					url : "../../FluorescentCloud/getMonitorUrl.action?random="
							+ new Date().getTime(),
					async : false,
					data : addJpParams,
					dataType : "json",
					success : function(data) {
						//var addObj = JSON.parse(data);
						var addObj = data;
						videoId = addObj.data[0].deviceSerial;
						var isContrl = '';
						var ifContrl = isBall(videoId);
						if (ifContrl) { // 可控制
							isContrl = "can";
						} else { // 不可控制
							isContrl = "cannot";
						}
						dataPanelId = "dataPanel" + videoId;
						showPanelId = "showPanel" + videoId;
						if (ua.match(/msie/) != null
								|| ua.match(/trident/) != null) {
							browserType = "IE";
							browserVersion = ua.match(/msie ([\d.]+)/) != null ? ua
									.match(/msie ([\d.]+)/)[1]
									: ua.match(/rv:([\d.]+)/)[1];
							// alert("现在是IE")
							// if(isHave == -1) {
							var url = addObj.data[0].hls;
							$(".getJpNameAll").hide();
							var vhtml = [
									"<div class=\"video\" name=\"" + videoId
											+ "\">",
									"<object classid=\"clsid:54FC7795-1014-4BF6-8BA3-500C61EC1A05\" id=\""
											+ videoId
											+ "\" width=\"800\" height=\"400\" name=\"EZUIKit\" ></object>",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(0,\'"
											+ videoId
											+ "\')\">上</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(1,\'"
											+ videoId
											+ "\')\">下</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(2,\'"
											+ videoId
											+ "\')\">左</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(3,\'"
											+ videoId
											+ "\')\">右</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(4,\'"
											+ videoId
											+ "\')\">左上</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(5,\'"
											+ videoId
											+ "\')\">左下</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(6,\'"
											+ videoId
											+ "\')\">右上</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(7,\'"
											+ videoId
											+ "\')\">右下</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(8,\'"
											+ videoId
											+ "\')\">放大</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(9,\'"
											+ videoId
											+ "\')\">缩小</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(10,\'"
											+ videoId
											+ "\')\">近焦距</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpcontroller(11,\'"
											+ videoId
											+ "\')\">远焦距</span>&thinsp;",
									"<span class=" + isContrl
											+ " onclick=\"Jpstop(\'" + videoId
											+ "\')\">停</span>&thinsp;",
									"<span onclick=\"JpisDel(this,\'" + videoId
											+ "\')\">移除设备</span>",
									"<textarea rows=8 cols=70 id=\""
											+ dataPanelId
											+ "\" style=\"display: none;\"></textarea>",
									"<textarea rows=8 cols=70 id=\""
											+ showPanelId
											+ "\" value=\"\" style=\"display: none;\"></textarea>",
									"</div>" ].join("");
							;
							$(e).parents(".getJpNameAll").siblings(".JpList")
									.append(vhtml);
							var ezopenUrl = "ezopen://open.ys7.com/" + videoId
									+ "/1.hd.live";
							videoParams.Url = ezopenUrl;
							$("#" + dataPanelId).text(
									JSON.stringify(videoParams))
							isVideoAdd.push(videoId);
							$('.cannot').removeAttr('onclick').addClass(
									'disClick');

							$(".getJpNameAll").remove();
							var str = '<div class="addBtn " onclick="JpisShow(this)"><div class="JpList"></div><div class="getJpNameAll" style="display:none"><select class="getJpName"><option value="-1">请选择</option></select><p class="chooseBtn" onclick="addJp(this)">选择该设备</p></div></div>';
							$('.videoContain').append(str);

							StartPlay(dataPanelId, showPanelId, videoId);
							// } else {
							// alert("你已经添加该设备")
							// }
						} else if (ua.match(/chrome/) != null) {
							// alert("现在是chrome")
							// if(isHave) {
							var url = addObj.data[0].hls;

							var vhtml = [
									"<div name=\"" + videoId + "\">",
									"<video class=\"video\" id=\""
											+ videoId
											+ "\" poster=\"\" controls playsInline webkit-playsinline autoplay>",
									"<source src=\"" + url
											+ "\" type=\"rtmp/flv\" />",
									"</video>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(0,\'"
											+ videoId + "\')\">上</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(1,\'"
											+ videoId + "\')\">下</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(2,\'"
											+ videoId + "\')\">左</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(3,\'"
											+ videoId + "\')\">右</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(4,\'"
											+ videoId + "\')\">左上</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(5,\'"
											+ videoId + "\')\">左下</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(6,\'"
											+ videoId + "\')\">右上</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(7,\'"
											+ videoId + "\')\">右下</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(8,\'"
											+ videoId + "\')\">放大</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(9,\'"
											+ videoId + "\')\">缩小</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(10,\'"
											+ videoId + "\')\">近焦距</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpcontroller(11,\'"
											+ videoId + "\')\">远焦距</p>",
									"<p class=" + isContrl
											+ " onclick=\"Jpstop(\'" + videoId
											+ "\')\">停</p>",
									"<p onclick=\"JpisDel(this,\'" + videoId
											+ "\')\">移除设备</p>", "</div>" ]
									.join("");
							$(e).parents(".getJpNameAll").siblings(".JpList")
									.append(vhtml);
							new EZUIPlayer(videoId);
							isVideoAdd.push(videoId);
							$('.cannot').removeAttr('onclick').addClass(
									'disClick');
							$(".getJpNameAll").remove();
							var str = '<div class="addBtn " onclick="JpisShow(this)"><div class="JpList"></div><div class="getJpNameAll" style="display:none"><select class="getJpName"><option value="-1">请选择</option></select><p class="chooseBtn" onclick="addJp(this)">选择该设备</p></div></div>';
							$('.videoContain').append(str);

							// } else {
							// alert("你已经添加该设备")
							// }
						}

					}
				});
	} else {
		alert('请选择设备！')
	}

}

function Jpcontroller(id, deviceSerial) {

	var params = {
		"accessToken" : Token,
		"deviceSerial" : deviceSerial,
		"channelNo" : 1,
		"direction" : id,
		"speed" : 1
	};
	$.ajax({
		url : "../../FluorescentCloud/controllerJp.action?random="
				+ new Date().getTime(),
		async : false,
		data : params,
		dataType : "json",
		success : function(data) {
			//var obj = JSON.parse(data);
			var obj = data;
			// var code =obj.msg;
			if (obj.code == '60000') {
				alert("不支持云台设备")
			}
		}
	});
}

function Jpstop(deviceSerial) {
	var params = {
		"accessToken" : Token,
		"deviceSerial" : deviceSerial,
		"channelNo" : 1
	};
	$.ajax({
		url : "../../FluorescentCloud/controllerJpStop.action?random="
				+ new Date().getTime(),
		async : false,
		data : params,
		dataType : "json",
		success : function(data) {
			//var obj = JSON.parse(data);
			var obj = data;
			var codeStop = obj.code;
			if (codeStop == 60000) {

			}
		}
	});
}

function isBall(deviceSerial) {
	var flag = true;
	var params = {
		"accessToken" : Token,
		"deviceSerial" : deviceSerial,
		"channelNo" : 1
	};
	$.ajax({
		url : "../../FluorescentCloud/controllerJpStop.action?random="
				+ new Date().getTime(),
		async : false,
		data : params,
		dataType : "json",
		success : function(data) {
			//var obj = JSON.parse(data);
			var obj = data;
			var codeStop = obj.code;
			if (codeStop == 60000 ) {
				flag = false;
			} else if(codeStop == 20007) {
				flag = false;
				alert("设备不在线")
			}else {
				flag = true;
			}

		}
	});
	return flag;
}
function login() {
	var password = $("#JpPassWord").val();
	if (password == 123) {
		$(".videoContain").show();
		$("#isLogin").hide();
	} else {
		alert("密码错误")
	}
}

function JpisDel(e, name) {
	$(e).parents('.addBtn').remove();
	isVideoAdd.splice($.inArray(name, isVideoAdd), 1);
	getToken();
}

function TestActiveX() {
	try {
		var ax = new ActiveXObject("EZOPENUIACTIVEXK.EzOpenUIActiveXKCtrl.1");
		bInstall = true;
		// alert("已安装插件");
	} catch (e) {
		alert("未安装IE Tab插件或activeX插件");
	}
}
var gAppKey; // app key
var gAccessToken; // accesstoken
var gPlaybackSearchRecord; // search record

function StartPlay(dPanelId, sPanelId, vId) {
	gPlaybackSearchRecord = "";
	// 得到控件引用
	var playOcx = document.getElementById(vId);
	if (!playOcx) {
		alert("EZUIKit can't find!");
		return;
	}

	// 获取取流参数
	var streamparams = document.getElementById(dPanelId).value;
	try {
		var streamobj = JSON.parse(streamparams);
	} catch (e) {
		alert("JSON parser failed.")
	}

	if (!streamobj) {
		alert("Get stream params invalid!");
		return;
	}
	var appkey = streamobj.AppKey;
	var accesstoken = streamobj.AccessToken;
	var ezurl = streamobj.Url;
	// 检测取流参数
	if (appkey == "") {
		alert("Appkey is empty!");
		return;
	}
	if (accesstoken == "") {
		alert("Accesstoken is empty!");
		return;
	}
	if (ezurl == "") {
		alert("EzUrl is empty!");
		return;
	}
	// alert(appkey);
	// alert(accesstoken);
	// alert(ezurl);
	// 设置appkey
	// 判断参数是否初始化过
	if (gAppKey != appkey) {
		var res = playOcx.InitWithAppKey(appkey);
		if (0 != res) {
			alert("Init appkey Error!");
			return;
		}
		gAppKey = appkey;
		// alert("Init appkey success.");
	}
	// 设置
	if (gAccessToken != accesstoken) {
		var res = playOcx.SetAccessToken(accesstoken);
		if (0 != res) {
			alert("Init accesstoken Error!");
			return;
		}
		gAccessToken = accesstoken;
		// alert("Init accesstoken success.");
	}
	// 清理播放结果窗口
	var showpanel = document.getElementById(sPanelId); // 获取显示的窗口
	showpanel.value = "";
	// 开始播放, 播放结果 根据 PluginEventHandler 回调函数
	var res = playOcx.StartPlay(ezurl);
	if (0 != res) {
		alert("startplay failed! check ezurl!");
		return;
	}
	// alert("Startplay success.");
}
/*
 * function StopPlay() { //清理播放结果窗口 var showpanel =
 * document.getElementById(showPanelId); //获取显示的窗口 showpanel.value = ""; var
 * playOcx = document.getElementById(videoId);//得到控件引用 var res =
 * playOcx.StopPlay(); if(0 != res) { alert("StopPlay Error！"); } }
 */

// handle message msgtype
var EZUI_MSGID_PLAY_EXCEPTION = 0; // 播放异常
var EZUI_MSGID_PLAY_RECONNECT = 1; // 播放重连
var EZUI_MSGID_PLAY_RECONNECT_EXCEPTION = 2; // 播放重连异常
var EZUI_MSGID_PLAY_START = 3; // 播放开始
var EZUI_MSGID_PLAY_STOP = 4; // 播放停止
var EZUI_MSGID_PLAY_ARCHIVE_END = 5; // 回放结束
var EZUI_MSGID_VOICETALK_START = 16; // 语音对讲开始
var EZUI_MSGID_VOICETALK_STOP = 17; // 语音对讲停止
var EZUI_MSGID_VOICETALK_EXCEPTION = 18; // 语音对讲异常
var EZUI_MSGID_RECORD_FILE = 20; // 查询的录像文件
var EZUI_MSGID_PTZCTRL_SUCCESS = 46; // 云台控制命令发送成功
var EZUI_MSGID_PTZCTRL_FAILED = 47; // 云台控制失败

var EZUI_ERROR_ACCESSTOKEN_EXPIRE = "UE001"; // /<
// accesstoken异常或失效，需要重新获取accesstoken，并传入到sdk
var EZUI_ERROR_APPKEY_TOKEN_NOT_MATCH = "UE002"; // /<
// appkey和AccessToken不匹配,建议更换appkey或者AccessToken
var EZUI_ERROR_CHANNEL_NOT_EXIST = "UE004"; // /< 通道不存在，设备参数错误，建议重新获取播放地址
var EZUI_ERROR_DEVICE_NOT_EXIST = "UE005"; // /< 设备不存在，设备参数错误，建议重新获取播放地址
var EZUI_ERROR_PARAM_INVALID = "UE006"; // /< 参数错误，建议重新获取播放地址
var EZUI_ERROR_EZOPEN_URL_INVALID = "UE007"; // /< 播放地址错误,建议重新获取播放地址
var EZUI_ERROR_NO_RESOURCE = "UE101"; // /< 设备连接数过大，停止其他连接后再试试
var EZUI_ERROR_DEVICE_OFFLINE = "UE102"; // /< 设备不在线，确认设备上线之后重试
var EZUI_ERROR_CONNECT_DEVICE_TIMEOUT = "UE103"; // /<
// 播放失败，请求连接设备超时，检测设备网路连接是否正常.
var EZUI_ERROR_INNER_VERIFYCODE = "UE104"; // /< 视频验证码错误，建议查看设备上标记的验证码
var EZUI_ERROR_PLAY_FAIL = "UE105"; // /< 视频播放失败
var EZUI_ERROR_TERMINAL_BINDING = "UE106"; // /< 当前账号开启了终端绑定，只允许指定设备登录操作
var EZUI_ERROR_DEVICE_INFO_INVALID = "UE107"; // /< 设备信息异常为空，建议重新获取播放地址
var EZUI_ERROR_VIDEO_RECORD_NOTEXIST = "UE108"; // /< 未查找到录像文件
var EZUI_ERROR_VTDU_NO_RESOURCE = "UE109"; // /< 取流并发路数限制,请升级为企业版.
var EZUI_ERROR_UNSUPPORTED = "UE110"; // /< 设备不支持的清晰度类型, 请根据设备预览能力级选择.

function PluginEventHandler(lEventType, strErrorCode, lInterErrorCode) {

	var showpanel = document.getElementById(showPanelId); // 获取显示的窗口
	switch (lEventType) {
	case EZUI_MSGID_PLAY_START: // 播放开始
	{
		var info;
		if (gPlaybackSearchRecord != "") {
			info = "回放成功!" + gPlaybackSearchRecord;
		} else {
			info = "播放成功!";
		}
		// showpanel.value = info;
		// alert(info)
		/*
		 * document.getElementById('startplaybtn').style.display='none';
		 * document.getElementById('stopplaybtn').style.display='block';
		 * document.getElementById('starttalkbtn').style.display='block';
		 * document.getElementById('stoptalkbtn').style.display='none';
		 * document.getElementById('ptzupbtn').style.display='block';
		 * document.getElementById('ptzdownbtn').style.display='block';
		 * document.getElementById('ptzleftbtn').style.display='block';
		 * document.getElementById('ptzrightbtn').style.display='block';
		 */
	}
		break;
	case EZUI_MSGID_PLAY_EXCEPTION: // 播放异常
	{
		var errinfo;
		if (strErrorCode == EZUI_ERROR_ACCESSTOKEN_EXPIRE) {
			errinfo = "accesstoken异常或失效，需要重新获取accesstoken，并传入到sdk";
		} else if (strErrorCode == EZUI_ERROR_APPKEY_TOKEN_NOT_MATCH) {
			errinfo = "ppkey和AccessToken不匹配,建议更换appkey或者AccessToken";
		} else if (strErrorCode == EZUI_ERROR_CHANNEL_NOT_EXIST) {
			errinfo = "通道不存在，设备参数错误，建议重新获取播放地址";
		} else if (strErrorCode == EZUI_ERROR_DEVICE_NOT_EXIST) {
			errinfo = "设备不存在，设备参数错误，建议重新获取播放地址";
		} else if (strErrorCode == EZUI_ERROR_PARAM_INVALID) {
			errinfo = "参数错误，建议重新获取播放地址";
		} else if (strErrorCode == EZUI_ERROR_EZOPEN_URL_INVALID) {
			errinfo = "播放地址错误,建议重新获取播放地址";
		} else if (strErrorCode == EZUI_ERROR_NO_RESOURCE) {
			errinfo = "设备连接数过大，停止其他连接后再试试";
		} else if (strErrorCode == EZUI_ERROR_DEVICE_OFFLINE) {
			errinfo = "设备不在线，确认设备上线之后重试";
		} else if (strErrorCode == EZUI_ERROR_CONNECT_DEVICE_TIMEOUT) {
			errinfo = "播放失败，请求连接设备超时，检测设备网路连接是否正常.";
		} else if (strErrorCode == EZUI_ERROR_INNER_VERIFYCODE) {
			errinfo = "视频验证码错误，建议查看设备上标记的验证码";
		} else if (strErrorCode == EZUI_ERROR_PLAY_FAIL) {
			errinfo = "视频播放失败";
		} else if (strErrorCode == EZUI_ERROR_TERMINAL_BINDING) {
			errinfo = "当前账号开启了终端绑定，只允许指定设备登录操作";
		} else if (strErrorCode == EZUI_ERROR_DEVICE_INFO_INVALID) {
			errinfo = "设备信息异常为空，建议重新获取播放地址";
		} else if (strErrorCode == EZUI_ERROR_VIDEO_RECORD_NOTEXIST) {
			errinfo = "未查找到录像文件";
		} else if (strErrorCode == EZUI_ERROR_VTDU_NO_RESOURCE) {
			errinfo = "取流并发路数限制,请升级为企业版.";
		} else if (strErrorCode == EZUI_ERROR_UNSUPPORTED) {
			errinfo = "设备不支持的清晰度类型, 请根据设备预览能力级选择";
		}

		var info = "播放失败," + errinfo + ".错误码:" + strErrorCode + ", 内部错误码:"
				+ lInterErrorCode;
		alert(info)
		// showpanel.value = info;
	}
		break;
	case EZUI_MSGID_PLAY_STOP: // 播放停止
	{
		/*
		 * document.getElementById('startplaybtn').style.display='block';
		 * document.getElementById('stopplaybtn').style.display='none';
		 * document.getElementById('starttalkbtn').style.display='none';
		 * document.getElementById('stoptalkbtn').style.display='none';
		 * document.getElementById('ptzupbtn').style.display='none';
		 * document.getElementById('ptzdownbtn').style.display='none';
		 * document.getElementById('ptzleftbtn').style.display='none';
		 * document.getElementById('ptzrightbtn').style.display='none';
		 */
	}
		break;
	case EZUI_MSGID_RECORD_FILE: // 录像搜索成功
	{
		gPlaybackSearchRecord = "录像搜索成功:" + strErrorCode;
	}
		break;
	case EZUI_MSGID_VOICETALK_START: // 对讲开启
	{
		var info = "对讲开启成功";
		showpanel.value = info;
		document.getElementById('starttalkbtn').style.display = 'none';
		document.getElementById('stoptalkbtn').style.display = 'block';
	}
		break;
	case EZUI_MSGID_VOICETALK_STOP: // 对讲开启
	{
		var info = "对讲停止成功";
		showpanel.value = info;
		document.getElementById('starttalkbtn').style.display = 'block';
		document.getElementById('stoptalkbtn').style.display = 'none';
	}
		break;
	case EZUI_MSGID_PTZCTRL_SUCCESS: // 云台控制成功
	{
		var info = "云台控制信令发送成功";
		showpanel.value = info;
	}
		break;
	case EZUI_MSGID_PTZCTRL_FAILED: // 云台控制失败
	{
		var info = "云台控制失败";
		showpanel.value = info;
	}
		break;
	default:
	}

}