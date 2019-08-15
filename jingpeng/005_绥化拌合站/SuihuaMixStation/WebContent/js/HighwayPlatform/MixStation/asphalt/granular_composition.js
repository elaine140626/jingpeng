//当前登录用户
var username = "";
//0:新增  1:修改  2:复制
var flag;
//级配id
var gId;

$(function(){
	
	//获取用户信息
	$.ajax({
        type: "post",
        url: "../../UserInfo/getPtUserInfo.action",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	//获取当前登录人做显示
        	username = data.name;
        }
    });
	
	//查询拌合站名称
	getAllOrgName();
	
	//查询所有级配信息
	getAllAsphaltGradingInfo();
});

//查询所有级配信息
function getAllAsphaltGradingInfo(){
	var table = $('#asphalt_gradId').dataTable();
	table.fnDestroy();
	$('#asphalt_gradId').DataTable({
        "paging" : true,
        "lengthChange" : false,
        "bLengthChange": false,
        "pageLength" : 14,
        "searching" : false,
        "ordering" : false,
        "info" : true,
        "sInfo" : true,
        "autoWidth" : false,
        "language": {
            "lengthMenu": "每页 _MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
            "infoEmpty": "无记录",
            "sSearch": "在结果中查找：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "oPaginate": {
                "sFirst": "第一页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "最后一页"
            },
        },
        "ajax" : {
            type: "post",
            url: "../../granularComposition/getAllAsphaltGradingInfo.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [ {
            "data" : "id"
        }, {
            "data" : "id",
             render : function(data, type, row, meta) {
                 	var html = '';
     	  		    	html += "<a onclick=updateGrading("+row.id+","+row.isEnable+","+row.isUsed+")>修改&nbsp;</a>";
     	  		    	html +=	"<a onclick=delGrading("+row.id+","+row.isEnable+","+row.isUsed+")>删除&nbsp;</a>";
     	  		    	html +=	"<a onclick=copyGrading("+row.id+")>复制&nbsp;</a>";
     	  		    	html += "<a onclick=isEnableGrading("+row.id+","+row.mpId+")>启用</a>";
     	  		    return html;
             			
             }
        }, {
            "data" : "orgName"
           
        }, {
            "data" : "gradeCode",
            render : function(data, type, row, meta) {
          	   html ="<span><a onclick='detailed("+row.id+")'>"+data+"</a></span>";
                 return html;
            }
        }, {
            "data" : "materialName",
            render : function(data, type, row, meta) {
        			return  row.materialName +"/"+row.materialModel;
		}
        }, {
            "data" : "mixNumber"
        }, {
        	"data" : "isEnable",
        	render : function(data, type, row, meta) {
        		if(data == 0){
        			data = "未启用";
        		}else if(data == 1){
        			data = "启用";
        		}
        		return data;
        	}
        }, {
            "data" : "operator"
        }, {
            "data" : "createDate"
        }, {
            "data" : "remarks"
        }
        ],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一
           return nRow;
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        }]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#asphalt_gradId').css('text-align','center');
}

//跳转新增页面
function getSelectView(){
	flag = 0;
	//弹出新增页面
	$('#loginModal').modal('show');
	//保存按钮
	$("#gradSave").show();
	//清空操作
	$("#orgId").val("");
	$("#mixNumber").empty();
	$("#gradTable1 input:text").val("");
	$("#gradTable2 [name = meshId]").val("0");
	$("#gradTable2 input:text").val("");
}

//查询拌合站名称
function getAllOrgName(){
	$.ajax({
		type : "POST",
		url : "../../PlatformAsphaltData/getOrgId.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				if (data[i].org_Type == '2'){
					html += "<option value='" + data[i].Id + "'>"
					+ data[i].Org_Name + "</option>"
				}			
			}
			$("#orgId").empty();
			$("#orgId").html(html);
		}
	});
}

//拌合站改变事件
function getOrgId(value){
	//联动清空文本框
	$("#materialName").val("");
	$("#materialModel").val("");
	//获取筛孔信息
	$("#gradTable2 [name = meshId]").val("0");
	getMeshSizeInfo(value);
	//获取成产配合比
	$.ajax({
		type : "POST",
		url : "../../granularComposition/getApshaltMixProportionUsed.action",
		async : false,
		data : {"orgId" : value},
        dataType : "json",
        success : function (data) {
        	if(data != null && data.length > 0){
        		//清空配比下拉框
        		$("#mixNumber").empty();
        		$("#mixNumber").append("<option value=''>请选择</option>");
        		//获取生产配比下拉框值
        		for (var i = 0; i < data.length; i++) {
        			$("#mixNumber").append("<option value='"+data[i].id+"'>"+data[i].mix_Number+"</option>");
        		}
        	}else{
        		//清空
        		$("#mixNumber").empty();
        	}
        }
	});
}

