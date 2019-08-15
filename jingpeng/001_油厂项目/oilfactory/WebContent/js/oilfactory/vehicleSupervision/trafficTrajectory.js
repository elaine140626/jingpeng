var carId;//车辆Id
var flag;
var trafficTrajectory;//行车轨迹坐标
var mapColor = new Map(); //颜色Map
var gpsCar = new Map(); //车牌号Map
var arry = [];
var carIds = [];
$(function(){
	//止运地初始化
	getCustomertransports()
    // 创建一个 Icon
	carId = getUrlParam("carId");
	flag = getUrlParam("flag");
	carIds = getCarUrlParam("carIds");
	getTrafficTrajectory();
})

function getTrafficTrajectory(){
	var imageIcon;
	if(carId != null){
		$.ajax({
			type: "post",
			url: baseUrl+'/Map/getCarTrajectory.action',
			data:{"carId":carId,"flag":flag},
			async:false,
			dataType: "json",
			success: function (data) {
				trafficTrajectory = data
			}
		})
	}
	if(carIds != null){
		$.ajax({
			type: "post",
			url: baseUrl+'/Map/getCarTrajectory.action',
			data:{"carId":carIds,"flag":flag},
			async:false,
			dataType: "json",
			success: function (data) {
				trafficTrajectory = data
			}
		})
	}
	var carIdNumber;
	var html = "";
	for (var i = 0; i < trafficTrajectory.length; i++) {
		//车辆颜色KEY值 为gps编号
		var carColorCarId = trafficTrajectory[i][0].carId
		//随机选取颜色
		mapColor.set(carColorCarId,colorArray[Math.floor((Math.random()*colorArray.length))]);
		var colorVehicleID = mapColor.get(carColorCarId)
		arry = [];
		//循环该车坐标
		for (var j = 0; j < trafficTrajectory[i].length; j++) {
			carIdNumber = trafficTrajectory[i][j].carId
			gpsCar.set(carIdNumber,trafficTrajectory[i][j].plateNumber);
			//坐标数组
			arry.push([Number(trafficTrajectory[i][j].coordinateX), Number(trafficTrajectory[i][j].coordinateY)])
		}
		addPolyline(arry,0,carIdNumber,colorVehicleID)
		var carLastX = trafficTrajectory[i][0]
		//该车最后坐标点
		var XY = [carLastX.coordinateX,carLastX.coordinateY]
		//var endXY = [trafficTrajectory[i][0].endAddressX,trafficTrajectory[i][0].endAddressY]
		var positionStr = new AMap.LngLat(carLastX.coordinateX, carLastX.coordinateY)
		var positionEnd = new AMap.LngLat(trafficTrajectory[i][0].endAddressX,trafficTrajectory[i][0].endAddressY)
		var positionRurDe = new AMap.LngLat(121.792897,41.368075);
		//往返状态
		var returnState = trafficTrajectory[i][0].returnState
		if(returnState == 1){
			imageIcon = FANIMAGES;
			computeDis(positionStr,positionRurDe,returnState)
			html += ["<tr>",
				"<td>"+trafficTrajectory[i][0].plateNumber+"</td>",
				"<td>"+Math.round(positionStr.distance(positionRurDe))+"米</td>",
				"</tr>"].join("");
		}else{
			imageIcon = YUNIMAGES;
			computeDis(positionStr,positionEnd,returnState)
			html += ["<tr>",
				"<td>"+trafficTrajectory[i][0].plateNumber+"</td>",
				"<td>"+Math.round(positionStr.distance(positionEnd))+"米</td>",
				"</tr>"].join("");
		}
		var startIcon = new AMap.Icon({
			// 图标尺寸
			size: new AMap.Size(128, 128),
			imageSize: new AMap.Size(32, 32),
			// 图标的取图地址
			image: imageIcon //'images/u1914.png',
		});
		addMarker(XY,carIdNumber,startIcon)
	}
	$("#carTrafficTable").append(html);
	
	//缩放事件
	map.on('zoomend', mapZoomend);
}

//获取前页面参数
function getCarUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null) {
		return r[2];
		// 这里为什么是从第三个字符解析呢？不知道这样理解对不对，因为路径后面的参数形式为参数名=参数值，而第一个字符为参数名，第二个为=，第三个就为参数值了。。。因为下面调用的时候得出的就是参数值
	}
	return null;// 返回参数值
}