//接收项目路径
var baseUrl = "";
var testName = "";
//试验室权限
var roletype =""; 
// 试验状态
var teststate ="";
$(function() {
	roletype = getUrlParam("roletype");
	teststate = getUrlParam("teststate");
	// 是否盲样采集
	var istestblind = getUrlParam("istestblind");
	if(istestblind == 'true'){
		// 盲样试验时，显示二维码和(施工单位、工程名称、工程部位)
		$("#qrCode").attr("style","display:block;");
		$("#show").attr("style","display:block;");
		$("#istestblind").attr("style","display:block;padding-top: 60px;");
		$("#istestcollection").attr("style","display:none;");
	}
	// 是否自动采集
	var istestcollection = getUrlParam("istestcollection");
	if(istestcollection == 'true'){
		// 表格自动采集试验时，不显示二维码和（施工单位、工程名称、工程部位）；
		$("#qrCode").attr("style","display:none;");
		$("#show").attr("style","display:none;");
		$("#istestcollection").attr("style","display:block;padding-top: 60px;");
		$("#istestblind").attr("style","display:none;");
	}
	var serialNumber = getUrlParam("serialNumber");
	testName = getUrlParam("TestRoomName");
	//获取路径
	baseUrl = getContextPath();
	csh(serialNumber);
});

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

