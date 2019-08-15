//session超时
$(function(){
	$.ajaxSetup({	
	    statusCode: {
	        401: function() { 
	        	parent.location.href="../user/login.html";
	        }, 
	        403: function() { 
	        	parent.location.href="../user/login.html";
	        }
	    }
	})
});

/**
 * 获得url
 * @returns
 */
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = window.location.protocol + "//" + window.location.host
			+ pathName.substr(0, index + 1);
	return result;
}

/**
 * 表单转json
 * @param form
 * @returns
 */
function formToJson(form) {
	var o = {};
	var a = form.serializeArray();
	$.each(a, function() {
		if (o[this.name] != undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || "");
		} else {
			o[this.name] = this.value || "";
		}

	});
	return o;
}
//相差一个月
function getPreMonth(date) {
	var arr = date.split('-');
	var year = arr[0]; //获取当前日期的年份
	var month = arr[1]; //获取当前日期的月份
	var day = arr[2]; //获取当前日期的日
	var days = new Date(year, month, 0);
	days = days.getDate(); //获取当前日期中月的天数
	var year2 = year;
	var month2 = parseInt(month) - 1;
	if (month2 == 0) {
		year2 = parseInt(year2) - 1;
		month2 = 12;
	}
	var day2 = day;
	var days2 = new Date(year2, month2, 0);
	days2 = days2.getDate();
	if (day2 > days2) {
		day2 = days2;
	}
	if (month2 < 10) {
		month2 = '0' + month2;
	}
	var t2 = year2 + '-' + month2 + '-' + day2;
	return t2;
}
//相差三天
function getPreMonths(date) {
	var arr = date.split('-');
	var year = arr[0]; //获取当前日期的年份
	var month = arr[1]; //获取当前日期的月份
	var day = arr[2]; //获取当前日期的日
	var days = new Date(year, month, 0);
	days = days.getDate(); //获取当前日期中月的天数

	var days1 = new Date(year, month - 1, 0);
	days1 = days1.getDate(); //获取上个月的天数
	var year2 = year;
	var month2 = parseInt(month);
	var day2 = parseInt(day) - 3;

	if (day2 <= 0) {
		month2 = parseInt(month) - 1;
		if (month2 == 0) {
			year2 = parseInt(year2) - 1;
			month2 = 12;
		}
		day2 = days1 - (day2 * -1);
	}

	if (month2 < 9) {
		month2 = '0' + month2;
	}
	if (day2 < 9) {
		day2 = '0' + day2;
	}
	var t2 = year2 + '-' + month2 + '-' + day2;
	return t2;
}
//结束时间补0
function getDatePosition(date) {
	var arr = date.split('-');
	var year = arr[0]; //获取当前日期的年份
	var month = arr[1]; //获取当前日期的月份
	var day = arr[2]; //获取当前日期的日

	if (month < 10) {
		month = '0' + month;
	}
	if (day < 10) {
		day = '0' + day;
	}
	var t2 = year + '-' + month + '-' + day;
	return t2;
}

//料仓对应验证
function SiloVerification(cc1, cc2, cc3, cc4, allIds) {
	//定义数组接收
	var c1 = [];
	var c2 = [];
	var c3 = [];
	var c4 = [];
	//遍历变量数组
	var c5 = [];
	//所有材料id的数组
	var c6 = [];

	//接收去重后的数组
	c1 = DuplicateRemoval(cc1);
	c2 = DuplicateRemoval(cc2);
	c3 = DuplicateRemoval(cc3);
	c4 = DuplicateRemoval(cc4);

	c6 = allIds;
	for (var i = 0; i < 3; i++) {
		if (i == 0) {
			c5 = c2;
		}
		if (i == 1) {
			c5 = c3;
		}
		if (i == 2) {
			c5 = c4;
		}
		//合并数组
		for (var j = 0; j < c5.length; j++) {
			c1[c1.length] = c5[j];
		}
	}
	//数组排序
	var nary = c1.sort();
	var nary1 = allIds.sort();
	//循环判断数组中是否有重复的值
	for (var i = 0; i < c1.length; i++) {
		if (nary[i] == nary[i + 1]) {
			return 0;
		}
	}
	//如果选择完施工配比编号，没有在仓内录入信息
	if (c1.length < 1) {
		return 1
	}

	if (nary.toString() != nary1.toString()) {
		return 2;
	}
}
//去掉重复
function DuplicateRemoval(ar) {
	var end;//临时变量用于对比重复元素
	ar.sort();//将数重新组排序
	end = ar[0];
	for (var i = 1; i < ar.length; i++) {
		if (ar[i] == end) {//当前元素如果和临时元素相等则将此元素从数组中删除
			ar.splice(i, 1);
			i--;
		} else {
			end = ar[i];
		}
	}
	return ar;
}

