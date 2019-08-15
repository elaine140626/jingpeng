var addOrUpdate = 0;
var baseUrl = "";
var i_org_Id = "";
$(function () {

    /* baseUrl = getContextPath();*/
	$.ajax({
        type: "post",
        url: "../Common/getUserName.action",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            $(".userid").html(data.str_name)
        }
 })
    
    
 i_org_Id = orgid;
    

    $("input[type='radio']").each(function(){
		var cshow = $(this).attr("cshow");
		if(cshow == orgname) {
			$(this).attr('checked', 'checked');
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
        "ajax" : "../cementTheory/getCement_TheoryProportions.action?i_org_Id="+i_org_Id,
        "deferRender" : true,
        "columns" : [{
            "data" : "serialNumber"
        }, {
            "data" : "str_prop_Code"
        }, {
            "data" : "material_Model"
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
        	$(row).children('td').eq(2).html(data.material_Name+"/"+data.material_Model)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
            $(row).children("td").eq(1).addClass("mbpb_xg");
			//$(row).children("td").eq(1).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_prop_Code+"</a>");
        	$(row).children("td").eq(1).html("<a href='javascript:void(0);'>"+data.str_prop_Code+"</a>");
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
    $('#str_prop_Code').blur(function(){
        $.ajax({
            type: "post",
            async: false,
            url: "../cementTheory/getCementProportionCode.action",
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
//    $('#str_material_Name').focus(function(){})
    
})

function getMaterialName(obj){
	$(obj).parent().parent().find("td:eq(2)").children().find("option:selected").empty();
	obj.empty();
	obj.html(clName);
}

function getStrName(){
	var name = $("#str_material_Name").val();
	if(name!=null){
		return;
	}
	 $.ajax({
       type: "post",
       async: false,
       url: "../Common/getMaterialNames.action",
       dataType: "json",

       data:{"str_material_Type":1,"str_material_Mold":1,"i_org_Id":i_org_Id},
       success: function (data) {
          
           var html  = "<option value='null'></option>"
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].str_material_Name+"'>"+data[i].str_material_Name+"</option>";
			}
			 $("#str_material_Name").empty();
	         $("#str_material_Name").html(html);
       }
   });


}
var clName = "";
function getStrModel(){
	
	var params = {};
	params = {"str_material_Type":1,"str_material_Mold":1,"str_material_Name":$("#str_material_Name").val(),"i_org_Id":i_org_Id};
	
	$.ajax({
        type: "post",
        async: false,
        url: "../Common/getMaterialModels.action",
        data: params,
        dataType: "json",
        success: function (data) {

        	 var html  = "<option value='null'></option>"
     			for(var i = 0; i < data.length; i++) {
     				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>";
     			}
     			$("#str_material_Model").empty();
     	        $("#str_material_Model").html(html);
        }
    });	
	
	$.ajax({
        type: "post",
        async: false,
        url: "../Common/getMaterialNames.action",
        dataType: "json",

        data:{"str_material_Type":1,"str_material_Mold":0,"i_org_Id":i_org_Id},
        success: function (data) {
        	 var html  = "<option value='null'></option>"
      			for(var i = 0; i < data.length; i++) {
      				html += "<option value='"+data[i].str_material_Name+"'>"+data[i].str_material_Name+"</option>";
      			}
        	 	clName = html;
        	
        }
    });
	
}

function getStrModels(){
	
	var params = {};

	params = {"str_material_Type":1,"str_material_Mold":1,"str_material_Name":$("#upDateStr_material_Name").val(),"i_org_Id":i_org_Id};

	
	$.ajax({
        type: "post",
        async: false,
        url: "../Common/getMaterialModels.action",
        data: params,
        dataType: "json",
        success: function (data) {

        	 var html  = "<option value='null'></option>"
     			for(var i = 0; i < data.length; i++) {
     				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>";
     			}
     			$("#str_material_Model").empty();
     	        $("#str_material_Model").html(html);
        }
    });	
}

function toInt(number) {
    return number*1 | 0 || 0;
}


var params = {};
function addCement_T(cs) {
	var url = '';
	if(cs==2){
		var str_prop_Code = $("#upDateStr_prop_Code").val()
	    var d_water_Cement_Ratio = $("#upDateD_water_Cement_Ratio").val()
	    var d_sand_Ratio = $("#upDateD_sand_Ratio").val()
	    var str_design_Strength = $("#upDateStr_design_Strength").val()
	    var i_slump_Low = $("#upDateI_slump_Low").val()
	    var i_slump_High = $("#upDateI_slump_High").val()
	    var str_operator = $("#upDateStr_operator").val()
	    var str_material_Name = $("#upDateStr_material_Name").val();
	    var i_product_Id = $("#upDateStr_material_Model").val();
	    var str_remarks = $("#upDateStr_remarks").val();
	   
	    if(str_prop_Code==null || str_prop_Code==""){
	    	/*alert("理论配比编号不能为空");*/
	    	swal("操作失败","理论配比编号不能为空!", "info");
	    	return;
	    }
	    if(str_material_Name==null || str_material_Name=="" || str_material_Name=="null"){
	    	/*alert("产品名称不能为空");*/
	    	swal("操作失败","产品名称不能为空!", "info");
	    	return;
	    }
	    if(i_product_Id==null || i_product_Id=="" || i_product_Id=="null"){
	    	/*alert("规格型号不能为空");*/
	    	swal("操作失败","规格型号不能为空!", "info");
	    	return;
	    }
	    if(d_water_Cement_Ratio==null || d_water_Cement_Ratio==""){
	    	/*alert("设计水灰比不能为空");*/
	    	swal("操作失败","设计水灰比不能为空!", "info");
	    	return;
	    }else{
	    	
	    	 if(isNaN(d_water_Cement_Ratio)==true){
		    		swal("操作失败","设计水灰比只能是整数或小数", "info");
		    			return;
		    	}else if(toInt(d_water_Cement_Ratio)>100){
		    		swal("操作失败","设计水灰比不能大于100", "info");
	    			return;
		    	}
	    }
	    if(d_sand_Ratio==null || d_sand_Ratio==""){
	    	/*alert("砂率不能为空");*/
	    	swal("操作失败","砂率不能为空", "info");
	    	return;
	    }else{
	    	 if(isNaN(d_sand_Ratio)==true){
		    		swal("操作失败","砂率只能是整数或小数", "info");
		    			return;
		    	}else if(toInt(d_sand_Ratio)>100){
		    		swal("操作失败","砂率不能大于100", "info");
	    			return;
		    	}
	    }
	    if(str_design_Strength==null || str_design_Strength==""){
	    	/*alert("设计强度不能为空");*/
	    	swal("操作失败","设计强度不能为空", "info");
	    	return;
	    }else{

	    	 if(isNaN(str_design_Strength)==true){
		    		swal("操作失败","设计强度只能是整数或小数", "info");
		    			return;
		    	}else if(toInt(str_design_Strength)>100){
		    		swal("操作失败","设计强度不能大于100", "info");
	    			return;
		    	}
	    	
	    }
	    if(i_slump_Low==null || i_slump_Low=="" || i_slump_High==null || i_slump_High==""){
	    	/*alert("坍落度不能为空");*/
	    	 swal("操作失败","坍落度不能为空", "info");
	    	return;
	    }
	    else{
	    	var regu = /^[1-9]\d*$/;
	    	
	    	 if (!regu.test(i_slump_Low)) {
	    		 
	    		/* alert("设计水灰应输入1-100的正整数");*/
	    		 swal("操作失败","坍落度应输入1-100的正整数!", "info");
	 	    
	 	    	return;
	    	 }
	    	 if (!regu.test(i_slump_High)) {
	    		 
		    		 swal("操作失败","坍落度应输入1-100的正整数!", "info");
		 	    
		 	    	return;
		    }
	    	 
	    	 if(toInt(i_slump_Low)>100){
		    		swal("操作失败","坍落度不能大于100", "info");
	    			return;
		     }
	    	 
	    	 if(toInt(i_slump_High)>100){
		    		swal("操作失败","坍落度不能大于100", "info");
	    			return;
		     }
	    }
	    
 var tbody = document.getElementById("tbIds2");
	    
	    var rows = tbody.rows;
	    var index = 0;
	    var json = '[';
	    var nms = 0;
	    for (var i = 0; i <rows.length; i++) {
	    	
	    	
	    	var fh="";
	    	
	    	if((tbody.rows.item(i).cells[1].childNodes[0].selectedOptions.length==0 || tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value=="") && (tbody.rows.item(i).cells[2].childNodes[0].selectedOptions.length==0 || tbody.rows.item(i).cells[2].childNodes[0].selectedOptions[0].value=="") && tbody.rows.item(i).getElementsByTagName("input")[0].value=="" && tbody.rows.item(i).getElementsByTagName("input")[1].value=="" && tbody.rows.item(i).getElementsByTagName("input")[2].value==""){
	    		continue;
	    	}

	    	if(i+1<rows.length){
	    		fh=",";
	    	}
	    	if(tbody.rows.item(i).cells[1].childNodes[0].selectedOptions.length>0){
	    		if(tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value=="" || tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value==null || tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value=="null"){
	    			swal("操作失败","请选择第"+(i+1)+"行原材料信息！", "info");
	    			return;
	    		}
	    		json+='{"str_material_Name":"'+tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value+'",';
	    	}else{
	    		swal("操作失败","请选择第"+(i+1)+"行原材料信息！", "info");
	    		return;
	    	}
	    	
	    	if(tbody.rows.item(i).cells[2].childNodes[0].selectedOptions.length>0){
	    		if(tbody.rows.item(i).cells[2].childNodes[0].selectedOptions[0].value=="" || tbody.rows.item(i).cells[2].childNodes[0].selectedOptions[0].value==null || tbody.rows.item(i).cells[2].childNodes[0].selectedOptions[0].value=="null"){
	    			swal("操作失败","请选择第"+(i+1)+"行原材料信息！", "info");
	    			return;
	    		}
	    		json+='"i_materials_Id":"'+tbody.rows.item(i).cells[2].childNodes[0].selectedOptions[0].value+'",';
	    	}else{
	    		swal("操作失败","请选择第"+(i+1)+"行原材料信息！", "info");
	    		return;
	    	}
	    	
	    	if(tbody.rows.item(i).getElementsByTagName("input")[0].value!=""){
	    		json+='"d_weight":"'+tbody.rows.item(i).getElementsByTagName("input")[0].value+'",';
	    		index++;
	    	}else{
	    		swal("操作失败","请输入第"+(i+1)+"行原材料用量！", "info");
	    		return;
	    	}
	    	if(tbody.rows.item(i).getElementsByTagName("input")[1].value!=""){
	    		json+='"str_Manufacturer":"'+tbody.rows.item(i).getElementsByTagName("input")[1].value+'",';
				index++;
	    	}else{
	    		json+='"str_Manufacturer":"",';
				index++;
	    	}
	    	if(tbody.rows.item(i).getElementsByTagName("input")[2].value!=""){
	    		json+='"str_material_Origin":"'+tbody.rows.item(i).getElementsByTagName("input")[2].value+'"}'+fh;
				index++;
	    	}else{
	    		json+='"str_material_Origin":""}'+fh;
				index++;
	    	}
			
			
		}
	    json+="]";

	    if(json=="[]"){
    		swal("操作失败","原材料信息不能为空", "info");
	    	return;
	    }
	    
	    
		var list = json;
		var dtList = eval('(' + json + ')'); 
		var dataList = [];
		
		
		//之后调整
		for (var i = 0; i < dtList.length; i++) {
			dataList[i]=dtList[i].i_materials_Id;
		}
		
		for (var i = 0; i < dataList.length; i++) {
			for (var j = 0; j < dataList.length-1; j++) {

				if(dataList[i]==dataList[j+i+1]){
					swal("操作失败","原材料信息不能重复", "info");
					return;
				}
			}
		}
		
		url = '../cementTheory/updateCement_TheoryProportion.action';
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
	}else{
		var str_prop_Code = $("#str_prop_Code").val()
	    var d_water_Cement_Ratio = $("#d_water_Cement_Ratio").val()
	    var d_sand_Ratio = $("#d_sand_Ratio").val()
	    var str_design_Strength = $("#str_design_Strength").val()
	    var i_slump_Low = $("#i_slump_Low").val()
	    var i_slump_High = $("#i_slump_High").val()
	    var str_operator = $("#str_operator").val()
	    var str_material_Name = $("#str_material_Name").val();
	    var i_product_Id = $("#str_material_Model").val();
	    var str_remarks = $("#str_remarks").val();
	   
	    
	    
	    
//	    if(str_prop_Code==null || str_prop_Code==""){
//	    	alert("理论配比编号不能为空");
//	    	return;
//	    }
	    if(str_prop_Code==null || str_prop_Code==""){
	    	/*alert("理论配比编号不能为空");*/
	    	swal("操作失败","理论配比编号不能为空", "info");
	    	return;
	    }
	    if(str_material_Name==null || str_material_Name=="" || str_material_Name=="null"){
	    	/*alert("产品名称不能为空");*/
	    	swal("操作失败","产品名称不能为空", "info");
	    	return;
	    }
	    if(i_product_Id==null || i_product_Id=="" || i_product_Id=="null"){
	    	/*alert("规格型号不能为空");*/
	    	swal("操作失败","规格型号不能为空", "info");
	    	return;
	    }
	    if(d_water_Cement_Ratio==null || d_water_Cement_Ratio==""){
	    	/*alert("设计水灰比不能为空");*/
	    	swal("操作失败","设计水灰比不能为空!", "info");
	    	return;
	    }else{
	    	
	    
	    	 if(isNaN(d_water_Cement_Ratio)==true){
		    		swal("操作失败","设计水灰比只能是整数或小数", "info");
		    			return;
		    	}else if(toInt(d_water_Cement_Ratio)>100){
		    		swal("操作失败","设计水灰比不能大于100", "info");
	    			return;
		    	}
	    }
	    if(d_sand_Ratio==null || d_sand_Ratio==""){
	    	/*alert("砂率不能为空");*/
	    	swal("操作失败","砂率不能为空", "info");
	    	return;
	    }else{
	    	 if(isNaN(d_sand_Ratio)==true){
		    		swal("操作失败","砂率只能是整数或小数", "info");
		    			return;
		    	}else if(toInt(d_sand_Ratio)>100){
		    		swal("操作失败","砂率不能大于100", "info");
	    			return;
		    	}
	    }
	    if(str_design_Strength==null || str_design_Strength==""){
	    	/*alert("设计强度不能为空");*/
	    	swal("操作失败","设计强度不能为空", "info");
	    	return;
	    }else{

	    	 if(isNaN(str_design_Strength)==true){
		    		swal("操作失败","设计强度只能是整数或小数", "info");
		    			return;
		    	}else if(toInt(str_design_Strength)>100){
		    		swal("操作失败","设计强度不能大于100", "info");
	    			return;
		    	}
	    	
	    }
	    if(i_slump_Low==null || i_slump_Low=="" || i_slump_High==null || i_slump_High==""){
	    	/*alert("坍落度不能为空");*/
	    	 swal("操作失败","坍落度不能为空", "info");
	    	return;
	    }
	    else{
	    	var regu = /^[1-9]\d*$/;
	    	
	    	 if (!regu.test(i_slump_Low)) {
	    		 
	    		/* alert("设计水灰应输入1-100的正整数");*/
	    		 swal("操作失败","坍落度应输入1-100的正整数!", "info");
	 	    
	 	    	return;
	    	 }
	    	 if (!regu.test(i_slump_High)) {
	    		 
		    		 swal("操作失败","坍落度应输入1-100的正整数!", "info");
		 	    
		 	    	return;
		    }
	    	 
	    	 if(toInt(i_slump_Low)>100){
		    		swal("操作失败","坍落度不能大于100", "info");
	    			return;
		     }
	    	 
	    	 if(toInt(i_slump_High)>100){
		    		swal("操作失败","坍落度不能大于100", "info");
	    			return;
		     }
	    }
	    var tbody = document.getElementById("tbIds");
	    
	    var rows = tbody.rows;
	    var index = 0;
	    var json = '[';
	    var nms = 0;
	    for (var i = 0; i <rows.length; i++) {
	    	
	    	
	    	var fh="";
	    	
	    	if(tbody.rows.item(i).cells[1].childNodes[0].selectedOptions.length==0 && (tbody.rows.item(i).cells[2].childNodes[0].selectedOptions.length==0 || tbody.rows.item(i).cells[2].childNodes[0].selectedOptions[0].value=="") && tbody.rows.item(i).getElementsByTagName("input")[0].value=="" && tbody.rows.item(i).getElementsByTagName("input")[1].value=="" && tbody.rows.item(i).getElementsByTagName("input")[2].value==""){
	    		continue;
	    	}

	    	if(i+1<rows.length){
	    		fh=",";
	    	}
	    	if(tbody.rows.item(i).cells[1].childNodes[0].selectedOptions.length>0){
	    		if(tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value=="" || tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value==null || tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value=="null"){
	    			swal("操作失败","请选择第"+(i+1)+"行原材料信息！", "info");
	    			return;
	    		}
	    		json+='{"str_material_Name":"'+tbody.rows.item(i).cells[1].childNodes[0].selectedOptions[0].value+'",';
	    	}else{
	    		swal("操作失败","请选择第"+(i+1)+"行原材料信息！", "info");
	    		return;
	    	}
	    	
	    	if(tbody.rows.item(i).cells[2].childNodes[0].selectedOptions.length>0){
	    		if(tbody.rows.item(i).cells[2].childNodes[0].selectedOptions[0].value=="" || tbody.rows.item(i).cells[2].childNodes[0].selectedOptions[0].value==null || tbody.rows.item(i).cells[2].childNodes[0].selectedOptions[0].value=="null"){
	    			swal("操作失败","请选择第"+(i+1)+"行原材料信息！", "info");
	    			return;
	    		}
	    		json+='"i_materials_Id":"'+tbody.rows.item(i).cells[2].childNodes[0].selectedOptions[0].value+'",';
	    	}else{
	    		swal("操作失败","请选择第"+(i+1)+"行原材料信息！", "info");
	    		return;
	    	}
	    	
	    	if(tbody.rows.item(i).getElementsByTagName("input")[0].value!=""){
	    		json+='"d_weight":"'+tbody.rows.item(i).getElementsByTagName("input")[0].value+'",';
	    		index++;
	    	}else{
	    		swal("操作失败","请录入原材料信息的第"+(i+1)+"行原材料用量！", "info");
	    		return;
	    	}
	    	if(tbody.rows.item(i).getElementsByTagName("input")[1].value!=""){
	    		json+='"str_Manufacturer":"'+tbody.rows.item(i).getElementsByTagName("input")[1].value+'",';
				index++;
	    	}else{
	    		json+='"str_Manufacturer":"",';
				index++;
	    	}
	    	if(tbody.rows.item(i).getElementsByTagName("input")[2].value!=""){
	    		json+='"str_material_Origin":"'+tbody.rows.item(i).getElementsByTagName("input")[2].value+'"}'+fh;
				index++;
	    	}else{
	    		json+='"str_material_Origin":""}'+fh;
				index++;
	    	}
			
			
		}
	    json+="]";

	    if(json=="[]"){
    		swal("操作失败","原材料信息不能为空", "info");
	    	return;
	    }
	    
	    
		var list = json;
		var dtList = eval('(' + json + ')'); 
		var dataList = [];
		
		
		//之后调整
		for (var i = 0; i < dtList.length; i++) {
			dataList[i]=dtList[i].i_materials_Id;
		}
		
		for (var i = 0; i < dataList.length; i++) {
			for (var j = 0; j < dataList.length-1; j++) {

				if(dataList[i]==dataList[j+i+1]){
					swal("操作失败","原材料规格型号不能重复", "info");
					return;
				}
			}
		}
		
		
		
		url = '../cementTheory/addCement_TheoryProportion.action';
		params = {

	            "i_org_Id":i_org_Id,
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
            if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					window.location.href="../cementTheory/getCement_TheoryProportion.action";
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
        }
    });
}
function closes(){
	window.location.href="../cementTheory/getCement_TheoryProportion.action";
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
//	var obj = eval('(' + str + ')'); 
//	var count1 = 0;
//	var count2 = 0;
//	for(var i = 0; i < obj.length; i++) {
//		if(obj[i].i_materials_Id != null && obj[i].i_materials_Id != ""){
//			count1++;
//		}
//		if(obj[i].d_weight != null || obj[i].d_weight != "") {
//			count2++;
//		}
//	}
////	if(count1 == 0 || count1 < count2) {
////		alert("原材料名称原材料型号和比例不能为空");
////		return false;
////	}
//	if(count2 == 0 || count1 > count2) {
//		alert("比例不能为空");
//		return false;
//	}
//	return true;
//}

function numcn(obj){
	
	var flag = true;
	//密度浮点型校验
	var regExp = /^[+-]?(0|([1-9]\d*))(\.\d+)?$/g;
	//正整数
	var regExp1 = /^[1-9]\d*$/;

	   
	if(obj.value==null||obj.value==""){
		
		flag = false;
	}
	
	
	if(!regExp.test(obj.value)&&flag){
		
		if((obj.value.toString()).indexOf('.')>-1 && !regExp1.test(obj.value.toString().split(".")[0])){
			
			/*alert("材料用量输入有误，请重新输入");*/
			swal("操作失败","材料用量输入有误，请重新输入", "info");
			obj.value =""
				
			return;		
			
		}else if((obj.value.toString()).indexOf('.')==-1){
						
			/*alert("材料用量输入有误，请重新输入");*/
			swal("操作失败","材料用量输入有误，请重新输入", "info");
			obj.value = "";
			return;	
		}
		
	   		
	}
	
	
	//整数位置
	
	if( (obj.value.toString()).indexOf('.')==-1 ){
		//alert((obj.value.toString()).indexOf('.'));
		
		if(obj.value.length > 13 && obj.value.substr(obj.value.length-1,1)!="."){
			
			obj.value = obj.value.substr(0,13);
		}
	}
	
	else{
		
	obj.value = obj.value.replace(/[^\d.]/g,""); 
	obj.value = obj.value.replace(/^\./g,"");
	obj.value = obj.value.replace(/\.{2,}/g,".");
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
	
	}
	
	
}

function show(i_id) {
	
	getStrModel();
	addOrUpdate = 1;
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
    $("#upDateDetailedTab tbody ").empty();
       
    $.ajax({
        type : "POST",
        url : "../cementTheory/getCement_TheoryProportionById.action",
        data : {"i_id":i_id,"i_org_Id":i_org_Id},
        dataType : "json",
        success : function(data) {
        	if(data.str_prop_Code == "error"){
        		swal("操作失败",data.str_design_Strength, "error");
        		return;
        	}
        	$("#loginModal2").modal("show");
        	 $("#i_id").val(data.i_id);
        	 $("#upDateStr_prop_Code").val(data.str_prop_Code);
        	 $("#upDateStr_material_Name").html("<option selected='selected'>"+data.material_Name+"</option>");
        	 $("#upDateStr_material_Model").html("<option value='"+data.i_product_Id+"' selected='selected'>"+data.material_Model+"</option>");
        	 $("#upDateD_water_Cement_Ratio").val(data.d_water_Cement_Ratio);
        	 $("#upDateD_sand_Ratio").val(data.d_sand_Ratio);
        	 $("#upDateStr_design_Strength").val(data.str_design_Strength);
        	 $("#upDateI_slump_Low").val(data.i_slump_Low);
        	 $("#upDateI_slump_High").val(data.i_slump_High);
        	 $("#upDateStr_operator").val(data.str_operator);
        	 $("#upDateStr_remarks").val(data.str_remarks);
        	 var html = ''
        	 var cement_TheoPropDetaileds = data.cement_TheoPropDetaileds;
        	 for(var i = 0; i < cement_TheoPropDetaileds.length; i++) {
        		 html += '<tr ><td style="display: none">' + i+1 + '</td>' +
        	        '<td><select onfocus="getMaterialName($(this));"  onchange="getMaterialModel($(this));"><option selected="selected">'+cement_TheoPropDetaileds[i].str_material_Name+'</option></select></td> ' +
        	        '<td><select name="i_materials_Id" id="str_material_Model"><option selected="selected" value="'+cement_TheoPropDetaileds[i].i_materials_Id+'">'+cement_TheoPropDetaileds[i].str_material_Model+'</option></select></td>' +
        	        '<td><input class="texta" type="text" name="d_weight" maxlength="18" onkeyup="numcn(this)" value="'+cement_TheoPropDetaileds[i].d_weight+'"></input></td>' +
        	        '<td><input name="str_Manufacturer" value="'+cement_TheoPropDetaileds[i].str_Manufacturer+'"></td>' +
        	        '<td><input name="str_material_Origin" value="'+cement_TheoPropDetaileds[i].str_material_Origin+'"></td>' +
        	        '<td><img src="../resources/images/del.png" onclick="delDetailed1($(this));"></td>' +
        	        '</tr>';
        	 }
        	 if(cement_TheoPropDetaileds.length-5<0){
        		 var nb = Math.abs(cement_TheoPropDetaileds.length-5);
        		 for (var i = 0; i < nb; i++) {
        			 html += '<tr ><td style="display: none">' + i+1 + '</td>' +
         	        '<td><select onfocus="getMaterialName($(this));"  onchange="getMaterialModel($(this));"><option selected="selected"></option></select></td> ' +
         	        '<td><select name="i_materials_Id" id="str_material_Model"><option selected="selected" value=""></option></select></td>' +
         	        '<td><input class="texta" type="text" name="d_weight" maxlength="18" onkeyup="numcn(this)" value=""></input></td>' +
         	        '<td><input name="str_Manufacturer" value=""></td>' +
         	        '<td><input name="str_material_Origin" value=""></td>' +
         	        '<td><img src="../resources/images/del.png" onclick="delDetailed1($(this));"></td>' +
         	        '</tr>';
				}
        	 }
        	 $("#tbIds2").html(html);
        	 getStrModels();
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
        url :"../cementTheory/deletCement_TheoryProportion.action",
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
					window.location.href= "../cementTheory/getCement_TheoryProportion.action";
				}); },200);
			}else{
				setTimeout(function(){swal(data.message,"","error"); },200);
			}
        }
    });
	});
}

