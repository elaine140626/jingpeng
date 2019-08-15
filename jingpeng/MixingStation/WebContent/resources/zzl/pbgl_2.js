var addOrUpate = 0;
var i_org_Id = "";
$(function () {
    getList();


    $.ajax({
        type: "post",
        url: "../CementConstructionProp/getValue.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            $(".userid").html(data.userName)
        }
    });

})

 function getList() {
        $('#CConstruction').DataTable({
            "paging" : true,
            "lengthChange" : false,
            "pageLength" : 14,
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
            "ajax" : "../CementConstructionProp/getCementConstructionProp.html",
            "deferRender" : true,
            "columns" : [ {
                "data" : "serialNumber"
            }, {
                "data" : "str_prop_Code"
            },{
                "data" : "str_llProp_Code"
            }, {
                "data" : "str_material_Model"
            }, {
                "data" : "d_water_Cement_Ratio"
            }, {
                "data" : "str_tld"
            }, {
                "data" : "str_design_Strength"
            }, {
                "data" : "d_sand_Ratio"
            }, {
                "data" : "str_operator"
            },{
                "data" : "str_create_Date"
            },{
                "data" : "operate"
            }],
            "createdRow" : function(row, data, dataIndex) {

                $(row).children('td').eq(1).attr('style', 'text-align: center;')
            },
            "columnDefs" : [ {
                "targets" : [ 0 ],
                "visible" : true,
                "searchable" : false
            } ]
        });
        
        $("#str_Material_Name").blur(function(){
            $.ajax({
                type: "post",
                url: "../Common/getMaterialModel.html",
                dataType: "json",
                data:{"str_material_Type":1,"str_material_Name":$(this).find("option:selected").text(),"str_material_Mold":1},
                success: function (data) {
                    var html = "";
                    $("#str_material_Model").empty();
                    $.each(data, function (i, v) {
                        $('<option></option>').attr("value", v.i_id).html(v.str_material_Model).appendTo($("#str_material_Model"));

                    })
                }
            });
        });
}

function login_popup() {
	addOrUpate = 0;
	$("#table1 input").val("");
	$("#str_remarks").val("");
	$("#table1 select").html("<option></option>")
	$("#detailed").empty();
    $("#loginModal").modal("show")
}

$(".globalLoginBtn").on("click", login_popup),function() {
    var e = [];
    $(".modal").on("show.bs.modal",
    function() {
        for (var s = 0; e.length > s; s++) e[s] && (e[s].modal("hide"), e[s] = null);
        e.push($(this));
        var o = $(this),
        a = o.find(".modal-dialog"),
        t = $('<div style="display:table; width:100%; height:100%;"></div>');
        t.html('<div style="vertical-align:middle; display:table-cell;"></div>'),
        t.children("div").html(a),
        o.html(t)
    })
} ();

function getMaterialName(){
	 $.ajax({
	        type: "post",
	        url: "../Common/getMaterialName.html",
	        data: {"str_material_Type":1,"str_material_Mold":1},
	        dataType: "json",
	        success: function (data) {
	        	 $("#str_material_Name").empty();
	        	 var html = "";
	        	 for(var i = 0; i < data.length; i++) {
	 				html += "<option>"+data[i].str_material_Name+"</option>"
	 			 }
	        	 $("#str_Material_Name").html(html);
	        }
	    });
}

function getTheoryProportionCode(){
	 $.ajax({
	        type: "post",
	        url: "../CementConstructionProp/getTheoryProportionCode.html",
	        data: {},
	        dataType: "json",
	        success: function (data) {
	        	 $("#i_theoProp_Id").empty();
	        	 var html = "";
	        	 for(var i = 0; i < data.length; i++) {
	 				html += "<option value='"+data[i].i_id+"'>"+data[i].str_prop_Code+"</option>"
	 			 }
	        	 $("#i_theoProp_Id").html(html);
	        }
	    });
}

