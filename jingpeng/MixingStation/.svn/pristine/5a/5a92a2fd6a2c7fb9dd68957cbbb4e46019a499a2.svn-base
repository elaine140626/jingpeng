var addOrUpdate = 0;
var baseUrl = "";
var i_org_Id = "";
$(function () {

    /* baseUrl = getContextPath();*/

    $.ajax({
        type: "post",
        url: "../CementTheory/getValue.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            $(".userid").html(data.userName)
        }
    });

    $('#llConstruction').DataTable({
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
        "ajax" : "../CementTheory/getCement_TheoryProportions.html",
        "deferRender" : true,
        "columns" : [{
            "data" : "serialNumber"
        }, {
            "data" : "str_prop_Code"
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

    $('#str_prop_Code').blur(function(){
        $.ajax({
            type: "post",
            async: false,
            url: "../CementTheory/getCementProportionCode",
            dataType: "json",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify({"str_prop_Code":$("#str_prop_Code").val()}),
            success: function (data) {
                /* if(data.code == 300){
                     window.Equipment_Code.attr("cz","cz");
                     alert(data.message)
                 }else{
                     window.Equipment_Code.attr("cz","bcz");
                 }*/
            }
        });
    });

    
    
    //光标移除产品名称
    $('#str_material_Name').blur(function(){
        $.ajax({
            type: "post",
            async: false,
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


})

function getMaterialName(){
	 $.ajax({
	        type: "post",
	        async: false,
	        url: "../Common/getMaterialName.html",
	        data: {"str_material_Type":1,"str_material_Mold":1},
	        dataType: "json",
	        success: function (data) {
	        	 $("#str_material_Name").empty();
	            /*$.each(data, function (i, v) {

	                $('<option></option>').attr("value", v.str_material_Name).html(v.str_material_Name).appendTo($("#str_material_Name"));

	            })*/
	        	 var html = "";
	        	 for(var i = 0; i < data.length; i++) {
	 				html += "<option value='"+data[i].i_mateName_id+"'>"+data[i].str_material_Name+"</option>"
	 			}
	        	 $("#str_material_Name").html(html);
	        }
	    });
}


function addCement_T() {
    var str_prop_Code = $("#str_prop_Code").val()
    var d_water_Cement_Ratio = $("#d_water_Cement_Ratio").val()
    var d_sand_Ratio = $("#d_sand_Ratio").val()
    var str_design_Strength = $("#str_design_Strength").val()
    var i_slump_Low = $("#i_slump_Low").val()
    var i_slump_High = $("#i_slump_High").val()
    var str_operator = $("#str_operator").val()
    var i_product_Id = $("#str_material_Model").val();
    var str_remarks = $("#str_remarks").val();
	//var i_id;
	var obj = $("#tabForm").serializeArray();
	if(obj == null || obj == "") {
		alert("目标配合比明细不能为空");
		return;
	}
	var list = toJsonArr(obj);
	var flag = validForm(list);
	if(flag == false) {
		return;
	}
	var url = '';
	var params = {};
	if(addOrUpdate == 0) {
		url = '../CementTheory/addCement_TheoryProportion.html';
		params = {

	            "i_org_Id":6,
	            "str_remarks": str_remarks,
	            "str_prop_Code": str_prop_Code,
	            "i_product_Id": i_product_Id,
	            "d_water_Cement_Ratio": d_water_Cement_Ratio,
	            "d_sand_Ratio": d_sand_Ratio,
	            "str_design_Strength": str_design_Strength,
	            "i_slump_Low": i_slump_Low,
	            "i_slump_High": i_slump_High,
	            "str_operator": str_operator,
	            "i_upload":1,
	            "i_valid_Flag":1,
	            "list":list
	        };
	} else {
		url = '../CementTheory/updateCement_TheoryProportion.html';
		params = {
				"i_id":$("#i_id").val(),
	            "i_org_Id":6,
	            "str_remarks": str_remarks,
	            "str_prop_Code": str_prop_Code,
	            "i_product_Id": i_product_Id,
	            "d_water_Cement_Ratio": d_water_Cement_Ratio,
	            "d_sand_Ratio": d_sand_Ratio,
	            "str_design_Strength": str_design_Strength,
	            "i_slump_Low": i_slump_Low,
	            "i_slump_High": i_slump_High,
	            "str_operator": str_operator,
	            "i_upload":1,
	            "i_valid_Flag":1,
	            "list":list
	        };
	}
    $.ajax({
        type: "POST",
        url:url,
        data:params,
        dataType: "json",
        success: function (data) {
            alert(data.message);
            window.location.href="../CementTheory/getCement_TheoryProportion.html";
        }
    });
}

function toJsonArr(obj) {
	var strList="[";
	for(var i = 0; i < obj.length; i++) {
		if(i % 4 == 0) {
			strList += "{\""+obj[i].name+"\":"+"\""+obj[i].value+"\"";
		} else {
			strList += "\""+obj[i].name+"\":"+"\""+obj[i].value+"\"";
		}
		if((i + 1) % 4 == 0) {
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

function validForm(str) {
	var obj = JSON.parse(str);
	var count1 = 0;
	var count2 = 0;
	for(var i = 0; i < obj.length; i++) {
		if(obj[i].i_materials_Id != null && obj[i].i_materials_Id != ""){
			count1++;
		}
		if(obj[i].d_weight != null || obj[i].d_weight != "") {
			count2++;
		}
	}
	if(count1 == 0 || count1 < count2) {
		alert("原材料名称原材料型号和比例不能为空");
		return false;
	}
	if(count2 == 0 || count1 > count2) {
		alert("比例不能为空");
		return false;
	}
	return true;
}

function numcn(obj){
    obj.value = obj.value.replace(/[^\d.]/g,"");
    obj.value = obj.value.replace(/^\./g,"");
    obj.value = obj.value.replace(/\.{2,}/g,".");
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}

function show(i_id) {
	addOrUpdate = 1;
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
         })
    $("#detailedTab tbody ").empty();

    $.ajax({
        type : "POST",
        url : "../CementTheory/getCement_TheoryProportionById.html",
        data : {"i_id":i_id},
        dataType : "json",
        success : function(data) {
        	 $("#i_id").val(data.i_id);
        	 $("#str_prop_Code").val(data.str_prop_Code);
        	 $("#str_material_Name").html("<option selected='selected'>"+data.str_material_Name+"</option>");
        	 $("#str_material_Model").html("<option value='"+data.i_product_Id+"' selected='selected'>"+data.str_material_Model+"</option>");
        	 $("#d_water_Cement_Ratio").val(data.d_water_Cement_Ratio);
        	 $("#d_sand_Ratio").val(data.d_sand_Ratio);
        	 $("#str_design_Strength").val(data.str_design_Strength);
        	 $("#i_slump_Low").val(data.i_slump_Low);
        	 $("#i_slump_High").val(data.i_slump_High);
        	 $("#str_operator").val(data.str_operator);
        	 $("#str_remarks").val(data.str_remarks);
        	 var html = ""
        	 var cement_TheoPropDetaileds = data.cement_TheoPropDetaileds;
        	 for(var i = 0; i < cement_TheoPropDetaileds.length; i++) {
        		 html += '<tr ><td>' + i+1 + '</td>' +
        	        '<td><select onfocus="getYclName(this);" onchange="clearModel(this)" class="m"><option selected="selected">'+cement_TheoPropDetaileds[i].str_material_Name+'</option></select></td> ' +
        	        '<td><select name="i_materials_Id" onfocus="getYclModel(this);"><option selected="selected" value="'+cement_TheoPropDetaileds[i].i_materials_Id+'">'+cement_TheoPropDetaileds[i].str_material_Model+'</option></select></td>' +
        	        '<td><input class="texta" type="text" name="d_weight" maxlength="18" value="'+cement_TheoPropDetaileds[i].i_theoProp_Id+'"></input></td>' +
        	        '<td><input name="str_Manufacturer" value="'+cement_TheoPropDetaileds[i].str_Manufacturer+'"></td>' +
        	        '<td><input name="str_material_Origin" value="'+cement_TheoPropDetaileds[i].str_material_Origin+'"></td>' +
        	        '<td><img src="../resources/images/del.png" onclick="delDetailed(this);"></td>' +
        	        '</tr>';
        	 }
        	 $("#tb").html(html);
        }
    });
}

function del(i_id) {
    $.ajax({
        type : "POST",
        url :"../CementTheory/deletCement_TheoryProportion.html",
        data : {"i_id":i_id},
        dataType : "json",
        success : function(data) {
            alert(data.message);
            window.location.href= "../CementTheory/getCement_TheoryProportion.html";
        }
    });
}


function getMaterialModel(obj) {
    var aterial_modelid = obj.id
    var materialmodelser = (obj.id).toString().substring(13)

    console.log(materialmodelser)
    $.ajax({
        type : "POST",
        url :"../Common/getMaterialModel.html",
        contentType : 'application/json;charset=UTF-8',
        data : JSON.stringify({"str_material_Type":1,
            "str_material_Mold":1,
            "str_material_Name":$("#MaterialName"+materialmodelser).val()}),
        dataType : "json",
        success : function(data) {
            $("#" + aterial_modelid).empty();
            $.each(data, function (i, v) {
                $('<option></option>').attr("value", v.i_id).html(v.str_material_Model).appendTo($("#" + aterial_modelid));

            })
            /*       var html = "";
                   for(var i = 0; i < data.length; i++) {
                       html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>";
                   }
                   obj.empty();
                   obj.append(html);
       */
        }
    });
}

//删除动态添加行
function RemoveMaterialAdd(rowIndex) {

    var table = document.all['detailedTab'];

    table.deleteRow(rowIndex);
    count = count - 1;
}

function delDetailed(obj) {
    RemoveMaterialAdd(obj.parentElement.parentElement.rowIndex);
}

function login_popup() {
	addOrUpdate = 0;
	$("#loginModal input[type=text]").val("");
	var html = '<td>1</td>'
                    +'<td><select  size="1" onfocus="getYclName(this);" onchange="clearModel(this)" class="m">'
                      +'<option></option>'
                    +'</select></td>'
                    +'<td><select name="i_materials_Id" size="1" onfocus="getYclModel(this);">'
                      +'<option></option>'
                    +'</select></td>'
                    +'<td><input name="d_weight" type="text" placeholder="输入"  onkeyup="num(this)"></td>'
                    +'<td><input type="text" name="str_Manufacturer"></td>'
                    +'<td><input type="text" name="str_material_Origin"></td>'
                    +'<td>&nbsp;</td>';
	$("#tb").html(html);
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


function addrow(){
    var num = parseInt(100 * Math.random());
    $("#num").val(num);
    var tables = $('.table2');
    var tempTR = '<tr name="' + num + 'AfteRzsdtype"><td>' + (parseInt(count++) + 1) + '</td>' +
        '<td><select onfocus="getYclName(this);" onchange="clearModel(this)" class="m"></select></td> ' +
        '<td><select name="i_materials_Id" onfocus="getYclModel(this);"></select></td>' +
        '<td><input class="texta" type="text" name="d_weight" maxlength="18" ></input></td>' +
        '<td><input name="str_Manufacturer" ></td>' +
        '<td><input name="str_material_Origin" ></td>' +
        '<td><img src="../resources/images/del.png" onclick="delDetailed(this);"></td>' +
        '</tr>';
    tables.append(tempTR)
}

function clearModel(obj){
	str_material_Name = $(obj).parent().parent().find("td:eq(3)").children().empty();
}

function getYclName(obj) {
	var name = "";
	var params = {};
	$(".m").each(function(){
		var value = $(this).find("option:selected").text();
		if(value != "") {
			name += value+","
		}
	});
	var a = $(obj).find("option:selected").text();
	if(a != "") {
		name.replace(a+",","")
	}
	params = {"name":name};
	$.ajax({
		type : "POST",
		url : "../CementTheory/getRawMaterial.html",
		data : params,
		dataType : "json",
		success : function(data) {
			var html = ""
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_mateName_id+"'>"+data[i].str_material_Name+"</option>"
			}
			$(obj).empty();
			$(obj).html(html);
		}
	});
}

function getYclModel(obj) {
	var str_material_Name = $(obj).parent().parent().find("td:eq(1)").children().find("option:selected").text();
	$.ajax({
		type : "POST",
		url : "../Common/getMaterialModel.html",
		data : {"str_material_Type":1,
		        "str_material_Mold":0,
		        "str_material_Name":str_material_Name},
		dataType : "json",
		success : function(data) {
			var html = "";
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>";
			}
			$(obj).empty();
			$(obj).append(html);
		}
	});
}

function CurentTime()
{
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒

    var clock = year + "-";

    if(month < 10)
        clock += "0";

    clock += month + "-";

    if(day < 10)
        clock += "0";

    clock += day + " ";

    if(hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm + ":";

    if (ss < 10) clock += '0';
    clock += ss;
    return(clock);
}

d.checkNode = function (id,i_parent_Id,flag,checked) {
	if(checked == true) {
		i_org_Id = id;
	} else {
		i_org_Id = "";
	}
}

function getPbgl_1Select(){
	
	var table = $('#llConstruction').dataTable();
	table.fnDestroy();
	$('#llConstruction').DataTable({
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
            url: "../CementTheory/getCement_TheoryProportions.html",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_power_Org_Id = i_org_Id;
                param.productName = $("#productName").val();
                param.productModel = $("#productModel").val();
                param.theoreticalProportionNumber = $("#theoreticalProportionNumber").val();
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [{
            "data" : "serialNumber"
        }, {
            "data" : "str_prop_Code"
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