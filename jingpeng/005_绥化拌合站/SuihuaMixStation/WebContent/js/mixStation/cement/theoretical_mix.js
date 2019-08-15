var baseUrl = "";
var addOrUpdateTag = 1;//添加和修改的标识 1 : 添加 2 ： 修改
var str_MaterialNameList = "";//原材料名称
var flag = true;//明框的添加标识
$(function(){
	$("#locationText").html(cementOrgName);
	baseUrl = getContextPath(); 
	//物料名称
	getMaterialNameList();
	//原材料名称
	getStr_MaterialNameList();
	//画面显示数据
	var param = {};
	param.orgId = cementOrgId;
	if(cementOrgId != ''){
		getListInfo(param);
	}
});

//查询理论配比信息列表
function getListInfo(param){
	var table = $("#list").dataTable();
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
            url: baseUrl + "/cementTheoryProportion/getAllCementTheoryProportion.action",
            dataSrc: "data",
            data: function (d) {
                return param;
            }
        },
        "deferRender": true,
        "columns": [ 
			{"data" : "id"},
			{"data" : "propCode"},
			{
				"data" : "productId",
				render : function(data,type,row){
					data = row.materialName + "/" + row.materialModel;
					return data;
				}
			},
			{"data" : "waterCementRatio"},
			{"data" : "lowAndHight"},
			{"data" : "designStrength"},
			{"data" : "sandRatio"},
			{"data" : "operator"},
			{"data" : "createDate"},
			{
				"data" : "id",
				render:function(data,type,row){
					var html = "";
        			html += "<span><a onclick=updateCementTheoProp("+row.id+")><img src=\"../../image/images/xiu.png\" width=\"17\" height=\"16\" alt=\"修改\" title=\"修改\"></a></span>";
    	  		    html +=	"<span><a onclick=deleteCementTheoProp("+row.id+")><img src=\"../../image/images/del.png\" width=\"17\" height=\"16\" alt=\"删除\" title=\"删除\"></a></span>";
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
	param.orgId = cementOrgId;
	//理论配比编号
	param.propCode = $("#prop_Code").val();
	//产品名称
	param.materialName = $("#material_Name").val();
	//产品型号
	param.materialModel = $("#material_Model").val();
	if(cementOrgId != ''){
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

//跳窗添加页面
function addView(){
	$("#propCode").removeAttr("disabled");
	$("#tab input:text").val("");
	$("#materialName").val("");
	$("#materialModel").empty();
	$("#remarks").val("");
	//动态行的初始化
	$("#detailedTab tbody").find("tr").remove();
	for (var i = 0; i < 5; i++) {
		addRow();
	}
	$('#loginModal').modal('show');
} 

//获取产品名称
function getMaterialNameList(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/cementTheoryProportion/getMaterialNameList.action",
		data : {"materialType":1,"materialMold":1,"orgId":cementOrgId},
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
function getStrModel(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/cementTheoryProportion/getMaterialModelList.action",
		data : {
				"materialType":1,
		        "materialMold":1,
		        "materialName":$("#materialName").val(),
		        "orgId":cementOrgId
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

//获取原材料
function getStr_MaterialNameList(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/cementTheoryProportion/getMaterialNameList.action",
		data : {"materialType":1,"materialMold":0,"orgId":cementOrgId},
		dataType : "json",
		success : function(data){
			var html = "<option value='' selected='selected'></option>";
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].mateName+"'>"+data[i].mateName+"</option>"
			}
			str_MaterialNameList = html;
		}
	});
}

//获取原材料型号
function getMaterialModel(row,value){
	$.ajax({
		type : "POST",
		url : baseUrl + "/cementTheoryProportion/getMaterialModelList.action",
		data : {
				"materialType":1,
		        "materialMold":0,
		        "materialName":value,
		        "orgId":cementOrgId
		        },
		dataType : "json",
		success : function(data) {
			// 清空
			$("#str_materialModel"+row).empty();
			$("#str_materialModel"+row).append("<option value=''></option>");
			for(var i = 0; i < data.length; i++) {
				$("#str_materialModel"+row).append("<option value='"+data[i].id+"'>"+data[i].mateModel+"</option>");
			}
		}
	});
}

//动态行的添加
function addRow(){
	var len = $("#detailedTab tr").length;
	var row;
	var result = "";
	if(len == 1){
		row = 1;
	}else{
		for (var i = 1; i < len; i++) {
			row = i + 1;
		}
	}
		result += '<tr class="h50">';
		result += '<td width="4%" style="display: none">'+row+'</td>';
		result += '<td width="17%"><select size="1" name="str_materialName" id="str_materialName'+row+'" onchange="getMaterialModel('+row+',this.value);">'+str_MaterialNameList+'</select></td>';
		result += '<td width="17%"><select size="1" name="str_materialModel" id="str_materialModel'+row+'"></select></td>'; 
		result += '<td width="19%" align="center"><input  name="weight" id="weight'+row+'" type="text" maxlength="10" onkeyup="num(this)"></td>';
		result += '<td width="23%" align="center"><input name="manufacturer" id="manufacturer'+row+'" type="text" maxlength="20"></td>';
		result += '<td width="14%" align="center"><input  name="materialOrigin" id="materialOrigin'+row+'" type="text" maxlength="20"></td>';
		result += '<td width="6%" align="center"><a href="javascript:void(0)" onclick="delDetailed($(this))"><img src="../../image/images/del.png" width="16" height="16"></a></td>';
		result += '</tr>';
	$("#detailedTab").append(result);
}

//动态行的删除
function delDetailed(obj){
	obj.parent().parent().remove();
}

//添加理论配比
function addCementTheory(){
	//理论配比编号
	var propCode = $("#propCode").val();
	if(propCode==""){
		swal("操作失败", "理论配比编号不能为空", "info");
		return;
	}
	//产品名称
	var materialName = $("#materialName").val();
	if(materialName==""){
		swal("操作失败", "产品名称不能为空", "info");
		return;
	}
	//规格型号
	var materialModel = $("#materialModel").val();
	if(materialModel==""){
		swal("操作失败", "规格型号不能为空", "info");
		return;
	}
	//设计水灰比(%)
	var waterCementRatio = $("#waterCementRatio").val();
	if(waterCementRatio==""){
		swal("操作失败", "设计水灰比不能为空", "info");
		return;
	}
	//砂率(%)
	var sandRatio = $("#sandRatio").val();
	if(sandRatio==""){
		swal("操作失败", "砂率不能为空", "info");
		return;
	}
	//设计强度(MPa)
	var designStrength = $("#designStrength").val();
	if(designStrength==""){
		swal("操作失败", "设计强度不能为空", "info");
		return;
	}
	//坍落度(mm)
	var slumpLow = $("#slumpLow").val();
	var slumpHigh = $("#slumpHigh").val();
	if(slumpLow=="" || slumpHigh == ""){
		swal("操作失败", "坍落度不能为空", "info");
		return;
	}else if(slumpLow > slumpHigh){
		swal("操作失败","输入合理的坍落度范围","info");
		$("#slumpLow").val("");
		$("#slumpHigh").val("");
		return;
	}
	//备注
	var remarks = $("#remarks").val();
	var params = {
			"id" : $("#id").val(),
			"orgId" : cementOrgId,
			"propCode" : propCode,
			"productId" : materialModel,
			"designStrength" : designStrength,
			"waterCementRatio" : waterCementRatio,
			"sandRatio" : sandRatio,
			"slumpLow" : slumpLow,
			"slumpHigh" : slumpHigh,
			"remarks" : remarks
			};
	//理论配比明细
	var cementTheoPropDetailedList = [];
	//行数
	var row = 1;
	//所有提示信息
	var swalMessage = "";
	$("#detailedTab tbody").find("tr").each(function(){
		var cementTheoPropDetailed = {};
		var unfilled = 0;
		//提示信息
		var message = "";
		$(this).find("td").each(function(){
			$(this).find("input").each(function(){//获取td中的input值
				if($(this).attr("name")){//myName是input标签的一个自定义属性
					var idValue = $(this).val();//获取属性id对应的属性值
					var name = $(this).attr("name");//获取该name的属性名称
					if(idValue != ""){
						eval("cementTheoPropDetailed."+name+"=" + "'"+idValue+"'");
					}else{
						if(name == "weight"){
							message += ("\n")+"第"+row+"原材料用量不能为空";
							unfilled += 1;
						}
						if(name == "manufacturer"){
							cementTheoPropDetailed.manufacturer = "";
						}
						if(name == "materialOrigin"){
							cementTheoPropDetailed.materialOrigin = "";
						}
					}
				}
			});
			$(this).find('select').each(function () {//获取td中select的值
	           if($(this).attr("name")) {//myName是select标签的一个自定义属性
		          var idValue = $(this).val(); //获取属性id对应的属性值
	              var name=$(this).attr("name");  //获取该name的属性名称 
	              if (idValue !=''){
	            	  eval("cementTheoPropDetailed."+name+"=" + "'"+idValue+"'");
	              } else {
	            	  if (name == 'str_materialName'){
	            		  message += ("\n")+"第"+row+"原材料名称不能为空";
	            		  unfilled += 1;
	            	  }
	            	  if (name == 'str_materialModel'){
	            		  message += ("\n")+"第"+row+"原材料型号不能为空";
	            		  unfilled += 1;
	            	  }
	              } 
	           }
		    }); 
		});
		cementTheoPropDetailedList.push(cementTheoPropDetailed);
		// 当该行所有必填项都未填时，不提示未填信息
		if (unfilled < 5) {
			swalMessage += message;
		}
		row++;
	});
	if (swalMessage!=""){
		swal("操作失败", "理论配比明细"+swalMessage, "info");
		return;
	}
	params.cementTheoPropDetailedList = JSON.stringify(cementTheoPropDetailedList);
	var url;
	if(addOrUpdateTag == 1){
		//添加
		url = baseUrl + "/cementTheoryProportion/addCementTheoryProportion.action";
	}else{
		//修改
		flag = true;
		url = baseUrl + "/cementTheoryProportion/updateCementTheoryProportion.action";
	}
	$.ajax({
		type : "POST",
		url : url,
		data : params,
		dataType : "json",
		success : function(data){
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

//修改(回显)
function updateCementTheoProp(id){
	$("#propCode").attr("disabled","disabled");
	addOrUpdateTag = 2;
	flag = true;
	$("#loginModal").modal("show");
	$.ajax({
		type : "POST",
		url : baseUrl + "/cementTheoryProportion/getCementTheoryProportionById.action",
		data : {"id" : id},
		dataType : "json",
		success : function(data){
			//理论配比信息
			var theoryProportionList = data.cementTheoryProportionByIdInfo;
			//理论配比信息明细
			var theoPropDetailedList = data.cementTheoPropDetailedByIdInfo;
			//理论配比编号
			$("#propCode").val(theoryProportionList[0].propCode);
			//产品名称
			$("#materialName").val(theoryProportionList[0].materialName);
			//规格型号
			$("#materialModel").html("<option value='"+theoryProportionList[0].productId+"'>"+theoryProportionList[0].materialModel+"</option>");
			//设计水灰比
			$("#waterCementRatio").val(theoryProportionList[0].waterCementRatio);
			//砂率
			$("#sandRatio").val(theoryProportionList[0].sandRatio);
			//设计强度
			$("#designStrength").val(theoryProportionList[0].designStrength);
			//坍落度
			$("#slumpLow").val(theoryProportionList[0].slumpLow);
			$("#slumpHigh").val(theoryProportionList[0].slumpHigh);
			//备注
			$("#remarks").val(theoryProportionList[0].remarks);
			$("#id").val(theoryProportionList[0].id);
			
			//明细
			if(theoPropDetailedList.length > 0){
				$("#detailedTab tbody").find("tr").remove();
				for (var i = 0; i < theoPropDetailedList.length; i++) {
					addRow();
					//原材料名称
					$("#str_materialName"+(i+1)).val(theoPropDetailedList[i].materialName);	
					//原材料型号
					$("#str_materialModel"+(i+1)).html("<option value='"+theoPropDetailedList[i].materialsId+"'>"+theoPropDetailedList[i].materialModel+"</option>");
					//原材料用量
					$("#weight"+(i+1)).val(theoPropDetailedList[i].weight);	
					//厂家
					$("#manufacturer"+(i+1)).val(theoPropDetailedList[i].manufacturer);	
					//产地
					$("#materialOrigin"+(i+1)).val(theoPropDetailedList[i].materialOrigin);	
				}
			}else{
				$("#detailedTab tbody").find("tr").remove();
				for (var i = 0; i < 5; i++) {
					addRow();
				}
			}
		}
	});
}

//删除理论配比
function deleteCementTheoProp(id){
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
			url : baseUrl + "/cementTheoryProportion/deleteCementTheoryProportionById.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data){
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

//理论配比编号改变的事件
function changePropCode(value){
	$.ajax({
		type : "POST",
		url: baseUrl + "/cementTheoryProportion/getAllCementTheoryProportion.action",
		data: {"orgId" : cementOrgId},
		dataType : "json",
		success : function(data){
			if(data.data != null && data.data.length > 0){
				for (var i = 0; i < data.data.length; i++) {
					if(data.data[i].propCode == value){
						setTimeout(function(){
							swal({
								title: "该理论配比编号已存在!",
								type: "error",
								cancelButtonText: '确定',
								cancelButtonFont: '微软雅黑',
						},
						function(){
							//清空
							$("#propCode").val("");
						}); },200);
					}
				}
			}
		}
	});
}