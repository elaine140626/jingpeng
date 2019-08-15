var param = {};
var uesrId = "";
var uesrInfo = ""; //用户信息
$(function(){
	//uesrId = getUrlParam("userId");
	//uesrInfo = getUserInfo(uesrId);
	//查询
	search();
	//下拉列表
	getDatalistInfo();
})
//查询列表
function search(){
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var client = $("#client").val();
	var plateNumber = $("#plateNumber").val();
	var deliveryMan = $("#deliveryMan").val();
	var fleetName = $("#fleetName").val();
	var materielName = $("#materielName").val();
	var materielModel = $("#materielModel").val();
	param.startTime = startTime;
	param.endTime = endTime;
	param.client = client;
	param.plateNumber = plateNumber;
	param.deliveryMan = deliveryMan;
	param.fleetName = fleetName;
	param.materielName = materielName;
	param.materielModel = materielModel;
	getWeighingQueryOtherOut(param);
}
// datatable数据初始化
function getWeighingQueryOtherOut(param){
	var table1 = $('#table1').dataTable();
	table1.fnDestroy();
	$("#table1").DataTable({
		 "paging": true,
         "lengthChange": false,
         "pageLength": 14,
         "searching": false,
         "ordering": false,
         "info": true,
         "sInfo": true,
         "autoWidth": false,
         "iDisplayStart" : 0,
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
            url: "../../WeighingQueryOtherOut/getWeighingQueryOtherOut.action",
            dataSrc: "data",
            data: function (d) {
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": [
        	{"data": "id"},
        	{"data": "id",
        		render:function(data,type,row,meta) {
        			console.log(row);
        			var html = '';
        			html += '<a onclick="printingOtherOut('+ row.id + ',\'' + row.unit +'\')" style="text-decoration:none" href="javascript:;" title="打印">打印</a>&nbsp;';
        			html += "<a onclick='updateWeighingQueryOtherOut("+row.id+")' style='text-decoration:none' href='javascript:;' title='编辑'>编辑</a>&nbsp;";
        			html += "<a onclick='deleteWeighingQueryOtherOut("+row.id+")' style='text-decoration:none' href='javascript:;' title='删除'>删除</a>";
        			return html;
        		}},
        	{"data": "client"},
        	{"data": "plateNumber"},
        	{"data": "serial_ID"},
        	{"data": "materielName"},
        	{"data": "materielModel"},
        	{"data": "grossWeight"},
        	{"data": "tareWeight"},
        	{"data": "suttle"},
        	{"data": "measureTime"},
        	{"data": "unit"},
        	{"data": "deliveryMan"},
        	{"data": "deliveryManPhone"},
        	{"data": "consigneePhone"},
        	{"data": "fleetName"},
        	{"data": "isSelfMention",
        		render:function(data,type,row,meta) {
        			var html = '';
        			if(data == 0){
        				return '是'
        			}else{
        				return '否'
        			}
        		}}
        	],
        "createdRow": function( row, data, dataIndex ) {
        	console.log(data);
        },
        "order": [],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一      
            return nRow;
        },
        "columnDefs": []
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('.table1_info').parent('div').addClass('col-sm-12');
}

//查询条件下拉列表
function getDatalistInfo(){
	$.ajax({
		type : "post",
		url : "../../WeighingQueryOtherOut/getWeighingQueryOtherOut.action",
		data : {},
		async : false,
		dataType : "json",
		success : function(data) {
			data = data.data;
			if (data != null) {
				// 客户名称
				var client = "";
				for (var i = 0; i < data.length; i++) {
					if(client != data[i].client && data[i].client != null){
						$("#client").append("<option value='"+data[i].client+"'>"+data[i].client+"</option>");
					}
					client = data[i].client;
				}
				// 车牌号码
				var plateNumber = "";
				for (var i = 0; i < data.length; i++) {
					if(plateNumber != data[i].plateNumber && data[i].plateNumber != null){
						$("#plateNumber").append("<option value='"+data[i].plateNumber+"'>"+data[i].plateNumber+"</option>");
					}
					plateNumber = data[i].plateNumber;
				}
				// 承运人
				var deliveryMan = "";
				for (var i = 0; i < data.length; i++) {
					if(deliveryMan != data[i].deliveryMan && data[i].deliveryMan != null){
						$("#deliveryMan").append("<option value='"+data[i].deliveryMan+"'>"+data[i].deliveryMan+"</option>");
					}
					deliveryMan = data[i].deliveryMan;
				}
				// 车队名称
				var fleetName = "";
				for (var i = 0; i < data.length; i++) {
					if(fleetName != data[i].fleetName && data[i].fleetName != null){
						$("#fleetName").append("<option value='"+data[i].fleetName+"'>"+data[i].fleetName+"</option>");
					}
					fleetName = data[i].fleetName;
				}
				// 产品名称
				var materielName = "";
				for (var i = 0; i < data.length; i++) {
					if(materielName != data[i].materielName && data[i].materielName != null){
						$("#materielName").append("<option value='"+data[i].materielName+"'>"+data[i].materielName+"</option>");
					}
					materielName = data[i].materielName;
				}
				// 规格型号
				var materielModel = "";
				for (var i = 0; i < data.length; i++) {
					if(materielModel != data[i].materielModel && data[i].materielModel != null){
						$("#materielModel").append("<option value='"+data[i].materielModel+"'>"+data[i].materielModel+"</option>");
					}
					materielModel = data[i].materielModel;
				}
			}
		}
	});
}

//删除称重信息
function deleteWeighingQueryOtherOut(id){
		layer.confirm('确认要删除吗？', 
			function(index) {
				$.ajax({
					type: 'POST',
					url: '../../WeighingQueryOtherOut/deleteWeighingQueryOtherOut.action',
					data: {"id":id},
					dataType: 'json',
					success: function(data){
						if(data.code = "success"){
							layer.msg(data.message, {
								icon: 1,
								time: 1000
							},function(){
								location.reload();
							});
						}else{
							layer.msg(data.message, {
								icon: 2,
								time: 1000
							},function(){
								location.reload();
							});
						}
					}
				});
			});
}
//编辑称重信息
function updateWeighingQueryOtherOut(id){
	var index = layer.open({
		type: 2,
		title: '编辑称重信息',
		area: ['500px', '500px'],
		content: 'weighingQueryOtherOut_add.html?id='+id,
	});
	layer.full(index);
}

//打印出库单信息
function printingOtherOut(id,unit){
	if(unit.indexOf("瑞德") != -1){//瑞德小票
		var index = layer.open({
			type: 2,
			title: '出库称重单信息打印',
			content: 'weighingQuery_otherOutPrint.html?id='+id
		});
		layer.full(index);
	}else{//其它公司小票		
		var index = layer.open({
			type: 2,
			title: '出库称重单信息打印',
			content: 'weighingQuery_otherOutPrint2.html?id='+id
		});
		layer.full(index);
	}
}