function getTheoryProportionDetailed() {
	$.ajax({
        type: "post",
        url: "../CementTheory/getCement_TheoryProportionById.html",
        data: {i_id:$("#i_theoProp_Id").val()},
        dataType: "json",
        success: function (data) {
        	$("#d_water_Cement_Ratio").val(data.d_water_Cement_Ratio);
        	$("#str_design_Strength").val(data.str_design_Strength);
        	$("#d_sand_Ratio").val(data.d_sand_Ratio);
        	$("#i_slump_Low").val(data.i_slump_Low);
        	$("#i_slump_High").val(data.i_slump_High);
        	$("#str_operator").val(data.str_operator)
        	var cement_TheoPropDetaileds = data.cement_TheoPropDetaileds;
        	var html = "";
        	for(var i = 0; i < cement_TheoPropDetaileds.length; i++) {
        		html += "<tr>"
                  	   +"<td>"+i+1+"</td>"
                  	   +"<td><input type='text' readonly='readonly' value='"+cement_TheoPropDetaileds[i].str_material_Name+"'>"
                  	   +"<input type='hidden' name='i_id' value='"+cement_TheoPropDetaileds[i].i_id+"'>"
                  	   +"<input type='hidden' name='i_materials_Id' value='"+cement_TheoPropDetaileds[i].i_materials_Id+"'></td>"
                  	   +"<td><input type='text' name='str_material_Model' readonly='readonly' value='"+cement_TheoPropDetaileds[i].str_material_Model+"'></td>"
                  	   +"<td><input name='d_theory_Weight' type='text' readonly='readonly' value='"+cement_TheoPropDetaileds[i].d_weight+"'></td>"
                  	   +"<td><input name='d_construction_Weight' type='text' placeholder='输入' onkeyup='num(this)'></td>"
                  	   +"<td><input type='text' name='str_manufacturer' value='"+cement_TheoPropDetaileds[i].str_Manufacturer+"' readonly='readonly'>"
                  	   +"<td><input type='text' name='str_material_Origin' value='"+cement_TheoPropDetaileds[i].str_material_Origin+"' readonly='readonly'></td>"
                  	   +"</tr>";
        	}
        	$("#detailed").html(html);
        }
    });
}

function addOrUpdate() {
	var url = "";
	var params = {};
	var obj = $("#tabForm").serializeArray();
	var list = toJsonArr(obj);
	if(addOrUpate == 0) {
		url = "../CementConstructionProp/addCementConstructionPro.html";
		params = {
				  d_water_Cement_Ratio:$("#d_water_Cement_Ratio").val(),
				  d_sand_Ratio:$("#d_sand_Ratio").val(),
				  str_design_Strength:$("#str_design_Strength").val(),
				  i_slump_Low:$("#i_slump_Low").val(),
				  i_slump_High:$("#i_slump_High").val(),
				  str_prop_Code:$("#str_prop_Code").val(),
				  i_product_Id:$("#str_material_Model").val(),
				  i_theoProp_Id:$("#i_theoProp_Id").val(),
				  str_remarks:$("#str_remarks").val(),
				  str_operator:$("#str_operator").val(),
				  list:list
				 };
	} else {
		url = "../CementConstructionProp/updateCementConstructionPro.html";
		params = {
				  i_id:$("#i_id").val(),
				  d_water_Cement_Ratio:$("#d_water_Cement_Ratio").val(),
				  d_sand_Ratio:$("#d_sand_Ratio").val(),
				  str_design_Strength:$("#str_design_Strength").val(),
				  i_slump_Low:$("#i_slump_Low").val(),
				  i_slump_High:$("#i_slump_High").val(),
				  str_prop_Code:$("#str_prop_Code").val(),
				  i_product_Id:$("#str_material_Model").val(),
				  i_theoProp_Id:$("#i_theoProp_Id").val(),
				  str_remarks:$("#str_remarks").val(),
				  str_operator:$("#str_operator").val(),
				  list:list
				 };
	}
	$.ajax({
        type: "post",
        url: url,
        data:params,
        dataType: "json",
        success: function (data) {
           alert(data.message);
           window.location.href="../CementConstructionProp/cementConstructionProp.html";
        }
    });
}

function toJsonArr(obj) {
	var strList="[";
	for(var i = 0; i < obj.length; i++) {
		if(i % 7 == 0) {
			strList += "{\""+obj[i].name+"\":"+"\""+obj[i].value+"\"";
		} else {
			strList += "\""+obj[i].name+"\":"+"\""+obj[i].value+"\"";
		}
		if((i + 1) % 7 == 0) {
			if(i == obj.length - 1) {
				strList += "}]"
			} else {
				strList += "},"
			}
		}else {
			strList += ","
		}
	}
	return strList;
}

function num(obj){
    obj.value = obj.value.replace(/[^\d.]/g,"");
    obj.value = obj.value.replace(/^\./g,"");
    obj.value = obj.value.replace(/\.{2,}/g,".");
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}

