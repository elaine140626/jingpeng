// 合同id
var id;
// 0:新增  
var flag;
// 物料信息下拉列表
var MaterielInfo;
// 物料List
var MaterielList;
// 项目检测下拉列表
var detectionProject;

var testreport; 
// 当前登录人姓名
var Creater = "";

var testreportInfoByIdFalg = true;

$(function(){
	//客户名称下拉列表
	var TestreportNameInfo = getTestreportName();
	$("#inspector").empty();
	$("#inspector").html(TestreportNameInfo);
	
	//电话下拉列表
	var TestreportTelephone = getTestreportTelephone();
	$("#telephone").empty();
	$("#telephone").html(TestreportTelephone);
	
	//物料信息取得
	getMaterielInfo();
	
	//检测项目取得
	detectionProjects = getDataDictionarys('jcxm');
	
	//检测项目对象
	testreport = getTestreport();
	
	//检测项目取得
	detectionProject = getDataDictionary('jcxm');

	// 获取前页面传过来的id
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	getTestreportInfoByIds();
	if(userInfo != ""){	
		Creater = userInfo.id;
	}
})

// 根据入库单id获取检测项目明细
function getTestreportInfoByIds(){
	$.ajax({
		type : "POST",
		url : "../../testreportsaledetailed/getTestreportInfoByIds.action",
		async:false,
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			var testreportListList = data.testreportListList;
			var userinfoList = data.userinfoList;
			var testreportsaledetailedList = data.testreportsaledetailedList;
			if (userinfoList.length > 0) {
				// 检测员电话
				$("#telephones").val(userinfoList[0].telephone);
				// 检测员
				$("#inspector").val(userinfoList[0].id);	
			} else {
				// 检测员电话
				$("#telephones").val(userInfo.telephone);
				// 检测员
				$("#inspector").val(userInfo.id);
			} 
			if (testreportListList.length > 0) {
				// 检测时间
				$("#testDate").val(testreportListList[0].testDate);
				// 入库单检测报告单id
				$("#testreportId").val(testreportListList[0].id);
				//备注
				$("#Remarks").val(testreportListList[0].remarks);
			} else {
				// 检测时间
				$("#testDate").val(getNowFormatDate());
			}
			// 检测报告单销售订单明细
			if (testreportsaledetailedList.length>0){
				for(var i=0;i<testreportsaledetailedList.length;i++){
					if (testreportInfoByIdFalg) {
						//检测报告单销售订单明细新加一条
						addTable();
					}
					// 物料名称
					$("#materielName"+(i+1)).val(testreportsaledetailedList[i].materielId);				
					//检测项目
					$("#detectionProject"+(i+1)).val(testreportsaledetailedList[i].testProject);
					changeMaterielName(i+1,testreportsaledetailedList[i].testProject);
					//技术指标
					$("#technicalIndicators"+(i+1)).val(testreportsaledetailedList[i].skillIndex);
					changeResultDetermination(i+1,testreportsaledetailedList[i].skillIndex);
					//检测结果
					$("#detectionResult"+(i+1)).val(testreportsaledetailedList[i].testResult);
					//检测报告单销售订单明细id
					$("#testreportsaledetailedId"+(i+1)).val(testreportsaledetailedList[i].id);
					getBlurs(i+1);
				}
			} else {
				//检测报告单销售订单明细新加一条
				addTable();
				$("#materielName1").val(data.storagemeasureList.materielNameId);
			}
			// 若为合格状态则不允许修改，隐藏保存按钮,表单项不可修改
			if (data.storagemeasureList.flag == "合格") {
				$("#telephone").attr("disabled","disabled");
				$("#inspector").attr("disabled","disabled");
				$("#telephones").attr("readonly","readonly");
				$("#testDate").attr("readonly","readonly");
				$("select[name='materielName']").attr("disabled","disabled");
				$("select[name='detectionProject']").attr("disabled","disabled");
				$("select[name='technicalIndicators']").attr("disabled","disabled");
				$("select[name='technicalIndicators']").attr("disabled","disabled");
				$("input[name='detectionResult']").attr("disabled","disabled");
				$("select[name='resultDetermination']").attr("disabled","disabled");
				$("#saveBtn").hide();
				$("#addBtn").hide();
				$(".del").hide();
			}
		}
	});
}


//物料信息取得
function getMaterielInfo(){
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getMaterielInfo.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			MaterielList = data;
			var html = "<option value='' selected='selected'>请选择</option>";
			var MaterielNameId = "";
			for (var i = 0; i < data.length; i++) {
				if (!MaterielNameId.match(","+data[i].MaterielNameId+",")){
					html += "<option value='" + data[i].MaterielNameId + "'>"
					+ data[i].MaterielName + "</option>"
					MaterielNameId += ","+data[i].MaterielNameId+",";
				}			
			}
			MaterielInfo = html;
		}
	});
}

