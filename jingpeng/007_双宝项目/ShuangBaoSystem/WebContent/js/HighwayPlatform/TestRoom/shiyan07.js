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
		url :  "../../ShiYan/getshiyan07.action",
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			console.log(data.data.list);
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
			if(info[0].testRules != null && info[0].testRules != ''){
				// 试验依据
				document.getElementById("testRules").innerHTML = info[0].testRules;	
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
			if(info[0].diameter != null && info[0].diameter != ''){
				// 直径
				document.getElementById("diameter").innerHTML=info[0].diameter;
			}			
			if(info[0].strength_Grade != null && info[0].strength_Grade != ''){
				// 钢筋牌号
				document.getElementById("strength_Grade").innerHTML=info[0].strength_Grade;
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
			if(list != null && list.length > 0){
				for(var i = 0; i < list.length; i++) {
		    		group += "<tr>"
					       + "<td>"+(i+1)+"</td>"
					       + "<td>"+list[i].yieldLoad1+"</td>"
					       + "<td>"+list[i].yieldLoad2+"</td>"
					       + "<td>"+list[i].yieldStrth1+"</td>"
					       + "<td>"+list[i].yieldStrth2+"</td>"
					       + "<td>"+list[i].maxLoad1+"</td>"
					       + "<td>"+list[i].maxLoad2+"</td>"
					       + "<td>"+list[i].tensileStrength1+"</td>"
					       + "<td>"+list[i].tensileStrength2+"</td>"
					       + "</tr>";
		    	}
			}else{
				group += "<tr>"
				       + "<td>1</td>"
				       + "<td>&nbsp;</td>"
				       + "<td>&nbsp;</td>"
				       + "<td>&nbsp;</td>"
				       + "<td>&nbsp;</td>"
				       + "<td>&nbsp;</td>"
				       + "<td>&nbsp;</td>"
				       + "<td>&nbsp;</td>"
				       + "<td>&nbsp;</td>"
				       + "</tr>";
			}
	    	
	    	group += "<tr>"
		              + "<td>&nbsp;</td>"
		              + "<td colspan='2'>平均屈服强度（Mpa)</td>"
		              + "<td colspan='2'><label id='avgYieldStrth'></label></td>"
		              + "<td colspan='2'>平均抗拉强度（Mpa)</td>"
		              + "<td colspan='2'><label id='avgTensileStrength'></label></td>"
		              + "</tr>"
		              + "<tr>"
		              + "<td>判定指标</td>"
		              + "<td colspan='2'>屈服强度</td>"
		              + "<td colspan='2'><label id='yieldStrth'></label></td>"
		              + "<td colspan='2'>极限强度</td>"
		              + "<td colspan='2'><label id='tensileStrength'></label></td>"
				      + "</tr>" 
	    	$("#list").html(group);
			if(info[0].avgYieldStrth != null && info[0].avgYieldStrth != ''){
				// 平均屈服强度（Mpa)
				document.getElementById("avgYieldStrth").innerHTML=info[0].avgYieldStrth;
			}
			if(info[0].avgTensileStrength != null && info[0].avgTensileStrength != ''){
				// 平均抗拉强度（Mpa)
				document.getElementById("avgTensileStrength").innerHTML=info[0].avgTensileStrength;
			}
			if(info[0].yieldStrth != null && info[0].yieldStrth != ''){
				// 屈服强度
				document.getElementById("yieldStrth").innerHTML=info[0].yieldStrth;
			}
			if(info[0].tensileStrength != null && info[0].tensileStrength != ''){
				// 极限强度
				document.getElementById("tensileStrength").innerHTML=info[0].tensileStrength;
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