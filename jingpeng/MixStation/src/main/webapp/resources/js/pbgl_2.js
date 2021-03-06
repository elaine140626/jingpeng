var baseUrl = "";
var i_org_Id = "";
var addOrupdateTag = 1;
$(function() {
    $.ajax({
        type: "post",
        url: "../AsphProportion/getAsphalt_ProdValue",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            $(".userid").html(data.userName)
        }
    });
    
    i_org_Id = orgid;

    $("input[type='radio']").each(function(){
		var cshow = $(this).attr("cshow");
		if(cshow == orgname) {
			$(this).attr('checked', 'checked');
		}
	});
	baseUrl = getContextPath();
	getList();
	
});

$('.inputb').blur(function (e) {
    var strid = e.target.id;
    var value = e.target.value;
    var StrId = "";
    var RowCol = strid.split('_');
    var No1 = parseInt(RowCol[0]);
    var No2 = parseInt(RowCol[1]);
    if (value == 0 && value != "") {
        for (var i = (No2 + 1) ; i < 16; i++) {
            var II = i.toString();
            StrId = No1 + "_" + II;
            $("#" + StrId + "").val(0);
        }
    }
    else if (value == 100) {
        for (var w = 1; w < (No2 + 1) ; w++) {
            var WW = w.toString();
            StrId = No1 + "_" + WW;
            $("#" + StrId + "").val(100);

        }
    }
});

function getList() {
	
	$('#list').DataTable(
			{
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
				"ajax" : baseUrl+ "/AsphaltGrading/getAsphalt_Gradings.html?i_org_Id="+i_org_Id,
				"deferRender" : true,
				"columns" : [ {
					"data" : "serialNumber"
				}, {
					"data" : "str_grade_Code"
				}, {
					"data" : "materNameAndModel"
				}, {
					"data" : "str_operator"
				}, {
					"data" : "str_create_Date"
				}, {
					"data" : "str_remarks"
				}, {
					"data" : "operate"
				} ],
				"createdRow" : function(row, data, dataIndex) {
//					$(row).children("td").eq(1).attr("onmouseover","getDetailed('" + data.i_id + "');")
					$(row).children("td").eq(1).addClass("mbpb_xg");
					//$(row).children("td").eq(1).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_grade_Code+"</a>");
					$(row).children("td").eq(1).html("<a href='javascript:void(0);'>"+data.str_grade_Code+"</a>");
		            $(row).children("td").eq(1).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_id+");");
		            $(row).children("td").eq(1).attr('onMouseOut','hide(this,\"mydiv1\")');
				},
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : true,
					"searchable" : false
				} ]

		});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}

//function getMaterialName(str_material_Mold,obj){
//	console.log(i_org_Id)
//	$.ajax({
//		type : "POST",
//		url : baseUrl + "/Common/getMaterialName.html",
//		data : {"str_material_Type":0,
//			"i_org_Id":i_org_Id,
//			 "str_material_Mold":str_material_Mold},
//		dataType : "json",
//		success : function(data) {
//			var html = ""
//			for(var i = 0; i < data.length; i++) {
//				html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>"
//			}
//			obj.empty();
//			obj.html(html);
//		}
//	});
//}



function getMaterialName(){

	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialName.html",
		data : {"str_material_Type":0,
			"i_org_Id":i_org_Id,
			 "str_material_Mold":'1'},
		dataType : "json",
		success : function(data) {
			var html =  "<option value='null' selected='selected'> </option>"
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].name+"'>"+data[i].name+"</option>"
			}
			$("#str_material_Name").empty();
			$("#str_material_Name").html(html);
		}
	});
	
}
//产品名称监听事件
function getOutName(){
    var index = $("#str_material_Name")[0].selectedOptions[0].index-1;
    if(index<0){
    	$("#str_material_Model").empty();
    	return;
    }
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialName.html",
		data : {"str_material_Type":0,"str_material_Mold":1,"i_org_Id":i_org_Id},
		dataType : "json",
		success : function(data) {
			var html =""
			for(var i = 0; i < data[index].model.length; i++) {
				html += "<option value='"+data[index].model[i].i_id+"'>"+data[index].model[i].str_material_Model+"</option>"
			}
			$("#str_material_Model").empty();
			$("#str_material_Model").html(html);
		}
	});
}

