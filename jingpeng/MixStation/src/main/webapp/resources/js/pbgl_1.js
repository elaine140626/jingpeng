var baseUrl = "";
var i_org_Id = ""
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

function getList() {
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
		"ajax" : baseUrl + "/AsphTargetPro/getAsphTargetPro.html?i_org_Id="+i_org_Id,
		"deferRender" : true,
		"columns" : [ 
			{"data" : "serialNumber"}, 
			{"data" : "str_proportion_Code"},
			{"data" : "materNameAndModel"},
			{"data" : "str_operator"}, 
			{"data" : "str_create_Date"}, 
			{"data" : "str_remarks"}, 
			{"data" : "operate"} 
			],
		"createdRow" : function(row, data, dataIndex) {
			$(row).children("td").eq(1).addClass("mbpb_xg");
			//$(row).children("td").eq(1).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_proportion_Code+"</a>");
			$(row).children("td").eq(1).html("<a href='javascript:void(0);'>"+data.str_proportion_Code+"</a>");
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

/*function getDetailed(i_id) {
	alert(i_id);
}*/
//原材料名称
var YclName = "";
function getMaterialName(){
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialName.html",
		data : {"str_material_Type":0,
			"i_org_Id":i_org_Id,
			 "str_material_Mold":'1'},
		dataType : "json",
		success : function(data) {
			
			var html = "<option value='null' selected='selected'> </option>"
			for(var i = 0; i < data.length; i++) {
			
				html += "<option value='"+data[i].name+"'>"+data[i].name+"</option>"
			}
			$("#str_material_Name").empty();
			$("#str_material_Name").html(html);
		}
	});
	

	
	params = {"name":name, "i_org_Id":i_org_Id};
	$.ajax({
	type : "POST",
	url : baseUrl + "/AsphTargetPro/getRawMaterial.html",
	data : params,
	dataType : "json",
	success : function(data) {
		
		var html = "<option value='null' selected='selected'> </option>"
		for(var i = 0; i < data.length; i++) {
			html += "<option value='"+data[i].str_material_Name+"'>"+data[i].str_material_Name+"</option>"
		}
		YclName = html;
	}
	
});

}
//产品名称监听事件
function getOutName(){
    var index = $("#str_material_Name")[0].selectedOptions[0].index-1;
    if(index<0){
    	$("#i_product_Id").empty();
    	return;
    }
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialName.html",
		data : {"str_material_Type":0,"str_material_Mold":1,"i_org_Id":i_org_Id},
		dataType : "json",
		success : function(data) {
			
			
			console.log(data);
			var html =""
			for(var i = 0; i < data[index].model.length; i++) {
				html += "<option value='"+data[index].model[i].i_id+"'>"+data[index].model[i].str_material_Model+"</option>"
			}
			$("#i_product_Id").empty();
			$("#i_product_Id").html(html);
		}
	});
}

//原材料名称
function getYclName(str_material_Mold,obj){
	obj.empty();
	obj.append(YclName);
}
//原材料类型
function getYclModel(obj){
	var str_material_Name = obj.parent().parent().find("td:eq(0)").children().find("option:selected").text();
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialModels.html",
		data : {"str_material_Type":0,
		        "str_material_Mold":0,
		        "i_org_Id":i_org_Id,
		        "str_material_Name":str_material_Name},
		dataType : "json",
		success : function(data) {
			var html =  "<option value='null'></option>";
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>";
			}
			obj.parent().parent().find("td:eq(1)").children().empty();
			obj.parent().parent().find("td:eq(1)").children().append(html);
		}
	});
	
}
//function getMaterialName(str_material_Mold,obj){
//	var name = "";
//	var url = "";
//	var params = {};
//	if(str_material_Mold == 1) {
//		url = baseUrl + "/Common/getMaterialName.html";
//		params = {"str_material_Type":0,"str_material_Mold":str_material_Mold,"i_org_Id":i_org_Id};
//	} else {
//		url = baseUrl + "/AsphTargetPro/getRawMaterial.html";
//		name = "";
//		$(".nm").each(function(){
//			var value = $(this).find("option:selected").text();
//			if(value != "") {
//				name += value+","
//			}
//		});
//		var a = obj.find("option:selected").text();
//		if(a != "") {
//			name.replace(a+",","")
//		}
//		params = {"name":name, "i_org_Id":i_org_Id};
//	}
//	$.ajax({
//		type : "POST",
//		url : url,
//		data : params,
//		dataType : "json",
//		success : function(data) {
//			var html = ""
//			for(var i = 0; i < data.length; i++) {
//				html += "<option value='"+data[i].i_mateName_id+"'>"+data[i].str_material_Name+"</option>"
//			}
//			obj.empty();
//			obj.html(html);
//		}
//	});
//}

