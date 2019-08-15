// 获取的id
var id = "";
// 用户的id
var userId = "";
// 材料名称
var MaterielName = "";
// 规格型号
var MaterielModel = "";

var generateNumber;
$(function(){
	// 材料种类列表
	var MaterielTypeList = getDataDictionaryMultiple('cllb');
	$("#materielType").empty();
	$("#materielType").html(MaterielTypeList);
	uesrId = getUrlParam("userId");
	//传所需的前缀英文简写
	$.ajax({
		type : "POST",
		url : "../../MaterialSetting/getGenerateNumber.action",
		async:false,
		data : {"type":"LI"},
		dataType : "json",
		success : function(data) {
			generateNumber = data.generateNumber;
		}
	});
	// 获取传值id
	id = getUrlParam("id");
	
	if(id != null && id != ''){
		// 获取材料名称信息
		$.ajax({
			type : "post",
			url : "../../MaterialSetting/getMaterialSettingList.action",
			data : {"id" : id},
			dataType : "json",
			success : function(data) {
				// 画面赋值
				if(data != null && data.data.length > 0){
					SetFromValues($("#form1"), data.data[0]);
					// 材料名称
					MaterielName = data.data[0].materielName;
					// 规格型号
					MaterielModel = data.data[0].materielModel;
				}
			}
		});
	}else{
		$("#materielNumber").val(generateNumber);
	}
})

// 保存按钮
function save(){
	var params = formToJson($("#form1"));
	
	// 材料编号
	if(params.materielNumber == ''){
		layer.alert("材料编号不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 材料名称
	if(params.materielName == ''){
		layer.alert("材料名称不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 材料型号
	if(params.materielModel == ''){
		layer.alert("规格型号不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	// 材料种类
	if(params.materielType == ''){
		layer.alert("材料种类不能为空!", {
			icon: 2,
			title: "提示"
		});
		return;
	}
	
	if (id == null){
		// 新增
		url = '../../MaterialSetting/insertMaterialSetting.action';
		params.creater=userId;
	} else {
		// 修改
		url = '../../MaterialSetting/updateMaterialSetting.action';
		params.id = id;
		params.reviser=userId;
	}
	
	// 保存操作
	$.ajax({
		type: 'POST',
		url: url,
		data:JSON.stringify(params),
		dataType: 'json',
		contentType: 'application/json',
		success: function(data){
			if (data.code == 'success'){
				// 操作成功
				layer.alert(data.message, {
					icon: 1,
					title: "提示"
				},function(){
					window.parent.location.reload();
				});
			} else {
				// 操作失败
				layer.alert(data.message, {
					icon: 2,
					title: "提示"
				});
			}			
		}
	});		
}

// 判断是否存在相同的材料名称和规格编号
function changeMateriel(){
	
	// 参数
	var param = {};
	// 材料名称
	var name = $("#materielName").val();
	param.materielName = name;
	
	// 规格型号
	var model = $("#materielModel").val();
	param.materielModel = model;
	
	// 修改(修改时材料名称和规格型号可以不修改)
	if(id != null && id != ""){
		// 材料名称和规格型号没有变更
		if(name == MaterielName && model == MaterielModel){
			return;
		}
	}
	
	// 保存操作
	$.ajax({
		type: 'POST',
		url: "../../MaterialSetting/getCount.action",
		data:JSON.stringify(param),
		dataType: 'json',
		contentType: 'application/json',
		success: function(data){
			if(data != null && data.length > 0){
				layer.alert("相同材料名称和规格型号已经存在,请确认!", {
					icon: 2,
					title: "提示"
				});
				$("#materielName").val("");
				$("#materielModel").val("");
				return;
			}
		}
	});
}