var baseUrl = "";
var userName = '';
$(function() {
	baseUrl = getContextPath();
	$("#locationText").html(orgName);
	// 获取当前用户信息
	$.ajax({
		type: "post",
		url: baseUrl + "/login/getSessionUserInfo.action",
		data:{
			"MixStationType":0,
		},
		dataType: "json",
		async:false,
		success: function (data) {
			data = data.bean;
			userInfo = data.userInfo;
			userName = userInfo.name;
		}
	});
	search();
});

function getList(param) {
	var table = $('#asph_TargetProList').dataTable();
	table.fnDestroy();
	$('#asph_TargetProList').DataTable({
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
	           url: baseUrl + "/AsphTargetPro/getAsphTargetPro.action",
	           dataSrc: "data",
	           data: function (d) {
	               return param;//自定义需要传递的参数。
	           }
	       },
		"deferRender" : true,
		"columns" :
			[{
      	        "data": "proportion_Code"
	        },{
	            "data": "proportion_Code",
	            render:function(data,type,row,meta) {
  	            	var html = '';
  		  		    html += "<span class='globalLoginBtn'><a onclick=update(\""+row.id+"\")><img src=\"img/images/update.png\" width=\"16\" height=\"17\" alt=\"修改\" title=\"修改\"></a></span><span><a onclick=del(\""+row.id+"\")><img src=\"img/images/del.png\" width=\"16\" height=\"17\" alt=\"删除\" title=\"删除\"></a></span>";
  		  		    return html;
            }},{
      	        "data": "proportion_Code"
	        }, {
	            "data": "materNameAndModel"
	        }, {
	            "data": "operator"
	        }, {
	            "data": "create_Date"
	        }, {
	            "data": "remarks"   
	        }],
	        "fnRowCallback" : function(nRow, aData, iDisplayIndex){  
	        	var html = '';
	        	html += iDisplayIndex +1;        	
	            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一
	            return nRow;
	        },
	});
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}
//查询
function search(){
	var param = {};
	//组织id
	param.org_Id= orgId;
	//目标配合比编号
	var proportion_Code = $("#proportion_Code").val();
	param.proportion_Code = proportion_Code;
	//产品名称
	var material_Name = $("#material_Name").val();
	param.material_Name = material_Name;
	//产品型号
	var material_Model = $("#material_Model").val();
	param.material_Model = material_Model;
	getList(param);

}

