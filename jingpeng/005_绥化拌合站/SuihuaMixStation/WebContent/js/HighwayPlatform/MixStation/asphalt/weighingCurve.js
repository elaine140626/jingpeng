var startTime;
var endTime;
var org_ID;
$(function() {
	/*$("#startTime").hide();
	$("#endTime").hide();*/
	$("#asphalt_search").hide();
	debugger;
	$("#startTime2").val(getNowFormatDate(1));
	$("#endTime2").val(getNowFormatDate(0));
	startTime = $("#startTime2").val();
	endTime = $("#endTime2").val();
	org_ID = $("#orgIds").val();
	if (org_ID.length>0){
		org_ID = org_ID.substr(1,org_ID.length-1)
	}
<<<<<<< .mine
||||||| .r200
	debugger;
	getOrgId()
	getEchar(org_ID);
});

function getOrgId(){
	$("#areaScreening").html("");
	$.ajax({
		type : "POST",
		url : "../../WeighingCurve/getOrganizationInfo.action",
		data : {
			"org_ID":org_ID
			},
		dataType : "json",
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				$("#areaScreening").append(["<a style=\"font-size: 17px;\" onclick='getEchar("+data[i].id+")' href='javascript:;'>"+data[i].orgName+"</a>&nbsp;&nbsp;&nbsp;"].join(""))
				//$("#areaScreening").append(["<input name=\"\" type=\"button\" onclick=\"getEchar("+data[i].id+")\" value=\""+data[i].orgName+"\" class=\"login-button\">"].join(""))
				}
			//$("#areaScreening").append(["<br><input name=\"\" style=\"margin-top: 10px;\" type=\"button\" onclick=\"getEchar('"+org_ID+"')\" value=\"轮播显示\" class=\"login-button\">"].join(""))
			$("#areaScreening").append(["<br><a style=\"font-size: 17px; color: #0080FF;\"   onclick=\"getEchar('"+org_ID+"')\"href='javascript:;'>轮播显示</a>"].join(""))
			}
		})
}

function getEchar(orgId){
	if(orgId == -1){
		org_ID = $("#orgIds").val();
		if (org_ID.length>0){
			org_ID = org_ID.substr(1,org_ID.length-1)
		}
		orgId = org_ID;
		getOrgId()
	}
	$("#loading").show();
	var myChart = echarts.init(document.getElementById('echar'));
	startTime = $("#startTime2").val();
	endTime = $("#endTime2").val();
	if(startTime == '' || endTime == ''){
		swal("操作失败","请输入查询时间", "info");
		$("#loading").hide();
		return;
	}
	if(startTime>endTime){
		swal("操作失败","开始时间大于结束时间!", "info");
		$("#loading").hide();
		return;
	}
	var timeCount = daysBetween(startTime,endTime)
	if(timeCount > 12){
		swal("操作失败","查询间隔不能超过12小时", "info");
		$("#loading").hide();
		return;
	}
=======
	getOrgId()
	getEchar(org_ID);
});

function getOrgId(){
	$("#areaScreening").html("");
	$.ajax({
		type : "POST",
		url : "../../WeighingCurve/getOrganizationInfo.action",
		data : {
			"org_ID":org_ID
			},
		dataType : "json",
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
				$("#areaScreening").append(["<a style=\"font-size: 17px;\" onclick='getEchar("+data[i].id+")' href='javascript:;'>"+data[i].orgName+"</a>&nbsp;&nbsp;&nbsp;"].join(""))
				//$("#areaScreening").append(["<input name=\"\" type=\"button\" onclick=\"getEchar("+data[i].id+")\" value=\""+data[i].orgName+"\" class=\"login-button\">"].join(""))
				}
			//$("#areaScreening").append(["<br><input name=\"\" style=\"margin-top: 10px;\" type=\"button\" onclick=\"getEchar('"+org_ID+"')\" value=\"轮播显示\" class=\"login-button\">"].join(""))
			$("#areaScreening").append(["<br><a style=\"font-size: 17px; color: #0080FF;\"   onclick=\"getEchar('"+org_ID+"')\"href='javascript:;'>轮播显示</a>"].join(""))
			}
		})
}

function getEchar(orgId){
	if(orgId == -1){
		org_ID = $("#orgIds").val();
		if (org_ID.length>0){
			org_ID = org_ID.substr(1,org_ID.length-1)
		}
		orgId = org_ID;
		getOrgId()
	}
	$("#loading").show();
	var myChart = echarts.init(document.getElementById('echar'));
	startTime = $("#startTime2").val();
	endTime = $("#endTime2").val();
	if(startTime == '' || endTime == ''){
		swal("操作失败","请输入查询时间", "info");
		$("#loading").hide();
		return;
	}
	if(startTime>endTime){
		swal("操作失败","开始时间大于结束时间!", "info");
		$("#loading").hide();
		return;
	}
	var timeCount = daysBetween(startTime,endTime)
	if(timeCount > 12){
		swal("操作失败","查询间隔不能超过12小时", "info");
		$("#loading").hide();
		return;
	}
>>>>>>> .r322
	
	//生成初始时间和结束时间时间段的数组(按秒查询)
	var d_arr = [];
	d_arr = getSecondsAll(startTime,endTime);
});

//日期初始化赋值
function getNowFormatDate(flg) {
	if(flg == 0) {
		//获取当前日期
		var date = new Date().Format("yyyy-MM-dd hh:mm:ss");
	} else {
		//获取30天前的日期
		var myDate = new Date().Format("yyyy-MM-dd hh:mm:ss")
		var date = new Date(new Date() - 1000 * 60 * 60 * 24).Format("yyyy-MM-dd hh:mm:ss");
	}
	return date;
}

//对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//区间时间段内的秒
function getSecondsAll(begin,end){
	var dateArry = [];
	var bd = new Date(begin), be = new Date(end);
	var bd_time = bd.getTime(), be_time = be.getTime(), time_diff = be_time - bd_time;
	for (var i = 0; i <= time_diff; i += 1000) {
		var ds = new Date(bd_time + i);
		var year =ds.getYear()+"";
		var years ='20'+ year.slice(1);
		var month = ds.getMonth() + 1;
		var day = ds.getDate();
		var hours = ds.getHours(); //小时 
		var minutes = ds.getMinutes(); //分 
		var seconds = ds.getSeconds(); //秒 
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		if (hours < 10) {
			hours = "0" + hours;
		}
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		if (seconds < 10) {
			seconds = "0" + seconds;
		}
		dateArry.push(years + "-"+month + '-' + day+" "+hours+":"+minutes+":"+seconds);
	}
	return dateArry;
}

jeDate("#startTime2",{
	format: "YYYY-MM-DD hh:mm:ss",
	isinitVal:true,
	isTime: true,
})
jeDate("#endTime2",{
	format: "YYYY-MM-DD hh:mm:ss",
	isinitVal:true,
	isTime: true,
})