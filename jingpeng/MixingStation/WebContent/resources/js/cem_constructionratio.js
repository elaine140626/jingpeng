var addOrUpate = 0;
var i_org_Id = "";
$(function () {
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
    getList();
    
    //产品名称
    $('#addIds').focus(function(){
    	$('#str_material_Mold').empty();
        $.ajax({
            type: "post",
            async: false,
            url: "../Common/getMaterialNames.action",
            dataType: "json",

            data:{"str_material_Type":1,"str_material_Mold":1,"i_org_Id":i_org_Id},
            success: function (data) {
            	$("#str_material_Names").empty();
	        	 var html = "<option value=''></option>";
	        	 for(var i = 0; i < data.length; i++) {
	        		 html += "<option value='"+data[i].str_material_Name+"'>"+data[i].str_material_Name+"</option>"
	 			 }
	        	 $("#str_material_Names").html(html);
            }
        });
        
        
    });


   

})

/*查询配比
$('#consProp').focus(function(){
       $.ajax({
          type: "post",
          url: "../CementConstructionProp/getCementConstructionPropbypid",
      data:{"i_product_Id": $("#str_material_Mold").val(),"i_org_Id":i_org_Id},
     dataType: "json",
        success: function (data) {
             $("#consProp").empty();
               $.each(data, function (i, v) {
                    $('<option></option>').attr("value", v.i_id).html(v.str_prop_Code).appendTo($("#consProp"));
                })
           }
       });
  });*/

function getHqMolds(){
	
	 var myselect=document.getElementById("str_material_Names");

	 var index=myselect.selectedIndex ; // selectedIndex代表的是你所选中项的index

//	 myselect.options[index].value;

	 var str_material_Names = myselect.options[index].text;
	 
    $.ajax({
        type: "post",
        async: false,
        url: "../Common/getMaterialModels.action",
        data: {"str_material_Type":1,"str_material_Mold":1,"str_material_Name":str_material_Names,"i_org_Id":i_org_Id},
        dataType: "json",
        success: function (data) {
            //施工配合比产品名称加载规格调用
        	$("#str_material_Mold").empty();
        	 var html = "<option value=''></option>";
        	 for(var i = 0; i < data.length; i++) {
        		 html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>"
 			 }
        	 $("#str_material_Mold").html(html);
        }
    });
}
function getPhbNumber(){

	if($("#str_material_Mold").val()==null || $("#str_material_Mold").val()==""){
		 $("#consProp").empty();
		 return;
	}
    $.ajax({
        type: "post",
        url: "../cementConstructionProp/getTheory.action",
        data:{"i_product_Id":$("#str_material_Mold").val() ,"i_org_Id":i_org_Id},
        dataType: "json",
        success: function (data) {
        	 var html = "<option value=''></option>";
            for (var i = 0; i < data.length; i++) {
            	 html += "<option value='"+data[i].i_id+"'>"+data[i].str_prop_Code+"</option>"
			}
            $("#consProp").empty();
            $("#consProp").html(html);
        }
    });

}

