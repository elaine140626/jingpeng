var baseUrl = "";
var id = "";
var MaterielinfoList; //所有物料信息
var infoList;
var type;
$(function(){
	var user = getUserInfo();
	$("#transportParty").val(user.name)
	id = getUrlParam("id");
	flag = getUrlParam("flag");
	if(id!=null&&id!=""&&flag!=0){
		$("#isShowBillNumber").show();
	}
	baseUrl = getContextPath();
	//html 出库入库单 车牌号
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/getPlate.action',
		async:false,
		dataType: "json",
		success: function (data) {
			var PlateList = data.data;
			$.each(PlateList, function (i, v) {
				$('<option></option>').attr("value", i).html(v.PlateNumber).appendTo($("#plateNumber"));
			})
		}
	});
	//物料信息
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/getMaterielinfo.action',
		async:false,
		dataType: "json",
		success: function (data) {
			MaterielinfoList = data.data;
			$.each(MaterielinfoList, function (i, v) {
				$('<option></option>').attr("value", v.id).html(v.materielName).appendTo($("#materielId"));
			})
		}
	});
	//结算类别 数据字典
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/getSaleType.action',
		async:false,
		dataType: "json",
		success: function (data) {
			$.each(data.data, function (i, v) {
				$('<option></option>').attr("value", v.code).html(v.content).appendTo($("#saleType"));
			})
		}
	});
	
	if(id != null && id != ""){
		$("#id").attr("value", id);
		
		findTranSportListByid(id);
	}else{
		$("#id").attr("value", "");
	}
})
/*function changeMaterielId(value){
	$("#model").empty();
	if(MaterielinfoList != null){
		for(var i=0; i<MaterielinfoList.length; i++){
			if(MaterielinfoList[i].id == value){
				$('<option></option>').attr("value", MaterielinfoList[i].id).html(MaterielinfoList[i].materielModel).appendTo($("#model"));
			}
		}
	}
}*/

function changePlateNumber(value){
	var platen = $("#plateNumber option:selected").text()
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/getExOrStPlateNumber.action',
		async:false,
		data:{plateNumber:platen},
		dataType: "json",
		success: function (data) {
			$("#serialID").empty();
			$.each(data.data, function (i, v) {
				$('<option></option>').attr("value", v.id).html(v.SerialId).appendTo($("#serialID"));
			})
		}
	});
	
	var ser = $("#serialID option:selected").text()
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/getExOrStPlateNumber.action',
		async:false,
		data:{serialID:ser},
		dataType: "json",
		success: function (data) {
			infoList = data.data;
			$("#carOwner").val(infoList[0].carOwner);
			$("#carOwnerTelephone").val(infoList[0].carOwnerTelephone);
			$("#remarks").val(infoList[0].remarks);
			$("#materielId").empty();
			$("#model").empty();
			$('<option></option>').attr("value", infoList[0].materielNameId).html(infoList[0].materielName).appendTo($("#materielId"));
			$('<option></option>').attr("value", infoList[0].materielModelId).html(infoList[0].materielModel).appendTo($("#model"));
/*			$("#materielId").val(infoList[0].materielName);
			$("#model").val(infoList[0].materielModel);*/
		}
	});
	
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/getIsExAndSt.action',
		async:false,
		data:{serialID:ser},
		dataType: "json",
		success: function (data) {
			if(data.data!=null){
				if(data.data.outTypeMark){
					type = 1;
				}else{
					type = 0;
				}
			}else{
					type = 2;
			}
		}
	});
}

function changeSerialID(value){
	var ser = $("#serialID option:selected").text()
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/getExOrStPlateNumber.action',
		async:false,
		data:{serialID:ser},
		dataType: "json",
		success: function (data) {
			infoList = data.data;
			$("#carOwner").val(infoList[0].carOwner);
			$("#carOwnerTelephone").val(infoList[0].carOwnerTelephone);
			$("#remarks").val(infoList[0].remarks);
			$("#materielId").empty();
			$("#model").empty();
			$('<option></option>').attr("value", infoList[0].materielNameId).html(infoList[0].materielName).appendTo($("#materielId"));
			$('<option></option>').attr("value", infoList[0].materielModelId).html(infoList[0].materielModel).appendTo($("#model"));
		}
	});
	
	//0：出库单 1：未入厂出库单  2:入库单
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/getIsExAndSt.action',
		async:false,
		data:{serialID:ser},
		dataType: "json",
		success: function (data) {
			if(data.data!=null){
				if(data.data.outTypeMark){
					type = 1;
				}else{
					type = 0;
				}
			}else{
					type = 2;
			}
		}
	});
}


