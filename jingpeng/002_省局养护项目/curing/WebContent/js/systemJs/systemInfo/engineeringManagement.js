// 当前登录用户id
var userId = ''; 
var type;
$(function(){
	// 获取当前登录用户信息
	type = getUrlParam('type')
	switch (type) {
	case '0':
		$("#add1").css('display','block');
		$("#select1").css('display','block');
		$("#div1").css('display','block');
		$("#ccpName").html("市管理")
		break;
	case '1':
		$("#add2").css('display','block');
		$("#select2").css('display','block');
		$("#div2").css('display','block');
		$("#ccpName").html("县区管理")
		break;
	case '2':
		$("#add3").css('display','block');
		$("#select3").css('display','block');
		$("#div3").css('display','block');
		$("#ccpName").html("项目管理")
		break;
	}
	userInfo = getUserInfo();
	if(userInfo != ""){
		cityId = userInfo.cityId;
		countyId = userInfo.countyId
		if(cityId == 0){
			cityId = '';
		}else{
			layer.alert("权限不足", {
				icon: 2,
				title: "提示"
			},function(){
				window.parent.location.reload();
			});
			return;
		}
		if(countyId == 0){
			countyId = '';
		}else{
			layer.alert("权限不足", {
				icon: 2,
				title: "提示"
			},function(){
				window.parent.location.reload();
			});
			return;
		}
	}
	// datatable数据初始化
	getListCityInfo(); //市级table
	getListCountyInfo(); //县级table
	getListEntryName();	//项目级table
})

// datatable市级数据初始化
function getListCityInfo(){
	var param = formToJson($("#form1"));
	var table1 = $('#table1').dataTable();
	table1.fnDestroy();
	$("#table1").DataTable({
		 "paging": true,
         "lengthChange": false,
         "pageLength": 8,
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
            url: "../../EngineeringManagement/getCityInfoList.action",
            data: function (d) {
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
        			html += "<a onclick='update("+row.id+",1)'style='text-decoration:none' href='javascript:;' title='编辑'>编辑</a>&nbsp;&nbsp;&nbsp";
        			html += "<a onclick='del("+row.id+",1)' style='text-decoration:none' href='javascript:;' title='删除'>删除</a>";
        			return html;
        		}
        	},
        	{"data": "cityName"} 	
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
}


//datatable县级数据初始化
function getListCountyInfo(){
	var param = formToJson($("#form1"));
	var table1 = $('#table2').dataTable();
	table1.fnDestroy();
	$("#table2").DataTable({
		 "paging": true,
         "lengthChange": false,
         "pageLength": 8,
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
            url: "../../EngineeringManagement/getCountyInfo.action",
            dataSrc: "data",
            data: function (d) {
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
        			html += "<a onclick='update("+row.id+",2)'style='text-decoration:none' href='javascript:;' title='编辑'>编辑</a>&nbsp;&nbsp;&nbsp";
        			html += "<a onclick='del("+row.id+",2)' style='text-decoration:none' href='javascript:;' title='删除'>删除</a>";
        			return html;
        		}
        	},
        	{"data": "cityName"},
        	{"data": "county"} 
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
}


//datatable项目级数据初始化
function getListEntryName(){
	var param = formToJson($("#form1"));
	var table1 = $('#table3').dataTable();
	table1.fnDestroy();
	$("#table3").DataTable({
		 "paging": true,
         "lengthChange": false,
         "pageLength": 8,
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
            url: "../../EngineeringManagement/getEngineeringManagementList.action",
            dataSrc: "data",
            data: function (d) {
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
        			html += "<a onclick='update("+row.id+",3)'style='text-decoration:none' href='javascript:;' title='编辑'>编辑</a>&nbsp;&nbsp;&nbsp";
        			html += "<a onclick='del("+row.id+",3)' style='text-decoration:none' href='javascript:;' title='删除'>删除</a>";
        			return html;
        		}
        	},
        	{"data": "cityName"},
        	{"data": "county"},
        	{"data": "projectNumber"},
        	{"data": "projectName"},
        	{"data": "contractSection"},
        	{"data": "contractingUnit"},
        	{"data": "mileage"}
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
}

//树形节点点击事件
function findClick(event, treeId, treeNode, isCancel) {
/*	treeIds = treeNode.id;
	// 0:所有 1:市  2:县  3:项目
	levels = treeNode.level;
	if (levels != 0){
		treeIds = treeIds.substr(2,treeIds.length-1);
	}	
	// datatable
	getList(treeIds,levels);
	// 合计行
	getSum(treeIds,levels);*/
}

//查询
function search(){	
	getListCityInfo()
	getListCountyInfo();
	getListEntryName();
}

// 添加
function add(flag) {
	var url = "";
	var title = ""
	switch (flag) {
	case 1:
		url =  'engineeringManagement_addCityInfo.html?flag='+flag;
		title = "添加市";
		break;
	case 2:
		url =  'engineeringManagement_addCountyInfo.html?flag='+flag;
		title = "添加县"
		break;
	case 3:
		url =  'engineeringManagement_addEntryName.html?flag='+flag;
		title = "添加项目"
		break;
	}
	var index = layer.open({
		type: 2,
		title: title,
//		area: ['850px', '620px'],
		content: url,
	});
	layer.full(index);
}

// 编辑
function update(id,flag){
	var url = "";
	var title = ""
	switch (flag) {
	case 1:
		url =  'engineeringManagement_addCityInfo.html?flag='+flag+'&id='+id;
		title = "修改市";
		break;
	case 2:
		url =  'engineeringManagement_addCountyInfo.html?flag='+flag+'&id='+id;
		title = "修改县"
		break;
	case 3:
		url =  'engineeringManagement_addEntryName.html?flag='+flag+'&id='+id;
		title = "修改项目"
		break;
	}
	var index = layer.open({
		type: 2,
		title: title,
//		area: ['850px', '620px'],
		content: url,
	});
	layer.full(index);
}


// 删除
function del(id,flag){
	var url = "";
	layer.confirm('确认要删除吗？', 
		function(index) {
		switch (flag) {
		case 1:
			url = '../../EngineeringManagement/deleteCityInfo.action';
			break;
		case 2:
			url = '../../EngineeringManagement/deleteCountyInfo.action';
			break;
		case 3:
			url = '../../EngineeringManagement/deleteEntryName.action';
			break;
		}
			var params = {"id":id,"reviser":userId};	
			$.ajax({
				type: 'POST',
				url: url,
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
