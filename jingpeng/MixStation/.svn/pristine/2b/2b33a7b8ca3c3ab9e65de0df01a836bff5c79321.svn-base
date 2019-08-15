$(function () {
    getList();





})

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

    "ajax" : "../Andr/Deviation_Asphalt",
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

    "columnDefs": [{
        "targets": [0],
        "visible": true,
        "searchable": false
    }]
});

function addss() {
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
            "org_id":3,
            "cjr":"zhu"

        }

        $.ajax({
            type: "post",
            url: "../Andr/addDev",
            dataType: "json",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify(options),
            success: function (data) {
                alert(111)
                location.reload();
            }
        });

    
}
