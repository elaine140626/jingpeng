// 试验室权限
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
		url :  "../ShiYan/getshiyan05",
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var info = data.data.list;
			var list1 = data.data.listDetail01;
			var list2 = data.data.listDetail02;
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
			if(info[0].testRules != null && info[0].testRules != ''){
				// 试验依据
				document.getElementById("testRules").innerHTML=info[0].testRules;	
			}
			if(info[0].sampleName != null && info[0].sampleName != ''){
				// 样品名称
				document.getElementById("sampleName").innerHTML=info[0].sampleName;
			}
			if(info[0].testOperator != null && info[0].testOperator != ''){
				// 试验员
				document.getElementById("testOperator").innerHTML=info[0].testOperator;
			}
			if(info[0].asphaltType != null && info[0].asphaltType != ''){
				// 沥青种类
				document.getElementById("asphaltType").innerHTML=info[0].asphaltType;
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
					
			var group = "";

			if(list1 != null && list1.length > 0 && list2 != null && list2.length > 0){
				// 针入点 > 软化点
				if(list1.length >= list2.length){
			    	for(var i = 0; i < list1.length; i++) {
			    		group += "<tr>" + "<td>"+(i+1)+"</td>";
			    		if (i<list2.length){
			    			group += "<td>"+list2[i].softenPoint1+"</td>"
						     +"<td>"+list2[i].softenPoint2+"</td>"
						     +"<td>"+list2[i].softenPoint+"</td>";
			    		}			    		 	    
			    		
			    		// 超出部分空白补位
			    		else{
			    				group +="<td></td>"
								       +"<td></td>"
								       +"<td></td>";
				    	}
			    		 
			    		group += "<td>"+list1[i].penetration1+"</td>"
								+"<td>"+list1[i].penetration2+"</td>"
								+"<td>"+list1[i].penetration3+"</td>"
								+"<td>"+list1[i].penetration+"</td>"
								+"</tr>";
			    	}
				}else{
					// 针入点 < 软化点
					for(var i = 0; i < list2.length; i++) {
			    		group += "<tr>"
							+"<td>"+(i+1)+"</td>"
							+"<td>"+list2[i].softenPoint1+"</td>"
							+"<td>"+list2[i].softenPoint2+"</td>"
							+"<td>"+list2[i].softenPoint+"</td>";
			    		if (i<list1.length){
			    			group += "<td>"+list1[i].penetration1+"</td>"
						     +"<td>"+list1[i].penetration2+"</td>"
						     +"<td>"+list1[i].penetration3+"</td>"
						     +"<td>"+list1[i].penetration+"</td>";
			    		}			    						    		
			    		else{
			    				group += "<td></td>"
							     +"<td></td>"
							     +"<td></td>"
							     +"<td></td>";
			    			}
			    		}
			    		group += "</tr>";
				}
			}else{
				group += "<tr>"
					+"<td>1</td>"
					+"<td>&nbsp;</td>"
					+"<td>&nbsp;</td>"
					+"<td>&nbsp;</td>"
					+"<td>&nbsp;</td>"
					+"<td>&nbsp;</td>"
					+"<td>&nbsp;</td>"
					+"<td>&nbsp;</td>";
			}
		    group += "<tr><td>&nbsp;</td>"
					+ "<td>平均针入度</td>"
					+ "<td colspan='2'><label id='avgPenetration'></label></td>"
					+ "<td>平均软化点</td>"
					+ "<td colspan='3'><label id='avgSoftenPoint'></label></td></tr>";
	    	$("#list").html(group);
	    	
			if(info[0].avgPenetration != null && info[0].avgPenetration != ''){
				// 平均针入度
				document.getElementById("avgPenetration").innerHTML=info[0].avgPenetration;
			}
			if(info[0].avgSoftenPoint != null && info[0].avgSoftenPoint != ''){
				// 平均软化点
				document.getElementById("avgSoftenPoint").innerHTML=info[0].avgSoftenPoint;
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