//产成品名称
function getMaterialName(){
	$.ajax({
		type : "post",
		url :  baseUrl +"/material/getMaterialName.action",
		data : {
			"orgId" : orgId,
			"materialType" : 0,
		},
		async : false,
		dataType : "json",
		success : function(data) {
			var html = "<option value='' selected='selected'></option>"
			var html_1 = "<option value='' selected='selected'></option>"
				for(var i = 0; i < data.length; i++) {
					if(data[i].materialMold == 1){
						html += "<option value='"+data[i].mateName+"'>"+data[i].mateName+"</option>"
					}else{
						html_1 += "<option value='"+data[i].mateName+"'>"+data[i].mateName+"</option>"
					}
				}
				$("#str_material_Name").empty();
				$("#str_material_Name").html(html);
				YclName = html_1;
		}
	});
}
//产成品型号
function getOutName(){
	var materialName = $("#str_material_Name").val();
    var index = $("#str_material_Name")[0].selectedOptions[0].index-1;
    if(index<0){
    	$("#str_material_Model").empty();
    	return;
    }
	$.ajax({
		type : "POST",
		url : baseUrl +"/material/getMaterialList.action",
		data : {"materialType":0,"materialMold":1,"orgId":orgId,"materialName":materialName},
		dataType : "json",
		success : function(data) {
			var html =""
			for(var i = 0; i < data.data.length; i++) {
				html += "<option value='"+data.data[i].id+"'>"+data.data[i].mateModel+"</option>"
			}
			$("#str_material_Model").empty();
			$("#str_material_Model").html(html);
		}
	});
}
//原材料名称
function getYclName(str_material_Mold,obj){
	obj.empty();
	obj.append(YclName);
}
//修改时原材料名称
function updateName(obj){
	obj.empty();
	obj.append(YclName);
}
//原材料型号
function getYclModel(obj){
	var materialName = obj.parent().parent().find("td:eq(0)").children().find("option:selected").text();
	$.ajax({
		type : "POST",
		url : baseUrl +"/material/getMaterialList.action",
		data : {"materialType":0,
		        "materialMold":0,
		        "orgId":orgId,
		        "materialName":materialName},
		dataType : "json",
		success : function(data) {
			var html =  "<option value=''></option>";
			for(var i = 0; i < data.data.length; i++) {
				html += "<option value='"+data.data[i].id+"'>"+data.data[i].mateModel+"</option>";
			}
			obj.parent().parent().find("td:eq(1)").children().empty();
			obj.parent().parent().find("td:eq(1)").children().append(html);
		}
	});
	
}
//动态添加行
function addDetailed(tag) {
	var html = "<tr class='h50'>"
               +"<td width='18%' align='center'><select class='sekuan2' onfocus='getYclName(0,$(this));'  name='str_materials_Name' onchange='getYclModel($(this))' class='nm' >"
               +"</select></td>"
               +"<td width='16%'><select class='sekuan2' name='i_materials_Id'>"  
               +"<option value='' selected='selected'></option></select></td>"
               +"<td align='center'><input name='d_weight' type='text' placeholder='' size='5' onkeyup='num(this)'></td>"
               +"<td align='center'><input name='str_manufacturer' type='text' placeholder='' size='15' value=''></td>"
               +"<td align='center'><input name='str_material_Origin' type='text' placeholder='' size='10' value=''></td>"
               +"<td align='center'><a href='javascript:void(0)' onclick='delDetailed($(this))'><img src='img/images/del.png' width='16' height='16' value=''></a></td>"
               +"</tr>";
    if(tag == 0) {
    	$("#detailedTab").append(html);
    } else {
    	$("#upatedetailedTab").append(html);
    }
}
//动态删减行
function delDetailed(obj) {
	obj.parent().parent().remove();
}

//新增界面打开
function login_popup() {
    $("#loginModal").modal("show");
}
//点击触发界面
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
//新增/修改目标配合比  tag: 1=新增  2=修改
function addOrUpdate(tag) {
	var str_proportion_Code;		//目标配合比编号
	var i_product_Id;				//产品型号
	var str_material_Name;			//产品名称
	var str_remarks;				//备注
	var i_id;
	var operator = userName;
	if(tag == 1) {
		str_proportion_Code = $("#str_proportion_Code").val();
		i_product_Id = $("#str_material_Model").val();
		str_material_Name = $("#str_material_Name").find("option:selected").text();
		str_remarks = $("#str_remarks").val();
		
	} else {
		str_proportion_Code = $("#updatestr_proportion_Code").val();
		i_product_Id = $("#updatestr_material_Model").val();
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
	//自定义url地址
	var url = "";
	var num;
	if(tag == 1) {
		var tbody = document.getElementById("addIds1");
		url = baseUrl + "/AsphTargetPro/addAsphTargetPro.action";
	} else {
		var tbody = document.getElementById("addIds2");
		url = baseUrl + "/AsphTargetPro/updateAsphTargetPro.action";
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
			    "i_org_Id":orgId,
			    "operator":operator,
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
					$('#loginModal').modal('hide');
					$('#loginModal2').modal('hide');
					search();
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
}
//删除目标配合比
function del(id) {
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
			url : baseUrl + "/AsphTargetPro/delAsphTargetPro.action",
			data : {"id":id,
				"i_org_Id":orgId},		
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
						$('#loginModal').modal('hide');
						$('#loginModal2').modal('hide');
						search();
					}); },200);
				}else{
					setTimeout(function(){swal(data.message,"","error"); },200);
				}
			}
		});
	
	});
}

