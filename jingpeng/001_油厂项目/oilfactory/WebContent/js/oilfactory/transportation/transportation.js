var baseUrl = "";
var id = "";
var infoList;
var param = {};
$(function(){
	baseUrl = getContextPath();
	getTranSportList(param);
})

function update(id){
		$.fancybox({
			'href': 'transportation_addEdit.html?id='+id,
			'width': 900,
			'height': 720,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'transportation.html';
			}
		});
}

function select(id){
	$.fancybox({
		'href': 'transportation_addEdit.html?flag=3&id='+id,
		'width': 900,
		'height': 720,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'transportation.html';
		}
	});
}

function del(BillNumber){
	swal({
		title: "确定要删除吗？",
		text:"删除后将无法恢复，请谨慎操作！",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	},
	function(){
	});
}

function getTranSportList(param){
	infoList = [];
	var table1 = $('#tranSportList').dataTable();
	table1.fnDestroy();
	$("#tranSportList").DataTable({
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
//         fixedColumns:   {
//             leftColumns: 5
//         },
         "language": {
             "lengthMenu": "每页 _MENU_ 条记录",
             "zeroRecords": "没有找到记录",
             "info": "第 _PAGE_ 页,共 _PAGES_ 页",
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
            url: baseUrl+'/TranSportList/getExOrStPlateNumber.action',
            dataSrc : "data",
			data : param
        },
        "deferRender": true,
        "columns": 
        	[{
        	   "data": "rowCount"
	        }, {
		         "data": "operate"
		    }, {
	            "data": "billNumber"
	        }, 
	        {
	            "data": "plateNumber"
	        }, 
	        {
	            "data": "factoryTime",
	            render:function(data,type,row,meta) {
	            	if(data == undefined){
	            		return "";
	            	}else{
	            		return data;	
	            	}
            }
	        },
	        {
	            "data": "materielName",
	            render:function(data,type,row,meta) {
	            		return row.materielName + "--" + row.materielModel;
	            }
	        },
	        {
	            "data": "supplierName",
	            render:function(data,type,row,meta) {
	            	if(data){
	            		return data;
	            	}else{
	            		return "辽宁瑞德";	
	            	}
            }
	        },
	        {
	            "data": "endAddress",
	            render:function(data,type,row,meta) {
	            	if(data == undefined){
	            		return "";
	            	}else{
	            		return data;	
	            	}
            }
	        },
	        {
	            "data": "settleDate",
	            render:function(data,type,row,meta) {
	            	if(data == '0000-00-00 00:00:00' || data == undefined){
	            		return '';
	            	}else{
	            		return data;	
	            	}
	            }
	            	
	        },
	        {
	            "data": "freightPrice",
	            render:function(data,type,row,meta) {
	            	if(data == undefined){
	            		return "";
	            	}else{
	            		return data;	
	            	}
            }
	        },
	        {
	            "data": "serialId"
	        },
	        {
	            "data": "weighEX",
	            render:function(data,type,row,meta) {
	            	if(row.outType == 0){
	            		return data;
	            	}else{
	            		return row.allotWeight ;	
	            	}
	            }
	        },
	        {
	            "data": "collectWeigh",
	            render:function(data,type,row,meta) {
	            	if(data == undefined){
	            		return "";
	            	}else{
	            		return data;	
	            	}
	            }
	        },
	        {
	            "data": "riseLoss",
	            render:function(data,type,row,meta) {
	            	if(data == null ){
	            		return "";
	            	}else{
	            		return data;
	            	}
	            }
	        },
	        {
	            "data": "freightMoney",
	            render:function(data,type,row,meta) {
	            	if(data == undefined){
	            		return "";
	            	}else{
	            		return data;	
	            	}
            }
	        },
	        {
	            "data": "escortMoney",
	            render:function(data,type,row,meta) {
	            	if(data == undefined){
	            		return "";
	            	}else{
	            		return data;	
	            	}
            }
	        },
	        {
	            "data": "sumMoney",
	            render:function(data,type,row,meta) {
	            	if(row.freightMoney == undefined || row.escortMoney == undefined  ){
	            		return "";
	            	}else{
	            		return row.freightMoney + row.escortMoney;
	            	}
	            }
	        },
	        {
	            "data": "carOwner",
	            render:function(data,type,row,meta) {
	            	if(data == undefined){
	            		return "";
	            	}else{
	            		return data;	
	            	}
            }
	        }, {
	            "data": "type",
	            render:function(data,type,row,meta) {
	            	var html = '';
		  		    switch (row.type) {
					case 0:
						html = "正常出库单"
						break;
					case 1:
						html = "调拨出库单"
						break;
					case 3:
						html = "空发出库单"
						break;
					case 4:
						html = "兑换出库单"
						break;
					case 99:
						html = "已被调拨出库单"
						break;
					default:
						break;
					}
		  		    return html;
	            }
	        }, {
	            "data": "carOwnerTelephone",
	            render:function(data,type,row,meta) {
	            	if(data == undefined){
	            		return "";
	            	}else{
	            		return data;	
	            	}
            }
	        }],
    });
	 $('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

function search(){
	 param.mbillNumber = $("#freibillNumber").val();//按票据单号查询
     param.mplateNumber = $("#freplateNumber").val();
	 getTranSportList(param);
}