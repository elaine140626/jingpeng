var id = "";
var outOrEnter= "";
var feedCompanyList = "";
var receiveUnitList = "";
var materielNameList = "";
var materielModelList = "";
$(function(){
	id = getUrlParam("id")
	outOrEnter = getUrlParam("outOrEnter")
	$("#u362_text").click(function(){
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	})
	$("#u370").append("<select id='u370_input'></select>");
	$("#u386").append("<select id='u386_input' onchange='change(this.value)'></select>");
	getDatalistInfo();
	FindWeighingQueryById(id);
})
//根据id查询称重信息
function FindWeighingQueryById(id){
			$.ajax({
				type: 'POST',
				url: '../../WeighingQuery/getWeighingQuery.action',
				data: {"id":id},
				dataType: 'json',
				success: function(data){
					data = data.data[0];
					$("#u364_input").val(data.plateNumber);//车牌号码
					$("#u367_input").val(data.materielName);//材料名称
					$("#u368_input").val(data.materielModelId);//规格型号
					$("#u370_input").val(data.receiveUnitId);//收料单位
					$("#u386_input").val(data.feedCompanyId);//送料单位
					$("#u373_input").val(data.grossWeight);//毛重
					$("#u375_input").val(data.tareWeight);//皮重
					$("#u409_input").val(data.deduction);//选择扣除量扣除率
					$("#u376_input").val(data.numericalValue);//扣除量扣除率
					$("#u378_input").val(data.netWeight);//净重.
					$("#u380_input").val(data.driverName);//司机姓名
					$("#u404_input").val(data.endAddress);//止运地
					$("#u405_input").val(data.startAddress);//始运地
					$("#u408_input").val(data.temperature);//温度 
					$("#u556_input").val(data.remarks);//备注
					 console.log(data);
				}
			});
}

//查询条件下拉列表
function getDatalistInfo(){
		var Modelvalue="";
		$.ajax({
			type : "post",
			url : "../../WeighingQuery/getDataList_add.action",
			data : {"outOrEnter":outOrEnter},
			async : false,
			dataType : "json",
			success : function(data) {
				// 清空
				if (data != null) {
					feedCompanyList = data.feedCompanyList;
					receiveUnitList = data.receiveUnitList;
					materielNameList = data.materielNameList;
				    materielModelList = data.materielModelList;
					//供料单位datalist
					if (feedCompanyList != null && feedCompanyList.length > 0) {
						for (var i = 0; i < feedCompanyList.length; i++) {
							if(  feedCompanyList[i] != null &&  feedCompanyList[i] != '')
							{$("#u386_input").append("<option value='"+feedCompanyList[i].id+"'>" + feedCompanyList[i].feedCompanyName + "</option>");}
						}
					}
					//收料单位datalist
					if (receiveUnitList != null && receiveUnitList.length > 0) {
						for (var i = 0; i < receiveUnitList.length; i++) {
							if(  receiveUnitList[i] != null &&  receiveUnitList[i] != '')
							{$("#u370_input").append("<option value='"+receiveUnitList[i].id+"'>" + receiveUnitList[i].receiveUnitName + "</option>");}
						}
					}
					//材料名称datalist
					if (materielNameList != null && materielNameList.length > 0) {
						for (var i = 0; i < materielNameList.length; i++) {
							if(  materielNameList[i] != null &&  materielNameList[i] != '')
							{$("#u367_input").append("<option value='"+materielNameList[i].materielName+"'>" + materielNameList[i].materielName + "</option>");}
						}
					}
					//规格型号datalist
					if (materielModelList != null && materielModelList.length > 0) {
						for (var i = 0; i < materielModelList.length; i++) {
							if(  materielModelList[i] != null &&  materielModelList[i] != '' && materielModelList[i].materielModel != Modelvalue)
							{$("#u368_input").append("<option value='"+materielModelList[i].id+"'>" + materielModelList[i].materielModel + "</option>");}
							Modelvalue = materielModelList[i].materielModel
						}
					}
				
				}
			}
		});
}
//编辑称重信息
function save(){
			var param = {};
			var feedCompanyId = $("#u386_input").val();			//供料单位
			var receiveUnitId = $("#u370_input").val();			//收料单位
			var materielNameId = $("#u368_input").val();		//规格型号
			var grossWeight = $("#u373_input").val();			//毛重
			var tareWeight = $("#u375_input").val();			//皮重
			var netWeight = $("#u378_input").val();				//净重
			var temperature = $("#u408_input").val();			//温度
			var driverName = $("#u380_input").val();			//司机名字
			var endAddress = $("#u404_input").val();			//止运地
			var startAddress = $("#u405_input").val();			//始运地
			var numericalValue = $("#u376_input").val();			//皮重
			var deduction = $("#u409_input").val();   			//扣除量/扣除率
			var remarks = $("#u556_input").val();   			//备注
			param.id = id;
			param.feedCompanyId = feedCompanyId;
			param.receiveUnitId = receiveUnitId;
			param.materielNameId = materielNameId;
			param.grossWeight = grossWeight;
			param.tareWeight = tareWeight;
			param.netWeight = netWeight;
			param.temperature = temperature;
			param.driverName = driverName;
			param.endAddress = endAddress;
			param.startAddress = startAddress;
			param.deduction = deduction;
			param.numericalValue = numericalValue;
			param.remarks = remarks;
			$.ajax({
				type : 'POST',
				url : '../../WeighingQuery/updateWeighingQuery.action',
				data:JSON.stringify(param),
				dataType: 'json',
				contentType: 'application/json',
				success : function(data) {
					if (data.code = "success") {
						layer.alert(data.message, {
							icon : 1,
							title : "提示"
						}, function() {
							window.parent.location.reload();
							var index=parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
						});
					} else {
						layer.msg(data.message, {
							icon: 2,
							time: 1000
						},function(){
							window.location.reload();
						});
					}
				}
			});
	}

