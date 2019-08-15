var baseUrl = "";
var id = "";
var infoList;
$(function(){
	baseUrl = getContextPath();
	getTranSportList();
})

function update(BillNumber){
		$.fancybox({
			'href': 'TranSportListStoragemeasure_addEdit.html?BillNumber='+BillNumber,
			'width': 1680,
			'height': 650,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'TranSportListStoragemeasure.html';
			}
		});
}

function copy(BillNumber){
	$.fancybox({
		'href': 'TranSportListStoragemeasure_addEdit.html?BillNumber='+BillNumber+"&flag=0",
		'width': 1680,
		'height': 650,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'TranSportListStoragemeasure.html';
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
		var serialIDList;
		$.ajax({
	        type: "post",
	        url:  baseUrl+'/TranSportListStoragemeasure/getTranSportList.action',
	        async:false,
	        data:{"JbillNumber":BillNumber},
	        dataType: "json",
	        success: function (data) {
	        	serialIDList = data.data;
	        }
		});
		$.ajax({
	        type: "post",
	        url:  baseUrl+'/TranSportListStoragemeasure/getUploadfile.action',
	        async:false,
	        dataType: "json",
	        success: function (data) {
	        	UploadList = data.data;
	        }
		});
		$.ajax({
	        type: "post",
	        url:  baseUrl+'/TranSportListStoragemeasure/delTranSportList.action',
	        async:false,
	        data:{"BillNumber":BillNumber},
	        dataType: "json",
	        success: function (delTranSportData) {
	        	var isSuccess = true;
	        	var message = "";
	        	if(delTranSportData.code == 'success'){
	        		for (var i = 0; i < serialIDList.length; i++) {
		        		var serialID = serialIDList[i].serialID
		        		for (var w = 0; w < UploadList.length; w++) {
							if(UploadList[w].serialID == serialID){
								var filename = serialIDList[i].fileName
				        		$.ajax({
				        			type: "post",
				        			url: baseUrl+'/TranSportListStoragemeasure/delUploadfile.action',
				        			data:{"serialID":serialID},
				        			async:false,
				        			dataType: "json",
				        			success: function (data) {
				        				if(data != null){
				        					if(data.code == "success"){
				        						$.ajax({
				        							url : "../../fileUp/fileDelete.action",
				        							async : false,
				        							dataType:'json',
				        							data : {
				        								"filename" : filename
				        							},
				        							type : "post",
				        							success : function(isSuccess) {
				        								if(!isSuccess){
				        									isSuccess = false;
				        									message = "本地上传文件删除失败"
				        								}
				        							}
				        						});
				        					}else{
				        						 isSuccess = false;
				        						 message = "数据上传文件删除失败"
				        					}
				        				}
				        			}
				        		});
							}
						}
					}
	        	}else{
	        		 isSuccess = false;
					 message = "运输单删除失败"
	        	}
	        	if(isSuccess){
	        		setTimeout(function(){swal({
						title: '操作成功',
						type: "success",
						cancelButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						window.location.href="TranSportListStoragemeasure.html";
					}); },200);
	        	}else{
	        		setTimeout(function(){swal({
						title: '未知错误',
						type: "error",
						cancelButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						window.location.href="TranSportListStoragemeasure.html";
					}); },200);
	        	}
	        }
		});
	});
}

