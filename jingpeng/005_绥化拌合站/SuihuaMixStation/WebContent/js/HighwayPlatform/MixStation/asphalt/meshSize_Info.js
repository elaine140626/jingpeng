//当前用户
var username = "";
//0:新增  1:修改
var flag;
//级配id
var mId;

$(function(){
	//获取用户信息
	$.ajax({
        type: "post",
        url: "../../UserInfo/getPtUserInfo.action",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	//获取当前登录人做显示
        	username = data.name;
        }
	});
	
	//查询拌合站名称
	getAllOrgName();
	
	//查询所有筛孔信息
	getMeshSizeInfo();
	
});

//查询所有筛孔信息
function getMeshSizeInfo(){
	var table = $('#asphalt_meshSize').dataTable();
	table.fnDestroy();
	$('#asphalt_meshSize').DataTable({
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
            url: "../../meshSizeInfo/getAllMeshSizeInfo.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [ {
            "data" : "id"
        }, {
            "data" : "id",
             render : function(data, type, row, meta) {
                 	var html = '';
     	  		    	html += "<a onclick=updateMeshSize("+row.id+")>修改&nbsp;</a>";
     	  		    	html +=	"<a onclick=delMeshSize("+row.id+")>删除&nbsp;</a>";
     	  		    return html;
             			
             }
        }, {
            "data" : "orgName"
           
        }, {
            "data" : "meshSize"
        }, {
            "data" : "operator"
        }, {
            "data" : "createDate"
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
	$('#asphalt_gradId').css('text-align','center');
}

//跳转到新增页面
function getAddView(){
	flag = 0;
	//弹出页面
	$("#loginModal").modal("show");
	//清空操作
	$("#orgId").val("");
	$("#meshSize").val("");
}

//查询拌合站名称
function getAllOrgName(){
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
			$("#orgId").empty();
			$("#orgId").html(html);
		}
	});
}

//保存
function addMeshSize(){
	//获取页面数据
	var data = formToJson($("#addForm"));
	if(data.orgId == ""){
		swal("操作失败","拌合站名称不能为空!", "error");
		return;
	}
	if(data.meshSize == ""){
		swal("操作失败","筛孔不能为空!", "error");
		return;
	}
	//创建人
	data.operator = username;
	//0:新增  1:修改 
	data.flag = flag; 
	//修改级配的id
	data.id = mId;
	$.ajax({
		type : "POST",
		url : "../../meshSizeInfo/addMeshSizeInfo.action",
		data : data,
		async:false,
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
					//添加页面隐藏
					$('#loginModal').modal('hide');
					//查询所有筛孔信息
					getMeshSizeInfo();
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});	
}

//修改筛孔信息
function updateMeshSize(id){
	mId = id;
	flag = 1;
	//修改页面显示
	$('#loginModal').modal('show');
	//清空操作
	$("#orgId").val("");
	$("#meshSize").val("");
	$.ajax({
		type : "POST",
		url :  "../../meshSizeInfo/getAllMeshSizeInfo.action",
		data : {"id" : id},
		async:false,
		dataType : "json",
		success : function(data) {	
			//拌合站名称
			$("#orgId").val(data.data[0].orgId);
			//筛孔信息
			$("#meshSize").val(data.data[0].meshSize);
		}
	});
}

//删除筛孔信息
function delMeshSize(id){
	$.ajax({
		type: "post",
		url: '../../meshSizeInfo/getGradingInfo.action',
		async:false,
		data:{"meshId":id},
		dataType: "json",
		success: function (data) {
			if(data != null && data.length > 0){
				swal({
					title: "该筛孔已经生成级配，不可删除!",
					text: "",
					type: "error",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					//查询所有筛孔信息
					getMeshSizeInfo();
				});
			}else{
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
						url: '../../meshSizeInfo/deleteMeshSizeInfo.action',
						async:false,
						data:{"id":id},
						dataType: "json",
						success: function (data) {
							if(data.code=="success"){
								setTimeout(function(){swal({
									title: data.message,
									type: "success",
									cancelButtonText: '确定',
									cancelButtonFont: '微软雅黑',
								},
								function(){
									//查询所有筛孔信息
									getMeshSizeInfo();
								}); },200);
							}else{
								setTimeout(function(){swal({
									title: data.message,
									type: "error",
									cancelButtonText: '确定',
									cancelButtonFont: '微软雅黑',
								}); 
								});
							}
						}
					});
				});
			}
		}
	});
}

//数字decimal类型check(integer:整数位；decimal：小数位)(onkeyup)
function clearNoDecimal(obj,integer,decimal){  			
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的   
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
    // 小数位check
    if (decimal == 1){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入一个小数   
    }
    if (decimal == 2){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数   
    }	
    if (decimal == 3){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d).*$/,'$1$2.$3');//只能输入三个小数   
    }	
    if (decimal == 4){
    	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/,'$1$2.$3');//只能输入四个小数   
    }	
    if (obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额  
    	// 整数位check
    	if(obj.value.length > integer){
	    	obj.value = obj.value.substring(0,integer);
	    }else{
	    	obj.value= parseFloat(obj.value);
	    } 
    }  
}

// 失去焦点时check最后一位不是小数点(onblur)
function checkPoint(obj){
	clearNoDecimal(obj);
	if (obj.value.charAt(obj.value.length - 1)=='.'){			
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}		
}