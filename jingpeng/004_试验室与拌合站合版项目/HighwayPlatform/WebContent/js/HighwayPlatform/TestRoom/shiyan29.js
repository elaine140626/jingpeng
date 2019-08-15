// 试验室权限
var roletype = "";
// 试验状态
var teststate = "";
// 流水号
var serialNumber = "";

var selectList = [];
$(function() {
	roletype = getUrlParam("roletype");
	teststate = getUrlParam("teststate");

	// 是否盲样采集
	var istestblind = getUrlParam("istestblind");
	if (istestblind == 'true') {
		// 盲样试验时，显示二维码和(施工单位、工程名称、工程部位)
		$("#qrCode").attr("style", "display:block;");
		$("#show").attr("style", "display:block;");
		$("#istestblind").attr("style", "display:block;padding-top: 60px;");
		$("#istestcollection").attr("style", "display:none;");
	}
	// 是否自动采集
	var istestcollection = getUrlParam("istestcollection");
	if (istestcollection == 'true') {
		// 表格自动采集试验时，不显示二维码和（施工单位、工程名称、工程部位）；
		$("#qrCode").attr("style", "display:none;");
		$("#show").attr("style", "display:none;");
		$("#istestcollection")
				.attr("style", "display:block;padding-top: 60px;");
		$("#istestblind").attr("style", "display:none;");
	}
	var id = getUrlParam("id");
	// 试验查询显示保存
	issave = getUrlParam("issave");
	// 保存按钮显示
	if (roletype == 0 && issave == 'true' && teststate != '2') {
		$("#saveBtnShow").show();
	} else {
		$("#saveBtnShow").hide();
	}
	
	$.ajax({
		type : "POST",
		url : "../../ShiYan02/getPosition.action",
		data : {
			'Aggr_Type':1
		},
		dataType : "json",
		success : function(data) {
			selectList = data;
		}
	});


	// 获取试验项目的显示值
	getTestType();
	// 获取画面的显示数据
	getList(id);
});

// 获取前页面参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null) {
		return decodeURIComponent(r[2]);
		// 这里为什么是从第三个字符解析呢？不知道这样理解对不对，因为路径后面的参数形式为参数名=参数值，而第一个字符为参数名，第二个为=，第三个就为参数值了。。。因为下面调用的时候得出的就是参数值
	}
	return null;// 返回参数值
}

function getList(id) {
	$.ajax({
				type : "POST",
				url : "../../ShiYan02/getshiyan29.action",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data) {
					var info = data.data.list;
					var list = data.data.listDetail;
					serialNumber = info[0].serialNumber;
					if (info[0].qrcode != null && info[0].qrcode != '') {
						// 二维码
						makeCode(info[0].qrcode);
					}

					// 权限判断显示内容(试验室权限,未完成状态)
					if (roletype == 1 && teststate != 2) {
						// 施工单位
						document.getElementById("constructionUnit").innerHTML = "********";
						// 工程名称
						document.getElementById("engineeringName").innerHTML = "********";
						// 工程部位或用途
						document.getElementById("purpose").innerHTML = "********";
					} else {
						if (info[0].constructionUnit != null
								&& info[0].constructionUnit != '') {
							// 施工单位
							document.getElementById("constructionUnit").innerHTML = info[0].constructionUnit;
						}
						if (info[0].engineeringName != null
								&& info[0].engineeringName != '') {
							// 工程名称
							document.getElementById("engineeringName").innerHTML = info[0].engineeringName;
						}
						if (info[0].purpose != null && info[0].purpose != '') {
							// 工程部位或用途
							document.getElementById("purpose").innerHTML = info[0].purpose;
						}
					}

					if (info[0].testRoomName != null
							&& info[0].testRoomName != '') {
						// 试验室名称
						document.getElementById("testRoomName").innerHTML = info[0].testRoomName;
					}
					if (info[0].testRules != null && info[0].testRules != '') {
						// 试验依据
						document.getElementById("testRules").innerHTML = info[0].testRules;
					}
					if (info[0].testDate != null && info[0].testDate != '') {
						// 试验日期
						$("#testDate").val(info[0].testDate);
					}
					if (info[0].sampleName != null && info[0].sampleName != '') {
						// 样品名称
						document.getElementById("sampleName").innerHTML = info[0].sampleName;
					}
					if (info[0].sampleCount != null
							&& info[0].sampleCount != '') {
						// 试件数量
						document.getElementById("sampleCount").innerHTML = info[0].sampleCount;
					}
					if (info[0].testOperator != null
							&& info[0].testOperator != '') {
						// 试验员
						document.getElementById("testOperator").innerHTML = info[0].testOperator;
					}
					if (info[0].diameter != null && info[0].diameter != '') {
						// 直径
						document.getElementById("diameter").innerHTML = info[0].diameter;
					}
					if (info[0].aggregate_Type != null
							&& info[0].aggregate_Type != '') {
						// 集料种类
						document.getElementById("aggregate_Type").innerHTML = info[0].aggregate_Type;
					}
					if (info[0].aggregate_Specification != null
							&& info[0].aggregate_Specification != '') {
						// 集料规格
						document.getElementById("aggregate_Specification").innerHTML = info[0].aggregate_Specification;
					}

					if (list != null && list.length > 0) {
						for (var j = 0; j < list.length; j++) {
							
							// 重新查询
							var info = {};
							info.id = 'test_Project'+(j + 1);
							info.value = list[j].test_Project;
							changeTest_Project(info);
							$("#test_Project"+(j + 1)).find("option[value='"+list[j].test_Project+"']").prop("selected", 'selected');
    						$("#technical_Index"+(j + 1)).find("option[value='"+list[j].technical_Index+"']").prop("selected", 'selected');
							$("#test_Item" + (j + 1)).val(list[j].test_Item);
							$("#detection_Result"+(j + 1)).find("option[value='"+list[j].detection_Result+"']").prop("selected", 'selected');
							
						}
					}
				}
			});
}

