$(function() {
	getList();
});

// 拌合站名称获取
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
            url: "../../PlatformAsphaltWarningDeviation/getPlatformAsphaltWarningDeviation.action",
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
            "data" : "Aggregate",
        	render : function(data, type, row, meta) {
				return row.dev_AggregateDown + "~"
						+ row.dev_Aggregate;
			}
        }, {
            "data" : "Powder",
        	render : function(data, type, row, meta) {
				return row.dev_PowderDown + "~"
						+ row.dev_Powder;
			}
        }, {
            "data" : "Admixture",
        	render : function(data, type, row, meta) {
				return row.dev_AdmixtureDown + "~"
						+ row.dev_Admixture;
			}
        }, {
            "data" : "Asphalt",
        	render : function(data, type, row, meta) {
				return row.dev_AsphaltDown + "~"
						+ row.dev_Asphalt;
			}
        }, {
            "data" : "material",
        }, {
            "data" : "mixTemperature",
        	render : function(data, type, row, meta) {
				return row.mixTemperatureDown + "~"
						+ row.mixTemperatureUp;
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
	//骨料仓(%)
	var Dev_Aggregate = ($("#Dev_Aggregate").val()!= '')?parseFloat($("#Dev_Aggregate").val()):$("#Dev_Aggregate").val();
	//骨料仓(%)Down
	var Dev_AggregateDown = ($("#Dev_AggregateDown").val()!= '')?parseFloat($("#Dev_AggregateDown").val()):$("#Dev_AggregateDown").val();
	//粉料仓(%)
	var Dev_Powder = ($("#Dev_Powder").val()!= '')?parseFloat($("#Dev_Powder").val()):$("#Dev_Powder").val();
	//粉料仓(%)Down
	var Dev_PowderDown = ($("#Dev_PowderDown").val()!= '')?parseFloat($("#Dev_PowderDown").val()):$("#Dev_PowderDown").val();
	//沥青仓(%)
	var Dev_Asphalt = ($("#Dev_Asphalt").val()!= '')?parseFloat($("#Dev_Asphalt").val()):$("#Dev_Asphalt").val();
	//沥青仓(%)Down
	var Dev_AsphaltDown = ($("#Dev_AsphaltDown").val()!= '')?parseFloat($("#Dev_AsphaltDown").val()):$("#Dev_AsphaltDown").val();
	//外掺挤仓(%)
	var Dev_Admixture = ($("#Dev_Admixture").val()!= '')?parseFloat($("#Dev_Admixture").val()):$("#Dev_Admixture").val();
	//外掺挤仓(%)Down
	var Dev_AdmixtureDown = ($("#Dev_AdmixtureDown").val()!= '')?parseFloat($("#Dev_AdmixtureDown").val()):$("#Dev_AdmixtureDown").val();
	//拌合站名称：
	var Product_ID = $("#Product_ID").val();
	//混合料温度上限(℃)
	var MixTemperatureUp = ($("#MixTemperatureUp").val()!= '')?parseFloat($("#MixTemperatureUp").val()):$("#MixTemperatureUp").val();
	//混合料温度下限(℃)
	var MixTemperatureDown = ($("#MixTemperatureDown").val()!= '')?parseFloat($("#MixTemperatureDown").val()):$("#MixTemperatureDown").val();
	
	if(Org_ID == null || Org_ID == "") {
		swal("操作失败","拌合站名称不能为空!", "info");
		return;
	}
	if(Dev_Aggregate == null || Dev_Aggregate == "") {
		swal("操作失败","骨料仓上限(%)不能为空!", "info");
		return;
	}
	/*if((Dev_Aggregate.indexOf(".") == -1 && Dev_Aggregate.length>4)){
		swal("操作失败","骨料仓(%)最多入力4位整数!", "info");
		return;
	}*/
	if(Dev_Aggregate > 9999.99){
		swal("操作失败","骨料仓上限(%)最多入力4位整数!", "info");
		return;
	}
	if(Dev_AggregateDown == null || Dev_AggregateDown == "") {
		swal("操作失败","骨料仓下限(%)不能为空!", "info");
		return;
	}
	if(Dev_AggregateDown > 9999.99){
		swal("操作失败","骨料仓下限(%)最多入力4位整数!", "info");
		return;
	}
	
	if(Dev_AggregateDown > Dev_Aggregate){
		swal("操作失败","骨料仓下限(%)超过骨料仓上限(%)!", "info");
		return;
	}
	
	
	if(Dev_Powder == null || Dev_Powder == "") {
		swal("操作失败","粉料仓上限(%)不能为空!", "info");
		return;
	}
	if(Dev_Powder > 9999.99){
		swal("操作失败","粉料仓上限(%)最多入力4位整数!", "info");
		return;
	}
	
	if(Dev_PowderDown == null || Dev_PowderDown == "") {
		swal("操作失败","粉料仓下限(%)不能为空!", "info");
		return;
	}
	if(Dev_PowderDown > 9999.99){
		swal("操作失败","粉料仓下限(%)最多入力4位整数!", "info");
		return;
	}
	
	if(Dev_PowderDown > Dev_Powder){
		swal("操作失败","粉料仓下限(%)超过粉料仓上限(%)!", "info");
		return;
	}
	
	
	if(Dev_Asphalt == null || Dev_Asphalt == "") {
		swal("操作失败","沥青仓上限(%)不能为空!", "info");
		return;
	}
	if(Dev_Asphalt > 9999.99){
		swal("操作失败","沥青仓上限(%)最多入力4位整数!", "info");
		return;
	}
	
	if(Dev_AsphaltDown == null || Dev_AsphaltDown == "") {
		swal("操作失败","沥青仓下限(%)不能为空!", "info");
		return;
	}
	if(Dev_AsphaltDown > 9999.99){
		swal("操作失败","沥青仓下限(%)最多入力4位整数!", "info");
		return;
	}
	
	if(Dev_AsphaltDown > Dev_Asphalt){
		swal("操作失败","沥青仓下限(%)超过沥青仓上限(%)!", "info");
		return;
	}
	
	
	if(Dev_Admixture == null || Dev_Admixture == "") {
		swal("操作失败","外掺挤仓上限(%)不能为空!", "info");
		return;
	}
	if(Dev_Admixture > 9999.99){
		swal("操作失败","外掺挤仓上限(%)最多入力4位整数!", "info");
		return;
	}
	
	if(Dev_AdmixtureDown == null || Dev_AdmixtureDown == "") {
		swal("操作失败","外掺挤仓下限(%)不能为空!", "info");
		return;
	}
	if(Dev_AdmixtureDown > 9999.99){
		swal("操作失败","外掺挤仓下限(%)最多入力4位整数!", "info");
		return;
	}
	
	if(Dev_AdmixtureDown > Dev_Admixture){
		swal("操作失败","外掺仓下限(%)超过外掺仓上限(%)!", "info");
		return;
	}
	if(Product_ID == null || Product_ID == "") {
		swal("操作失败","混合料名称/型号不能为空!", "info");
		return;
	}
	
	if(MixTemperatureUp == null || MixTemperatureUp == "") {
		swal("操作失败","混合料温度上限(℃)不能为空!", "info");
		return;
	}
	if(MixTemperatureUp > 9999.99){
		swal("操作失败","混合料温度上限(℃)最多入力4位整数!", "info");
		return;
	}
	
	if(MixTemperatureDown == null || MixTemperatureDown == "") {
		swal("操作失败","混合料温度下限(℃)不能为空!", "info");
		return;
	}
	if(MixTemperatureDown > 9999.99){
		swal("操作失败","混合料温度下限(℃)最多入力4位整数!", "info");
		return;
	}
	if(MixTemperatureDown > MixTemperatureUp){
		swal("操作失败","混合料温度下限(℃)超过混合料温度上限(℃)!", "info");
		return;
	}
	var params = $('#addForm').serialize();
	console.log(params);
	
	$.ajax({
		type : "POST",
		url :  "../../PlatformAsphaltWarningDeviation/insertPlatformAsphaltWarningDeviation.action",
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

//只能入力数字和小数点          //和负数 
function keepNumOrDecimal(obj){ 
    /*obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');//只能输入一个小数  
*/    
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
/*
//判断后面得值是否大于等于前面得值
function checkGreaterThanOrEqual(obj) {
	if(obj.value != '' && $(obj).prevAll('input').val() != '' && (obj.value - $(obj).prevAll('input').val()) < 0 ) {
		swal("操作失败","阀值区间应大于等于0！", "error");
	} else {
		
	}
}

//判断前面得值是否小于等于后面得值
function checkLowerThanOrEqual(obj) {
	if(obj.value != '' && $(obj).nextAll('input').val() != '' && (obj.value - $(obj).nextAll('input').val()) > 0 ) {
		swal("操作失败","阀值区间应大于等于0！", "error");
	} else {
		
	}
}*/
//获取混合料名称/型号
function selectMaterials() {
	if($('#Org_ID').val() != '') {
		$.ajax({
			type : "POST",
			url : "../../MaterialProduct/getMaterialProduct.action",
			async:false,
			data : {
				Org_ID:$('#Org_ID').val(),
				Material_Type:0
			},
			dataType : "json",
			success : function(data) {
				console.log(data);
				var html = "<option value='' selected='selected'>请选择</option>";
				$.each(data , function(i,n){
						html += "<option value='" + n.id + "'>" + n.material_Name + '/' + n.material_Model + "</option>";
				});
				$("#Product_ID").empty();
				$("#Product_ID").html(html);
			}
		});
	}
}