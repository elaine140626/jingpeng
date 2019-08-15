$(function () {
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

    cementProductionPlan.item.i_org_id= 3
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
            "ajax": "../cementProductionPlan/getCementProductionPlan?i_org_id="+cementProductionPlan.item.i_org_id,
            "deferRender": true,
            "createdRow": function (row, data, dataIndex) {

                $(row).children('td').eq(1).attr('style', 'text-align: center;')
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

    }

    getList();

  /*  //施工配比编号
    $.ajax({
        type: "post",
        async: false,
        url: "../CementConstructionProp/getCementConstructionProp",
        data: JSON.stringify({"i_org_Id":window.i_org_id}),
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: function (data) {


            $.each(data.data,function (i,v) {
                $('<option></option>').attr("value", v.i_id).html(v.str_prop_Code).appendTo($("#consProp"));
            })
        }

    });*/




    $('#str_plan_No').blur(function(){
        $.ajax({
            type: "post",
            async: false,
            url: "../cementProductionPlan/getPlanNo",
            dataType: "json",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify({"str_plan_No":window.str_plan_No.val(),"i_org_id":window.i_org_id}),
            success: function (data) {
                if(data.code == 300){
                    window.str_plan_No.attr("cz","cz");
                    alert(data.message)
                }else{
                    window.str_plan_No.attr("cz","bcz");
                }
            }
        });
    });
    //光标移除产品名称
    $('#str_material_Name').blur(function(){
        $.ajax({
            type: "post",
            async: false,
            url: "../Common/getMaterialModel.html",
            dataType: "json",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify({"str_material_Type":1,"i_org_id":3,"str_material_Name":window.str_material_Name.val(),"str_material_Mold":1}),
            success: function (data) {

                var html = "";
                $("#str_material_Mold").empty();

                $.each(data, function (i, v) {
                   $('<option></option>').attr("value", v.i_id).html(v.str_material_Model).appendTo($("#str_material_Mold"));

                })
            }
        });
    });

    $('#str_material_Mold').blur(function(){

        $.ajax({
            type: "post",
            async: false,
            url: "../CementConstructionProp/getCementConstructionPropbypid",
            data: JSON.stringify({"i_org_Id":3,"i_product_Id":window.str_material_Mold.val()}),
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {

                $("#consProp").empty();
                $.each(data, function (i, v) {
                    $('<option></option>').attr("value", v.i_id).html(v.str_prop_Code).appendTo($("#consProp"));
                })
            }

        });



    });


        /*$.ajax({
            type: "post",
            async: false,
            url: "../Common/getMaterialName.html",
            dataType: "json",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify({"str_material_Type":1,"i_org_id":window.i_org_id,"str_material_Mold":window.str_material_Mold,"str_material_Name":window.str_material_Name}),
            success: function (data) {
                $.each(data, function (i, v) {


                    $('<option></option>').attr("value", v.i_mateName_id).html(v.str_material_Model).appendTo($("#Material_Model"));

                })

            }
        });*/


//光标移除 配比
    $('#consProp').blur(function(){
        $.ajax({
            type: "post",
            async: false,
            contentType: 'application/json;charset=UTF-8',
            url: "../Bunker_Correspondence/getBunker_Correspondences",
            data: JSON.stringify({"i_org_Id":3,"i_consProp_Id": window.consProp.val()}),
            dataType: "json",
            success: function (data) {
                $("#i_bunkerCor").empty();
                $.each(data.data, function (i, v) {
                    $('<option></option>').attr("value", v.i_id).html(v.str_bunker_Code).appendTo($("#i_bunkerCor"));

                })
            }
        });
    });

    $.ajax({
        type: "post",
        async: false,
        url: "../Common/getEquipmentInfo.html",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({"i_org_Id":3,"i_equipment_Type":1}),
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, v) {
                $('<option></option>').attr("value", v.i_id).html(v.str_equipment_Name).appendTo($("#i_equ_Id"));

            })
        }
    });




    //选择产品名称
    $.ajax({
        type: "post",
        async: false,
        contentType: 'application/json;charset=UTF-8',
        url: "../Common/getMaterialName.html",
        data: JSON.stringify({"str_material_Type":1,"str_material_Mold":1,"i_org_Id":window.i_org_id}),
        dataType: "json",
        success: function (data) {

            $.each(data, function (i, v) {

                $('<option></option>').attr("value", v.str_material_Name).html(v.str_material_Name).appendTo($("#str_material_Name"));

            })
        }
    });




    function saveData () {
        var reg = /^[1-9]\d*$/;
        options = {


            "i_org_id":3,
            "str_plan_No":    window.str_plan_No.val(),
            "i_product_Id":  window.str_material_Mold.val(),
            "i_consProp_Id": window.consProp.val(),
            "i_bunkerCor_Id":  window.i_bunkerCor.val(),
            "str_proj_Pos":  window.str_proj_Pos.val(),
            "i_isCancel":0,
            "i_equ_Id":window.i_equ_Id.val(),
            "str_construction_Unit":  window.str_construction_Unit.val(),
            "str_Watering_Site":  window.str_Watering_Site.val(),
            "d_scheduled_Production": window.d_scheduled_Production.val(),
            "i_valid_Flag": 1,
            "str_startTime":window.str_startTime.val()

        };

        if (window.editFlg == false) {
            //添加
            options["str_operator"] = "朱昭霖";

            options["str_create_Date"] = CurentTime();
            url = '../cementProductionPlan/addCementProductionPlan'

        }
        if(window.editFlg == true) {
            console.log(111)
            options["i_id"] = window.i_id;
            options["str_modifier"] = "朱昭霖改";
            options["str_modify_Date"] = CurentTime();
            $.ajax({
                type: "post",
                url: '../cementProductionPlan/updateCementProductionPlan',
                data: JSON.stringify(options),
                contentType: 'application/json;charset=UTF-8',
                dataType: "json",
                success: function (data) {
                    console.log(data)
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



})


function upt(i_id) {
    window.i_id= i_id
    $("#loginModal3").modal("show")

}

function update(i_id) {



    window.editFlg =true;
    window.i_id =i_id;
    options={
        "i_id":i_id
    };
    $.ajax({
        type: "post",
        async: false,
        contentType : 'application/json;charset=UTF-8',
        url: "../cementProductionPlan/getCementProductionPlanById",
        data: JSON.stringify(options),
        dataType: "json",
        success: function (data) {

            console.log(data)

            window.str_plan_No.val(data.str_plan_No);
            window.str_plan_No.attr("readonly","true")
            window.i_equ_Id.val(data.i_equ_Id);
            window.str_material_Name.val(data.str_material_Name);
            window.str_material_Mold.val(data.str_material_Mold);
            window.consProp.val(data.consProp);
            window.i_bunkerCor.val(data.i_bunkerCor);
            window.str_proj_Pos.val(data.str_proj_Pos);
            window.str_construction_Unit.val(data.str_construction_Unit)
            window.str_Watering_Site.val(data.str_Watering_Site);
            window.d_scheduled_Production.val(data.d_scheduled_Production)
            window.str_startTime.val(data.str_startTime)
        }
    });



    $("#loginModal").modal("show")

}

function del(i_id){
    $.ajax({
        type: "post",
        async: false,
        contentType: 'application/json;charset=UTF-8',
        url: "../cementProductionPlan/delCementProductionPlan",
        data: JSON.stringify({"i_id": i_id}),
        dataType: "json",
        success: function (data) {
            location.reload();
        }
    });


}
$("#zfbutton").on("click",function () {


    $.ajax({
        type: "post",
        async: false,
        contentType: 'application/json;charset=UTF-8',
        url: "../cementProductionPlan/zfProductionPlan",
        data: JSON.stringify({"i_id": window.i_id,"str_cancel_Reason":window.textarea.val(),"str_invalid_Person":'朱昭霖',"str_cancel_Time":CurentTime}),
        dataType: "json",
        success: function (data) {
            location.reload();
        }
    });
    
})

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