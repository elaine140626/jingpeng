// 试验室权限
var roletype =""; 
// 试验状态
var teststate ="";
//流水号
var serialNumber = "";
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
	//试验查询显示保存
	issave = getUrlParam("issave");
	// 保存按钮显示
	if(roletype == 0 && issave == 'true' && teststate != '2'){
		$("#saveBtnShow").show();
	}else{
		$("#saveBtnShow").hide();
	}
	getList(id);
});

// 获取前页面参数
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
		url :  "../../ShiYan02/getshiyan27.action",
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var info = data.data.list;
			serialNumber = info[0].serialNumber;
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
			if(info[0].testDate != null && info[0].testDate != ''){
				// 试验日期
				$("#testDate").val(info[0].testDate);
			}
			if(info[0].sampleName != null && info[0].sampleName != ''){
				// 样品名称
				document.getElementById("sampleName").innerHTML=info[0].sampleName;
			}
			if(info[0].sampleCount != null && info[0].sampleCount != ''){
				// 试件数量
				document.getElementById("sampleCount").innerHTML=info[0].sampleCount;
			}
			if(info[0].testOperator != null && info[0].testOperator != ''){
				// 试验员
				document.getElementById("testOperator").innerHTML=info[0].testOperator;
			}
			if(info[0].diameter != null && info[0].diameter != ''){
				// 直径
				document.getElementById("diameter").innerHTML=info[0].diameter;
			}
			if(info[0].cementType != null && info[0].cementType != ''){
				// 水泥品种
				document.getElementById("cementType").innerHTML=info[0].cementType;
			}
			if(info[0].cementStrengthGrade != null && info[0].cementStrengthGrade != ''){
				// 水泥强度
				document.getElementById("cementStrengthGrade").innerHTML=info[0].cementStrengthGrade;
			}
			
			var isQualifiedTest = info[0].isQualifiedTest;
			if(isQualifiedTest == true){
				isQualifiedTest = '1';
			}else if(isQualifiedTest == false){
				isQualifiedTest = '0';
			}else{
				isQualifiedTest = '';
			}
			// 判定结果
			$("#isQualifiedTest").find("option[value='"+isQualifiedTest+"']").prop("selected", 'selected');

			// 起始时间(时)
    		$("#start_Hour").val(info[0].start_Hour);
    		// 起始时间(分)
    		$("#start_Minute").val(info[0].start_Minute);
    		// 初凝状态时间(时)
    		$("#initial_Set_Hour").val(info[0].initial_Set_Hour);
    		// 初凝状态时间(分)
    		$("#initial_Set_Minute").val(info[0].initial_Set_Minute);
    		// 终凝状态时间(时)
    		$("#final_Set_Hour").val(info[0].final_Set_Hour);
    		// 终凝状态时间(分)
    		$("#final_Set_Minute").val(info[0].final_Set_Minute);
    		// 初凝时间(min)
    		$("#init_Set_Time").val(info[0].init_Set_Time);
    		// 终凝时间(min)
    		$("#final_Set_Time").val(info[0].final_Set_Time);

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

//保存按钮
$('#save').on('click', function(event) {
	var param = {};
	// 试验流水号
	param.serialNumber = serialNumber;
	// 试验日期
	param.testDate = $("#testDate").val();
	// 起始时间(时)
	param.start_Hour = $("#start_Hour").val();
	// 起始时间(分)
	param.start_Minute = $("#start_Minute").val();
	// 初凝状态时间(时)
	param.initial_Set_Hour = $("#initial_Set_Hour").val();
	// 初凝状态时间(分)
	param.initial_Set_Minute = $("#initial_Set_Minute").val();
	// 终凝状态时间(时)
	param.final_Set_Hour = $("#final_Set_Hour").val();
	// 终凝状态时间(分)
	param.final_Set_Minute = $("#final_Set_Minute").val();
	// 初凝时间(min)
	param.init_Set_Time = $("#init_Set_Time").val();
	// 终凝时间(min)
	param.final_Set_Time = $("#final_Set_Time").val();
	// 结果判定
	param.isQualifiedTest = $("#isQualifiedTest option:selected").val();
	$.ajax({
		type : "POST",
		url :  "../../ShiYan02/saveShiYan27.action",
		data : param,
		dataType : "json",
		success : function(data) {
			if(data.code == "success"){
				swal({
					title: data.message,
					text: "",
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				});
			}else{
				swal({
					title: data.message,
					text: "",
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				});
			}
		}
	});
});