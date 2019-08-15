var baseUrl = "";
//组织id
var orgId = ''
//接收通过施工配比id获取所有的材料id
var allIds = [];
$(function () {
	$("#locationText").html(cementOrgName);
	baseUrl = getContextPath();
	orgId = cementOrgId;
	search();
})
//查询
function search(){
	var param = {};
	//组织id
	param.orgId= orgId;
	//料仓对应关系编号
	var siloCorrespondingRelationshipNumber = $("#SiloCorrespondingRelationshipNumber").val();
	param.siloCorrespondingRelationshipNumber = siloCorrespondingRelationshipNumber;
	//产品名称
	var productName = $("#productName").val();
	param.productName = productName;
	//产品型号
	var productModel = $("#productModel").val();
	param.productModel = productModel;
	getList(param);
}
//查询列表
function getList(param){
	var table = $('#bunk').dataTable();
	table.fnDestroy();
	 $('#bunk').DataTable({
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
		           url: baseUrl + "/cement_Correspondence/getBunker_Correspondences.action",
		           dataSrc: "data",
		           data: function (d) {
		               return param;//自定义需要传递的参数。
		           }
		       },
	        "deferRender" : true,
	        "columns" : [{
	            "data" : "bunkerCode"
	        }, {
	            "data" : "bunkerCode"
	        }, {
	            "data" : "equipmentName"
	        }, {
	            "data" : "remarks"
	        }, {
	            "data" : "operator"
	        }, {
	            "data" : "createDate"
	        }],
	        "fnRowCallback" : function(nRow, aData, iDisplayIndex){  
	        	var html = '';
	        	html += iDisplayIndex +1;        	
	            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一
	            return nRow;
	        },
	        "createdRow" : function(row, data, dataIndex) {
	            $(row).children('td').eq(1).attr('style', 'text-align: center;')
	        },
	        "columnDefs" : [ {
	            "targets" : [ 0 ],
	            "visible" : true,
	            "searchable" : false
	        } ]
	    });
	    $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}
//查询拌合机设备名称
function getEquipmentName() {
	$('#loginModal').modal('show');
    $.ajax({
    	type : "post",
		url :  baseUrl +"/equipment/getAllEquipmentInfo.action",
		data : {
			"orgId" : orgId,
			"equipmentType" : 1
		},
        dataType: "json",
        success: function (data) {
			var html ="<option value=''></option>"
			for(var i = 0; i < data.data.length; i++) {
				html += "<option value='"+data.data[i].id+"'>"+data.data[i].equipmentName+"</option>"
			}
			nameStr = html;
        }
    });
}
//拌合机设备名称赋值
function getShowName(){
	 $("#equipmentName").empty();
	 $("#equipmentName").html(nameStr);
}

//获取原材料名称List
function getStrNmae(obj){
	if($("#equipmentName").val()==""){
		return;
	}
	$.ajax({
        type: "post",
        async: false,
        url: baseUrl + "/material/getMaterialName.action",
        dataType: "json",
        data:{"materialMold":0,
        	"materialType":1,
        	"orgId":orgId},
        success: function (data) {
        	strNames = "";
        	 var html  = "<option value=''></option>"
      			for(var i = 0; i < data.length; i++) {
      				html += "<option value="+data[i].mateName+">"+data[i].mateName+"</option>";
      			}
        	 strNames = html;
        }
    });
}

//原材料名称赋值
function getNameShow(obj){
	$("#"+obj).empty();
	$("#"+obj).html(strNames);
}

