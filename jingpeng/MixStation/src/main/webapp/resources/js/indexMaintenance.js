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
	
	// 水泥品种(默认显示权限下所有水泥品种) 
	$.ajax({
		type : "POST",
		url : "../BlindSample/getCementTypeList.html",
		dataType : "json",
		data : {},
		success : function(data) { //请求成功后处理函数。  
			for (var i = 0; i < data.length; i++) {
				console.log(data[i].CementType);
				if (data[i].CementType != null && data[i].CementType != '') {
					$('#list').append(
						"<option value='" + data[i].CementType + "'>");
				}
			}
		}
	});
	
});

//datatable数据获取
function getList() {
    $('#cementMortarStrength').DataTable({
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
            url: "../BlindSample/getCementMortarStrength.html",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "CementType"
        }, {
            "data" : "CementStrengthGrade"
        }, {
            "data" : "CompTrength3"
        }, {
            "data" : "CompTrength28"
        }, {
            "data" : "RuptureStrength3"
        }, {
            "data" : "RuptureStrength28"
        }, {
            "data" : "delete"
        }  ],
        "createdRow" : function(row, data, dataIndex) {
//        	console.log(row);
//        	console.log(data);
//        	console.log(dataIndex);

        },
        "columnDefs" : [ {
//            "targets" : [ 0 ],
//            "visible" : true,
//            "searchable" : false
        	targets: [0,1], //要合并的列数（第1，2，3列）
            createdCell: function (td, cellData, rowData, row, col) { //重要的操作可以合并列的代码
                var rowspan = rowData.merge;//这里主要是利用了模拟数据中的merge来控制
                if (rowspan > 1) {
                    $(td).attr('rowspan', rowspan)
                }
                if (rowspan == 0) {
                    $(td).remove();
                }
            }
        

        } ]
    });
	$('.lq_biao .row:first-child').css('display','none');
	$('#cementMortarStrength_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

//保存
function add(){
	//水泥品种
	var cementtype = $("#cementtype").val();
	if(cementtype == null || cementtype == "") {
		swal("操作失败","水泥品种不能为空!", "info");
		return;
	}
	//强度等级
	var cementstrengthgrade = $("#cementstrengthgrade").val();
	if(cementstrengthgrade == null || cementstrengthgrade == "") {
		swal("操作失败","强度等级不能为空!", "info");
		return;
	}
	//抗压强度3d
	var comptrength3 = $("#comptrength3").val();
	if(comptrength3 == null || comptrength3 == "") {
		swal("操作失败","抗压强度3d不能为空!", "info");
		return;
	}
	if((comptrength3.indexOf(".") == -1 && comptrength3.length>14)){
		swal("操作失败","抗压强度3d最多入力14位整数!", "info");
		return;
	}
	//抗压强度28d
	var comptrength28 = $("#comptrength28").val();
	if(comptrength28 == null || comptrength28 == "") {
		swal("操作失败","抗压强度28d不能为空!", "info");
		return;
	}
	if((comptrength28.indexOf(".") == -1 && comptrength28.length>14)){
		swal("操作失败","抗压强度28d最多入力14位整数!", "info");
		return;
	}
	//抗折强度3d
	var rupturestrength3 = $("#rupturestrength3").val();
	if(rupturestrength3 == null || rupturestrength3 == "") {
		swal("操作失败","抗折强度3d不能为空!", "info");
		return;
	}
	if((rupturestrength3.indexOf(".") == -1 && rupturestrength3.length>14)){
		swal("操作失败","抗折强度3d最多入力14位整数!", "info");
		return;
	}
	//抗折强度28d
	var rupturestrength28 = $("#rupturestrength28").val();
	if(rupturestrength28 == null || rupturestrength28 == "") {
		swal("操作失败","抗折强度28d不能为空!", "info");
		return;
	}
	if((rupturestrength28.indexOf(".") == -1 && rupturestrength28.length>14)){
		swal("操作失败","抗折强度28d最多入力14位整数!", "info");
		return;
	}
	
	var params = $('#addForm').serialize();
	console.log(params);
	$.ajax({
		type : "POST",
		url :  "../BlindSample/addCementMortarStrength.html",
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
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入一个小数  
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
        data: JSON.stringify({"id": id,"flg":"1"}),
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

 