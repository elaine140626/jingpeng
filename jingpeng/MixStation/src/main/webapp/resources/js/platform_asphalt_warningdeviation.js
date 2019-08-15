var org_Ids = "";
$(function () {
   
    
    $("#orgid").focus(function () {

        $.ajax({
            type: "Post",
            url: "../Organization/getAsphaltmixingstation",
            data:{"name":"dfz"},
            dataType: "json",
            success: function (data) {
                $("#orgid").empty();
                $.each(data,function (i,v) {
                    $('<option></option>').attr("value", v.id).html(v.org_Name).appendTo($("#orgid"));

                })


            }
        });
    })
    
    $.ajax({
        type: "post",
        url: "../Andr/getValue.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            $(".userid").html(data.userName)
            
        	if (data.org_Ids != null && data.org_Ids != "" && data.org_Ids != "null"){ 
        		org_Ids = data.org_Ids;
        	}
        	var list = data.list;
        	if(list != null && list.length > 0) {
        		for(var i = 0; i < list.length; i++) {
        			var name = list[i].org_Name;
		        	$("input[type='checkbox']").each(function(){
		        		var cshow = $(this).attr("cshow");
		        		if(cshow == name) {
		        			$(this).attr('checked', 'true');
		        		}
		        	});
		        }
            } else {
            	$("input[type='checkbox']").attr('checked', 'true');
            }
        }
    });
    
    getList(org_Ids);
    //btncha
$("#btncha").on("click",function () {
    	
        var table = $('#Deviation_Asphalt').dataTable();
        table.fnDestroy();
        $("#Deviation_Asphalt").DataTable({
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

            "ajax": "../Andr/Deviation_Asphalt",
            "deferRender": true,
            "createdRow": function (row, data, dataIndex) {

                $(row).children('td').eq(1).attr('style', 'text-align: center;')
            },
            "columns": [{
                "data": "serialNumber"
            }, {
                "data": "org_Name"
            }, {
                "data": "dev_Aggregate"
            }, {
                "data": "dev_Powder"
            }, {
                "data": "dev_Admixture"
            }, {
                "data": "dev_Asphalt"
            }, {
                "data": "mixTemperatureUp"
            }, {
                "data": "mixTemperatureDown"
            }],
            "createdRow": function (row, data, dataIndex) {
           	 if(data.dev_Aggregate == null || data.dev_Aggregate == "null") {
                	$('td:eq(2)', row).html(0)
                }
                if(data.dev_Powder == null || data.dev_Powder == "null") {
                	$('td:eq(3)', row).html(0)
                }
                if(data.dev_Admixture == null || data.dev_Admixture == "null") {
                	$('td:eq(4)', row).html(0)
                }
                if(data.dev_Asphalt == null || data.dev_Asphalt == "null") {
                	$('td:eq(5)', row).html(0)
                }
                if(data.mixTemperatureUp == null || data.mixTemperatureUp == "null") {
                	$('td:eq(6)', row).html(0)
                }
                if(data.mixTemperatureDown == null || data.mixTemperatureDown == "null") {
                 	$('td:eq(6)', row).html(0)
                 }
           },
            "columnDefs": [{
                "targets": [0],
                "visible": true,
                "searchable": false
            }]
        });
    })
$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
})