// 输入框只能输入数字
function onlyNumber(data) {
	if (data.value.length == 1) {
		data.value = data.value.replace(/[^1-9]/g, '')
	} else {
		data.value = data.value.replace(/\D/g, '')
		data.value= data.value.replace(/[^\w\.\/]/ig,'');
	}
}
function onlyNumberAfter(data) {
	if (this.value.length == 1) {
		this.value = this.value.replace(/[^1-9]/g, '0')
	} else {
		this.value = this.value.replace(/\D/g, '')
		this.value= this.value.replace(/[^\w\.\/]/ig,'');
	}
}

// 获取当前登录用户的信息
function getUserInfo(){
	var userInfo = '';
	$.ajax({
		type: "post",
		url: "../../login/getUser.action",
		data:{},
		dataType: "json",
		async: false,
		success: function (data) {
			userInfo = data;
		}
	});
	return userInfo;
}

//获取前页面参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null) {
		return decodeURIComponent(r[2]);
		// 这里为什么是从第三个字符解析呢？不知道这样理解对不对，因为路径后面的参数形式为参数名=参数值，而第一个字符为参数名，第二个为=，第三个就为参数值了。。。因为下面调用的时候得出的就是参数值
	}
	return null;// 返回参数值
}

//根据前缀区分获取对应的前缀号码
function getContractInfoPrefix(types){
	var prefixs;
	$.ajax({
		type : "POST",
		url : "../..//prefix/getContractInfoPrefix.action",
		async:false,
		data : {"types":types},
		dataType : "json",
		success : function(data) {
			prefixs = data.prefixs;
		}
	});
	return prefixs;
}

//根据添加成功的前缀区分修改流水号加+1
function updateContractInfoPrefix(types){
	
	$.ajax({
		type : "POST",
		url : "../..//prefix/updateContractInfoPrefix.action",
		async:false,
		data : {"types":types},
		dataType : "json",
		success : function(data) {
		}
	});
}

//获取数据字典(下拉列表用--默认请选择)
function getDataDictionarys(type){
	var DataDictionaryInfo;
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getDataDictionary.action",
		async:false,
		data : {Type:type},
		dataType : "json",
		success : function(data) {
			DataDictionaryInfo = data;
		}
	});
	return DataDictionaryInfo;
}

// 获取数据字典(下拉列表用--默认请选择)
function getDataDictionary(type){
	var DataDictionaryInfo;
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getDataDictionary.action",
		async:false,
		data : {Type:type},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].Code + "'>"
				+ data[i].Content + "</option>"							
			}
			DataDictionaryInfo = html;
		}
	});
	return DataDictionaryInfo;
} 

//获取数据字典(下拉列表用--默认请选择)
function getDataDictionaryMultiple(type){
	var DataDictionaryInfo;
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getDataDictionary.action",
		async:false,
		data : {Type:type},
		dataType : "json",
		success : function(data) {
			var html = "<optgroup label='请选择'> ";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].Code + "'>"
				+ data[i].Content + "</option>"							
			}
			html+="</optgroup>";
			DataDictionaryInfo = html;
		}
	});
	return DataDictionaryInfo;
}