function getAlls(){
		
	var consProp = $("#consProp").val();
	var params = {};
	params.i_id = consProp;
	$.ajax({
		  type: "post",
		  url: "../cementConstructionProp/getCementConstructionPropbypid.action",
		  data:params,
		  dataType: "json",
		  success: function (data) {
			  var lis = data.data[0];
			  var li =data.data[1];
			 if(li.length>0){
				 document.getElementById("textfield2").value=li[0].Water_Cement_Ratio;
				 document.getElementById("textfield4").value=li[0].Sand_Ratio;
				 document.getElementById("textfield3").value=li[0].Design_Strength;
				 document.getElementById("textfield5").value=li[0].Slump_Low;
				 document.getElementById("textfield6").value=li[0].Slump_High;
			 }
			 $("#forList").empty();
		 	 var html = '<tr class="mbphb_luru_dise">'
                +' <td width="5%" style="display: none">序号</td>'
               /* +' <td width="15%">材料名称</td>'
                +' <td width="14%">规格型号</td>'*/
                +'<td colspan="2" align="center"><strong>原材料</strong></td>'
                 +'<td width="17%">理论配比用量</td>'
                 +'<td width="17%">施工配比用量</td>'
                 +'<td width="21%">厂家</td>'
                +' <td width="11%">产地</td>'
               +'</tr>';
		     for (var i = 0; i < lis.length; i++) {
//		     	 html += "<option value='"+data[i].i_id+"'>"+data[i].str_prop_Code+"</option>"
		    	 html+='<tr>'
               	+'<td style="display: none">'+(i+1)+'</td>'
                 +'<td><input name="textfield" type="text"  disabled="disabled"  placeholder="" value='+lis[i].Material_Name+'></td>'
                 +'<td><input name="textfield" type="text"  disabled="disabled"  placeholder="" value='+lis[i].Material_Model+'></td>'
                // +"<td><input type='text' name='d_construction_Weight'  onkeyup='num(this)' value='"+cement_TheoPropDetaileds[i].Construction_Weight+"'></input></td>"
                 +'<td><input name="textfielda" type="text" id="textfielda" disabled="disabled" onkeyup="num(this)" placeholder="" value='+lis[i].Weight+'></td>'
                 +'<td><input name="textfield" type="text"  placeholder=""></td>'
                 +'<td><input name="textfield" type="text"  disabled="disabled" placeholder="" value='+lis[i].Manufacturer+'></td>'
                 +'<td><input name="textfield" type="text"  disabled="disabled" placeholder="" value='+lis[i].Material_Origin+'></td>'
                 +'<td><input name="textfield" type="text" style="display: none"  placeholder="" value='+lis[i].i_id+'></td>'
               +'</tr>'
			}
		    
		     $("#forList").append(html);
		}
	});
}
//添加
function addSubmission(){
	
	
	//施工配比编号
	var sgpb =  $("#textfield").val();
	//产品名称
	var myselect=document.getElementById("str_material_Mold");

	 var index=myselect.selectedIndex ; 

	 //空判断  tongnan  add
	 var cpmcId = $("#str_material_Mold").val();

	 var cpmc = "";
	 
	 if(myselect.options[index]!= undefined){
		 
	  cpmc = myselect.options[index].text;
	 }
	 
	 
	 //理论配比  
	 var myselects=document.getElementById("consProp");

	 var indexs=myselects.selectedIndex ; 

	 // 全空判断  防止报错
	 var llpbbhId = "";
	 var llpbbh  = "";
	 if(indexs!=-1){		 
		 llpbbhId = myselects.options[indexs].value;
		 llpbbh = myselects.options[indexs].text;
	 }

/*	 if(myselect.options[indexs]!= undefined){
		 
	  }*/


	//规格型号
	var ggxh =  $("#str_material_Mold").val();
	//水灰比
	var shb =  $("#textfield2").val();
	//砂率
	var sl =  $("#textfield4").val();
	//设计强度
	var sjqd =  $("#textfield3").val();
	//塌落度
	var tlda =  $("#textfield5").val();
	var tldb =  $("#textfield6").val();
	//备注
	var bz =  $("#textarea").val();
	//创建人
	var cjr =  $("#textfield7").val();
	
	obj = $('#asph_TargetPropList').serializeArray();
	var list = obj;
	
	var tbody = document.getElementById("forList");
    var rows = tbody.rows;
    var json = '[';
    var nms = 0;
    
    //tongn update
    if(sgpb==""){
    	
     /* alert("施工配比编号不能为空");*/
      swal("操作失败","施工配比编号不能为空!", "info");
      return;
      
    }
    
    //产品名称  tongn update
    if($("#str_material_Names").val() ==null||$("#str_material_Names").val() ==""){
    	
    	  /*alert("请选择产品名称");*/
    	  swal("操作失败","请选择产品名称!", "info");
          return;
    }
    
    
    //规格型号 tongn update
    if(ggxh==null||ggxh==""){
    	
    	
  	  /*alert("请选择规格型号");*/
  	  swal("操作失败","请选择规格型号", "info");
      return;
        
    }
    
    
    //理论配比 tongn update
    if(llpbbhId == ""){
  	  /*alert("请选择理论配比编号");*/
  	  swal("操作失败","请选择理论配比编号", "info");
      return;
      
    }
    
    for (var i = 1; i <rows.length; i++) {
    	 var cells = tbody.rows.item(i).cells.length;
    	 var index = 0;
    	 for (var j = 0; j <= cells; j++) {
				var fh = "";
				switch (j) {
				case 1:
					
					//json+=fh+'{"i_materials_Id":"'+tbody.rows.item(i).cells[1].children[0].value+'",';
					index++;
					break;
				case 2:
					if(nms>2){
						fh = ",";
					}
					json+=fh+'{"i_materials_Id":"'+tbody.rows.item(i).cells[7].children[0].value+'",';
					index++;
					break;
				case 3:
					json+='"d_theory_Weight":"'+tbody.rows.item(i).cells[3].children[0].value+'",';
					index++;
					break;
				case 4:
					if(tbody.rows.item(i).cells[4].children[0].value==""){
						/*alert("施工配比用量不能为空！");*/
						swal("操作失败","施工配比用量不能为空", "info");
						return;
					}
					//密度浮点型校验
					var regExp = /^[+-]?(0|([1-9]\d*))(\.\d+)?$/g;
					if(!regExp.test(tbody.rows.item(i).cells[4].children[0].value)){
						
						/*alert("施工配比用量格式输入错误！");*/
						swal("操作失败","施工配比用量格式输入错误", "info");
						return;
					}
					
					 var ysz = yz(tbody.rows.item(i).cells[4].children[0].value);
					if(ysz == false){
						/*alert("最多输入13位整数，2位小数");*/
						swal("操作失败","最多输入13位整数，2位小数", "info");
						return;
					}
					
					json+='"d_construction_Weight":"'+tbody.rows.item(i).cells[4].children[0].value+'",';
					index++;
					break;
				case 5:
					json+='"str_manufacturer":"'+tbody.rows.item(i).cells[5].children[0].value+'",';
					index++;
					break;
				case 6:
					json+='"str_material_Origin":"'+tbody.rows.item(i).cells[6].children[0].value+'"}';
					index++;
					break;
				default:
					break;
				}
				nms++;
			}
	}
    json+="]";
	
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/cementConstructionProp/addSgpbXx.action",
		data : {
			//组织机构id
			"i_org_Id":i_org_Id,
			//施工配比编号
			 "sgpb" : sgpb,
			//产品名称id
			 "cpmcId" : cpmcId,
			//产品名称
			 "cpmc" : cpmc,
			//规格型号
			 "ggxh" :  ggxh,
			//水灰比
			 "shb" :  shb,
			//砂率
			 "sl" :  sl,
			//设计强度
			 "sjqd" :  sjqd,
			//塌落度
			 "tlda" :  tlda,
			 "tldb" : tldb,
			 //创建人
			 "cjr":cjr,
			 //备注
			 "bz":bz,
			 //理论配比id
			 "llpbbhId":llpbbhId,
			 //理论配比
			 "llpbbh":llpbbh,
			 //集合
			 "list": json
		},
		dataType : "json",
		success : function(data) {
		/*	if(data.message.indexOf('成功')>-1){
				alert(data.message);
				window.location.href=baseUrl + "/CementConstructionProp/cementConstructionProp.html";
			}else{
				alert(data.message);
				return;
			}
			*/

			if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					window.location.href=baseUrl + "/cementConstructionProp/cementConstructionProp.action";
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
		}
	});
}
function closes(){
	window.location.href=baseUrl + "/cementConstructionProp/cementConstructionProp.action";
}
function yz(strr){
	
	var index = strr.indexOf('.');
	var zs = strr.substring(0,index);
	var xs = strr.substring(index+1,strr.length);
	
	if(strr.indexOf(".") >-1){
		if(zs.length>13){
			return false;
		}else{
			if(xs.length>2){
				return false;
			}
		}
	}else{
		if(zs.length>13){
			return false;
		}
	}
}
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
            "ajax" : "../cementConstructionProp/getCementConstructionProp.action?i_org_Id="+i_org_Id,
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
                $(row).children("td").eq(1).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_id+");");
                $(row).children("td").eq(2).attr('onMouseOver',"show3(this,\"mydiv2\","+data.i_theoProp_Id+");");
                $(row).children("td").eq(1).attr('onMouseOut','hide(this,\"mydiv1\")');
                $(row).children("td").eq(2).attr('onMouseOut','hide(this,\"mydiv2\")');
                $(row).children("td").eq(1).html("<a href='javascript:void(0);'>"+data.str_prop_Code+"</a>");
                $(row).children("td").eq(2).html("<a href='javascript:void(0);'>"+data.str_llProp_Code+"</a>");
    			//$(row).children("td").eq(1).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_prop_Code+"</a>");
               // $(row).children("td").eq(2).html("<a href='javascript:void(0);' id='t1' onMouseOver='show3(this,\"mydiv1\","+data.i_product_Id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_llProp_Code+"</a>");
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
		
		var param = {};
		param.i_id = ids;
		$.ajax({
		    type: "post",
//		    contentType: 'application/json;charset=UTF-8',
		    data : param,
			dataType : "json",
		    url :  "../cementConstructionProp/getC_ConstructionDeatlByid.action",
//		    data: JSON.stringify(param),
//		    dataType: "json",
		    success: function (data) {
		    	if(data!=null){
		    		/* $("#nr_l").html(data.data.cc.str_prop_Code);
		    		 $("#xh_l").html(data.data.cc.str_material_Name+"-"+data.data.cc.str_material_Model);*/
		    		 var html = "";
		    		 if(data.data.cd.length>0){
		    			 for (var i = 0; i < data.data.cd.length; i++) {
		    				 html+="<tr> <td>"+(i+1)+"</td>" +
		    				 		"<td align='center'>"+data.data.cd[i].Theory_Weight+"</td>" +
		    				 		"<td align='center'>"+data.data.cd[i].Construction_Weight+"</td>" +
		    				 		"<td align='center'>"+data.data.cd[i].Manufacturer+"</td>" +
		    				 		"<td align='center'>"+data.data.cd[i].Material_Origin+"</td> </tr>"
		    				 
		    			 }
		    			 $("#nr_l_new").html(html);
		    		 }
		    	}
		    }
		});
	} 
 function show3(obj,id,ids) { 
		var objDiv = $("#"+id+""); 
		$(objDiv).css("display","block"); 
		$(objDiv).css("left", event.clientX); 
		$(objDiv).css("top", event.clientY + 10); 
		
		/*var param = {};
		param.i_id = ids;
		param.orgid = i_org_Id*/
		$.ajax({
		    type: "post",
//		    contentType: 'application/json;charset=UTF-8',
		    data : {"i_id":ids,"i_org_Id":i_org_Id},
			dataType : "json",
		    url :"../cementTheory/getCement_TheoryProportionByIdShow.action",
//		    data: JSON.stringify(param),
//		    dataType: "json",
		    success: function (data) {

		    	var html = "";
		    	if(data!=null){
		    			 for (var i = 0; i < data.cement_TheoPropDetaileds.length; i++) {
		    				 
		    				 html+="<tr><td>"+(i+1)+"</td><td>"
		    				 +data.cement_TheoPropDetaileds[i].d_weight+"</td><td>"
		    				 +data.cement_TheoPropDetaileds[i].str_Manufacturer+"</td><td>"
		    				 +data.cement_TheoPropDetaileds[i].str_material_Origin+"</td></tr>"
						}
		    			 $("#nr_l_new2").html(html);
		    	}
		    }
		});
	}
 
	function hide(obj,id) { 
		var objDiv = $("#"+id+""); 
		$(objDiv).css("display", "none"); 
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
	        url: "../Common/getMaterialName.action",
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
	        url: "../cementConstructionProp/getTheoryProportionCode.action",
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
        url: "../cementTheory/getCement_TheoryProportionById.action",
        data: {i_id:$("#i_theoProp_Id").val(),"i_org_Id":i_org_Id},
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
                  	   +"<td style='display: none'>"+i
                  	   +"<td><input type='text' readonly='readonly' value='"+cement_TheoPropDetaileds[i].str_material_Name+"'>"
                  	   +"<input type='hidden' name='i_id' value='"+cement_TheoPropDetaileds[i].i_id+"'>"
                  	   +"<input type='hidden' name='i_materials_Id' value='"+cement_TheoPropDetaileds[i].i_materials_Id+"'></td>"
                  	   +"<td><input type='text' name='str_material_Model' readonly='readonly' value='"+cement_TheoPropDetaileds[i].str_material_Model+"'></td>"
                  	   +"<td><input name='d_theory_Weight' type='text' readonly='readonly' value='"+cement_TheoPropDetaileds[i].d_weight+"'></td>"
                  	   +"<td><input name='d_construction_Weight' type='text' placeholder='输入' onkeyup='num(this)'></td>"
                  	 //+"<td><input type='text' name='d_construction_Weight'  onkeyup='num(this)' value='"+cement_TheoPropDetaileds[i].Construction_Weight+"'></input></td>"
                  	   +"<td><input type='text' name='str_manufacturer' value='"+cement_TheoPropDetaileds[i].str_Manufacturer+"' readonly='readonly'>"
                  	   +"<td><input type='text' name='str_material_Origin' value='"+cement_TheoPropDetaileds[i].str_material_Origin+"' readonly='readonly'></td>"
                  	   +"</tr>";
        	}
        	$("#detailed").html(html);
        }
    });
}

