//待出厂检测条数
var countQuality = 0;
//待下发生产工艺通知单条数
var countPlanQuality = 0;
function myrefresh() 
{ 
       window.location.reload(); 
} 
setTimeout('myrefresh()',6000000); 

$(function(){
	//获取待出厂检测信息
	getQualityList();
	
	//查询待出厂检测数据
	getPlanQualityList();
});

//获取待出厂检测信息
function getQualityList(){
	$.ajax({
		type: "post",
		url: "../../quality/getAllBeforeQuality.action",
		data: {},
		dataType: "json",
		async:false,
		success: function (data) {
			$("#qualityList").html("");
			countQuality = data.length;
			$("#count1").html(countQuality);
			if(data != null && data.length > 0){
				var html = "";
				for(var i = 0; i < data.length; i++){
					html +="<tr>"
						+"<td>"+(i+1)+"</td>"
						+"<td>"+(data[i].plateNumber == null ? '' : data[i].plateNumber)+"</td>"
						+"<td>"+(data[i].materielNameId == null ? '' : data[i].materielNameId)+"</td>"
						+"<td>"+(data[i].materielModelId == null ? '' : data[i].materielModelId)+"</td>"
						+"<td>"+(data[i].suttle == null ? '' : data[i].suttle)+"</td>"
						+"<td>"+(data[i].client == null ? '' : data[i].client)+"</td>"
						+"</tr>";
				}
				$("#qualityList").append(html);
			}			
		}
	})
}

//查询待下发生产工艺通知单
function getPlanQualityList(){
	$.ajax({
		type: "post",
		url: "../../quality/getAllPlanQuality.action",
		data: {},
		dataType: "json",
		async:false,
		success: function (data) {
			$("#planQualityList").html("");
			countPlanQuality = data.length;
			$("#count2").html(countPlanQuality);
			if(data != null && data.length > 0){
				var html = "";
				for(var i = 0; i < data.length; i++){
					html +="<tr>"
						+"<td>"+(i+1)+"</td>"
						+"<td>"+(data[i].batch == null ? '' : data[i].batch)+"</td>"
						+"<td>"+(data[i].materielName == null ? '' : data[i].materielName)+"</td>"
						+"<td>"+(data[i].materielModel == null ? '' : data[i].materielModel)+"</td>"
						+"<td>"+(data[i].planWeight == null ? '' : data[i].planWeight)+"</td>"
						+"<td>"+(data[i].unit == null ? '' : data[i].unit)+"</td>"
						+"<td>"+(data[i].customerCode == null ? '' : data[i].customerCode)+"</td>"
						+"</tr>";
				}
				$("#planQualityList").append(html);
			}			
		}
	})
}