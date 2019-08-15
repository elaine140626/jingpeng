$(function () {

    function getList() {

        $('#Equipmentlist').DataTable({
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
            "ajax" : "../Equipment/getEquipments",
            "deferRender" : true,
            "columns" : [ {
                "data" : "serialNumber"
            }, {
                "data" : "str_org_name"
            }, {
                "data" : "i_upload_Module_Id"
            }, {
                "data" : "str_equipment_Code"
            }, {
                "data" : "i_equipment_Type"
            }, {
                "data" : "str_equipment_No"
            }, {
                "data" : "str_equipment_Name"
            }, {
                "data" : "str_equipment_Model"
            },{
                "data" : "str_vendor"
            },{
                "data" : "str_remarks"
            }, {
                "data" : "str_operator"
            },{
                "data" : "str_create_Date"
            },{
                "data" : "operate"
            } ],
            "createdRow" : function(row, data, dataIndex) {

                data.i_equipment_Type == 0 ? ($('td:eq(4)', row).html('<p>沥青拌和机</p>')):(data.i_equipment_Type == 1 ?( $('td:eq(4)', row).html('<p>水泥混凝土拌和机</p>')):'')
                $(row).children('td').eq(1).attr('style', 'text-align: center;')
            },
            "columnDefs" : [ {
                "targets" : [ 0 ],
                "visible" : true,
                "searchable" : false
            } ]
        });

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
    var Equipment = {
        saveBtn: $("#login_close").on("click", saveData),
        editFlg:false,
        items: {}
    };
    window.i_id=0;
    window.editFlg = false;
    window.Org_id = $("#Org_Name");
    window.Upload_Module_ID = $("#Upload_Module_ID");
    window.Equipment_Code = $("#Equipment_Code");
    window.Equipment_Type = $("#Equipment_Type");
    window.Equipment_No = $("#Equipment_No");
    window.Equipment_Name = $("#Equipment_Name");
    window.Equipment_Model = $("#Equipment_Model");
    window.Vendor = $("#Vendor");
    window.Remarks = $("#Remarks");


    getList();
    $.ajax({
        type: "post",
        async: false,
        url: "../getOrganization_Infos",
        dataType: "json",
        success: function (data) {
            $('<option></option>').attr("value","all").html("全部").appendTo($("#fEquipment_Name"));

            $.each(data, function (i, v) {
                $('<option></option>').attr("value", v.i_id).html(v.str_org_Name).appendTo($("#Org_Name"));
                $('<option></option>').attr("value", v.i_id).html(v.str_org_Name).appendTo($("#uOrg_Name"));
                $('<option></option>').attr("value", v.i_id).html(v.str_org_Name).appendTo($("#fEquipment_Name"))
        })
        }
    });
    $.ajax({
        type: "post",
        async: false,
        url: "../Equipment/getEquipments",
        data: {},
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, v) {
                $('<option></option>').attr("value", v.str_equipment_Code).html(v.str_equipment_Code).appendTo($("#Equipment_Code"));
            })
        }
    });
    //模糊查询按钮
    $("#findEquipment").on("click", function () {

        options = {
            "str_equipment_No":$("#fEquipmentNo").val(),
            "i_equipment_Type":$("#fEquipmenttype").val(),
            "i_org_Id":$("#fEquipment_Name").val()
        }
        if ($("#fEquipmenttype").val() == "all" ){
            options["i_equipment_Type"] = ''
        }
        if ($("#fEquipment_Name").val() == "all" ){
            options["i_org_Id"] = ''
        }

        $.ajax({
            type: "post",
            async: false,
            url: "../Equipment/getEquipments",
            data: JSON.stringify(options),
            dataType: "json",
            success: function (data) {
                console.log(data);
            }
        });
    })

    $('#Equipment_Code').blur(function(){
        $.ajax({
            type: "post",
            async: false,
            url: "../Equipment/getEquipmentbyCode",
            dataType: "json",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify({"str_equipment_Code":window.Equipment_Code.val()}),
            success: function (data) {
                if(data.code == 300){
                    window.Equipment_Code.attr("cz","cz");
                    alert(data.message)
                }else{
                    window.Equipment_Code.attr("cz","bcz");
                }
            }
        });
    });

        $('#Equipment_No').blur(function(){
            $.ajax({
                type: "post",
                async: false,
                url: "../Equipment/getEquipmentbyNo",
                dataType: "json",
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify({
                    "str_equipment_No": window.Equipment_No.val(),
                    "i_org_Id": 6
                }),
                success: function (data) {
                    if(data.code == 300){
                        window.Equipment_No.attr("cz","cz");
                        alert(data.message)
                    }else{
                        window.Equipment_No.attr("cz","bcz");
                    }
                }
            })
        });


    function saveData () {
        var reg = /^[1-9]\d*$/;
        options = {

            "i_org_Id":  window.Org_id.val(),
            "i_upload_Module_Id":  window.Upload_Module_ID.val(),
            "str_equipment_Code":  window.Equipment_Code.val(),
            "i_equipment_Type": window.Equipment_Type.val(),
            "str_equipment_No":  window.Equipment_No.val(),
            "str_equipment_Name":  window.Equipment_Name.val(),
            "str_equipment_Model":  window.Equipment_Model.val(),
            "str_remarks":  window.Remarks.val(),
            "str_vendor": window.Vendor.val(),
            "i_upload": 1,
            "i_valid_Flag": 1

        };

        if (window.editFlg == false) {
            //添加
            options["str_operator"] = "朱昭霖";
            options["str_create_Date"] = CurentTime();
            url = '../Equipment/addEquipment.html'

        }
        if(window.editFlg == true) {

            options["i_id"] = window.i_id;
            options["str_modifier"] = "朱昭霖改";
            options["str_modify_Date"] = CurentTime();
            url = '../Equipment/updateEquipment.html'

        }
        console.log(options)
        if ( window.Equipment_Code.attr("cz") == "bcz" && window.Equipment_No.attr("cz") == "bcz" &&  window.Equipment_Name.val() != '' &&
            window.Equipment_Name.val() != null &&  window.Equipment_Model.val() != '' &&  window.Equipment_Model.val() != null) {
            if ( window.Upload_Module_ID.val() == "" || reg.test( window.Upload_Module_ID.val())) {
                $.ajax({
                    type: "post",
                    url: url,
                    data: JSON.stringify(options),
                    contentType: 'application/json;charset=UTF-8',
                    dataType: "json",
                    success: function (data) {
                    }
                });
                location.reload();
            } else {
                alert("上传模块标识 只能添整数")
            }
            window.Equipment_Code.removeAttr("cz");
            window.Equipment_No.removeAttr("cz");
            window.Equipment_Code.val('');
            window.Upload_Module_ID.val('');
            window.Equipment_No.val('');
            window.Equipment_Name.val('');
            window.Equipment_Model.val('');
            window.Vendor.val('');
            window.Remarks.val('')
        } else {
            return false
        }
    }


});

//外面
function del(i_id) {
    $.ajax({
        type : "POST",
        url : "../Equipment/deleteEquipment",
        data : {i_id},
        dataType : "json",
        success : function(data) {
            alert(data.message);
            location.reload();
        }
    });
}
//修改ht
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
        url: "../Equipment/getEquipmentById",
        data: JSON.stringify(options),
        dataType: "json",
        success: function (data) {

            window.Org_id.val(data.i_org_Id);
            window.Upload_Module_ID .val(data.i_upload_Module_Id);
            window.Equipment_Code.val(data.str_equipment_Code);
            window.Equipment_Type.val(data.i_equipment_Type.toString());
            window.Equipment_No.val(data.str_equipment_No);
            window.Equipment_Name.val(data.str_equipment_Name);
            window.Equipment_Model.val(data.str_equipment_Model);
            window.Vendor.val(data.str_vendor);
            window.Remarks.val(data.str_remarks)
        }
    });
    $("#loginModal").modal("show");

};



