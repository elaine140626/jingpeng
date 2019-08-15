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
		url :  "../ShiYan02/getshiyan23",
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var info = data.data.list;
			var list = data.data.listDetail1;
			var list1 = data.data.listDetail2;
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
				// 判定指标
				$("#judgementIndex").val(info[0].judgementIndex);
			}
			if(info[0].samp_Mass != null && info[0].samp_Mass != ''){
				// 试件总质量(g) 粗集料针、片状颗粒含量试验（规准仪法）
				$("#samp_Mass").val(info[0].samp_Mass);
			}
			if(info[0].elon_Part_Mass != null && info[0].elon_Part_Mass != ''){
				// 针片状颗粒总质量（g) 粗集料针、片状颗粒含量试验（规准仪法）
				$("#elon_Part_Mass").val(info[0].elon_Part_Mass);
			}
			if(info[0].elongated_Particles != null && info[0].elongated_Particles != ''){
				// 针片状颗粒含量(%) 粗集料针、片状颗粒含量试验（规准仪法）
				$("#elongated_Particles").val(info[0].elongated_Particles);
			}
			// 试验方法
			$("#test_Method option[value='"+info[0].test_Method+"']").prop("selected", 'selected');
			var html = '';
			if(info[0].test_Method == 'a'){
				$("#show1").show();
				$("#show2").hide();
				if(list1 != null && list1.length > 0){
					for(var j=0;j<list1.length;j++){
						$("#single_Stage"+(j+1)).val(list1[j].single_Stage);
						$("#acic_Parti_Mass"+(j+1)).val(list1[j].acic_Parti_Mass);
						$("#flaky_Parti_Mass"+(j+1)).val(list1[j].flaky_Parti_Mass);
					}
				}			
			}else if(info[0].test_Method == 'b'){
				$("#show2").show();
				$("#show1").hide();
				var data = document.getElementsByName("name1");
				if(list != null && list.length > 0){
					for(var j=0;j<list.length;j++){
							$("#samp_Mass"+(j+1)).val(list[j].samp_Mass1);
							$("#in_Samp_Mass"+(j+1)).val(list[j].in_Samp_Mass1);
							$("#elongated_Particles"+(j+1)).val(list[j].elongated_Particles1);
							$("#avg_Elon_Part"+(j+1)).val(list[j].avg_Elon_Part);
							$("#samp_Mass2"+(j+1)).val(list[j].samp_Mass2);          
							$("#in_Samp_Mass2"+(j+1)).val(list[j].in_Samp_Mass2);       
							$("#elongated_Particles2"+(j+1)).val(list[j].elongated_Particles2);
							$("#samp_Mass3"+(j+1)).val(list[j].samp_Mass3);          
							$("#in_Samp_Mass3"+(j+1)).val(list[j].in_Samp_Mass3);       
							$("#elongated_Particles3"+(j+1)).val(list[j].elongated_Particles3);
					}
				}
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
	// 试件总质量(g) 粗集料针、片状颗粒含量试验（规准仪法）
	param.samp_Mass = $("#samp_Mass").val();
	// 针片状颗粒总质量（g) 粗集料针、片状颗粒含量试验（规准仪法）
	param.elon_Part_Mass = $("#elon_Part_Mass").val();
	// 针片状颗粒含量(%) 粗集料针、片状颗粒含量试验（规准仪法）
	param.elongated_Particles = $("#elongated_Particles").val();
	// 判定指标
	param.judgementIndex = $("#judgementIndex").val();
	// 结果判定
	param.isQualifiedTest = $("#isQualifiedTest option:selected").val();
	// 试验方法
	param.test_Method = $("#test_Method option:selected").val();
	var pd = $("#test_Method option:selected").val();
	var infoList = [];
	if(pd == 'a'){ 
		for(var j=0; j<=5; j++){
			var info = {};
			info.single_Stage = $("#single_Stage"+(j+1)).val();
			info.acic_Parti_Mass = $("#acic_Parti_Mass"+(j+1)).val();
			info.flaky_Parti_Mass = $("#flaky_Parti_Mass"+(j+1)).val();
			infoList.push(info);
		}
	    param.data = JSON.stringify(infoList);	
	}else if(pd == 'b'){
		for(var j=0; j<=1; j++){
			var info = {};
			info.samp_Mass = $("#samp_Mass"+(j+1)).val();
			info.in_Samp_Mass = $("#in_Samp_Mass"+(j+1)).val();
			info.elongated_Particles = $("#elongated_Particles"+(j+1)).val();
			if((info.elongated_Particles.indexOf(".") == -1 && info.elongated_Particles.length>14)){
				swal("操作失败","针片状颗粒含量最多入力14位整数!", "info");
				$("#elongated_Particles"+(j+1)).val("");
				return;
			}
			info.avg_Elon_Part = $("#avg_Elon_Part"+(j+1)).val();
			if((info.avg_Elon_Part.indexOf(".") == -1 && info.avg_Elon_Part.length>14)){
				swal("操作失败","针片状颗粒含量平均值最多入力14位整数!", "info");
				$("#avg_Elon_Part"+(j+1)).val("");
				return;
			}
			info.samp_Mass2 = $("#samp_Mass2"+(j+1)).val();          
			info.in_Samp_Mass2 = $("#in_Samp_Mass2"+(j+1)).val();       
			info.elongated_Particles2 = $("#elongated_Particles2"+(j+1)).val();
			if((info.elongated_Particles2.indexOf(".") == -1 && info.elongated_Particles2.length>14)){
				swal("操作失败","针片状颗粒含量最多入力14位整数!", "info");
				$("#elongated_Particles2"+(j+1)).val("");
				return;
			}
			info.samp_Mass3 = $("#samp_Mass3"+(j+1)).val();          
			info.in_Samp_Mass3 = $("#in_Samp_Mass3"+(j+1)).val();       
			info.elongated_Particles3 = $("#elongated_Particles3"+(j+1)).val();
			if((info.elongated_Particles3.indexOf(".") == -1 && info.elongated_Particles3.length>14)){
				swal("操作失败","针片状颗粒含量最多入力14位整数!", "info");
				$("#elongated_Particles3"+(j+1)).val("");
				return;
			}
			infoList.push(info);
		}
	    param.data = JSON.stringify(infoList);
	}
	$.ajax({
		type : "POST",
		url :  "../ShiYan02/saveShiYan23.html",
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