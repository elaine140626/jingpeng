// 当前登录用户id
var userId = '';
var cityId = '';
var countyId = '';
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		cityId = userInfo.cityId;
		countyId = userInfo.countyId
		if(cityId == 0){
			cityId = '';
		}
		if(countyId == 0){
			countyId = '';
		}
	}
	showZtreeS(cityId,countyId);
	// datatable数据初始化
	getList();
	
	// 合计行
	getSum();
})

// datatable数据初始化
function getList(treeIds,levels){
	var param = formToJson($("#form1"));
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
            url: "../../SideDitchNumber/getSideDitchNumberList.action",
            dataSrc: "data",
            data: function (d) {
            	param.cityId = cityId
            	param.countyId = countyId
            	param.levels = levels;
            	param.treeIds = treeIds;
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": [
        	{"data": "id"},
        	{
        		"data": "id",
        		render:function(data,type,row,meta) {
        			var html = '';
        			html += "<a onclick='check(\""+row.id+"\")'style='text-decoration:none; font-size:14px;' href='javascript:;' title='查看'>查看&nbsp;</a>";
        			html += "<a onclick='update(\""+row.id+"\")'style='text-decoration:none; font-size:14px;' href='javascript:;' title='编辑'>&nbsp;编辑&nbsp;</a>";
        			html += "<a onclick='copy(\""+row.id+"\")'style='text-decoration:none; font-size:14px;' href='javascript:;' title='复制'>&nbsp;复制&nbsp;</a>";
        			html += "<a onclick='del(\""+row.id+"\")' style='text-decoration:none; font-size:14px;' href='javascript:;' title='删除'>&nbsp;删除</a>";
        			return html;
        		}
        	},
        	{"data": "pileNumber"},
        	{"data": "position"},
        	{"data": "form"},
        	{"data": "length"},
        	{"data": "foundation"},
        	{"data": "mortar"},
        	{"data": "sandCushion"},
        	{"data": "rubble"},
        	{"data": "c20Concrete"},
        	{"data": "coverPlate"},
        	{"data": "eightRebar"},
        	{"data": "twelveRebar"},
        	{"data": "c25Concrete"},
        	{"data": "remarks"},      	
        	],
        "createdRow": function( row, data, dataIndex ) {
//        	console.log(data);
        },
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一                
            return nRow;
        },
        "columnDefs": []
    });
//	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
//	$('.table1_info').parent('div').addClass('col-sm-12');
}

//树形节点点击事件
function findClick(event, treeId, treeNode, isCancel) {
	treeIds = treeNode.id;
	// 0:所有 1:市  2:县  3:项目
	levels = treeNode.level;
	if (levels != 0){
		treeIds = treeIds.substring(2,treeIds.length);
	}	
	// datatable
	getList(treeIds,levels);
	// 合计行
	getSum(treeIds,levels);
}
//合计值赋值
function getSum(treeIds,levels){	
	var param = formToJson($("#form1"));
	param.levels = levels;
	param.treeIds = treeIds;
	$.ajax({
		type: 'POST',
		url: '../../SideDitchNumber/getSideDitchNumberSum.action',
		data:param,
		dataType: 'json',
		success: function(data){
			if (data[0] != null){
				$("#lengthSum").html(data[0].lengthSum);
				$("#foundationSum").html(data[0].foundationSum);
				$("#mortarSum").html(data[0].mortarSum);
				$("#sandCushionSum").html(data[0].sandCushionSum);
				$("#rubbleSum").html(data[0].rubbleSum);
				$("#c20ConcreteSum").html(data[0].c20ConcreteSum);
				$("#coverPlateSum").html(data[0].coverPlateSum);
				$("#eightRebarSum").html(data[0].eightRebarSum);
				$("#twelveRebarSum").html(data[0].twelveRebarSum);
				$("#c25ConcreteSum").html(data[0].c25ConcreteSum);
			} else {
				$("#lengthSum").html("");
				$("#foundationSum").html("");
				$("#mortarSum").html("");
				$("#sandCushionSum").html("");
				$("#rubbleSum").html("");
				$("#c20ConcreteSum").html("");
				$("#coverPlateSum").html("");
				$("#eightRebarSum").html("");
				$("#twelveRebarSum").html("");
				$("#c25ConcreteSum").html("");
			}	
		}
	});			
}

