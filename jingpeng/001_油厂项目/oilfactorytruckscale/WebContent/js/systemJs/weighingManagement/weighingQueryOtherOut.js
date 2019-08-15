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
        	{"data": "grossWeight",
        		render:function(data,type,row,meta) {
        			if(data != 0){
        				return data
        			}else{
        				return ''
        			}
        		}},
        	{"data": "tareWeight",
        		render:function(data,type,row,meta) {
        			if(data != 0){
        				return data
        			}else{
        				return ''
        			}
        		}},
        	{"data": "suttle",
        		render:function(data,type,row,meta) {
        			if(data != 0){
        				return data
        			}else{
        				return ''
        			}
        		}},
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
		url : "../../WeighingQueryOtherOut/getDataList.action",
		data : {},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data != null) {
				var ClientList = data.ClientList;
				var PlateNumberList = data.PlateNumberList;
				var DeliveryManList = data.DeliveryManList;
				var FleetNameList = data.FleetNameList;
				var MaterielNameList = data.MaterielNameList;
				var MaterielModelList = data.MaterielModelList;
				// 客户名称
				if (ClientList != null && ClientList.length > 0) {
					for (var i = 0; i < ClientList.length; i++) {
						if(  ClientList[i].client != null &&  ClientList[i].client != '')
						{$("#client").append("<option>" + ClientList[i].client + "</option>");}
					}
				}
				// 车牌号码
				if (PlateNumberList != null && PlateNumberList.length > 0) {
					for (var i = 0; i < PlateNumberList.length; i++) {
						if(  PlateNumberList[i].plateNumber != null &&  PlateNumberList[i].plateNumber != '')
						{$("#plateNumber").append("<option>" + PlateNumberList[i].plateNumber + "</option>");}
					}
				}
				// 承运人
				if (DeliveryManList != null && DeliveryManList.length > 0) {
					for (var i = 0; i < DeliveryManList.length; i++) {
						if(  DeliveryManList[i].deliveryMan != null &&  DeliveryManList[i].deliveryMan != '')
						{$("#deliveryMan").append("<option>" + DeliveryManList[i].deliveryMan + "</option>");}
					}
				}
				// 车队名称
				if (FleetNameList != null && FleetNameList.length > 0) {
					for (var i = 0; i < FleetNameList.length; i++) {
						if(  FleetNameList[i].fleetName != null &&  FleetNameList[i].fleetName != '')
						{$("#fleetName").append("<option>" + FleetNameList[i].fleetName + "</option>");}
					}
				}
				// 产品名称
				if (MaterielNameList != null && MaterielNameList.length > 0) {
					for (var i = 0; i < MaterielNameList.length; i++) {
						if(  MaterielNameList[i].materielName  != null &&  MaterielNameList[i].materielName  != '')
						{$("#materielName").append("<option>" + MaterielNameList[i].materielName + "</option>");}
					}
				}
				// 规格型号
				if (MaterielModelList != null && MaterielModelList.length > 0) {
					for (var i = 0; i < MaterielModelList.length; i++) {
						if(  MaterielModelList[i].materielModel != null &&  MaterielModelList[i].materielModel != '')
						{$("#materielModel").append("<option>" + MaterielModelList[i].materielModel + "</option>");}
					}
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