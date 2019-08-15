//获取绝对路径
var baseUrl = "";
$(document).ready(function(){
	baseUrl = getContextPath();
	// 画面数据显示
	var param = {};
	//查询list数据
	getInfoList(param);
	//跳转到修改页面
	$("#addBtn").fancybox({
		'href': 'sales_Company_edit.html',
		'width': 800,
		'height': 480,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'sales_Company.html';
		}
	});
});
//查询按钮
function search(){
	// 画面数据显示
	var param = {};
	var companyName = $("#companyName").val();
	param.companyName = companyName;
	getInfoList(param);
}
//查询list数据
function getInfoList(param){
	//获取列表 刷新
	var table = $('#listTable').dataTable();
	table.fnDestroy();
	$('#listTable').DataTable({
		  "paging": true,
	      "lengthChange": false,
	      "pageLength": 12,
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
            url: baseUrl + "/salesCompany/getSalesCompanyList.action",
            dataSrc: "data",
            data: function (d) {
            	return param;
            }
        },
        "deferRender" : true,
	      "columns": [{
	          "data": "serialnumber"
	      }, {
	          "data": "id",
	          render: function(data, type, row) {
	  			var html = '';
	  		    html =["<a style=\"margin: 0 15px;\" onclick=update("+row.id+")><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>",
	  		    	"<a style=\"margin: 0 15px;\" onclick=del("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>"].join("");
	  		    return html;
	       }
	      }, { 
	    	  "data": "companyNumber"
	      }, {
	          "data": "companyName"
	      }, {
	          "data": "companyAddress"
	      }, {
	          "data": "contacts"
	      }, {
	          "data": "telephone"
	      }, {
	          "data": "remarks"
	      }],
	      "createdRow" : function(row, data, dataIndex) {
	    	  console.log(data);
	          $(row).children('td').eq(1).attr('style', 'text-align: center;')
	          if(data.remarks.length > 10){//只有超长，才有td点击事件
	             $(row).children('td').eq(7).attr('onmouseover','javascript:changeShowRemarks(this);');
	          }
	          $(row).children('td').eq(7).attr('content',data.remarks);
	          //销售公司编号
	          if(data.companyNumber.length > 10){//只有超长，才有td点击事件
		             $(row).children('td').eq(2).attr('onmouseover','javascript:changeShowRemarks(this);');
		          }
		          $(row).children('td').eq(2).attr('content',data.companyNumber);
	          //销售公司名称
	          if(data.companyName.length > 10){//只有超长，才有td点击事件
		             $(row).children('td').eq(3).attr('onmouseover','javascript:changeShowRemarks(this);');
		          }
		          $(row).children('td').eq(3).attr('content',data.companyName);
	          //地址
	          if(data.companyAddress.length > 10){//只有超长，才有td点击事件
		             $(row).children('td').eq(4).attr('onmouseover','javascript:changeShowRemarks(this);');
		          }
		          $(row).children('td').eq(4).attr('content',data.companyAddress);
	          //联系人
	          if(data.contacts.length > 10){//只有超长，才有td点击事件
		             $(row).children('td').eq(5).attr('onmouseover','javascript:changeShowRemarks(this);');
		          }
		          $(row).children('td').eq(5).attr('content',data.contacts);
	      },
	      "columnDefs" : [ {
	    	  "targets": 7,
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
	      },{
	    	  "targets": 2,
	            "visible": true,
	            "searchable": false,
	            "type": "date",
	            "render": function (data, type, full, meta) {
	                if (full.companyNumber.length  > 10) {
	                    return getPartialRemarksHtml(full.companyNumber);//显示部分信息
	                } else {
	                    return full.companyNumber;//显示原始全部信息            }
	                }
	            }
	      },{
	    	  "targets": 3,
	            "visible": true,
	            "searchable": false,
	            "type": "date",
	            "render": function (data, type, full, meta) {
	                if (full.companyName.length  > 10) {
	                    return getPartialRemarksHtml(full.companyName);//显示部分信息
	                } else {
	                    return full.companyName;//显示原始全部信息            }
	                }
	            }
	      },{
	    	  "targets": 4,
	            "visible": true,
	            "searchable": false,
	            "type": "date",
	            "render": function (data, type, full, meta) {
	                if (full.companyAddress.length  > 10) {
	                    return getPartialRemarksHtml(full.companyAddress);//显示部分信息
	                } else {
	                    return full.companyAddress;//显示原始全部信息            }
	                }
	            }
	      },{
	    	  "targets": 5,
	            "visible": true,
	            "searchable": false,
	            "type": "date",
	            "render": function (data, type, full, meta) {
	                if (full.contacts.length  > 10) {
	                    return getPartialRemarksHtml(full.contacts);//显示部分信息
	                } else {
	                    return full.contacts;//显示原始全部信息            }
	                }
	            }
	      }]
	});
		$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
		$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}
//删除
function del(id){
	swal({
		title: "确定操作吗？",
		text:"删除后将无法恢复，请谨慎操作！",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	},
	function(){
		$.ajax({
			type: "post",
			url: "../../salesCompany/getAllSalesCompany.action",
			data: {"salesCompanyId":id},
			dataType: "json",
			success: function (data) {
				if(data.length>0){
					setTimeout(function(){swal({
	    				title: '该销售公司已被调用,不可删除!',
	    				type: "error",
	    				cancelButtonText: '确定',
	    				cancelButtonFont: '微软雅黑',
	    			},
	    			function(){
	    				window.location.href="sales_Company.html";
	    			}); },200);
				}else{
					$.ajax({
						type: "post",
						url: "../../salesCompany/delSalesCompany.action",
						data: {"id":id},
						dataType: "json",
						success: function (data) {
							if(data.code == 'success'){
								var param = {};
								getInfoList(param);	
								window.location.href = 'sales_Company.html';
							}else{
								swal({
									title: "错误提示",
									text: data.message,
									type: data.code,
									confirmButtonText: '确定',
									cancelButtonFont: '微软雅黑',
								});
							}	
						}
					});
				}
			}
		});
	});
}
//修改
function update(id){
	$.fancybox({
		'href': 'sales_Company_edit.html?id='+id,
		'width': 800,
		'height': 480,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'sales_Company.html';
		}
	});
}