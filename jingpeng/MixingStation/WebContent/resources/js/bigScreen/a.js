var baseUrl = "";

$(function() {
	baseUrl = getContextPath();
	getAll();
	getCharts();
});

function getAll() {
	$.ajax({
		type : "POST",
		url : baseUrl + "/BigScreen/getAll.html",
		data : {},
		dataType : "json",
		async: false,
		success : function(data) {
			var left = data.left;
			var tabHtml = "<ul>";
			for(var i = 0; i < left.length; i++) {
				Total = Number(left[i].total);
				Eligibility = Number(left[i].eligibility);
				var hgl = Eligibility/Total*10
				if(isNaN(hgl)) {
					hgl = 0
				}
				tabHtml += "<li>"
					    +"<h4><span></span>"+left[i].org_Name+"</h4>"
					    +"<p><b>生产总量(t)：</b><span>"+left[i].totalWeight+"</span></p>"
					    +"<p><b>合格率：</b><span>"+hgl.toFixed(2)+"%</span></p>"
			            +"</li>";
			}
			tabHtml += "</ul>";
			$("#left").html(tabHtml);
			
			var tab = data.Tab;
			var tabHtml = "";
			for(var i = 0; i < tab.length; i++) {
				Total = Number(tab[i].total);
				Eligibility = Number(tab[i].eligibility);
				var hgl = Eligibility/Total*100
				if(isNaN(hgl)) {
					hgl=0
				}
				tabHtml += "<tr>"
                     +"<td>"+tab[i].org_Name+"<input type='hidden' value='"+tab[i].org_ID+","+tab[i].type+"'></td>"
                     +"<td>"+tab[i].totalWeight+"</td>"
                     +"<td>"+tab[i].total+"</td>"
                     +"<td>"+tab[i].eligibility+"</td>"
                     +"<td>"+hgl.toFixed(2)+"</td>"
                     +"<tr>";
			}
			$("#tab").html(tabHtml);
		}
	});
}

function getCharts() {
	var org_ids = [];
	$("#tab input[type=hidden]").each(function(){
		org_id = $(this).val();
		org_ids.push(org_id)
	});
	getPie(org_ids[0]);
	getBar(org_ids[0]);
	getAxis(org_ids[0]);
}

function getPie(org_id) {
	var arr = org_id.split(",")
	$.ajax({
		type : "POST",
		url : baseUrl + "/BigScreen/getPie.html",
		data : {"org_id":arr[0]},
		dataType : "json",
		async: false,
		success : function(data) {
			var eligibility = Number(data.eligibility);
			var total = Number(data.total);
			var uneligibility = total-eligibility;
			str = "[{"
		            +"name: '',"
		            +"type: 'pie',"
		            +"radius : '55%',"
		            +"center: ['50%', '50%'],"
		            +"data:["
		            +"{value:"+eligibility+", name:'合格盘数'},"
		            +"{value:"+uneligibility+", name:'不合格盘数'}"
		            +"],"
		            +"itemStyle: {"
		            +"emphasis: {"
		            +"shadowBlur: 10,"
		            +"shadowOffsetX: 0,"
		            +"shadowColor: 'rgba(0, 0, 0, 0.5)'"
		            +"}"
		            +"}"
		            +"}]";
		    var Series = eval('(' + str + ')');
		    str = "{"
	        +"text: '"+data.org_Name+"',"
	        +"x:'center'"
	        +"}";
		    var Title = eval('(' + str + ')');
		    option1.series = Series; 
		    option1.title = Title; 
		    var myChart1 = echarts.init(document.getElementById('chartmain1'));
		    myChart1.setOption(option1);
		}
	});
	
}

