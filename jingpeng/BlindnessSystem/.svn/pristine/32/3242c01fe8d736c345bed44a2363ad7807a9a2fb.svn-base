var baseUrl = "";
var orgId;
$(function() {
	baseUrl = getContextPath();
	$.ajax({
        type: "post",
        url: "../BlindSampleInfo/getBlindSampleInfoValue",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	orgId = data.orgId;
        	Id = data.id;
        	$(".userid").html(data.name)
        }
    });
	getList(Id);
});

function getList(Id) {
	$('#li').DataTable(
			{
				"paging" : true,
				"lengthChange" : false,
				"pageLength" : 10,
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
				"ajax" : {
					type : "post",
					url : baseUrl+ "/TestUser/getUserinfo.html",
					dataSrc : "data",
					data : function(d) {
						var param = {};
						param.i_id = Id;
						param.orgId = orgId;
						return param;//自定义需要传递的参数。
					}
				},

				"deferRender" : true,
				"columns" : [
					{"data" : "serialNumber"},
					{"data" : "UserCode"},
					{"data" : "Name"},
					{"data" : "Org_Name"},
					{"data" : "details"}
					],
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false
				} ]
		});
	$('#li_wrapper .row:first-child').css('display','none');
}

function show(Id,orgId) {
	window.location.href = baseUrl+"/TestUser/usershow.html?id="+Id;
}

function del(id) {
	baseUrl = getContextPath();
	$.ajax({
        type: "post",
        url: "../BlindSampleInfo/getBlindSampleInfoValue",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        var orgId = data.orgId;
        }
    });
	
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/TestUser/delUserInfo.html",
		data : {"id":id,"orgId":orgId},
		async:false,
		dataType : "json",
		success : function(data) {
			alert(data.message);
			if(data.amount=="1"){
				window.location.href = baseUrl+"/TestUser/userinfo.html";
			}else{
				window.location.href = baseUrl+"/TestUser/login.html";
			}
		}
	});
}