baseUrl = getContextPath();
var startTime; //开始时间
var endTime; //结束时间
var mapColor = new Map(); //颜色Map
var gpsCar = new Map(); //颜色Map
var carTrajectoryXY;//所有行车坐标
var fleetCars;
var carInfos;
var userId;
var fleetNameArray = [];
$(function(){
	//止运地初始化
	getCustomertransports()
	if(isSelectAll){
		userId = '';
	}else{
		userId = user.id;
	}
	$.ajax({
		type: "post",
		url: baseUrl+'/Map/getCarTrajectory.action',
		data:{"userId":userId},
		async:false,
		dataType: "json",
		success: function (data) {
			carTrajectoryXY = data
		}
	})
	getAllCar();
/*	$.ajax({
		type: "post",
		url: baseUrl+'/Map/getFleetCars.action',
		async:false,
		data : {isSelectFleet:1},
		dataType : "json",
		success: function (data) {
			fleetCars = data
			var html = "<option value='-1' selected='selected'>--请选择车队---</option>";
			for (var i = 0; i < fleetCars.length; i++) {
				html += "<option value='" + fleetCars[i].fid + "'>"
				+ fleetCars[i].fleetName + "</option>"		
			}
			$("#fleetinfos").append(html)
		}
	})*/
})

function getAllCar(){
	var imageIcon;
	var yunCount = 0
	var fanCount = 0
	
	var isHave = []
	for (var i = 0; i < carTrajectoryXY.length; i++) {
		var fleetNameInfo = {
				fid:carTrajectoryXY[i][0].fid,
				fleetName:carTrajectoryXY[i][0].fleetName
			};
		var index = $.inArray(carTrajectoryXY[i][0].fleetName, isHave);
		//车辆名称重复则不添加
		if(index < 0){
			isHave.push(carTrajectoryXY[i][0].fleetName)
			fleetNameArray.push(fleetNameInfo)
		}
		//唯一ID 为GPS编号
		if( carTrajectoryXY[i].length > 0){
			var carId = carTrajectoryXY[i][0].carId
			//最新坐标点
			gpsCar.set(carId,carTrajectoryXY[i][0].plateNumber);
			var carLastX = carTrajectoryXY[i][0]
			var XY = [carLastX.coordinateX,carLastX.coordinateY]
			var returnState = carTrajectoryXY[i][0].returnState
			if(returnState == 1){
				imageIcon = FANIMAGES;
				fanCount = fanCount+1
			}else{
				imageIcon = YUNIMAGES;
				yunCount = yunCount+1
			}
			var startIcon = new AMap.Icon({
				// 图标尺寸
			     size: new AMap.Size(128, 128),
			     imageSize: new AMap.Size(32, 32),
				// 图标的取图地址
				image: imageIcon //'images/u1914.png',
			});
			addMarker(XY,carId,startIcon)
		}
	}
	var html = "<option value='-1' selected='selected'>--请选择车队---</option>";
	for (var i = 0; i < fleetNameArray.length; i++) {
			html += "<option value='" + fleetNameArray[i].fid + "'>"
			+ fleetNameArray[i].fleetName + "</option>"		
	}
	$("#fleetinfos").append(html)
	$("#carAllCount").html(carTrajectoryXY.length);
	$("#caryunCount").html(yunCount);
	$("#carfanCount").html(fanCount);
	for (var q = 0; q < markers.length; q++) {
		markers[q].on('dblclick', function(e) {
			hrefTrafficTrajectory(this.getExtData().id,e)
		});
	}
	
	//缩放事件
	map.on('zoomend', mapZoomend);
}

function hrefTrafficTrajectory(carId,e){
	location.href = 'trafficTrajectory.html?carId='+carId+"&flag=1";
}

//多选车辆
function getCars(e){
	var	html ="";
	if(e.value != -1){
		//$("#carDiv").css("display","block")
		$.ajax({
			type: "post",
			url: baseUrl+'/Map/getCarTrajectory.action',
			async:false,
			data : {fid:e.value,"userId":userId},
			dataType : "json",
			success: function (data) {
				carInfos = data
				$("#carinfo").html("")
				if(carInfos.length > 0){
					for (var i = 0; i < carInfos.length; i++) {
						html += ["<input name=\"carInfos\" type=\"checkbox\" value=\""+carInfos[i][0].carId+"\" />"+carInfos[i][0].plateNumber+""].join("");
						if(i != 0 && i % 2 == 0){
							html +=["<br>"].join("");
						}
					}
					html +=["<br>"].join("");
					html +=["<button id=\"getTraffics\" onclick=\"getTraffics()\">搜索车辆</button>"].join("");
				}else{
					html += "无车辆"	
				}
			}
		})
	}
	$("#carinfo").append(html)
}

//多选车辆判断
function getTraffics(){
	 var vals = [];
	 $('input:checkbox:checked').each(function (index, item) {
		 vals.push($(this).val());
	 });
	 if(vals.length > 5){
		 alert("最多选取5台车辆")
		 return;
	 }
	 var valString = "";
	 for (var i = 0; i < vals.length; i++) {
		 if(i == 0){
			 valString += ""+vals[i]
		 }else{
			 valString += ","+vals[i]
		 }
	}
	 location.href = 'trafficTrajectory.html?carIds='+valString;
}

