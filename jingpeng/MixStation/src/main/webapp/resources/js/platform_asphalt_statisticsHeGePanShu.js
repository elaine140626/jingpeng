

$(function () {

    var afterUrl =  window.location.search.substring(1);

    var start_Time = "";
	var end_Time = "";
	if(afterUrl != "") {
		var a =  afterUrl.toString().split("&");
		var start_Time = a[0].substring(11);
	    var end_Time = a[1].substring(9);
	    var org_id = a[2].substring(7)
	}
	

    if(start_Time == null || start_Time == ''){
        var start_Time = new Date().getFullYear() + "-" + ((new Date().getMonth()) < 10 ? '0' + (new Date().getMonth()) : '' + (new Date().getMonth() + 1)) + "-" + new Date().getDate()+" "+"00:"+"00:"+"00"
    }else{
    	start_Time = start_Time+" "+"00:"+"00:"+"00"
    }
    if(end_Time == null || end_Time == '') {
        var end_Time = new Date().getFullYear() + '-' + ((new Date().getMonth() + 1) < 10 ? '0' + (new Date().getMonth() + 1) : '' + (new Date().getMonth() + 1)) + '-' + new Date().getDate()+" "+"23:"+"59:"+"59";
    }else {
    	end_Time = end_Time+" "+"23:"+"59:"+"59"
    }
     window.start_Time=  start_Time
    window.end_Time = end_Time

    getList(org_id,end_Time,start_Time);

    $.ajax({
        type: "post",
        url: "../cemAndr/getValue.html",
        data:{},
        async:false,
        dataType: "json",
        success: function (data) {
            $(".userid").html(data.userName)
            /*if (data.start_Time == "null"){
                $("#start_Time").val("");
            } else {
                $("#start_Time").val(data.start_Time);
            }
            if (data.end_Time == "null"){
                $("#end_Time").val("");
            } else {
                $("#end_Time").val(data.end_Time);
            }*/
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
            }
        }
    });



})
function getList(org_id,end_Time,start_Time) {

    $("#cement_ProductionDatas").DataTable(
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
                "ajax" : {
                    type: "post",
                    url: "../AsphaltProductionData/getAsphaltProductionData.html",
                    dataSrc: "data",
                    data: function (d) {
                        var param = {};
                        param.os = ","+org_id;
                        param.tag = "1"
                        param.str_analysis_Result="1";
                        param.str_start_Time =start_Time;
                        param.str_end_Time = end_Time;
                        
                        return param;//自定义需要传递的参数。
                    }
                },
                "deferRender" : true,
                "columns" : [/*{
    	            orderable: false,
    	            className: 'select-checkbox',
    	            targets:   0
    	            },*/
                    {"data" : "serialNumber"},
                    {
                        "data" : "str_org_name"
                    },
                    {"data" : "str_collect_Time"},
                    {"data" : "str_construction_Unit"},
                    {"data" : "str_proj_Pos"},
                    {"data" : "str_equipment_Name"},
                    {"data" : "str_productInfo"},
                    {"data" : "str_proportion_Code"},
                    {"data" : "str_grade_Code"},
                    {"data" : "d_temperature_Meter"},
                    {"data" : "d_total_Weight"},
                    {"data" : "d_no1_MeterValue"},
                    {"data" : "d_no2_MeterValue"},
                    {"data" : "d_no3_MeterValue"},
                    {"data" : "d_no4_MeterValue"},
                    {"data" : "d_no5_MeterValue"},
                    {"data" : "d_no6_MeterValue"},
                    {"data" : "d_coldP_MeterValue"},
                    {"data" : "d_hotP_MeterValue"},
                    {"data" : "d_asphalt_Meter"},
                    {"data" : "d_admixture1"},
                    {"data" : "d_admixture2"},
                    {"data" : "str_analysis_Result"},
                ],
                "createdRow" : function(row, data, dataIndex) {
                    $(".userid").val()
                    //$(row).children("td").eq(1).attr("onclick", "getDetailed('" + data.i_id + "');");
                    //$(row).children("td").eq(0).addClass("scgl_xz");
                    $(row).children("td").eq(2).addClass("scsj_xh");
                    $(row).children("td").eq(3).addClass("sgdw_count");
                    $(row).children("td").eq(4).addClass("gcbw_100");
                    $(row).children("td").eq(5).addClass("gcbw_100");
                    $(row).children("td").eq(6).addClass("cpmc_140");
                   /* if(data.no1 == 1) {
                        $(row).children("td").eq(10).addClass("warn2");
                    }
                    if(data.no2 == 1) {
                        $(row).children("td").eq(11).addClass("warn2");
                    }
                    if(data.no3 == 1) {
                        $(row).children("td").eq(12).addClass("warn2");
                    }
                    if(data.no4 == 1) {
                        $(row).children("td").eq(13).addClass("warn2");
                    }
                    if(data.no5 == 1) {
                        $(row).children("td").eq(14).addClass("warn2");
                    }
                    if(data.no6 == 1) {
                        $(row).children("td").eq(15).addClass("warn2");
                    }
                    if(data.hotPower == 1) {
                        $(row).children("td").eq(16).addClass("warn2");
                    }
                    if(data.coldPower == 1) {
                        $(row).children("td").eq(17).addClass("warn2");
                    }
                    if(data.asphalt == 1) {
                        $(row).children("td").eq(18).addClass("warn2");
                    }
                    if(data.admixture1 == 1) {
                        $(row).children("td").eq(19).addClass("warn2");
                    }
                    if(data.admixture2 == 1) {
                        $(row).children("td").eq(20).addClass("warn2");
                    }*/
                    $(row).children("td").eq(10).text(Number(data.d_total_Weight).toFixed(2));
                    $(row).children("td").eq(11).text(Number(data.d_no1_MeterValue).toFixed(2));
                    $(row).children("td").eq(12).text(Number(data.d_no2_MeterValue).toFixed(2));
                    $(row).children("td").eq(13).text(Number(data.d_no3_MeterValue).toFixed(2));
                    $(row).children("td").eq(14).text(Number(data.d_no4_MeterValue).toFixed(2));
                    $(row).children("td").eq(15).text(Number(data.d_no5_MeterValue).toFixed(2));
                    $(row).children("td").eq(16).text(Number(data.d_no6_MeterValue).toFixed(2));
                    $(row).children("td").eq(17).text(Number(data.d_totalP_MeterValue).toFixed(2));
                    $(row).children("td").eq(18).text(Number(data.hotPower).toFixed(2));
                },
                "columnDefs" : [ {
                    "targets" : [ 0 ],
                    "visible" : true,
                    "searchable" : false
                } ]
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
        url :  "../AsphaltProductionData/getAsphaltPropDataAnalysis.html",
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
                str += "{name: '"+name+"',type:'line',"
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
            option.series = Series;
            var myChart = echarts.init(document.getElementById('chartmain'))
            myChart.setOption(option);
            var materialConsumption = data.materialConsumption;
            var xh = "";
            for(var i = 0; i < materialConsumption.length; i++) {
                xh += "<tr><td>"+i+1+"</td><td>"+materialConsumption[i].Material+"</td><td>"+materialConsumption[i].MaterialConsumption+"</td></tr>"
            }
            $("#xh").html(xh);
        }
    });
}
