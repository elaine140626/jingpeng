//路径
var baseUrl ="";
//仓库信息id
var id;
//仓库信息
var wareHouseList;
//仓库编号
var lowwareHouseNumber;

$(function(){
	//关闭按钮
	$("#cancelbutton").click(function() {
		window.parent.$.fancybox.close();
	});
	//获取路径
	baseUrl = getContextPath();
	//解析所有参数 
	var param = parseUrl();
	//查询全部仓库信息数据
	getLevel()
	//获取仓库信息
	$.ajax({
	     type: "post",
	     url: baseUrl+'/WareHouse/getWareHouseInfo.action',
	     async:false,
	     dataType: "json",
	     success: function (data) {
	    	 wareHouseList = data.data;
	     }
	});
	if(param != null && param != ""){
		if(param.continuity != null && param.continuity != ""){
			continuity = param['continuity']
			if(continuity == "true"){
				//选中连续添加
				$("#continuityAdd").attr("checked", true);
			}
		}
		if(param.id != null && param.id != ""){
			id = param['id'];
			//隐藏连续添加
			$("#continuityTr").css('display','none');
			//通过仓库id查询仓库信息
			findwarehouseByid(id);
		}
	}
	//储罐信息
	ShowIsHide()
})

//解析所有参数 
function parseUrl() {
	var url = location.href;
	var i = url.indexOf('?');
	if (i == -1)
		return;
	var querystr = url.substr(i + 1);
	var arr1 = querystr.split('&');
	var arr2 = new Object();
	for (i in arr1) {
		var ta = arr1[i].split('=');
		arr2[ta[0]] = ta[1];
	}
	return arr2;
}

