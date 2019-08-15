$(function() {
	getList();
});

// 拌合站名称获取
function getSelect(){
	$.ajax({
		type : "POST",
		url : "../../platformCementStableData/getOrgId.action",
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
			$("#Org_ID").empty();
			$("#Org_ID").html(html);
		}
	});
	$("#loginModal").modal('show');
}

// 预警偏差设计dataTable
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
            url: "../../platformCementStableWarningDeviation/getPlatformCementStableWarningDeviation.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "org_Name"
        }, {
            "data" : "aggregate_Deviation"
        }, {
            "data" : "cement_Deviation"
        }, {
            "data" : "water_Deviation"
        }, {
            "data" : "admixture_Deviation"
        }],
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
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#Deviation_Asphalt_info').css('text-align','center');
}

//保存
function addss(){
	//拌合站名称：
	var Org_ID = $("#Org_ID").val();
	if(Org_ID == null || Org_ID == "") {
		swal("操作失败","拌合站名称不能为空!", "info");
		return;
	}
	//骨料仓(%)
	var aggregate_Deviation = $("#aggregate_Deviation").val();
	if(aggregate_Deviation == null || aggregate_Deviation == "") {
		swal("操作失败","骨料仓(%)不能为空!", "info");
		return;
	}
	if((aggregate_Deviation.indexOf(".") == -1 && aggregate_Deviation.length>4)){
		swal("操作失败","骨料仓(%)最多入力4位整数!", "info");
		return;
	}
	//水泥仓(%)
	var cement_Deviation = $("#cement_Deviation").val();
	if(cement_Deviation == null || cement_Deviation == "") {
		swal("操作失败","水泥仓(%)不能为空!", "info");
		return;
	}
	if((cement_Deviation.indexOf(".") == -1 && cement_Deviation.length>4)){
		swal("操作失败","水泥仓(%)最多入力4位整数!", "info");
		return;
	}
	//水仓(%)
	var water_Deviation = $("#water_Deviation").val();
	if(water_Deviation == null || water_Deviation == "") {
		swal("操作失败","水仓(%)不能为空!", "info");
		return;
	}
	if((water_Deviation.indexOf(".") == -1 && water_Deviation.length>4)){
		swal("操作失败","水仓(%)最多入力4位整数!", "info");
		return;
	}
	//外掺挤仓(%)
	var admixture_Deviation = $("#admixture_Deviation").val();
	if(admixture_Deviation == null || admixture_Deviation == "") {
		swal("操作失败","外掺挤仓(%)不能为空!", "info");
		return;
	}
	if((admixture_Deviation.indexOf(".") == -1 && admixture_Deviation.length>4)){
		swal("操作失败","外掺挤仓(%)最多入力4位整数!", "info");
		return;
	}
	var params = $('#addForm').serialize();
	console.log(params);
	$.ajax({
		type : "POST",
		url :  "../../platformCementStableWarningDeviation/insertPlatformCementStableWarningDeviation.action",
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