var ggModel = "";
function getMaterialModel(obj) {

	var str_material_Name = obj.parent().parent().find("td:eq(1)").children().find("option:selected").text();
	obj.parent().parent().find("td:eq(2)").children().find("option:selected").html('');
    $.ajax({
        type : "POST",
        url :"../Common/getMaterialModels.action",
        data : {"str_material_Type":1,
        	"i_org_Id":i_org_Id,
            "str_material_Mold":0,
            "str_material_Name":str_material_Name},
        dataType : "json",
        success : function(data) {
        	var html = "<option value=''></option>"; 
        		
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>"; 
			}
        	obj.parent().parent().find("td:eq(2)").children().empty();
        	obj.parent().parent().find("td:eq(2)").children().append(html);
        }
    });
}


//删除动态添加行
function RemoveMaterialUpdate(obj) {

   /* var table = document.all['detailedTab'];

    table.deleteRow(rowIndex);
    count = count - 1;*/
	obj.parent().parent().remove();
	count = count - 1;
}

function delDetailed(obj) {
	//obj.parentElement.parentElement.rowIndex update obj对象
    //RemoveMaterialUpdate(obj);
    RemoveMaterialAdd(obj);
}

function delDetailed1(obj) {
	//obj.parentElement.parentElement.rowIndex update obj对象
    RemoveMaterialUpdate(obj);
    //RemoveMaterialAdd(obj.parentElement.parentElement.rowIndex);
}


