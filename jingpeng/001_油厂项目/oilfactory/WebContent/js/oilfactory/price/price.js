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
// 车辆检索框赋值
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
/*	var isOut;
	if($.inArray("1", obj)){
		arr.splice($.inArray('1',obj),1);
		isOut = 1;
	}*/
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
			url : "../../price/getInfoList.action",
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
             		if (row.outType == 4 || row.outType == 5) {
             			data = "由 " + row.materielName2 + "--" + row.materielModel2 + " 兑换为 " + row.materielName + "--" + row.materielModel 
             				+ "</br>兑换比例为" + row.proportion + "%";
             			return data;
             		} else if(row.outType == 98){
             			if(row.allotWeight != null){
             				data = "调拨为 " +row.materielName + "--" + row.materielModel 
             				+ "</br>调拨重量为" + row.allotWeight + "t";
             			}else{
             				data = row.materielName + "--" + row.materielModel;
             			}
             			return data;
             		}else {
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
        	 "data": "contractId",
             render: function(data, type, row) {
             	var html = "";
             	if(row.contractNumber != null && row.contractNumber != ''){
             		//html = "<span class='hetongbianhao hetong' onmouseover='mouseover("+row.id+",0)'  onmouseout ='mouseout("+row.id+",0)'>"+row.contractNumber+"</span>";
             		html = "<span class='hetongbianhao hetong'>"+row.contractNumber+"</span>";
             		var Contract = getContractList(row.contractId);
             		// 获取销售合同信息
//                 	html += "<div id='contractNumber"+row.id+"' class='fuchuang4' style='margin-left:50px;'>"
//     							+"<table style='border-top: 1px solid #ECECEC;'>"
//     							+"	<tr>"
//     							+"		<td class='hetongzhuangtai'>合同名称：</td>"
//     							+"		<td class='hetongzhuangtai'>"+Contract[0].contractName+"</td>"
//     							+"		<td class='hetongzhuangtai'>合同日期：</td>"
//     							+"		<td class='hetongzhuangtai'>"+Contract[0].contractDate+"</td>"
//     							+"	</tr>"
//     							+"	<tr>"
//     							+"		<td class='hetongzhuangtai'>客户名称：</td>"
//     							+"		<td class='hetongzhuangtai'>"+Contract[0].customerName+"</td>"
//     							+"		<td class='hetongzhuangtai'>销售公司名称：</td>"
//     							+"		<td class='hetongzhuangtai'>"+Contract[0].salesCompanyName+"</td>"
//     							+"	</tr>"
//     							+"	<tr>"
//     							+"		<td class='hetongzhuangtai'>销售数量(t)：</td>"
//     							+"		<td class='hetongzhuangtai'>"+Contract[0].saleNumber+"</td>"
//     							+"		<td class='hetongzhuangtai'>是否来料加工：</td>"
//     							+"		<td class='hetongzhuangtai'>"+Contract[0].isIncoming+"</td>"
//     							+"	</tr>"
//     							+"</table>"
//     							+"</div>";
           	}
             	
             	return html;
           }
        },{
            "data": "salesOrderId",
             render: function(data, type, row) {
             	var html = "";
             	if(row.orderNumber != null && row.orderNumber != ''){
             		//html = "<span class='hetongbianhao hetong' onmouseover='mouseover("+row.id+",1)'  onmouseout ='mouseout("+row.id+",1)'>"+row.orderNumber+"</span>";
             		html = "<span class='hetongbianhao hetong'>"+row.orderNumber+"</span>";
             		var OrderNumber = getOrderNumberList(row.salesOrderId);
             		
//             		// 获取销售合同信息
//                 	html += "<div id='orderNumber"+row.id+"' class='fuchuang3' style='margin-left:150px;'>"
//     							+"<table style='border-top: 1px solid #ECECEC;'>"
//     							+"	<tr>"
//     							+"		<td class='hetongzhuangtai'>客户名称：</td>"
//     							+"		<td class='hetongzhuangtai'>"+OrderNumber[0].customerName+"</td>"
//     							+"		<td class='hetongzhuangtai'>客户别称：</td>"
//     							+"		<td class='hetongzhuangtai'>"+OrderNumber[0].customer+"</td>"
//     							+"	</tr>"
//     							+"	<tr>"
//     							+"		<td class='hetongzhuangtai'>销售公司名称：</td>"
//     							+"		<td class='hetongzhuangtai'>"+OrderNumber[0].salesCompanyName+"</td>"
//     							+"		<td class='hetongzhuangtai'>是否实际发货：</td>"
//     							+"		<td class='hetongzhuangtai'>"+OrderNumber[0].isActualDelivery+"</td>"
//     							+"	</tr>"
//     							+"	<tr>"
//     							+"		<td class='hetongzhuangtai'>联系人：</td>"
//     							+"		<td class='hetongzhuangtai'>"+OrderNumber[0].contacts+"</td>"
//     							+"		<td class='hetongzhuangtai'>联系方式：</td>"
//     							+"		<td class='hetongzhuangtai'>"+OrderNumber[0].telephone+"</td>"
//     							+"	</tr>"
//     							+"</table>"
//     							+"</div>";
             	}else{
             		html = "<span class='hetongbianhao hetong'"+row.serialID+"</span>";
             	}
             	
             	return html;
           }
        },{
        	"data": "serialId"
        },{
            "data": "OutType",
            render: function(data, type, row) {
            		var html = "";
            		if(row.outType == 0){
            			html = "正常出库单";
            			return html;
            		}
            		if(row.outType == 1){
            			html = "调拨出库单"
            			return html;
            		}
            		if(row.outType == 2){
            			html = "退货出库单";
            			return html;
            		}
            		if(row.outType == 3){
            			html = "空发出库单";
            			return html;
            		}
            		if(row.outType == 4){
            			html = "兑换出库单";
            			return html;
            		}
            		if(row.outType == 5){
            			html = "兑换时调拨单";
            			return html;
            		}
            		if(row.outType == 98){
            			html = "未称重出库单";
            			return html;
            		}
            		if(row.outType == 99){
            			html = "入库单";
            			return html;
            		}
	            }
	    },{
	    	"data": "name"
	    },{
            "data": "carOwner"
        },{
            "data": "amount",
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
            $("td:first", nRow).html("<input name='checkbox' type='checkbox'  value=\""+ aData.orderDetailedId+"-"+aData.serialId+"\" />"+(iDisplayIndex +1));//设置序号位于第一列，并顺次加一               	
            /*if(aData.validFlag == 0){                		
            	$(nRow).css("background", "#FF7F00");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                   	
            }*/
          //  onclick='save(this,"+ aData.outType+",\""+ aData.serialId+"\")'
           // 获取所有数据
           /*infoList.push(aData);
           return nRow;*/
        },
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}