function add(){
	$("#materielId").removeAttr("disabled"); 
	$("#model").removeAttr("disabled"); 
	var data = formToJson($("#submitForm"));
	data.dispatchOutWarehouseId = infoList[0].id;
	data.plateNumber = $("#plateNumber").find("option:selected").text();
	data.serialID =  $("#serialID").find("option:selected").text();
	data.type = type;
	if(id == "" || id == null ||flag==0){
		data.id="";
		
		data.billNumber = getContractInfoPrefix("YS");
		updateContractInfoPrefix("YS");
	}
	var ContractRoute = $("#files").val();
	data.transportRoute = ContractRoute
	if(data.plateNumber == -1){
		swal({
 			title: "错误提示",
 			text: "车牌号未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.company == ""){
		swal({
 			title: "错误提示",
 			text: "送达单位/采购单位未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.materielId == "" || data.model==""){
		swal({
 			title: "错误提示",
 			text: "物料名称/型号未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.carOwner ==""){
		swal({
 			title: "错误提示",
 			text: "车主未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.carOwnerTelephone ==""){
		swal({
 			title: "错误提示",
 			text: "联系电话未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.startAddress ==""){
		swal({
 			title: "错误提示",
 			text: "起运地未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.endAddress ==""){
		swal({
 			title: "错误提示",
 			text: "止运地未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.kilometre ==""){
		swal({
 			title: "错误提示",
 			text: "公里数未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.settlementKilometre ==""){
		swal({
 			title: "错误提示",
 			text: "结算公里数未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.weigh ==""){
		swal({
 			title: "错误提示",
 			text: "称重重量未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.collectWeigh ==""){
		swal({
 			title: "错误提示",
 			text: "称重重量未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.freightPrice ==""){
		swal({
 			title: "错误提示",
 			text: "运输单价未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.freightMoney ==""){
		swal({
 			title: "错误提示",
 			text: "运费总额未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}

	if(data.escortDays ==""){
		swal({
 			title: "错误提示",
 			text: "押车天数未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.escortMoney ==""){
		swal({
 			title: "错误提示",
 			text: "押车金额未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	if(data.saleType ==""){
		swal({
 			title: "错误提示",
 			text: "结算类别未选择!",
 			type: "error",
 			confirmButtonText: '确定',
 			cancelButtonFont: '微软雅黑',
 		});
		return;
	}
	
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/addTranSportList.action',
		async:false,
		data:data,
		dataType: "json",
		success: function (data) {
			if(data != null){
				if(data.code == "success"){
					swal({
			 			title: "提示",
			 			text: data.message,
			 			type: data.code,
			 			confirmButtonText: '确定',
			 			cancelButtonFont: '微软雅黑',
			 		},function(){
			 			window.parent.$.fancybox.close();
			 		});
				}else{
					swal({
			 			title: "错误提示",
			 			text: data.message,
			 			type: data.code,
			 			confirmButtonText: '确定',
			 			cancelButtonFont: '微软雅黑',
			 		});
				}
			}
		}
	});
}

function SetFromValues(el, data)
{
    for (var p in data)
    {
        el.find(":input[name='" + p + "']").val(data[p]);
    } 
}

function findTranSportListByid(id){
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportList/getTranSportList.action',
		async:false,
		data:{"id":id},
		dataType: "json",
		success: function (data) {
		var	TranSport = data.data;
		SetFromValues($("#submitForm"), TranSport[0]);
		$("#plateNumber option").each(function(i,n){
            if($(n).text()==TranSport[0].plateNumber)
            {
                $(n).attr("selected",true);
                changePlateNumber($(n).text())
            }
        })
        $("#serialID option").each(function(i,n){
            if($(n).text()==TranSport[0].serialID)
            {
                $(n).attr("selected",true);
            }
        })
        if (TranSport[0].transportRoute) {
				$("#downloadBtn").show();
				$("#deleteBtn").show();
				$("#file").html(TranSport[0].transportRoute);
				$("#files").val(TranSport[0].transportRoute);
			}
		$("#saleType option[value='"+TranSport[0].saleType+"']").attr("selected", true);
		}
	});
}
