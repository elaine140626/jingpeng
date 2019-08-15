var i_org_Id = "";
$(function () {
	 i_org_Id = orgid;
	    

	    $("input[type='radio']").each(function(){
			var cshow = $(this).attr("cshow");
			if(cshow == orgname) {
				$(this).attr('checked', 'checked');
			}
		});
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

    var cementProductionPlan ={
        item:{},
        saveBtn: $("#planbtn").on("click", saveData),
    }
    /*!!!*/
    window.i_id=0
    window.textarea =$("#textarea")
    window.editFlg =false
    window.str_plan_No = $("#str_plan_No")
    window.i_org_id =6
    window.consProp = $("#consProp")
    window.str_material_Mold  = $("#str_material_Mold")//产品名称
    window.str_material_Name =$("#str_material_Name")
    window.i_bunkerCor_Id =$("#bunkerCor")
    window.str_proj_Pos = $("#str_proj_Pos")
    window.str_construction_Unit= $("#str_construction_Unit")
    window.str_Watering_Site =$("#str_Watering_Site")
    window.d_scheduled_Production =$("#d_scheduled_Production")
    window.str_startTime = $("#str_startTime")
    window.i_bunkerCor = $("#i_bunkerCor")
    window.i_equ_Id = $("#i_equ_Id")


    $.ajax({
        type: "post",
        url: "../cement_Production/getcementValue",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            $(".userid").html(data.userName)
        }
    });
    
    function getList() {

        $("#cementProductionPlan").DataTable({
            "paging": true,
            "lengthChange": false,
            "pageLength": 14,
            "searching": false,
            "ordering": false,
            "info": true,
            "sInfo": true,
            "autoWidth": false,
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
            "ajax": "../cement_Production/getCementProductionPlan?i_org_Id="+i_org_Id,
            "deferRender": true,
            "createdRow": function (row, data, dataIndex) {

                $(row).children('td').eq(1).attr('style', 'text-align: center;')
    			//$(row).children("td").eq(4).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_Prop_Code+"</a>");
                $(row).children("td").eq(4).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_id+");");
                $(row).children("td").eq(4).attr('onMouseOut','hide(this,\"mydiv1\")');
                $(row).children("td").eq(4).html("<a href='javascript:void(0);'>"+data.str_Prop_Code+"</a>");
            },
            "columns": [{
                "data": "serialNumber"
            }, {
                "data": "str_plan_No"
            }, {
                "data": "str_equipment_Name"
            }, {
                "data": "str_material_Model"
            }, {
                "data": "str_Prop_Code"
            }, {
                "data": "str_bunker_Code"
            }, {
                "data": "str_proj_Pos"
            }, {
                "data": "str_construction_Unit"
            }, {
                "data": "str_Watering_Site"
            }, {
                "data": "d_scheduled_Production"
            }, {
                "data": "i_isCancel"
            }, {
                "data": "str_startTime"
            }, {
                "data": "str_create_Date"
            }, {
                "data": "str_cancel_Reason"
            },
                {
                    "data": "operate"
                }],

            "columnDefs": [{
                "targets": [0],
                "visible": true,
                "searchable": false
            }]
        });
        $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
    }

    getList();

    $('#str_plan_No').blur(function(){
        $.ajax({
            type: "post",
            async: false,
            url: "../cement_Production/getPlanNo",
            dataType: "json",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify({"str_plan_No":window.str_plan_No.val(),"i_org_id":window.i_org_id}),
            success: function (data) {
                if(!$("#str_plan_No").attr("readonly")){
                    if(data.code == 300){
                        window.str_plan_No.attr("cz","cz");
                        alert(data.message)
                    }else{
                        window.str_plan_No.attr("cz","bcz");
                    }
                }

            }
        });
    });

    $('#i_equ_Id').focus(function(){
    		$("#str_material_Name").removeAttr("disabled");
    	    $("#str_material_Mold").removeAttr("disabled");
    	    $("#consProp").removeAttr("disabled");
    	    $("#i_bunkerCor").removeAttr("disabled");
    	
    	    $("#str_material_Name").html("");
    	    $("#str_material_Mold").html("");
    	    $("#consProp").html("");
    	    $("#i_bunkerCor").html("");
    	    
        $.ajax({
            type: "post",
            async: false,
            url: "../Common/getEquipmentInfo",
            dataType: "json",

            data:{"i_equipment_Type":1,"i_org_Id":i_org_Id},
            success: function (data) {
                $("#i_equ_Id").empty();
                $.each(data,function (i,v) {
                    $("<option></option>").attr("value",v.i_id).html(v.str_equipment_Name).appendTo($("#i_equ_Id"))
                })

            }
        }); 
    });

    function saveData () {
        var reg = /^[1-9]\d*$/;

        options = {
        		"i_org_id":i_org_Id,
            "str_plan_No":    window.str_plan_No.val(),
            "i_product_Id":  window.str_material_Mold.val(),
            "i_consProp_Id": window.consProp.val(),
            "i_bunkerCor_Id":  window.i_bunkerCor.val(),
            "str_proj_Pos":  window.str_proj_Pos.val(),
            "i_isCancel":0,
            "i_equ_Id":$("#i_equ_Id").val(),
            "str_construction_Unit":  window.str_construction_Unit.val(),
            "str_Watering_Site":  window.str_Watering_Site.val(),
            "d_scheduled_Production": $("#d_scheduled_Production").val(),
            "i_valid_Flag": 1,
            "str_startTime":window.str_startTime.val(),
            "str_material_Name":window.str_material_Name.val()

        };
        
        var flag = true;
        
        if(options.str_plan_No == null || options.str_plan_No == "") {
        	
    		/*alert("生产计划编号不能为空");*/
    		swal("操作失败","生产计划编号不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	if(options.i_equ_Id == null || options.i_equ_Id == "") {
    		/*alert("拌合设备名称不能为空");*/
    		swal("操作失败","拌合设备名称不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	if(options.str_material_Name == null || options.str_material_Name == "") {
    		/*alert("产品名称不能为空");*/
    		swal("操作失败","产品名称不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	if(options.i_product_Id == null || options.i_product_Id == "") {
    		/*alert("产品型号不能为空");*/
    		swal("操作失败","产品型号不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	if(options.i_consProp_Id == null || options.i_consProp_Id == "") {
    		/*alert("施工配比编号不能为空");*/
    		swal("操作失败","施工配比编号不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	if(options.i_bunkerCor_Id == null || options.i_bunkerCor_Id == "") {
    		/*alert("料仓对应关系编号不能为空");*/
    		swal("操作失败","料仓对应关系编号不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	if(options.str_proj_Pos == null || options.str_proj_Pos == "") {
    		/*alert("施工地点不能为空");*/
    		swal("操作失败","施工地点不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	if(options.str_construction_Unit == null || options.str_construction_Unit == "") {
    		/*alert("施工单位不能为空");*/
    		swal("操作失败","施工单位不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	if(options.str_Watering_Site == null || options.str_Watering_Site == "") {
    		/*alert("浇灌不能为空");*/
    		swal("操作失败","浇灌不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	if(options.d_scheduled_Production == null || options.d_scheduled_Production == "") {
    		/*alert("计划生产量不能为空");*/
    		swal("操作失败","计划生产量不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	if(options.str_startTime == null || options.str_startTime == "") {
    		/*alert("计划生产时间不能为空");*/
    		swal("操作失败","计划生产时间不能为空", "info");
    		flag = false;
    		$("#planbtn").removeAttr("data-dismiss");
    		return;
    	}
    	
    	if(flag){
    		$("#planbtn").attr("data-dismiss","modal");
    		
        if (window.editFlg == false) {
        	
        	
        	
            //添加
            options["str_create_Date"] = CurentTime();
        	 $.ajax({
                 type: "post",
                 url: '../cement_Production/addCementProductionPlan',
                 data: JSON.stringify(options),
                 contentType: 'application/json;charset=UTF-8',
                 dataType: "json",
                 success: function (data) {
//                     location.reload()             	 
               /*      if(data.message.indexOf('成功')>-1){
                 		alert(data.message);
                 		window.location.href=baseUrl + "/cement_Production/c_productionplan.html";
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
        					window.location.href=baseUrl + "/cement_Production/c_productionplan.html";
        				});
        				
        			}else{
        				swal("操作失败",data.message, "error");
        			}
                     
                     
                 }
             });

        }
        if(window.editFlg == true) {
            options["i_id"] = window.i_id;

            options["str_modify_Date"] = CurentTime();
            $.ajax({
                type: "post",
                url: '../cement_Production/updateCementProductionPlan',
                data: JSON.stringify(options),
                contentType: 'application/json;charset=UTF-8',
                dataType: "json",
                success: function (data) {
                	 console.log(data)
/*                 if(data.message.indexOf('成功')>-1){
              		alert(data.message);
              		window.location.href=baseUrl + "/cement_Production/c_productionplan.html";
              	}else{
              		alert(data.message);
              		window.location.href=baseUrl + "/cement_Production/c_productionplan.html";
              		return;
              	}*/
                	 if(data.code=="success"){
         				swal({
         					title: data.message,
         					text: "",
         					type: "success",
         					confirmButtonText: '确定',
         					cancelButtonFont: '微软雅黑',
         				},
         				function(){
         					window.location.href=baseUrl + "/cement_Production/c_productionplan.html";
         				});
         				
         			}else{
         				swal("操作失败",data.message, "error");
         				window.location.href=baseUrl + "/cement_Production/c_productionplan.html";
         			}
                   
                }
            });

        }
        console.log(options)
        if ( window.str_plan_No.attr("cz") == "bcz" ) {
              $.ajax({
                    type: "post",
                    url: url,
                    data: JSON.stringify(options),
                    contentType: 'application/json;charset=UTF-8',
                    dataType: "json",
                    success: function (data) {
                    	
                    	
                    }
                });
                //location.reload();

            window.str_plan_No.removeAttr("cz");
            window.str_proj_Pos = ''
            window.str_construction_Unit= ''
            window.str_Watering_Site =''
            window.d_scheduled_Production =''
            window.str_startTime = ''

        } else {
            return false
        }
    }

    }

})
 //产品名称
//飘窗
    
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
		    url :  "../CementConstructionProp/getC_ConstructionDeatlByid.html",
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
		    				 		"<td>"+data.data.cd[i].Material_Name+"/"+data.data.cd[i].Material_Model+"</td>" +
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
 
	function hide(obj,id) { 
		var objDiv = $("#"+id+""); 
		$(objDiv).css("display", "none"); 
	} 
  var cpNameStr = "";
function addName(){
	$.ajax({
        type: "post",
        async: false,
        url: "../Common/getMaterialNames.html",
        dataType: "json",

        data:{"str_material_Type":1,"str_material_Mold":1,"i_org_Id":i_org_Id},
        success: function (data) {

			var html = "<option value='' selected='selected'> </option>"
			for(var i = 0; i < data.length; i++) {
				html += "<option value='"+data[i].str_material_Name+"'>"+data[i].str_material_Name+"</option>"
			}
			cpNameStr = html;
        }
    });

}

 
    function closes(){
    	window.location.href=baseUrl + "/cement_Production/c_productionplan.html";
    }
    function cpName(){
    	$("#str_material_Mold").empty();
    	$("#str_material_Name").empty();
		$("#str_material_Name").html(cpNameStr);
    }
    //查询产品型号
    var cpModelStr="";
    function getModels(){
    	 $.ajax({
             type: "post",
             async: false,
             url: "../Common/getMaterialModels.html",
             data: {"str_material_Type":1,"str_material_Mold":1,"str_material_Name":$("#str_material_Name").val(),"i_org_Id":i_org_Id},
             dataType: "json",
             success: function (data) {
            	 var html = "<option value='' selected='selected'> </option>"
         			for(var i = 0; i < data.length; i++) {
         				html += "<option value='"+data[i].i_id+"'>"+data[i].str_material_Model+"</option>"
         			}
            	 	cpModelStr = html;
             }
         });
    }
   function cpModel(){
	   	$("#str_material_Mold").empty();
		$("#str_material_Mold").html(cpModelStr);
   }
   
 //查询施工配比编号
   var sgpbhStr = "";
   function getSgpb(){
       $.ajax({
           type: "post",
           url: "../CementConstructionProp/getSgpbNo.html",
           data:{"i_product_Id": $("#str_material_Mold").val(),"i_org_Id":i_org_Id},
           dataType: "json",
           success: function (data) {
          	 var html = "<option value='' selected='selected'> </option>"
       			for(var i = 0; i < data.data.length; i++) {
       				html += "<option value='"+data.data[i].Id+"'>"+data.data[i].Prop_Code+"</option>"
       			}
          	sgpbhStr = html;
           }
       });
   
   }
  function showSgpb(){
		$("#consProp").empty();
		$("#i_bunkerCor").empty();
		$("#consProp").html(sgpbhStr);
  }

   //查询料仓
  var LcbhStr = "";
  function getLcbh(){
      $.ajax({
          type: "post",
          url: "../cement_Production/getBunker_CorrespondencesByContonid",
          data:{"i_consProp_Id": $("#consProp").val()},
          dataType: "json",
          success: function (data) {
        	  var html = "<option value='' selected='selected'> </option>"
         			for(var i = 0; i < data.data.length; i++) {
         				html += "<option value='"+data.data[i].i_id+"'>"+data.data[i].str_bunker_Code+"</option>"
         			}
        	  LcbhStr = html;
          }
      });
  }
  
  function showLcbh(){
	  $("#i_bunkerCor").empty();
	  $("#i_bunkerCor").html(LcbhStr);
  }

    
function getMaterialName(obj){
	
}


function upt(i_id) {
    window.i_id= i_id
    $("#loginModal3").modal("show")

}

function update(i_id) {

	addName();
    $("#str_plan_No").attr("readonly",true);
    $("#str_material_Name").attr("disabled","disabled");
    $("#str_material_Mold").attr("disabled","disabled");
    $("#consProp").attr("disabled","disabled");
    $("#i_bunkerCor").attr("disabled","disabled");
    window.editFlg =true;
    window.i_id =i_id;
    var params = {};
    params.i_id=i_id;
    $.ajax({
        type: "post",
        async: false,
//        contentType : 'application/json;charset=UTF-8',
        url: "../cement_Production/getProductionById",
        data: params,
        dataType: "json",
        success: function (data) {
            console.log(data[0])

            window.str_plan_No.val(data[0].Plan_No);
            window.str_plan_No.attr("readonly","true");
            window.i_equ_Id.html("<option selected='selected' value="+data[0].Equ_ID+">"+data[0].Equipment_Name+"</option>");
            window.str_material_Name.html("<option selected='selected'>"+data[0].Material_Name+"</option>");
            window.str_material_Mold.html("<option selected='selected' value="+data[0].Product_ID+">"+data[0].Material_Model+"</option>");
            window.consProp.html("<option selected='selected' value="+data[0].ConsProp_ID+">"+data[0].Prop_Code+"</option>");
            window.i_bunkerCor.html("<option selected='selected' value="+data[0].BunkerCor_ID+">"+data[0].Bunker_Code+"</option>");
            window.str_proj_Pos.val(data[0].Proj_Pos);
            window.str_construction_Unit.val(data[0].Construction_Unit);
            window.str_Watering_Site.val(data[0].Watering_Site);
            window.d_scheduled_Production.val(data[0].Scheduled_Production);
            window.str_startTime.val(FormatDateTime(data[0].StartTime));
        }
    });



        
        

    $("#loginModal").modal("show")

}

function del(i_id){
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
        url: "../cement_Production/delCementProductionPlan",
        data: JSON.stringify({"i_id": i_id}),
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
					location.reload();
				}); },200);
				
			}else{
				swal("操作失败",data.message, "error");
			}
            /*location.reload();*/
        }
    });

});
}
//$("#zfbutton").on("click",function () {
//
//
//    $.ajax({
//        type: "post",
//        async: false,
//        contentType: 'application/json;charset=UTF-8',
//        url: "../cement_Production/zfProductionPlan",
//        data: JSON.stringify({"i_id": window.i_id,"str_cancel_Reason":window.textarea.val(),"str_invalid_Person":'朱昭霖',"str_cancel_Time":CurentTime}),
//        dataType: "json",
//        success: function (data) {
//            location.reload();
//        }
//    });
//    
//})