function clearModel(){
	$("#str_material_Model").empty();
}

//function getMaterialModel(str_material_Mold, obj) {
//	var str_material_Name;
//	str_material_Name = $("#str_material_Name").find("option:selected").text();
//	$.ajax({
//		type : "POST",
//		url : baseUrl + "/Common/getMaterialModel.html",
//		data : {"str_material_Type":0,
//		        "str_material_Mold":str_material_Mold,
//		        "i_org_Id":i_org_Id,
//		        "str_material_Name":str_material_Name},
//		dataType : "json",
//		success : function(data) {
//			var html = "";
//			for(var i = 0; i < data.length; i++) {
//				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>";
//			}
//			obj.empty();
//			obj.html(html);
//		}
//	});
//}



$('.inputb').keyup(function () {
    onkeyup = this.value = this.value.replace(/[^\d.]/g, '')
                                     .replace(/^\./g, "").replace(/\.{2,}/g, ".")//只能输入一个小数点
                                     .replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//小数点后只能有一位
    onafterpaste = this.value = this.value.replace(/[^\d.]/g, '');

});

function login_popup() {
    $("#loginModal").modal("show")
    $("#tab input").val("");
    $("#str_grade_Code").val("");
    $("#str_material_Name").empty();
    $("#str_material_Model").empty();
    $("#str_remarks").val("");
    $("#str_grade_Code").removeAttr("readonly");
    addOrupdateTag = 1;
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

function addOrUpdate() {
    var result = 0;
    var arr1 = [];
    var Number = "";
    var Value = "";
  
    //inputb
    for (var i = 1; i < 11; i++) {
        for (var w = 1; w < 16; w++) {
            var I = i.toString();
            var W = w.toString();
            Number = I + "_" + W;
            Value = document.getElementById(Number);
            if (Value.value > 100) {
                result = 100;
            }
            if (I == 1) {
                if (Value.value == "" || Value.value == null) {
                    arr1[w - 1] = 0;
                }
                else {
                    arr1[w - 1] = parseFloat(document.getElementById(Number).value);
                    if (w > 1) {
                        if (arr1[w - 2] != 0) {
//                            if (arr1[w - 1] > arr1[w - 2]) {
//                                alert("您的输入有误,筛孔通过率应逐级递减!");
//                                return false;
//                            }
                        }
                    }
                    $("#N1").val("N1");
                }
            }
            if (I == 2) {
                if (Value.value == "" || Value.value == null) {
                    arr1[w + 14] = 0;
                }
                else {
                    arr1[w + 14] = parseFloat(document.getElementById(Number).value);
                    if (w > 1) {
                        if (arr1[w + 14] != 0) {
//                            if (arr1[w + 14] < arr1[w + 13]) {
//                                alert("您的输入有误,筛孔通过率应逐级递减!");
//                                return false;
//                            }
                        }
                    }
                    $("#N2").val("N2");
                }
            }
            if (I == 3) {
                if (Value.value == "" || Value.value == null) {
                    arr1[w + 29] = 0;
                }
                else {
                    arr1[w + 29] = parseFloat(document.getElementById(Number).value);
                    if (w > 1) {
                        if (arr1[w + 29] != 0) {
//                            if (arr1[w + 29] < arr1[w + 28]) {
//                                alert("您的输入有误,筛孔通过率应逐级递减!");
//                                return false;
//                            }
                        }
                    }
                    $("#N3").val("N3");
                }
            }
            if (I == 4) {
                if (Value.value == "" || Value.value == null) {
                    arr1[w + 44] = 0;
                }
                else {
                    arr1[w + 44] = parseFloat(document.getElementById(Number).value);
                    if (w > 1) {
                        if (arr1[w + 44] != 0) {
//                            if (arr1[w + 44] < arr1[w + 43]) {
//                                alert("您的输入有误,筛孔通过率应逐级递减!");
//                                return false;
//                            }
                        }
                    }
                    $("#N4").val("N4");
                }
            }
            if (I == 5) {
                if (Value.value == "" || Value.value == null) {
                    arr1[w + 59] = 0;
                }
                else {
                    arr1[w + 59] = parseFloat(document.getElementById(Number).value);
                    if (w > 1) {
                        if (arr1[w + 59] != 0) {
//                            if (arr1[w + 59] < arr1[w + 58]) {
//                                alert("您的输入有误,筛孔通过率应逐级递减!");
//                                return false;
//                            }
                        }
                    }
                    $("#N5").val("N5");
                }
            }
            if (I == 6) {
                if (Value.value == "" || Value.value == null) {
                    arr1[w + 74] = 0;
                }
                else {
                    arr1[w + 74] = parseFloat(document.getElementById(Number).value);
                    if (w > 1) {
                        if (arr1[w + 74] != 0) {
//                            if (arr1[w + 74] < arr1[w + 73]) {
//                                alert("您的输入有误,筛孔通过率应逐级递减!");
//                                return false;
//                            }
                        }
                    }
                    $("#N6").val("N6");
                }
            }
            if (I == 7) {
                if (Value.value == "" || Value.value == null) {
                    arr1[w + 89] = 0;
                }
                else {
                    arr1[w + 89] = parseFloat(document.getElementById(Number).value);
                    if (w > 1) {
                        if (arr1[w + 89] != 0) {
//                            if (arr1[w + 89] < arr1[w + 88]) {
//                                alert("您的输入有误,筛孔通过率应逐级递减!");
//                                return false;
//                            }
                        }
                    }
                    $("#NH").val("NH");
                }
            }
            if (I == 8) {
                if (Value.value == "" || Value.value == null) {
                    arr1[w + 104] = 0;
                }
                else {
                    arr1[w + 104] = parseFloat(document.getElementById(Number).value);
                    if (w > 1) {
                        if (arr1[w + 104] != 0) {
//                            if (arr1[w + 104] < arr1[w + 103]) {
//                                alert("您的输入有误,筛孔通过率应逐级递减!");
//                                return false;
//                            }
                        }
                    }
                    $("#NC").val("NC");  //    $("#NV").val("NV");
                }
            }
            if (I == 9) {
                if (Value.value == "" || Value.value == null) {
                    arr1[w + 119] = 0;
                }
                else {
                    arr1[w + 119] = parseFloat(document.getElementById(Number).value);
                    if (w > 1) {
                        if (arr1[w + 119] != 0) {
//                            if (arr1[w + 119] < arr1[w + 118]) {
//                                alert("您的输入有误,筛孔通过率应逐级递减!");
//                                return false;
//                            }
                        }
                    }
                    $("#Max").val("Max");
                }
            }
            if (I == 10) {
                if (Value.value == "" || Value.value == null) {
                    arr1[w + 134] = 0;
                }
                else {
                    arr1[w + 134] = parseFloat(document.getElementById(Number).value);
                    if (w > 1) {
                        if (arr1[w + 134] != 0) {
//                            if (arr1[w + 134] < arr1[w + 133]) {
//                                alert("您的输入有误,筛孔通过率应逐级递减!");
//                                return false;
//                            }
                        }
                    }
                    $("#Min").val("Min");
                }
            }            
            if (result == 100) {
            	swal("操作失败","您的输入已超出最大值范围,请重新确认后再次输入并保存!", "info");
                return false;
            }
        }
    }
    // 通过
    var str_grade_Code = $("#str_grade_Code").val();
    var str_material_Name = $("#str_material_Name").find("option:selected").text();
    var str_material_Model = $("#str_material_Model").find("option:selected").text();
    if(str_grade_Code == null || str_grade_Code =="") {
    	swal("操作失败","级配编号不能为空!", "info");
    	return;
    }
    str_material_Name = str_material_Name.replace(/\s+/g,"");
	if(str_material_Name == null || str_material_Name == "" ) {
		swal("操作失败","产品名称不能为空!", "info");
		return;
	}
	str_material_Model = str_material_Model.replace(/\s+/g,"");
	if(str_material_Model == null || str_material_Model == "") {
		swal("操作失败","产品型号不能为空!", "info");
		return;
	}
	
    if (result != 100 && i == 11) {
    	obj = $("#add").serializeArray();
        var list = toJsonArr(obj);
        
        var bl = false;
    	for (var j = 0; j < obj.length; j++) {
    		if(obj[j].value!=""){
    			bl = true;
    		}
    	}
    	
    	if(bl==false){
    		swal("操作失败","筛孔不能为空!", "info");
    		return;
    	}
    	
        if(addOrupdateTag == 1) {
        	$.ajax({
         		type : "POST",
         		url :baseUrl + "/AsphaltGrading/addAsphalt_Grad.html",
         		data : {"list":list,
         			    "str_remarks":$("#str_remarks").val(),
         			    "str_grade_Code":$("#str_grade_Code").val(),
         			    "i_product_Id":$("#str_material_Model").val(),
         			    "i_org_Id":i_org_Id
         			    },
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
        					window.location.href = baseUrl + "/AsphaltGrading/asphaltGrading.html";
        				});
        				
        			}else{
        				swal("操作失败",data.message, "error");
        			}
         		}
         	});
        } else {
        	$.ajax({
         		type : "POST",
         		url :  baseUrl + "/AsphaltGrading/updateAsphalt_Grad.html",
         		data : {"list":list,
         			    "str_remarks":$("#str_remarks").val(),
         			    "str_grade_Code":$("#str_grade_Code").val(),
         			    "i_product_Id":$("#str_material_Model").val(),
         			    "i_id":$("#i_id").val(),
         			    },
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
        					window.location.href = baseUrl + "/AsphaltGrading/asphaltGrading.html";
        				});
        				
        			}else{
        				swal("操作失败",data.message, "error");
        			}
         			
         		}
         	});
        }
        
    }
    
}

