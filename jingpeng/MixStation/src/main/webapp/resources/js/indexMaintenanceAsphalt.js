$(function () {
	$.ajax({
        type: "post",
        url: "../testUserInfo/getTestUserName.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
        	//获取当前登录人做显示
        	$(".userid").html(data.userName);
        }
    });
	getList();
	
});

function getList() {
    $('#asphalt').DataTable({
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
            url: "../BlindSample/getAsphalt.html",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "AsphaltType"
        }, {
            "data" : "PenetLimit"
        }, {
            "data" : "SoftenPoint"
        }, {
            "data" : "delete"
        }  ],
        "createdRow" : function(row, data, dataIndex) {
//        	console.log(row);
//        	console.log(data);
//        	console.log(dataIndex);

        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$('.lq_biao .row:first-child').css('display','none');
	$('#asphalt_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

//保存
function add(){
	//沥青种类
	var asphalttype = $("#asphalttype").val();
	if(asphalttype == null || asphalttype == "") {
		swal("操作失败","沥青种类不能为空!", "info");
		return;
	}
	//针入度下限
	var penetlowerlimit = $("#penetlowerlimit").val();
	if(penetlowerlimit == null || penetlowerlimit == "") {
		swal("操作失败","针入度下限不能为空!", "info");
		return;
	}
	if((penetlowerlimit.indexOf(".") == -1 && penetlowerlimit.length>38)){
		swal("操作失败","针入度下限最多入力38位整数!", "info");
		return;
	}
	//针入度上限
	var penetupperlimit = $("#penetupperlimit").val();
	if(penetupperlimit == null || penetupperlimit == "") {
		swal("操作失败","针入度上限不能为空!", "info");
		return;
	}
	if((penetupperlimit.indexOf(".") == -1 && penetupperlimit.length>38)){
		swal("操作失败","针入度上限最多入力38位整数!", "info");
		return;
	}	
	//软化点
	var softenpoint = $("#softenpoint").val();
	if(softenpoint == null || softenpoint == "") {
		swal("操作失败","软化点不能为空!", "info");
		return;
	}
	if((softenpoint.indexOf(".") == -1 && softenpoint.length>38)){
		swal("操作失败","软化点最多入力38位整数!", "info");
		return;
	}	
	
	var params = $('#addForm').serialize();
	console.log(params);
	$.ajax({
		type : "POST",
		url :  "../BlindSample/addAsphalt.html",
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
					location.reload();
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
	
}

//只能入力数字和小数点  
function keepNumOrDecimal(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
//    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入两个小数  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    } 
} 

//最后一位不是小数点
function checkPoint(obj){
	keepNumOrDecimal(obj);
	if (obj.value.charAt(obj.value.length - 1)=='.'){
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}		
}

//删除
function del(id){
	swal({
		title: "确定操作吗？",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
		
	},function(){
    $.ajax({
        type: "post",
        async: false,
        contentType: 'application/json;charset=UTF-8',
        url: "../BlindSample/delCementMortarStrength.html",
        data: JSON.stringify({"id": id,"flg":"2"}),
        dataType: "json",
        success: function (data) {
        	console.log(data);
            if(data.code=="success"){
            	setTimeout(function(){swal({
					title: data.message,
					type: "success",
					cancelButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					location.reload();
				}); },200);
				
			}else{
				swal("操作失败",data.message, "error");
			}
            /*location.reload();*/
        }
    });
  });	
}

//tab跳转
function goto(url) {
	window.location.href=url

}

 
