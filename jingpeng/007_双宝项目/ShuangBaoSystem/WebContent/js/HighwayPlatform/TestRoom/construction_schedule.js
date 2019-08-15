//权限对应该用户权限标段下的标段信息
var construction;
//施工单位
var constructionOrg = [];
// 标段桥梁信息
var constructionDetalis = []
var constructionDetalisSmall = {
		name: '',
		testroomname: '',
		bridge: []
};
var uploadImgId = '';
$('.rightcon ').hide();
$(function() {
	// 权限对应该用户权限标段下的标段信息
	$.ajax({
		type : "POST",
		url : "../../ConstructionProgress/getConstructionList.action",
		async : false,
		dataType : "json",
		success : function(data) {
			construction = data;
			$.each(construction,function(i,n) {
				if(constructionOrg.indexOf(n.testroomname)==-1){
					constructionDetalisSmall = {};
					constructionOrg.push(n.testroomname);
					constructionDetalisSmall.name = n.uniqueidentifier;
					constructionDetalisSmall.testroomname = n.testroomname;
					constructionDetalisSmall.bridge = [];
					constructionDetalis.push(constructionDetalisSmall);
			     }
				$.each(constructionDetalis,function(y,u) {
					if( u.name.indexOf(n.uniqueidentifier) != -1 ) {
						u.bridge.push(n)
					}
				})
			})
			//施工单位赋值（施工进度页和新增进度页面共用）
			selectionDown(constructionDetalis)
		}
	});
	
	// 初始化数据
	init();
});

// 初始化画面list
function init(){
	var param = {};
	param.UniqueIdentifier = $("#constructionOrgs").val();
	param.startTime = $("#startTime").val();
	param.endTime = $("#endTime").val();
	
	//获取列表 刷新
	var table = $('#list').dataTable();
	table.fnDestroy();
	// 试验基本信息
	$('#list').DataTable({
		"paging" : true,
		"lengthChange" : false,
		"pageLength" : 12,
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
			url : "../../ConstructionProgress/getActualCompletionQuantity.action",
			dataSrc : "data",
			async : false,
			data : function(d) {
				return param;//自定义需要传递的参数。
			}
		},
		"deferRender" : true,
		"columns" : [ {
			"data" : "id"
		}, {
			"data" : "id",
			render: function(data, type, row) {
				var html = '';
    	  		html +=	"<a style='margin-right:5%' onclick=select("+row.id+")>查看</a>";
    	  		html +=	"<a style='margin-right:5%' onclick=update("+row.id+")>修改</a>";
    	  		html +=	"<a style='margin-right:5%' onclick=del("+row.id+")>删除</a>";
    	  		if(row.picturePath == '' || row.picturePath == null) {
    	  			html +=	"<a style='margin-right:5%' onclick=upload("+row.id+")>上传图片</a>";
    	  		}
            	return html;
			}
		}, {
			"data" : "testRoomName"
		}, {
			"data" : "creater"
		}, {
			"data" : "createrDate"
		},{
			"data" : "picturePath",
			render: function(data, type, row) {
				var html = '';
				if(data != null && data != ''){
					html = "是";
				}else{
					html = "否";
				}
    			return html;	
            }
		}],
		"createdRow" : function(row, data, dataIndex) {
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		}],
		"fnRowCallback" : function(nRow, aData, iDisplayIndex){
            $("td:first", nRow).html((iDisplayIndex +1));//设置序号位于第一列，并顺次加一          
            return nRow;
        }
	});
}

// 查询方法
function search(){
	// 初始化数据
	init();
}

//工程设计量弹窗
function show1() {
	$("#Modal1").modal('show');
	if($("#uniqueIdentifier").val() != null && $("#uniqueIdentifier").val() != ""){
		$.ajax({
			type : "POST",
			url : "../../ConstructionProgress/getEngineeringDesignContent.action",
			data:{"UniqueIdentifier":$("#uniqueIdentifier").val()},
			dataType : "json",
			success : function(data) {
				if(data != null && data.length > 0){
					SetFromValues($("#addForm"), data[0]);
				}else{
					$("tr input").val("");
					$("tr td:last-child input").val("m³");
				}
			}
		});
	}
}

// 获取路基总量
function getSubgrade_Total(){
	// 路基的总量=路基的填方量+路基的挖方量
	var subgrade_Fill = $("#subgrade_Fill").val() * 1;
	var subgrade_Excavation = $("#subgrade_Excavation").val() * 1;
	$("#subgrade_Total").val(subgrade_Fill + subgrade_Excavation);
}

