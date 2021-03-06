var startTime;
var endTime;
var org_ID;
var weight = "";//盘重量
$(function() {
	getList();
	//查询数据总和
	searchSum();
});

// 生产统计dataTable
function getList() {
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	org_ID = $("#orgIds").val();
	if (org_ID.length>0){
		org_ID = org_ID.substr(1,org_ID.length-1)
	}
	var table = $('#list').dataTable();
	table.fnDestroy();
	$('#list').DataTable({
		"paging" : true,
		"lengthChange" : false,
		"bLengthChange" : false,
		"pageLength" : 14,
		"searching" : false,
		"ordering" : false,
		"info" : true,
		"sInfo" : true,
		"autoWidth" : false,
		"language" : {
			"lengthMenu" : "每页 _MENU_ 条记录",
			"zeroRecords" : "没有找到记录",
			"info" : "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
			"infoEmpty" : "无记录",
			"sSearch" : "在结果中查找：",
			"infoFiltered" : "(从 _MAX_ 条记录过滤)",
			"oPaginate" : {
				"sFirst" : "第一页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "最后一页"
			},
		},
		"ajax" : {
			type : "post",
			url : "../../PlatformAsphaltData/getPlatformAsphaltData.action",
			dataSrc : "data",
			data : function(d) {
				var param = {};
				param.startTime = startTime + " " + "00:" + "00:" + "00";
				param.endTime = endTime + " " + "23:" + "59:" + "59";
				param.org_ID = org_ID;
				param.Analysis_Result = 0;
				return param;// 自定义需要传递的参数。
			}
		},
		"columns" : [
				{"data" : "serialNumber"},
				{"data" : "org_Name"},
				{
					"data" : "collect_Time",
					render : function(data, type, row, meta) {
						row = JSON.stringify(row);
						var html = '';
						html = "<span><a onclick='detailed("+ row + ")'>" + data + "</a></span>";
						return html;
					}
				}, 
				{"data" : "equipment_Name"}, 
				{"data" : "str_productInfo"}, 
				{
					"data" : "total_Weight",
					render : function(data, type, row, meta){
						if(row.org_Name.indexOf("一标") != -1){
							if(row.no1_MeterValue > 20){
								data = data - 20;
							}
							if(row.no2_MeterValue > 20){
								data = data - 20;
							}
							if(row.no3_MeterValue > 20){
								data = data - 20;
							}
							if(row.no4_MeterValue > 20){
								data = data - 20;
							}
							if(row.no5_MeterValue > 20){
								data = data - 20;
							}
							if(row.no6_MeterValue > 20){
								data = data - 20;
							}
						}else{
							data = row.total_Weight;
						}
						return parseFloat(data).toFixed(2);
					}
				}, 
				{
					"data" : "no1_MeterValue",
					render : function(data, type, row, meta){
						if(row.org_Name.indexOf("一标") != -1){
							if(row.no1_MeterValue > 20){
								data = row.no1_MeterValue - 20;
							}else{
								data = row.no1_MeterValue;
							}
						}
						return data;
					}
				},
				{
					"data" : "no2_MeterValue",
					render : function(data, type, row, meta){
						if(row.org_Name.indexOf("一标") != -1){
							if(row.no2_MeterValue > 20){
								data = row.no2_MeterValue - 20;
							}else{
								data = row.no2_MeterValue;
							}
						}
						return data;
					}
				}, 
				{
					"data" : "no3_MeterValue",
					render : function(data, type, row, meta){
						if(row.org_Name.indexOf("一标") != -1){
							if(row.no3_MeterValue > 20){
								data = row.no3_MeterValue - 20;
							}else{
								data = row.no3_MeterValue;
							}
						}
						return data;
					}
				},
				{
					"data" : "no4_MeterValue",
					render : function(data, type, row, meta){
						if(row.org_Name.indexOf("一标") != -1){
							if(row.no4_MeterValue > 20){
								data = row.no4_MeterValue - 20;
							}else{
								data = row.no4_MeterValue;
							}
						}
						return data;
					}
				},
				{
					"data" : "no5_MeterValue",
					render : function(data, type, row, meta){
						if(row.org_Name.indexOf("一标") != -1){
							if(row.no5_MeterValue > 20){
								data = row.no5_MeterValue - 20;
							}else{
								data = row.no5_MeterValue;
							}
						}
						return data;
					}	
				}, 
				{
					"data" : "no6_MeterValue",
					render : function(data, type, row, meta){
						if(row.org_Name.indexOf("一标") != -1){
							if(row.no6_MeterValue > 20){
								data = row.no6_MeterValue - 20;
							}else{
								data = row.no6_MeterValue;
							}
						}
						return data;
					}
				}, 
				{"data" : "coldP_MeterValue"},
				{"data" : "coldP2_MeterValue"},
				{"data" : "hotP_MeterValue"},
				{"data" : "asphalt_Meter"}, 
				{"data" : "admixture1"}, 
				{"data" : "admixture2"}, 
				{
					"data" : "analysis_Result",
					render : function(data, type, row, meta) {
						var str =''
							var a=0;
			            	var result=data.split(",");
			            	for(var i=0;i<result.length;i++){
			            		if(result[i] == 1){
			            			str += '正常   '
			            				a=1;
			            		}
			            		if(result[i] == 2 || result[i] == 3 || result[i] == 4){
			            			str += '骨料超差   '
			            		}
			            		if(result[i] == 5 || result[i] == 6 || result[i] == 7){
			            			str += '粉料超差   '
			            		}
			            		if(result[i] == 8 || result[i] == 9 || result[i] == 10){
			            			str += '沥青超差   '
			            		}
			            		if(result[i] == 11 || result[i] == 12 || result[i] == 13){
			            			str += '外掺剂超差   '
			            		}
			            		if(result[i] == 14){
			            			str += '温度超差   '
			            		}
			            	}
		  	            	var html = '';
		  	            	if(a==1){
		  	            		html += "<span style='color: black;' >"+str+"</span>";	
		  	            	}else{
		  	            		html += "<span style='color: red;' >"+str+"</span>";
		  	            	}
		  		  		    return html;
					}
				}],
		"createdRow" : function(row, data, dataIndex) {
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ]
	});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#list_info').css('text-align', 'center');
}

