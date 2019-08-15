var startTime;
var endTime;
var org_ID;
var Analysis_Result;
$(function() { 
	startTime = getUrlParam("startTime");
	endTime = getUrlParam("endTime");
	org_ID = getUrlParam("org_ID");
	Analysis_Result = getUrlParam("Analysis_Result");
	getList();
});

// 生产统计dataTable
function getList(){
	var table = $('#list').dataTable();
	table.fnDestroy();
	$('#list').DataTable({
        "paging" : true,
        "lengthChange" : false,
        "bLengthChange": false,
        "pageLength" : 7,
        "searching" : false,
        "ordering" : false,
        "info" : true,
        "sInfo" : true,
        "autoWidth" : false,
        "language": {
            "lengthMenu": "每页 _MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
            "infoEmpty": "无记录",
            "sSearch": "在结果中查找：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "oPaginate": {
                "sFirst": "第一页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "最后一页"
            },
        },
        "ajax" : {
            type: "post",
            url: "../../PlatformCementData/getPlatformCementData.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.startTime = startTime +" "+"00:"+"00:"+"00";
                param.endTime = endTime +" "+"23:"+"59:"+"59";
                param.org_ID = org_ID;
                param.Analysis_Result = Analysis_Result;
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : /*[
			{"data" : "serialNumber"},
			{"data" : "org_Name"},
			{"data" : "str_equipment_Name"}, 
			{"data" : "i_cons_Mix_ID"}, 
			{"data" : "construction_Unit"}, 
			{"data" : "proj_Pos"}, 
			{
				"data" : "str_collect_Date",
				render : function(data, type, row, meta) {
					row = JSON.stringify(row);
					var html = '';
					html = "<span><a onclick='detailed("+ row + ")'>" + data + "</a></span>";
					return html;
				}
			}, 
			{"data" : "i_mix_Time"},
			{"data" : "d_weight_Cement1"}, 
			{"data" : "d_weight_Cement2"}, 
			{"data" : "d_weight_Cement3"}, 
			{"data" : "d_weight_Cement4"}, 
			{"data" : "d_weight_Aggregate1"}, 
			{"data" : "d_weight_Aggregate2"}, 
			{"data" : "d_weight_Aggregate3"}, 
			{"data" : "d_weight_Aggregate4"}, 
			{"data" : "d_weight_Aggregate5"}, 
			{"data" : "d_weight_Aggregate6"}, 
			{"data" : "d_weight_Water"}, 
			{"data" : "d_weight_Admixture1"}, 
			{"data" : "d_weight_Admixture2"}, 
			{"data" : "d_total_Weight"}, 
	
			{
				"data" : "analysis_Result",
				render : function(data, type, row, meta) {
					if (data == ''|| data == null) {
						return '';
					} else if (data == '1'){
						return "合格";
					} else {
						return '<span style="color: red;">不合格</span>';
					}
				}
			}],*/
        	[{
	            "data": "serialNumber"
	        }, {
	            "data": "equipment_Name"
	        }, {
	            "data": "collect_Date",
	            render : function(data, type, row, meta) {
					row = JSON.stringify(row);
					var html = '';
					html = "<span><a onclick='detailed("+ row + ")'>" + data + "</a></span>";
					return html;
				}
	        }, {
	            "data": "grade"
	        }, {
	            "data": "disk_Number"
	        }, {
	            "data": "mix_Time"
	        }, {
	            "data": "weight_Cement1"
	        }, {
	            "data": "weight_Cement2"
	        }, {
	            "data": "weight_Cement3"
	        }, {
	            "data": "weight_Cement4"
	        }, {
	            "data": "weight_Aggregate1"
	        }, {
	            "data": "weight_Aggregate2"
	        }, {
	            "data": "weight_Aggregate3"
	        }, {
	            "data": "weight_Aggregate4"
	        }, {
	            "data": "weight_Aggregate5"
	        }, {
	            "data": "weight_Aggregate6"
	        }, {
	            "data": "weight_Water"
	        }, {
	            "data": "weight_Admixture1"
	        }, {
	            "data": "weight_Admixture2"
	        }, {
	            "data": "total_Weight"
	        }, {
	            "data": "mixing_Volume"
	        }, {
	            "data": "proj_Pos"
	        }, {
	            "data": "watering_Site"
	        }, {
	            "data": "analysis_Results",
	            render:function(data,type,row,meta) {
	            	var str =''
	            	var result=data.split(",");
	            	for(var i=0;i<result.length;i++){
	            		if(result[i] == 1){
	            			str += '正常   '
	            		}
	            		if(result[i] == 2){
	            			str += '拌合时间不合格   '
	            		}
	            		if(result[i] == 3){
	            			str += '水泥仓数据异常   '
	            		}
	            		if(result[i] == 4){
	            			str += '骨料仓数据异常   '
	            		}
	            		if(result[i] == 5){
	            			str += '外掺剂仓数据异常   '
	            		}
	            		if(result[i] == 6){
	            			str += '水仓数据异常   '
	            		}
	            	}
		            	var html = '';
			  		    html += '<span style="color: red;">'+str+'</span>';
			  		    return html;
	        }
	        }],
        "createdRow" : function(row, data, dataIndex) {
   //     	console.log(row);
   //    	console.log(data);
//        	console.log(dataIndex);
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#list_info').css('text-align','center');
}

//popup明细
function detailed(row) {
	// 采集数据明细
	getTable1(row.prodPlan_No,row.org_Id);
	// 弹窗显示
	$('#loginModal').modal('show');
}

//采集数据明细
function getTable1(prodPlan_No,orgId) {
	$.ajax({
		type : "POST",
		url : "../../PlatformCementData/getCementPropDataAnalysis.action",
		data : {"prodPlan_No" : prodPlan_No,"orgId":orgId},
		dataType : "json",
		success : function(data) {
			/*var html = "";
			if (data.data.length != 0) {
				for (var i = 0; i < data.data.length; i++) {
					html += "<tr><td class='dise'>" + data.data[i].serialNumber+"</td>" 
						    + "<td>" + data.data[i].material_Code + "</td>"
							+ "<td>" + data.data[i].cons_Prop_Weight + "</td>"
							+ "<td>" + data.data[i].collection_Weight + "</td>"  
							+ "<td>" + data.data[i].actual_Proportion+ "</td></tr>";
				}
			}
			$("#cetb").html(html);*/
			console.log(data.data);
        	var datas = data.data[0];
        	 var htmltb="";
        	 		if(datas.set_Cement1 != '' || datas.weight_Cement1  != ''){
        	 		if(datas.material_Cement_1 == null){datas.material_Cement_1 = ''}
        	 		if(datas.material_Cement_2 == null){datas.material_Cement_2 = ''}
        	 		if(datas.material_Cement_3 == null){datas.material_Cement_3 = ''}
        	 		if(datas.material_Cement_4 == null){datas.material_Cement_4 = ''}
        	 		if(datas.material_Aggregate_1 == null){datas.material_Aggregate_1 = ''}
        	 		if(datas.material_Aggregate_2 == null){datas.material_Aggregate_2 = ''}
        	 		if(datas.material_Aggregate_3 == null){datas.material_Aggregate_3 = ''}
        	 		if(datas.material_Aggregate_4 == null){datas.material_Aggregate_4 = ''}
        	 		if(datas.material_Aggregate_5 == null){datas.material_Aggregate_5 = ''}
        	 		if(datas.material_Aggregate_6 == null){datas.material_Aggregate_6 = ''}
        	 		if(datas.material_Admixture_1 == null){datas.material_Admixture_1 = ''}
        	 		if(datas.material_Admixture_2 == null){datas.material_Admixture_2 = ''}
        	htmltb +="<tr><td>1</td>"
        			 +"<td>水泥一仓</td>"
        			 +"<td>"+datas.material_Cement_1+"</td>" 
        			 +"<td>"+datas.set_Cement1+"</td>"
        			 +"<td>"+datas.weight_Cement1+"</td>"
        			 +"<td>"+evenRound(datas.deviation_Cement_1,1)+"</td></tr>"
        	 		 }
        	 		 if(datas.set_Cement2 != '' || datas.weight_Cement2  != ''){
			htmltb +="<tr><td>2</td>"
        			 +"<td>水泥二仓</td>"
				 	 +"<td>"+datas.material_Cement_2+"</td>"
				 	 +"<td>"+datas.set_Cement2+"</td>"
				 	 +"<td>"+datas.weight_Cement2+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Cement_2,1)+"</td></tr>"
        	 		 }
				 	 if(datas.set_Cement3 != '' || datas.weight_Cement3  != ''){
			htmltb +="<tr><td>3</td>"
        			 +"<td>水泥三仓</td>"
				 	 +"<td>"+datas.material_Cement_3+"</td>"
				 	 +"<td>"+datas.set_Cement3+"</td>"
				 	 +"<td>"+datas.weight_Cement3+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Cement_3,1)+"</td></tr>"
				 	 }
				 	 if(datas.set_Cement4 != '' || datas.weight_Cement4  != ''){
			htmltb +="<tr><td>4</td>"
        			 +"<td>水泥四仓</td>"
				 	 +"<td>"+datas.material_Cement_4+"</td>"
				 	 +"<td>"+datas.set_Cement4+"</td>"
				 	 +"<td>"+datas.weight_Cement4+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Cement_4,1)+"</td></tr>"
				 	 }
				 	 if(datas.set_Aggregate1 != '' || datas.weight_Aggregate1  != ''){
			htmltb +="<tr><td>5</td>"
        			 +"<td>骨料一仓</td>"
				 	 +"<td>"+datas.material_Aggregate_1+"</td>"
				 	 +"<td>"+datas.set_Aggregate1+"</td>"
				 	 +"<td>"+datas.weight_Aggregate1+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Aggregate_1,1)+"</td></tr>"
				 	 }
				 	 if(datas.set_Aggregate2 != '' || datas.weight_Aggregate2  != ''){
			htmltb +="<tr><td>6</td>"
        			 +"<td>骨料二仓</td>"
				 	 +"<td>"+datas.material_Aggregate_2+"</td>"
				 	 +"<td>"+datas.set_Aggregate2+"</td>"
				 	 +"<td>"+datas.weight_Aggregate2+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Aggregate_2,1)+"</td></tr>"
				 	 }
				 	 if(datas.set_Aggregate3 != '' || datas.weight_Aggregate3  != ''){
			htmltb +="<tr><td>7</td>"
        			 +"<td>骨料三仓</td>"
				 	 +"<td>"+datas.material_Aggregate_3+"</td>"
				 	 +"<td>"+datas.set_Aggregate3+"</td>"
				 	 +"<td>"+datas.weight_Aggregate3+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Aggregate_3,1)+"</td></tr>"
				 	 }
				 	 if(datas.set_Aggregate4 != '' || datas.weight_Aggregate4  != ''){
			htmltb +="<tr><td>8</td>"
        			 +"<td>骨料四仓</td>"
				 	 +"<td>"+datas.material_Aggregate_4+"</td>"
				 	 +"<td>"+datas.set_Aggregate4+"</td>"
				 	 +"<td>"+datas.weight_Aggregate4+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Aggregate_4,1)+"</td></tr>"
				 	 }
				 	 if(datas.set_Aggregate5 != '' || datas.weight_Aggregate5  != ''){
			htmltb +="<tr><td>9</td>"
        			 +"<td>骨料五仓</td>"
				 	 +"<td>"+datas.material_Aggregate_5+"</td>"
				 	 +"<td>"+datas.set_Aggregate5+"</td>"
				 	 +"<td>"+datas.weight_Aggregate5+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Aggregate_5,1)+"</td></tr>"
				 	 }
				 	 if(datas.set_Aggregate6 != '' || datas.weight_Aggregate6  != ''){
			htmltb +="<tr><td>10</td>"
        			 +"<td>骨料六仓</td>"
				 	 +"<td>"+datas.material_Aggregate_6+"</td>"
				 	 +"<td>"+datas.set_Aggregate6+"</td>"
				 	 +"<td>"+datas.weight_Aggregate6+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Aggregate_6,1)+"</td></tr>"
				 	 }
				 	 if(datas.set_Water != '' || datas.weight_Water  != ''){
			htmltb +="<tr><td>11</td>"
					 +"<td>水仓</td>"
				 	 +"<td>水</td>"
				 	 +"<td>"+datas.set_Water+"</td>"
				 	 +"<td>"+datas.weight_Water+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Water,1)+"</td></tr>"
				 	 }
				 	 if(datas.set_Admixture1 != '' || datas.weight_Admixture1  != ''){
			htmltb +="<tr><td>12</td>"
					 +"<td>外掺剂一仓</td>"
				 	 +"<td>"+datas.material_Admixture_1+"</td>"
				 	 +"<td>"+datas.set_Admixture1+"</td>"
				 	 +"<td>"+datas.weight_Admixture1+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Admixture_1,1)+"</td></tr>"
				 	 }
				 	 if(datas.set_Admixture2 != '' || datas.weight_Admixture2  != ''){
			htmltb +="<tr><td>13</td>"
					 +"<td>外掺剂二仓</td>"
				 	 +"<td>"+datas.material_Admixture_2+"</td>"
				 	 +"<td>"+datas.set_Admixture2+"</td>"
				 	 +"<td>"+datas.weight_Admixture2+"</td>"
				 	 +"<td>"+evenRound(datas.deviation_Admixture_2,1)+"</td></tr>";
				 	 }
        	 $("#cetb").html(htmltb);
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