// 生成二维码大小
var qrcode = new QRCode(document.getElementById("qrCode"), {});

// 转换二维码
function makeCode(value) {
	qrcode.makeCode(value);
}

// 保存按钮
$('#save').on('click', function(event) {
	var param = {};
	// 试验流水号
	param.serialNumber = serialNumber;

	// 试验日期
	param.testDate = $("#testDate").val();
	
	// 判定结果
	// 合格
	param.isQualifiedTest = '1';
	var infoList = [];
	for (var j = 0; j < 20; j++) {
		var info = {};

		info.test_Project = $("#test_Project"+(j + 1)).val();
		info.technical_Index = $("#technical_Index"+(j + 1)).val();
		info.test_Item = $("#test_Item"+(j + 1)).val();
		info.detection_Result = $("#detection_Result"+(j + 1)).val();
		if($("#detection_Result"+(j + 1)).val() == '0'){
			param.isQualifiedTest = '0';
		};
		
		infoList.push(info);
	}
	param.data = JSON.stringify(infoList);

	$.ajax({
		type : "POST",
		url : "../../ShiYan02/saveShiYan29.action",
		data : param,
		dataType : "json",
		success : function(data) {
			if (data.code == "success") {
				swal({
					title : data.message,
					text : "",
					type : data.code,
					confirmButtonText : '确定',
					cancelButtonFont : '微软雅黑',
				});
			} else {
				swal({
					title : data.message,
					text : "",
					type : data.code,
					confirmButtonText : '确定',
					cancelButtonFont : '微软雅黑',
				});
			}
		}
	});
});

// 试验项目赋值
function getTestType() {
	$.ajax({
		type : "POST",
		url : "../../ShiYan02/getQualification.action",
		data : {
			"Aggr_Type" : 1
		},
		dataType : "json",
		success : function(data) {
			if (data != null && data != "") {
				for (var i = 0; i < data.length; i++) {
					$(".search_select1").append("<option value='" + data[i].qualification + "'>"+data[i].qualification+"</option>");
				}
			}
		}
	});
}

// 根据试验项目获取技术指标
function changeTest_Project(info){	
	var id = parseInt(info.id.replace(/[^0-9]/ig,""));// 截取数字
	
	// 全部数据
	if(selectList != null && selectList.length > 0){
		for(var i = 0; i < selectList.length; i++){
			// 根据选择的试验项目获取技术指标
			if(info.value == selectList[i].Qualification){
				var list = [];
				// 根据*分割
				list =  selectList[i].children.split('*');
				$("#technical_Index"+id).html("");
				for(var j = 0; j < list.length; j++){
					// 技术指标的下拉列表赋值
					$("#technical_Index"+id).append("<option value='" + list[j].split(',')[0] +','+ list[j].split(',')[1] +','+list[j].split(',')[2]+"'>"
	                        + list[j].split(',')[0] +"&nbsp;&nbsp;&nbsp;&nbsp;"+list[j].split(',')[1] +"&nbsp;&nbsp;&nbsp;&nbsp;"+ list[j].split(',')[2]+"</option>");
				}	
			}
		}
	}
}

// 检测结果的判定
function changeTest_Item(info){
	var id = parseInt(info.id.replace(/[^0-9]/ig,""));// 截取数字
	var value = info.value;
	var technical_Index = $("#technical_Index"+id).val();
	if(technical_Index != null && technical_Index != ""){
		if(technical_Index.split(',')[1] == '='){
			if(value == technical_Index.split(',')[2]){
				$("#detection_Result"+id).find("option[value='1']").prop("selected", 'selected');
			}else{
				$("#detection_Result"+id).find("option[value='0']").prop("selected", 'selected');
			}
		}else if(technical_Index.split(',')[1] == '<'){
			if(value < technical_Index.split(',')[2]){
				$("#detection_Result"+id).find("option[value='1']").prop("selected", 'selected');
			}else{
				$("#detection_Result"+id).find("option[value='0']").prop("selected", 'selected');
			}
		}else if(technical_Index.split(',')[1] == '<='){
			if(value <= technical_Index.split(',')[2]){
				$("#detection_Result"+id).find("option[value='1']").prop("selected", 'selected');
			}else{
				$("#detection_Result"+id).find("option[value='0']").prop("selected", 'selected');
			}
		}else if(technical_Index.split(',')[1] == '>'){
			if(value > technical_Index.split(',')[2]){
				$("#detection_Result"+id).find("option[value='1']").prop("selected", 'selected');
			}else{
				$("#detection_Result"+id).find("option[value='0']").prop("selected", 'selected');
			}
		}else if(technical_Index.split(',')[1] == '>='){
			if(value >= technical_Index.split(',')[2]){
				$("#detection_Result"+id).find("option[value='1']").prop("selected", 'selected');
			}else{
				$("#detection_Result"+id).find("option[value='0']").prop("selected", 'selected');
			}
		}else if(technical_Index.split(',')[1] == '!='){
			if(value != technical_Index.split(',')[2]){
				$("#detection_Result"+id).find("option[value='1']").prop("selected", 'selected');
			}else{
				$("#detection_Result"+id).find("option[value='0']").prop("selected", 'selected');
			}
		}
	}
}

//只能入力数字和小数点  
function keepNumOrFloat(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    } 
} 

//最后一位不是小数点
function checkPoint(obj){
	if (obj.value.charAt(obj.value.length - 1)=='.'){
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}		
}
