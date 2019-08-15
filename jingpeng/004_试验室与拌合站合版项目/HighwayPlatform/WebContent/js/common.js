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

/**
 * 获得url
 * @returns
 */
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = window.location.protocol+"//"+window.location.host+pathName.substr(0,index+1);
    return result;
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

/**
 * 表单转json
 * @param form
 * @returns
 */
function formToJson(form){
    var o={};
    var a=form.serializeArray();
    $.each(a,function(){
    if(o[this.name]!=undefined){
        if(!o[this.name].push){
            o[this.name]=[o[this.name]];
        }
            o[this.name].push(this.value||"");
      }else{
            o[this.name]=this.value||"";
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
    
    var days1 = new Date(year, month-1, 0);
    days1 = days1.getDate(); //获取上个月的天数
    var year2 = year;
    var month2 = parseInt(month);
    var day2 = parseInt(day)-3;
    
    if (day2 <= 0) {
    	month2 = parseInt(month) - 1;
    	if (month2 == 0) {
            year2 = parseInt(year2) - 1;
            month2 = 12;
        }
    	day2 =days1-(day2*-1);
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
function SiloVerification(cc1,cc2,cc3,cc4,allIds){
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
		if(i==0){
			c5 = c2;
		}
		if(i==1){
			c5 = c3;
		}
		if(i==2){
			c5 = c4;
		}
		//合并数组
		for (var j = 0; j < c5.length; j++) {
			c1[c1.length]=c5[j];
		}
	}
	//数组排序
	var nary  = c1.sort(); 
	var nary1 = allIds.sort();
	//循环判断数组中是否有重复的值
	for(var i=0;i<c1.length;i++){  
		if (nary[i]==nary[i+1]){  
			return 0;
		}
	}
	//如果选择完施工配比编号，没有在仓内录入信息
	if(c1.length<1){
		return 1
	}
	
	if(nary.toString()!=nary1.toString()){
		return 2;
	}
}
//去掉重复
function DuplicateRemoval(ar){
	var  end;//临时变量用于对比重复元素
	     ar.sort();//将数重新组排序
	      end = ar[0];
	      for (var i = 1; i < ar.length; i++) {
	          if (ar[i] == end) {//当前元素如果和临时元素相等则将此元素从数组中删除
	              ar.splice(i,1);
	             i--;
	         }else{
	             end = ar[i];
	         }
	     }
	     return ar;
}

//注册验证码
function getVerificationCode(){
	var VerificationCode = "test2018";
	return VerificationCode;
}

