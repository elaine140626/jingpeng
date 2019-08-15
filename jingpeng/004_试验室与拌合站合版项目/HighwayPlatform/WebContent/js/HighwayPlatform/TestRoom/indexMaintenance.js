$(function () {
	// 水泥胶砂强度
	getCementList();
	// 水泥品种(默认显示权限下所有水泥品种) 
	$.ajax({
		type : "POST",
		url : "../../BlindSample/getCementTypeList.action",
		dataType : "json",
		data : {},
		success : function(data) { //请求成功后处理函数。  
			for (var i = 0; i < data.length; i++) {
				if (data[i].CementType != null && data[i].CementType != '') {
					$('#list').append(
						"<option value='" + data[i].CementType + "'>");
				}
			}
		}
	});

	// 沥青三大指标
	getAsphaltList();
	// 马歇尔
	getMarshallList();
	// 钢筋抗拉强度、屈服强度、伸长率
	getRebarList();
	// 钢筋接头抗拉强度、冷弯
	getRebarJointList();
});
//钢筋接头抗拉强度、冷弯
function getRebarJointList() {
    $('#rebarJoint').DataTable({
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
            url: "../../BlindSample/getRebarJoint.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "StrengthGrade"
        }, {
            "data" : "Diameter"
        }, {
            "data" : "TensileStrength"
        }, {
            "data" : "delete"
        }  ],
        "createdRow" : function(row, data, dataIndex) {
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$('.lq_biao .row:first-child').css('display','none');
	$('#rebarJoint_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

// 钢筋抗拉强度、屈服强度、伸长率
function getRebarList() {
    $('#rebar').DataTable({
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
            url: "../../BlindSample/getRebar.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "StrengthGrade"
        }, {
            "data" : "Diameter"
        }, {
            "data" : "YieldStrth"
        }, {
            "data" : "TensileStrength"
        }, {
            "data" : "delete"
        }  ],
        "createdRow" : function(row, data, dataIndex) {
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$('.lq_biao .row:first-child').css('display','none');
	$('#rebar_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

// 马歇尔
function getMarshallList() {
    $('#marshall').DataTable({
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
            url: "../../BlindSample/getMarshall.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "StabLimit"
        }, {
            "data" : "FlowLimit"
        }, {
            "data" : "delete"
        }  ],
        "createdRow" : function(row, data, dataIndex) {
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$('.lq_biao .row:first-child').css('display','none');
	$('#marshall_info').css('text-align','center');
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

// 水泥胶砂强度数据获取
function getCementList() {
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
            url: "../../BlindSample/getCementMortarStrength.action",
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

        },
        "columnDefs" : [ {
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

function getAsphaltList() {
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
            url: "../../BlindSample/getAsphalt.action",
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

// 水泥保存
function addCement(){
	var cementtype = $("#cementtype").val();
	//水泥品种
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
	
	var params = $('#cement_addForm').serialize();
	$.ajax({
		type : "POST",
		url :  "../../BlindSample/addCementMortarStrength.action",
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
					//location.reload();
					loadHTML("maintenance.html", contain);
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
	
}

// 沥青保存
function addAsphalt(){
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
	if(parseFloat(penetlowerlimit)>parseFloat(penetupperlimit)){
		swal("操作失败", "针入度下限不能大于针入度上限", "info");
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
	
	var params = $('#pitch_addForm').serialize();
	$.ajax({
		type : "POST",
		url :  "../../BlindSample/addAsphalt.action",
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
					//location.reload();
					loadHTML("maintenance.html", contain);
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
	
}
// 保存
function addRebar(){
	//钢筋牌号
	var strengthgrade = $("#strengthgrade").val();
	if(strengthgrade == null || strengthgrade == "") {
		swal("操作失败","钢筋牌号不能为空!", "info");
		return;
	}
	//直径下限
	var diameter1 = $("#diameter1").val();
	if(diameter1 == null || diameter1 == "") {
		swal("操作失败","直径下限不能为空!", "info");
		return;
	}
	if((diameter1.indexOf(".") == -1 && diameter1.length>38)){
		swal("操作失败","直径下限最多入力40位整数!", "info");
		return;
	}
	//直径上限
	var diameter2 = $("#diameter2").val();
	if(diameter2 == null || diameter2 == "") {
		swal("操作失败","直径上限不能为空!", "info");
		return;
	}
	if((diameter2.indexOf(".") == -1 && diameter2.length>38)){
		swal("操作失败","直径上限最多入力40位整数!", "info");
		return;
	}
	if(parseFloat(diameter1)>parseFloat(diameter2)){
		swal("操作失败", "直径下限不能大于直径上限", "info");
		return;
	}
	//屈服强度
	var yieldstrth = $("#yieldstrth").val();
	if(yieldstrth == null || yieldstrth == "") {
		swal("操作失败","屈服强度不能为空!", "info");
		return;
	}
	//极限强度
	var tensilestrength = $("#tensilestrength").val();
	if(tensilestrength == null || tensilestrength == "") {
		swal("操作失败","极限强度不能为空!", "info");
		return;
	}
	var params = $('#strength_addForm').serialize();
	$.ajax({
		type : "POST",
		url :  "../../BlindSample/addRebar.action",
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
					//location.reload();
					loadHTML("maintenance.html", contain);
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
	
}
// 马歇尔
function addMarshall(){
	//稳定度下限
	var stablowerlimit = $("#stablowerlimit").val();
	if(stablowerlimit == null || stablowerlimit == "") {
		swal("操作失败","稳定度下限不能为空!", "info");
		return;
	}
	if((stablowerlimit.indexOf(".") == -1 && stablowerlimit.length>38)){
		swal("操作失败","稳定度下限最多入力38位整数!", "info");
		return;
	}
	//稳定度上限
	var stabupperlimit = $("#stabupperlimit").val();
	if(stabupperlimit == null || stabupperlimit == "") {
		swal("操作失败","稳定度上限不能为空!", "info");
		return;
	}
	if((stabupperlimit.indexOf(".") == -1 && stabupperlimit.length>38)){
		swal("操作失败","稳定度上限最多入力38位整数!", "info");
		return;
	}	
	if(parseFloat(stablowerlimit)>parseFloat(stabupperlimit)){
		swal("操作失败", "稳定度下限不能大于稳定度上限", "info");
		return;
	}
	//流值下限
	var flowlowerlimit = $("#flowlowerlimit").val();
	if(flowlowerlimit == null || flowlowerlimit == "") {
		swal("操作失败","流值下限不能为空!", "info");
		return;
	}
	if((flowlowerlimit.indexOf(".") == -1 && flowlowerlimit.length>38)){
		swal("操作失败","流值下限最多入力38位整数!", "info");
		return;
	}
	//流值上限
	var flowupperlimit = $("#flowupperlimit").val();
	if(flowupperlimit == null || flowupperlimit == "") {
		swal("操作失败","流值上限不能为空!", "info");
		return;
	}
	if((flowupperlimit.indexOf(".") == -1 && flowupperlimit.length>38)){
		swal("操作失败","流值上限最多入力38位整数!", "info");
		return;
	}	
	
	var params = $('#matthus_addForm').serialize();
	$.ajax({
		type : "POST",
		url :  "../../BlindSample/addMarshall.action",
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
					//location.reload();
					loadHTML("maintenance.html", contain);
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
	
}

//保存
function addRebarJoint(){
	//钢筋牌号
	var strengthgrade = $("#strengthgrade1").val();
	if(strengthgrade == null || strengthgrade == "") {
		swal("操作失败","钢筋牌号不能为空!", "info");
		return;
	}
	//直径下限
	var diameter1 = $("#diameter11").val();
	if(diameter1 == null || diameter1 == "") {
		swal("操作失败","直径下限不能为空!", "info");
		return;
	}
	if((diameter1.indexOf(".") == -1 && diameter1.length>38)){
		swal("操作失败","直径下限最多入力40位整数!", "info");
		return;
	}
	//直径上限
	var diameter2 = $("#diameter21").val();
	if(diameter2 == null || diameter2 == "") {
		swal("操作失败","直径上限不能为空!", "info");
		return;
	}
	if((diameter2.indexOf(".") == -1 && diameter2.length>38)){
		swal("操作失败","直径上限最多入力40位整数!", "info");
		return;
	}
	if(parseFloat(diameter1)>parseFloat(diameter2)){
		swal("操作失败", "直径下限不能大于直径上限", "info");
		return;
	}
	//极限强度
	var tensilestrength = $("#tensilestrength1").val();
	if(tensilestrength == null || tensilestrength == "") {
		swal("操作失败","极限强度不能为空!", "info");
		return;
	}
	
	var params = $('#bend_addForm').serialize();
	$.ajax({
		type : "POST",
		url :  "../../BlindSample/addRebar.action",
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
					//location.reload();
					loadHTML("maintenance.html", contain);
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
//只能入力数字和小数点  
function keepDecimal(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");   //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); //保证.只出现一次，而不能出现两次以上 
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    } 
}

//只能入力数字 
function keepNum(obj){ 
    obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”以外的字符  
} 

//删除
function del(id, flg){
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
        url: "../../BlindSample/delCementMortarStrength.action",
        data: JSON.stringify({"id": id,"flg":flg}),
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
					//location.reload();
					loadHTML("maintenance.html", contain);
				}); },200);
				
			}else{
				swal("操作失败",data.message, "error");
			}
        }
    });
  });	
}
$("input:text").each(function () {
    jQuery(this).change(function () {
      jQuery(this).val(jQuery.trim(jQuery(this).val()));
    })
})

 
