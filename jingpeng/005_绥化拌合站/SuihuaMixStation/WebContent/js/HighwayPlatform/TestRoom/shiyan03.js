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
	var id = getUrlParam("id");
	getList(id);
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

function getList(id){
	$.ajax({
		type : "POST",
		url : "../../ShiYan/getshiyan03.action",
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var info = data.data.list;
			var list = data.data.listDetail;
			if(info[0].qrcode != null && info[0].qrcode != ''){
				// 二维码
				makeCode(info[0].qrcode);
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
				if(info[0].constructionUnit != null && info[0].constructionUnit != ''){
					// 施工单位
					document.getElementById("constructionUnit").innerHTML = info[0].constructionUnit;
				}
				if(info[0].engineeringName != null && info[0].engineeringName != ''){
					// 工程名称
					document.getElementById("engineeringName").innerHTML = info[0].engineeringName;
				}
				if(info[0].purpose != null && info[0].purpose != ''){
					// 工程部位或用途
					document.getElementById("purpose").innerHTML = info[0].purpose;
				}	
			}
			
			if(info[0].testRoomName != null && info[0].testRoomName != ''){
				// 试验室名称
				document.getElementById("testRoomName").innerHTML = info[0].testRoomName;
			}
			if(info[0].testRoomName != null && info[0].testRoomName != ''){
				// 试验室名称
				document.getElementById("testRoomName").innerHTML = info[0].testRoomName;
			}
			if(info[0].testRules != null && info[0].testRules != ''){
				// 试验依据
				document.getElementById("testRules").innerHTML=info[0].testRules;	
			}
			if(info[0].testDate != null && info[0].testDate != ''){
				// 试验日期
				document.getElementById("testDate").innerHTML=info[0].testDate;
			}
			if(info[0].sampleCount != null && info[0].sampleCount != ''){
				// 试件个数
				document.getElementById("sampleCount").innerHTML=info[0].sampleCount;
			}
			if(info[0].sampleName != null && info[0].sampleName != ''){
				// 样品名称
				document.getElementById("sampleName").innerHTML=info[0].sampleName;
			}
			if(info[0].testOperator != null && info[0].testOperator != ''){
				// 试验员
				document.getElementById("testOperator").innerHTML=info[0].testOperator;
			}
			if(info[0].sampleAmount!= null && info[0].sampleAmount != ''){
				// 试件数量
				document.getElementById("SampleAmount").innerHTML=info[0].sampleAmount;
			}
			if(info[0].strength_Grade != null && info[0].strength_Grade != ''){
				// 强度等级
				document.getElementById("strength_Grade").innerHTML=info[0].strength_Grade;
			}
			if(info[0].moldDate != null && info[0].moldDate != ''){
				// 成型日期
				document.getElementById("moldDate").innerHTML=info[0].moldDate;
			}
			if(info[0].avgCompStrength != null && info[0].avgCompStrength != ''){
				// 平均抗压强度
				document.getElementById("avgCompStrength").innerHTML=info[0].avgCompStrength;
			}
			var isQualifiedTest = "";
			if(info[0].isQualifiedTest == '0'){
				isQualifiedTest = "不合格";	
			}else if(info[0].isQualifiedTest == '1'){
				isQualifiedTest = "合格";				
			}else{
				isQualifiedTest = "未判定";
			}	
			document.getElementById("isQualifiedTest").innerHTML=isQualifiedTest;
			
			if(document.getElementById("SampleAmount").innerHTML=="3"){
				var group = "";
				if(list != null && list.length > 0){
					for(var i = 0; i < list.length; i++) {
			    		group +='<tr>'
				    			+'<td rowspan="3">'+(i+1)+'</td>'
				    		    +'<td>'+list[i].ultimateLoad1+'</td>'
				    		    +'<td>'+list[i].comprStrength1+'</td>'
				    		    +'<td rowspan="3">'+list[i].compressionStrength+'</td>'
				    		    +'<td width="17%" rowspan="3"><img src='+list[i].ultLoadImage1+' alt="" width="145" height="70"></td>'
				    		    +'<td width="17%" rowspan="3"><img src='+list[i].ultLoadImage2+' alt="" width="145" height="70"></td>'
				    		    +'<td width="16%" rowspan="3"><img src='+list[i].ultLoadImage3+' alt="" width="145" height="70"></td>'
				    		  +'</tr>'
				    		  +'<tr>'
				    		    +'<td>'+list[i].ultimateLoad2+'</td>'
				    		    +'<td>'+list[i].comprStrength2+'</td>'
				    		    +'</tr>'
				    		  +'<tr>'
				    		    +'<td>'+list[i].ultimateLoad3+'</td>'
				    		    +'<td>'+list[i].comprStrength3+'</td>'
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
				if(list != null && list.length > 0){
					for(var i = 0; i < list.length; i++) {
						group +=	'<tr>'
								    +'<td rowspan="6">'+(i+1)+'</td>'
								    +'<td>'+list[i].ultimateLoad1+'</td>'
								    +'<td>'+list[i].comprStrength1+'</td>'
								    +'<td rowspan="6">'+list[i].compressionStrength+'</td>'								 
								    +'<td width="17%" rowspan="3"><img src='+list[i].ultLoadImage1+' alt="" width="145" height="70"></td>'
					    		    +'<td width="17%" rowspan="3"><img src='+list[i].ultLoadImage2+' alt="" width="145" height="70"></td>'
					    		    +'<td width="16%" rowspan="3"><img src='+list[i].ultLoadImage3+' alt="" width="145" height="70"></td>'
								  +'</tr>'
								  +'<tr>'
								    +'<td>'+list[i].ultimateLoad2+'</td>'
								    +'<td>'+list[i].comprStrength2+'</td>'
								    +'</tr>'
								  +'<tr>'
								    +'<td>'+list[i].ultimateLoad3+'</td>'
								    +'<td>'+list[i].comprStrength3+'</td>'
								    +'</tr>'
								  +'<tr>'
								    +'<td>'+list[i].ultimateLoad4+'</td>'
								    +'<td>'+list[i].comprStrength4+'</td>'
								    +'<td width="17%" rowspan="3"><img src='+list[i].ultLoadImage4+' alt="" width="145" height="70"></td>'
					    		    +'<td width="17%" rowspan="3"><img src='+list[i].ultLoadImage5+' alt="" width="145" height="70"></td>'
					    		    +'<td width="16%" rowspan="3"><img src='+list[i].ultLoadImage6+' alt="" width="145" height="70"></td>'
								  +'</tr>'
								  +'<tr>'
								    +'<td>'+list[i].ultimateLoad5+'</td>'
								    +'<td>'+list[i].comprStrength5+'</td>'
								    +'</tr>'
								  +'<tr>'
								    +'<td>'+list[i].ultimateLoad6+'</td>'
								    +'<td>'+list[i].comprStrength6+'</td>'
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
}

//生成二维码大小
var qrcode = new QRCode(document.getElementById("qrCode"), {
});

// 转换二维码
function makeCode (value) {	
	qrcode.makeCode(value);
}