// popup明细
function detailed(row) {
	// 产品名称/型号
	//$("#str_productInfo").html(row.str_productInfo);
	// 盘重量(kg)
<<<<<<< .mine
	//$("#d_total_Weight").html(row.total_Weight);
	//weight = row.total_Weight;
||||||| .r357
	$("#d_total_Weight").html(row.total_Weight);
	//weight = row.total_Weight;
=======
	var total_Weight = row.total_Weight;
	if(row.org_Name.indexOf("一标") != -1){
		if(row.no1_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		if(row.no2_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		if(row.no3_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		if(row.no4_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		if(row.no5_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		if(row.no6_MeterValue > 20){
			total_Weight = total_Weight - 20;
		}
		$("#d_total_Weight").html(total_Weight.toFixed(2));
	}else{
		$("#d_total_Weight").html(parseFloat(row.total_Weight).toFixed(2));
	}
>>>>>>> .r512
	// 采集数据明细
	getTable1(row.id);
	// 筛分通过率(数据id，级配id，标段id)
	getTable2(row.id,row.pro_Id,row.org_ID);
	// 弹窗显示
	$('#loginModal').modal('show');
}

// 采集数据明细
function getTable1(id) {
	$.ajax({
		type : "POST",
		url : "../../PlatformAsphaltData/getAsphaltPropDataAnalysis.action",
		data : {"id" : id},
		dataType : "json",
		success : function(data) {
			var html = "";
			if (data.data.length != 0) {
				for (var i = 0; i < data.data.length; i++) {
					if(data.data[i].data_Type == 0){
						html += "<tr><td class='dise'>" + data.data[i].data_Name
						+ "</td>" + "<td>" + data.data[i].no1 + "</td>"
						+ "<td>" + data.data[i].no2 + "</td>" + "<td>"
						+ data.data[i].no3 + "</td>" + "<td>"
						+ data.data[i].no4 + "</td>" + "<td>"
						+ data.data[i].no5 + "</td>" + "<td>"
						+ data.data[i].no6 + "</td>" + "<td>"
						+ data.data[i].hotPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder2 + "</td>" + "<td>"
						+ data.data[i].asphalt + "</td>" + "<td>"
						+ data.data[i].admixture1 + "</td>" + "<td>"
						+ data.data[i].admixture2 + "</td></tr>";
					}
					if(data.data[i].data_Type == 1){
						html += "<tr><td class='dise'>" + data.data[i].data_Name
						+ "</td>" + "<td>" + data.data[i].no1 + "</td>"
						+ "<td>" + data.data[i].no2 + "</td>" + "<td>"
						+ data.data[i].no3 + "</td>" + "<td>"
						+ data.data[i].no4 + "</td>" + "<td>"
						+ data.data[i].no5 + "</td>" + "<td>"
						+ data.data[i].no6 + "</td>" + "<td>"
						+ data.data[i].hotPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder2 + "</td>" + "<td>"
						+ data.data[i].asphalt + "</td>" + "<td>"
						+ data.data[i].admixture1 + "</td>" + "<td>"
						+ data.data[i].admixture2 + "</td></tr>";
					}
					if(data.data[i].data_Type == 2){
						html += "<tr><td class='dise'>" + data.data[i].data_Name
						+ "</td>" + "<td>" + data.data[i].no1 + "</td>"
						+ "<td>" + data.data[i].no2 + "</td>" + "<td>"
						+ data.data[i].no3 + "</td>" + "<td>"
						+ data.data[i].no4 + "</td>" + "<td>"
						+ data.data[i].no5 + "</td>" + "<td>"
						+ data.data[i].no6 + "</td>" + "<td>"
						+ data.data[i].hotPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder2 + "</td>" + "<td>"
						+ data.data[i].asphalt + "</td>" + "<td>"
						+ data.data[i].admixture1 + "</td>" + "<td>"
						+ data.data[i].admixture2 + "</td></tr>";
					}
					if(data.data[i].data_Type == 4){
						html += "<tr><td class='dise'>" + data.data[i].data_Name
						+ "</td>" + "<td>" + data.data[i].no1 + "</td>"
						+ "<td>" + data.data[i].no2 + "</td>" + "<td>"
						+ data.data[i].no3 + "</td>" + "<td>"
						+ data.data[i].no4 + "</td>" + "<td>"
						+ data.data[i].no5 + "</td>" + "<td>"
						+ data.data[i].no6 + "</td>" + "<td>"
						+ data.data[i].hotPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder + "</td>" + "<td>"
						+ data.data[i].coldPowder2 + "</td>" + "<td>"
						+ data.data[i].asphalt + "</td>" + "<td>"
						+ data.data[i].admixture1 + "</td>" + "<td>"
						+ data.data[i].admixture2 + "</td></tr>";
					}
				}
			}
			$("#tb").html(html);
		}
	});
}
function getTable2(id,pro_Id,org_id,grad_Id){
	$.ajax({
		type : "POST",
		url : "../../PlatformAsphaltData/getAsphaltGradDataAnalysis.action",
		data : {"id" : id,"Mp_Id" : pro_Id,"data_Type" : 1,"org_id":org_id,"grad_Id":grad_Id},
		dataType : "json",
		success : function(data) {
			//列表
            var tb2 = "";
			//earch图
			var meshSizelist = [];
            var upperLimitlist = [];
            var lowerLimitlist = [];
            var medianlimitlist = [];
            var comprehensivelist = [];
            for (var i = 0; i < data.data.length; i++) {
            	meshSizelist.push(data.data[i].meshSize);
            	upperLimitlist.push(data.data[i].upperLimit);
            	lowerLimitlist.push(data.data[i].lowerLimit);
            	medianlimitlist.push(evenRound(data.data[i].medianlimit,1));
            	comprehensivelist.push(evenRound((data.data[i].comprehensive),1));
            	//列表
            	tb2 += "<tr>" 
            				+"<td>"+data.data[i].meshSize+"</td>"
            				+"<td>"+data.data[i].upperLimit+"</td>"
            				+"<td>"+data.data[i].lowerLimit+"</td>"
            				+"<td>"+evenRound(data.data[i].medianlimit,1)+"</td>"
            				+"<td>"+evenRound((data.data[i].comprehensive),1)+"</td>"
            			+"</tr>";
            }
            option = {
            	    tooltip: {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:['级配上线','级配下限','级配中值','综合级配']
            	    },
            	    grid: {
            	        left: '3%',
            	        right: '4%',
            	        bottom: '3%',
            	        containLabel: true
            	    },
            	    toolbox: {
            	        feature: {
            	            saveAsImage: {}
            	        }
            	    },
            	    xAxis: {
            	        type: 'category',
            	        boundaryGap: false,
            	        data: meshSizelist
            	    },
            	    yAxis: {
            	        type: 'value'
            	    },
            	    series: [
            	        {
            	            name:'级配上线',
            	            type:'line',
            	            data:upperLimitlist
            	        },
            	        {
            	            name:'级配下限',
            	            type:'line',
            	            data:lowerLimitlist
            	        },
            	        {
            	            name:'级配中值',
            	            type:'line',
            	            data:medianlimitlist
            	        },
            	        {
            	            name:'综合级配',
            	            type:'line',
            	            data:comprehensivelist
            	        }]
            	};
            var myChart = echarts.init(document.getElementById('shaifen_chart'));
            myChart.setOption(option,true);
            $("#tb2").html(tb2);
		}
	});
}

//四舍六入 && 补零 参数1：要变换的数；参数2：保留小数位数
function evenRound(num, decimalPlaces) {
	if (num == "" || num == null){
		return "";
	} else {
		// 四舍六入
	    var d = decimalPlaces || 0;
	    var m = Math.pow(10, d);
	    var n = +(d ? num * m : num).toFixed(8); // Avoid rounding errors
	    var i = Math.floor(n), f = n - i;
	    var e = 1e-8; // Allow for rounding errors in f
	    var r = (f > 0.5 - e && f < 0.5 + e) ?
	                ((i % 2 == 0) ? i : i + 1) : Math.round(n);
		// 补零
	    var number = d ? r / m : r;
	    var f_x = parseFloat(number);
	    if (isNaN(f_x)) {
	        return 0;
	    }
	    var s_x = number.toString();
	    var pos_decimal = s_x.indexOf('.');
	    if (pos_decimal < 0 && decimalPlaces > 0) {
	        pos_decimal = s_x.length;
	        s_x += '.';
	    }
	    while (s_x.length <= pos_decimal + decimalPlaces) {
	        s_x += '0';
	    }
	    return s_x;
	}	
}

//查询数据总和
function searchSum(){
	startTime = $("#startTime").val();
	endTime = $("#endTime").val();
	org_ID = $("#orgIds").val();
	var param = {};
	param.startTime = startTime + " " + "00:" + "00:" + "00";
	param.endTime = endTime + " " + "23:" + "59:" + "59";
	param.org_ID = org_ID;
	param.Analysis_Result = 0;
	$.ajax({
		type : "post",
		url : "../../PlatformAsphaltData/getPlatformAsphaltDataSum.action",
		data : param,
		async : false,
		dataType : "json",
		success : function(data) {
			if(data != null && data.length > 0){
					//一标参数
					var TotalWeightSum = 0;
					var No1MeterValueSum = 0;
					var No2MeterValueSum = 0;
					var No3MeterValueSum = 0;
					var No4MeterValueSum = 0;
					var No5MeterValueSum = 0;
					var No6MeterValueSum = 0;
					//其它标参数
					var TotalWeightOtherSum = 0;
					var No1MeterValueOtherSum = 0;
					var No2MeterValueOtherSum = 0;
					var No3MeterValueOtherSum = 0;
					var No4MeterValueOtherSum = 0;
					var No5MeterValueOtherSum = 0;
					var No6MeterValueOtherSum = 0;
					//共同参数
					//冷粉
					var ColdPMeterValueSum = 0;
					//冷粉2
					var ColdP2MeterValueSum = 0;
					//热粉
					var HotPMeterValueSum = 0;
					//沥青
					var AsphaltMeterSum = 0;
					//外掺剂1
					var Admixture1Sum = 0;
					//外掺剂1
					var Admixture2Sum = 0;
					
					for (var i = 0; i < data.length; i++) {
					//总盘重量
					if(data[i].orgId == 5){//一标
						TotalWeightSum += data[i].TotalWeightSum - data[i].Subtractive1 - data[i].Subtractive2 - data[i].Subtractive3 - data[i].Subtractive4 - data[i].Subtractive5 - data[i].Subtractive6;
					}else{
						TotalWeightOtherSum += data[i].TotalWeightSum;
					}
					
					//1#仓
					if(data[i].orgId == 5){//一标
						No1MeterValueSum += data[i].No1MeterValueSum - data[i].Subtractive1;
					}else{
						No1MeterValueOtherSum += data[i].No1MeterValueSum;
					}
					
					//2#仓
					if(data[i].orgId == 5){//一标
						No2MeterValueSum += data[i].No2MeterValueSum - data[i].Subtractive2;
					}else{
						No2MeterValueOtherSum += data[i].No2MeterValueSum;
					}
					
					//3#仓
					if(data[i].orgId == 5){//一标
						No3MeterValueSum += data[i].No3MeterValueSum - data[i].Subtractive3;
					}else{
						No3MeterValueOtherSum += data[i].No3MeterValueSum;
					}
					
					//4#仓
					if(data[i].orgId == 5){//一标
						No4MeterValueSum += data[i].No4MeterValueSum - data[i].Subtractive4;
					}else{
						No4MeterValueOtherSum += data[i].No4MeterValueSum;
					}
					
					//5#仓
					if(data[i].orgId == 5){//一标
						No5MeterValueSum += data[i].No5MeterValueSum - data[i].Subtractive5;
					}else{
						No5MeterValueOtherSum += data[i].No5MeterValueSum;
					}
					
					//6#仓
					if(data[i].orgId == 5){//一标
						No6MeterValueSum += data[i].No6MeterValueSum - data[i].Subtractive6;
					}else{
						No6MeterValueOtherSum += data[i].No6MeterValueSum;
					}
					//冷粉
					ColdPMeterValueSum += data[i].ColdPMeterValueSum;
					//冷粉2
					ColdP2MeterValueSum += data[i].ColdP2MeterValueSum;
					//热粉
					HotPMeterValueSum += data[i].HotPMeterValueSum;
					//沥青
					AsphaltMeterSum += data[i].AsphaltMeterSum;
					//外掺剂1
					Admixture1Sum += data[i].Admixture1Sum;
					//外掺剂1
					Admixture2Sum += data[i].Admixture2Sum;
				}
					//总盘重量
					$("#TotalWeightSum").val((TotalWeightSum + TotalWeightOtherSum).toFixed(2));
					//1#仓
					$("#No1MeterValueSum").val((No1MeterValueSum + No1MeterValueOtherSum).toFixed(2));
					//2#仓
					$("#No2MeterValueSum").val((No2MeterValueSum + No2MeterValueOtherSum).toFixed(2));
					//3#仓
					$("#No3MeterValueSum").val((No3MeterValueSum + No3MeterValueOtherSum).toFixed(2));
					//4#仓
					$("#No4MeterValueSum").val((No4MeterValueSum + No4MeterValueOtherSum).toFixed(2));
					//5#仓
					$("#No5MeterValueSum").val((No5MeterValueSum + No5MeterValueOtherSum).toFixed(2));
					//6#仓
					$("#No6MeterValueSum").val((No6MeterValueSum + No6MeterValueOtherSum).toFixed(2));
					//冷粉
					$("#ColdPMeterValueSum").val(ColdPMeterValueSum.toFixed(2));
					//冷粉
					if(isNaN(ColdP2MeterValueSum)){
						$("#ColdP2MeterValueSum").val(0);
					}else{
						$("#ColdP2MeterValueSum").val(ColdP2MeterValueSum.toFixed(2));
					}
					//热粉
					$("#HotPMeterValueSum").val(HotPMeterValueSum.toFixed(2));
					//沥青
					$("#AsphaltMeterSum").val(AsphaltMeterSum.toFixed(2));
					//外掺剂1
					if(isNaN(Admixture1Sum)){
						$("#Admixture1Sum").val(0);
					}else{
						$("#Admixture1Sum").val(Admixture1Sum.toFixed(2));
					}
					//外掺剂2
					if(isNaN(Admixture2Sum)){
						$("#Admixture2Sum").val(0);
					}else{
						$("#Admixture2Sum").val(Admixture2Sum.toFixed(2));
					}
			}else{
				//清空input框
				$("#TotalWeightSum").val("");
				$("#No1MeterValueSum").val("");
				$("#No2MeterValueSum").val("");
				$("#No3MeterValueSum").val("");
				$("#No4MeterValueSum").val("");
				$("#No5MeterValueSum").val("");
				$("#No6MeterValueSum").val("");
				$("#ColdPMeterValueSum").val("");
				$("#ColdP2MeterValueSum").val("");
				$("#HotPMeterValueSum").val("");
				$("#AsphaltMeterSum").val("");
				$("#Admixture1Sum").val("");
				$("#Admixture2Sum").val("");
			}
		}
	});
}