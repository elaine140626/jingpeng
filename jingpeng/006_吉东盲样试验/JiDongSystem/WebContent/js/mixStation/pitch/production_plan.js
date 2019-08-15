var baseUrl = "";
var addOrupdateTag = 1;//添加和修改的标识
$(function(){
	$("#locationText").html(orgName);
	baseUrl = getContextPath(); 
	//获取工程部位/用途
	getProjPos();
	//获取施工单位
	getConstructionUnit();
	// 画面数据显示
	var param = {};
	param.orgId = orgId;
	if(orgId != ''){
		getListInfo(param);
	}
});
function getListInfo(param){
	var table = $('#list').dataTable();
	table.fnDestroy();
	$("#list").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 14,
        "searching": false,
        "ordering": false,
        "info": true,
        "sInfo": true,
        "autoWidth": false,
        "iDisplayStart" : 0,
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
            url: baseUrl + "/asphaltProductionPlan/getAsphaltProductionPlanInfo.action",
            dataSrc: "data",
            data: function (d) {
                return param;
            }
        },
        "deferRender": true,
        "columns": [ 
			{"data" : "id"},
			{"data" : "planNo"},
			{"data" : "proportionCode"},
			{"data" : "equipmentName"},
			{"data" : "nameAndModel"},
			{"data" : "proportion_Code"},
			{"data" : "gradeCode"},
			{"data" : "projPos"},
			{"data" : "constructionUnit"},
			{"data" : "scheduledProduction"},
			{
				"data" : "isCancel",
				render : function(data,type,row){
					if(row.isCancel == 0){
						data = "未作废";
					}else if(row.isCancel == 1){
						data = "作废";
					}
					return data;
				}
			},
			{"data" : "startTime"},
			{"data" : "createDate"},
			{"data" : "cancelReason"},
			{
				"data" : "id",
				render:function(data,type,row){
					var html = "";
        			/*html += "<span><a onclick=updatePlan("+row.id+")><img src=\"../../image/images/xiu.png\" width=\"17\" height=\"16\" alt=\"修改\" title=\"修改\"></a></span>";*/
        			if(row.isCancel == 0){
        				html +=	"<span><a onclick=zuoFeiPlan("+row.id+")><img src=\"../../image/images/zuofei.png\" width=\"17\" height=\"16\" alt=\"作废\" title=\"作废\"></a></span>";
        			}
    	  		    html +=	"<span><a onclick=deletePlan("+row.id+")><img src=\"../../image/images/del.png\" width=\"17\" height=\"16\" alt=\"删除\" title=\"删除\"></a></span>";
    	  		    return html;
				}
			}
			],
        "columnDefs": [{
        	"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一        
            if(aData.isCancel == 1){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#FFB90F");        
            	//设置满足条件行的字体颜色                	
            	$(nRow).css("color", "white !important");                	
            }
            // 获取所有数据
            return nRow;
        }
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

//模糊查询
function search(){
	var param = {};
	param.orgId = orgId;
    param.materialName = $("#material_Name").val();
    param.materialModel = $("#material_Model").val();
    param.planNo = $("#plan_No").val();
    if(orgId != ''){
		getListInfo(param);
	}
}

//输入数字
function num(obj){
	obj.value = obj.value.replace(/[^\d.]/g,""); 
	obj.value = obj.value.replace(/^\./g,"");
	obj.value = obj.value.replace(/\.{2,}/g,".");
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}

//添加
function addOrUpdate(){
	var url = "";
	//获取页面数据
	var data = formToJson($("#tabForm"));
	if(data.planNo == null || data.planNo == "") {
		swal("操作失败","生产计划编号不能为空!", "info");
		return;
	}
	if(data.equId == null || data.equId == "") {
		swal("操作失败","拌合设备名称不能为空!", "info");
		return;
	}
	if(data.materialName == null || data.materialName == "") {
		swal("操作失败","产品名称不能为空!", "info");
		return;
	}
	if(data.productId == null || data.productId == "") {
		swal("操作失败","产品型号不能为空!", "info");
		return;
	}
	if(data.prodId == null || data.prodId == "") {
		swal("操作失败","生产配合比编号不能为空!", "info");
		return;
	}
	/*if(data.projPos == null || data.projPos == "") {
		swal("操作失败","工程部位/用途不能为空!", "info");
		return;
	}
	if(data.constructionUnit == null || data.constructionUnit == "") {
		swal("操作失败","施工单位不能为空!", "info");
		return;
	}*/
	if(data.scheduledProduction == null || data.scheduledProduction == "") {
		swal("操作失败","计划生产量不能为空!", "info");
		return;
	}
	if(data.startTime == null || data.startTime == "") {
		swal("操作失败","计划生产时间不能为空!", "info");
		return;
	}
	if(addOrupdateTag == 1) {
		data.orgId = orgId;
		url =  baseUrl + "/asphaltProductionPlan/addAsphaltProductionPlan.action";
	} else {
		url =  baseUrl + "/asphaltProductionPlan/updateAsphaltProductionPlan.action";
	}
	$.ajax({
		type : "POST",
		url : url,
		data : data,
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
					search();
					$('#loginModal').modal('hide');
				});
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
}

//获取拌合站设备名称
function getAddPlan(){
	$("#planNo").removeAttr("readonly");
	$("#tab input:text").val("");
	$("#tab select").html("<option></option>")
    $("#loginModal").modal("show");
	if(orgId != ''){
		$.ajax({
			type : "POST",
			url : baseUrl + "/asphaltProductionPlan/getEquipmentInfo.action",
			data : {"equipmentType":0,"orgId":orgId},
			dataType : "json",
			success : function(data) {
				// 清空
				$("#equId").empty();
				$("#equId").append("<option value=''></option>");
				for(var i = 0; i < data.length; i++) {
					$("#equId").append("<option value='"+data[i].id+"'>"+data[i].equipmentName+"</option>");
				}
			}
		});
	}
}

//获取产品名称
function getMcName(){
	$("#materialName").removeAttr("disabled");
    $("#materialModel").removeAttr("disabled");
    $("#proportionCode").removeAttr("disabled");
    $("#materialName").empty();
    $("#materialModel").empty();
    $("#proportionCode").empty();
    $("#gradId").empty();
    $.ajax({
    	type : "POST",
    	url : baseUrl + "/asphaltProductionPlan/getMaterialNameList.action",
    	data : {"materialType":0,"materialMold":1,"orgId":orgId},
    	dataType : "json",
    	success : function(data){
    		// 清空
			$("#materialName").empty();
			$("#materialName").append("<option value=''></option>");
			for(var i = 0; i < data.length; i++) {
				$("#materialName").append("<option value='"+data[i].mateName+"'>"+data[i].mateName+"</option>");
			}
    	}
    });
}

//获取产品型号
function getMaterialModel(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProductionPlan/getMaterialModelList.action",
		data : {
				"materialType":0,
		        "materialMold":1,
		        "materialName":$("#materialName").val(),
		        "orgId":orgId
		        },
		dataType : "json",
		success : function(data) {
			// 清空
			$("#materialModel").empty();
			$("#materialModel").append("<option value=''></option>");
			for(var i = 0; i < data.length; i++) {
				$("#materialModel").append("<option value='"+data[i].id+"'>"+data[i].mateModel+"</option>");
			}
		}
	});
}