function addOrUpdate(cs) {
	var url = "";
	var params = {};
	var obj = $("#tabForm").serializeArray();
	var list = toJsonArr(obj);
	var myselect=document.getElementById("ggxh");

	 var index=myselect.selectedIndex ; 

	 var cpmcId = myselect.options[index].value;
	 
	 
	//理论配比
	 var myselects=document.getElementById("llpbbh");

	 var indexs=myselects.selectedIndex ; 

	 var llpbbhId = myselects.options[indexs].value;
	 
	 var tbody = document.getElementById("trlist");
	    var rows = tbody.rows;
	    var json = '[';
	    var nms = 0;
	    for (var i = 1; i <rows.length; i++) {
	    	 var cells = tbody.rows.item(i).cells.length;
	    	 var index = 0;
	    	 for (var j = 0; j <= cells; j++) {
					var fh = "";
					switch (j) {
					case 1:
						if(nms>1){
							fh = ",";
						}
						json+=fh+'{"i_id":"'+tbody.rows.item(i).cells[7].children[0].value+'",';
						index++;
						break;
					case 2:
						json+='"i_materials_Id":"'+tbody.rows.item(i).cells[1].children[0].value+'",';
						index++;
						break;
					case 3:
						json+='"d_theory_Weight":"'+tbody.rows.item(i).cells[3].children[0].value+'",';
						index++;
						break;
					case 4:
						if(tbody.rows.item(i).cells[4].children[0].value==""){
							/*alert("施工配比用量不能为空！");*/
							swal("操作失败","施工配比用量不能为空!", "info");
							return;
						}
						json+='"d_construction_Weight":"'+tbody.rows.item(i).cells[4].children[0].value+'",';
						index++;
						break;
					case 5:
						json+='"str_manufacturer":"'+tbody.rows.item(i).cells[5].children[0].value+'",';
						index++;
						break;
					case 6:
						json+='"str_material_Origin":"'+tbody.rows.item(i).cells[6].children[0].value+'"}';
						index++;
						break;
					default:
						break;
					}
					nms++;
				}
		}
	    json+="]";
	if(cs == 1) {
		url = "../cementConstructionProp/addCementConstructionPro.action";
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
		url = "../cementConstructionProp/updateCementConstructionPro.action";
		params = {
				  i_id:$("#i_id").val(),
				  d_water_Cement_Ratio:$("#sjshb").val(),
				  d_sand_Ratio:$("#sl").val(),
				  str_design_Strength:$("#sjqd").val(),
				  i_slump_Low:$("#tlda").val(),
				  i_slump_High:$("#tldb").val(),
				  str_Prop_Code:$("#sgpbbh").val(),
				  i_product_Id:cpmcId,
				  i_theoProp_Id:llpbbhId,
				  str_remarks:$("#bz").val(),
				  str_operator:$("#zdr").val(),
				  list:json
				 };
	}
	$.ajax({
        type: "post",
        url: url,
        data:params,
        dataType: "json",
        success: function (data) {
           /*alert(data.message);
           window.location.href="../cementConstructionProp/cementConstructionProp.html";*/
           
           if(data.code=="success"){
				swal({
					title: data.message,
					text: "",
					type: "success",
					confirmButtonText: '确定',
					cancelButtonFont: '微软雅黑',
				},
				function(){
					 window.location.href="../cementConstructionProp/cementConstructionProp.action";
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
			
			swal("操作失败","施工配比用量输入有误，请重新输入!", "error");
			obj.value =""
				
			return;		
			
		}else if((obj.value.toString()).indexOf('.')==-1){
						
			swal("操作失败","施工配比用量输入有误，请重新输入!", "error");
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
	
	addOrUpate = 1;
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
    var params = {};
    params.i_id=i_id;
	$.ajax({
        type: "post",
        url: "../cementConstructionProp/getC_ConstructionDeatlByid.action",
        data:params,
        dataType: "json",
        success: function (data) {
        	if(data.data.ca.code =="error"){
        		swal("操作失败",data.data.ca.message, "error");
        		return;
        	}
        	$("#loginModal2").modal("show");
        	$("#i_id").val(data.data.cc.i_id);
        	$("#sgpbbh").val(data.data.cc.str_prop_Code);
        	$("#cpmc").html("<option selected='selected'>"+data.data.cc.str_material_Name+"</option>");
       	    $("#ggxh").html("<option value='"+data.data.cc.i_product_Id+"' selected='selected'>"+data.data.cc.str_material_Model+"</option>");
	       	$("#llpbbh").html("<option value='"+data.data.cc.i_theoProp_Id+"' selected='selected'>"+data.data.cc.str_llProp_Code+"</option>");
	       	$("#sjshb").val(data.data.cc.d_water_Cement_Ratio);
	       	$("#sl").val(data.data.cc.d_sand_Ratio);
	       	$("#sjqd").val(data.data.cc.str_design_Strength);
	       	$("#tlda").val(data.data.cc.i_slump_Low);
	       	$("#tldb").val(data.data.cc.i_slump_High);
	       	$("#zdr").val(data.data.cc.str_operator);
	       	$("#bz").val(data.data.cc.str_remarks);
	       	
	       	
       	    
       	    // $("#i_theoProp_Id").html("<option value='"+data.i_theoProp_Id+"' selected='selected'>"+data.i_theoProp_Id+"</option>");
//        	$("#d_water_Cement_Ratio").val(data.d_water_Cement_Ratio);
//        	$("#str_design_Strength").val(data.str_design_Strength);
//        	$("#d_sand_Ratio").val(data.d_sand_Ratio);
//        	$("#i_slump_Low").val(data.i_slump_Low);
//        	$("#i_slump_High").val(data.i_slump_High);
//        	$("#str_operator").val(data.str_operator);
//        	$("#str_remarks").val(data.str_remarks);
        	var cement_TheoPropDetaileds = data.data.cd;
        	var html =' <tr class="mbphb_luru_dise">'
               +' <td width="5%" style="display: none">序号</td>'
         /*      +' <td width="15%">材料名称</td>'
               + '<td width="14%">规格型号</td>'*/
               + '<td colspan="2" align="center"><strong>原材料</strong></td>'
               + '<td width="17%">理论配比用量</td>'
               +' <td width="17%">施工配比用量</td>'
               + '<td width="21%">厂家</td>'
               + '<td width="11%">产地</td>'
              +'</tr>';
        	for(var i = 0; i < cement_TheoPropDetaileds.length; i++) {
        		$("#trlist").empty();
        		html += "<tr>"
                  	   +"<td style='display: none'>"+i+1+"</td>"
                  	   +"<td width='15%'><select ><option selected='selected' value='"+cement_TheoPropDetaileds[i].Materials_ID+"'>"+cement_TheoPropDetaileds[i].Material_Name+"</option></select>"
                  	   +"<td><input type='text' name='str_material_Model' readonly='readonly' value='"+cement_TheoPropDetaileds[i].Material_Model+"'></td>"
                  	   +"<td><input name='d_theory_Weight' type='text' readonly='readonly' value='"+cement_TheoPropDetaileds[i].Theory_Weight+"'></td>"
                  	   //+"<td><input type='text' name='d_construction_Weight'  onkeyup='num(this)' value='"+cement_TheoPropDetaileds[i].Construction_Weight+"'></input></td>"
                  	   +"<td><input name='d_construction_Weight' type='text' placeholder=' '   onkeyup='num(this)' value='"+cement_TheoPropDetaileds[i].Construction_Weight+"'></td>"
                  	   +"<td><input type='text' name='str_manufacturer' value='"+cement_TheoPropDetaileds[i].Manufacturer+"' readonly='readonly'>"
                  	   +"<td><input type='text' name='str_material_Origin' value='"+cement_TheoPropDetaileds[i].Material_Origin+"' readonly='readonly'></td>"
                  	   +"<td><input type='hidden' name='i_id' value='"+cement_TheoPropDetaileds[i].Id+"'><td>"
                  	   +"</tr>";
        	}
        	$("#trlist").html(html);
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
        type: "post",
        url:"../cementConstructionProp/delCementConstructionPro.action",
        data : {"i_id":i_id,
			"i_org_Id":i_org_Id},
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
					window.location.href="../cementConstructionProp/cementConstructionProp.action";
				}); },200);
			}else{
				setTimeout(function(){swal(data.message,"","error"); },200);
			}
        }
	});
    });
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

function getPbgl_2Select(){
	
	var table = $('#CConstruction').dataTable();
	table.fnDestroy();
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
        "ajax" : {
            type: "post",
            url: "../cementConstructionProp/getCementConstructionProp.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_org_Id = i_org_Id;
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
        	$(row).children('td').eq(1).attr('style', 'text-align: center;')
            $(row).children("td").eq(1).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_id+");");
            $(row).children("td").eq(2).attr('onMouseOver',"show3(this,\"mydiv2\","+data.i_theoProp_Id+");");
            $(row).children("td").eq(1).attr('onMouseOut','hide(this,\"mydiv1\")');
            $(row).children("td").eq(2).attr('onMouseOut','hide(this,\"mydiv2\")');
            $(row).children("td").eq(1).html("<a href='javascript:void(0);'>"+data.str_prop_Code+"</a>");
            $(row).children("td").eq(2).html("<a href='javascript:void(0);'>"+data.str_llProp_Code+"</a>");
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}