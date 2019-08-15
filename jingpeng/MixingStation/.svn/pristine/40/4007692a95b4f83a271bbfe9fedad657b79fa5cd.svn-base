$(function () {
    getList();





})

function getList() {

    $('#ycl').DataTable({

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
            "oPaginate": {
                "sFirst": "第一页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "最后一页"
            },
        },
        "ajax" : {

            type: "post",
            url: "../Andr/getmaterial_consumption",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.os = "3,17,12";
                param.start_Time = $("#start_Time").val();
                param.end_Time = $("#end_Time").val();

                return param;//自定义需要传递的参数。
            }

            /* "url": "../Andr/getAsphalt_production_statisticslist",

             "data": {

                     "os": "3,17,12"/!*, "start_Time": $("#start_Time").val(), "end_Time": $("#end_Time").val()*!/

             }*/
        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "org_Name"
        },
            {
                "data" : "material"
            },{
            "data" : "materialConsumption"
        }, {
            "data" : "details"
        } ],
        "createdRow" : function(row, data, dataIndex) {

            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs": [{
            targets: [0,1,2], //第1，2，3列
            createdCell: function (td, cellData, rowData, row, col) {
                var rowspan = 1;
                if(col == 0){
                    rowspan = rowData.areaRowSpan;
                }
                if(col ==1){
                    rowspan = rowData.companyRowSpan;
                }
                if(col ==2){
                    rowspan = rowData.departmentRowSpan;
                }

                if (rowspan > 1) {
                    $(td).attr('rowspan', rowspan)
                }
                if (rowspan == 0) {
                    $(td).remove();
                }
            }
        }]

    });

}

function detail(id,orgname) {
    console.log(orgname)
$("#loginModal").modal("show")
    list2(id)


}

function list2(id) {
    $("#ma").DataTable({
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
            "oPaginate": {
                "sFirst": "第一页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "最后一页"
            },
        },

        "ajax" : {
            type: "post",
            url: "../Andr/getmaterial_consumption1",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.os = id;

                return param;//自定义需要传递的参数。
            }

            /* "url": "../Andr/getAsphalt_production_statisticslist",

             "data": {

                     "os": "3,17,12"/!*, "start_Time": $("#start_Time").val(), "end_Time": $("#end_Time").val()*!/

             }*/
        },
        "deferRender": true,
        "createdRow": function (row, data, dataIndex) {

            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columns": [ {
            "data" : "serialNumber"
        }, {
            "data" : "org_Name"
        }, {
            "data" : "date"
        },{
            "data" : "material"
        },{
            "data" : "materialConsumption"
        } ],

        "columnDefs": [{
            "targets": [0],
            "visible": true,
            "searchable": false
        }]
    });

}