function getBar(org_id) {
	var arr = org_id.split(",");
	$.ajax({
		type : "POST",
		url : baseUrl + "/BigScreen/getBar.html",
		data : {"org_id":arr[0],"type":arr[1]},
		dataType : "json",
		async: false,
		success : function(data) {
			var d = "";
			var x = "";
			if(data[0].type == "0") {
				x = "{"
			         +"type : 'category',"
			         +"data : ['不合格', '骨料不合格', '粉料不合格', '沥青不合格', '外掺剂不合格', '级配不合格'],"
			         +"axisTick: {"
			         +"alignWithLabel: true"
			         +"},"
			         +"nameTextStyle: {"
			         +"fontSize:7"
			         +"},"
			         +"axisLabel: {"
			         +"color:'#ffffff'"
				     +"},"
				     +"axisLine: {"
				     +"lineStyle: {"
				     +"color: '#ffffff'"
				     +"}"
				     +"}"
				     +"} "
				d = "{"
			           +"name:'',"
			           +"type:'bar',"
			           +"barWidth: '60%',"
			           +"itemStyle: {"
			           +"normal: {"
			           +"color: function(params) { "
			           +"var colorList = ['#c1232b','#b5c334','#27727b','#fad860','#60c0dd','#60c0dd']; "
			           +"return colorList[params.dataIndex] "
			           +"}"
			           +"},"
			           +"},"
			           +"data:["+Number(data[0].unqualified)+", "+Number(data[0].glunqualified)+", "+Number(data[0].flunqualified)+", "+Number(data[0].lqunqualified)+", "+Number(data[0].wcjunqualified)+", "+Number(data[0].jpunqualified)+"]"
			           +"}";
			}
			if(data[0].type == "1") {
				x = "{"
			         +"type : 'category',"
			         +"data : ['不合格','外掺剂不合格','骨料仓不合格','水泥仓不合格','水仓不合格'],"
			         +"axisTick: {"
			         +"alignWithLabel: true"
			         +"},"
			         +"nameTextStyle: {"
			         +"fontSize:7"
			         +"},"
			         +"axisLabel: {"
			         +"color:'#ffffff'"
				     +"},"
				     +"axisLine: {"
				     +"lineStyle: {"
				     +"color: '#ffffff'"
				     +"}"
				     +"}"
				     +"} "
				d = "{"
			           +"name:'',"
			           +"type:'bar',"
			           +"barWidth: '60%',"
			           +"itemStyle: {"
			           +"normal: {"
			           +"color: function(params) { "
			           +"var colorList = ['#c1232b','#b5c334','#27727b','#fad860','#60c0dd','#60c0dd']; "
			           +"return colorList[params.dataIndex] "
			           +"}"
			           +"},"
			           +"},"
			           +"data:["+Number(data[0].unqualified)+", "+Number(data[0].glunqualified)+", "+(data[0].wcjunqualified)+", "+Number(data[0].snunqualified)+", "+Number(data[0].waterunqualified)+"]"
			           +"}";
			}
			if(data[0].type == "2") {
				x = "{"
			         +"type : 'category',"
			         +"data : ['不合格','骨料不合格','外掺剂不合格','水泥仓不合格','水仓不合格'],"
			         +"axisTick: {"
			         +"alignWithLabel: true"
			         +"},"
			         +"axisLabel: {"
			         +"color:'#ffffff'"
				     +"},"
				     +"nameTextStyle: {"
			         +"fontSize:7"
			         +"},"
				     +"axisLine: {"
				     +"lineStyle: {"
				     +"color: '#ffffff'"
				     +"}"
				     +"}"
				     +"} "
				d = "{"
			           +"name:'',"
			           +"type:'bar',"
			           +"barWidth: '30%',"
			           +"itemStyle: {"
			           +"normal: {"
			           +"color: function(params) { "
			           +"var colorList = ['#c1232b','#b5c334','#27727b','#fad860','#60c0dd','#60c01d']; "
			           +"return colorList[params.dataIndex] "
			           +"}"
			           +"},"
			           +"},"
			           +"data:["+Number(data[0].unqualified)+", "+Number(data[0].glunqualified)+", "+Number(data[0].wcjunqualified)+", "+Number(data[0].SNUnqualified)+", "+Number(data[0].waterunqualified)+"]"
			           +"}";
			}
			var Series = eval('(' + d + ')');
			var XAxis = eval('(' + x + ')');
			option3.series = Series; 
			option3.xAxis = XAxis; 
			var myChart3 = echarts.init(document.getElementById('chartmain3'));
		    myChart3.setOption(option3);
		}
	});
}

function getAxis(org_id) {
	var arr = org_id.split(",");
	$.ajax({
		type : "POST",
		url : baseUrl + "/BigScreen/getAxis.html",
		data : {"org_id":arr[0],"type":arr[1]},
		dataType : "json",
		async: false,
		success : function(data) {
			var s = "[{name:'"+data[0].material+"',"
		         +"type:'line',"
		         +"stack:'总量',"
		         +"data:";
			var arr = [];
			var material = data[0].material;
			for(var i = 0; i < data.length; i++) {
				if(material != data[i].material) {
					var d = JSON.stringify(arr);
					s += d;
					material = data[i].material;
					s += "},{name:'"+data[i].material+"',"
				         +"type:'line',"
				         +"stack: '总量',"
				         +"data:";
					arr = [];
				}
				arr.push(data[i].materialConsumption);
				if(i + 1 == data.length) {
					var d = JSON.stringify(arr);
					s += d;
				}
			}
			s += "}]";
			var Series = eval('(' + s + ')');
			option2.series = Series; 
			var myChart2 = echarts.init(document.getElementById('chartmain2'));
			myChart2.setOption(option2);
		}
	});
}