//删除动态添加行
function RemoveMaterialAdd(obj) {

	obj.parent().parent().remove();
//   var table = document.all['detailedTab'];

//    table.deleteRow(obj);
    count = count - 1;
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
//    var tempTR = '<tr name="' + num + 'AfteRzsdtype"><td>' + (parseInt(count++) + 5) + '</td>' +
//        '<td><select onfocus="getMaterialName($(this));" onchange="getMaterialModel($(this));" class="m"></select></td> ' +
//        '<td><select name="i_materials_Id" ></select></td>' +
//        '<td><input class="texta" type="text" name="d_weight" maxlength="18" ></input></td>' +
//        '<td><input name="str_Manufacturer" ></td>' +
//        '<td><input name="str_material_Origin" ></td>' +
//        '<td><img src="../resources/images/del.png" onclick="delDetailed(this);"></td>' +
//        '</tr>';
    
    var tempTR = '<tr ><td style="display: none">' + num + '</td>' +
    '<td><select onfocus="getMaterialName($(this));"  onchange="getMaterialModel($(this));"><option selected="selected"></option></select></td> ' +
    '<td><select name="i_materials_Id" id="str_material_Model"><option selected="selected" value=""></option></select></td>' +
    '<td><input  name="d_weight" type="text" placeholder=""  size="10" value="" onkeyup="numcn(this)"></input></td>' +
    '<td><input name="str_manufacturer" type="text" placeholder="" size="15" value=""  maxlength="20"></td>' +
    '<td><input  name="str_material_Origin" type="text" placeholder="" size="10" value=""  maxlength="20"></td>' +
    '<td><img src="../resources/images/del.png" onclick="delDetailed1($(this));"></td>' +
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
		url : "../cementTheory/getRawMaterial.action",
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
		url : "../Common/getMaterialModel.action",
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
	
	/* 查找nodes 名称  tongn 2018.6.22*/
	var objList = d.aNodes;
	for(var i=0,l=objList.length;i<l;i++){  
		
		if(objList[i].id == id){
			
			$("#locationText").text(objList[i].cshow);
		}
		  
	}
}