//查询
function search(){	
	var treeIds = 0;
	var levels = 0;
	var zTreeObj = $.fn.zTree.getZTreeObj("EntryTree"); 
	//获取光标选中的(selected)
	var selectedNodes = zTreeObj.getSelectedNodes();
	if (selectedNodes[0] != undefined){
		levels = selectedNodes[0].level;
		if (levels !=0){
			treeIds = selectedNodes[0].id.substring(2,selectedNodes[0].id.length);
		}		
	}	
	// datatable
	getList(treeIds,levels);
	// 合计行
	getSum(treeIds,levels)
}

// 添加
function add() {
	var zTreeObj = $.fn.zTree.getZTreeObj("EntryTree"); 
	//获取光标选中的(selected)
	var selectedNodes = zTreeObj.getSelectedNodes();
	if (selectedNodes != null && selectedNodes.length>0){
		if (selectedNodes[0].level != 3){
			layer.alert('请选择一个项目！', {
				icon: 7,
				title: "提示"
				});
		} else {
			console.log(selectedNodes[0].id);
			var ProjectId = selectedNodes[0].id.substring(2,selectedNodes[0].id.length);
			var index = layer.open({
				type: 2,
				title: '添加边沟工程',
//				area: ['850px', '620px'],
				content: 'sideDitchNumber_add.html?ProjectId='+ProjectId,
			});
			layer.full(index);
		}
		
	} else {
		layer.alert('请选择一个项目！', {
			icon: 7,
			title: "提示"
			});
	}
}

// 编辑
function update(id){
	var index = layer.open({
		type: 2,
		title: '修改边沟工程',
//		area: ['850px', '620px'],
		content: 'sideDitchNumber_add.html?id='+id+"&flag=1",
	});
	layer.full(index);
}

//复制
function copy(id){
	var index = layer.open({
		type: 2,
		title: '复制边沟工程',
		content: 'sideDitchNumber_add.html?id='+id+"&flag=2",
	});
	layer.full(index);
}


// 查看
function check(id){
	var index = layer.open({
		type: 2,
		title: '查看边沟工程',
//		area: ['850px', '620px'],
		content: 'sideDitchNumber_add.html?id='+id+"&flag=0",
	});
	layer.full(index);
}

// 删除
function del(id){
	layer.confirm('确认要删除吗？', 
		function(index) {
			var params = {"id":id,"reviser":userId};	
			$.ajax({
				type: 'POST',
				url: '../../SideDitchNumber/deleteSideDitchNumber.action',
				data:JSON.stringify(params),
				dataType: 'json',
				contentType: 'application/json',
				success: function(data){
					if (data.code == 'success'){
						// 操作成功
						layer.msg(data.message, {
							icon: 1,
							time: 1000
						},function(){
							// 刷新当前页
							location.reload();
						});
					} else {
						// 操作失败
						layer.msg(data.message, {
							icon: 2,
							time: 1000
						},function(){
							// 刷新当前页
							location.reload();
						});
					}	
				}
			});		
	});
}


function exportInfo(){
	var param = formToJson($("#form1"));
	param.cityId = cityId
	param.countyId = countyId
/*	$.ajax({
		type: 'POST',
		url: '../../RoadbedProtectionNumber/export.action',
		data: param,
		dataType: 'json',
		success: function(data){
		}
	});	*/
	$.ajaxFileUpload({
		url : '../../SideDitchNumber/export.action',
		data:param,
		secureuri : false,
		fileElementId : 'file',
		success : function(res, status) {
		}
	});
}