//供料单位onchange事件
function change(value){
	var Namevalue="";
	var Modelvalue="";
	$("#u367_input").empty();//材料名称
	$("#u367_input").append("<option value=''>-- 请选择 --</option>");
	$("#u368_input").empty();//材料名称
	$("#u368_input").append("<option value=''>-- 请选择 --</option>");
	var feedCompanyId = $("#u386_input").val();//供料单位id
	$.ajax({
		type : "post",
		url : "../../WeighingQuery/getFeedcompanyList.action",
		data : {"feedCompanyId":feedCompanyId},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data != null) {
				var materielNameList = data.materielNameList;
				var materielModelList = data.materielModelList;
				//物料名称datalist
				if (materielNameList != null && materielNameList.length > 0) {
					for (var i = 0; i < materielNameList.length; i++) {
						if(  materielNameList[i] != null &&  materielNameList[i] != '' && materielNameList[i].materielName != Namevalue)
							{$("#u367_input").append("<option value='"+materielNameList[i].materielName+"'>" + materielNameList[i].materielName + "</option>");
							Namevalue = materielNameList[i].materielName}
					}
				}
			}
		}
	});
}

//材料名称onchange事件
function Namechange(value){
	var Modelvalue="";
	$("#u368_input").empty();//供料单位
	$("#u368_input").append("<option value=''>-- 请选择 --</option>");
	var materielName = $("#u367_input").val();//材料名称
	$.ajax({
		type : "post",
		url : "../../WeighingQuery/getMaterielNameList.action",
		data : {"materielName":materielName},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data != null) {
				var materielModelList = data.materielModelList;
				//规格型号datalist
				if (materielModelList != null && materielModelList.length > 0) {
					for (var i = 0; i < materielModelList.length; i++) {
					//	debugger;
						if(  materielModelList[i] != null &&  materielModelList[i] != ''  && materielModelList[i].materielModel != Modelvalue){
							$("#u368_input").append("<option value='"+materielModelList[i].id+"'>" + materielModelList[i].materielModel+ "</option>");
							Modelvalue = materielModelList[i].materielModel
							}
					}
				}
			}
		}
	});
}