function show(i_id) {
	addOrUpate = 1;
    $("#loginModal").modal("show");
    var e = [];
    $(".modal").on("show.bs.modal",
    function() {
        for (var s = 0; e.length > s; s++) e[s] && (e[s].modal("hide"), e[s] = null);
        e.push($(this));
        var o = $(this),
        a = o.find(".modal-dialog"),
        t = $('<div style="display:table; width:100%; height:100%;"></div>');
        t.html('<div style="vertical-align:middle; display:table-cell;"></div>'),
        t.children("div").html(a),
        o.html(t)
    });
	$.ajax({
        type: "post",
        url: "../CementConstructionProp/getC_ConstructionDeatlByid.html",
        data:{"i_id":i_id},
        dataType: "json",
        success: function (data) {
        	$("#i_id").val(data.i_id);
        	$("#str_prop_Code").val(data.str_prop_Code);
        	$("#str_material_Name").html("<option selected='selected'>"+data.str_material_Name+"</option>");
       	    $("#str_material_Model").html("<option value='"+data.i_product_Id+"' selected='selected'>"+data.str_material_Model+"</option>");
       	    $("#i_theoProp_Id").html("<option value='"+data.i_theoProp_Id+"' selected='selected'>"+data.i_theoProp_Id+"</option>");
        	$("#d_water_Cement_Ratio").val(data.d_water_Cement_Ratio);
        	$("#str_design_Strength").val(data.str_design_Strength);
        	$("#d_sand_Ratio").val(data.d_sand_Ratio);
        	$("#i_slump_Low").val(data.i_slump_Low);
        	$("#i_slump_High").val(data.i_slump_High);
        	$("#str_operator").val(data.str_operator);
        	$("#str_remarks").val(data.str_remarks);
        	var cement_TheoPropDetaileds = data.cement_ConsPropDetailedList;
        	var html = "";
        	for(var i = 0; i < cement_TheoPropDetaileds.length; i++) {
        		html += "<tr>"
                  	   +"<td>"+i+1+"</td>"
                  	   +"<td><input type='text' readonly='readonly' value='"+cement_TheoPropDetaileds[i].str_Material_Name+"'>"
                  	   +"<input type='hidden' name='i_id' value='"+cement_TheoPropDetaileds[i].i_id+"'>"
                  	   +"<input type='hidden' name='i_materials_Id' value='"+cement_TheoPropDetaileds[i].i_materials_Id+"'></td>"
                  	   +"<td><input type='text' name='str_material_Model' readonly='readonly' value='"+cement_TheoPropDetaileds[i].str_Material_Model+"'></td>"
                  	   +"<td><input name='d_theory_Weight' type='text' readonly='readonly' value='"+cement_TheoPropDetaileds[i].d_theory_Weight+"'></td>"
                  	   +"<td><input name='d_construction_Weight' type='text' placeholder='输入' onkeyup='num(this)' value='"+cement_TheoPropDetaileds[i].d_construction_Weight+"'></td>"
                  	   +"<td><input type='text' name='str_manufacturer' value='"+cement_TheoPropDetaileds[i].str_manufacturer+"' readonly='readonly'>"
                  	   +"<td><input type='text' name='str_material_Origin' value='"+cement_TheoPropDetaileds[i].str_material_Origin+"' readonly='readonly'></td>"
                  	   +"</tr>";
        	}
        	$("#detailed").html(html);
        }
    });
}

function del(i_id) {
	$.ajax({
        type: "post",
        url:"../CementConstructionProp/delCementConstructionPro.html",
        data:{"i_id":i_id},
        dataType: "json",
        success: function (data) {
           alert(data.message);
           window.location.href="../CementConstructionProp/cementConstructionProp.html";
        }
    });
}
d.checkNode = function (id,i_parent_Id,flag,checked) {
	if(checked == true) {
		i_org_Id = id;
	} else {
		i_org_Id = "";
	}
	
}

function getPbgl_2Select(){
	
	var table = $('#CConstruction').dataTable();
	table.fnDestroy();
	$('#CConstruction').DataTable({
        "paging" : false,
        "lengthChange" : false,
        "bLengthChange": false,
        "pageLength" : 14,
        "searching" : false,
        "ordering" : false,
        "info" : true,
        "sInfo" : true,
        "autoWidth" : false,

        "ajax" : {
            type: "post",
            url: "../CementConstructionProp/getCementConstructionProp.html",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_power_Org_Id = i_org_Id;
                param.str_material_Name = $("#str_material_Name").val();
                param.str_material_Model = $("#str_material_Model").val();
                param.str_prop_Code = $("#str_prop_Code").val();
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "str_prop_Code"
        },{
            "data" : "str_llProp_Code"
        }, {
            "data" : "str_material_Model"
        }, {
            "data" : "d_water_Cement_Ratio"
        }, {
            "data" : "str_tld"
        }, {
            "data" : "str_design_Strength"
        }, {
            "data" : "d_sand_Ratio"
        }, {
            "data" : "str_operator"
        },{
            "data" : "str_create_Date"
        },{
            "data" : "operate"
        }],
        "createdRow" : function(row, data, dataIndex) {

            var c = parseInt(data.HeGePanShu) * 100 /parseInt(data.TotalCount)
            var bfb = c.toFixed(2) +'%'
            $('td:eq(7)', row).html(bfb)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
}