function getTranSportList(){
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
            url: baseUrl+'/TranSportListStoragemeasure/getTranSportList.action',
        },
        "deferRender": true,
        "columns": 
        	[{
        	   "data": "rowCount"
	        },{
		          "data": "operate",
		    }, {
	            "data": "billNumber"
	        }, {
	            "data": "plateNumber"
	        }, {
	            "data": "goOutTime"
	        }, {
	            "data": "SettleDate"
	        }, {
	            "data": "endAddress",
	            render:function(data,type,row,meta) {
	            	if(row.consigneeAddress!="" && row.consigneeAddress != undefined){
	            		var EndAddressHtml="";
	            		EndAddressHtml+=
							 "<table style=\"width:100%;\"> <tr>"+
							 "           <td>"+
							 "               "+row.endAddress+""+
							 "           </td>"+
							 "        </tr>"+
							 "        <tr>"+
							 "             <td>"+
							 "            		"+row.consigneeAddress+""+
							 "             </td>"+
							 "        </tr>"+
							 "</table>";
	            		return EndAddressHtml;
	            	}else{
	            		return row.endAddress;
	            	}
	            }
	        }, {
	            "data": "weigh"
	        }, {
	            "data": "collectWeigh"
	        }, {
	            "data": "deliveryMan"
	        }, {
	            "data": "type",
	            render:function(data,type,row,meta) {
	            	if(row.type==0){
	            		 html = "出库";	
	            	}else if(row.type==1){
	            		 html = "未入厂出库";	
	            	}else if(row.type==2){
	            		 html = "入库";	
	            	}
		  		    return html;
	            }
	        }, {
	            "data": "deliveryManPhone"
	        }, {
	            "data": "remarks"
	        }],
		"createdRow" : function(row, data, dataIndex) {
			if(data.remarks.length > 10){//只有超长，才有td点击事件
                $(row).children('td').eq(12).attr('onmouseover','javascript:changeShowRemarks(this);');
            }
            $(row).children('td').eq(12).attr('content',data.remarks);
		},
        "columnDefs": [{
            "targets": 12,
            "visible": true,
            "searchable": false,
            "type": "date",
            "render": function (data, type, full, meta) {
                if (full.remarks.length  > 10) {
                    return getPartialRemarksHtml(full.remarks);//显示部分信息
                } else {
                    return full.remarks;//显示原始全部信息            }
                }
            }
        },
        {
        	targets: [0,1,2,3], //要合并的列数（第1，2，3 列）
            createdCell: function (td, cellData, rowData, row, col) { //重要的操作可以合并列的代码
                var rowspan = rowData.merge;//这里主要是利用了模拟数据中的merge来控制
                if (rowspan > 1) {
                    $(td).attr('rowspan', rowspan)
                }
                if (rowspan == 0) {
                    $(td).remove();
                }
            }
        

        }]
    });
	 $('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

function search(){
	
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
             url: baseUrl+'/TranSportListStoragemeasure/getTranSportList.action',
             dataSrc: "data",
             data: function (d) {
           	var param = {};
                 param.billNumber = $("#freibillNumber").val();//按票据单号查询
                 param.type = $("#zhuangtai").val();//按票据单号查询
                 param.plateNumber = $("#freplateNumber").val();
                 return param;//自定义需要传递的参数。
             }
         },
        "deferRender": true,
        "columns": 
        	[{
        	   "data": "rowCount"
	        },{
		          "data": "operate",
		    }, {
	            "data": "billNumber"
	        }, {
	            "data": "plateNumber"
	        }, {
	            "data": "goOutTime"
	        }, {
	            "data": "endAddress",
	            render:function(data,type,row,meta) {
	            	if(row.consigneeAddress!="" && row.consigneeAddress != undefined){
	            		var EndAddressHtml="";
	            		EndAddressHtml+=
							 "<table style=\"width:100%;\"> <tr>"+
							 "           <td>"+
							 "               "+row.endAddress+""+
							 "           </td>"+
							 "        </tr>"+
/*							 "        <tr>"+
							 "            <td style=\"background-color:#044599;color:#fff;height: 25px;\">止运地</td>"+
							 "        </tr>"+*/
							 "        <tr>"+
							 "             <td>"+
							 "            		"+row.consigneeAddress+""+
							 "             </td>"+
							 "        </tr>"+
							 "</table>";
	            		return EndAddressHtml;
	            	}else{
	            		return row.endAddress;
	            	}
	            }
	        }, {
	            "data": "SettleDate"
	        }, {
	            "data": "weigh"
	        }, {
	            "data": "collectWeigh"
	        },/* {
	            "data": "startAddress",
	            render:function(data,type,row,meta) {
	            	if(row.DStartAddress!=""){
	            		var StartAddress1Html="";
	            		StartAddress1Html+=
							 "<table style=\"width:100%;\"> <tr>"+
							 "           <td>"+
							 "               "+row.startAddress+""+
							 "           </td>"+
							 "        </tr>"+
							 "        <tr>"+
							 "            <td style=\"background-color:#044599;color:#fff;height: 25px;\">起运地</td>"+
							 "        </tr>"+
							 "        <tr>"+
							 "             <td>"+
							 "            		"+row.DStartAddress+""+
							 "             </td>"+
							 "        </tr>"+
							 "</table>";
	            		return StartAddress1Html;
	            	}else{
	            		return row.startAddress;
	            	}
	            }
	        },*/ {
	            "data": "carOwner"
	        }, {
	            "data": "type",
	            render:function(data,type,row,meta) {
	            	if(row.type==0){
	            		 html = "出库";	
	            	}else if(row.type==1){
	            		 html = "未入厂出库";	
	            	}else if(row.type==2){
	            		 html = "入库";	
	            	}
		  		    return html;
	            }
	        }, {
	            "data": "carOwnerTelephone"
	        }, {
	            "data": "remarks"
	        }],
		"createdRow" : function(row, data, dataIndex) {
			if(data.remarks.length > 10){//只有超长，才有td点击事件
                $(row).children('td').eq(12).attr('onmouseover','javascript:changeShowRemarks(this);');
            }
            $(row).children('td').eq(12).attr('content',data.remarks);
		},
        "columnDefs": [{
            "targets": 12,
            "visible": true,
            "searchable": false,
            "type": "date",
            "render": function (data, type, full, meta) {
                if (full.remarks.length  > 10) {
                    return getPartialRemarksHtml(full.remarks);//显示部分信息
                } else {
                    return full.remarks;//显示原始全部信息            }
                }
            }
        },
        {
        	targets: [0,1,2,3], //要合并的列数（第1，2，3 列）
            createdCell: function (td, cellData, rowData, row, col) { //重要的操作可以合并列的代码
                var rowspan = rowData.merge;//这里主要是利用了模拟数据中的merge来控制
                if (rowspan > 1) {
                    $(td).attr('rowspan', rowspan)
                }
                if (rowspan == 0) {
                    $(td).remove();
                }
            }
        

        }]
    });
	 $('.dataTables_paginate').parent('div').addClass('col-sm-12');
}