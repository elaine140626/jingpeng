var startTime;
var endTime;
var org_ID;
$(function() {
	getList();
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
			url : "../../platformCementStableData/getPlatformCementStableData.action",
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
		"columns" : [ {
		            "data" : "serialNumber"
		        }, { "data" : "org_Name"},
		        {"data" : "str_equipment_Name"},
		        {"data" : "i_cons_Mix_ID"},
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
		        {"data" : "construction_Unit"},
		        {"data" : "proj_Pos"},
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
				}],
		"createdRow" : function(row, data, dataIndex) {
			// console.log(row);
			// console.log(data);
			// console.log(dataIndex);
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
	getTable1(row.i_Id);
	// 弹窗显示
	$('#loginModal').modal('show');
}

// 采集数据明细
function getTable1(i_Id) {
	$.ajax({
		type : "POST",
		url : "../../platformCementStableData/getCementStablePropDataAnalysis.action",
		data : {"id" : i_Id},
		dataType : "json",
		success : function(data) {
			var html = "";
			if (data.data.length != 0) {
				for (var i = 0; i < data.data.length; i++) {
					html += "<tr><td class='dise'>"+data.data[i].serialNumber+"</td>"
                    +"<td>"+data.data[i].material_Name+"</td>"
                    +"<td>"+data.data[i].cons_Prop_Weight+"</td>"
                    +"<td>"+data.data[i].collection_Weight+"</td>"
                    +"<td>"+data.data[i].actual_Proportion+"</td></tr>";  
				}
			}
			$("#cetb").html(html);
		}
	});
}