// 入库单检测报告单明细新增一条
function addTable(){
	var len = $('#tableList1 tr').length;
	var row;
	var result="";
	if(len==1){
		row = 1;
	}else{
		for(var i = 1;i<len;i++){
			row = i+1;
		}
	}
	result +="<tr>";
	result +="<td>"+row+"</td>";
	result +="<td ><a class='del' style='margin: 0 15px;' onclick='removeTr1(this,"+ row +")'><img src='../../img/common/del.png' width='20' height='20' alt='删除' title='删除'></a></td>";
	result +="<td ><select name='materielName' id='materielName"+row+"'>"+MaterielInfo+"</select></td>";
	result +="<td ><select name='detectionProject' id='detectionProject"+row+"' onchange='changeMaterielName("+row+",this.options[this.options.selectedIndex].value)'>"+detectionProject+"</select></td>";
	result +="<td ><select name='technicalIndicators' id='technicalIndicators"+row+"'onchange='changeResultDetermination("+row+",this.options[this.options.selectedIndex].text)' ></select></td>";
	result +="<td ><input name='detectionResult' onBlur='getBlurs("+ row +")' id='detectionResult"+row+"' type='text' maxlength='200' onkeyup='keepNumOrDecimal(this)' onblur='checkPoint(this)' /></td>";
	result +="<td ><select name='resultDetermination' id='resultDetermination"+row+"'></select></td>";
	result +="<td ><input name='testreportsaledetailedId'  id='testreportsaledetailedId"+row+"' value = '0' type='hidden'/></td>";
	result +="</tr>";
	$("#tableList1").append(result);
}

//按输入模糊搜索下拉选项数据
function getBlur(){
	var telephones = $("#telephones").val();
	$.ajax({
		type : "POST",
		url : "../../testreportsaledetailed/getTestreportNameInfo.action",
		async:false,
		data : {"telephones":telephones},
		dataType : "json",
		success : function(data) {
			var html = "<option value=''  selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].id + "'>"
				+ data[i].telephone + "</option>"		
			}
			TestreportNameInfo = html;
			$("#telephone").empty();
			$("#telephone").html(TestreportNameInfo);
		}
	});
}

//按选中用户名称关联用户的电话
function changeTestreportName(){
	for (var i = 0; i < testreport.length; i++) {
		if(testreport[i].id == $("#inspector option:selected").val()){
			$("#telephones").val(testreport[i].telephone);
		}
	}
}

//按选中的电话关联用户名称
function getTelephone(){
	$("#telephones").val($("#telephone option:selected").text());
	for (var i = 0; i < testreport.length; i++) {
		if(testreport[i].id == $("#telephone option:selected").val()){
			$("#inspector").val(testreport[i].id);
		}
	}
}

//技术指标选中的值与检测结果比较,如果是在技术指指标范围内结果判定为合格,不在范围内就定为不合格  
function changeResultDetermination(data , value){
	var technicalIndicatorsValue = value;
	var detectionResultValue = $("#detectionResult"+data).val();
	if(detectionResultValue != null && detectionResultValue != "" ){
		getDetectionResultValue(data,detectionResultValue,technicalIndicatorsValue);
	}
}

//取检测项目中选中相对应的技术指标下拉数据
function changeMaterielName(data,value){
	//技术指标下拉数据取得
	$("#technicalIndicators"+data).html(getDataDictionary(value));
}

//检测结果输入完成与技术指标选中的值比较,如果是在技术指指标范围内结果判定为合格,不在范围内就定为不合格
function getBlurs(data){
	var technicalIndicatorsValue = $("#technicalIndicators"+data +" option:selected").text();
	var detectionResultValue = $("#detectionResult"+data).val();
	if(technicalIndicatorsValue != "请选择" && detectionResultValue != ""){
		getDetectionResultValue(data,detectionResultValue,technicalIndicatorsValue);
	}
}

//判断检测结果是否在技术指指标范围内
function getDetectionResultValue(data,detectionResultValue,technicalIndicatorsValue){
	var technicalIndicatorsValue1 = technicalIndicatorsValue.substring(0,technicalIndicatorsValue.indexOf("-"));
	var technicalIndicatorsValue2 = technicalIndicatorsValue.substring(technicalIndicatorsValue.indexOf("-")+1,technicalIndicatorsValue.length);
	var html = "";
	if(parseInt(detectionResultValue) >= parseInt(technicalIndicatorsValue1) && parseInt(detectionResultValue) <= parseInt(technicalIndicatorsValue2)){
		html += "<option value='0'>合格</option>"
			$("#resultDetermination"+data).html(html);
	}else{
		html += "<option value='1'>不合格</option>"
			$("#resultDetermination"+data).html(html);
	}
}