//生产配比改变事件
function getmaterialInfo(){
	//拌合站id
	var orgId = $("#orgId option:selected") .val();
	//生产配比编号
	var mixNumber = $("#mixNumber option:selected") .text();
	$.ajax({
		type : "POST",
		url : "../../granularComposition/getApshaltMixProportionUsed.action",
		async : false,
		data : {"orgId" : orgId,"mixNumber" : mixNumber},
        dataType : "json",
        success : function (data) {
        	if(data != null && data.length > 0){
        		$("#materialName").val(data[0].material_Name);
        		$("#materialModel").val(data[0].material_Model);
        	}else{
        		//清空
        		$("#materialName").val("");
        		$("#materialModel").val("");
        	}
        }
	});
}

//获取筛孔信息
function getMeshSizeInfo(orgId){
	$.ajax({
		type : "POST",
		url : "../../granularComposition/getAllMeshSizeInfo.action",
		async : false,
		data : {"orgId":orgId},
        dataType : "json",
        success : function (data) {
        	if(data != null && data.length > 0){
        		//清空
        		$("#meshSize1").empty();
        		$("#meshSize2").empty();
        		$("#meshSize3").empty();
        		$("#meshSize4").empty();
        		$("#meshSize5").empty();
        		$("#meshSize6").empty();
        		$("#meshSize7").empty();
        		$("#meshSize8").empty();
        		$("#meshSize9").empty();
        		$("#meshSize10").empty();
        		$("#meshSize11").empty();
        		$("#meshSize12").empty();
        		$("#meshSize13").empty();
        		$("#meshSize14").empty();
        		$("#meshSize15").empty();
        		$("#meshSize1").append("<option value='0'></option>");
        		$("#meshSize2").append("<option value='0'></option>");
        		$("#meshSize3").append("<option value='0'></option>");
        		$("#meshSize4").append("<option value='0'></option>");
        		$("#meshSize5").append("<option value='0'></option>");
        		$("#meshSize6").append("<option value='0'></option>");
        		$("#meshSize7").append("<option value='0'></option>");
        		$("#meshSize8").append("<option value='0'></option>");
        		$("#meshSize9").append("<option value='0'></option>");
        		$("#meshSize10").append("<option value='0'></option>");
        		$("#meshSize11").append("<option value='0'></option>");
        		$("#meshSize12").append("<option value='0'></option>");
        		$("#meshSize13").append("<option value='0'></option>");
        		$("#meshSize14").append("<option value='0'></option>");
        		$("#meshSize15").append("<option value='0'></option>");
        		//获取下拉框值
        		for (var i = 0; i < data.length; i++) {
        			$("#meshSize1").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize2").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize3").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize4").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize5").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize6").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize7").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize8").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize9").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize10").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize11").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize12").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize13").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize14").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        			$("#meshSize15").append("<option value='"+data[i].id+"'>"+data[i].meshSize+"</option>");
        		}
        	}else{
        		$("#meshSize1").empty();
        		$("#meshSize2").empty();
        		$("#meshSize3").empty();
        		$("#meshSize4").empty();
        		$("#meshSize5").empty();
        		$("#meshSize6").empty();
        		$("#meshSize7").empty();
        		$("#meshSize8").empty();
        		$("#meshSize9").empty();
        		$("#meshSize10").empty();
        		$("#meshSize11").empty();
        		$("#meshSize12").empty();
        		$("#meshSize13").empty();
        		$("#meshSize14").empty();
        		$("#meshSize15").empty();
        		$("#meshSize1").append("<option value='0'></option>");
        		$("#meshSize2").append("<option value='0'></option>");
        		$("#meshSize3").append("<option value='0'></option>");
        		$("#meshSize4").append("<option value='0'></option>");
        		$("#meshSize5").append("<option value='0'></option>");
        		$("#meshSize6").append("<option value='0'></option>");
        		$("#meshSize7").append("<option value='0'></option>");
        		$("#meshSize8").append("<option value='0'></option>");
        		$("#meshSize9").append("<option value='0'></option>");
        		$("#meshSize10").append("<option value='0'></option>");
        		$("#meshSize11").append("<option value='0'></option>");
        		$("#meshSize12").append("<option value='0'></option>");
        		$("#meshSize13").append("<option value='0'></option>");
        		$("#meshSize14").append("<option value='0'></option>");
        		$("#meshSize15").append("<option value='0'></option>");
        	}
        }
	});
}