//获取生产配合比编号
function getProportionCode(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProductionPlan/getProportionCodeList.action",
		data : {
				"materialType":0,
				"orgId":orgId,
				"productId":$("#materialModel").val()
				},
		dataType : "json",
		success : function(data) {
			// 清空
			$("#proportionCode").empty();
			$("#proportionCode").append("<option value=''></option>");
			for(var i = 0; i < data.length; i++) {
				$("#proportionCode").append("<option value='"+data[i].id+"'>"+data[i].proportionCode+"</option>");
			}
		}
	});
}

//获取级配编号
function getGradeCode(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProductionPlan/getGradeCode.action",
		data : {"id":$("#proportionCode").val(),"orgId":orgId},
		dataType : "json",
		success : function(data) {
			// 清空
			$("#gradId").empty();
			$("#gradId").append("<option value=''></option>");
			for(var i = 0; i < data.length; i++) {
				$("#gradId").append("<option value='"+data[i].id+"'>"+data[i].gradeCode+"</option>");
			}
		}
	});
}

//修改生产计划
function updatePlan(id){
    //获取拌和设备名称
    getAddPlan();
	$("#planNo").attr("readonly","readonly");
	$("#loginModal").modal("show");
	addOrupdateTag = 2;
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProductionPlan/getProductionPlanById.action",
		data : {"id" : id},
		dataType : "json",
		success : function(data) {
			$("#planNo").val(data[0].planNo);
			$("#equId").val(data[0].equId);
			$("#materialName").html("<option selected='selected'>"+data[0].materialName+"</option>");
			$("#materialModel").html("<option selected='selected' value='"+data[0].productId+"'>"+data[0].materialModel+"</option>");
			$("#proportionCode").html("<option selected='selected' value='"+data[0].prodId+"'>"+data[0].proportionCode+"</option>");
			$("#gradeCode").val(data[0].gradeCode);
			$("#gradId").html("<option selected='selected' value='"+data[0].gradId+"'>"+data[0].gradeCode+"</option>");
			$("#constructionUnit").val(data[0].constructionUnit);
			$("#scheduledProduction").val(data[0].scheduledProduction);
			$("#startTime").val(data[0].startTime);
			$("#projPos").val(data[0].projPos);
			$("#id").val(data[0].id);
		}
	});
}

