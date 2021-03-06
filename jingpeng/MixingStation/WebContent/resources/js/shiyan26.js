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
	if(roletype == 1 && issave == 'true' && teststate != '2'){
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
		url :  "../ShiYan02/getshiyan26",
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var info = data.data.list;
			var list = data.data.listDetail;
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
			if(info[0].aggregate_Type != null && info[0].aggregate_Type != ''){
				// 集料种类
				document.getElementById("aggregate_Type").innerHTML=info[0].aggregate_Type;
			}
			if(info[0].aggregate_Specification != null && info[0].aggregate_Specification != ''){
				// 集料规格
				document.getElementById("aggregate_Specification").innerHTML=info[0].aggregate_Specification;
			}
			if(info[0].judgementIndex != null && info[0].judgementIndex != ''){
				// 判定指标1
				$("#judgementIndex").val(info[0].judgementIndex);
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
			
			if(list != null && list.length > 0){
				// 试验前烘干试样质量(g)1
				$("#bSmpl_Mass1").val(list[0].bsmpl_Mass1);
				// 试验后烘干试样质量(g)1
				$("#aSmpl_Mass1").val(list[0].asmpl_Mass1);
				// 含泥量/小于0.075mm颗粒含量(%)1
				$("#sediment_Percentage1").val(list[0].sediment_Percentage1);
				// 试验前烘干试样质量(g)2
				$("#bSmpl_Mass2").val(list[0].bsmpl_Mass2);
				// 试验后烘干试样质量(g)2
				$("#aSmpl_Mass2").val(list[0].asmpl_Mass2);
				// 含泥量/小于0.075mm颗粒含量(%)2
				$("#sediment_Percentage2").val(list[0].sediment_Percentage2);
				// 平均值
				$("#avg_Sedi_Perc").val(list[0].avg_Sedi_Perc);
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

//保存按钮
$('#save').on('click', function(event) {
	var param = {};
	// 试验流水号
	param.serialNumber = serialNumber;
	// 试验日期
	param.testDate = $("#testDate").val();
	// 试验前烘干试样质量(g)1
	param.bSmpl_Mass1 = $("#bSmpl_Mass1").val();
	// 试验后烘干试样质量(g)1
	param.aSmpl_Mass1 = $("#aSmpl_Mass1").val();
	// 含泥量/小于0.075mm颗粒含量(%)1
	param.sediment_Percentage1 = $("#sediment_Percentage1").val();
	if((param.sediment_Percentage1.indexOf(".") == -1 && param.sediment_Percentage1.length>14)){
		swal("操作失败","含泥量最多入力14位整数!", "info");
		$("#sediment_Percentage1").val("");
		return;
	}
	// 试验前烘干试样质量(g)2
	param.bSmpl_Mass2 = $("#bSmpl_Mass2").val();
	// 试验后烘干试样质量(g)2
	param.aSmpl_Mass2 = $("#aSmpl_Mass2").val();
	// 含泥量/小于0.075mm颗粒含量(%)2
	param.sediment_Percentage2 = $("#sediment_Percentage2").val();
	if((param.sediment_Percentage2.indexOf(".") == -1 && param.sediment_Percentage2.length>14)){
		swal("操作失败","含泥量最多入力14位整数!", "info");
		$("#sediment_Percentage2").val("");
		return;
	}
	// 平均值
	param.avg_Sedi_Perc = $("#avg_Sedi_Perc").val(); 
	if((param.avg_Sedi_Perc.indexOf(".") == -1 && param.avg_Sedi_Perc.length>14)){
		swal("操作失败","含泥量平均值最多入力14位整数!", "info");
		$("#avg_Sedi_Perc").val("");
		return;
	}
	// 判定指标
	param.judgementIndex = $("#judgementIndex").val();
	// 结果判定
	param.isQualifiedTest = $("#isQualifiedTest option:selected").val();
	$.ajax({
		type : "POST",
		url :  "../ShiYan02/saveShiYan26.html",
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

//只能入力数字和小数点  
function keepNumOrFloat(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
    // obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入一个小数  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    } 
} 


//只能入力数字和小数点  
function keepNumOrDecimal(obj){ 
  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
  obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
  obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入一个小数  
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