//保存
function addGranular(){
	//获取页面数据
	var data = formToJson($("#addForm"));
	if(data.orgId == ""){
		swal("操作失败","拌合站名称不能为空!", "error");
		return;
	}
	if(data.mixNumber == ""){
		swal("操作失败","生产配比编号不能为空!", "error");
		return;
	}
	if(data.materialName == ""){
		swal("操作失败","混合料名称不能为空!", "error");
		return;
	}
	if(data.materialModel == ""){
		swal("操作失败","混合料型号不能为空!", "error");
		return;
	}
	if(data.gradeCode == ""){
		swal("操作失败","级配编号不能为空!", "error");
		return;
	}
	var meshSize1 = $("#meshSize1 option:selected").text();
	var meshSize2 = $("#meshSize2 option:selected").text();
	var meshSize3 = $("#meshSize3 option:selected").text();
	var meshSize4 = $("#meshSize4 option:selected").text();
	var meshSize5 = $("#meshSize5 option:selected").text();
	var meshSize6 = $("#meshSize6 option:selected").text();
	var meshSize7 = $("#meshSize7 option:selected").text();
	var meshSize8 = $("#meshSize8 option:selected").text();
	var meshSize9 = $("#meshSize9 option:selected").text();
	var meshSize10 = $("#meshSize10 option:selected").text();
	var meshSize11 = $("#meshSize11 option:selected").text();
	var meshSize12 = $("#meshSize12 option:selected").text();
	var meshSize13 = $("#meshSize13 option:selected").text();
	var meshSize14 = $("#meshSize14 option:selected").text();
	var meshSize15 = $("#meshSize15 option:selected").text();
	if(Number(meshSize1) < Number(meshSize2)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize2) < Number(meshSize3)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize3) < Number(meshSize4)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize4) < Number(meshSize5)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize5) < Number(meshSize6)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize6) < Number(meshSize7)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize7) < Number(meshSize8)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize8) < Number(meshSize9)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize9) < Number(meshSize10)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize10) < Number(meshSize11)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize11) < Number(meshSize12)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize12) < Number(meshSize13)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize13) < Number(meshSize14)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	if(Number(meshSize14) < Number(meshSize15)){
		swal("操作失败","筛孔必须一次递减添加!", "error");
		return;
	}
	//筛孔通过率
	var asphaltGradDetailedList = [];
	var gradDetailed = {};
	for (var i = 0; i < 15; i++) {
		//清空操作
		gradDetailed = {};
		//筛孔
		if(data.meshId[i] == undefined){
			gradDetailed.meshId = "0";
		}else{
			gradDetailed.meshId = data.meshId[i];
		}
		//1仓
		gradDetailed.ware1 = data.ware1[i];
		/*if(data.meshId[i] != "0" && data.ware1[i] == ""){
			swal("操作失败","筛孔有值，1#仓通过百分率必须有值!", "error");
			return;
		}*/
		//2仓
		gradDetailed.ware2 = data.ware2[i];
		/*if(data.meshId[i] != "0" && data.ware2[i] == ""){
			swal("操作失败","筛孔有值，2#仓通过百分率必须有值!", "error");
			return;
		}*/
		//3仓
		gradDetailed.ware3 = data.ware3[i];
		/*if(data.meshId[i] != "0" && data.ware3[i] == ""){
			swal("操作失败","筛孔有值，3#仓通过百分率必须有值!", "error");
			return;
		}*/
		//4仓
		gradDetailed.ware4 = data.ware4[i];
		/*if(data.meshId[i] != "0" && data.ware4[i] == ""){
			swal("操作失败","筛孔有值，4#仓通过百分率必须有值!", "error");
			return;
		}*/
		//5仓
		gradDetailed.ware5 = data.ware5[i];
		/*if(data.meshId[i] != "0" && data.ware5[i] == ""){
			swal("操作失败","筛孔有值，5#仓通过百分率必须有值!", "error");
			return;
		}*/
		//6仓
		gradDetailed.ware6 = data.ware6[i];
		/*if(data.meshId[i] != "0" && data.ware6[i] == ""){
			swal("操作失败","筛孔有值，6#仓通过百分率必须有值!", "error");
			return;
		}*/
		//冷粉1
		gradDetailed.coldPowder1 = data.coldPowder1[i];
		/*if(data.meshId[i] != "0" && data.coldPowder1[i] == ""){
			swal("操作失败","筛孔有值，冷粉1通过百分率必须有值!", "error");
			return;
		}*/
		//冷粉2
		gradDetailed.coldPowder2 = data.coldPowder2[i];
		/*if(data.meshId[i] != "0" && data.coldPowder2[i] == ""){
			swal("操作失败","筛孔有值，冷粉2通过百分率必须有值!", "error");
			return;
		}*/
		//热粉
		gradDetailed.hotPowder = data.hotPowder[i];
		/*if(data.meshId[i] != "0" && data.hotPowder[i] == ""){
			swal("操作失败","筛孔有值，热粉通过百分率必须有值!", "error");
			return;
		}*/
		//级配上限
		gradDetailed.upperLimit = data.upperLimit[i];
		/*if(data.meshId[i] != "0" && data.upperLimit[i] == ""){
			swal("操作失败","筛孔有值，级配上限通过百分率必须有值!", "error");
			return;
		}*/
		//级配下限
		gradDetailed.lowerLimit = data.lowerLimit[i];
		/*if(data.meshId[i] != "0" && data.lowerLimit[i] == ""){
			swal("操作失败","筛孔有值，级配下限通过百分率必须有值!", "error");
			return;
		}*/
		//创建人
		gradDetailed.operator = username;
		//添加数组
		asphaltGradDetailedList.push(gradDetailed);
	}
	//转换成json
	data.asphaltGradDetailedList = JSON.stringify(asphaltGradDetailedList);
	//创建人
	data.operator = username;
	//0:新增  1:修改  2:复制
	data.flag = flag; 
	//修改级配的id
	data.id = gId;
	$.ajax({
		type : "POST",
		url : "../../granularComposition/addAsphaltGradingInfo.action",
		data : data,
		async:false,
		dataType : "json",
		success : function(data) {			
			if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					//添加页面隐藏
					$('#loginModal').modal('hide');
					//查询所有级配信息
					getAllAsphaltGradingInfo();
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});	
}

