$(function() {
	getList();
<<<<<<< .mine
||||||| .r4
	selectMaterials();
=======
	//混合料名称/型号获取
	selectMaterials();
>>>>>>> .r200
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
				if (data[i].org_Type == '2'){//机构类型：0其他部门,1合同段拌和站，2 拌和站
					html += "<option value='" + data[i].Id + "'>"
					+ data[i].Org_Name + "</option>"
				}			
			}
			$("#Org_ID").empty();
			$("#Org_ID").html(html);
		}
	});
	//清空数据
	$("#tab input:text").val("");
	$("#Product_ID").val("");
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
            "data" : "dev_AggregateDown"
        }, {
            "data" : "dev_AggregateCenter"
        }, {
            "data" : "dev_Aggregate"
        }, {
            "data" : "dev_PowderDown"
        }/*, {
            "data" : "dev_PowderCenter"
        }, {
            "data" : "dev_Powder"
        }, {
            "data" : "dev_AdmixtureDown"
        }, {
            "data" : "dev_AdmixtureCenter"
        }, {
            "data" : "dev_Admixture"
        }, {
            "data" : "dev_AsphaltDown"
        }, {
            "data" : "dev_AsphaltCenter"
        }, {
            "data" : "dev_Asphalt"
        }, {
            "data" : "material",
            render : function(data, type, row, meta){
            	if(data.indexOf("undefined") != -1){
            		return "";
            	}else{
            		return row.material;
            	}
            }
        }, {
            "data" : "mixTemperature",
        	render : function(data, type, row, meta) {
        		if(row.mixTemperatureDown == null || row.mixTemperatureUp == null){
        			return "";
        		}else{
        			return row.mixTemperatureDown + "~"
					+ row.mixTemperatureUp;	
        		}
			}
<<<<<<< .mine
        }*/
||||||| .r4
        }
=======
        }, {
        	"data" : "create_Date"
        }
>>>>>>> .r200
        ],
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
	//骨料仓(%)Center
	var Dev_AggregateCenter = ($("#Dev_AggregateCenter").val()!= '')?parseFloat($("#Dev_AggregateCenter").val()):$("#Dev_AggregateCenter").val();
	//骨料仓(%)Down
	var Dev_AggregateDown = ($("#Dev_AggregateDown").val()!= '')?parseFloat($("#Dev_AggregateDown").val()):$("#Dev_AggregateDown").val();
	
	//粉料仓(%)
	var Dev_Powder = ($("#Dev_Powder").val()!= '')?parseFloat($("#Dev_Powder").val()):$("#Dev_Powder").val();
	//粉料仓(%)Center
	var Dev_PowderCenter = ($("#Dev_PowderCenter").val()!= '')?parseFloat($("#Dev_PowderCenter").val()):$("#Dev_PowderCenter").val();
	//粉料仓(%)Down
	var Dev_PowderDown = ($("#Dev_PowderDown").val()!= '')?parseFloat($("#Dev_PowderDown").val()):$("#Dev_PowderDown").val();
	
	//沥青仓(%)
	var Dev_Asphalt = ($("#Dev_Asphalt").val()!= '')?parseFloat($("#Dev_Asphalt").val()):$("#Dev_Asphalt").val();
	//沥青仓(%)Center
	var Dev_AsphaltCenter = ($("#Dev_AsphaltCenter").val()!= '')?parseFloat($("#Dev_AsphaltCenter").val()):$("#Dev_AsphaltCenter").val();
	//沥青仓(%)Down
	var Dev_AsphaltDown = ($("#Dev_AsphaltDown").val()!= '')?parseFloat($("#Dev_AsphaltDown").val()):$("#Dev_AsphaltDown").val();
	
	//外掺挤仓(%)
	var Dev_Admixture = ($("#Dev_Admixture").val()!= '')?parseFloat($("#Dev_Admixture").val()):$("#Dev_Admixture").val();
	//外掺挤仓(%)Center
	var Dev_AdmixtureCenter = ($("#Dev_AdmixtureCenter").val()!= '')?parseFloat($("#Dev_AdmixtureCenter").val()):$("#Dev_AdmixtureCenter").val();
	//外掺挤仓(%)Down
	var Dev_AdmixtureDown = ($("#Dev_AdmixtureDown").val()!= '')?parseFloat($("#Dev_AdmixtureDown").val()):$("#Dev_AdmixtureDown").val();
<<<<<<< .mine
	//拌合站名称：
	var Product_ID = $("#Product_ID").val();
||||||| .r4
	//混合料名称/型号：
	var Product = $("#Product_ID").val();
	arr=Product.split('/');
	var Material_Name = arr[0];
	var Material_Model = arr[1];
=======
	
	//混合料名称/型号：
	var Product = $("#Product_ID").val();
	arr=Product.split('/');
	var Material_Name = arr[0];
	var Material_Model = arr[1];
>>>>>>> .r200
	//混合料温度上限(℃)
	//var MixTemperatureUp = ($("#MixTemperatureUp").val()!= '')?parseFloat($("#MixTemperatureUp").val()):$("#MixTemperatureUp").val();
	//混合料温度下限(℃)
	//var MixTemperatureDown = ($("#MixTemperatureDown").val()!= '')?parseFloat($("#MixTemperatureDown").val()):$("#MixTemperatureDown").val();
	
	if(Org_ID == null || Org_ID == "") {
		swal("操作失败","拌合站名称不能为空!", "info");
		return;
	}
	
<<<<<<< .mine
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
	/*if(Product_ID == null || Product_ID == "") {
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
	}*/
||||||| .r4
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
=======
>>>>>>> .r200
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
<<<<<<< .mine
						html += "<option value='" + n.id + "'>" + n.material_Name + '/' + n.material_Model + "</option>";
||||||| .r4
						html += "<option value='" + n.material_Name + '/'+n.material_Model+"'>" + n.material_Name + '/' + n.material_Model + "</option>";
=======
					if(n != null){
						html += "<option value='" + n.material_Name + '/'+n.material_Model+"'>" + n.material_Name + '/' + n.material_Model + "</option>";
>>>>>>> .r200
					}
				});
				$("#Product_ID").empty();
				$("#Product_ID").html(html);
			}
		});
	}
}