/*$(".nm").blur(function(){
	name = "";
	$(".nm").each(function(){
		var value = $(this).find("option:selected").text();
		if(value != "") {
			name += value+","
		}
	});
});*/

function clearModel(tag, obj){
	if(tag == 1 || tag == "1") {
		$("#i_product_Id").empty()
	} else {
		str_material_Name = obj.parent().parent().find("td:eq(3)").children().empty()
	}
}

function clearUpateModel(tag, obj){
	if(tag == 1 || tag == "1") {
		$("#upatei_product_Id").empty()
	} else {
		str_material_Name = obj.parent().parent().find("td:eq(3)").children().empty()
	}
}

//function getMaterialModel(str_material_Mold, tag, obj) {
//	var str_material_Name;
//	if(tag == 1 || tag == "1") {
//		str_material_Name = $("#str_material_Name").find("option:selected").text();
//	} else {
//		str_material_Name = obj.parent().parent().find("td:eq(1)").children().find("option:selected").text();
//	}
//	
//	$.ajax({
//		type : "POST",
//		url : baseUrl + "/Common/getMaterialModels.html",
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
//			obj.append(html);
//			
//		}
//	});
//}

function getUpdateMaterialModel(str_material_Mold, tag, obj) {
	var str_material_Name;
	if(tag == 1 || tag == "1") {
		str_material_Name = $("#updatestr_material_Name").find("option:selected").text();
	} else {
		str_material_Name = obj.parent().parent().find("td:eq(1)").children().find("option:selected").text();
	}
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/Common/getMaterialModel.html",
		data : {"str_material_Type":0,
		        "str_material_Mold":str_material_Mold,
		        "str_material_Name":str_material_Name},
		dataType : "json",
		success : function(data) {
			var html = "";
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>";
			}
			obj.empty();
			obj.append(html);
		}
	});
}

function addDetailed(tag) {
	var html = "<tr class='h50'>"
             /*  +"<td width='8%' align='right'>名称：</td>"*/
               +"<td width='18%' align='center'><select class='sekuan2' onfocus='getYclName(0,$(this));'  name='str_materials_Name' onchange='getYclModel($(this))' class='nm' >"
               +"</select></td>"
             /*  +"<td width='6%' align='right'>型号：</td>"*/
               +"<td width='16%'><select class='sekuan2' name='i_materials_Id'>"  
               +"<option value='' selected='selected'></option></select></td>"
               +"<td align='center'><input name='d_weight' type='text' placeholder='' size='5' onkeyup='num(this)'></td>"
               +"<td align='center'><input name='str_manufacturer' type='text' placeholder='' size='15' value=''></td>"
               +"<td align='center'><input name='str_material_Origin' type='text' placeholder='' size='10' value=''></td>"
               +"<td align='center'><a href='javascript:void(0)' onclick='delDetailed($(this))'><img src='../resources/images/del.png' width='16' height='16' value=''></a></td>"
               +"</tr>";
    if(tag == 0) {
    	$("#detailedTab").append(html);
    } else {
    	$("#upatedetailedTab").append(html);
    }
	
}

function delDetailed(obj) {
	obj.parent().parent().remove();
}