function copy(id){
	//点击复制 (需通过标识来判断是出库单还是未入厂出库单)
//	id = $('input[type=radio]:checked').val();
	if(!id) {
		alert('请选择要复制的客户回访信息！');
		return false;
	}else{
		var outTypeMark;
		for(var i=0;i<infoList.length;i++){
			if(id == infoList[i].id){
				outTypeMark = infoList[i].outTypeMark;
				break;
			}
		}
		
		// 出库单
		if(outTypeMark == 0){
			$.fancybox({
				'href': 'price_edit.html?id='+id+"&flag=0",
				'width': 800,
				'height': 425,
				'type': 'iframe',
				'hideOnOverlayClick': false,
				'showCloseButton': false,
				'onClosed': function() {
					window.location.href = 'price.html';
				}
			});
		}else if(outTypeMark == 1){
			// 未入厂出库单
			$.fancybox({
				'href': 'outunbound_edit.html?id='+id+"&flag=0",
				'width': 800,
				'height': 425,
				'type': 'iframe',
				'hideOnOverlayClick': false,
				'showCloseButton': false,
				'onClosed': function() {
					window.location.href = 'price.html';
				}
			});
		}
		
	}
}

//销售合同编号
function getContractList(id){
	var Contract = '';
	$.ajax({
		type: "post",
		url: "../../instore/getContractList.action",
		data:{"id":id},
		dataType: "json",
		async:false,
		success: function (data) {
			Contract = data;
		}
	});
	return Contract;
}

//销售订单编号
function getOrderNumberList(id){
	var OrderNumber = '';
	$.ajax({
		type: "post",
		url: "../../price/getOrderNumberList.action",
		data:{"id":id},
		dataType: "json",
		async:false,
		success: function (data) {
			OrderNumber = data;
		}
	});
	return OrderNumber;
}

function mouseover(id,flag){
	if(flag == 0){
		//鼠标放在合同编号，显示飘窗
		$('#contractNumber'+id).show();
	}else{
		//鼠标放在合同编号，显示飘窗
		$('#orderNumber'+id).show();
	}
}
function mouseout(id,flag){
	if(flag == 0){
		//鼠标离开合同编号，飘窗消失
		$('#contractNumber'+id).hide();
	}else{
		//鼠标离开合同编号，飘窗消失
		$('#orderNumber'+id).hide();
	}
}
//修改价格
function update(){
	id = $('input[name=checkbox]:checked').val();
	if (id !='undefined' && id != null && id != ''){
	 obj = document.getElementsByName("checkbox");
	 check_val = [];
	    for(k in obj){
	        if(obj[k].checked)
	            check_val.push(obj[k].value);
	  }
	  //  var sss = JSON.stringify(params);
		$.fancybox({
			'href': 'price_empty.html?id=' + check_val ,
			'width': 1000,
			'height': 630,
			'type': 'iframe',
			'hideOnOverlayClick': false,
			'showCloseButton': false,
			'onClosed': function() {
				window.location.href = 'price.html';
			}
		});
	} else {
		swal("操作失败", "请选择一条数据", "info");
	}	
}
function checkboxOnclick(){
	var isCheck=$("#priceBox").is(':checked');  //获得全选复选框是否选中
    $("input[name='checkbox']").each(function() {  
        this.checked = isCheck;       //循环赋值给每个复选框是否选中
    });
   
}

function search(){
	var params = {};
	var str = [];
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	params.startTime = startTime;
	params.endTime = endTime;
	getInfoList(params);
}

function searchAll(){
	var params = {};
	getInfoList(params);
}


function save(event, type, sid){
	var info = {};
	if(event.checked){
		info.sid = sid;
		info.type = type;
		params.push(info);
		console.log(params);
	}else{
		info.sid = sid;
		info.type = type;
		params.remove(info);
		console.log(params);
	}

}

Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
	this.splice(index, 1);
	}
	};
//客户止运地
function getCustomerTrans(id){
	var Transports = '';
	$.ajax({
		type: "post",
		url: "../../price/getCustomerTrans.action",
		data:{"id":id},
		dataType: "json",
		async:false,
		success: function (data) {
			Transports = data;
		}
	});
	return Transports;
}