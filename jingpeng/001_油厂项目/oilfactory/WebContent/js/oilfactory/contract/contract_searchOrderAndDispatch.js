// 合同id
var id;
//调度出库单信息
var dataEXList;

$(function(){
	// 获取前页面传过来的id
	id = getUrlParam("id");
	getOrderAndDispatchInfo(id);
});

//查询合同下的销售订单信息，调度出库信息(ygt)
function getOrderAndDispatchInfo(id){
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getOrderAndDispatchInfo.action",
		async:false,
		data : {"contractId":id},
		dataType : "json",
		success : function(data) {
			if(data != null && data.length > 0){
				//订单编号
				$("#OrderNumber").empty();
				$("#OrderNumber").append("<option value=''>请选择</option>");
				for (var i = 0; i < data.length; i++) {
					$("#OrderNumber").append("<option value='"+data[i].Id+"'>"+data[i].OrderNumber+"</option>");
				}
			}
		}
	});
}

//销售订单的改变事件
function  changeOrderNumber(value){
	if(value != ""){
		//销售订单
		$.ajax({
			type : "POST",
			url : "../../salesContractManagement/getOrderAndDispatchInfo.action",
			async:false,
			data : {"salesOrderId":value},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					for (var i = 0; i < data.length; i++) {
						//合同编号
						$("#ContractId").val(data[i].ContractNumber);
						//客户编号
						$("#CustomerId").val(data[i].CustomerCode);
						//客户别称
						$("#CustomerAlias").val(data[i].CustomerAlias);
						//订单状态
						$("#OrderState").val(data[i].OrderState);
						//送货地址
						$("#Address").val(data[i].Address);
						//联系人
						$("#Contacts").val(data[i].Contacts);
						//联系方式
						$("#Telephone").val(data[i].Telephone);
						//销售公司名称
						$("#SalesCompanyId").val(data[i].CompanyName);
						//销售员
						$("#UserInfoId").val(data[i].Name);
						//备注
						$("#Remarks").val(data[i].Remarks);
					}
				}
			}
		});
		//销售订单明细
		$.ajax({
			type : "POST",
			url : "../../salesContractManagement/getOrdersDetailed.action",
			async:false,
			data : {"salesOrderId":value},
			dataType : "json",
			success : function(data) {
				//清空销售订单明细
				$("#tableList1 tbody").find("tr").remove();
				if(data != null && data.length > 0){
					dataEXList = data;
					//销售订单明细
					addTable();
				}
			}
		});
		//调度出库单
		getExportMeasureInfo(value);
	}else{
		//清空销售订单
		$("input[type='text']").val("");
		//清空销售订单明细
		$("#tableList1 tbody").find("tr").remove();
		//清空调度出库单
		$('#listTable').dataTable().fnDestroy();
		$('#listTable tbody').find("tr").remove();
	}
}

//销售订单明细新增一条
function addTable(){
	var result="";
	var zz = "this.value=this.value.replace(/\\s+/g,'')"
	for (var i = 0; i < dataEXList.length; i++) {
		var trColor = (i%2 ==0) ?"#E6E6FA":"#E6E6FA";
		result +="<tr style='border: 2px solid #708090;height:70px;' bgcolor='"+trColor+"'>";
		result +="<td width=\"30\">"+(i+1)+"</td>";
		result +="<td>"+dataEXList[i].orderDetailedNumber+"</td>";
		result +="<td>"+dataEXList[i].materielName+"</br>"+dataEXList[i].materielModel+"</td>";
		if(dataEXList[i].asphaltContent != null){
			result +="<td>"+dataEXList[i].asphaltContent+"</br>"+dataEXList[i].listModel+"</td>";
		}else{
			result +="<td></br>"+dataEXList[i].listModel+"</td>";
		}
		result +="<td>"+dataEXList[i].detailedIsActualDelivery+"</br>"+dataEXList[i].transportBalanceName+"</td>";
		result +="<td>"+dataEXList[i].unitPrice+"</br>"+dataEXList[i].expectedDeliveryDate+"</td>";
		result +="<td>"+dataEXList[i].transports+"</br>"+dataEXList[i].distance+"</td>";
		if(dataEXList[i].mileage != null){
			result +="<td>"+dataEXList[i].mileage+"</td>";
		}else{
			result +="<td></td>";
		}
		result +="<td>"+dataEXList[i].saleNumber+"</br>"+dataEXList[i].salePrice+"</td>";
		if(dataEXList[i].taxRate != null){
			result +="<td>"+dataEXList[i].taxRate+"</br>"+dataEXList[i].saleMoney+"</td>";
		}else{
			result +="<td>0</br>"+dataEXList[i].saleMoney+"</td>";
		}
		result +="<td>"+dataEXList[i].detailedIsExchange+"</td>";
		if(dataEXList[i].detailedIsExchange == "是"){
			if(dataEXList[i].exchangeMaterielName != null){
				result +="<td>"+dataEXList[i].exchangeMaterielName+"</br>"+dataEXList[i].exchangeMaterielModel+"</td>";
			}else{
				result +="<td></td>";
			}
			result +="<td>"+dataEXList[i].proportion+"</br>"+dataEXList[i].exchangeWeight+"</td>";
		}else{
			result +="<td></td>";
			result +="<td></td>";
		}
		result +="<td>"+dataEXList[i].detailedIsCancel+"</br>"+dataEXList[i].cancelPerson+"</td>";
		result +="<td>"+dataEXList[i].remarks+"</td>";
		result +="</tr>";
	}
	$("#tableList1").append(result);
}