function addOrUpdate(tag) {
	
	
	$("#i_org_Id").val(i_org_Id)
	var obj;//目标配合比
	var obj1;
	var str_proportion_Code;//目标配合比编号
	var i_product_Id;//产品型号
	var str_material_Name;//产品名称
	var str_remarks;//时间
	var i_id;
	if(tag == 1) {
		obj = $('#asph_TargetPropList').serializeArray();
		//tongn update 判断配比明细
		obj1 = $('#asph_TargetPropList')[0];
		str_proportion_Code = $("#str_proportion_Code").val();
		i_product_Id = $("#i_product_Id").val();
		str_material_Name = $("#str_material_Name").find("option:selected").text();
		str_remarks = $("#str_remarks").val();
		
	} else {
		obj = $('#updateasph_TargetPropList').serializeArray();
		obj1 = $('#updateasph_TargetPropList')[0];
		str_proportion_Code = $("#updatestr_proportion_Code").val();
		i_product_Id = $("#updatei_product_Id").val();
		str_remarks = $("#updatestr_remarks").val();
		str_material_Name = $("#updatestr_material_Name").find("option:selected").text();
		i_id = $("#i_id").val();
	}
	if(str_proportion_Code == null || str_proportion_Code == "") {
		swal("操作失败","目标配合比编号不能为空！", "info");
		return;
	}
	str_material_Name = str_material_Name.replace(/\s+/g,"");
	if(str_material_Name == null || str_material_Name == "" ) {
		swal("操作失败","产品名称不能为空！", "info");
		return;
	}
	i_product_Id = i_product_Id.replace(/\s+/g,"");
	if(i_product_Id == null || i_product_Id == "") {
		swal("操作失败","产品型号不能为空！", "info");
		return;
	}
	
	//tongnan  判断配比明细   update
//	if(!check(obj1)) {
//		swal("操作失败","原材料信息至少要录入一条！", "info");
//		return;
//	}
	
	console.log(obj);
	
	var url = "";
	var num;
	if(tag == 1) {
		var tbody = document.getElementById("addIds1");
		url = baseUrl + "/AsphTargetPro/addAsphTargetPro.html";
	} else {
		var tbody = document.getElementById("addIds2");
		url = baseUrl + "/AsphTargetPro/updateAsphTargetPro.html";
	}

	
	
	var dataList = [];
    var rows = tbody.rows;
    var index = 0;
    var json = '[';
    var nms = 0;
    for (var i = 0; i <rows.length; i++) {
    	
    	if((tbody.rows.item(i).cells[0].childNodes[0].selectedOptions.length==0 || tbody.rows.item(i).cells[0].childNodes[0].selectedOptions[0].value=="")  && (tbody.rows.item(i).cells[1].childNodes[0].selectedOptions.length==0 || tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value=="")  && tbody.rows.item(i).getElementsByTagName("input")[0].value=="" && tbody.rows.item(i).getElementsByTagName("input")[1].value=="" && tbody.rows.item(i).getElementsByTagName("input")[2].value==""){
    		continue;
    	}else{
    		if(tbody.rows.item(i).cells[0].childNodes[0].selectedOptions[0].value=="null" || tbody.rows.item(i).cells[0].childNodes[0].selectedOptions[0].value==""){
    			swal("操作失败","请选择第"+(i+1)+"行原材料信息", "info");
    	    	return;
    		}
    		if(tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value=="null" || tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value==""){
    			swal("操作失败","请选择第"+(i+1)+"行原材料信息", "info");
    	    	return;
    		}
    		if(tbody.rows.item(i).getElementsByTagName("input")[0].value==""){
    			swal("操作失败","请输入第"+(i+1)+"行原材料比例", "info");
    	    	return;
    		}
    	}
    	var fh="";
    	
    	if(i+1<rows.length){
    		fh=",";
    	}
    	json+='{"str_materials_Name":"'+tbody.rows.item(i).cells[0].childNodes[0].selectedOptions[0].value+'",';
    	dataList[nms]=tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value;
    	nms++;
    	json+='"i_materials_Id":"'+tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value+'",';
    	json+='"d_weight":"'+tbody.rows.item(i).getElementsByTagName("input")[0].value+'",';
    	json+='"str_manufacturer":"'+tbody.rows.item(i).getElementsByTagName("input")[1].value+'",';
    	json+='"str_material_Origin":"'+tbody.rows.item(i).getElementsByTagName("input")[2].value+'"}'+fh;
	}
    
    
    json+="]";
  
  
    if(json=="[]"){
    	swal("操作失败","原材料信息不能为空", "info");
    	return;
    }
	
	var list = json;

	for (var i = 0; i < dataList.length; i++) {
		for (var j = 0; j < dataList.length-1; j++) {

			if(dataList[i]==dataList[j+i+1]){
				swal("操作失败","原材料信息不能重复！", "info");
				return;
			}
		}
	}
	$.ajax({
		type : "POST",
		url : url,
		data : {"str_proportion_Code":str_proportion_Code,
			    "i_product_Id":i_product_Id,
			    "str_remarks":str_remarks,
			    "i_id":i_id,
			    "i_org_Id":$("#i_org_Id").val(),
			    "list":list},
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
					window.location.href=baseUrl + "/AsphTargetPro/asphTargetPro.html";
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
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

//function validForm(str) {
//	var obj = JSON.parse(str);
//	var count1 = 0;
//	var count2 = 0;
//	for(var i = 0; i < obj.length; i++) {
//		if(obj[i].i_materials_Id == null || obj[i].i_materials_Id == ""){
//			
//			alert("原材料名称原材料型号和比例不能为空");
//			return false;
//						
//		}
//		if(obj[i].d_weight == null || obj[i].d_weight == "") {
//			
//			alert("比例不能为空");
//			return false;
//		}
//	}
///*	if(count1 == 0 || count1 < count2) {
//		alert("原材料名称原材料型号和比例不能为空");
//		return false;
//	}
//	if(count2 == 0 || count1 > count2) {
//		alert("比例不能为空");
//		return false;
//	}*/
//	return true;
//}

function num(obj){
	obj.value = obj.value.replace(/[^\d.]/g,""); 
	obj.value = obj.value.replace(/^\./g,"");
	obj.value = obj.value.replace(/\.{2,}/g,".");
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}
//修改的时候
function updateName(obj){
	obj.empty();
	obj.append(YclName);
}
function show(i_id) {
	getMaterialName();
	$("#str_grade_Code").attr("readonly","readonly");
	/*$(".globalLoginBtn2").on("click", login_popup),function() {
	    
	}*/
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
	
	$("#upatedetailedTab tbody ").empty();
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/AsphTargetPro/getAsphTargetProById.html",
		data : {"i_id":i_id},
		dataType : "json",
		success : function(data) {
			if(data.ca.code == "error"){
        		swal("操作失败",data.ca.message, "error");
        		return;
        	}
			$("#loginModal2").modal("show");
			var asphTargetPro = data.asphTargetPro;
			var asphTargetProDList = data.asphTargetProDList;
			$("#updatestr_proportion_Code").val(asphTargetPro.str_proportion_Code);
			$("#i_id").val(asphTargetPro.i_id);
			$("#updatestr_material_Name").html("<option selected='selected'>"+asphTargetPro.str_material_Name+"</option>");
			$("#updatei_product_Id").html("<option selected='selected' value='"+asphTargetPro.i_product_Id+"' selected='selected'>"+asphTargetPro.str_material_Model+"</option>");
			$("#updatestr_remarks").val(asphTargetPro.str_remarks);
			var h = "";
			
			for(var i = 0; i < asphTargetProDList.length; i++) {
				var str_manufacturer = asphTargetProDList[i].str_manufacturer;
				var str_material_Origin = asphTargetProDList[i].str_material_Origin;
				if (typeof(str_manufacturer) == "undefined") {
					str_manufacturer = "";
				}
				if(typeof(str_material_Origin) == "undefined") {
					str_material_Origin = "";
				}
				h += "<tr class='h50'>"
		            /*   +"<td width='8%' align='right'>名称：</td>"*/
		               +"<td width='18%' align='center'><select class='sekuan2' onfocus='updateName($(this));'  name='str_materials_Name'  onchange='getYclModel($(this))'>"
		               +"<option selected='selected'>"+asphTargetProDList[i].str_material_Name+"</option></select></td>"
		             /*  +"<td width='6%' align='right'>型号：</td>"*/
		               +"<td width='16%'><select class='sekuan2' onfocus='getMaterialModel(0,2,$(this));' name='i_materials_Id'>"  
		               +"<option selected='selected' value='"+asphTargetProDList[i].i_materials_Id+"'>"+asphTargetProDList[i].str_material_Model+"</option></select></td>"
		               +"<td align='center'><input name='d_weight' type='text' placeholder='' size='5' onkeyup='num(this)' value='"+asphTargetProDList[i].d_weight+"'>"
		               +"</td>"
		               +"<td align='center'><input name='str_manufacturer' type='text' placeholder='' size='15' value='"+str_manufacturer+"'></td>"
		               +"<td align='center'><input name='str_material_Origin' type='text' placeholder='' size='10' value='"+str_material_Origin+"'></td>"
		               +"<td align='center'><a href='javascript:void(0)' onclick='delDetailed($(this))'><img src='../resources/images/del.png' width='16' height='16' value=''></a></td>"
		               +"</tr>";
			}
			var xhcs = asphTargetProDList.length-5;
			if(xhcs<0){
				xhcs = Math.abs(xhcs);
				for (var j = 0; j < xhcs; j++) {
					h += "<tr class='h50'>"
			               +"<td width='18%' align='center'><select class='sekuan2' onfocus='updateName($(this));'  name='str_materials_Name'  onchange='getYclModel($(this))'>"
			               +"<option selected='selected'></option></select></td>"
			               +"<td width='16%'><select class='sekuan2' onfocus='getMaterialModel(0,2,$(this));' name='i_materials_Id'>"  
			               +"<option selected='selected' value=''></option></select></td>"
			               +"<td align='center'><input name='d_weight' type='text' placeholder='' size='5' onkeyup='num(this)' value=''>"
			               +"</td>"
			               +"<td align='center'><input name='str_manufacturer' type='text' placeholder='' size='15' value=''></td>"
			               +"<td align='center'><input name='str_material_Origin' type='text' placeholder='' size='10' value=''></td>"
			               +"<td align='center'><a href='javascript:void(0)' onclick='delDetailed($(this))'><img src='../resources/images/del.png' width='16' height='16' value=''></a></td>"
			               +"</tr>";
				}
			}
			$("#upatedetailedTab").append(h);
		}
	});
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
			url : baseUrl + "/AsphTargetPro/delAsphTargetPro.html",
			data : {"i_id":i_id,
				"i_org_Id":i_org_Id},		
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
						window.location.href=baseUrl + "/AsphTargetPro/asphTargetPro.html";
					}); },200);
				}else{
					setTimeout(function(){swal(data.message,"","error"); },200);
				}
			}
		});
	
	});
}