//获取数据字典(下拉列表用--默认0)
function getDataDictionaryDefaultNum(type){
	var DataDictionaryDefaultNumInfo;
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getDataDictionary.action",
		async:false,
		data : {Type:type},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>0</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].Code + "'>"
				+ data[i].Content + "</option>"							
			}
			DataDictionaryDefaultNumInfo = html;
		}
	});
	return DataDictionaryDefaultNumInfo;
} 

//获取数据字典(下拉列表用--默认0)
function getDataDictionaryDefaultNums(type){
	var DataDictionaryDefaultNumInfo;
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getDataDictionary.action",
		async:false,
		data : {Type:type},
		dataType : "json",
		success : function(data) {
			DataDictionaryDefaultNumInfo = data;
		}
	});
	return DataDictionaryDefaultNumInfo;
}

//客户名称下拉列表
function getCustomerName(){
	var CustomerNameInfo;
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getCustomerName.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].Id + "'>"
				+ data[i].CustomerCode + "</option>"		
			}
			CustomerNameInfo = html;
		}
	});
	return CustomerNameInfo;
}


//销售员下拉列表
function getSalesName(){
	var SalesNameInfo;
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getSalesName.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].id + "'>"
				+ data[i].name + "</option>"		
			}
			SalesNameInfo = html;
		}
	});
	return SalesNameInfo;
}

//销售公司表下拉
function getSalesCompany(){
	var SalesCompanyInfo;
	$.ajax({
		type : "POST",
		url : "../../salesCompany/getSalesCompanyName.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].id + "'>"
				+ data[i].companyName + "</option>"		
			}
			SalesCompanyInfo = html;
		}
	});
	return SalesCompanyInfo;
}

function getTestreportName(){
	var TestreportNameInfo;
	$.ajax({
		type : "POST",
		url : "../../testreportsaledetailed/getTestreportName.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].id + "'>"
				+ data[i].userName + "</option>"		
			}
			TestreportNameInfo = html;
		}
	});
	return TestreportNameInfo;
}

function getTestreportTelephone(){
	var TestreportTelephone;
	$.ajax({
		type : "POST",
		url : "../../testreportsaledetailed/getTestreportName.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].id + "'>"
				+ data[i].telephone + "</option>"		
			}
			TestreportTelephone = html;
		}
	});
	return TestreportTelephone;
}

function getTestreport(){
	var Testreport;
	$.ajax({
		type : "POST",
		url : "../../testreportsaledetailed/getTestreportNameInfo.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			Testreport = data;
		}
	});
	return Testreport;
}


//function getTime(){	
//	return  Math.round(new Date().getTime()/1000).toString();	
//}
//
//
//function getRandomNumber() {
//	var rand = parseInt(Math.random() * (99) + 10);
//	return rand  	
//}

// 获取系统当前时间
function getNowDay(){
//	if(flg == 0){
	//获取当前日期
	var date = new Date(); 
//	}
//	else{
//		//获取30天前的日期
//		var myDate = new Date();
//		var date = new Date(myDate - 1000 * 60 * 60 * 24 * 30);
//	}
  
//修改于2018/11/02 修改人:刘江   有问题联系
    var seperator1 = "-";
    var seperator2 = ":";
    var seperator3 = " ";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();       //获取当前小时数(0-23)
    var minutes = date.getMinutes();     //获取当前分钟数(0-59)
    var seconds = date.getSeconds();     //获取当前秒数(0-59)
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hours >= 0 && hours <= 9) {
    	hours = "0" + hours;
    }
    if (minutes >= 0 && minutes <= 9) {
    	minutes = "0" + minutes;
    }
    if (seconds >= 0 && seconds <= 9) {
    	seconds = "0" + seconds;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate + seperator3 + hours + seperator2 + minutes + seperator2 + seconds;
    return currentdate;
}

//只能入力数字和小数点  
function keepNumOrDecimal(obj){ 
obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数 
if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
    obj.value= parseFloat(obj.value); 
}  
} 

//最后一位不是小数点
function checkPoint(obj){
	keepNumOrDecimal(obj);
	if (obj.value.charAt(obj.value.length - 1)=='.'){
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}		
}


