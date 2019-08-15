var infoList;
var params=[];
$(document).ready(function() {
	// 按车辆查询
	plateNumberList();
	$("#ContractState").fSelect();
	// 画面数据显示
	var param = {};
	getInfoList(param);
	// 左侧菜单展开收起
	$('.shouqi').click(function() {
		$('#sider').removeClass('show');
		$('#sider').addClass('hiden');
		$('#box_top').hide();
		$('#tree').hide();
		$('#main').removeClass('mshow');
		$('#main').addClass('mhide');
		$('.zhankai').show();
		$('.shouqi').hide();
	})
	$('.zhankai').click(function() {
		$('#sider').removeClass('hiden');
		$('#sider').addClass('show');
		$('#tree').show();
		$('#main').removeClass('mhide');
		$('#main').addClass('mshow');
		$('.zhankai').hide();
		$('.shouqi').show();
	})
});
//车辆检索框赋值
function plateNumberList(){
	$.ajax({
		type: "post",
		url: "../../price/getPlateNumberList.action",
		data: {},
		dataType: "json",
		success: function (data) {
			if(data != null && data.length > 0){
				// 先清空
				$("#plateNumbers").html("");
				for(var i=0; i< data.length; i++){
					$("#plateNumbers").append("<option value="+data[i]+">"+data[i]+"</option>");
				}
			}
		}
	});
}
// 画面list数据显示
function getInfoList(param){
	infoList = [];
	var obj = $("#ContractState").val();
	if(obj==null){
		var ContractState = '';
	}else{
		var ContractState = obj.join(',');
	}
	//获取列表 刷新
	var table = $('#listTable').dataTable();
	table.fnDestroy();
	
	$("#listTable").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 14,
        "searching": false,
        "ordering": false,
        "info": true,
        "sInfo": true,
        "autoWidth": false,
        "iDisplayStart" : 0,
        scrollY:        "100%",
        scrollX:        true,
        scrollCollapse: true,
        fixedColumns:   {
            leftColumns: 2
        },
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
			type : "post",
			url : "../../price/getAllOfferrecord.action",
			dataSrc : "data",
			data : function(d) {
                param.OutType = ContractState;
                return param;//自定义需要传递的参数。
			}
		},
        "deferRender": true,
        "columns": [ 
    	{
            "data": "id"
        },{
            "data": "plateNumber"
        },{
            "data": "factoryTime"
        },{
            "data": "transports"
        },{
        	"data": "productID",
            render: function(data, type, row) {
             	if(row.materielName != null){
             		if (row.OutType == 4 || row.OutType == 5) {
             			data = "由 " + row.materielName2 + "--" + row.materielModel2 + " 兑换为 " + row.materielName + "--" + row.materielModel 
             				+ "</br>兑换比例为" + row.proportion + "%";
             			return data;
             		} else {
             			data = row.materielName + "--" + row.materielModel;
             			return data;	
             		}
             	}else{
             		return "";
             	}
             }
         },{
             "data": "client"
         }, {
             "data": "customerAlias"
         },{
        	 "data": "contractNumber"
        },{
            "data": "orderNumber"
        },{
        	"data": "serialId"
        },{
            "data": "OutType",
            render: function(data, type, row) {
            	console.log(data);
            		var html = "";
            		if(row.OutType == 0){
            			html = "正常出库单";
            			return html;
            		}
            		if(row.OutType == 1){
            			html = "调拨出库单"
            			return html;
            		}
            		if(row.OutType == 2){
            			html = "退货出库单";
            			return html;
            		}
            		if(row.OutType == 3){
            			html = "空发出库单";
            			return html;
            		}
            		if(row.OutType == 4){
            			html = "兑换出库单";
            			return html;
            		}
            		if(row.OutType == 5){
            			html = "兑换时调拨单";
            			return html;
            		}
            		if(row.OutType == 98){
            			html = "未称重出库单";
            			return html;
            		}
	            }
	    },{
	    	"data": "name"
	    },{
            "data": "deliveryMan"
        },{
            "data": "suttle",
            render:function(data,type,row){
            	var count;
            	if(data != null && data != 0 && data != ""){
            		count = data;
            		return count;
            	}else{
            		return "";
            	}
            }
        },{
            "data": "salePrice",
            render:function(data,type,row){
            	if(data != null && data != ""){
            		return data.toFixed(2);
            	}else{
            		return "";
            	}
            }
        },{
            "data": "updatePrice"
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html(iDisplayIndex +1);//设置序号位于第一列，并顺次加一               	
        },
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

function search(){
	var params = {};
	var str = [];
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var plateNumbers = $("#plateNumber").val();
	params.startTime = startTime;
	params.endTime = endTime;
	params.plateNumber = plateNumbers;
	getInfoList(params);
}