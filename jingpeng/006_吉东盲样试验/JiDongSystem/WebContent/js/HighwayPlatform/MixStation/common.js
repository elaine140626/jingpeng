//获取项目名称
function getProjectName() {
	$.ajax({
		type: "get",
		url: "../../js/set.json",
		async: true,
		dataType: 'json',
		success: function(data) {
			$(".topleft").html(data.projectName);
			$("head title").html(data.projectName);
		}
	});
}

//获取用户名
function getUserName() {
	$.ajax({
		type: "",
		url: "",
		async: true,
		dataType: 'json',
		success: function(data) {
			$(".userid").html(data);
		}
	});
}

//截取url字符串
function URL_Request(strName) {
	var strHref = document.location.toString();
	var intPos = strHref.indexOf("?");
	var strRight = strHref.substr(intPos + 1); //==========获取到右边的参数部分
	var arrTmp = strRight.split("&"); //=============以&分割成数组

	for(var i = 0; i < arrTmp.length; i++) //===========循环数组
	{
		var dIntPos = arrTmp[i].indexOf("=");
		var paraName = arrTmp[i].substr(0, dIntPos);
		var paraData = arrTmp[i].substr(dIntPos + 1);
		if(paraName.toUpperCase() == strName.toUpperCase()) {
			return paraData;
		}
	}
	return "";
}

//获取前页面参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if(r != null){
        return decodeURIComponent(r[2]);
//这里为什么是从第三个字符解析呢？不知道这样理解对不对，因为路径后面的参数形式为参数名=参数值，而第一个字符为参数名，第二个为=，第三个就为参数值了。。。因为下面调用的时候得出的就是参数值
    }
    return null;//返回参数值
}

//百分数计算
function percentNum(dividend, divisor) { 
	if(divisor != 0){
		return (Math.round(dividend / divisor * 10000) / 100.00 + "%"); // 小数点后两位百分比
	}else{
		return "0%";
	}	
}

//session超时
$(function(){
	$.ajaxSetup({ 
	    statusCode: {
	        401: function() { 
	            window.location.href="../../page/centerLogin/centerLogin.html"
	        }, 
	        403: function() { 
	        	window.location.href="../../page/centerLogin/centerLogin.html"
	        }
	    }
	})
});

// 区间时间段内的天
function getDayAll(begin,end){
	var dateArry = [];
	var bd = new Date(begin), be = new Date(end);
	var bd_time = bd.getTime(), be_time = be.getTime(), time_diff = be_time - bd_time;
	for (var i = 0; i <= time_diff; i += 86400000) {
		var ds = new Date(bd_time + i);
		var year =ds.getYear()+"";
		var years ='20'+ year.slice(1);
		var month = ds.getMonth() + 1;
		var day = ds.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		dateArry.push(years + "-"+month + '-' + day);
	}
	return dateArry;
}

//区间时间段内的月
function getMonthAll(begin,end) {
    var d1 = begin;
    var d2 = end;
    var dateArry = [];
    var s1 = d1.split("-");
    var s2 = d2.split("-");
    var mCount = 0;
    if (parseInt(s1[0]) < parseInt(s2[0])) {
        mCount = (parseInt(s2[0]) - parseInt(s1[0])) * 12 + parseInt(s2[1]) - parseInt(s1[1])+1;
    } else {
        mCount = parseInt(s2[1]) - parseInt(s1[1])+1;
    }
    if (mCount > 0) {
        var startM = parseInt(s1[1]);
        var startY = parseInt(s1[0]);
        for (var i = 0; i < mCount; i++) {
            if (startM < 12) {
                dateArry.push(startY + "-" + (startM>9 ? startM : "0" + startM));
                startM += 1;
            } else {
                dateArry.push(startY + "-" + (startM > 9 ? startM : "0" + startM));
                startM = 1;
                startY += 1;
            }
        }
    }
    return dateArry;
}

// 区间时间段内的年
function getYearAll(begin,end) {
    var d1 = begin;
    var d2 = end;
    var dateArry = [];
    var s1 = d1.split("-");
    var s2 = d2.split("-");
    var mYearCount = parseInt(s2[0]) - parseInt(s1[0])+1;
    var startY = parseInt(s1[0]);
    for (var i = 0; i < mYearCount;i++) {
        dateArry.push(startY);
        startY += 1;
    }
    return dateArry;
}
