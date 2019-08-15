var baseUrl = "";
var addOrUpdateTag = 1;//修改和添加的标识
$(function(){
	$("#locationText").html(orgName);
	baseUrl = getContextPath();
	//获取产品名称
	getgetMaterialNameList();
	var param = {};
	param.orgId = orgId;
	if(orgId != ''){
		getList(param);
	}
});

//查询所有数据
function getList(param){
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
            url: baseUrl + "/asphaltProdProportion/getAllAsphaltProdProportion.action",
            dataSrc: "data",
            data: function (d) {
                return param;
            }
        },
        "deferRender": true,
        "columns": [ 
			{"data" : "id"},
			{"data" : "proportionCode"},
			{"data" : "gradeCode"},
			{"data" : "mproportionCode"},
			{"data" : "materNameAndModel"},
			{"data" : "operator"},
			{"data" : "createDate"},
			{"data" : "remarks"},
			{
				"data" : "id",
				render : function(data,type,row){
					var html = "";
					html += "<span><a onclick=updateProportion("+row.id+")><img src=\"../../image/images/xiu.png\" width=\"17\" height=\"16\" alt=\"修改\" title=\"修改\"></a></span>";
    	  		    html +=	"<span><a onclick=deleteProportion("+row.id+")><img src=\"../../image/images/del.png\" width=\"17\" height=\"16\" alt=\"删除\" title=\"删除\"></a></span>";
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
            // 获取所有数据
            return nRow;
        }
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

//模糊查询
function search(){
	var param = {};
	//生产配合比编号
	var proportion_Code = $("#proportion_Code").val();
	//产品名称
	var material_Name = $("#material_Name").val();
	//产品型号
	var material_Model = $("#material_Model").val();
	param.orgId = orgId;
	param.proportion_Code = proportion_Code;
	param.material_Name = material_Name;
	param.material_Model = material_Model;
	if(orgId != ''){
		getList(param);
	}
}

//只能输入数字
function num(obj){
	obj.value = obj.value.replace(/[^\d.]/g,""); 
	obj.value = obj.value.replace(/^\./g,"");
	obj.value = obj.value.replace(/\.{2,}/g,".");
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}

//新增页面和数据
function getAddView(){
	$("#proportionCode").removeAttr("readonly");
	$("#proportionCode").val("");
	$("#materialName").val("");
	$("#materialModel").empty();
	$("#mbproportionCode").empty();
	$("#gradeCode").empty();
	$("#remarks").val("");
	$("#detailedTab input:text").val("");
	$('#loginModal').modal('show');
}

//获取产品名称
function getgetMaterialNameList(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProdProportion/getMaterialNameList.action",
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

//产品名称事件
function getOutName(){
	//获取产品型号
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProdProportion/getMaterialModelList.action",
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

//产品型号事件
function getOutModel(){
	//获取目标配合比编号
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProdProportion/getAsphTargetProCodeByProductId.action",
		data : {"orgId":orgId,"productId":$("#materialModel").val()},
		dataType : "json",
		success : function(data){
			// 清空
			$("#mbproportionCode").empty();
			$("#mbproportionCode").append("<option value=''></option>");
			for(var i = 0; i < data.length; i++) {
				$("#mbproportionCode").append("<option value='"+data[i].id+"'>"+data[i].proportionCode+"</option>");
			}
		}
	});
	
	//获取级配编码
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProdProportion/getGradeCodeByIdByProductId.action",
		data : {"orgId":orgId,"productId":$("#materialModel").val()},
		dataType : "json",
		success : function(data){
			// 清空
			$("#gradeCode").empty();
			$("#gradeCode").append("<option value=''></option>");
			for(var i = 0; i < data.length; i++) {
				$("#gradeCode").append("<option value='"+data[i].id+"'>"+data[i].gradeCode+"</option>");
			}
		}
	});
}