//获取当前时间 yyyy-MM-dd
function getNowFormatDate() {
 var date = new Date();
 var seperator1 = "-";
 var year = date.getFullYear();
 var month = date.getMonth() + 1;
 var strDate = date.getDate();
 if (month >= 1 && month <= 9) {
     month = "0" + month;
 }
 if (strDate >= 0 && strDate <= 9) {
     strDate = "0" + strDate;
 }
 var currentdate = year + seperator1 + month + seperator1 + strDate;
 return currentdate;
}


//切换显示备注信息，显示部分或者全部
function changeShowRemarks(obj){//obj是td
    var content = $(obj).attr("content");
    if(content != null && content != ''){
        if($(obj).attr("isDetail") == 'true'){//当前显示的是详细备注，切换到显示部分
            //$(obj).removeAttr('isDetail');//remove也可以
            $(obj).attr('isDetail',false);
            $(obj).html(getPartialRemarksHtml(content));
        }else{//当前显示的是部分备注信息，切换到显示全部
            $(obj).attr('isDetail',true);
            $(obj).attr('title',content);
           // $(obj).html(getTotalRemarksHtml(content));
        }
    }
}
//部分备注信息
function getPartialRemarksHtml(remarks){
    return remarks.substr(0,10) + '&nbsp;&nbsp;<b>...</b>';
}
//单价
function checkPrice(obj){ 
obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数 
obj.value = obj.value.replace(/^[0-9]{6}/g,obj.value.substring(0,5));
if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
    if(obj.value.length > 5){
    	obj.value = obj.value.substring(0,5);
    }else{
    	obj.value= parseFloat(obj.value);
    }
} 
} 

function checkPriceSum(obj){ 
	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数 
	}

//数量
function checkNumber(obj){ 
obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数 
obj.value = obj.value.replace(/^[0-9]{8}/g,obj.value.substring(0,7));
if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
    if(obj.value.length > 7){
    	obj.value = obj.value.substring(0,7);
    }else{
    	obj.value= parseFloat(obj.value);
    }
}
}

//去除空格
function trimStr(str){
	
	var valTr = str.value.replace(/(^\s*)|(\s*$)/g,"");
	str.value = valTr	
}


//随机数生成
function createRandom2(num , from , to)
{
    var arr=[];
    var json={};
    while(arr.length<num)
    {
        //产生单个随机数
        var ranNum=Math.ceil(Math.random()*(to-from))+from;
        //通过判断json对象的索引值是否存在 来标记 是否重复
        if(!json[ranNum])
        {
            json[ranNum]=1;
            arr.push(ranNum);
        }
         
    }
    return arr;
     
     
}


function day(e){  //keyup事件处理 
    e.value = e.value.replace(/\D|^0/g,'');  
}


//供应商下拉
function getSupplier(){
	var SupplierInfo;
	$.ajax({
		type : "POST",
		url : "../../Purchase/getSupplier.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			if(data!=null && data !=""){
				SupplierInfo = data.data;
			}
		}
	});
	return SupplierInfo;
}


//数字decimal类型check(integer:整数位；decimal：小数位)(onkeyup)
function clearNoDecimal(obj,integer,decimal){  			
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的   
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
    // 小数位check
    if (decimal == 1){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入一个小数   
    }
    if (decimal == 2){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数   
    }	
    if (decimal == 3){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d).*$/,'$1$2.$3');//只能输入三个小数   
    }	
    if (decimal == 4){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/,'$1$2.$3');//只能输入四个小数   
    }	
    if (obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额  
    	// 整数位check
    	if(obj.value.length > integer){
	    	obj.value = obj.value.substring(0,integer);
	    }else{
	    	obj.value= parseFloat(obj.value);
	    } 
    }  
}

// 失去焦点时check最后一位不是小数点(onblur)
function checkPoint(obj){
	clearNoDecimal(obj);
	if (obj.value.charAt(obj.value.length - 1)=='.'){			
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}		
}

// 整数check
function clearNoInteger(obj){
	obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”以外的字符  	 
	obj.value= parseFloat(obj.value);// 首位不能为类似于 01、02的金额 
}	