//删除生产计划
function deletePlan(id){
	swal({
		title: "确定操作吗？",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
		
	},function(){
		$.ajax({
			type : "POST",
			url : baseUrl + "/asphaltProductionPlan/deleteAsphaltProductionPlan.action",
			data : {"id":id},
			dataType : "json",
			success : function(data) {
				
				if(data.code=="success"){
					setTimeout(function(){swal({
						title: data.message,
						type: "success",
						cancelButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						search();
					}); },200);
				}else{
					setTimeout(function(){swal(data.message,"","error"); },200);
				}
			}
		});
	});
}

//获取工程部位/用途
function getProjPos(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProductionPlan/getProjPos.action",
		data : {},
		dataType : "json",
		success : function(data) {
			if(data != null){
				// 清空
				$("#projPoList").empty();
				$("#projPoList").append("<option value=''></option>");
				for(var i = 0; i < data.length; i++) {
					$("#projPoList").append("<option value='"+data[i].projPos+"'>"+data[i].projPos+"</option>");
				}
			}
		}
	});
}

//获取施工单位
function getConstructionUnit(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProductionPlan/getConstructionUnit.action",
		data : {},
		dataType : "json",
		success : function(data) {
			// 清空
			if(data != null){
				$("#constructionUnitList").empty();
				$("#constructionUnitList").append("<option value=''></option>");
				for(var i = 0; i < data.length; i++) {
					$("#constructionUnitList").append("<option value='"+data[i].cnstructionUnit+"'>"+data[i].cnstructionUnit+"</option>");
				}
			}
		}
	});
}

//作废
var zfId = "";
function zuoFeiPlan(id){
	zfId = id;
	$("#loginModal2").modal("show");
}

function zuofei(){
	//作废原因
	var cancelReason = $("#textarea").val();
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProductionPlan/zfAsphaltProductionPlan.action",
		data : {"id" : zfId,"cancelReason" : cancelReason},
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
					search();
					$('#loginModal2').modal('hide');
				});
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
}

//生产计划编号改变事件
function changePlanNo(value){
	$.ajax({
		type : "POST",
		url: baseUrl + "/asphaltProductionPlan/getAsphaltProductionPlanInfo.action",
		data: {"orgId" : orgId},
		dataType : "json",
		success : function(data){
			if(data.data != null && data.data.length > 0){
				for (var i = 0; i < data.data.length; i++) {
					if(data.data[i].planNo == value){
						setTimeout(function(){
							swal({
								title: "该生产计划编号已存在!",
								type: "error",
								cancelButtonText: '确定',
								cancelButtonFont: '微软雅黑',
						},
						function(){
							//清空
							$("#planNo").val("");
						}); },200);
					}
				}
			}
		}
	});
}