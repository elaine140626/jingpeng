var baseUrl = "";
$(function() {
	baseUrl = getContextPath();
	getList();
	 $.ajax({
	        type: "post",
	        url: "../Andr/getValue.html",
	        data:{},
	        async:false,
	        dataType: "json",
	        success: function (data) {
	            $(".userid").html(data.userName)
	        }
	 })
});

function getList() {
	$('#li').DataTable(
			{
				"paging" : true,
				"lengthChange" : false,
				"pageLength" : 14,
				"searching" : false,
				"ordering" : false,
				"info" : true,
				"sInfo" : true,
				"autoWidth" : false,
				"language" : {
					"lengthMenu" : "每页 _MENU_ 条记录",
					"zeroRecords" : "没有找到记录",
					"info" : "第 _PAGE_ 页,共 _PAGES_ 页 (显示_END_ 条，共 _TOTAL_ 项)",
					"infoEmpty" : "无记录",
					"sSearch" : "在结果中查找：",
					"infoFiltered" : "(从 _MAX_ 条记录过滤)",
					"oPaginate" : {
						"sFirst" : "第一页",
						"sPrevious" : "上一页",
						"sNext" : "下一页",
						"sLast" : "最后一页"
					},
				},
				"ajax" : baseUrl+ "/User/getPlatformUserinfo.html",
				"deferRender" : true,
				"columns" : [
					{"data" : "serialNumber"},
					{"data" : "str_user_Name"},
					{"data" : "str_name"},
					{"data" : "org_Name"},
					{"data" : "operate"}
					],
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false
				} ]
		});
}

function show(i_id) {
	window.location.href = baseUrl+"/User/platformShow.html?i_id="+i_id;
}

function del(i_id) {
	$.ajax({
		type : "POST",
		url : baseUrl + "/User/delPlatform.html",
		data : {"i_id":i_id},
		async:false,
		dataType : "json",
		success : function(data) {
			alert(data.message);
		}
	});
	window.location.href = baseUrl+"/User/platformUserinfo.html";
}