//修改级配相关信息
function updateGrading(id,isEnable,isUsed){
	if(id != null && id != ""){
		if(isEnable == 1 || isUsed == 1){//已启用
			swal({
				title: "该级配已启用过，不可修改!",
				text: "",
				type: "error",
				confirmButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			},
			function(){
				//查询所有级配信息
				getAllAsphaltGradingInfo();
			});
		}else{
			gId = id;
			flag = 1;
			//修改页面显示
			$('#loginModal').modal('show');
			//保存按钮
			$("#gradSave").show();
			//清空操作
			$("#orgId").val("");
			$("#mixNumber").empty();
			$("#gradTable1 input:text").val("");
			$("#gradTable2 [name = meshId]").val("0");
			$("#gradTable2 input:text").val("");
			$.ajax({
				type : "POST",
				url :  "../../granularComposition/getAllInfoById.action",
				data : {"id" : id},
				async:false,
				dataType : "json",
				success : function(data) {	
					//级配信息
					var asphaltGradingList = data.asphaltGradingList;
					//筛孔通过率信息
					var asphaltGradDetailedList = data.asphaltGradDetailedList;
					//拌合站名称
					$("#orgId").val(asphaltGradingList[0].orgId);
					//生产配合比编号
					getOrgId(asphaltGradingList[0].orgId);
					$("#mixNumber").val(asphaltGradingList[0].mpId);
					//混合料名称
					$("#materialName").val(asphaltGradingList[0].materialName);
					//型号
					$("#materialModel").val(asphaltGradingList[0].materialModel);
					//级配编号
					$("#gradeCode").val(asphaltGradingList[0].gradeCode);
					//备注
					$("#remarks").val(asphaltGradingList[0].remarks);
					if(asphaltGradDetailedList.length > 0){
						for (var i = 0; i < asphaltGradDetailedList.length; i++) {
							//筛孔
							$("#meshSize"+(i+1)).val(asphaltGradDetailedList[i].meshId);
							//1仓
							$("#ware1"+(i+1)).val(asphaltGradDetailedList[i].ware1);
							//2仓
							$("#ware2"+(i+1)).val(asphaltGradDetailedList[i].ware2);
							//3仓
							$("#ware3"+(i+1)).val(asphaltGradDetailedList[i].ware3);
							//4仓
							$("#ware4"+(i+1)).val(asphaltGradDetailedList[i].ware4);
							//5仓
							$("#ware5"+(i+1)).val(asphaltGradDetailedList[i].ware5);
							//6仓
							$("#ware6"+(i+1)).val(asphaltGradDetailedList[i].ware6);
							//冷粉1
							$("#coldPowder1"+(i+1)).val(asphaltGradDetailedList[i].coldPowder1);
							//冷粉2
							$("#coldPowder2"+(i+1)).val(asphaltGradDetailedList[i].coldPowder2);
							//热粉
							$("#hotPowder"+(i+1)).val(asphaltGradDetailedList[i].hotPowder);
							//级配上限
							$("#upperLimit"+(i+1)).val(asphaltGradDetailedList[i].upperLimit);
							//级配下限
							$("#lowerLimit"+(i+1)).val(asphaltGradDetailedList[i].lowerLimit);
						}
					}
				}
			});
		}
	}
}

