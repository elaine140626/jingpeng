var param = {};
var name = "";
$(function() {
	$.ajax({
        type: "post",
        url: "../../UserInfo/getPtUserInfo.action",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	//获取当前登录人做显示
        	name = data.name;
        }
    });
	getList();
	$('#asphalt_search').hide()
});

// 跳转添加配比
function getSelect(){
	$.ajax({
		type : "POST",
		url : "../../PlatformAsphaltData/getOrgId.action",
		async:false,
		data : {},
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'>请选择</option>";
			for (var i = 0; i < data.length; i++) {
				if (data[i].org_Type == '2'){
					html += "<option value='" + data[i].Id + "'>"
					+ data[i].Org_Name + "</option>"
				}			
			}
			$("#Org_Id").empty();
			$("#Org_Id").html(html);
		}
	});
	$("#loginModal").modal('show');
	$(".btn_bc").css("display", "");
	$("#Org_Id").val("");
	$("#Material_Name").val("");
	$("#Material_Model").val("");
	$("#mix_Number").val("");
	$("#remarks").val("");
	$("#str_material_1").val("");
	$("#str_material_2").val("");
	$("#str_material_3").val("");
	$("#str_material_4").val("");
	$("#str_material_5").val("");
	$("#str_material_6").val("");
	$("#str_material_7").val("");
	$("#str_material_8").val("");
	$("#str_material_9").val("");
	$("#str_material_10").val("");
	$("#str_material_11").val("");
	$("#str_material_12").val("");
	param.id = "";
}

function getList(){
	var table = $('#Deviation_Asphalt').dataTable();
	table.fnDestroy();
	$('#Deviation_Asphalt').DataTable({
        "paging" : true,
        "lengthChange" : false,
        "bLengthChange": false,
        "pageLength" : 14,
        "searching" : false,
        "ordering" : false,
        "info" : true,
        "sInfo" : true,
        "autoWidth" : false,
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
            url: "../../PlatformAsphaltMixProportion/getList.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [ {
            "data" : "id"
        }, {
            "data" : "org_Id",
             render : function(data, type, row, meta) {
                 	var html = '';
                 	var date = new Date(row.modify_Date);
                 	var modify_Date = date.getTime();
     	  		    html += "<a onclick=update("+row.id+",'"+modify_Date+"')>修改&nbsp;</a>";
     	  		    html +=	"<a onclick=del("+row.id+","+modify_Date+")>删除&nbsp;</a>";
     	  		    html += "<a onclick=enable("+row.id+","+row.org_Id+")>启用</a>";
     	  		    return html;
             			
             }
        }, {
            "data" : "org_Name"
           
        }, {
            "data" : "mix_Number",
            render : function(data, type, row, meta) {
         	   html ="<span><a onclick='detailed("+row.id+")'>"+data+"</a></span>";
                return html;
 		}
        }, {
            "data" : "material_Name",
            render : function(data, type, row, meta) {
        			return  row.material_Name +"/"+row.material_Model;
        			
		}
        }, {
            "data" : "isEnable",
            render : function(data, type, row, meta) {
        		if(row.isEnable  == 0){
        			return "未启用";
        		}else{
        			return "启用";
        		}
        			
			}
        }, {
            "data" : "modify_Date",
        }, {
            "data" : "operator",
        }, {
            "data" : "create_Date",
        }, {
            "data" : "remarks",
        }
        ],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一
           return nRow;
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        }]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#Deviation_Asphalt_info').css('text-align','center');
}

