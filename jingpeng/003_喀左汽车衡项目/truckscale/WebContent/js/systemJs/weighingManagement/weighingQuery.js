var param = {};
var uesrId = "";
var uesrInfo = ""; //用户信息
$(function(){
	uesrId = getUrlParam("userId");
	uesrInfo = getUserInfo(uesrId);
	//下拉列表
	getDatalistInfo();
	//查询
	search();
})
//查询列表
function search(){
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var feedCompany = $("#feedCompany").val();
	var receiveUnit = $("#receiveUnit").val();
	var outOrEnter = $("#outOrEnter").val();
	var materielName = $("#materielName").val();
	var materielModel = $("#materielModel").val();
	var driverName = $("#driverName").val();
	var carDriverName = $("#carDriverName").val();
	var PlateNumber = $("#plateNumber").val();
	var measurementMode = $("#measurementMode").val();
	param.startTime = startTime;
	param.endTime = endTime;
	param.feedCompany = feedCompany;
	param.receiveUnit = receiveUnit;
	param.outOrEnter = outOrEnter;
	param.materielName = materielName;
	param.materielModel = materielModel;
	param.driverName = driverName;
	param.carDriverName = carDriverName;
	param.PlateNumber = PlateNumber;
	param.measurementMode = measurementMode;
	getWeighingQuery(param);
	sum(param);
}
// datatable数据初始化
function getWeighingQuery(param){
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
            url: "../../WeighingQuery/getWeighingQuery.action",
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
        			html += "<a onclick='DDDWeighingQuery("+row.id+","+row.outOrEnter+")'style='text-decoration:none' href='javascript:;' title='打印'>打印</a>&nbsp;";
        			html += "<a onclick='updateWeighingQuery("+row.id+","+row.outOrEnter+")'style='text-decoration:none' href='javascript:;' title='编辑'>编辑</a>&nbsp;";
        			html += "<a onclick='deleteWeighingQuery("+row.id+")' style='text-decoration:none' href='javascript:;' title='删除'>删除</a>";
        			return html;
        		}},
        	{"data": "serialId"},
        	{"data": "createrDate"},
        	{"data": "plateNumber"},
        	{"data": "materielName"},
        	{"data": "materielModel"},
        	{"data": "outOrEnter",
        		render:function(data,type,row,meta) {
        			if(data == 0){
        				return '出库'
        			}else{
        				return '入库'
        			}
        		}},
        	{"data": "grossWeight"},
        	{"data": "tareWeight"},
        	{"data": "netWeight"},
        	{"data": "deduction",
        		render:function(data,type,row,meta) {
        			var html = '';
        			if(data == 0){//扣除量
        				data = row.numericalValue+"t";
        				return data;
        			}else{//扣除率
        				data = row.numericalValue+"%";
        				return data;
        			}
        		}},
        	{"data": "measurementMode",
        		render:function(data,type,row,meta) {
        			if(data == 0){
        				return '自动'
        			}else{
        				return '手动'
        			}
        		}},
        	{"data": "grossWeightCount"},
        	{"data": "tareWeightCount"},
        	{"data": "creater"},
        	{"data": "feedCompanyName"},
        	{"data": "receiveUnitName"},
        	{"data": "driverName"},
        	{"data": "transportCompanyName"},
        	{"data": "endAddress"},
        	{"data": "startAddress"},
        	{
        		"data": "temperature",
        		render: function(data,type,row,meta){
        			if(data == null || data == 0){
        				return "";
        			}else{
        				return data;
        			}
        		}
        	},
        	{"data": "ratioOfOil",
        		render:function(data,type,row,meta) {
        			var html = '';
        			if(data == null || data == ""){
        				html += "<td></td>"
        			}else{
        				html += "<td>"+data+"%</td>"
        			}
        			return html;
        		}},
        	{"data": "route"},
        	{"data": "remarks"}
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
function sum(param){
	$.ajax({
		type: 'POST',
		url: '../../WeighingQuery/getWeighingQuerySum.action',
		data: param,
		dataType: 'json',
		success: function(data){
			if(data.data != null && data.data != ""){
				var netWeight =data.data[0].netWeight;
				$("#netWeight").val(netWeight);
			}else{
				$("#netWeight").val("");
			}
		}
	});
}
//查询条件下拉列表
function getDatalistInfo(){
	$.ajax({
		type : "post",
		url : "../../WeighingQuery/getDataList.action",
		data : {},
		async : false,
		dataType : "json",
		success : function(data) {
			// 清空
			if (data != null) {
				var feedCompanyList = data.feedCompanyList;
				var receiveUnitList = data.receiveUnitList;
				var materielNameList = data.materielNameList;
				var materielModelList = data.materielModelList;
				var driverNameList = data.driverNameList;
				var carDriverNameList = data.carDriverNameList;
				var carNumberList = data.carNumberList;
				//供料单位datalist
				if (feedCompanyList != null && feedCompanyList.length > 0) {
					for (var i = 0; i < feedCompanyList.length; i++) {
						if(  feedCompanyList[i] != null &&  feedCompanyList[i] != '')
						{$("#feedCompany").append("<option>" + feedCompanyList[i].feedCompanyName + "</option>");}
					}
				}
				//收料单位datalist
				if (receiveUnitList != null && receiveUnitList.length > 0) {
					for (var i = 0; i < receiveUnitList.length; i++) {
						if(  receiveUnitList[i] != null &&  receiveUnitList[i] != '')
						{$("#receiveUnit").append("<option>" + receiveUnitList[i].receiveUnitName + "</option>");}
					}
				}
				//材料名称datalist
				if (materielNameList != null && materielNameList.length > 0) {
					for (var i = 0; i < materielNameList.length; i++) {
						if(  materielNameList[i] != null &&  materielNameList[i] != '')
						{$("#materielName").append("<option>" + materielNameList[i].materielName + "</option>");}
					}
				}
				//规格型号datalist
				if (materielModelList != null && materielModelList.length > 0) {
					for (var i = 0; i < materielModelList.length; i++) {
						if(  materielModelList[i] != null &&  materielModelList[i] != '')
						{$("#materielModel").append("<option>" + materielModelList[i].materielModel + "</option>");}
					}
				}
				//司机名字datalist
				if (driverNameList != null && driverNameList.length > 0) {
					for (var i = 0; i < driverNameList.length; i++) {
						if(  driverNameList[i] != null &&  driverNameList[i] != '')
						{$("#driverName").append("<option>" + driverNameList[i].driverName + "</option>");}
					}
				}
				//车主名字datalist
				if (carDriverNameList != null && carDriverNameList.length > 0) {
					for (var i = 0; i < carDriverNameList.length; i++) {
						if(  carDriverNameList[i] != null &&  carDriverNameList[i] != '')
						{$("#carDriverName").append("<option>" + carDriverNameList[i].carOwnerName + "</option>");}
					}
				}
				//车牌号码datalist
				if (carNumberList != null && carNumberList.length > 0) {
					for (var i = 0; i < carNumberList.length; i++) {
						if(  carNumberList[i] != null &&  carNumberList[i] != '')
						{$("#plateNumber").append("<option>" + carNumberList[i].plateNumber + "</option>");}
					}
				}
			}
		}
	});
}

