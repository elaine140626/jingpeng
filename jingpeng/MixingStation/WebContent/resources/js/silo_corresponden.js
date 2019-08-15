var i_org_Id = "";
//接收通过施工配比id获取所有的材料id
var allIds = [];
$(function () {
	
	 i_org_Id = orgid;
	    

	    $("input[type='radio']").each(function(){
			var cshow = $(this).attr("cshow");
			if(cshow == orgname) {
				$(this).attr('checked', 'checked');
			}
		});

    window.i_org_id =3
    window.consProp = $("#consProp")
    window.str_material_Mold  = $("#str_material_Mold")
    window.str_material_Name =$("#str_material_Name")

    window.arrayname = []
    window.arraymodle =[]
var bunk = {
        arrayname :[],
        arraymodle:[]
}


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

//    $('#consProp').blur(function() {
//
//        $.ajax({
//            type: "post",
//            url: "../CementConstructionProp/getC_ConstructionDeatlByid",
//            data: JSON.stringify({"i_id": $("#consProp").val()}),
//            dataType: "json",
//            contentType: 'application/json;charset=UTF-8',
//            success: function (data) {
//
//
//
//                $.each(data,function (i,v) {
//
//                    window.arrayname.push(v.str_Material_Name)
//
//                    window.arraymodle.push(v.str_Material_Model)
//
//                })
//            }
//        });
//    })


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
        "ajax" : "../cement_Production/getBunker_Correspondences.action?i_org_Id="+i_org_Id,
        "deferRender" : true,
        "columns" : [{
            "data" : "serialNumber"
        }, {
            "data" : "str_bunker_Code"
        }, {
            "data" : "str_prop_Code"
        }, {
            "data" : "str_Material"
        }, {
            "data" : "str_operator"
        }, {
            "data" : "str_create_Date"
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
    $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
    $('#Bunker_code').blur(function(){
        $.ajax({
            type: "post",
            async: false,
            url: "../cement_Production/getBunker_CorrespondenceByCode.action",
            dataType: "json",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify({"str_bunker_Code":$("#Bunker_code").val(),"i_org_Id":3}),
            success: function (data) {
                if(data.code == "success"){
                    $("#str_bunker_Code").attr("cz","cz");
                    swal(data.message,"", "success");
                }else{
                    $("#str_bunker_Code").attr("cz","bcz");
                }
            }
        });


    });


    $("#btnbunk").on("click",function () {
        if($("#str_bunker_Code").attr("cz")){
            return;
        }

        options ={

                "str_bunker_Code":$("#Bunker_code").val(),
                "i_org_Id":i_org_Id,
                "i_consProp_Id":$("#consProp").val(),
                "str_material_Name":$("#str_material_Name").val(),
                "str_material_Mold":$("#str_material_Mold").val(),
                "str_remarks":$("#str_remarks").val(),
                "i_mateId_Cement1":$("#str_material_Mold1").val(),
                "i_mateId_Cement2":$("#str_material_Mold2").val(),
                "i_mateId_Cement3":$("#str_material_Mold3").val(),
                "i_mateId_Cement4":$("#str_material_Mold4").val(),
                "i_mateId_Aggregate1":$("#str_material_Mold5").val(),
                "i_mateId_Aggregate2":$("#str_material_Mold6").val(),
                "i_mateId_Aggregate3":$("#str_material_Mold7").val(),
                "i_mateId_Aggregate4":$("#str_material_Mold8").val(),
                "i_mateId_Aggregate5":$("#str_material_Mold9").val(),
                "i_mateId_Aggregate6":$("#str_material_Mold10").val(),
                "i_mateId_Water":$("#str_material_Mold11").val(),                
                "i_mateId_Admixture1":$("#str_material_Mold12").val(),
                "i_mateId_Admixture2":$("#str_material_Mold13").val(),
                "i_valid_Flag":1
        };
        if(options.str_bunker_Code == null || options.str_bunker_Code == ""){
        	swal("操作失败","料仓对应关系编号不能为空", "info");
	    	return;
        }
       if(options.str_material_Name == null || options.str_material_Name == ""){
        	swal("操作失败","产品名称不能为空", "info");
	    	return;
        }
        if(options.str_material_Mold == null || options.str_material_Mold == ""){
        	swal("操作失败","规格型号不能为空", "info");
	    	return;
        }
        if(options.i_consProp_Id == null || options.i_consProp_Id == ""){
        	swal("操作失败","施工配比编号不能为空", "info");
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
        }else if(ss==1){
        	swal("操作失败","施工配比里的物料没有配置在物料仓内", "info");
        	return;
        }else if(ss==2){
        	swal("操作失败","施工配比里的物料没有全部配置在物料仓内", "info");
        	return;
        }

        
        $.ajax({
            type: "post",
            url: "../cement_Production/addBunker_Correspondence.action",
            data: JSON.stringify(options),
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
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
    					window.location.href=baseUrl + "/cement_Production/silo_correspondence.action";
    				});
    				
    			}else{
    				swal("操作失败",data.message, "error");
    			}
            }

        });

    })

})
function closes(){
	window.location.href=baseUrl + "/cement_Production/silo_correspondence.action";
}
//产品名称查询
var nameStr = "";
function getMaterialNames() {

    $.ajax({
        type: "post",
        async: false,
        //contentType: 'application/json;charset=UTF-8',
        url: "../Common/getMaterialNames.action",
        data: {"str_material_Type":"1","str_material_Mold":"1","i_org_Id":i_org_Id},
        dataType: "json",
        success: function (data) {

			var html ="<option value=''></option>"
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].str_material_Name+"'>"+data[i].str_material_Name+"</option>"
			}
			nameStr = html;
        }
    });
    
}