//删除级配
function delGrading(id,isEnable,isUsed){
	if(isEnable == 1 || isUsed == 1){//已启用
		swal({
			title: "该级配已启用过，不可删除!",
			text: "",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			//查询所有级配信息
			getAllAsphaltGradingInfo();
		});
	}else{
		swal({
			title: "确定要删除吗？",
			text:"删除后将无法恢复，请谨慎操作！",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: '#AEDEF4',
			confirmButtonText: '确定',
			cancelButtonText: '取消',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			$.ajax({
				type: "post",
				url: '../../granularComposition/deleteAsphaltGradingInfo.action',
				async:false,
				data:{"id":id,"modifier":username},
				dataType: "json",
				success: function (data) {
					if(data.code=="success"){
						setTimeout(function(){swal({
							title: data.message,
							type: "success",
							cancelButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						},
						function(){
							//查询所有级配信息
							getAllAsphaltGradingInfo();
						}); },200);
					}else{
						setTimeout(function(){swal({
							title: data.message,
							type: "error",
							cancelButtonText: '确定',
							cancelButtonFont: '微软雅黑',
						}); 
						});
					}
				}
			});
		});
	}
}

//复制级配
function copyGrading(id){
	if(id != null && id != ""){
		gId = id;
		flag = 2;
		//修改页面显示
		$('#loginModal').modal('show');
		//保存按钮
		$("#gradSave").show();
		$.ajax({
			type : "POST",
			url :  "../../granularComposition/getAllInfoById.action",
			data : {"id" : id},
			async:false,
			dataType : "json",
			success : function(data) {	
				//级配信息
				var asphaltGradingList = data.asphaltGradingList;
				//筛孔通过率信息
				var asphaltGradDetailedList = data.asphaltGradDetailedList;
				//拌合站名称
				$("#orgId").val(asphaltGradingList[0].orgId);
				//生产配合比编号
				getOrgId(asphaltGradingList[0].orgId);
				$("#mixNumber").val(asphaltGradingList[0].mpId);
				//混合料名称
				$("#materialName").val(asphaltGradingList[0].materialName);
				//型号
				$("#materialModel").val(asphaltGradingList[0].materialModel);
				//级配编号
				$("#gradeCode").val(asphaltGradingList[0].gradeCode);
				//备注
				$("#remarks").val(asphaltGradingList[0].remarks);
				if(asphaltGradDetailedList.length > 0){
					for (var i = 0; i < asphaltGradDetailedList.length; i++) {
						//筛孔
						$("#meshSize"+(i+1)).val(asphaltGradDetailedList[i].meshId);
						//1仓
						$("#ware1"+(i+1)).val(asphaltGradDetailedList[i].ware1);
						//2仓
						$("#ware2"+(i+1)).val(asphaltGradDetailedList[i].ware2);
						//3仓
						$("#ware3"+(i+1)).val(asphaltGradDetailedList[i].ware3);
						//4仓
						$("#ware4"+(i+1)).val(asphaltGradDetailedList[i].ware4);
						//5仓
						$("#ware5"+(i+1)).val(asphaltGradDetailedList[i].ware5);
						//6仓
						$("#ware6"+(i+1)).val(asphaltGradDetailedList[i].ware6);
						//冷粉1
						$("#coldPowder1"+(i+1)).val(asphaltGradDetailedList[i].coldPowder1);
						//冷粉2
						$("#coldPowder2"+(i+1)).val(asphaltGradDetailedList[i].coldPowder2);
						//热粉
						$("#hotPowder"+(i+1)).val(asphaltGradDetailedList[i].hotPowder);
						//级配上限
						$("#upperLimit"+(i+1)).val(asphaltGradDetailedList[i].upperLimit);
						//级配下限
						$("#lowerLimit"+(i+1)).val(asphaltGradDetailedList[i].lowerLimit);
					}
				}
			}
		});
	}
}