//修改目标配合比赋值
function update(id) {
	getMaterialName();
	$("#str_grade_Code").attr("readonly","readonly");
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
		url : baseUrl + "/AsphTargetPro/getAsphTargetProById.action",
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			if(data.c1.code == 300){
        		swal("操作失败",data.c1.message, "error");
        		return;
        	}
			$("#loginModal2").modal("show");
			var asphTargetPro = data.asphTargetPro;
			var asphTargetProDList = data.asphTargetProDList;
			$("#updatestr_proportion_Code").val(asphTargetPro.proportion_Code);
			$("#i_id").val(asphTargetPro.id);
			$("#updatestr_material_Name").html("<option selected='selected'>"+asphTargetPro.material_Name+"</option>");
			$("#updatestr_material_Model").html("<option selected='selected' value='"+asphTargetPro.product_Id+"' selected='selected'>"+asphTargetPro.material_Model+"</option>");
			$("#updatestr_remarks").val(asphTargetPro.remarks);
			var h = "";
			for(var i = 0; i < asphTargetProDList.length; i++) {
				var str_manufacturer = asphTargetProDList[i].Manufacturer;
				var str_material_Origin = asphTargetProDList[i].Material_Origin;
				if (typeof(str_manufacturer) == "undefined") {
					str_manufacturer = "";
				}
				if(typeof(str_material_Origin) == "undefined") {
					str_material_Origin = "";
				}
				h += "<tr class='h50'>"
		               +"<td width='18%' align='center'><select class='sekuan2' onfocus='updateName($(this));'  name='str_materials_Name'  onchange='getYclModel($(this))'>"
		               +"<option selected='selected'>"+asphTargetProDList[i].Material_Name+"</option></select></td>"
		               +"<td width='16%'><select class='sekuan2' onfocus='getMaterialModel(0,2,$(this));' name='i_materials_Id'>"  
		               +"<option selected='selected' value='"+asphTargetProDList[i].materials_Id+"'>"+asphTargetProDList[i].Material_Model+"</option></select></td>"
		               +"<td align='center'><input name='d_weight' type='text' placeholder='' size='5' onkeyup='num(this)' value='"+asphTargetProDList[i].d_weight+"'>"
		               +"</td>"
		               +"<td align='center'><input name='str_manufacturer' type='text' placeholder='' size='15' value='"+asphTargetProDList[i].Manufacturer+"'></td>"
		               +"<td align='center'><input name='str_material_Origin' type='text' placeholder='' size='10' value='"+asphTargetProDList[i].Material_Origin+"'></td>"
		               +"<td align='center'><a href='javascript:void(0)' onclick='delDetailed($(this))'><img src='img/images/del.png' width='16' height='16' value=''></a></td>"
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
			               +"<td align='center'><a href='javascript:void(0)' onclick='delDetailed($(this))'><img src='img/images/del.png' width='16' height='16' value=''></a></td>"
			               +"</tr>";
				}
			}
			$("#upatedetailedTab").append(h);
		}
	});
}



//原材料比例格式
function num(obj){
	obj.value = obj.value.replace(/[^\d.]/g,""); 
	obj.value = obj.value.replace(/^\./g,"");
	obj.value = obj.value.replace(/\.{2,}/g,".");
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}

//验证只能是字母和数字 
function checkZmOrNum(){ 
	var zmnum = $("#str_proportion_Code").val();
	var zmnumReg=/^[0-9a-zA-Z]*$/; 
	if(zmnum!=""&&!zmnumReg.test(zmnum)){ 
		setTimeout(function(){swal({
			title: "只能输入是字母或者数字,请重新输入",
			type: "error",
			cancelButtonText: '确定',
			cancelButtonFont: '微软雅黑',
		},
		function(){
			$("#str_proportion_Code").val("");
		}); },200);
	return false; 
} 
} 