$(function () {
    function getList() {
        console.log(11111)
        $("#Statistics").DataTable({
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
            "ajax" : "../cement_Production/getcement_ProductionStatistics",
            "deferRender" : true,
            "columns" : [ {
                "data" : "serialNumber"
            }, {
                "data" : "str_collect_Date"
            }, {
                "data" : "str_product"
            }, {
                "data" : "str_construction_Unit"
            }, {
                "data" : "str_proj_Pos"
            }, {
                "data" : "i_total_number"
            }, {
                "data" : "i_qualified_number"
            }, {
                "data" : "i_failures"
            },{
                "data" : "cementWeight"
            },{
                "data" : "d_total_Weight"
            } ],
            "createdRow" : function(row, data, dataIndex) {
                console.log(data)
                var tol = data.d_weight_Cement1 + data.d_weight_Cement2 + data.d_weight_Cement3 +data.d_weight_Cement4
                console.log(tol)
                $('td:eq(8)', row).html(tol)
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
    getList();


    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));




    option = {
        title : {
            text: '某地区蒸发量和降水量',
            subtext: '纯属虚构'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['蒸发量','降水量']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['总盘数','合格盘数','不合格盘数']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'总盘数',
                type:'bar',
                data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            },
            {
                name:'合格盘数',
                type:'bar',
                data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
                markPoint : {
                    data : [
                        {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
                        {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            },
            {
                name:'不合格盘数',
                type:'bar',
                data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
                markPoint : {
                    data : [
                        {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
                        {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ]
    };














    myChart.setOption(option);

})