// 用户
var userInfo = "";
//用户权限
var roleFlag = "";
$(document).ready(function(){
	// 获取用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){	
		reviser = userInfo.name;
		var list = userInfo.rolecode;
		
		if(list.indexOf(",9,") != -1 || list.indexOf(",10,") != -1 || list.indexOf(",11,") != -1 || list.indexOf(",12,") != -1){
			// 生产部长    生产副部长   生产班长   检斤员(查看)
			roleFlag = false;
			$("#addExchangeBtn").hide();
		}
		if(list.indexOf(",3,") != -1 || list.indexOf(",13,") != -1){
			// 调度&&调度管理员(操作)
			roleFlag = true;
			$("#addExchangeBtn").show();
		}
		if(list.indexOf(",0,") != -1){
			// 管理员(操作)
			roleFlag = true;
			$("#addExchangeBtn").show();
		}
		if(list.indexOf(",7,") != -1){
			// 总经理(操作)
			roleFlag = true;
			$("#addExchangeBtn").show();
		}
	}
	// 画面数据list初始化
	getList();
	
	// 新增生产计划
	$("#addExchangeBtn").fancybox({
		'href': 'nextProductionPlan_edit.html',
		'width': 800,
		'height': 500,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'nextProductionPlan.html';
		}
	})
})

function getList(){
	//获取列表 刷新
	var table = $('#list').dataTable();
	table.fnDestroy();
	
	// 检索参数
	var param = formToJson($("#submitForm"));
	$("#list").DataTable({
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
			type : "post",
			url : "../../nextProductionPlan/getProductionPlanList.action",
			dataSrc : "data",
			data : function(d) {
				return param;
				
			}
		},
        "deferRender": true,
        "columns": [ 
    	{
            "data": "id"
        },
        {
            "data": "id",
            render: function(data, type, row) {
            	var html = '';
            	if(roleFlag){
            		if (row.isLowerHair == 0) {
                		html +="<a class='fb' onclick=update("+row.id+",1)><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
                	}else{
                		html +="<a class='fb' onclick=update("+row.id+",0)><img src=\"../../img/common/bao.png\" width=\"20\" height=\"20\" alt=\"修改\" title=\"修改\"></a>";
                		html +=	"<a onclick=del("+row.id+")><img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\"></a>";
                		html +="<a class='fb' onclick=down("+row.id+")><img src=\"../../img/common/tijiao.png\" width=\"20\" height=\"20\" alt=\"下发\" title=\"下发\"></a>";
                	}
            	}else{
            		html +="<a class='fb' onclick=update("+row.id+",1)><img src=\"../../img/common/cha.png\" width=\"20\" height=\"20\" alt=\"查看\" title=\"查看\"></a>";
            	}
	  		    return html;
            }
        }, {
        	 "data": "planNumber"
        }, {
            "data": "materielName"
        },{
        	"data": "materielModel"
        }, {
        	"data": "materielNumber"
        },{
            "data": "customerCode"
        },{
            "data": "customerName"
        }, {
            "data": "planWeight"
        },
        {
        	"data":"unit"
        },{
	       	 "data": "storageLocationName",
	            render: function(data, type, row) {
	            	var _html = "";
	            	if (data != null && data.length > 10) {
	            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
	            	} else {
	            		if(data != null){
	            			_html += "<span title='"+data+"'>"+data+"</span>";
	            		}
	            	}
	            	return _html;
	            }
	    }, {
            "data": "batch"
        }, {
            "data": "isLowerHair",
            render: function(data, type, row) {
				if(data == 0){
					data = "是";
				}else{
					data = "否";
				}
				return data;
            }
        },
        {
            "data": "finishTime"
        }, {
            "data": "analystOrDirector",
            render: function(data, type, row) {
				if(data == 0){
					data = "质检员";
				}else{
					data = "质检主任";
				}
				return data;
            }
        }, {
            "data": "lowerHairPerson"
        }, {
            "data": "lowerHairDate"
        }, {
            "data": "remarks",
            render: function(data, type, row) {
            	var _html = "";
            	if (data != null && data.length > 10) {
            		_html += "<span title='"+data+"'>"+data.substring(0,10) + "..." +"</span>";
            	} else {
            		if(data != null){
            			_html += "<span title='"+data+"'>"+data+"</span>";
            		}
            	}
            	
            	return _html;
            }
        }],
        "order": [[ 1, 'asc' ]],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
       		html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一
        	
        	// 调整行变色
            if(aData.isLowerHair == 0){                		
            	//设置满足条件行的背景颜色                		
            	$(nRow).css("background", "#999999");                		
            	//设置满足条件行的字体颜色                		
            	$(nRow).css("color", "white !important");                	
            }
            return nRow;
        }
    });
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
}

// 查询方法
function search(){
	getList();
}

// 修改
function update(id, flag){
	$.fancybox({
		'href': 'nextProductionPlan_edit.html?id='+id+"&flag="+flag,
		'width': 800,
		'height': 425,
		'type': 'iframe',
		'hideOnOverlayClick': false,
		'showCloseButton': false,
		'onClosed': function() {
			window.location.href = 'nextProductionPlan.html';
		}
	});
}

// 删除
function del(id){
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
		$.ajax({
			type: "post",
			url: "../../nextProductionPlan/delProductionPlan.action",
			data: {"Id":id,"Reviser":userInfo.id},
			dataType: "json",
			success: function (data) {
				if(data.code == 'success'){
					window.location.href="nextProductionPlan.html";
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
	});
}

// 下发
function down(id){
	
	swal({
		title: "确定要下发生产计划吗？",
		text:"",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	},
	function(){
		var param = {};
		param.Id = id;
		param.IsLowerHair = 0;
		param.LowerHairPerson = userInfo.name;
		param.Reviser = userInfo.id;
		$.ajax({
			type: "post",
			url: "../../nextProductionPlan/updateProductionPlan.action",
			data: param,
			dataType: "json",
			success: function (data) {
				if(data.code == 'success'){
					window.location.href="nextProductionPlan.html";
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
	});
}