$(function () {

    window.i_org_id =3
    window.consProp = $("#consProp")
    window.str_material_Mold  = $("#str_material_Mold")
    window.str_material_Name =$("#str_material_Name")

    window.arrayname = []
    window.arraymodle =[]
var bunk = {
        arrayname :[],
        arraymodle:[]
}
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

    $('#consProp').blur(function() {

        $.ajax({
            type: "post",
            url: "../CementConstructionProp/getC_ConstructionDeatlByid",
            data: JSON.stringify({"i_consProp_Id": $("#consProp").val()}),
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {



                $.each(data,function (i,v) {

                    window.arrayname.push(v.str_Material_Name)

                    window.arraymodle.push(v.str_Material_Model)

                })
            }
        });
    })


    $('#bunk').DataTable({
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
        "ajax" : "../Bunker_Correspondence/getBunker_Correspondences?i_org_Id="+3,
        "deferRender" : true,
        "columns" : [{
            "data" : "serialNumber"
        }, {
            "data" : "str_prop_Code"
        }, {
            "data" : "str_bunker_Code"
        }, {
            "data" : "str_Material"
        }, {
            "data" : "str_operator"
        }, {
            "data" : "str_create_Date"
        }],
        "createdRow" : function(row, data, dataIndex) {

            $(row).children('td').eq(1).attr('style', 'text-align: center;')
        },
        "columnDefs" : [ {
            "targets" : [ 0 ],
            "visible" : true,
            "searchable" : false
        } ]
    });

    $('#Bunker_code').blur(function(){
        $.ajax({
            type: "post",
            async: false,
            url: "../Bunker_Correspondence/getBunker_CorrespondenceByCode",
            dataType: "json",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify({"str_bunker_Code":$("#Bunker_code").val(),"i_org_Id":3}),
            success: function (data) {
                if(data.code == 300){
                    $("#str_bunker_Code").attr("cz","cz");
                    alert(data.message)
                }else{
                    $("#str_bunker_Code").attr("cz","bcz");
                }
            }
        });
    });

//查询设备
    $.ajax({
        type: "post",
        contentType: 'application/json;charset=UTF-8',
        url: "../Common/getEquipmentInfo.html",
        data:JSON.stringify({"i_equipment_Type":1,"i_org_Id":6}),
        dataType: "json",
        success: function (data) {
            $("#bEquipment_Name").empty()
            $.each(data,function (i,v) {
                $('<option></option>').attr("value", v.i_id).html(v.str_equipment_Name).appendTo($("#bEquipment_Name"));
            })


        }
    });

    $("#btnbunk").on("click",function () {

        options ={

                "i_org_Id":3,
                "str_bunker_Code":$("#Bunker_code").val(),

                "i_consProp_Id":$("#consProp").val(),
                "str_remarks":$("#str_remarks").val(),
                "i_mateId_Cement1":$("#str_material_Mold1").attr("prid"),
                "i_mateId_Cement2":$("#str_material_Mold2").attr("prid"),
                "i_mateId_Cement3":$("#str_material_Mold3").attr("prid"),
                "i_mateId_Cement4":$("#str_material_Mold4").attr("prid"),
                "i_mateId_Aggregate1":$("#str_material_Mold5").attr("prid"),
                "i_mateId_Aggregate2":$("#str_material_Mold6").attr("prid"),
                "i_mateId_Aggregate3":$("#str_material_Mold7").attr("prid"),
                "i_mateId_Aggregate4":$("#str_material_Mold8").attr("prid"),
                "i_mateId_Aggregate5":$("#str_material_Mold9").attr("prid"),
                "i_mateId_Aggregate6":$("#str_material_Mold10").attr("prid"),
                "i_mateId_Water":$("#str_material_Mold11").attr("prid"),
                "i_mateId_Admixture1":$("#str_material_Mold12").attr("prid"),
                "i_mateId_Admixture2":$("#str_material_Mold13").attr("prid"),
                "i_org_Id":3,
                "str_operator":"zz",

            "i_valid_Flag":1


        }
        console.log(options)

        $.ajax({
            type: "post",
            url: "../Bunker_Correspondence/addBunker_Correspondence",
            data: JSON.stringify(options),
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {

            }

        });

    })

})

function getMaterialModels(obj) {


    var aterial_modelid = obj.id
    var materialmodelser = (obj.id).toString().substring(17)

    $.ajax({
        type : "POST",
        url :"../Common/getMaterialModels.html",
        contentType : 'application/json;charset=UTF-8',
        data : JSON.stringify({"str_material_Type":1,
            "str_material_Mold":1,
            "str_material_Name":$("#str_material_Name"+materialmodelser).val()}),
        dataType : "json",
        success : function(data) {
            $("#" + aterial_modelid).empty();
            $.each(data, function (i, v) {
                $('<option></option>').attr("value", v.i_id).html(v.str_material_Model).appendTo($("#" + aterial_modelid));

            })

        }
    });
}


function getMaterialModelss(obj) {


    var aterial_modelid = obj.id
    var materialmodelser = (obj.id).toString().substring(17)

    $("#" + aterial_modelid).empty()
    $.each(window.arraymodle,function (i,v) {

        $('<option></option>').html(v).appendTo($("#" + aterial_modelid));
    })


}

function  getproid(obj) {
    var aterial_modelid = obj.id
    var materialmodelser = (obj.id).toString().substring(17)

    console.log($("#str_material_Name"+materialmodelser).val())
    console.log($("#"+aterial_modelid).val())

    $.ajax({
        type : "POST",
        url :"../Common/getMaterialModelIdbyNameAndCode",
        contentType : 'application/json;charset=UTF-8',
        data : JSON.stringify({"str_material_Model":$("#"+aterial_modelid).val(), "str_material_Name":$("#str_material_Name"+materialmodelser).val(), "i_org_Id":3}),
        dataType : "json",
        success : function(data) {

            console.log(data)

            if(data.str_material_Model == $("#"+aterial_modelid).val()){
                $("#" + aterial_modelid).attr("prid",data.i_id)
                if($("#" + aterial_modelid).attr("prid") == 0){
                    alert("不存在")
                }

            }
        }
    });

}

function getMaterialNames() {

    $.ajax({
        type: "post",
        async: false,
        contentType: 'application/json;charset=UTF-8',
        url: "../Common/getMaterialNames.html",
        data: JSON.stringify({"str_material_Type":1,"str_material_Mold":1,"i_org_Id":window.i_org_id}),
        dataType: "json",
        success: function (data) {
            $("#str_material_Name").empty()
            $.each(data, function (i, v) {

                $('<option></option>').attr("value", v.str_material_Name).html(v.str_material_Name).appendTo($("#str_material_Name"));


            })
        }
    });
    
}


function getMaterialnamedelt(obj){

    var aterial_modelid = obj.id
    var materialmodelser = (obj.id).toString().substring(17)

    console.log(materialmodelser)
    $("#" + aterial_modelid).empty()
    $.each(window.arrayname,function (i,v) {

        $('<option></option>').attr("value", v).html(v).appendTo($("#" + aterial_modelid));
    })


}


function getconsProp() {


    $.ajax({
        type: "post",
        url: "../CementConstructionProp/getCementConstructionPropbypid",
        data: JSON.stringify({"i_org_Id":3,"i_product_Id":$("#str_material_Mold").val()}),
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: function (data) {

            $("#consProp").empty();
            $.each(data, function (i, v) {
                $('<option></option>').attr("value", v.i_id).html(v.str_prop_Code).appendTo($("#consProp"));

            })
        }
    });
    
}