/*
* Unix时间戳转换成日期格式  FormatDateTime("1497232433000")
* @param UnixTime Unix时间戳
* @return yyyy-MM-dd HH:mm:ss
* tongn create
*/
function FormatDateTime(UnixTime) {
    var a = UnixTime
    var date = new Date(parseInt(a));
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
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

function getC_productionplanSelect(){
	
	var table = $('#cementProductionPlan').dataTable();
	table.fnDestroy();
	$('#cementProductionPlan').DataTable({
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
            url: "../cement_Production/getCementProductionPlan",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_org_Id = i_org_Id;
                //施工配比编号
                //param.NumberOfConstructionRatio = $("#NumberOfConstructionRatio").val();
              
                param.str_Prop_Code = $("#NumberOfConstructionRatio").val();
                param.productName = $("#productName").val();
                param.productModel = $("#productModel").val();
                return param;//自定义需要传递的参数。
            }
        },
        "columns" : [{
            "data": "serialNumber"
        }, {
            "data": "str_plan_No"
        }, {
            "data": "str_equipment_Name"
        }, {
            "data": "str_material_Model"
        }, {
            "data": "str_Prop_Code"
        }, {
            "data": "str_bunker_Code"
        }, {
            "data": "str_proj_Pos"
        }, {
            "data": "str_construction_Unit"
        }, {
            "data": "str_Watering_Site"
        }, {
            "data": "d_scheduled_Production"
        }, {
            "data": "i_isCancel"
        }, {
            "data": "str_startTime"
        }, {
            "data": "str_create_Date"
        }, {
            "data": "str_cancel_Reason"
        },
            {
                "data": "operate"
            }],
        "createdRow" : function(row, data, dataIndex) {


            $(row).children('td').eq(1).attr('style', 'text-align: center;')
			//$(row).children("td").eq(4).html("<a href='javascript:void(0);' id='t1' onMouseOver='show2(this,\"mydiv1\","+data.i_id+");' onMouseOut='hide(this,\"mydiv1\")'>"+data.str_Prop_Code+"</a>");
            $(row).children("td").eq(4).attr('onMouseOver',"show2(this,\"mydiv1\","+data.i_id+");");
            $(row).children("td").eq(4).attr('onMouseOut','hide(this,\"mydiv1\")');
            $(row).children("td").eq(4).html("<a href='javascript:void(0);'>"+data.str_Prop_Code+"</a>");
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}