var strModels = "";
//根据原材料名称获取型号List
function getStrModels(obj,name){
	if($("#"+name).val() == ''){
		$("#"+obj).empty();
	}
	var params = {};
	params = {"materialMold":0,
        	"materialType":1,
        	"orgId":orgId,
        	"materialName":$("#"+name).val()};
	$.ajax({
        type: "post",
        async: false,
        url: baseUrl + "/material/getMaterialList.action",
        data: params,
        dataType: "json",
        success: function (data) {
        	strModels = "";
        	 var html  = "<option value=''></option>"
     			for(var i = 0; i < data.data.length; i++) {
     				html += "<option value='"+data.data[i].id+"'>"+data.data[i].mateModel+"</option>";
     			}
        	$("#"+obj).html(html);
        }
    });	
}
//新增料仓对应关系
function add(){
    if($("#str_bunker_Code").attr("cz")){
        return;
    }
    		var param = {};
            param.bunkerCode=$("#Bunker_code").val()
            param.orgId=orgId
            param.equId=$("#equipmentName").val()*1
            param.remarks=$("#str_remarks").val()
            param.mateIdCement1=$("#str_material_Mold1").val()*1
            param.mateIdCement2=$("#str_material_Mold2").val()*1
            param.mateIdCement3=$("#str_material_Mold3").val()*1
            param.mateIdCement4=$("#str_material_Mold4").val()*1
            param.mateIdAggregate1=$("#str_material_Mold5").val()*1
            param.mateIdAggregate2=$("#str_material_Mold6").val()*1
            param.mateIdAggregate3=$("#str_material_Mold7").val()*1
            param.mateIdAggregate4=$("#str_material_Mold8").val()*1
            param.mateIdAggregate5=$("#str_material_Mold9").val()*1
            param.mateIdAggregate6=$("#str_material_Mold10").val()*1
            param.mateIdWater=$("#str_material_Mold11").val()*1            
            param.mateIdAdmixture1=$("#str_material_Mold12").val()*1
            param.mateIdAdmixture2=$("#str_material_Mold13").val()*1
            param.valid_Flag=1
            param.operator = userName;
 
    if(param.bunkerCode == null || param.bunkerCode == ""){
    	swal("操作失败","料仓对应关系编号不能为空", "info");
    	return;
    }
   if(param.equId == null || param.equId == ""){
    	swal("操作失败","拌和设备名称不能为空", "info");
    	return;
    }
    var inx1 = 0;
    var cement = [];
    if($("#str_material_Mold1").val()!=null && $("#str_material_Mold1").val()!="" && $("#str_material_Mold1").val()!="null"){
    	cement[inx1] = $("#str_material_Mold1").val();
    	inx1++;
    }
    if($("#str_material_Mold2").val()!=null && $("#str_material_Mold2").val()!="" && $("#str_material_Mold2").val()!="null"){
    	cement[inx1] = $("#str_material_Mold2").val();
    	inx1++;
    }
    if($("#str_material_Mold3").val()!=null && $("#str_material_Mold3").val()!="" && $("#str_material_Mold3").val()!="null"){
    	cement[inx1] = $("#str_material_Mold3").val();
    	inx1++;
    }
    if($("#str_material_Mold4").val()!=null && $("#str_material_Mold4").val()!="" && $("#str_material_Mold4").val()!="null"){
    	cement[inx1] = $("#str_material_Mold4").val();
    	
    }
    
    var inx2 = 0;
    var aggregate = [];
    if($("#str_material_Mold5").val()!=null && $("#str_material_Mold5").val()!="" && $("#str_material_Mold5").val()!="null"){
    	aggregate[inx2] = $("#str_material_Mold5").val();
    	inx2++;
    }
    if($("#str_material_Mold6").val()!=null && $("#str_material_Mold6").val()!="" && $("#str_material_Mold6").val()!="null"){
    	aggregate[inx2] = $("#str_material_Mold6").val();
    	inx2++;
    }
    if($("#str_material_Mold7").val()!=null && $("#str_material_Mold7").val()!="" && $("#str_material_Mold7").val()!="null"){
    	aggregate[inx2] = $("#str_material_Mold7").val();
    	inx2++;
    }
    if($("#str_material_Mold8").val()!=null && $("#str_material_Mold8").val()!="" && $("#str_material_Mold8").val()!="null"){
    	aggregate[inx2] = $("#str_material_Mold8").val();
    	inx2++;
    }
    if($("#str_material_Mold9").val()!=null && $("#str_material_Mold9").val()!="" && $("#str_material_Mold9").val()!="null"){
    	aggregate[inx2] = $("#str_material_Mold9").val();
    	inx2++;
    }
    if($("#str_material_Mold10").val()!=null && $("#str_material_Mold10").val()!="" && $("#str_material_Mold10").val()!="null"){
    	aggregate[inx2] = $("#str_material_Mold10").val();
    	inx2++;
    }
    
    
    var water = [];
    if($("#str_material_Mold11").val()!=null && $("#str_material_Mold11").val()!="" && $("#str_material_Mold11").val()!="null"){
    	water[0] = $("#str_material_Mold11").val();
    }
    
    var inx3 = 0;
    var Admixture = [];
    if($("#str_material_Mold12").val()!=null && $("#str_material_Mold12").val()!="" && $("#str_material_Mold12").val()!="null"){
    	Admixture[inx3] = $("#str_material_Mold12").val();
    	inx3++;
    }
    if($("#str_material_Mold13").val()!=null && $("#str_material_Mold13").val()!="" && $("#str_material_Mold13").val()!="null"){
    	Admixture[inx3] = $("#str_material_Mold13").val();
    	inx3++;
    }
    var ss = SiloVerification(cement,aggregate,water,Admixture,allIds);
    if(ss==0){
    	swal("操作失败","不同仓内不能出现重复物料型号", "info");
    	return;
    }
    var params = {};
	var bunker_Correspondence = JSON.stringify(param);
	params.bunker_Correspondence = bunker_Correspondence;
    $.ajax({
        type: "post",
        url: baseUrl +"/cement_Correspondence/addBunker_Correspondence.action",
        data: params,
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
					search();
					$('#loginModal').modal('hide');
				});
				
			}else{
				swal("操作失败",data.message, "error");
			}
        }

   });
}