function csh(serialNumber){
	$.ajax({
		type : "POST",
		url : baseUrl + "/testInfo/getCollection.html",
		data : {"serialNumber":serialNumber},
		dataType : "json",
		success : function(data) {
			var dt = data.data.list;
			var dt1 = data.data.list1;
			if(dt[0].testRules!= null && dt[0].testRules != ''){
				document.getElementById("testRules").innerHTML=dt[0].testRules;
			}
			
			if(dt[0].qrcode != null && dt[0].qrcode != ''){
				// 二维码
				makeCode(dt[0].qrcode);
			}
			
			// 权限判断显示内容(试验室权限,未完成状态)
			if(roletype == 1 && teststate != 2){
				// 施工单位
				document.getElementById("constructionUnit").innerHTML = "********";
				// 工程名称
				document.getElementById("engineeringName").innerHTML = "********";
				// 工程部位或用途
				document.getElementById("purpose").innerHTML = "********";
			}else{
				if(dt[0].constructionUnit != null && dt[0].constructionUnit != ''){
					// 施工单位
					document.getElementById("constructionUnit").innerHTML = dt[0].constructionUnit;
				}
				if(dt[0].engineeringName != null && dt[0].engineeringName != ''){
					// 工程名称
					document.getElementById("engineeringName").innerHTML = dt[0].engineeringName;
				}
				if(dt[0].purpose != null && dt[0].purpose != ''){
					// 工程部位或用途
					document.getElementById("purpose").innerHTML = dt[0].purpose;
				}	
			}
			
			document.getElementById("TestRoomName").innerHTML="试验室名称："+testName;
			if(dt[0].testDate!= null && dt[0].testDate != ''){
				document.getElementById("testDate").innerHTML=dt[0].testDate;
			}
			if(dt[0].sampleCode!= null && dt[0].sampleCode != ''){
				document.getElementById("SampleCode").innerHTML=dt[0].sampleCode;
			}
			if(dt[0].sampleCount!= null && dt[0].sampleCount != ''){
				document.getElementById("SampleCount").innerHTML=dt[0].sampleCount;
			}
			if(dt[0].testOperator!= null && dt[0].testOperator != ''){
				document.getElementById("TestOperator").innerHTML=dt[0].testOperator;
			}
			if(dt[0].sampleAmount!= null && dt[0].sampleAmount != ''){
				document.getElementById("SampleAmount").innerHTML=dt[0].sampleAmount;
			}
			if(dt[0].designStrength!= null && dt[0].designStrength != ''){
				document.getElementById("DesignStrength").innerHTML=dt[0].designStrength;
			}
			if(dt[0].avgCompStrength!= null && dt[0].avgCompStrength != ''){
				document.getElementById("avgCompStrength").innerHTML=dt[0].avgCompStrength;
			}
			var isQualifiedTest = "";
			if(dt[0].isQualifiedTest==true){
				isQualifiedTest = "合格";
			}else if(dt[0].isQualifiedTest==false){
				isQualifiedTest = "不合格";
			}else {
				isQualifiedTest = "未判定";
			}
			document.getElementById("isQualifiedTest").innerHTML=isQualifiedTest;
			
			if(document.getElementById("SampleAmount").innerHTML=="3"){
				var group = "";
				if(dt1 != null && dt1.length > 0){
					for(var i = 0; i < dt1.length; i++) {
			    		group +='<tr>'
				    			+'<td rowspan="3">'+(i+1)+'</td>'
				    		    +'<td>'+dt1[i].ultimateLoad1+'</td>'
				    		    +'<td>'+dt1[i].comprStrength1+'</td>'
				    		    +'<td rowspan="3">'+dt1[i].uompressionStrength+'</td>'
				    		    +'<td width="17%" rowspan="3"><img src='+dt1[i].ultLoadImage1+' alt="" width="145" height="70"></td>'
				    		    +'<td width="17%" rowspan="3"><img src='+dt1[i].ultLoadImage2+' alt="" width="145" height="70"></td>'
				    		    +'<td width="16%" rowspan="3"><img src='+dt1[i].ultLoadImage3+' alt="" width="145" height="70"></td>'
				    		  +'</tr>'
				    		  +'<tr>'
				    		    +'<td>'+dt1[i].ultimateLoad2+'</td>'
				    		    +'<td>'+dt1[i].comprStrength2+'</td>'
				    		    +'</tr>'
				    		  +'<tr>'
				    		    +'<td>'+dt1[i].ultimateLoad3+'</td>'
				    		    +'<td>'+dt1[i].comprStrength3+'</td>'
				    		  +'</tr>';
			    	}
				}else{
					group +='<tr>'
		    			+'<td rowspan="3">1</td>'
		    		    +'<td>&nbsp;</td>'
		    		    +'<td>&nbsp;</td>'
		    		    +'<td rowspan="3">&nbsp;</td>'
		    		    +'<td width="17%" rowspan="3">&nbsp;</td>'
		    		    +'<td width="17%" rowspan="3">&nbsp;</td>'
		    		    +'<td width="16%" rowspan="3">&nbsp;</td>'
		    		  +'</tr>'
		    		  +'<tr>'
		    		    +'<td>&nbsp;</td>'
		    		    +'<td>&nbsp;</td>'
		    		    +'</tr>'
		    		  +'<tr>'
		    		    +'<td>&nbsp;</td>'
		    		    +'<td>&nbsp;</td>'
		    		  +'</tr>';
				}
		    	
		    	$("#list").html(group);
			}else{
				var group = "";
				if(dt1 != null && dt1.length > 0){
					for(var i = 0; i < dt1.length; i++) {
						group +=	'<tr>'
								    +'<td rowspan="6">'+(i+1)+'</td>'
								    +'<td>'+dt1[i].ultimateLoad1+'</td>'
								    +'<td>'+dt1[i].comprStrength1+'</td>'
								    +'<td rowspan="6">'+dt1[i].uompressionStrength+'</td>'
								    +'<td width="17%" rowspan="3"><img src='+dt1[i].ultLoadImage1+' alt="" width="145" height="70"></td>'
					    		    +'<td width="17%" rowspan="3"><img src='+dt1[i].ultLoadImage2+' alt="" width="145" height="70"></td>'
					    		    +'<td width="16%" rowspan="3"><img src='+dt1[i].ultLoadImage3+' alt="" width="145" height="70"></td>'
								  +'</tr>'
								  +'<tr>'
								    +'<td>'+dt1[i].ultimateLoad2+'</td>'
								    +'<td>'+dt1[i].comprStrength2+'</td>'
								    +'</tr>'
								  +'<tr>'
								    +'<td>'+dt1[i].ultimateLoad3+'</td>'
								    +'<td>'+dt1[i].comprStrength3+'</td>'
								    +'</tr>'
								  +'<tr>'
								    +'<td>'+dt1[i].ultimateLoad4+'</td>'
								    +'<td>'+dt1[i].comprStrength4+'</td>'
								    +'<td width="17%" rowspan="3"><img src='+dt1[i].ultLoadImage4+' alt="" width="145" height="70"></td>'
					    		    +'<td width="17%" rowspan="3"><img src='+dt1[i].ultLoadImage5+' alt="" width="145" height="70"></td>'
					    		    +'<td width="16%" rowspan="3"><img src='+dt1[i].ultLoadImage6+' alt="" width="145" height="70"></td>'
								  +'</tr>'
								  +'<tr>'
								    +'<td>'+dt1[i].ultimateLoad5+'</td>'
								    +'<td>'+dt1[i].comprStrength5+'</td>'
								    +'</tr>'
								  +'<tr>'
								    +'<td>'+dt1[i].ultimateLoad6+'</td>'
								    +'<td>'+dt1[i].comprStrength6+'</td>'
								  +'</tr>'
					}
				}else{
					group +=	'<tr>'
					    +'<td rowspan="6">1</td>'
					    +'<td>&nbsp;</td>'
					    +'<td>&nbsp;</td>'
					    +'<td rowspan="6">&nbsp;</td>'
					    +'<td width="17%" rowspan="3">&nbsp;</td>'
		    		    +'<td width="17%" rowspan="3">&nbsp;</td>'
		    		    +'<td width="16%" rowspan="3">&nbsp;</td>'
					  +'</tr>'
					  +'<tr>'
					    +'<td>&nbsp;</td>'
					    +'<td>&nbsp;</td>'
					    +'</tr>'
					  +'<tr>'
					    +'<td>&nbsp;</td>'
					    +'<td>&nbsp;</td>'
					    +'</tr>'
					  +'<tr>'
					    +'<td>&nbsp;</td>'
					    +'<td>&nbsp;</td>'
					    +'<td width="17%" rowspan="3">&nbsp;</td>'
		    		    +'<td width="17%" rowspan="3">&nbsp;</td>'
		    		    +'<td width="16%" rowspan="3">&nbsp;</td>'
					  +'</tr>'
					  +'<tr>'
					    +'<td>&nbsp;</td>'
					    +'<td>&nbsp;</td>'
					    +'</tr>'
					  +'<tr>'
					    +'<td>&nbsp;</td>'
					    +'<td>&nbsp;</td>'
					  +'</tr>';
				}
				
			$("#list").html(group);
		}
	}
});
	 
//var group =	'<tr>'
//			+'<td rowspan="3">1</td>'
//		    +'<td>2</td>'
//		    +'<td>3</td>'
//		    +'<td rowspan="3">4</td>'
//		    +'<td>5</td>'
//		    +'<td width="17%" rowspan="3"><img src="../resources/images2/tubiao.jpg" alt="" width="145" height="70"></td>'
//		    +'<td width="17%" rowspan="3"><img src="../resources/images2/tubiao.jpg" alt="" width="145" height="70"></td>'
//		    +'<td width="16%" rowspan="3"><img src="../resources/images2/tubiao.jpg" alt="" width="145" height="70"></td>'
//		  +'</tr>'
//		  +'<tr>'
//		    +'<td>6</td>'
//		    +'<td>7</td>'
//		    +'<td>8</td>'
//		    +'</tr>'
//		  +'<tr>'
//		    +'<td>9</td>'
//		    +'<td>10</td>'
//		    +'<td>11</td>'
//		  +'</tr>'
//$("#list").html(group);
}
//生成二维码大小
var qrcode = new QRCode(document.getElementById("qrCode"), {
});

// 转换二维码
function makeCode (value) {	
	qrcode.makeCode(value);
}