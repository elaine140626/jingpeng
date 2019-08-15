$(function() {
	getList();
});

// 拌合站名称获取
function getSelect(){
	$.ajax({
		type : "POST",
		url : "../../PlatformCementData/getOrgId.action",
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

// 生产统计dataTable
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
            url: "../../PlatformCementWarningDeviation/getPlatformCementWarningDeviation.action",
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
            "data" : "min_MixTime"
        }, {
            "data" : "aggregate",
        	render : function(data, type, row, meta) {
				return row.aggregate_DeviationDown + "~"
						+ row.aggregate_Deviation;
			}
        }, {
            "data" : "cement",
        	render : function(data, type, row, meta) {
				return row.cement_DeviationDown + "~"
						+ row.cement_Deviation;
			}
        }, {
            "data" : "water",
        	render : function(data, type, row, meta) {
				return row.water_DeviationDown + "~"
						+ row.water_Deviation;
			}
        }, {
            "data" : "admixture",
        	render : function(data, type, row, meta) {
        		return row.admixture_DeviationDown + "~"
				+ row.admixture_Deviation;
			}
        }],
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
	//拌和时间最小值：
	var Min_MixTime = ($("#Min_MixTime").val()!= '')?parseFloat($("#Min_MixTime").val()):$("#Min_MixTime").val();
	//骨料仓(%)
	var Aggregate_Deviation = ($("#Aggregate_Deviation").val()!= '')?parseFloat($("#Aggregate_Deviation").val()):$("#Aggregate_Deviation").val();
	//骨料仓(%)Down
	var Aggregate_DeviationDown = ($("#Aggregate_DeviationDown").val()!= '')?parseFloat($("#Aggregate_DeviationDown").val()):$("#Aggregate_DeviationDown").val();
	//水泥仓(%)
	var Cement_Deviation = ($("#Cement_Deviation").val()!= '')?parseFloat($("#Cement_Deviation").val()):$("#Cement_Deviation").val();
	//水泥仓(%)Down
	var Cement_DeviationDown = ($("#Cement_DeviationDown").val()!= '')?parseFloat($("#Cement_DeviationDown").val()):$("#Cement_DeviationDown").val();
	//水仓(%)
	var Water_Deviation = ($("#Water_Deviation").val()!= '')?parseFloat($("#Water_Deviation").val()):$("#Water_Deviation").val();
	//水仓(%)Down
	var Water_DeviationDown = ($("#Water_DeviationDown").val()!= '')?parseFloat($("#Water_DeviationDown").val()):$("#Water_DeviationDown").val();
	//外掺挤仓(%)
	var Admixture_Deviation = ($("#Admixture_Deviation").val()!= '')?parseFloat($("#Admixture_Deviation").val()):$("#Admixture_Deviation").val();
	//外掺挤仓(%)Down
	var Admixture_DeviationDown = ($("#Admixture_DeviationDown").val()!= '')?parseFloat($("#Admixture_DeviationDown").val()):$("#Admixture_DeviationDown").val();
	var params = $('#addForm').serialize();
	console.log(params);
	if(Org_ID == null || Org_ID == "") {
		swal("操作失败","拌合站名称不能为空!", "info");
		return;
	}
	if(Min_MixTime == null || Min_MixTime == "") {
		swal("操作失败","拌和时间最小值(s)不能为空!", "info");
		return;
	}
	if(Min_MixTime > 9999){
		swal("操作失败","拌和时间最小值(s)最多入力4位整数!", "info");
		return;
	}
	if(Aggregate_Deviation == null || Aggregate_Deviation == "") {
		swal("操作失败","骨料仓上限(%)不能为空!", "info");
		return;
	}
	if(Aggregate_Deviation > 9999.99){
		swal("操作失败","骨料仓上限(%)最多入力4位整数!", "info");
		return;
	}
	if(Aggregate_DeviationDown == null || Aggregate_DeviationDown == "") {
		swal("操作失败","骨料仓下限(%)不能为空!", "info");
		return;
	}
	if(Aggregate_DeviationDown > 9999.99){
		swal("操作失败","骨料仓下限(%)最多入力4位整数!", "info");
		return;
	}
	if(Aggregate_DeviationDown > Aggregate_Deviation){
		swal("操作失败","骨料仓下限(%)超过骨料仓上限(%)!", "info");
		return;
	}
	if(Cement_Deviation == null || Cement_Deviation == "") {
		swal("操作失败","水泥仓上限(%)不能为空!", "info");
		return;
	}
	if(Cement_Deviation > 9999.99){
		swal("操作失败","水泥仓上限(%)最多入力4位整数!", "info");
		return;
	}
	if(Cement_DeviationDown == null || Cement_DeviationDown == "") {
		swal("操作失败","水泥仓下限(%)不能为空!", "info");
		return;
	}
	if(Cement_DeviationDown > 9999.99){
		swal("操作失败","水泥仓下限(%)最多入力4位整数!", "info");
		return;
	}
	if(Cement_DeviationDown > Cement_Deviation){
		swal("操作失败","水泥仓下限(%)超过水泥仓上限(%)!", "info");
		return;
	}
	if(Water_Deviation == null || Water_Deviation == "") {
		swal("操作失败","水仓上限(%)不能为空!", "info");
		return;
	}
	if(Water_Deviation > 9999.99){
		swal("操作失败","水仓上限(%)最多入力4位整数!", "info");
		return;
	}
	if(Water_DeviationDown == null || Water_DeviationDown == "") {
		swal("操作失败","水仓下限(%)不能为空!", "info");
		return;
	}
	if(Water_DeviationDown > 9999.99){
		swal("操作失败","水仓下限(%)最多入力4位整数!", "info");
		return;
	}
	if(Water_DeviationDown > Water_Deviation){
		swal("操作失败","水仓下限(%)超过水仓上限(%)!", "info");
		return;
	}
	if(Admixture_Deviation == null || Admixture_Deviation == "") {
		swal("操作失败","外掺挤仓上限(%)不能为空!", "info");
		return;
	}
	if(Admixture_Deviation > 9999.99){
		swal("操作失败","外掺挤仓上限(%)最多入力4位整数!", "info");
		return;
	}
	if(Admixture_DeviationDown == null || Admixture_DeviationDown == "") {
		swal("操作失败","外掺挤仓下限(%)不能为空!", "info");
		return;
	}
	if(Admixture_DeviationDown > 9999.99){
		swal("操作失败","外掺挤仓下限(%)最多入力4位整数!", "info");
		return;
	}
	if(Admixture_DeviationDown > Admixture_Deviation){
		swal("操作失败","外掺挤仓下限(%)超过外掺挤仓上限(%)!", "info");
		return;
	}
	
	$.ajax({
		type : "POST",
		url :  "../../PlatformCementWarningDeviation/insertPlatformCementWarningDeviation.action",
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

//只能入力数字和小数点   //和负数 
function keepNumOrDecimal(obj){ 
//    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
//	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
//    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
//    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
//    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入一个小数  
	
	regu = /^(\-|\+)?\d+(\.\d+)?$/;
	
	if (obj.value != '' && !regu.test(obj.value)) {
		swal("操作失败","请输入正确的数字格式", "error");
		obj.value = '';
    }
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

////只能入力数字和小数点
function keepNumber(obj) {
	regu = /^(\-|\+)?\d+(\d+)?$/;
	if (obj.value != '' && !regu.test(obj.value)) {
		swal("操作失败","请输入正确的数字格式", "error");
		obj.value = '';
    }
//  obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”和“.”以外的字符  
  if (obj.value.charAt(obj.value.length - 1)=='.'){
		obj.value = obj.value.substring(0,obj.value.length - 1);
	}	
}