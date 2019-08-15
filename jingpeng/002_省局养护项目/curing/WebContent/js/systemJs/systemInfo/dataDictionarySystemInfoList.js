// 当前登录用户id
var userId = ''; 
var type;
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	type = getUrlParam('type');
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
	switch (type) {
	case 'sex':
		$("#titName").html("性别管理")
		break;
	case 'yhje':
		$("#titName").html("用户角色 管理")
		break;
	case 'bxgcsl':
		$("#titName").html("标线工程数量")
		break;
	case 'gcxm':
		$("#titName").html("工程项目")
		break;
	case 'gcxm':
		$("#titName").html("性别管理")
		break;
	case 'jhpc':
		$("#titName").html("计划批次")
		break;
	case 'jsxz':
		$("#titName").html("建设性质")
		break;
	case 'jsdj':
		$("#titName").html("技术等级管理")
		break;
	case 'xzdj':
		$("#titName").html("行政等级管理")
		break;
	case 'xmly':
		$("#titName").html("项目来源")
		break;
	case 'xmfl':
		$("#titName").html("项目分类")
		break;
	case 'pwlx':
		$("#titName").html("批文类型")
		break;
	case 'gcldw':
		$("#titName").html("工程量单位")
		break;
	case 'zbxz':
		$("#titName").html("招标性质")
		break;
	case 'gcjl':
		$("#titName").html("结算工程价款")
		break;
	case 'jsxz':
		$("#titName").html("建设性质")
		break;
	case 'xmxz':
		$("#titName").html("项目性质")
		break;
	case 'gx90':
		$("#titName").html("改性90#类别")
		break;
	case 'qt90':
		$("#titName").html("其他90#类别")
		break;
	case 'rhlqlb':
		$("#titName").html("乳化沥青类别")
		break;
	case 'zjly':
		$("#titName").html("资金来源")
		break;
	}
	// datatable数据初始化
	getDataDictionary();
})

// datatable市级数据初始化
function getDataDictionary(){
	var param = {"Type":type};
	var table1 = $('#table1').dataTable();
	table1.fnDestroy();
	$("#table1").DataTable({
		 "paging": true,
         "lengthChange": false,
         "pageLength": 10,
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
            url: "../../DataDictionarySystemInfoList/getDataDictionarySystemInfoList.action",
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
        			html += "<a onclick='modalUpdate(\""+row.id+"\")'style='text-decoration:none' href='javascript:;' title='编辑'>编辑</a>&nbsp;&nbsp;&nbsp";
        			html += "<a onclick='del(\""+row.id+"\")' style='text-decoration:none' href='javascript:;' title='删除'>删除</a>";
        			return html;
        		}
        	},
        	{"data": "content"},
        	{"data": "contentExplain"} 
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

//添加
function add() {
	var param = formToJson($("#addFrom"));
	var url;
	//添加
	url =  '../../DataDictionarySystemInfoList/insertDataDictionarySystemInfoList.action';
	param.type = type;
	
	if($("#contentAdd").val() == ''){
		$.Huimodalalert('名称不能为空',2000)
		return;
	}
	
	$.ajax({
		type : 'POST',
		url : url,
		data:JSON.stringify(param),
		dataType: 'json',
		contentType: 'application/json',
		success : function(data) {
			if (data.code = "success") {
				location.reload();
				$.Huimodalalert('操作成功',2000)
				$("#modal-add").modal("hide")
			} else {
				$.Huimodalalert('操作失败',2000)
			}
		}
	});
	
}

function update(){
	var id = $("#updateid").val();
	var param = formToJson($("#updateFrom"));
	var url;
	//修改
	url =  '../../DataDictionarySystemInfoList/updateDataDictionarySystemInfoList.action';
	// id
	param.id = id;
	
	if($("#contentUpdate").val() == ''){
		$.Huimodalalert('名称不能为空',2000)
		return;
	}
	$.ajax({
		type : 'POST',
		url : url,
		data:JSON.stringify(param),
		dataType: 'json',
		contentType: 'application/json',
		success : function(data) {
			if (data.code = "success") {
				location.reload();
				$.Huimodalalert('操作成功',2000)
				$("#modal-add").modal("hide")
			} else {
				$.Huimodalalert('操作失败',2000)
			}
		}
	});
	
}


// 删除
function del(id){
	layer.confirm('确认要删除吗？', 
		function(index) {
			var params = {"id":id,"reviser":userId};	
			$.ajax({
				type: 'POST',
				url: '../../DataDictionarySystemInfoList/deleteDataDictionarySystemInfoList.action',
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

function modalAdd(){
	$("#modal-add").modal("show")
}
function modalUpdate(id){
	$("#modal-update").modal("show")
	
	var dinfo;
	$.ajax({
		type : 'POST',
		url: "../../DataDictionarySystemInfoList/getDataDictionarySystemInfoList.action",
        data: {'id':id,"Type":type},
		dataType: 'json',
		success : function(data) {
			dinfo = data.data
			$("#updateid").val(id);
			$("#contentUpdate").val(dinfo[0].content);
		}
	});
}