function getList(org_Ids) {
    $("#Deviation_Asphalt").DataTable({
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

        "ajax": "../Andr/Deviation_Asphalt?Org_ID="+org_Ids,
        "deferRender": true,
        "createdRow": function (row, data, dataIndex) {

            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columns": [{
            "data": "serialNumber"
        }, {
            "data": "org_Name"
        }, {
            "data": "dev_Aggregate"
        }, {
            "data": "dev_Powder"
        }, {
            "data": "dev_Admixture"
        }, {
            "data": "dev_Asphalt"
        }, {
            "data": "mixTemperatureUp"
        }, {
            "data": "mixTemperatureDown"
        }],
        "createdRow": function (row, data, dataIndex) {
        	 if(data.dev_Aggregate == null || data.dev_Aggregate == "null") {
             	$('td:eq(2)', row).html(0)
             }
             if(data.dev_Powder == null || data.dev_Powder == "null") {
             	$('td:eq(3)', row).html(0)
             }
             if(data.dev_Admixture == null || data.dev_Admixture == "null") {
             	$('td:eq(4)', row).html(0)
             }
             if(data.dev_Asphalt == null || data.dev_Asphalt == "null") {
             	$('td:eq(5)', row).html(0)
             }
             if(data.mixTemperatureUp == null || data.mixTemperatureUp == "null") {
             	$('td:eq(6)', row).html(0)
             }
             if(data.mixTemperatureDown == null || data.mixTemperatureDown == "null") {
              	$('td:eq(6)', row).html(0)
              }
        },
        "columnDefs": [{
            "targets": [0],
            "visible": true,
            "searchable": false
        }]
    });
    $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
}
function addss() {
	var org_id = $("#orgid").val()
    var Dev_Aggregate = $("#Dev_Aggregate")
    var Dev_Powder = $("#Dev_Powder")
    var Dev_Admixture = $("#Dev_Admixture")
    var Dev_Asphalt = $("#Dev_Asphalt")
    var MixTemperatureUp = $("#MixTemperatureUp")
    var MixTemperatureDown = $("#MixTemperatureDown")
    options={

            "str_dev_Aggregate":Dev_Aggregate.val(),
            "str_dev_Powder":Dev_Powder.val(),
            "str_dev_Admixture":Dev_Admixture.val(),
            "str_dev_Asphalt":Dev_Asphalt.val(),
            "str_mixTemperatureUp":MixTemperatureUp.val(),
            "str_mixTemperatureDown":MixTemperatureDown.val(),
            "org_id":$("#orgid").val()


        }
	if(org_id == null || org_id == ""){
	    alert("拌合站名称不能为空");
	    return;
	    }
    if(options.str_dev_Aggregate == null || options.str_dev_Aggregate == ""){
    	alert("骨料仓不能为空");
    	return;
    }
    if(options.str_dev_Powder == null || options.str_dev_Powder == ""){
    	alert("粉料仓不能为空");
    	return;
    }
    if(options.str_dev_Admixture == null || options.str_dev_Admixture == ""){
    	alert("沥青仓不能为空");
    	return;
    }
    if(options.str_dev_Asphalt == null || options.str_dev_Asphalt == ""){
    	alert("外掺挤仓不能为空");
    	return;
    }
    if(options.str_mixTemperatureUp == null || options.str_mixTemperatureUp == ""){
    	alert("混合料温度上限不能为空");
    	return;
    }
    if(options.str_mixTemperatureDown == null || options.str_mixTemperatureDown == ""){
    	alert("混合料温度下限不能为空");
    	return;
    }
    
    if(options.str_dev_Aggregate>100 || options.str_dev_Aggregate<0){
    	alert("骨料仓内数值不能超过100并且大于0");
    	return;
    }
    if(options.str_dev_Powder>100 || options.str_dev_Powder<0){
    	alert("粉料仓内数值不能超过100并且大于0");
    	return;
    }
    if(options.str_dev_Admixture>100 || options.str_dev_Admixture<0){
    	alert("沥青仓内数值不能超过100并且大于0");
    	return;
    }
    if(options.str_dev_Asphalt>100 || options.str_dev_Asphalt<0){
    	alert("外掺挤仓内数值不能超过100并且大于0");
    	return;
    }
    if(options.str_mixTemperatureUp < options.str_mixTemperatureDown){
    	alert("混合料温度下限不能超过上限");
    	return;
    }
    
        $.ajax({
            type: "post",
            url: "../Andr/addDev",
            dataType: "json",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify(options),
            success: function (data) {
                location.reload();
            }
        });
    
}



function goto(url) {

    var start_Time = ''
    var end_Time =''
    window.location.href=url+"?start_Time="+start_Time+"&end_Time="+end_Time+"&org_Ids="+''
}

d.checkNode = function (id,i_parent_Id,flag,str,obj) {
	if(obj == true) {
		org_Ids += ","+id
		var html = "<span class='s'>"+str+"</span>"
		$("#station").append(html)
	} else {
		org_Ids = org_Ids.replace(","+id,"")
		$(".s").each(function() {
			if ($(this).text() == str) {
				$(this).remove();
			}
		});
	}
}

function goto(url) {
    if(org_Ids.length > 0 ) {
        org_Ids =org_Ids;
    }
    window.location.href=url+"?start_Time="+$("#start_Time").val()+"&end_Time="+$("#end_Time").val()+"&org_Ids="+org_Ids

}