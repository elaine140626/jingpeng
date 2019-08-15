$(function () {

    console.log(2222222222)
    getList();


})

function getList() {

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
                param.os = "3,6";
                param.str_beginDate = $("#str_beginDate").val();
                param.str_endDate = $("#str_endDate").val();

                return param;//自定义需要传递的参数。
            }},
        "deferRender": true,
        "createdRow": function (row, data, dataIndex) {

           var s = (data.i_analysis_Results ==1) ?  ("数据正常") :(data.i_analysis_Results == 2 ? "拌合时间不合格":"配比不合格")
            $('td:eq(21)', row).html(s)

            if(data.i_analysis_Results_Aggregate != 1){
                $('td:eq(9)', row).attr("class","warn2")
                $('td:eq(10)', row).attr("class","warn2")
                $('td:eq(11)', row).attr("class","warn2")
                $('td:eq(12)', row).attr("class","warn2")
                $('td:eq(13)', row).attr("class","warn2")
                $('td:eq(14)', row).attr("class","warn2")
            }
            if(data.i_analysis_Results_Cement !=1 ){
                $('td:eq(5)', row).attr("class","warn2")
                $('td:eq(6)', row).attr("class","warn2")
                $('td:eq(7)', row).attr("class","warn2")
                $('td:eq(8)', row).attr("class","warn2")

            }


            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columns": [{
            "data": "serialNumber"
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

}
function getcollect_Date(i_id) {
    alert(111)
    $("#cemdtel").DataTable({
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

        },

        "ajax" : {
            type: "post",
            url: "../Cement_ProductionDetailed/getCement_ProductionDetaileds.html",
            dataSrc: "data",
            data: function (d) {
                var param = {};
                param.i_cemment_Id = i_id;
                param.str_beginDate = $("#str_beginDate").val();
                param.str_endDate = $("#str_endDate").val();

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
    });

    $("#loginModal").modal("show")


    
}