//料仓对应验证
function SiloVerification(cc1,cc2,cc3,cc4,allIds){
	//定义数组接收
	var c1 = [];
	var c2 = [];
	var c3 = [];
	var c4 = [];
	//遍历变量数组
	var c5 = [];
	//所有材料id的数组
	var c6 = [];
	
	//接收去重后的数组
	c1 = DuplicateRemoval(cc1);
	c2 = DuplicateRemoval(cc2);
	c3 = DuplicateRemoval(cc3);
	c4 = DuplicateRemoval(cc4);
	
	c6 = allIds;
	for (var i = 0; i < 3; i++) {
		if(i==0){
			c5 = c2;
		}
		if(i==1){
			c5 = c3;
		}
		if(i==2){
			c5 = c4;
		}
		//合并数组
		for (var j = 0; j < c5.length; j++) {
			c1[c1.length]=c5[j];
		}
	}
	//数组排序
	var nary  = c1.sort(); 
	var nary1 = allIds.sort();
	//循环判断数组中是否有重复的值
	for(var i=0;i<c1.length;i++){  
		if (nary[i]==nary[i+1]){  
			return 0;
		}
	}
	//如果选择完施工配比编号，没有在仓内录入信息
	if(c1.length<1){
		return 1
	}
	
	if(nary.toString()!=nary1.toString()){
		return 2;
	}
}
//去掉重复
function DuplicateRemoval(ar){
	var  end;//临时变量用于对比重复元素
	     ar.sort();//将数重新组排序
	      end = ar[0];
	      for (var i = 1; i < ar.length; i++) {
	          if (ar[i] == end) {//当前元素如果和临时元素相等则将此元素从数组中删除
	              ar.splice(i,1);
	             i--;
	         }else{
	             end = ar[i];
	         }
	     }
	     return ar;
}