//添加生产配合比
function addOrUpdate(){
	var url = "";
	var num = 0;
	//获取页面数据
	var data = formToJson($("#tabForm"));
	if(data.proportionCode == null || data.proportionCode == "") {
		swal("操作失败","生产配合比编号不能为空!", "info");
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
	if(data.targPropId == null || data.targPropId == "") {
		swal("操作失败","目标配合比编号不能为空!", "info");
		return;
	}
	if(data.gradId == null || data.gradId == "") {
		swal("操作失败","级配编号不能为空!", "info");
		return;
	}
	if(data.asphaltSetValue == null || data.asphaltSetValue == "") {
		swal("操作失败","油石比不能为空!", "info");
		return;
	}
	if(data.no1SetValue == null || data.no1SetValue == "") {
		data.no1SetValue = 0;
	}else{
		num = num + parseInt(data.no1SetValue);
	}
	if(data.no2SetValue == null || data.no2SetValue == "") {
		data.no2SetValue = 0;
	}else{
		num = num + parseInt(data.no2SetValue);
	}
	if(data.no3SetValue == null || data.no3SetValue == "") {
		data.no3SetValue = 0;
	}else{
		num = num + parseInt(data.no3SetValue);
	}
	if(data.no4SetValue == null || data.no4SetValue == "") {
		data.no4SetValue = 0;
	}else{
		num = num + parseInt(data.no4SetValue);
	}
	if(data.no5SetValue == null || data.no5SetValue == "") {
		data.no5SetValue = 0;
	}else{
		num = num + parseInt(data.no5SetValue);
	}
	if(data.no6SetValue == null || data.no6SetValue == "") {
		data.no6SetValue = 0;
	}else{
		num = num + parseInt(data.no6SetValue);
	}
	if(data.coldPowderSetValue == null || data.coldPowderSetValue == "") {
		data.coldPowderSetValue = 0;
	}else{
		num = num + parseInt(data.coldPowderSetValue);
	}
	if(data.coldPowder2SetValue == null || data.coldPowder2SetValue == "") {
		data.coldPowder2SetValue = 0;
	}else{
		num = num + parseInt(data.coldPowder2SetValue);
	}
	if(data.hotPowderSetValue == null || data.hotPowderSetValue == "") {
		data.hotPowderSetValue = 0;
	}else{
		num = num + parseInt(data.hotPowderSetValue);
	}
	if(data.admixture1SetValue == null || data.admixture1SetValue == "") {
		data.admixture1SetValue = 0;
	}else{
		num = num + parseInt(data.admixture1SetValue);
	}
	if(data.admixture2SetValue == null || data.admixture2SetValue == "") {
		data.admixture2SetValue = 0;
	}else{
		num = num + parseInt(data.admixture2SetValue);
	}
	
	if(num==0){
		swal("操作失败","骨料仓、粉料仓、外掺剂至少要录入一项！", "info");
		return;
	}
	if(num != 100) {
		swal("操作失败","除油石比外用量总和必须等于100！", "info");
		return;
	}
	
	if(addOrUpdateTag == 1) {
		//添加
		data.orgId = orgId;
		url =  baseUrl + "/asphaltProdProportion/addAsphaltProdProportion.action";
	} else {
		//修改
		url =  baseUrl + "/asphaltProdProportion/updateAsphaltProdProportion.action";
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

//修改生产配合比(id查询回显)
function updateProportion(id){
	$.ajax({
		type : "POST",
		url : baseUrl + "/asphaltProdProportion/getPlanByProdId.action",
		data : {"id" : id},
		dataType : "json",
		success : function(data){
			if(data != null && data.length > 0){
				setTimeout(function(){
					swal({
						title: "已被生产计划调用,不可修改!",
						type: "error",
						cancelButtonText: '确定',
						cancelButtonFont: '微软雅黑',
				},
				function(){
					search();
				}); },200);
			}else{
				$("#proportionCode").attr("readonly","readonly");
				addOrUpdateTag = 2;
				$("#loginModal").modal("show");
				$.ajax({
					type : "POST",
					url : baseUrl + "/asphaltProdProportion/getAsphaltProdProportionById.action",
					data : {"id" : id},
					dataType : "json",
					success : function(data){
						//生产配合比编号
						$("#proportionCode").val(data[0].proportionCode);
						//产品名称
						$("#materialName").val(data[0].materialName);
						//产品型号
						$("#materialModel").html("<option value='"+data[0].productId+"'>"+data[0].materialModel+"</option>");
						//目标配合比编号
						$("#mbproportionCode").html("<option value='"+data[0].targPropId+"'>"+data[0].mproportionCode+"</option>");
						//级配编号
						$("#gradeCode").html("<option value='"+data[0].gradId+"'>"+data[0].gradeCode+"</option>");
						//备注
						$("#remarks").val(data[0].remarks);
						//1#仓
						$("#no1SetValue").val(data[0].no1SetValue);
						//2#仓
						$("#no2SetValue").val(data[0].no2SetValue);
						//3#仓
						$("#no3SetValue").val(data[0].no3SetValue);
						//4#仓
						$("#no4SetValue").val(data[0].no4SetValue);
						//5#仓
						$("#no5SetValue").val(data[0].no5SetValue);
						//6#仓
						$("#no6SetValue").val(data[0].no6SetValue);
						//冷粉仓
						$("#coldPowderSetValue").val(data[0].coldPowderSetValue);
						//冷粉仓2
						$("#coldPowder2SetValue").val(data[0].coldPowder2SetValue);
						//热粉仓
						$("#hotPowderSetValue").val(data[0].hotPowderSetValue);
						//油石比
						$("#asphaltSetValue").val(data[0].asphaltSetValue);
						//外掺剂1
						$("#admixture1SetValue").val(data[0].admixture1SetValue);
						//外掺剂2
						$("#admixture2SetValue").val(data[0].admixture2SetValue);
						//id
						$("#id").val(data[0].id);
					}
				});
			}
		}
	});
}

//删除
function deleteProportion(id){
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
			url : baseUrl + "/asphaltProdProportion/getPlanByProdId.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data){
				if(data != null && data.length > 0){
					setTimeout(function(){
						swal({
							title: "已被生产计划调用,不可删除!",
							type: "error",
							cancelButtonText: '确定',
							cancelButtonFont: '微软雅黑',
					},
					function(){
						search();
					}); },200);
				}else{
					$.ajax({
						type : "POST",
						url : baseUrl + "/asphaltProdProportion/deleteAsphaltProdProportion.action",
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
				}
			}
		});
	});
}

//生产配合比编号改变事件
function changeProportionCode(value){
	$.ajax({
		type : "POST",
		url: baseUrl + "/asphaltProdProportion/getAllAsphaltProdProportion.action",
		data: {"orgId" : orgId},
		dataType : "json",
		success : function(data){
			if(data.data != null && data.data.length > 0){
				for (var i = 0; i < data.data.length; i++) {
					if(data.data[i].proportionCode == value){
						setTimeout(function(){
							swal({
								title: "该生产配合比编号已存在!",
								type: "error",
								cancelButtonText: '确定',
								cancelButtonFont: '微软雅黑',
						},
						function(){
							//清空
							$("#proportionCode").val("");
						}); },200);
					}
				}
			}
		}
	});
}