//启用级配
function isEnableGrading(id,mpId){
	if(id != null && id != ""){
		$.ajax({
			type : "POST",
			url : "../../granularComposition/enableAsphaltGradingInfo.action",
			data : {"id":id,"mpId":mpId},
			async:false,
			dataType : "json",
			success : function(data) {			
				if(data.code=="success"){
					swal({
						title: data.message,
						text: "",
						type: "success",
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						//查询所有级配信息
						getAllAsphaltGradingInfo();
					});
					
				}else{
					swal("操作失败",data.message, "error");
				}
			}
		});	
	}
}

//查看级配
function detailed(id){
	if(id != null && id != ""){
		gId = id;
		//查看页面显示
		$('#loginModal').modal('show');
		//保存按钮隐藏
		$("#gradSave").hide();
		$.ajax({
			type : "POST",
			url :  "../../granularComposition/getAllInfoById.action",
			data : {"id" : id},
			async:false,
			dataType : "json",
			success : function(data) {	
				//级配信息
				var asphaltGradingList = data.asphaltGradingList;
				//筛孔通过率信息
				var asphaltGradDetailedList = data.asphaltGradDetailedList;
				//拌合站名称
				$("#orgId").val(asphaltGradingList[0].orgId);
				//生产配合比编号
				getOrgId(asphaltGradingList[0].orgId);
				$("#mixNumber").val(asphaltGradingList[0].mpId);
				//混合料名称
				$("#materialName").val(asphaltGradingList[0].materialName);
				//型号
				$("#materialModel").val(asphaltGradingList[0].materialModel);
				//级配编号
				$("#gradeCode").val(asphaltGradingList[0].gradeCode);
				//备注
				$("#remarks").val(asphaltGradingList[0].remarks);
				if(asphaltGradDetailedList.length > 0){
					for (var i = 0; i < asphaltGradDetailedList.length; i++) {
						//筛孔
						$("#meshSize"+(i+1)).val(asphaltGradDetailedList[i].meshId);
						//1仓
						$("#ware1"+(i+1)).val(asphaltGradDetailedList[i].ware1);
						//2仓
						$("#ware2"+(i+1)).val(asphaltGradDetailedList[i].ware2);
						//3仓
						$("#ware3"+(i+1)).val(asphaltGradDetailedList[i].ware3);
						//4仓
						$("#ware4"+(i+1)).val(asphaltGradDetailedList[i].ware4);
						//5仓
						$("#ware5"+(i+1)).val(asphaltGradDetailedList[i].ware5);
						//6仓
						$("#ware6"+(i+1)).val(asphaltGradDetailedList[i].ware6);
						//冷粉1
						$("#coldPowder1"+(i+1)).val(asphaltGradDetailedList[i].coldPowder1);
						//冷粉2
						$("#coldPowder2"+(i+1)).val(asphaltGradDetailedList[i].coldPowder2);
						//热粉
						$("#hotPowder"+(i+1)).val(asphaltGradDetailedList[i].hotPowder);
						//级配上限
						$("#upperLimit"+(i+1)).val(asphaltGradDetailedList[i].upperLimit);
						//级配下限
						$("#lowerLimit"+(i+1)).val(asphaltGradDetailedList[i].lowerLimit);
					}
				}
			}
		});
	}
}

//数字decimal类型check(integer:整数位；decimal：小数位)(onkeyup)
function clearNoDecimal(obj,integer,decimal){  			
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的   
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
    // 小数位check
    if (decimal == 1){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入一个小数   
    }
    if (decimal == 2){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数   
    }	
    if (decimal == 3){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d).*$/,'$1$2.$3');//只能输入三个小数   
    }	
    if (decimal == 4){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/,'$1$2.$3');//只能输入四个小数   
    }	
    if (obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额  
    	// 整数位check
    	if(obj.value.length > integer){
	    	obj.value = obj.value.substring(0,integer);
	    }else{
	    	obj.value= parseFloat(obj.value);
	    } 
    }  
}

// 失去焦点时check最后一位不是小数点(onblur)
function checkPoint(obj){
	clearNoDecimal(obj);
	if (obj.value.charAt(obj.value.length - 1)=='.'){			
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}		
}