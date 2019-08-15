// 初始化加载质检列表
function initQulityTable () {
	var table1 = $('#qualityTable').dataTable();
	table1.fnDestroy();
	$("#qualityTable").DataTable({
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
            url: "qualityInfo/queryQualityInfo.action",
            dataSrc: "data",
            data: function (d) {
              	var param = {};
                return param;//自定义需要传递的参数。
            }
        },
        "deferRender": true,
        "columns": 
        	[{
                sTitle: '序号',
                data: null,
                className: 'text-center whiteSpace',
                render:function(data,type,row,meta) {
                    return meta.row + 1 +
                    meta.settings._iDisplayStart;
                }
            }, {
	            "data": "ProductID",
                render:function(data,type,row,meta) {
                	var	html = '<div style="cursor:pointer" onclick="toDetail(this,`'+ row.flag +'`, '+ row.Id +')">'+data+'</div>';
                    return html;
                }
	        }, {
	            "data": "flag",
	            render:function(data,type,row,meta) {
	            	   var html = "";
	                   if (data == 'out'){
	                	   html += '<div style="cursor:pointer" onclick="toDetail(this,`'+ row.flag +'`, '+ row.Id +')">出库</div>';
	                   } else {
	                	   html += '<div style="cursor:pointer" onclick="toDetail(this,`'+ row.flag +'`, '+ row.Id +')">入库</div>';
	                   }
	                   return html;
	               }
	        }, {
	            "data": "time",
                render:function(data,type,row,meta) {
                	var	html = '<div style="cursor:pointer" onclick="toDetail(this,`'+ row.flag +'`, '+ row.Id +')">'+data+'</div>';
                    return html;
                }
	        }, {
                sTitle: '状态',
                data: null,
                className: 'text-center whiteSpace',
                render:function(data,type,row,meta) {
                	var	html = '<a style="cursor:pointer" onclick="toDetail(this,`'+ row.flag +'`, '+ row.Id +')">待检</a>';
                    return html;
                }
            }],
		"order": [[ 1, 'asc' ]] 
    });
	$.fn.dataTable.ext.errMode = 'throw';//当出现问题时不弹出
	$('#contractManagement_info').css('text-align','center');
	$('.dataTables_paginate').parent('div').addClass('col-sm-12');
	
}

// 待检跳转
function toDetail (obj, flag, id) {
	var itemId = $(obj).parents('.tableArea').parent('.item').attr('data-itemId');
	if (flag == 'out') {
		$('#rightMain').attr('src', 'page/quality/out_detection_edit.html?flag=1&id='+id);
	} else {
		$('#rightMain').attr('src', 'page/quality/in_detection_edit.html?flag=1&id='+id);
	}
	$('#now_area').show();
	$('#now_area').attr('onclick', 'jumpArea(' + itemId + ')');
	$('.item').hide();
	$('#rightMain').show();
	$('#top_nav').hide();
}

// 图标闪烁事件 
var noticeFlag;
//定时查询未通过检验单
function queryInfo() {
	$.ajax({
      type: "post",
      url: 'qualityInfo/queryQualityInfo.action',
      async:false,
      data:{},
      dataType: "json",
      success: function (data) {
      	if (data) {
      		// 先移除提示图标闪烁事件，防止多次触发事件，造成图标闪烁过快
      		clearTimeout(noticeFlag);
      		// 提示图标闪烁事件
      		showportal();
      	}
      }
	});
}

//提示图标闪烁事件
function showportal() {
	// 先移除提示图标绑定的鼠标事件，防止多次触发事件，造成图标闪烁过快
	$("#notice").unbind("mouseover");
	$("#notice").unbind("mouseout");
	
    if ($("#notice").is(":hidden")) {
    	$("#notice").show();
    } else{
    	$("#notice").hide();
    }
    noticeFlag = setTimeout('showportal()', 300);
    // 给图片绑定鼠标事件，鼠标在图标上时停止闪烁，移走时继续闪烁
	$("#notice").bind("mouseover", function () { clearTimeout(noticeFlag); });
	$("#notice").bind("mouseout", function () { showportal(); });
}

// 提示图标点击事件
function dealNotice() {
	// 先移除提示图标绑定的鼠标事件，防止多次触发事件，造成图标闪烁过快
	$("#notice").unbind("mouseover");
	$("#notice").unbind("mouseout");
	// 移除提示图标闪烁事件，防止多次触发事件，造成图标闪烁过快
	clearTimeout(noticeFlag);
	// 点击后隐藏图标
	$("#notice").hide();
	// 选中左侧质检管理菜单
	$('#TabPage2 li').each(function(i, ele) {
		if(i != 4) {
			$(ele).find('img').attr('src', 'img/common/' + (i + 1) + '.png');
			$(ele).find('span').css({
				color: '#fff'
			});
			$(ele).css({
				background: '#044599'
			});
		} else {
			$(ele).find('img').attr('src', 'img/common/' + (4 + 1) + '_hover.png');
			$(ele).css({
				background: '#fff'
			});
			$(ele).find('span').css({
				color: '#044599'
			});
			$('#here_area').html('当前位置：系统&nbsp;>&nbsp;<span style="color:#1A5CC6">' + $(ele).find('span').html() + '</span>');
		}
	});
	// 页面跳转相关代码，保证在任何页面都能跳转到质检管理页面
	$('.item').hide();
	$('#rightMain').hide();
	$('.item5').show();
	// 显示表格
	$("#qualityTable").show();
	$("#qualityTable_info").show();
	$("#qualityTable_paginate").show();
}