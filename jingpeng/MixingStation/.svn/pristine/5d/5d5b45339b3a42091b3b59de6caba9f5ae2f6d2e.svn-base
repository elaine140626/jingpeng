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

function hhh(a,b){
	var startTime = getDate(a);
	var endTime = getDate(b);
	while((endTime.getTime()-startTime.getTime())>=0){
		 var year = startTime.getFullYear();
		 var month = startTime.getMonth().toString().length==1?"0"+startTime.getMonth().toString():startTime.getMonth();
		 var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
		 str_Str[number] = year+"-"+month+"-"+day;
		 str_Date = day;
		 startTime.setDate(startTime.getDate()+1);
		 option.xAxis.data[number] = str_Date;
		 number++;
	}
}

$(function(){
	$.ajaxSetup({ 
	    statusCode: {
	        401: function() { 
	            window.location.href="../testUserInfo/login.html"
	        }, 
	        403: function() { 
	        	window.location.href="../testUserInfo/login.html"
	        }
	    }
	})
});


//注册验证码
function getVerificationCode(){
	var VerificationCode = "test2018";
	return VerificationCode;
}
