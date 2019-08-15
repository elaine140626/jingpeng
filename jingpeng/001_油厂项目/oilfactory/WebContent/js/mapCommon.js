var zooms = 14;
var colorArray = ["#FFB6C1","#DC143C","#DB785","#DB7093","#FF0000","#C71585","#800080","#000980","#1E90FF","#DAA520"];
var baseUrl;
var oldLine; //路线覆盖物
var marker; //点标注
var colorRow = 0; //颜色下标初始化
var colorMap = {}; // 颜色Map
var nowcid = []; //车辆id数组
var markers = []; //点标注集合
var oldLines = []; //路线集合
var RUIDEIIMAGES = "../../img/map/ruidei.png" //瑞德公司图标
var YUNIMAGES = "../../img/map/yunShu.png" //运输图标
var FANIMAGES = "../../img/map/wangFan.png" //返程图标
var ruideiPositions = [121.792897,41.368075]; //瑞德坐标点
var customertransportsInfo;
var user;
var isSelectAll = true;
var map = new AMap.Map("container", {
    center: [121.792897,41.368075],
    zoom: zooms
});
/*AMap.plugin('AMap.Weather', function() {
	var weather = new AMap.Weather();
	//查询实时天气信息, 查询的城市到行政级别的城市，如朝阳区、杭州市
	//未来4天天气预报
	weather.getForecast('盘锦市', function(err, data) {
		if (err) {return;}
		var str = [];
		for (var i = 0,dayWeather; i < data.forecasts.length; i++) {
			dayWeather = data.forecasts[i];
			str.push(dayWeather.date+' <span class="weather">'+dayWeather.dayWeather+'</span> '+ dayWeather.nightTemp + '~' + dayWeather.dayTemp + '℃');
		}
		document.getElementById('forecast').innerHTML = str.join('<br>');
	});
});*/
$(function(){
	baseUrl = getContextPath();
	$.ajax({
		type: "post",
		url: baseUrl+"/index/getMenu.action",
		data:{},
		async:false,
		dataType: "json",
		success: function (data) {
			user = data;
			var orgId = data.rolecode;
			var orgIds = orgId.split(",");
			if($.inArray("0", orgIds)!=-1 || $.inArray("2", orgIds)!=-1 || $.inArray("3", orgIds)!=-1|| $.inArray("13", orgIds)!=-1 || $.inArray("7", orgIds)!=-1){
				isSelectAll = true;
			}else{
				isSelectAll = false;
			}
		}
	})
    var startIcon = new AMap.Icon({
        // 图标尺寸
        size: new AMap.Size(31, 29),
        imageSize: new AMap.Size(31, 29),
        // 图标的取图地址
        image: RUIDEIIMAGES //'images/u1914.png',
    });
	addMarker(ruideiPositions,0,startIcon)
})
function getCustomertransports(){
	$.ajax({
		type: "post",
		url: baseUrl+'/Map/getCustomertransports.action',
		async:false,
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
}
//返回车辆ID和时间 (1.车辆ID 2.实时和时间点 3.开始时间 4.结束时间 )
function getCar(id,isDateChoice,startTime,endTime){
	if(id==''){
		if(marker!=null&&oldLine!=null){
			for (var i = 0; i < markers.length; i++) {
				map.remove(markers[i])
			}
			markers=[];
			for (var i = 0; i < oldLines.length; i++) {
				map.remove(oldLines[i]);
			}
			oldLines=[];
		}
		$("#carAll option").each(function(){
	        var valuecid = $(this).val();   
	        nowcid.push(valuecid);
	    });
		for (var i = 0; i < nowcid.length; i++) {
			if(nowcid[i]==0){
				nowcid.remove(i);
			}
		}
	}
	if(id!=''){
		if($.inArray(id, nowcid)!=-1){
			alert("该车辆已经选择")
			return;
		}
		nowcid.push(id);
	}
	$.unique(nowcid);
	if(isDateChoice == 1 && startTime =="" && endTime == "" ){
		alert("请输入完整时间");
		return;
	}
	if(isDateChoice == 0){
		startTime = beforeNowtimeByMinu(30);
		endTime = getNowtime();
	}
	var params = {
			cid:id,
			startTime:startTime,
			endTime:endTime
	}
	return params;
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

//创建轨迹
function addPolyline (arry,i,carid,colorVehicleID){
	 var path1 = arry
     oldLine = new AMap.Polyline({
       path:path1,
       strokeWeight:6,
       strokeOpacity:0.7,
       strokeColor:colorVehicleID,
       showDir:false,
       zIndex: i,
       extData:{
           id: carid
       }
     })
     
     map.add([oldLine]);
     oldLines.push(oldLine)
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

//1.坐标数组 2.颜色 3.覆盖位置 4.路线ID
function showMapCar(arry,colorVehicleID,i,carid){
          //当前车轨迹
          addPolyline (arry,colorVehicleID,i,carid)
          // 当前位置 
          addMarker(arry,carid,startIcon)
}

//删除窗口
function openInfo(plonCarId,e) {
    //构建信息窗体中显示的内容
    var info = [];
    info.push("<input id=\"submitbutton\" type=\"button\" value=\"移除该车辆\" class=\"ui_input_btnsave\" onclick=\"delCar('"+plonCarId+"')\"/>");
    infoWindow = new AMap.InfoWindow({
    	closeWhenClickMap:true,
        content: info.join("<br/>")  //使用默认信息窗体框样式，显示信息内容
    });
    infoWindow.open(map,e.lnglat);
}

//删除车辆
function delCar(pCarId){
	for (var i = 0; i < markers.length; i++) {
		if(markers[i].getExtData().id == pCarId){
			map.remove(markers[i])
			markers.remove(markers[i])
		}
	}
	for (var i = 0; i < oldLines.length; i++) {
		if(oldLines[i].getExtData().id == pCarId){
			map.remove(oldLines[i]);
			oldLines.remove(oldLines[i]);
			for (var j = 0; j < nowcid.length; j++) {
				if(nowcid[j]==pCarId){
					nowcid.remove(j)
				}
			}
		}
	}
	infoWindow.close();
	getHaveCar(nowcid);
}

//绑定radio点击事件
var radios = document.querySelectorAll("#map-styles input");
radios.forEach(function(ratio) {
  ratio.onclick = setMapStyle;
});
function setMapStyle() {
    var styleName = "amap://styles/" + this.value;
    map.setMapStyle(styleName);
}
//缩放事件
function mapZoomend(){
  var mapZoom = map.getZoom(); //获取当前地图级别
	if(mapZoom>=14){
		for (var c = 0; c < markers.length; c++) {
			if(markers[c].getExtData().id != 0){
				markers[c].setLabel({
					//修改label相对于maker的位置
					offset: new AMap.Pixel(-12, -27),
					content: gpsCar.get(markers[c].getExtData().id)
				});
			}
		}
	}else{
		for (var c = 0; c < markers.length; c++) {
			if(markers[c].getExtData().id != 0){
					markers[c].setLabel({
						content: ""
					});
			}
		}
	}
}

//距离判断
function computeDis(p1,p2,returnState){
	var lineColor;
	if(returnState != 0){
		lineColor = '#FF0000';
	}else{
		lineColor = '#80d8ff';
	}
	var line,text;
    var textPos = p1.divideBy(2).add(p2.divideBy(2));
    var distance = Math.round(p1.distance(p2));
    var path = [p1,p2];
    if(!line){
        line = new AMap.Polyline({
       		map:map,
          	strokeColor:lineColor,
          	isOutline:true,
          	outlineColor:'white',
            path:path
        });
    }else{
        line.setPath(path);
    }
    if(!text){
        text = new AMap.Text({
          	text:'距离目的地'+distance+'米',
            position: textPos,
            map:map,
          	style:{'background-color':'#29b6f6',
    				'border-color':'#e1f5fe',
    				'font-size':'12px'}
        })
    }else{
        text.setText('距离目的地'+distance+'米')
        text.setPosition(textPos)
    }
}

//数组移除方法
Array.prototype.remove=function(obj){
	for(var i =0;i <this.length;i++){
	var temp = this[i];
	if(!isNaN(obj)){
	temp=i;
	}
	if(temp == obj){
	for(var j = i;j <this.length;j++){
	this[j]=this[j+1];
	}
	this.length = this.length-1;
	}
	}
	}


//获取当前时间

function getNowtime(){
var date = new Date(); //日期对象
var now = "";
now = date.getFullYear()+"-"; 
now = now + (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';//取月的时候取的是当前月-1如果想取当前月+1就可以了
now = now + setFormat(date.getDate())+" ";
now = now + setFormat(date.getHours())+":";
now = now + setFormat(date.getMinutes())+":";
now = now + setFormat(date.getSeconds())+"";
return now;

}


//获取从现在到 beforetime 分钟前的时间
function beforeNowtimeByMinu(beforetime){
    var date = new Date(); //日期对象
    date.setMinutes (date.getMinutes () - beforetime);
    var now = "";
    now = date.getFullYear()+"-"; //读英文就行了
    now = now + (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';//取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + setFormat(date.getDate())+" ";
    now = now + setFormat(date.getHours())+":";
    now = now + setFormat(date.getMinutes())+":";
    now = now + setFormat(date.getSeconds())+"";
    return now;
}

//时间位数为1位数时，前面补0
var setFormat=function (x) {
  if (x < 10) {
    x = "0" + x;
  }
  return x;
 }

