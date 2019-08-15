// 当前登录用户id
var userId = '';
var cityId = '';
var countyId = '';
var title = '全省干线公路危桥改造工程进度情况汇总表'
$(function(){
	// 获取当前登录用户信息
	userInfo = getUserInfo();
	if(userInfo != ""){
		cityId = userInfo.cityId;
		countyId = userInfo.countyId
		if(cityId == 0){
			cityId = '';
		}
		if(countyId == 0){
			countyId = '';
		}
	}
	// datatable数据初始化
	getList();
	
	// 合计值赋值
	getSum();
})

// datatable数据初始化
function getList(){
	var CityName = $("#CityName").val();
	var table1 = $('#table1').dataTable();
	table1.fnDestroy();
	$("#table1").DataTable({
		 "paging": false,
         "lengthChange": false,
//         "pageLength": 14,
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
            url: "../../DangerousBridge/getDangerousBridgeList.action",
            dataSrc: "data",
            data: function (d) {
            	var param = {};
            	param.CityName = CityName;// 市别
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": [
        	{"data": "cityId"},
        	{
        		"data": "cityId",
        		render:function(data,type,row,meta) {
	            	var html = '';
	            	html += "<a onclick='lookUp(\""+row.cityId+"\")'style='text-decoration:none;font-size: 14px;' href='javascript:;' title='查看'>查看</a>&nbsp;&nbsp;&nbsp";
		  		    html += "<a onclick='update(\""+row.cityId+"\")'style='text-decoration:none;font-size: 14px;' href='javascript:;' title='编辑'>编辑</a>";
		  		    return html;
	            }
        	},
        	{"data": "cityName"},
        	{
        		"data": "engineeringAmount",
        		render:function(data,type,row,meta) {
	            	var html = '';
		  		    html += row.engineeringAmount+"/"+row.engineeringCount;
		  		    return html;
	            }
        	},
        	{
        		"data": "implementM",
        		render:function(data,type,row,meta) {
	            	var html = '';
		  		    html += row.implementM+"/"+row.implementSeat;
		  		    return html;
	            }
        	},
        	{
        		"data": "leadM",
        		render:function(data,type,row,meta) {
	            	var html = '';
		  		    html += row.leadM+"/"+row.leadSeat;
		  		    return html;
	            }
        	},
        	{"data": "startNumber"},
        	{"data": "startRate"},
        	{"data": "investmentProvince"},
        	{"data": "finishInvest"},
        	{"data": "tunnelSchedule"},
        	{"data": "projectProgress"}    	
        ],
        "createdRow": function( row, data, dataIndex ) {
//        	console.log(data);
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
}

//合计值赋值
function getSum(){
	var CityName = $("#CityName").val();
	$.ajax({
		type: 'POST',
		url: '../../DangerousBridge/getDangerousBridgeSum.action',
		data:{"CityName":CityName},
		dataType: 'json',
		success: function(data){
			if (data[0] != null){
				$("#planSum").html(data[0].engineeringAmountSum+"/"+data[0].engineeringCountSum)
				$("#implementSum").html(data[0].implementMSum+"/"+data[0].implementSeatSum)
				$("#leadSum").html(data[0].leadMSum+"/"+data[0].leadSeatSum);
				$("#startNumberSum").html(data[0].startNumberSum);
				$("#startRateSum").html(data[0].startRateSum);
				$("#provinceInvestSum").html(data[0].investmentProvinceSum);
				$("#finishInvestSum").html(data[0].finishInvestSum);
				$("#percentageOfProject").html(data[0].tunnelScheduleSum);
			} else {
				$("#planSum").html("")
				$("#implementSum").html("")
				$("#leadSum").html("");
				$("#startNumberSum").html("");
				$("#startRateSum").html("");
				$("#provinceInvestSum").html("");
				$("#finishInvestSum").html("");
				$("#percentageOfProject").html("");
			}	
		}
	});	
	
}

//查询
function search(){	
	getList();
	getSum();
}

//查看
function lookUp(cid){
	var index = layer.open({
		type: 2,
		title: '查看险桥',
		content: 'dangerousBridge_add.html?cityId='+cid+'&flag=0',
	});
	layer.full(index);
}

// 编辑
function update(cid){
	var index = layer.open({
		type: 2,
		title: '修改险桥',
		content: 'dangerousBridge_add.html?cityId='+cid+'&flag=1',
	});
	layer.full(index);
}

//打印
function printTable() { 
	var myDate = new Date();//获取系统当前时间
	var notChecked = $("input:checkbox").not("input:checked") ;//获取未被选择的checkbox
	if (notChecked.length>0){
		for(var i=notChecked.length-1;i>=0;i--){
			// 移除列
			delcol(notChecked[i].value);
		}
	}
	
	// 删除操作列
	delCZ();
	
	//Get the HTML of table
    var tableToPrint = document.getElementById('table1').outerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;
    //Reset the page's HTML with div's HTML only
    document.body.innerHTML = 
        "<html><head><title></title></head><body>" + 
        "<center><h1>"+title+"</h1></center>"+"<right><span>"
        +"填表日期："+myDate.getFullYear()+"-"+Number(Number(myDate.getMonth())+1)+"-"+myDate.getDate()+"</h1></span><br/>"
  	    +tableToPrint + "</body>";
    //Print Page
    window.print();
    //Restore orignal HTML
    document.body.innerHTML = oldPage;
    //Refresh the current page
    location.reload();  
}

// 移除列
function delcol(colnum) {
	var thred = Number(colnum) + 2;
	var tbody = Number(colnum) + 3;
	$("#table1 tr th");
	//$("#table1 tr td");
	//nth-child:获取子元素从 1 开始(tbody)	
	// 移除tbody部分
	$("#table1 tr td:nth-child("+tbody+")").remove();
	
	// 移除thread部分
	//eq:获取子元素索引从 0 开始，先删除表头
	$("#table1 tr th:eq("+ thred +")").remove();						
	
	//(tfoot)
	$("#table1 tfoot tr th:eq("+colnum+")").remove();
}

// 删除操作列
function delCZ(){
	//eq:获取子元素索引从 0 开始，先删除表头
	$("#table1 tr th:eq(1)").remove();
	//nth-child:获取子元素从 1 开始
	$("#table1 tr td:nth-child(2)").remove();
	$("#caozuoTab").attr('colSpan',$("#caozuoTab").attr('colSpan') -1);
}
