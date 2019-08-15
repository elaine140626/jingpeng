$(function () {
    getList();

    $("#btnlqchar").on("click",function () {
        $.ajax({
            type: "post",
            contentType: 'application/json;charset=UTF-8',
            url: "../Andr/getAsphalt_production_statisticsechar",
            data:JSON.stringify({"os":"3,12,17","start_Time":$("#start_Time").val(),"end_Time":$("#end_Time").val()}),
            dataType: "json",
            success: function (data) {
            }
        });
    })
})
function getList() {

    $('#Asphalt_warning').DataTable({
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
            url: "../Andr/getAsphalt_warning",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.os = "3,17,12";

              /*  param.start_Time = $("#start_Time").val();
                param.end_Time = $("#end_Time").val();
*/
                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "org_Name"
        }, {
            "data" : "equipment_Name"
        }, {
            "data" : "str_TotalCount"
        }, {
            "data" : "str_Unqualified"
        }, {
            "data" : "glunqualified"
        }, {
            "data" : "flunqualified"
        }, {
            "data" : "lqunqualified"
        }, {
            "data" : "wcjunqualified"
        },{
            "data" : "jpunqualified"
        },{
            "data" : "unqualifiedLV"
        },{"data":"details"}
        ],
        "createdRow" : function(row, data, dataIndex) {
            var c = parseInt(data.unqualified) * 100 /parseInt(data.totalCount)
           var bfb = c.toFixed(2) +'%'


            $('td:eq(10)', row).html(bfb)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });

}

function getTotalCount(org_id) {

    window.open("../Andr/lq_4_1.html")

}

function getUnqualified(org_id) {


    window.open("../Andr/lq_4_2.html?org_id="+org_id+'&'+'start_Time='+$("#start_Time").val()+'&'+'end_Time='+$("#end_Time").val())

}

function detail(id) {
    $("#loginModal").modal("show")
    yjmx(id);
    
}
function yjmx() {

    $('#yjmx').DataTable({
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
            url: "../Andr/getAsphalt_warning",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.os = "3,17,12";

                /*  param.start_Time = $("#start_Time").val();
                  param.end_Time = $("#end_Time").val();
  */
                return param;//自定义需要传递的参数。
            }

        },
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "collect_Date"
        }, {
            "data" : "org_Name"
        }, {
            "data" : "equipment_Name"
        }, {
            "data" : "totalCount"
        }, {
            "data" : "unqualified"
        }, {
            "data": "glunqualified"
        },{
            "data" : "flunqualified"
        }, {
            "data" : "lqunqualified"
        }, {
            "data" : "wcjunqualified"
        },{
            "data" : "jpunqualified"
        },{
            "data" : "unqualifiedLV"
        }
        ],
        "createdRow" : function(row, data, dataIndex) {
            var c = parseInt(data.unqualified) * 100 /parseInt(data.totalCount)
            var bfb = c.toFixed(2) +'%'


            $('td:eq(11)', row).html(bfb)
            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });
    
}