function login_popup() {
	$("#str_grade_Code").removeAttr("readonly");
	$("#loginModal [type='text']").val("");
	$("#loginModal select").html("<option></option>");
	$("#loginModal textarea").val("");
    $("#loginModal").modal("show");
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
}

function show2(obj,id,ids) { 
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display","block"); 
	$(objDiv).css("left", event.clientX); 
	$(objDiv).css("top", event.clientY + 10); 
	
	var param = {};
	param.i_id = ids;
	$.ajax({
	    type: "post",
//	    contentType: 'application/json;charset=UTF-8',
	    data : param,
		dataType : "json",
	    url :  "../AsphTargetPro/getAsphTargetProById.html",
//	    data: JSON.stringify(param),
//	    dataType: "json",
	    success: function (data) {
	    	if(data!=null){
	    		 $("#nr_l").html(data.asphTargetPro.str_proportion_Code);
	    		 $("#xh_l").html(data.asphTargetPro.str_material_Name+"-"+data.asphTargetPro.str_material_Model);
	    		 var html = "";
	    		 if(data.asphTargetProDList.length>0){
	    			 for (var i = 0; i < data.asphTargetProDList.length; i++) {
	    				 html+="<tr> <td>"+(i+1)+"</td><td>"+data.asphTargetProDList[i].str_material_Name+data.asphTargetProDList[i].str_material_Model+"</td><td align='center'>"+data.asphTargetProDList[i].d_weight+"</td> </tr>"
					}
	    			 $("#nr_l_new").html(html);
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
            url: baseUrl + "/AsphTargetPro/getAsphTargetPro.html",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_org_Id = i_org_Id;
                param.str_material_Name = $("#material_Name").val();
                param.str_material_Model = $("#material_Model").val();
                param.str_proportion_Code = $("#proportion_Code").val();
                return param;
            }
        },
		"deferRender" : true,
		"columns" : [ 
			{"data" : "serialNumber"}, 
			{"data" : "str_proportion_Code"},
			{"data" : "materNameAndModel"},
			{"data" : "str_operator"}, 
			{"data" : "str_create_Date"}, 
			{"data" : "str_remarks"}, 
			{"data" : "operate"} 
			],
		"createdRow" : function(row, data, dataIndex) {
			$(row).children("td").eq(1).addClass("mbpb_xg");
			$(row).children("td").eq(1).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_proportion_Code+"</a>");
		},
		"columnDefs" : [ {
			"targets" : [ 0 ],
			"visible" : true,
			"searchable" : false
		} ]
		
	});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}




function check(myform){
	var flag = false;
	for(var i=0;i<myform.length;i++){ //循环form表单
	if(!myform.elements[i].value==""){ //判断每一个元素是否为空
	
		flag = true;
		break;
	}
	}
	
	return flag;
}