function toJsonArr(obj) {
	var strList="[";
	for(var i = 0; i < obj.length; i++) {
		if(i % 16 == 0) {
			strList += "{\""+obj[i].name+"\":"+"\""+obj[i].value+"\"";
		} else {
			strList += "\""+obj[i].name+"\":"+"\""+obj[i].value+"\"";
		}
		if((i + 1) % 16 == 0) {
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

function show(i_id) {
	$("#str_grade_Code").attr("readonly","readonly");
	addOrupdateTag = 2;
	$("#tab input").val("");
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
	$.ajax({
		type : "POST",
		url : baseUrl + "/AsphaltGrading/getAsphalt_GradDetailedById.html",
		data : {"i_id":i_id},
		dataType : "json",
		success : function(data) {
			if(data[0].code == "error"){
        		swal("操作失败",data[0].message, "error");
        		return;
        	}
			$("#loginModal").modal("show")
			$("#i_id").val(data[0].i_grad_Id);
			$("#str_grade_Code").val(data[0].str_grade_Code);
			$("#str_remarks").val(data[0].str_remarks);
			$("#str_material_Name").html("<option selected='selected'>"+data[0].str_material_Name+"</option>");
			$("#str_material_Model").html("<option selected='selected' value='"+data[0].i_product_Id+"'>"+data[0].str_material_Model+"</option>");
			for(var i = 0; i < data.length; i++) {
				var str_warehouse_Num = data[i].str_warehouse_Num;
				switch(str_warehouse_Num)
				{
				case "N1":
					appendVal(data[i], "1");
					break;
				case "N2":
					appendVal(data[i], "2");
					break;
				case "N3":
					appendVal(data[i], "3");
					break;
				case "N4":
					appendVal(data[i], "4");
					break;
				case "N5":
					appendVal(data[i], "5");
					break;
			    case "N6":
			    	appendVal(data[i], "6");
			    	break;
				case "NH":
					appendVal(data[i], "7");
					break;
				case "NC":
					appendVal(data[i], "8");
					break;
				case "Max":
					appendVal(data[i], "9");
					break;
				default:
					appendVal(data[i], "10");
					break;
				}
			}
		}
	});
}

function appendVal(obj, num) {
	var id = "#"+num.toString()+"_";
	for(var key in obj) {
		switch(key)
		{
		case "d_a53":
			$(id+"1").val(obj.d_a53)
			break;
		case "d_a37_5":
			$(id+"2").val(obj.d_a37_5)
			break;
		case "d_a31_5":
			$(id+"3").val(obj.d_a31_5)
			break;
		case "d_a26_5":
			$(id+"4").val(obj.d_a26_5)
			break;
		case "d_a19":
			$(id+"5").val(obj.d_a19)
			break;
		case "d_a16":
			$(id+"6").val(obj.d_a16)
			break;
	    case "d_a13_2":
	    	$(id+"7").val(obj.d_a13_2)
			break;
		case "d_a9_5":
			$(id+"8").val(obj.d_a9_5)
			break;
		case "d_a4_75":
			$(id+"9").val(obj.d_a4_75)
			break;
		case "d_a2_36":
			$(id+"10").val(obj.d_a2_36)
			break;
		case "d_a1_18":
			$(id+"11").val(obj.d_a1_18)
			break;
		case "d_a0_6":
			$(id+"12").val(obj.d_a0_6)
			break;
		case "d_a0_3":
			$(id+"13").val(obj.d_a0_3)
			break;
		case "d_a0_15":
			$(id+"14").val(obj.d_a0_15)
			break;
		default:
			$(id+"15").val(obj.d_a0_075)
			break;
		}
	}
}

function NewappendVal(obj, num) {
	var id = "#"+num.toString()+"_";
	for(var key in obj) {
		switch(key)
		{
		case "d_a53":
			$(id+"1").html(obj.d_a53)
			break;
		case "d_a37_5":
			$(id+"2").html(obj.d_a37_5)
			break;
		case "d_a31_5":
			$(id+"3").html(obj.d_a31_5)
			break;
		case "d_a26_5":
			$(id+"4").html(obj.d_a26_5)
			break;
		case "d_a19":
			$(id+"5").html(obj.d_a19)
			break;
		case "d_a16":
			$(id+"6").html(obj.d_a16)
			break;
	    case "d_a13_2":
	    	$(id+"7").html(obj.d_a13_2)
			break;
		case "d_a9_5":
			$(id+"8").html(obj.d_a9_5)
			break;
		case "d_a4_75":
			$(id+"9").html(obj.d_a4_75)
			break;
		case "d_a2_36":
			$(id+"10").html(obj.d_a2_36)
			break;
		case "d_a1_18":
			$(id+"11").html(obj.d_a1_18)
			break;
		case "d_a0_6":
			$(id+"12").html(obj.d_a0_6)
			break;
		case "d_a0_3":
			$(id+"13").html(obj.d_a0_3)
			break;
		case "d_a0_15":
			$(id+"14").html(obj.d_a0_15)
			break;
		default:
			$(id+"15").html(obj.d_a0_075)
			break;
		}
	}
}

function del(i_id) {

	swal({
		title: "确定操作吗？",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
		
	},
	function(){
		$.ajax({
	 		type : "POST",
	 		url :  baseUrl + "/AsphaltGrading/deletAsphalt_Grad.html",
	 		data : {"i_id":i_id},
	 		dataType : "json",
	 		success : function(data) {
				if(data.code=="success"){
					setTimeout(function(){swal({
						title: data.message,
						type: "success",
						cancelButtonText: '确定',
						cancelButtonFont: '微软雅黑',
					},
					function(){
						window.location.href = baseUrl + "/AsphaltGrading/asphaltGrading.html";
					}); },200);
				}else{
					setTimeout(function(){swal(data.message,"","error"); },200);
				}
			}
		});
	
	});
}

function show2(obj,id,ids) { 
	for (var i = 100; i <= 110; i++) {
		for (var j = 1; j <= 15; j++) {
			var ist = "#"+i+"_"+j
			$(ist).empty(""); 
		}
	}
	
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display","block"); 
	$(objDiv).css("left", event.clientX); 
	$(objDiv).css("top", event.clientY + 10); 
	$("#tabIds  tr:not(:first)").html("");
	var param = {};
	param.i_id = ids;
	param.str_grade_Code='判定飘窗';
	$.ajax({
	    type: "post",
//	    contentType: 'application/json;charset=UTF-8',
	    data : param,
		dataType : "json",
	    url :  "../AsphaltGrading/getAsphalt_GradDetailedById.html",
//	    data: JSON.stringify(param),
//	    dataType: "json",
	    success: function (data) {
	    	
	    	if(data!=null){
	    		
	    		 if(data.length>0){
	    			
	    			 for (var i = 0; i < data.length; i++) {
	    				 $("#yjph").html(data[i].str_grade_Code);
			    		 $("#cpmc").html(data[i].str_material_Name);
	    				 switch(data[i].str_warehouse_Num)
	    					{
	    					case "N1":
	    						NewappendVal(data[i], "101");
	    						break;
	    					case "N2":
	    						NewappendVal(data[i], "102");
	    						break;
	    					case "N3":
	    						NewappendVal(data[i], "103");
	    						break;
	    					case "N4":
	    						NewappendVal(data[i], "104");
	    						break;
	    					case "N5":
	    						NewappendVal(data[i], "105");
	    						break;
	    				    case "N6":
	    				    	NewappendVal(data[i], "106");
	    				    	break;
	    					case "NH":
	    						NewappendVal(data[i], "107");
	    						break;
	    					case "NC":
	    						NewappendVal(data[i], "108");
	    						break;
	    					case "Max":
	    						NewappendVal(data[i], "109");
	    						break;
	    					default:
	    						NewappendVal(data[i], "110");
	    						break;
	    					}
	    				 
					}
	    		 }
	    	}
	    }
	});
} 
function hide(obj,id) { 
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display", "none"); 
} 


d.checkNode = function (id,i_parent_Id,flag,checked) {
	if(checked == true) {
		i_org_Id = id;
	} else {
		i_org_Id = "";
	}
	/* 查找nodes 名称  tongn 2018.6.22*/
	var objList = d.aNodes;
	for(var i=0,l=objList.length;i<l;i++){  
		
		if(objList[i].id == id){
			
			$("#locationText").text(objList[i].cshow);
		}
		  
	}
}

function search() {
	var table = $('#list').dataTable();
	table.fnDestroy();
	$('#list').DataTable({
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
		"ajax" : {
            type: "post",
            url: baseUrl+ "/AsphaltGrading/getAsphalt_Gradings.html",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_org_Id = i_org_Id;
                param.str_material_Name = $("#material_Name").val();
                param.str_material_Model = $("#material_Model").val();
                param.str_grade_Code = $("#grade_Code").val();
                return param;
            }
        },
		"deferRender" : true,
		"columns" : [ {
			"data" : "serialNumber"
		}, {
			"data" : "str_grade_Code"
		}, {
			"data" : "materNameAndModel"
		}, {
			"data" : "str_operator"
		}, {
			"data" : "str_create_Date"
		}, {
			"data" : "str_remarks"
		}, {
			"data" : "operate"
		} ],
		"createdRow" : function(row, data, dataIndex) {
//			$(row).children("td").eq(1).attr("onmouseover", "getDetailed('"+data.i_id+"');")
			$(row).children("td").eq(1).addClass("mbpb_xg");
			$(row).children("td").eq(1).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_grade_Code+"</a>");
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ]
	});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}