//添加信息
function addWareHouse(){
	var continuityAdd = $("#continuityAdd").is(':checked');//是否选中连续添加
	var re =  /^[0-9a-zA-Z]*$/g;  //判断数字字母
	var dble = /^[0-9]+([.]{1}[0-9]{1,2})?$/; //判断小数点
	var type = $("#type").val()
	//添加仓库信息
	if(type==6){
		//仓库编号
		var warehouseNumber = $("#warehouseNumber").val();
		//仓库名称
		var warehouseName = $("#warehouseName").val();
		//仓库类型
		var warehouseType = $("#warehouseType").val();
		//备注
		var remarks = $("#remarks").val();
	    if(warehouseNumber==""){
			swal("操作失败", "请输入编号", "info");
			return;
		}
		if(warehouseNumber.length>30){
			swal("操作失败", "字符长度不能超过30", "info");
			$("#warehouseNumber").val("");
			return;
		}
		if (!re.test(warehouseNumber)){
			swal("操作失败", "编号格式错误只能输入字母和数字", "info");
			$("#warehouseNumber").val("");
			return;
		}
		if(warehouseName==""){
			swal("操作失败", "请输入名称", "info");
			return;
		}
		if(warehouseName.length>30){
			swal("操作失败", "字符长度不能超过30", "info");
			$("#warehouseName").val("");
			return;
		}
		if(warehouseType==-1){
			swal("操作失败", "请选择级别", "info");
			return;
		}
		if(remarks.length>200){
			swal("操作失败", "字符长度不能超过200", "info");
			$("#remarks").val("");
			return;
		}
		if(id!=""&&id!=null){
			var wid = id;
			options = {
					"warehouseNumber" : warehouseNumber,
					"warehouseName" : warehouseName,
					"warehouseType" : warehouseType,
					"type":type,
					"remarks" : remarks,
					"id":wid
			};
			 $.ajax({
		         type: "post",
		         url: baseUrl+'/WareHouse/updateWareHouseInfo.action',
		         async:false,
		         data:JSON.stringify(options),
		         dataType: "json",
		         contentType : 'application/json;charset=UTF-8',
		         success: function (data) {
		        	 if(data.code == "200"){
		        		 swal({
								title : data.message,
								text : "",
								type : "success",
								confirmButtonText : '确定',
								cancelButtonFont : '微软雅黑',
							}, function() {
								window.parent.$.fancybox.close();
								window.location.reload();
							});
		        	 }else{
		        		 swal("操作失败", data.message, "error");
		        	 }
		         }
		    });
			
		}else{
			options = {
					"warehouseNumber" : warehouseNumber,
					"warehouseName" : warehouseName,
					"warehouseType" : warehouseType,
					"type":type,
					"remarks" : remarks
			};
			$.ajax({
				type: "post",
				url: baseUrl+'/WareHouse/addWareHouseInfo.action',
				async:false,
				data:JSON.stringify(options),
				dataType: "json",
				contentType : 'application/json;charset=UTF-8',
				success: function (data) {
					if(data.code == "200"){
						if(continuityAdd){
							window.location.href = 'warehouse_information_edit.html?continuity='+continuityAdd;
						}else{
						 swal({
								title : data.message,
								text : "",
								type : "success",
								confirmButtonText : '确定',
								cancelButtonFont : '微软雅黑',
							}, function() {
								window.parent.$.fancybox.close();
								window.location.reload();
							});
						}
					}else{
						swal("操作失败", data.message, "error");
					}
				}
			});
		}
	}
	//添加储罐信息
	if(type==7){
		//仓库编号
		var warehouseNumber = $("#warehouseNumber").val();
		//仓库名称
		var warehouseName = $("#warehouseName").val();
		//储罐高
		var tankHeight = $("#tankHeight").val();
		//罐容
		var tankCapacity = $("#tankCapacity").val();
		//位置
		var position = $("#position").val();
		//液体高度
		var liquidHeight = $("#liquidHeight").val();
		//储罐预警最低高度
		var alarmLow = $("#alarmLow").val();
		//储罐预警最高高度
		var alarmHigh = $("#alarmHigh").val();
		//备注
		var remarks = $("#remarks").val();
		if(warehouseNumber==""){
			swal("操作失败", "请输入编号", "info");
			return;
		}
		if(warehouseNumber.length>30){
			swal("操作失败", "编号字符长度不能超过30", "info");
			$("#warehouseNumber").val("");
			return;
		}
		if (!re.test(warehouseNumber)){
			swal("操作失败", "编号格式错误只能输入字母和数字", "info");
			$("#warehouseNumber").val("");
			return;
		}
		if(warehouseName==""){
			swal("操作失败", "请输入名称", "info");
			return;
		}
		if(warehouseName.length>30){
			swal("操作失败", "名称字符长度不能超过30", "info");
			$("#warehouseName").val("");
			return;
		}
		
		if(tankHeight==""){
			swal("操作失败", "请输入高度", "info");
			return;
		}
		if(tankHeight.length>8){
			swal("操作失败", "高度字符长度不能超过8", "info");
			$("#tankHeight").val("");
			return;
		}
		if (!dble.test(tankHeight)){
			swal("操作失败", "储罐高度格式错误", "info");
			$("#tankHeight").val("");
			return;
		}
		
		if(tankCapacity==""){
			swal("操作失败", "请输入罐容量", "info");
			return;
		}
		if(tankCapacity.length>8){
			swal("操作失败", "罐容量字符长度不能超过8", "info");
			$("#tankCapacity").val("");
			return;
		}
		if (!dble.test(tankCapacity)){
			swal("操作失败", "罐容高度格式错误", "info");
			$("#tankCapacity").val("");
			return;
		}
		
		if(position==""){
			swal("操作失败", "请输入位置", "info");
			return;
		}
		if(position.length>50){
			swal("操作失败", "位置字符长度不能超过50", "info");
			$("#position").val("");
			return;
		}
		
		if(liquidHeight==""){
			swal("操作失败", "请输入液体高度", "info");
			return;
		}
		if(liquidHeight.length>8){
			swal("操作失败", "液体高度字符长度不能超过8", "info");
			$("#liquidHeight").val("");
			return;
		}
		if (!dble.test(liquidHeight)){
			swal("操作失败", "液体高度格式错误", "info");
			$("#liquidHeight").val("");
			return;
		}
		
		if(alarmLow==""){
			swal("操作失败", "请输入储罐预警最低高度", "info");
			return;
		}
		if(alarmLow.length>8){
			swal("操作失败", "储罐预警最低高度字符长度不能超过8", "info");
			$("#alarmLow").val("");
			return;
		}
		if (!dble.test(alarmLow)){
			swal("操作失败", "储罐预警最低高度格式错误", "info");
			$("#alarmLow").val("");
			return;
		}
		
		if(alarmHigh==""){
			swal("操作失败", "请输入储罐预警最高高度", "info");
			return;
		}
		if(alarmHigh.length>8){
			swal("操作失败", "储罐预警最高高度字符长度不能超过8", "info");
			$("#alarmHigh").val("");
			return;
		}
		if (!dble.test(alarmHigh)){
			swal("操作失败", "储罐预警最高高度格式错误", "info");
			$("#alarmHigh").val("");
			return;
		}
		
		if(parseFloat(alarmLow)>parseFloat(alarmHigh)){
			swal("操作失败", "储罐预警最低高度不能大于或者等于储罐预警最高高度", "info");
			return;
		}
		if(parseFloat(alarmLow)>parseFloat(tankHeight)){
			swal("操作失败", "储罐预警最低高度不能大于储罐最高高度", "info");
			return;
		}
		if(parseFloat(alarmHigh)>parseFloat(tankHeight)){
			swal("操作失败", "储罐预警最高高度不能大于储罐最高高度", "info");
			return;
		}
		if(parseFloat(liquidHeight)>parseFloat(tankHeight)){
			swal("操作失败", "液体高度不能大于储罐最高高度", "info");
			return;
		}
		
		if(id!=""&&id!=null){
			var wid = id;
			options = {
					"warehouseNumber" : warehouseNumber,
					"warehouseName" : warehouseName,
					"type":type,
					"tankHeight" : tankHeight,
					"tankCapacity" : tankCapacity,
					"position":position,
					"liquidHeight" : liquidHeight,
					"alarmLow" : alarmLow,
					"alarmHigh":alarmHigh,
					"remarks" : remarks,
					"id" : wid

			};
			 $.ajax({
		         type: "post",
		         url: baseUrl+'/WareHouse/updateWareHouseInfoTank.action',
		         async:false,
		         data:JSON.stringify(options),
		         dataType: "json",
		         contentType : 'application/json;charset=UTF-8',
		         success: function (data) {
		        	 if(data.code == "200"){
		        		 swal({
								title : data.message,
								text : "",
								type : "success",
								confirmButtonText : '确定',
								cancelButtonFont : '微软雅黑',
							}, function() {
								window.parent.$.fancybox.close();
								window.location.reload();
							});
		        	 }else{
		        		 swal("操作失败", data.message, "error");
		        	 }
		         }
		    });
			
		}else{
			options = {
					"warehouseNumber" : warehouseNumber,
					"warehouseName" : warehouseName,
					"type":type,
					"tankHeight" : tankHeight,
					"tankCapacity" : tankCapacity,
					"position":position,
					"liquidHeight" : liquidHeight,
					"alarmLow" : alarmLow,
					"alarmHigh":alarmHigh,
					"remarks" : remarks
			};
			$.ajax({
				type: "post",
				url: baseUrl+'/WareHouse/addWareHouseInfoTank.action',
				async:false,
				data:JSON.stringify(options),
				dataType: "json",
				contentType : 'application/json;charset=UTF-8',
				success: function (data) {
					if(data.code == "200"){
						if(continuityAdd){
							window.location.href = 'warehouse_information_edit.html?continuity='+continuityAdd;
						}else{
						 swal({
								title : data.message,
								text : "",
								type : "success",
								confirmButtonText : '确定',
								cancelButtonFont : '微软雅黑',
							}, function() {
								window.parent.$.fancybox.close();
								window.location.reload();
							});
						}
					}else{
						swal("操作失败", data.message, "error");
					}
				}
			});
		}
	}
}