function getPbgl_1Select(){
	
	var table = $('#llConstruction').dataTable();
	table.fnDestroy();
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

        "ajax" : {
            type: "post",
            url: "../cementTheory/getCement_TheoryProportions.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_org_Id = i_org_Id;
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
            "data" : "material_Model"
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
        	$(row).children('td').eq(2).html(data.material_Name+"/"+data.material_Model)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
            $(row).children("td").eq(1).addClass("mbpb_xg");
			//$(row).children("td").eq(1).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_prop_Code+"</a>");
        	$(row).children("td").eq(1).html("<a href='javascript:void(0);'>"+data.str_prop_Code+"</a>");
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

function show2(obj,id,ids) { 
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display","block"); 
	$(objDiv).css("left", event.clientX); 
	$(objDiv).css("top", event.clientY + 10); 
	
	/*var param = {};
	param.i_id = ids;
	param.orgid = i_org_Id*/
	$.ajax({
	    type: "post",
//	    contentType: 'application/json;charset=UTF-8',
	    data : {"i_id":ids,"i_org_Id":i_org_Id},
		dataType : "json",
	    url :"../cementTheory/getCement_TheoryProportionByIdShow.action",
//	    data: JSON.stringify(param),
//	    dataType: "json",
	    success: function (data) {
	    	var html = "";
	    	if(data!=null){
	    			 for (var i = 0; i < data.cement_TheoPropDetaileds.length; i++) {
	    				 html+="<tr><td>"+(i+1)+"</td><td>"
	    				 +data.cement_TheoPropDetaileds[i].d_weight+"</td><td>"
	    				 +data.cement_TheoPropDetaileds[i].str_Manufacturer+"</td><td>"
	    				 +data.cement_TheoPropDetaileds[i].str_material_Origin+"</td></tr>"
					}
	    			 $("#nr_l_new").html(html);
	    	}
	    }
	});
}
function hide(obj,id) { 
	var objDiv = $("#"+id+""); 
	$(objDiv).css("display", "none"); 
} 