//保存
function addss(){
	//拌合站名称：
	var params = $('#addForm').serialize();
	params =params +"&"+"operator="+name+""
	var str_2 = $("#str_material_2").val()*1;
	var str_3 = $("#str_material_3").val()*1;
	var str_4 = $("#str_material_4").val()*1;
	var str_5 = $("#str_material_5").val()*1;
	var str_6 = $("#str_material_6").val()*1;
	var str_7 = $("#str_material_7").val()*1;
	var str_8 = $("#str_material_8").val()*1;
	var str_9 = $("#str_material_9").val()*1;
	var str_10 = $("#str_material_10").val()*1;
	var res = str_2 + str_3+str_4+str_5+str_6+str_7+str_8+str_9+str_10
	if($("#Org_Id").val() == ''){
		swal("填写有误","拌合站必选", "error");
		return
	}
	if($("#Material_Name").val() == ''){
		swal("填写有误","混合料名称必填", "error");
		return
	}
	if($("#Material_Model").val() == ''){
		swal("填写有误","混合料型号必填", "error");
		return
	}
	if($("#mix_Number").val() == ''){
		swal("填写有误","生产配合比编号必填", "error");
		return
	}
	if(res != 100){
		swal("填写有误","骨料与粉料的和没有达到100", "error");
		return
	}
	if(param.id == ""){
	$.ajax({
		type : "POST",
		url :  "../../PlatformAsphaltMixProportion/insertPlatformAsphaltMixProportion.action",
		data : params,
		dataType : "json",
		success : function(data) {			
			if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					$('#loginModal').modal('hide');
					getList();
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});	
	}else{
	params =params +"&"+"id="+param.id+""
	$.ajax({
		type : "POST",
		url :  "../../PlatformAsphaltMixProportion/updateApshaltMixProportion.action",
		data : params,
		dataType : "json",
		success : function(data) {			
			if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					$('#loginModal').modal('hide');
					getList();
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});	
	}
}
function enable(id,org_Id){
	param.id = id;
	param.org_Id = org_Id;
	$.ajax({
		type : "POST",
		url :  "../../PlatformAsphaltMixProportion/update.action",
		data : param,
		dataType : "json",
		success : function(data) {			
			if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					getList();
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});	
}
function detailed(id){
	getSelect();
	param.id = id;
	$.ajax({
		type : "POST",
		url :  "../../PlatformAsphaltMixProportion/getList.action",
		data : param,
		dataType : "json",
		success : function(data) {	
			data = data.data[0];
			$("#Org_Id").val(data.org_Id);
			$("#Material_Name").val(data.material_Name);
			$("#Material_Model").val(data.material_Model);
			$("#mix_Number").val(data.mix_Number);
			$("#remarks").val(data.remarks);
			$("#str_material_1").val(data.str_material_1);
			$("#str_material_2").val(data.str_material_2);
			$("#str_material_3").val(data.str_material_3);
			$("#str_material_4").val(data.str_material_4);
			$("#str_material_5").val(data.str_material_5);
			$("#str_material_6").val(data.str_material_6);
			$("#str_material_7").val(data.str_material_7);
			$("#str_material_8").val(data.str_material_8);
			$("#str_material_9").val(data.str_material_9);
			$("#str_material_10").val(data.str_material_10);
			$("#str_material_11").val(data.str_material_11);
			$("#str_material_12").val(data.str_material_12);
			$(".btn_bc").css("display", "none");
		}
	});
}
function update(id,modify_Date){
	if(modify_Date != "" && modify_Date !=null && modify_Date !=0){
		swal("操作失败","该配比启用过无法修改", "error");
		return;
	}
	getSelect();
	param.id = id;
	$.ajax({
		type : "POST",
		url :  "../../PlatformAsphaltMixProportion/getList.action",
		data : param,
		dataType : "json",
		success : function(data) {	
			data = data.data[0];
			$("#Org_Id").val(data.org_Id);
			$("#Material_Name").val(data.material_Name);
			$("#Material_Model").val(data.material_Model);
			$("#mix_Number").val(data.mix_Number);
			$("#remarks").val(data.remarks);
			$("#str_material_1").val(data.str_material_1);
			$("#str_material_2").val(data.str_material_2);
			$("#str_material_3").val(data.str_material_3);
			$("#str_material_4").val(data.str_material_4);
			$("#str_material_5").val(data.str_material_5);
			$("#str_material_6").val(data.str_material_6);
			$("#str_material_7").val(data.str_material_7);
			$("#str_material_8").val(data.str_material_8);
			$("#str_material_9").val(data.str_material_9);
			$("#str_material_10").val(data.str_material_10);
			$("#str_material_11").val(data.str_material_11);
			$("#str_material_12").val(data.str_material_12);
		}
	});
}
function del(id,modify_Date){
	if(modify_Date != "" && modify_Date !=null && modify_Date !=0){
		swal("操作失败","该配比启用过无法删除", "error");
		return;
	}
	swal({
		title: "确定操作吗？",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
		
	},function(){
	param.id = id;
	$.ajax({
		type : "POST",
		url :  "../../PlatformAsphaltMixProportion/del.action",
		data : param,
		dataType : "json",
		success : function(data) {	
			if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					getList();
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
	});
}
function checkPoint(obj){
	if(obj.value>100 || obj.value<0){
		swal("填写有误","只能输入0-100的数字", "error");
		obj.value = ""
		return;
	}
}