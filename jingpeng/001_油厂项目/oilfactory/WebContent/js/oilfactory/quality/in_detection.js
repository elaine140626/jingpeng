// 当前登录人姓名
var reviser = '';

var MaterielList;

$(function(){
	// 入库单管理datatable
	getList();
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		reviser = userInfo.id;
	}
	getMaterielInfo();
})

//关键字检索
function keywordSelect(){
	showZtreeS();
}

// 入库单管理datatable
function getList(){
	// 查询
	var fyZldz = $("#fyZldz").val();
	var table1 = $('#storagemeasure').dataTable();
	table1.fnDestroy();
	$("#storagemeasure").DataTable({
		 "paging": true,
         "lengthChange": false,
         "pageLength": 4,
         "stateSave": true,
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
            url: "../../testreportsaledetailed/getStoragemeasure.action",
            dataSrc: "data",
            data: function (d) {
          	var param = {};
                param.fyZldz = fyZldz;//按查询
//                param.ContractState = ContractState;//按合同状态查询
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": 
        	[{
        	   "data": "id"
	        }, {
	            "data": "id",
	            render:function(data,type,row,meta) {
	            	var html = '';
		  		    html += "<a onclick=update("+row.id+")><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"检测\" title=\"检测\"></a>";
		  		    return html;
	            }
	        }, {
	            "data": "plateNumber"
	        }, {
	            "data": "client"
	        }, {
	            "data": "deliveryMan"
	        }, {
	            "data": "productID",
	            render:function(data,type,row,meta) {
	            	if(MaterielList != null){
	            		for (var i = 0; i < MaterielList.length; i++) {
	            			if(data == MaterielList[i].MaterielNameId){
	            				return MaterielList[i].MaterielName;
			            	}	
						}
	            		return "";
	            	}else{
	            		return "";	
	            	}
	            }
	        }, {
	            "data": "carOwner"
	        }, {
	            "data": "carOwnerTelephone"
	        }, {
	            "data": "skillExplain"
	        },{
	        	"data": "enterDate"
	        },{
	        	"data": "flag",
	            render:function(data,type,row,meta) {
	            	var html="";
	            	if (data == "不合格") {
	            		html += "<span style='color:#F00'>不合格</span>"
	            	} else {
	            		html += "<span>合格</span>"
	            	}
	            	return html;
	            }
	        }],
		"createdRow" : function(row, data, dataIndex) {
		},
		"order": [[ 1, 'asc' ]],
        "columnDefs": [{
            "targets": [0],
            "visible": true,
            "searchable": false
        }]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#contractManagement_info').css('text-align','center');
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

//物料信息取得
function getMaterielInfo(){
	$.ajax({
		type : "POST",
		url : "../../salesContractManagement/getMaterielInfo.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			MaterielList = data;
		}
	});
	return MaterielList
}

// 点击查询
function search(){
	getList();
}

// 检测入库单项目
function update(id){
	location.href = 'in_detection_edit.html?flag=1&id='+id;
	}






