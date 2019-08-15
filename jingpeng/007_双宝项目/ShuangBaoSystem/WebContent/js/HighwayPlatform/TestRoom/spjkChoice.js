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
	baseUrl = getContextPath();

});

function JpisShow(e,row) {
	$(e).removeAttr('onclick');

	getToken();
	$(e).find(".getJpNameAll"+row).show();
}

function getToken() {
	var params = {
		"appKey" : "db22c9f57ec84651b3d73f6fb6cd059d",
		"appSecret" : "768602a8cea4c08f37ea21c5110149c3"
	};
	videoParams.AppKey = 'db22c9f57ec84651b3d73f6fb6cd059d';
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
						if(b.status = 0){
							var str = '<option value="' + b.deviceSerial + '">'
							+ b.deviceName + '    (设备不在线)</option>';
						}else{
						var str = '<option value="' + b.deviceSerial + '">'
								+ b.deviceName + '</option>';
						}
						$('.getJpName').append(str);
					}
				});
			} else {
				$.each(monitorAll.data, function(i, v) {
					if(v.status = 0){
						var str = '<option value="' + v.deviceSerial + '">'
						+ v.deviceName + '    (设备不在线)</option>';
					}else{
						var str = '<option value="' + v.deviceSerial + '">'
						+ v.deviceName + '</option>';
					}
					$('.getJpName').append(str);

				})
			}

		},
		error : function(e) {
			alert("1111111111--------" + e)
		}
	});
}
function addJp(e,row) {
	var deviceSerialAll = $(".JpList").children();
	var code = $(".getJpName").val();
	if (code != -1) {
		var deviceSerial = code + ":1";
		var addJpParams = {
			"accessToken" : Token,
			"source" : deviceSerial,
		};
/*		//单个设备的状态
		$.ajax({
			url : "../../FluorescentCloud/getDeviceInfo.action?random="
					+ new Date().getTime(),
			async : false,
			data : addJpParams,
			dataType : "json",
			success : function(data) {
				if(data.data.isEncrypt == 1){
					alert("该设备已开启视频加密，无法开启直播.请前往萤石云客户端进行解密")
					return;
				}
				
			}
		})*/
		$.ajax({
					url : "../../FluorescentCloud/getMonitorUrl.action?random="
							+ new Date().getTime(),
					async : false,
					data : addJpParams,
					dataType : "json",
					success : function(data) {
						//var addObj = JSON.parse(data);
						var addObj = data;
						videoId = addObj.data[0].deviceSerial;
						dataPanelId = "dataPanel" + videoId;
						showPanelId = "showPanel" + videoId;
						isVideoAdd.push(videoId);
						var url = addObj.data[0].hls;
						var vhtml = [
							"<div name=\"" + videoId + "\">",
							"<video class=\"video\" id=\""+ videoId+ "\" poster=\"\" controls playsInline webkit-playsinline autoplay>",
							"<source src=\"" + url + "\" type=\"rtmp/flv\" />",
							"</video>"]
							.join("");
						$(e).parents(".getJpNameAll"+row).siblings(".JpList").append(vhtml);
						new EZUIPlayer(videoId);
						$('.cannot').removeAttr('onclick').addClass('disClick');
						$(".getJpNameAll"+row).remove();
					}
		})
	}else {
		alert('请选择设备！')
	}
}