//删除称重信息
function deleteWeighingQuery(id){
		layer.confirm('确认要删除吗？', 
			function(index) {
				$.ajax({
					type: 'POST',
					url: '../../WeighingQuery/deleteWeighingQuery.action',
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
//编辑称重信息
function updateWeighingQuery(id,outOrEnter){
	var index = layer.open({
		type: 2,
		title: '编辑称重信息',
		area: ['500px', '500px'],
		content: 'weighingQuery_add.html?id='+id+'&outOrEnter='+outOrEnter,
	});
	layer.full(index);
}
//导出称重信息
function exportExcel() {
	$.ajaxFileUpload({
		url : "../../excell/dc.action",
		data:param,
		secureuri : false,
		fileElementId : 'file',
		success : function(res, status) {
		}
	});
}

//打印
function DDDWeighingQuery(id,outOrEnter){
	if(outOrEnter == 0){
		//出库称重单打印
		var index = layer.open({
			type: 2,
			title: '出库称重单信息打印',
			content: 'weighingQuery_outPrint.html?id='+id
		});
		layer.full(index);
	}else if(outOrEnter == 1){
		//入库称重单打印
		var index = layer.open({
			type: 2,
			title: '入库称重单信息打印',
			content: 'weighingQuery_inPrint.html?id='+id
		});
		layer.full(index);
	}
}