// 工程设计量保存
function save(){
	// 获取画面数据
	var param = formToJson($("#addForm"));
	if(param.uniqueIdentifier == null || param.uniqueIdentifier == ""){
		swal({
			title: "",
			text: "请选择施工单位!",
			type: "error",
			confirmButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		});
		return;
	}
	$.ajax({
		type : "POST",
		url : "../../ConstructionProgress/addEngineeringDesignContent.action",
		data:JSON.stringify(param),
		contentType : 'application/json;charset=UTF-8',
		dataType : "json",
		success : function(data) {
			if(data.code == "success"){
				swal({
					title: data.message,
					text: "",
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				}, function() {
					$("#Modal1").modal('hide');
					// 初始化数据
					init();
				});
			}else{
				swal({
					title: data.message,
					text: "",
					type: data.code,
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				});
			}
		}
	});
}

// 工程设计量试验室更改
function changeUniqueIdentifier(){
	show1();
}

//新增进度数据
function show2() {
	$.ajax({
		type: "get",
		url: 'construction_schedule_add.html',
		dataType: 'html',
		success: function(data) {
			$('.rightcon_content').html(data);
		},
		error: function(xhr, ajaxOptions, thrownError) {
		}
	});
}

// 修改操作
function update(id){
	$.ajax({
		type: "post",
		url: 'construction_schedule_add.html',
		dataType: 'html',
		success: function(data) {
			$('.rightcon_content').html(data);
			sessionStorage.setItem("id",id);
			sessionStorage.setItem("flag",1);
		},
		error: function(xhr, ajaxOptions, thrownError) {
		}
	});
}

//修改操作
function select(id){
	$.ajax({
		type: "post",
		url: 'construction_schedule_add.html',
		dataType: 'html',
		success: function(data) {
			$('.rightcon_content').html(data);
			sessionStorage.setItem("id",id);
			sessionStorage.setItem("flag",0);
		},
		error: function(xhr, ajaxOptions, thrownError) {
		}
	});
}

// 删除操作
function del(id){
	
	swal({
		title: "确定要删除吗？",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	},function(){
		$.ajax({
			type : "POST",
			url : "../../ConstructionProgress/delInfo.action",
			data:{"id":id},
			dataType : "json",
			success : function(data) {
				// 刷新页面
				window.location.reload();
				/*if(data.code == "success"){
					swal({
						title: data.message,
						text: "",
						type: data.code,
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					}, function() {
						// 刷新页面
						window.location.reload();
					});
				}else{
					swal({
						title: data.message,
						text: "",
						type: data.code,
						confirmButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					});
				}*/
			}
		});
	});
}

//上传图片
function upload(id) {
	$("#Modal2").modal('show');
	uploadImgId = id;
}

//施工单位赋值（施工进度页和新增进度页面共用）
function selectionDown(parms) {
	var str = '';
	$.each(parms,function(i,n) {
		str += "<option value="+n.name+">"+ n.testroomname+"</option>"
	})
	$('#constructionOrgs,#uniqueIdentifier').append(str);
}

//上传图片
function uploadImg (){
	if($('#myBlogImage').val() != '') {
		$("#Modal2").modal('hide');
		$.ajaxFileUpload({  
			url:'../../fileUp/upload.action',
			secureuri:false,                           //是否启用安全提交,默认为false   
			fileElementId:'myBlogImage',   //文件选择框的id属性  
			data:{"iD":uploadImgId},		
			dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
			success:function(data){            //服务器响应成功时的处理函数  
				$('#myBlogImage').val("");
				if(data.flag == "1") {
					swal({
						title: "上传成功！",
						type: "success",
						confirmButtonText: "确定"
					}, function() {
						// 初始化数据
						init();
					});
				 }else {
					 swal({
						title: "上传失败！",
						type: "error",
						confirmButtonText: "确定"
					});
				 }
			},  
			error:function(data, status, e){ //服务器响应失败时的处理函数  
				swal({
					title: "上传失败！",
					icon: "error",
					confirmButtonText: "确定"
				});
			}  
	    });
	}else {
		swal({
			title: "请选择上传图片！",
			type: "error",
			confirmButtonText: "确定"
		});
	}
}


/**
 * 表单转json
 * @param form
 * @returns
 */
function formToJson(form) {
	var o = {};
	var a = form.serializeArray();
	$.each(a, function() {
		if (o[this.name] != undefined) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || "");
		} else {
			o[this.name] = this.value || "";
		}

	});
	return o;
}

//form表单赋值方法
function SetFromValues(el, data)
{
    for (var p in data)
    {
        el.find(":input[name='" + p + "']").val(data[p]);
    } 
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