//获取调度出库单信息
function getExportMeasureInfo(value){
	var param = {};
	param.salesOrderId = value;
	var infoList = [];
	//获取列表 刷新
	var table = $('#listTable').dataTable();
	table.fnDestroy();
	
	$("#listTable").DataTable({
        "paging": true,
        "lengthChange": false,
        "pageLength": 5,
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
			type : "post",
			url : "../../salesContractManagement/getExportMeasureInfo.action",
			dataSrc : "data",
			data : function(d) {
				return param;
			}
		},
        "deferRender": true,
        "columns": [ 
    	{
            "data": "id"
        }, {
        	"data": "orderDetailedNumber"
        }, {
        	 "data": "serialId"
        }, {
            "data": "plateNumber"
        }, {
        	"data": "productID",
            render: function(data, type, row) {
            	if(row.materielName != null){
            		if (row.outType == 4 || row.outType == 5) {
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
        }, {
            "data": "customerCode",
            render: function(data, type, row) {
            	var _html = "";
            	if (data && data.length > 10) {
            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            	} else {
            		_html += "<span title='"+data+"'>"+data+"</span>";
            	}
            	return _html;
            }
        }, {
            "data": "customerAlias",
            render: function(data, type, row) {
            	var _html = "";
            	if (data && data.length > 10) {
            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            	} else {
            		_html += "<span title='"+data+"'>"+data+"</span>";
            	}
            	return _html;
            }
        }, {
	       	 "data": "contractId",
	         render: function(data, type, row) {
	         	var html = "";
	         	if(row.contractNumber != null && row.contractNumber != ''){
	         		html = "<span class='hetongbianhao hetong' >"+row.contractNumber+"</span>";
	         	}
	         	return html;
	       }
	    }, {
            "data": "salesOrderId",
             render: function(data, type, row) {
             	var html = "";
             	if(row.orderNumber != null && row.orderNumber != ''){
             		html = "<span class='hetongbianhao hetong'>"+row.orderNumber+"</span>";
             	}
             	return html;
           }
        }, {
            "data": "outTypeName"
        }, 
        {
            "data": "isExamine",
            render: function(data, type, row) {
            	var html = "";
            	if(data != null){
            		if(data == 0){
                		html = "审核通过"
                	}else if(data == 1){
                		html = "审核未通过"
                	}	
            	}else{
            		html = "未审核";
            	}
				return html;
            }
        },
        {
            "data": "name"
        }, {
            "data": "carOwner",
            render: function(data, type, row) {
				data = row.deliveryMan + "--" + row.deliveryManPhone;
				return data;
            }
        }, {
            "data": "address",
            render: function(data, type, row) {
            	var _html = "";
            	if (data && data.length > 10) {
            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            	} else {
            		_html += "<span title='"+data+"'>"+data+"</span>";
            	}
            	return _html;
            }
        }, {
            "data": "consignee",
            render: function(data, type, row) {
				data = row.consignee + "--" + row.consigneePhone;
				return data;
            }
        }, {
            "data": "testReportNumber"
        }, {
            "data": "factoryTime"
        }, {
            "data": "expectedDeliveryDate"
        }, {
            "data": "suttle",
            render: function(data, type, row) {
				if (data) {
					return data / 1000;
				} else {
					return data;
				}
            }
        }, {
            "data": "isTesting",
            render: function(data, type, row) {
				if(data == 0){
					data = "是";
				}else{
					data = "否";
				}
				return data;
            }
        }, {
            "data": "outTypeMark",
            render: function(data, type, row) {
				if(data == 1){
					data = "是";
				}else{
					data = "否";
				}
				return data;
            }
        }, {
            "data": "isEmpty",
            render: function(data, type, row) {
				if(data == "Y"){
					data = "是";
				}else{
					data = "否";
				}
				return data;
            }
        }, {
            "data": "emptyList",
            render: function(data, type, row) {
            	var _html = "";
            	if (data) {
            		if (data.length > 20) {
            			_html += "<span title='"+data+"'>"+data.substring(0,20) + "..." +"</span>";
            		} else {
            			_html += "<span title='"+data+"'>"+data+"</span>";
            		}
            	}
            	return _html;
            }
        }, {
            "data": "cancellationCause",
            render: function(data, type, row) {
            	var _html = "";
            	if (data) {
            		if (data.length > 10) {
            			_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            		} else {
            			_html += "<span title='"+data+"'>"+data+"</span>";
            		}
            	}
            	return _html;
            }
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html("<input name='radio' type='hidden' value=\""+ aData.id +"\" />"+(iDisplayIndex +1));//设置序号位于第一列，并顺次加一               	
            if(aData.validFlag == 0){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#CCCCCC");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }
           // 获取所有数据
           infoList.push(aData);
           return nRow;
        }
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}