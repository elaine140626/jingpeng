$(function () {


    getList();

    $("#btnchar").on("click",function () {
        $.ajax({
            type: "post",
            contentType: 'application/json;charset=UTF-8',
            url: "../Andr/getAsphalt_production_statisticsechar",
            data:JSON.stringify({"os":"3,12,17","start_Time":$("#start_Time").val(),"end_Time":$("#end_Time").val()}),
            dataType: "json",
            success: function (data) {
                console.log(data)

            }
        });

    })




    
})

function detail(id) {
    $("#loginModal").modal("show")
    getList2(id);
    
}



function getList2(id) {

    $('#statisticspage').DataTable({
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
            url: "../Andr/getAsphalt_production_statisticspage",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.os = id;
                param.start_Time = $("#start_Time").val();
                param.end_Time = $("#end_Time").val();

                return param;//自定义需要传递的参数。
            }},
        "deferRender" : true,
        "columns" : [ {
            "data" : "serialNumber"
        }, {
            "data" : "day"
        }, {
            "data" : "str_productInfo"
        }, {
            "data" : "totalCount"
        }, {
            "data" : "heGePanShu"
        }, {
            "data" : "unqualified"
        }, {
            "data" : "total_Asphalt"
        }, {
            "data" : "total_Weight"
        } ],
        "createdRow" : function(row, data, dataIndex) {

            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });

}
function getList() {

    $('#lqstatisticslist').DataTable({
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
            url: "../Andr/getAsphalt_production_statisticslist",
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
        }, {
            "data" : "zongChanLiang"
        }, {
            "data" : "lqyongLiang"
        }, {
            "data" : "totalCount"
        }, {
            "data" : "heGePanShu"
        }, {
            "data" : "buHeGePanShu"
        }, {
            "data" : "hegelv"
        }, {
            "data" : "details"
        } ],
        "createdRow" : function(row, data, dataIndex) {

            var c = parseInt(data.heGePanShu) * 100 /parseInt(data.totalCount)
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

}
