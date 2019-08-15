var param = {};
var uesrId = "";
var uesrInfo = ""; //用户信息
$(function(){
	//uesrId = getUrlParam("userId");
	//uesrInfo = getUserInfo(uesrId);
	//下拉列表
	getDatalistInfo();
	//查询
	search();
})
//查询列表
function search(){
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var customerName = $("#customerName").val();
	var plateNumber = $("#plateNumber").val();
	var companyName = $("#companyName").val();
	var driverName = $("#driverName").val();
	var fleetName = $("#fleetName").val();
	var materielName = $("#materielName").val();
	var materielModel = $("#materielModel").val();
	var outType = $("#outType").val();
	param.startTime = startTime;
	param.endTime = endTime;
	param.customerName = customerName;
	param.plateNumber = plateNumber;
	param.companyName = companyName;
	param.driverName = driverName;
	param.fleetName = fleetName;
	param.materielName = materielName;
	param.materielModel = materielModel;
	param.outType = outType;
	getWeighingQueryOut(param);
}
// datatable数据初始化
function getWeighingQueryOut(param){
	var table1 = $('#table1').dataTable();
	table1.fnDestroy();
	$("#table1").DataTable({
		 "paging": true,
         "lengthChange": false,
         "pageLength": 14,
         "searching": false,
         "ordering": false,
         "info": true,
         "sInfo": true,
         "autoWidth": false,
         "iDisplayStart" : 0,
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
            url: "../../WeighingQueryOut/getWeighingQueryOut.action",
            dataSrc: "data",
            data: function (d) {
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": [
        	{"data": "id"},
        	{"data": "id",
        		render:function(data,type,row,meta) {
        			var html = '';
        			html += "<a onclick='printWeighingQueryOut("+row.id+","+row.outType+","+row.isRelation+");'style='text-decoration:none' href='javascript:;' title='打印'>打印</a>&nbsp;";
        			/*html += "<a onclick='printBaoGao("+row.id+");'style='text-decoration:none' href='javascript:;' title='报告'>报告</a>&nbsp;";*/
        			html += "<a onclick='updateWeighingQueryOut("+row.id+");'style='text-decoration:none' href='javascript:;' title='编辑'>编辑</a>&nbsp;";
        			//html += "<a onclick='deleteWeighingQueryOut("+row.id+");' style='text-decoration:none' href='javascript:;' title='删除'>删除</a>";
        			return html;
        		}},
        	{"data": "client"},
        	{"data": "dispatchDate"},
        	{"data": "plateNumber"},
        	{"data": "materielName"},
        	{"data": "materielModel"},
        	{"data": "grossWeight",
        		render:function(data,type,row,meta) {
        			if(data != 0.00){
        				return data
        			}else{
        				return ''
        			}
        		}},
        	{"data": "grossMeasureTime"},
        	{"data": "tareWeight",
        		render:function(data,type,row,meta) {
        			if(data != 0.00){
        				return data
        			}else{
        				return ''
        			}
        		}},
        	{"data": "tareMeasureTime"},
        	{"data": "suttle",
        		render:function(data,type,row,meta) {
        			if(data != 0.00){
        				return data
        			}else{
        				return ''
        			}
        		}},
        	{"data": "deliveryMan"},
        	{"data": "deliveryManPhone"},
        	{"data": "transportBalance",
        		render:function(data,type,row,meta) {
        			var html = '';
        			if(data == 1){
        				return '是'
        			}else{
        				return '否'
        			}
        		}},
        	{"data": "companyName"},
        	{"data": "address"},  
        	],
        "createdRow": function( row, data, dataIndex ) {
        	console.log(data);
        },
        "order": [],
        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
        	var html = '';
        	html += iDisplayIndex +1;        	
            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一      
            return nRow;
        },
        "columnDefs": []
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('.table1_info').parent('div').addClass('col-sm-12');
}

//查询条件下拉列表
function getDatalistInfo(){
	$.ajax({
		type : "post",
		url : "../../WeighingQueryOut/getDataList.action",
		data : {},
		async : false,
		dataType : "json",
		success : function(data) {
			if (data != null) {
				var ClientList = data.ClientList;
				var PlateNumberList = data.PlateNumberList;
				var CompanyNameList = data.CompanyNameList;
				var Driver1List = data.Driver1List;
				var FleetNameList = data.FleetNameList;
				var MaterielNameList = data.MaterielNameList;
				var MaterielModelList = data.MaterielModelList;
				// 客户名称
				if (ClientList != null && ClientList.length > 0) {
					for (var i = 0; i < ClientList.length; i++) {
						if(ClientList[i] != null && ClientList[i] != ''){
							if(  ClientList[i].client != null &&  ClientList[i].client != '')
							{$("#customerName").append("<option>" + ClientList[i].client + "</option>");}
						}
					}
				}
				// 车牌号码
				if (PlateNumberList != null && PlateNumberList.length > 0) {
					for (var i = 0; i < PlateNumberList.length; i++) {
						if(PlateNumberList[i] != null && PlateNumberList[i] != ''){
							if(  PlateNumberList[i].plateNumber != null &&  PlateNumberList[i].plateNumber != '')
							{$("#plateNumber").append("<option>" + PlateNumberList[i].plateNumber + "</option>");}
						}
					}
				}
				// 关联公司
				if (CompanyNameList != null && CompanyNameList.length > 0) {
					for (var i = 0; i < CompanyNameList.length; i++) {
						if(CompanyNameList[i] != null && CompanyNameList[i] != '')
					   {if(CompanyNameList[i].companyName != null && CompanyNameList[i].companyName != '')
					    {$("#companyName").append("<option>" + CompanyNameList[i].companyName + "</option>");}}
					}
				}
				// 司机姓名
				if (Driver1List != null && Driver1List.length > 0) {
					for (var i = 0; i < Driver1List.length; i++) {
						if(Driver1List[i] != null && Driver1List[i] != ''){
							if(  Driver1List[i].driver1 != null &&  Driver1List[i].driver1 != '')
							{$("#driverName").append("<option>" + Driver1List[i].driver1 + "</option>");}
						}
					}
				}
				// 车队名称
				if (FleetNameList != null && FleetNameList.length > 0) {
					for (var i = 0; i < FleetNameList.length; i++) {
						if(FleetNameList[i] != null && FleetNameList[i] != ''){
							if(  FleetNameList[i].fleetName != null &&  FleetNameList[i].fleetName != '')
							{$("#fleetName").append("<option>" + FleetNameList[i].fleetName + "</option>");}
						}
					}
				}
				// 产品名称
				if (MaterielNameList != null && MaterielNameList.length > 0) {
					for (var i = 0; i < MaterielNameList.length; i++) {
						if(MaterielNameList[i] != null && MaterielNameList[i] != ''){
							if(  MaterielNameList[i].materielName  != null &&  MaterielNameList[i].materielName  != '')
							{$("#materielName").append("<option>" + MaterielNameList[i].materielName + "</option>");}
						}
					}
				}
				// 规格型号
				if (MaterielModelList != null && MaterielModelList.length > 0) {
					for (var i = 0; i < MaterielModelList.length; i++) {
						if(MaterielModelList[i] != null && MaterielModelList[i] != ''){
							if(  MaterielModelList[i].materielModel != null &&  MaterielModelList[i].materielModel != '')
							{$("#materielModel").append("<option>" + MaterielModelList[i].materielModel + "</option>");}
						}
					}
				}
			}
		}
	});
}
//编辑出库单信息
function updateWeighingQueryOut(id,outOrEnter){
	var index = layer.open({
		type: 2,
		title: '编辑称重信息',
		area: ['500px', '500px'],
		content: 'weighingQueryOut_add.html?id='+id,
	});
	layer.full(index);
}
//打印出库单信息
function printing(id,outType){
	alert(id);
	if(outType == 0){alert("正常")}
	if(outType == 1){alert("调拨")}
	if(outType == 2){alert("退货")}
	if(outType == 3){alert("空发")}
	if(outType == 4){alert("兑换")}
	if(outType == 5){alert("兑换时调拨")}
}
//删除出库单信息
function deleteWeighingQueryOut(id){
		layer.confirm('确认要删除吗？', 
			function(index) {
				$.ajax({
					type: 'POST',
					url: '../../WeighingQueryOut/deleteWeighingQueryOut.action',
					data: {"id":id},
					dataType: 'json',
					success: function(data){
						if(data.code = "success"){
							layer.msg(data.message, {
								icon: 1,
								time: 1000
							},function(){
								location.reload();
							});
						}else{
							layer.msg(data.message, {
								icon: 2,
								time: 1000
							},function(){
								location.reload();
							});
						}
					}
				});
			});
}
//打印
function printWeighingQueryOut(id,outType,isRelation){
	//出库称重单打印
	var index = layer.open({
		type: 2,
		title: '出库称重单信息打印',
		content: 'outPrint.html?id='+id+"&outType="+outType+"&isRelation="+isRelation
	});
	layer.full(index);
}

//检测报告打印
function printBaoGao(id){
	var index = layer.open({
		type: 2,
		title: '检测报告信息打印',
		content: 'testReportPrint.html?id='+id
	});
	layer.full(index);
}