//只能入力数字和小数点  
function keepNumOrDecimal(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数 
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

// 保存
function save(){
	// 合同编号
	var telephone = $("#telephones").val();
	if(telephone == ""){
		swal("操作失败", "电话不能为空", "info");
		return;
	}
	//检测员
	var inspector = $("#inspector").val();
	if(inspector == ""){
		swal("操作失败", "检测员不能为空", "info");
		return;
	}
	//检测时间
	var testDate = $("#testDate").val();
	if(testDate == ""){
		swal("操作失败", "检测时间不能为空", "info");
		return;
	}
	
	var params = {
			"id":id,
			"flag":flag,
			"testreportId":$("#testreportId").val(),
			"telephone" : telephone,
			"Remarks" : $("#Remarks").val(),
			"inspector" : inspector,
			"testDate" : testDate,
			"Creater" : Creater
	};
	// 入库单检测报告单明细
	var testreportsaledetailedList = [];
	var i = 0;
	var message = "";
	$('#tableList1 tbody').find('tr').each(function () { 
		var testreportsaledetailed = {};
		$(this).find('td').each(function () {				
			$(this).find('input').each(function () {                         //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
	              var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (idValue !=''){
	            	  eval("testreportsaledetailed."+name+"=" + idValue);
	              } else {
	            	  if (name == 'detectionResult'){
	            		  message += ("\n")+"第"+i+"行检测结果不能为空";
	            	  }
	              }              
	           }
	        })
	        $(this).find('select').each(function () {                        //获取td中input的值
	           if($(this).attr("name")) {                                    //myName是input标签的一个自定义属性
		          var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (idValue !=''&& name!="detectionProject"){
	            	  eval("testreportsaledetailed."+name+"=" + idValue);
	              }else if (name == "detectionProject") {
	            	  testreportsaledetailed.detectionProject = idValue;
	              }else if (idValue !='') {
	            	  if (name == 'materielName'){
	            		  message += ("\n")+"第"+i+"行物料名称不能为空";
	            	  }
	            	  if (name == 'detectionProject'){
	            		  message += ("\n")+"第"+i+"行检测项目不能为空";
	            	  }
	            	  if (name == 'technicalIndicators'){
	            		  message += ("\n")+"第"+i+"行技术指标不能为空";
	            	  }
	            	  if (name == 'resultDetermination'){
	            		  message += ("\n")+"第"+i+"行结果判定不能为空";
	            	  }
	              } 
	           }
	        }) 
	        
		})				  
		testreportsaledetailedList.push(testreportsaledetailed);	
		i++;
	})
	if (message!=""){
		swal("操作失败", "出库单检测报告单明细"+message, "info");
		return;
	}
	params.testreportsaledetailedList = JSON.stringify(testreportsaledetailedList);
	
	$.ajax({
        type: "post",
        url: '../../testreportsaledetailed/addTestreportNameInfos.action',
        async:false,
        data:params,
        dataType: "json",
        success: function (data) {
       	 if(data.code == "success"){
 			swal({
 				title: data.message,
 				text: "",
 				type: data.code,
 				confirmButtonText: '确定',
 				cancelButtonFont: '微软雅黑',
 			});
 			testreportInfoByIdFalg = false;
 			getTestreportInfoByIds();
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
}
// 删除入库单检测报告单明细
function removeTr1(obj,data){
	if($("#testreportsaledetailedId"+data).val() == 0 ){
		var tr= $(obj).parent().parent();
	    tr.remove();
	    var len = $('#tableList1 tr').length;
	    for (var i = 0; i < len; i++) {
			$('#tableList1 tr:eq('+i+') td:first').text(i);		
		}	
	}else{
		var params = {
				"testreportsaledetailedId":$("#testreportsaledetailedId"+data).val(),
				"Creater" : Creater
		};
		$.ajax({
	        type: "post",
	        url: '../../testreportsaledetailed/deleteTestreportsaledetailed.action',
	        async:false,
	        data:params,
	        dataType: "json",
	        success: function (data) {
	        	var tr= $(obj).parent().parent();
	    	    tr.remove();
	    	    var len = $('#tableList1 tr').length;
	    	    for (var i = 0; i < len; i++) {
	    			$('#tableList1 tr:eq('+i+') td:first').text(i);		
	    		}
	      }
		});
	}
}