//查询全部仓库信息数据
function getLevel(){
	$.ajax({
        type: "post",
        url: baseUrl+'/WareHouse/getLevel.action',
        async:false,
        dataType: "json",
        success: function (data) {
        	$.each(data.data, function (i, v) {
        		var str = '<option value="' + v.code + '">'
				+ v.content + '</option>';
        		$('#warehouseType').append(str);
            })
        }
   });
}

//通过仓库id查询仓库信息
function findwarehouseByid(id){
	$.ajax({
        type: "post",
        url: baseUrl+'/WareHouse/getWareHouseInfoByid.action',
        async:false,
        data:{"id":id},
        dataType: "json",
        success: function (data) {
        	var dataIfro = data.data;
        	//仓库编号
        	lowwareHouseNumber = dataIfro.warehouseNumber;
        	//仓库信息
        	if(dataIfro.type==6){
        		//仓库编号
        		$("#warehouseNumber").val(dataIfro.warehouseNumber);
        		//仓库编号
        		$("#warehouseName").val(dataIfro.warehouseName);
        		//仓库类别
        		$("#type").val(dataIfro.type);
        		$("#type").attr("disabled","disabled");
        		$("#type").css("background-color","#EEEEEE");
        		//存储类别
        		$("#warehouseType").val(""+dataIfro.warehouseType+"");
        		//备注
        		$("#remarks").html(dataIfro.remarks)
        	}
        	//储罐信息
        	if(dataIfro.type==7){
        		//仓库编号
        		$("#warehouseNumber").val(dataIfro.warehouseNumber);
        		//仓库编号
        		$("#warehouseName").val(dataIfro.warehouseName);
        		//仓库类别
        		$("#type").val(dataIfro.type);
        		$("#type").attr("disabled","disabled");
        		$("#type").css("background-color","#EEEEEE");
        		//储罐高
        		$("#tankHeight").val(dataIfro.tankHeight);
        		//罐容
        		$("#tankCapacity").val(dataIfro.tankCapacity);
        		//位置
        		$("#position").val(dataIfro.position);
        		//液体高度
        		$("#liquidHeight").val(dataIfro.liquidHeight);
        		//储罐预警最低高度
        		$("#alarmLow").val(dataIfro.alarmLow);
        		//储罐预警最高高度
        		$("#alarmHigh").val(dataIfro.alarmHigh);
        		//备注
        		$("#remarks").html(dataIfro.remarks);
        	}
        }
   });
}

//储罐信息根据选中类别显示
function ShowIsHide(){
	var type = $("#type").val()
	if(type==6){
		$(".isShow").hide();
		$(".isShowHouse").show();
	}
	if(type==7){
		$(".isShow").show();
		$(".isShowHouse").hide();
	}
}

//编号去重
function onchangeWarehouseNumber(e){
	var thisVlaue = e.value;
	for (var i = 0; i < wareHouseList.length; i++) {
		if(wareHouseList[i].warehouseNumber == thisVlaue && wareHouseList[i].warehouseNumber != lowwareHouseNumber){
			if(id!=""&&id!=null){
				e.value = lowwareHouseNumber
			}else{
				e.value = ''
			}
			swal("操作失败", '编号重复', "error");
			return;
		}
	}
}
