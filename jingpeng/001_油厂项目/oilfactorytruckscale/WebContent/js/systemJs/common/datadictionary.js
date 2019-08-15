// 获取当前登录用户信息
function getUserInfo(id){
	var userInfo = "";
	 $.ajax({
			type: 'POST',
			url: '../../UserManage/getUserInfoList.action',
			data: {id:id},
			dataType: 'json',
			async:false,
			success: function(data){
				if(data != ""){
					userInfo = data.data[0];
				}
			}
		});	
	// 用户id
	return userInfo;
}

//获取前页面参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = location.href.substring(location.href.indexOf("?")+1,location.href.length).match(reg); // 匹配目标参数
	if (r != null) {
		return decodeURIComponent(r[2]);
		// 这里为什么是从第三个字符解析呢？不知道这样理解对不对，因为路径后面的参数形式为参数名=参数值，而第一个字符为参数名，第二个为=，第三个就为参数值了。。。因为下面调用的时候得出的就是参数值
	}
	return null;// 返回参数值
}

// 表单转json
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

//form表单赋值方法
function SetFromValues(el, data)
{
    for (var p in data)
    {
        el.find(":input[name='" + p + "']").val(data[p]);
    } 
}

//获取数据字典List
function getDataDictionaryList(type){
	var DataDictionaryDefaultList;
	$.ajax({
		type : "POST",
		url : "../../InitialInfo/getDataDictionary.action",
		async:false,
		data : {Type:type},
		dataType : "json",
		success : function(data) {
			DataDictionaryDefaultList = data;
		}
	});
	return DataDictionaryDefaultList;
}

//获取数据字典(下拉列表用--无默认值)
function getDataDictionary(type){
	var DataDictionaryInfo;
	$.ajax({
		type : "POST",
		url : "../../InitialInfo/getDataDictionary.action",
		async:false,
		data : {Type:type},
		dataType : "json",
		success : function(data) {
			var html = "";
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
		url : "../../InitialInfo/getDataDictionary.action",
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

//获取数据字典(下拉列表用--默认0)
function getDataDictionaryDefaultNum(type){
	var DataDictionaryDefaultNumInfo;
	$.ajax({
		type : "POST",
		url : "../../InitialInfo/getDataDictionary.action",
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

// 数字decimal类型check(integer:整数位；decimal：小数位)(onkeyup)
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
	/*obj.value = obj.value.replace(/\D/g,"");  //清除“数字”以外的字符  	 

	obj.value= parseFloat(obj.value);// 首位不能为类似于 01、02的金额 
*/
	obj.value = obj.value.replace(/\D/g,"");  //清除“数字”以外的字符  
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

// 获取流水号
function getSerialNumber(){
	var SerialNumber;
	$.ajax({
		type : "POST",
		url : "../../serialNumber/getSerialNumber.action",
		async:false,
		data :{},
		dataType : "json",
		success : function(data) {
			SerialNumber = data;
		}
	});
	return SerialNumber;
}