function getShowName(){
	 $("#str_material_Name").empty();
	 $("#str_material_Name").html(nameStr);
}

//查询产品型号
function getMaterialModels(obj) {

	if($("#str_material_Name").val()==""){
		 $("#str_material_Mold").empty();
		return;
	}
	
    $.ajax({
        type : "POST",
        url :"../Common/getMaterialModels.action",
        data : {"str_material_Type":1,
        	"i_org_Id":i_org_Id,
            "str_material_Mold":1,
            "str_material_Name":$("#str_material_Name").val()},
        dataType : "json",
        success : function(data) {
			var html ="<option value=''></option>"
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>"
			}
			$("#str_material_Mold").empty();
			$("#str_material_Mold").html(html);
        }
    });
    
    
}

//注释：更改于2018-06-28
function getSgpb(){
	if($("#str_material_Mold").val()==null || $("#str_material_Mold").val()==""){
		 $("#consProp").empty();
		 return;
	}
   $.ajax({
       type: "post",
       url: "../cementConstructionProp/getCementConstructionPropbypids.action",
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
//注释：更改于2018-06-28 

//材料前面的
var strNames = "";


function getStrNmae(obj){
	
	if($("#consProp").val()==""){
		return;
	}
	
	$.ajax({
        type: "post",
        async: false,
        url: "../cementTheory/getYclList.action",
        dataType: "json",

        data:{"i_id":$("#consProp").val()},
        success: function (data) {
        	strNames = "";
        	 var html  = "<option value=''></option>"
      			for(var i = 0; i < data.data.length; i++) {
      				html += "<option value="+data.data[i].Material_Name+">"+data.data[i].Material_Name+"</option>";
      			}
        	 strNames = html;
        }
    });
	
	$.ajax({
        type: "post",
        async: false,
        url: "../cementTheory/getAllMaterials_id.action",
        dataType: "json",

        data:{"i_id":$("#consProp").val()},
        success: function (data) {
        	allIds.splice(0,allIds.length);
        	for (var i = 0; i < data.data.length; i++) {
        		allIds[i]=data.data[i].Materials_ID;
			}
        }
    });
}

function getNameShow(obj){
	$("#"+obj).empty();
	$("#"+obj).html(strNames);
}

//材料后面的信息
//注释：更改于2018-06-28
var strModels = "";
function getStrModels(obj,name){
	var params = {};

	params = {"i_id":$("#consProp").val(),"Material_Name":$("#"+name).val()};

	
	$.ajax({
        type: "post",
        async: false,
        url: "../cementTheory/getYclModelList.action",
        data: params,
        dataType: "json",
        success: function (data) {
        	strModels = "";
        	 var html  = "<option value=''></option>"
     			for(var i = 0; i < data.data.length; i++) {
     				html += "<option value='"+data.data[i].Materials_ID+"'>"+data.data[i].Material_Model+"</option>";
     			}
//        	 	strModels = html;
        	 	$("#"+obj).html(html);
        }
    });	
}


function getMaterialModelss(obj) {


    var aterial_modelid = obj.id
    var materialmodelser = (obj.id).toString().substring(17)

    $("#" + aterial_modelid).empty()
    $.each(window.arraymodle,function (i,v) {

        $('<option></option>').html(v).appendTo($("#" + aterial_modelid));
    })


}

function  getproid(obj) {
    var aterial_modelid = obj.id
    var materialmodelser = (obj.id).toString().substring(17)

    console.log($("#str_material_Name"+materialmodelser).val())
    console.log($("#"+aterial_modelid).val())

    $.ajax({
        type : "POST",
        url :"../Common/getMaterialModelIdbyNameAndCode.action",
        contentType : 'application/json;charset=UTF-8',
        data : JSON.stringify({"str_material_Model":$("#"+aterial_modelid).val(), "str_material_Name":$("#str_material_Name"+materialmodelser).val(), "i_org_Id":i_org_Id}),
        dataType : "json",
        success : function(data) {

            console.log(data)

            if(data.str_material_Model == $("#"+aterial_modelid).val()){
                $("#" + aterial_modelid).attr("prid",data.i_id)
                if($("#" + aterial_modelid).attr("prid") == 0){
                    alert("不存在")
                }

            }
        }
    });

}


function getMaterialnamedelt(obj){

    var aterial_modelid = obj.id
    var materialmodelser = (obj.id).toString().substring(17)


    $("#" + aterial_modelid).empty()
    $.each(window.arrayname,function (i,v) {

        $('<option></option>').attr("value", v).html(v).appendTo($("#" + aterial_modelid));
    })


}


function getconsProp() {
    $.ajax({
        type: "post",
        url: "../cementConstructionProp/getCementConstructionPropbypid.action",
        data: {"i_product_Id":$("#str_material_Mold").val(),"i_org_Id":i_org_Id},
        dataType: "json",

        success: function (data) {

            $("#consProp").empty();
            $.each(data, function (i, v) {
                $('<option></option>').attr("value", v.i_id).html(v.str_prop_Code).appendTo($("#consProp"));

            })
        }
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


function getSilo_correspondenceSelect(){
	
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
            url: "../cement_Production/getBunker_Correspondences.action",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_org_Id = i_org_Id;
                param.SiloCorrespondingRelationshipNumber = $("#SiloCorrespondingRelationshipNumber").val();
                param.productName = $("#productName").val();
                param.productModel = $("#productModel").val();
               // param.str_endDate = $("#str_endDate").val();
                //param.mixingEquipmentName = $("#mixingEquipmentName").val();
               // param.analysisResults = $("#analysisResults").val();
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [{
            "data" : "serialNumber"
        }, {
            "data" : "str_bunker_Code"
        }, {
            "data" : "str_prop_Code"
        }, {
            "data" : "str_Material"
        }, {
            "data" : "str_operator"
        }, {
            "data" : "str_create_Date"
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
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}