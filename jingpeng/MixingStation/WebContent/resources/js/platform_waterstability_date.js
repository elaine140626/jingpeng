var baseUrl = "";
var org_Ids = "";
$(function() {

	var afterUrl =  window.location.search.substring(1);

	var start_Time = "";
	var end_Time = "";
	if(afterUrl != "") {
		var a =  afterUrl.toString().split("&");
		start_Time = a[0].substring(11);
	    end_Time = a[1].substring(9);
	    var org_id = a[2].substring(7)
	}
	
	/*if(start_Time == null || start_Time == ''){*/
   /* start_Time = new Date().getFullYear() + "-" + ((new Date().getMonth()+1) < 10 ? '0' + (new Date().getMonth()+1) : '' + (new Date().getMonth() + 1)) + "-" + (new Date().getDate()-3)+" "+"00:"+"00:"+"00";
    start = new Date().getFullYear() + "-" + ((new Date().getMonth()+1) < 10 ? '0' + (new Date().getMonth()+1) : '' + (new Date().getMonth() + 1)) + "-" + (new Date().getDate()-3);

    $("#start_Time").attr("value",start);*/
/*}else{
	start_Time = start_Time+" "+"00:"+"00:"+"00";
}*/
/* if(end_Time == null || end_Time == '') {*/
	var date = new Date();
	var year = date.getFullYear(); //获取年 
	var month = date.getMonth()+1;//获取月  
	var day = date.getDate(); //获取当日
	
    end = getDatePosition(year+"-"+month+"-"+day);
    end_Time = getDatePosition(year+"-"+month+"-"+day)+" 23:"+"59:"+"59";
    $("#end_Time").attr("value",end);
    
    var start = getPreMonths(end);
    start_Time = start + " 00:00:00";
    $("#start_Time").attr("value",start);
/*}else {
	end_Time = end_Time+" "+"23:"+"59:"+"59";
	
}*/
    window.start_Time = start_Time
    window.end_Time = end_Time


    $.ajax({
        type: "post",
        url: "../waterstabilityAndr/getValuebySw.html",
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

	
    baseUrl = getContextPath();
    getList(start_Time,end_Time,org_Ids);

    $(".btncha").on("click",function () {

        var os = "";
        if (org_Ids.length > 0) {
            os = org_Ids;
        } else {
            os = "";
        }
        var table = $('#cement_ProductionDatas').dataTable();
        table.fnDestroy();
        $("#cement_ProductionDatas").DataTable({
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

            "ajax" : {
                type: "post",
                url: "../cement_Production/getCement_ProductionDatas",
                dataSrc: "data",
                data: function (d) {


                    var param = {};
                    param.os = os;
                    param.tag = "1"
                    
                    param.str_beginDate =$("#start_Time").val()+" "+"00:"+"00:"+"00";
                    param.str_endDate = $("#end_Time").val()+" "+"23:"+"59:"+"59";

                    return param;//自定义需要传递的参数。
                }},
            "deferRender": true,
            "createdRow": function (row, data, dataIndex) {

                var s = (data.i_analysis_Results ==1) ?  ("数据正常") :(data.i_analysis_Results == 2 ? "拌合时间不合格":"配比不合格")
                $('td:eq(21)', row).html(s)

                /*if(data.i_analysis_Results_Cement != 1){


                    $(row).children("td").eq(5).addClass("warn2");
                    // $('td:eq(5)', row).attr("class","warn2")
                    //$('td:eq(5)', row).addClass("warn2")

                    $('td:eq(6)', row).attr("class","warn2")
                    $('td:eq(7)', row).attr("class","warn2")
                    $('td:eq(8)', row).attr("class","warn2")

                }
                if(data.i_analysis_Results_Aggregate != 1){
                    $('td:eq(9)', row).attr("class","warn2")
                    $('td:eq(10)', row).attr("class","warn2")
                    $('td:eq(11)', row).attr("class","warn2")
                    $('td:eq(12)', row).attr("class","warn2")
                    $('td:eq(13)', row).attr("class","warn2")
                    $('td:eq(14)', row).attr("class","warn2")

                }
                if(data.i_analysis_Results_Water != 1){
                    $('td:eq(15)', row).attr("class","warn2")
                }
                if(data.i_analysis_Results_Admixture != 1){
                    $('td:eq(16)', row).attr("class","warn2")
                    $('td:eq(17)', row).attr("class","warn2")
                }*/


                $(row).children('td').eq(1).attr('style', 'text-align: center;')
            },
            "columns": [{
                "data": "serialNumber"
            }, {
                "data": "org_Name"
            }, {
                "data": "str_Equipment_Name"
            }, {
                "data": "str_Prop_Code"
            }, {
                "data": "str_collect_Date"
            }, {
                "data": "i_mix_Time"
            }, {
                "data": "d_weight_Cement1"
            }, {
                "data": "d_weight_Cement2"
            }, {
                "data": "d_weight_Cement3"
            }, {
                "data": "d_weight_Cement4"
            }, {
                "data": "d_weight_Aggregate1"
            }, {
                "data": "d_weight_Aggregate2"
            }, {
                "data": "d_weight_Aggregate3"
            }, {
                "data": "d_weight_Aggregate4"
            }, {
                "data": "d_weight_Aggregate5"
            }, {
                "data": "d_weight_Aggregate6"
            }, {
                "data": "d_weight_Water"
            }, {
                "data": "d_weight_Admixture1"
            }, {
                "data": "d_weight_Admixture2"
            }, {
                "data": "d_total_Weight"
            }, {
                "data": "str_proj_Pos"
            }, {
                "data": "str_watering_Site"
            }, {
                "data": "i_analysis_Results"
            }],

            "columnDefs": [{
                "targets": [0],
                "visible": true,
                "searchable": false
            }]
        });
    })
    $.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
});

function getList(start_Time,end_Time,org_Ids) {
	console.log(org_Ids)
    $("#cement_ProductionDatas").DataTable({
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

        "ajax" : {
            type: "post",
            url: "../cement_Production/getCement_ProductionDatas",
            dataSrc: "data",
            data: function (d) {


                var param = {};
                param.os = org_Ids;
                param.tag = "1"
                param.str_beginDate =start_Time;
                param.str_endDate = end_Time;

                return param;//自定义需要传递的参数。
            }},
        "deferRender": true,
        "createdRow": function (row, data, dataIndex) {

            var s = (data.i_analysis_Results ==1) ?  ("数据正常") :(data.i_analysis_Results == 2 ? "拌合时间不合格":"配比不合格")
            $('td:eq(21)', row).html(s)

            /*if(data.i_analysis_Results_Cement != 1){


                $(row).children("td").eq(5).addClass("warn2");
               // $('td:eq(5)', row).attr("class","warn2")
                //$('td:eq(5)', row).addClass("warn2")

                $('td:eq(6)', row).attr("class","warn2")
                $('td:eq(7)', row).attr("class","warn2")
                $('td:eq(8)', row).attr("class","warn2")

            }
            if(data.i_analysis_Results_Aggregate != 1){
                $('td:eq(9)', row).attr("class","warn2")
                $('td:eq(10)', row).attr("class","warn2")
                $('td:eq(11)', row).attr("class","warn2")
                $('td:eq(12)', row).attr("class","warn2")
                $('td:eq(13)', row).attr("class","warn2")
                $('td:eq(14)', row).attr("class","warn2")

            }
            if(data.i_analysis_Results_Water != 1){
                $('td:eq(15)', row).attr("class","warn2")
            }
            if(data.i_analysis_Results_Admixture != 1){
                $('td:eq(16)', row).attr("class","warn2")
                $('td:eq(17)', row).attr("class","warn2")
            }*/


            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columns": [{
            "data": "serialNumber"
        }, {
            "data": "org_Name"
        }, {
            "data": "str_Equipment_Name"
        }, {
            "data": "str_Prop_Code"
        }, {
            "data": "str_collect_Date"
        }, {
            "data": "i_mix_Time"
        }, {
            "data": "d_weight_Cement1"
        }, {
            "data": "d_weight_Cement2"
        }, {
            "data": "d_weight_Cement3"
        }, {
            "data": "d_weight_Cement4"
        }, {
            "data": "d_weight_Aggregate1"
        }, {
            "data": "d_weight_Aggregate2"
        }, {
            "data": "d_weight_Aggregate3"
        }, {
            "data": "d_weight_Aggregate4"
        }, {
            "data": "d_weight_Aggregate5"
        }, {
            "data": "d_weight_Aggregate6"
        }, {
            "data": "d_weight_Water"
        }, {
            "data": "d_weight_Admixture1"
        }, {
            "data": "d_weight_Admixture2"
        }, {
            "data": "d_total_Weight"
        }, {
            "data": "str_proj_Pos"
        }, {
            "data": "str_watering_Site"
        }, {
            "data": "i_analysis_Results"
        }],
        "createdRow" : function(row, data, dataIndex) {

           
            
            if(data.i_mix_Time==null || data.i_mix_Time==''){
            	$('td:eq(5)', row).html(0);
            }
            if(data.d_weight_Cement1==null || data.d_weight_Cement1==''){
            	$('td:eq(6)', row).html(0);
            }
            if(data.d_weight_Cement2==null || data.d_weight_Cement2==''){
            	$('td:eq(7)', row).html(0);
            }
            if(data.d_weight_Cement3==null || data.d_weight_Cement3==''){
            	$('td:eq(8)', row).html(0);
            }
            if(data.d_weight_Cement4==null || data.d_weight_Cement4==''){
            	$('td:eq(9)', row).html(0);
            }
            if(data.d_weight_Aggregate1==null || data.d_weight_Aggregate1==''){
            	$('td:eq(10)', row).html(0);
            }
            if(data.d_weight_Aggregate2==null || data.d_weight_Aggregate2==''){
            	$('td:eq(11)', row).html(0);
            }
            if(data.d_weight_Aggregate3==null || data.d_weight_Aggregate3==''){
            	$('td:eq(12)', row).html(0);
            }
            if(data.d_weight_Aggregate4==null || data.d_weight_Aggregate4==''){
            	$('td:eq(13)', row).html(0);
            }
            if(data.d_weight_Aggregate5==null || data.d_weight_Aggregate5==''){
            	$('td:eq(14)', row).html(0);
            }
            if(data.d_weight_Aggregate6==null || data.d_weight_Aggregate6==''){
            	$('td:eq(15)', row).html(0);
            }
            if(data.d_weight_Water==null || data.d_weight_Water==''){
            	$('td:eq(16)', row).html(0);
            }
            if(data.d_weight_Admixture1==null || data.d_weight_Admixture1==''){
            	$('td:eq(17)', row).html(0);
            }
            if(data.d_weight_Admixture2==null || data.d_weight_Admixture2==''){
            	$('td:eq(18)', row).html(0);
            }
            if(data.d_total_Weight==null || data.d_total_Weight==''){
            	$('td:eq(19)', row).html(0);
            }
            if(data.i_analysis_Results==null || data.i_analysis_Results==''){
            	$('td:eq(22)', row).html(0);
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

function getInfo(i_id) {
    $("#info span").html("");
    $("#loginModal").modal("show")
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
        url :  baseUrl + "/AsphaltProductionData/getAsphaltPropDataAnalysis.html",
        data : {"i_id":i_id},
        dataType : "json",
        success : function(data) {
            var asphalt_Production = data.asphalt_ProductionDTO;
            var asphaltPropDataAnalysisList = data.asphaltPropDataAnalysisList;
            $("#str_proportion_Code").html(asphalt_Production.str_proportion_Code);
            $("#str_grade_Code").html(asphalt_Production.str_grade_Code);
            $("#str_productInfo").html(asphalt_Production.str_productInfo);
            $("#d_total_Weight").html(asphalt_Production.d_total_Weight);
            var html="";
            for(var i = 0; i < asphaltPropDataAnalysisList.length; i++) {
                if(asphaltPropDataAnalysisList[i].str_data_Type == 0) {
                    html += "<tr><td class='dise'>采集重量</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
                }
                if(asphaltPropDataAnalysisList[i].str_data_Type == 1) {
                    html += "<tr><td class='dise'>采集配比</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
                }
                if(asphaltPropDataAnalysisList[i].str_data_Type == 2) {
                    html += "<tr><td class='dise'>生产配比</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
                }
                if(asphaltPropDataAnalysisList[i].str_data_Type == 3) {
                    html += "<tr><td class='dise'>生产配比重量</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
                }
                if(asphaltPropDataAnalysisList[i].str_data_Type == 4) {
                    html += "<tr><td class='dise'>实际偏差</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no2+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no3+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no4+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no5+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_no6+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_hotPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_coldPowder+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_asphalt+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture1+"</td>"
                        +"<td>"+asphaltPropDataAnalysisList[i].d_admixture2+"</td></tr>";
                }
            }
            var asphaltGradDataAnalysisList = data.asphaltGradDataAnalysisList;
            var str = "[";
            for(var i = 0; i < asphaltGradDataAnalysisList.length; i++) {
                var name="";
                if(asphaltGradDataAnalysisList[i].str_curve_Type == 1) {
                    name ="采集数据曲线";
                }
                if(asphaltGradDataAnalysisList[i].str_curve_Type == 2) {
                    name ="生产配比重量曲线";
                }
                if(asphaltGradDataAnalysisList[i].str_curve_Type == 3) {
                    name ="级配上限";
                }
                if(asphaltGradDataAnalysisList[i].str_curve_Type == 4) {
                    name ="级配下限";

                }
                if(asphaltGradDataAnalysisList[i].str_curve_Type == 5) {
                    name ="级配中值";
                }
                str += "[{name: '"+name+"',type:'line',"
                    +"data:[" +asphaltGradDataAnalysisList[i].d_a0_075+","
                    +asphaltGradDataAnalysisList[i].d_a0_15+","
                    +asphaltGradDataAnalysisList[i].d_a0_3+","
                    +asphaltGradDataAnalysisList[i].d_a0_6+","
                    +asphaltGradDataAnalysisList[i].d_a1_18+","
                    +asphaltGradDataAnalysisList[i].d_a2_36+","
                    +asphaltGradDataAnalysisList[i].d_a4_75+","
                    +asphaltGradDataAnalysisList[i].d_a9_5+","
                    +asphaltGradDataAnalysisList[i].d_a13_2+","
                    +asphaltGradDataAnalysisList[i].d_a16+","
                    +asphaltGradDataAnalysisList[i].d_a19+","
                    +asphaltGradDataAnalysisList[i].d_a26_5+","
                    +asphaltGradDataAnalysisList[i].d_a31_5+","
                    +asphaltGradDataAnalysisList[i].d_a37_5+","
                    +asphaltGradDataAnalysisList[i].d_a53+"]";
                if(i + 1 < asphaltGradDataAnalysisList.length) {
                    str += "},"
                } else {
                    str += "}]"
                }
            }

            $("#tb").html(html);
            var Series = eval('(' + str + ')')
            var jsonStr = JSON.stringify(Series);
            option.series = Series;
            var myChart = echarts.init(document.getElementById('chartmain'))
            myChart.setOption(option);
            var materialConsumption = data.materialConsumption;
            var xh = "";
            for(var i = 0; i < materialConsumption.length; i++) {
                xh += "<td>"+i+1+"</td><td>"+materialConsumption[i].Material+"</td><td>"+materialConsumption[i].MaterialConsumption+"</td>"
            }
            $("#xh").html(xh);
        }
    });

}

function getcollect_Date(i_id) {
	
	var param = {};
    param.i_cemment_Id = i_id;
    param.str_beginDate =  window.start_Time;
    param.str_endDate = window.end_Time;
	$.ajax({
	       type: "post",
	       url: "../Cement_ProductionDetailed/getCement_ProductionDetaileds.html",
	       data:param,
	       async:false,
	       dataType: "json",
	       success: function (data) {
	    	   var list = data.data;
	    	   html = "";
	    	   for(var i = 0; i < list.length; i++) {
	    	/*	   var c = parseInt(list[i].Unqualified) * 100 /parseInt(list[i].TotalCount)
	               var bfb = c.toFixed(2) +'%'
	               if (bfb=="NaN%"){
	                   bfb ="0%"
	               }*/
	    		   html += "<tr>"
	    			   +"<td>"+list[i].serialNumber+"</td>"
	    			   +"<td>"+list[i].str_materialInfo+"</td>"
	    			   +"<td>"+list[i].d_cons_Prop_Weight+"</td>"
	    			   +"<td>"+list[i].d_collection_Weight+"</td>"
	    			   +"<td>"+list[i].d_actual_Proportion+"</td>"
	    			   +"<tr>";
	    	   }
	    	   $("#cetb").html(html);
	       }
	 });
	
   /* $("#cemdtel").DataTable({
        "paging": false,
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

        },

        "ajax" : {
            type: "post",
            url: "../Cement_ProductionDetailed/getCement_ProductionDetaileds.html",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_cemment_Id = i_id;
                param.str_beginDate =$("#start_Time").val();
                param.str_endDate = $("#end_Time").val();

                return param;//自定义需要传递的参数。
            }},
        "deferRender": true,
        "createdRow": function (row, data, dataIndex) {

            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columns": [{
            "data": "serialNumber"
        }, {
            "data": "str_materialInfo"
        }, {
            "data": "d_cons_Prop_Weight"
        }, {
            "data": "d_collection_Weight"
        }, {
            "data": "d_actual_Proportion"
        }],

        "columnDefs": [{
            "targets": [0],
            "visible": true,
            "searchable": false
        }]
    });*/

    $("#timeModal").modal("show")



}

d.checkNode = function (id,i_parent_Id,flag,obj) {
	if(obj == true) {
		org_Ids += ","+id
	} else {
		org_Ids = org_Ids.replace(","+id,"")
	}
}
d.checkNode = function (id,i_parent_Id,flag,str,obj) {
    if(obj == true) {
        org_Ids += ","+id;
        var html = "<span class='s'>"+str+"</span>";
		$("#station").append(html);
    } else {
        org_Ids = org_Ids.replace(","+id,"");
        $(".s").each(function() {
			if ($(this).text() == str) {
				$(this).remove();
			}
		});
    }

}
function goto(url) {
    window.location.href=url+"?start_Time="+$("#start_Time").val()+"&end_Time="+$("#end_Time").val()+"&org_Ids="+org_Ids
}