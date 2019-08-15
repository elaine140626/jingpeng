var baseUrl = "";
var id = "";
var zooms = 14;
var marker =new AMap.Marker(); //点标注
var markers = []; //点标注集合
var markerXY =new AMap.Marker();
var RUIDEIIMAGES = "../../img/map/ruidei.png" //瑞德公司图标
var ruideiPositions = [121.792897,41.368075]; //瑞德坐标点
var customertransportsInfo;
var CustomerNameList;
var map = new AMap.Map("container", {
    center: [121.792897,41.368075],
    zoom: zooms
});
$(function() {
	$("#customerName").hide();
	// 客户名称下拉列表
	var CustomerNameInfo = getCustomerListId();
	$("#CustomerNameList").empty();
	$("#CustomerNameList").html(CustomerNameInfo);
	baseUrl = getContextPath();
    var startIcon = new AMap.Icon({
        // 图标尺寸
        size: new AMap.Size(31, 29),
        imageSize: new AMap.Size(31, 29),
        // 图标的取图地址
        image: RUIDEIIMAGES //'images/u1914.png',
    });
	addMarker(ruideiPositions,0,startIcon)
	//getCustomerInfo();
	var param = parseUrl();// 解析所有参数 
	if(param != null && param != ""){
		if(param.id != null && param.id != ""){
			id = param['id'];
			//$("#continuityTr").css('display','none');
			//mode = param['mode'];
			//止运地初始化
			$.ajax({
				type: "post",
				url: baseUrl+'/Map/getCustomertransports.action',
				async:false,
				data:{"id":id},
				dataType: "json",
				success: function (data) {
					customertransportsInfo = data
					var endIcon = new AMap.Icon({
						size: new AMap.Size(25, 34),
						image: '//a.amap.com/jsapi_demos/static/demo-center/icons/dir-marker.png',
						imageSize: new AMap.Size(135, 40),
						imageOffset: new AMap.Pixel(-95, -3)
					});
					for (var i = 0; i < customertransportsInfo.length; i++) {
						var arry = [];
						if(customertransportsInfo[i].coordinateX != undefined){
							arry.push([Number(customertransportsInfo[i].coordinateX), Number(customertransportsInfo[i].coordinateY)])
							addMarker(arry[0],0,endIcon,customertransportsInfo[i].transports)
							addCircle(arry[0])
						}
					}
				}
			})
			getTransportsById(id);
		}
/*		id = param['id'];
		getTransportsById(id);*/
	}
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		creater = userInfo.name;
	}
	
	/*
	 * 取消
	 */
	$("#cancelbutton").click(function() {
		/**  关闭弹出iframe  **/
		window.parent.$.fancybox.close();
	});
    document.getElementById("clickOn").onclick = clickOn;
    document.getElementById("clickOff").onclick = clickOff;
});
function parseUrl() {
	var url = location.href;
	var i = url.indexOf('?');
	if (i == -1)
		return;
	var querystr = url.substr(i + 1);
	var arr1 = querystr.split('&');
	var arr2 = new Object();
	var ta = arr1[0].split('=');
	arr2[ta[0]] = ta[1];
	return arr2;
}
function add(){
	//var continuityAdd = $("#continuityAdd").is(':checked')
	var customerId = $("#customerId").val();
    var coordinateX = $("#coordinateX").val();
    var coordinateY =  $("#coordinateY").val();
	var startAddress = $("#startAddress").val();
	var transports = $("#transports").val();
	var distance = $("#distance").val();
	var remarks = $("#remarks").val();
	var cid = id
	
	if(customerId==""){
		swal("操作失败", "必须输入客户名称", "info");
		return;
	}
	if(customerId.length>30){
		swal("操作失败", "客户名称长度不能超过20", "info");
		return;
	}
	if(coordinateX == ''){
		swal("操作失败", "请选取坐标", "info");
		return;
	}
	if(transports==""){
		swal("操作失败", "必须输入运输地点", "info");
		return;
	}
	if(distance==""){
		swal("操作失败", "必输输入运输距离", "info");
		return;
	}
	if(remarks.length>200){
		swal("操作失败", "备注内容过长", "info");
		return;
	}
	if(cid!=""&&cid!=null){
		options = {
				"customerId" : customerId,
				"startAddress" : startAddress,
				"transports" : transports,
				"distance" : distance,
				"coordinateX":coordinateX,
				"coordinateY":coordinateY,
				"remarks":remarks,
				"reviser":creater,
				"id":cid
		};

		 $.ajax({
	         type: "post",
	         url: baseUrl+'/Transports/updateTransports.action',
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
			"customerId" : customerId,
			"startAddress" : startAddress,
			"transports" : transports,
			"distance" : distance,
			"coordinateX":coordinateX,
			"coordinateY":coordinateY,
			"creater":creater,
			"remarks":remarks,
	};
	 $.ajax({
         type: "post",
         url: baseUrl+'/Transports/addTransports.action',
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
  }
}
/*function getCustomerInfo(){
	 $.ajax({
       type: "post",
       url: baseUrl+'/Transports/getCustomerInfo.action',
       async:false,
       dataType: "json",
       contentType : 'application/json;charset=UTF-8',
       success: function (data) {
      	  	$.each(data.data, function (i, v) {
          		var str = '<option value="' + v.uuid + '">'
  				+ v.customerCode + '</option>';
          		$('#customerId').append(str);
              })
       }
  });
}*/
function getTransportsById(id){
	$.ajax({
        type: "post",
        url: baseUrl+'/Transports/getTransportsById.action',
        async:false,
        data:{"id":id},
        dataType: "json",
        success: function (data) {
        	var transports = data.data;
        	$("#customerId").val(transports.customerId);
        	ChangeCustomerName(transports.customerId)
        	$("#startAddress").val(transports.startAddress);
        	$("#transports").val(transports.transports);
        	$("#distance").val(transports.distance);
        	$("#remarks").val(transports.remarks);
        	$("#coordinateX").val(transports.coordinateX);
        	$("#coordinateY").val(transports.coordinateY);
        }
   });
}

//验证方法
function isJudge(){
	//运输起点
	/*var startAddress = $("#startAddress").val();
	if(startAddress.length>10){
		swal("操作失败", "运输地点长度过长,请重新填写", "info");
		$("#startAddress").val("");
		return;
	}*/
	//运输地点
	var transports = $("#transports").val();
	if(transports.length>200){
		swal("操作失败", "运输地点长度过长,请重新填写", "info");
		$("#transports").val("");
		return;
	}
	if(transports != ""){
		$.ajax({
	        type: "post",
	        url: baseUrl+'/Transports/getTransports.action',
	        async:false,
	        data:{"transports":transports},
	        dataType: "json",
	        success: function (data) {
	        	if(data != null){
	        		for(var i=0;i<data.data.length;i++){
		        		//起运地点相同，运输地点不能相同
		            		if(data.data[i].transports == transports){
		            			swal("操作失败", "运输地点不能重复", "info");
		            			$("#transports").val("");
		            			return;
		            		}
		        	}
	        	}
	        }
		});
	}
}
function isJudgeDistance(){
	var distance = $("#distance").val();
	if(distance==""){
		swal("操作失败", "运输距离不能为空", "info");
		return;
	}
	if(distance.length>8){
		swal("操作失败", "运输距离数值过大,请重新填写", "info");
		$("#distance").val("");
		return;
	}
}
function isJudgeRemarks(){
	var remarks = $("#remarks").val();
	if(remarks.length>200){
		swal("操作失败", "备注内容过长", "info");
		$("#remarks").val("");
		return;
	}
}
function clearNoNum(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    } 
} 

//事件绑定
function clickOn(){
    log.success("绑定事件!");  
    map.on('click', showInfoClick);
}
// 解绑事件
function clickOff(){
    log.success("解除事件绑定!"); 
    map.off('click', showInfoClick);
}

function showInfoClick(e){
	markerXY.setMap(null);
	markerXY = null;
    $("#coordinateX").val(e.lnglat.getLng());
    $("#coordinateY").val(e.lnglat.getLat());
    markerXY = new AMap.Marker({
        icon: "//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
        position: [e.lnglat.getLng(),e.lnglat.getLat()],
        offset: new AMap.Pixel(-13, -30)
    });
    markerXY.setMap(map);
}

function addCircle(path){
    var circle = new AMap.Circle({
        center: path,
        radius: 1000, //半径
        borderWeight: 3,
        strokeColor: "#FF33FF", 
        strokeOpacity: 1,
        strokeWeight: 6,
        strokeOpacity: 0.2,
        fillOpacity: 0.4,
        strokeStyle: 'dashed',
        strokeDasharray: [10, 10], 
        // 线样式还支持 'dashed'
        fillColor: '#1791fc',
        zIndex: 50,
    })
    map.add(circle);
}

//创建Marker
function addMarker(arry,carid,startIcon,transports){
    var path = arry
    marker = new AMap.Marker({
        position: path,
        icon: startIcon,
        autoRotation: true,
        extData:{
            id: carid
        }
    });
    if(carid == 0 && transports!=undefined){
    	marker.setLabel({
    		//修改label相对于maker的位置
    		offset: new AMap.Pixel(20, 20),
    		content: transports
    	});
    }
  	map.add(marker);
    markers.push(marker);
}

//获取客户编号
function ChangeCustomerName(value){
	for (var i = 0; i < CustomerNameList.length; i++) {
		if(CustomerNameList[i].Id == value ){
			$("#customerName").val(CustomerNameList[i].CustomerName)
		}
	}
	if(value != ""){
		$.ajax({
			type : "POST",
			url : "../../salesContractManagement/getCustomerName.action",
			async:false,
			data : {},
			dataType : "json",
			success : function(data) {
				$("#customerCode").val("");
				for (var i = 0; i < data.length; i++) {
					if(value == data[i].Id){
						$("#customerCode").val(data[i].CustomerCode);
						return;
					}	
				}
			}
		});
	}
	$("#customerName").show();
	$("#customerId").hide();
}

function showCustomerNameList(){
	$("#customerId").show();
	$("#customerId").val('');
	$("#customerName").hide();
}

function getCustomerListId(){
	var CustomerNameInfo;
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getCustomerName.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			CustomerNameList = data;
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				html += "<option value='" + data[i].Id + "'>"
				+ data[i].CustomerName + "</option>"		
			}
			CustomerNameInfo = html;
		}
	});
	return CustomerNameInfo;
}

