// 获取出库单id
var id = '';
// 出库单信息
var info = "";

var allotClient = "";

var allotCustomerAlias ="";

var deliverer = "";

var deliveryPhone = "";

////销售订单编号
var orderNumberList;
$(function(){
	
//	$("#main_table1").hide();
//	$("#main_table2").hide();
	// 获取上页面传过来的值
	id = getUrlParam("id");
	outType = getUrlParam("outType");
	getOrderNumberList();
	$("#outbound").hide();
	//税率取得
	TaxRateInfo = getDataDictionaryDefaultNums('sl');
	// 获取出库单信息
	if(id != null && id != ""){
		// 画面数据初始化
		$.ajax({
			type: "post",
			url: "../../outlist/getSalesList.action",
			data:{"id":id},
			dataType: "json",
			async:false,
			success: function (data) {
				info = data.data[0];
				
				SetFromValues($("#submitForm"), info);
				$("#client").val(info.customerCode);
				if (info.outTypeMark == 0) {
					$("input[name=outTypeMark]:eq(1)").attr("checked",'checked'); 
				} else {
					$("input[name=outTypeMark]:eq(0)").attr("checked",'checked');
				}
				for (var i = 0; i < TaxRateInfo.length; i++) {
					if(TaxRateInfo[i].Code == info.taxRate){
						$("#taxRate").val(TaxRateInfo[i].Content);
						break;
					}else{
						$("#taxRate").val("");
					}
				}
				if(outType == "3"){
					$("#outbound").show();
					getOutboundList(info.transports);
				}
				var flag = 0;
				if(outType == "1" || outType == "5" || outType == "2"){
					// 画面数据初始化
					$.ajax({
						type: "post",
						url: "../../outlist/getNoweighoutList.action",
						data:{"dispatchOutWarehouseId":id},
						dataType: "json",
						async:false,
						success: function (data) {
							if (data.data.length > 0 ) {
								flag += 1;
								$("#main_table").show();
//								for(var i=0; i<orderNumberList.length; i++){
//									if(orderNumberList[i].orderNumbers == data.data[0].orderNumber){	
//										// 调拨客户名称
//										allotClient = orderNumberList[i].customerName;
//										// 调拨客户别称
//										allotCustomerAlias = orderNumberList[i].customer;
//										// 送货人
//										deliverer = orderNumberList[i].contacts;
//										// 送货电话
//										deliveryPhone = orderNumberList[i].telephone;
//										break;
//									}
//								}
								$("div [name = outTypes]").html("调拨详情1");
								var result = "";
								//result +="<div class='row newLine'>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>客户编号：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].customerCode == null?"":data.data[0].customerCode)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>客户别称：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].customerAlias == null?"":data.data[0].customerAlias)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>销售订单编号：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].orderNumber == null?"":data.data[0].orderNumber)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>合同编号：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].contractNumber == null?"":data.data[0].contractNumber)+" ></div>";
								//result +="</div>";
								//result +="<div class='row newLine'>";
//								result +="<div class='col-lg-3'><div class='ui_text_rt col-lg-4'>车牌号码：</div><input name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].carName == null?"":data.data[0].carName)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>结算情况：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].transportBalanceName == null?"":data.data[0].transportBalanceName)+"></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>物料名称：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].materielName == null?"":data.data[0].materielName)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>物料型号：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].materielModel == null?"":data.data[0].materielModel)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>出库重量(吨)：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(info.suttle == null ?"":info.suttle)+"></div>";
								//result +="</div>";
								//result +="<div class='row newLine'>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>结算重量(吨)：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].settlementWeight == null?"":data.data[0].settlementWeight)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>调拨重量(吨)：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].allotWeight == null?"":data.data[0].allotWeight)+" ></div>";
//								result +="<div class='col-lg-3'><div class='ui_text_rt col-lg-4'>车主：</div><input name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].carOwner == null?"":data.data[0].carOwner)+" ></div>";
//								result +="<div class='col-lg-3'><div class='ui_text_rt col-lg-4'>车主电话：</div><input name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].carOwnerTelephone == null?"":data.data[0].carOwnerTelephone)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>送货人：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].deliverer == null?"":data.data[0].deliverer)+"></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>送货电话：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].deliveryPhone == null?"":data.data[0].deliveryPhone)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>收货人：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].consignee == null?"":data.data[0].consignee)+"></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>收货电话：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].consigneePhone == null?"":data.data[0].consigneePhone)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>收货地址：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].consigneeAddress == null?"":data.data[0].consigneeAddress)+" ></div>";
								//result +="</div>";
								//result +="<div class='row newLine'>";
								//result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>调拨客户编号：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].customerCode == null?"":data.data[0].customerCode)+"></div>";
								//result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>调拨客户别称：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].customerAlias == null?"": data.data[0].customerAlias)+"></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>备注：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' readonly='readonly' type='text' id='detectionResult' value="+(data.data[0].remarks == null?"":data.data[0].remarks)+" ></div>";
								//result +="</div>";
								$("#tableList").append(result);
							}
							if (data.data.length == 2) {
//								flag += 1;
//								$("#main_tableTwo").show();
//								for(var i=0; i<orderNumberList.length; i++){
//									if(orderNumberList[i].orderNumbers == data.data[0].orderNumber){	
//										// 调拨客户名称
//										allotClient = orderNumberList[i].customerName;
//										// 调拨客户别称
//										allotCustomerAlias = orderNumberList[i].customer;
//										// 送货人
//										deliverer = orderNumberList[i].contacts;
//										// 送货电话
//										deliveryPhone = orderNumberList[i].telephone;
//										break;
//									}
//								}
								$("div [name = outTypesTwo]").html("调拨详情2");
								var result = "";
								//result +="<div class='row newLine'>";
								result +="<br/><div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>客户编号：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].customerCode == null?"":data.data[1].customerCode)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>客户别称：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].customerAlias == null?"":data.data[1].customerAlias)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>销售订单编号：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].orderNumber == null?"":data.data[1].orderNumber)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>合同编号：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].contractNumber == null?"":data.data[1].contractNumber)+" ></div>";
								//result +="</div>";
								//result +="<div class='row newLine'>";
//								result +="<div class='col-lg-3'><div class='ui_text_rt col-lg-4'>车牌号码：</div><input name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].carName == null?"":data.data[0].carName)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>结算情况：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].transportBalanceName == null?"":data.data[1].transportBalanceName)+"></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>物料名称：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].materielName == null?"":data.data[1].materielName)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>物料型号：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].materielModel == null?"":data.data[1].materielModel)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>出库重量(吨)：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(info.suttle == null ?"":info.suttle)+"></div>";
								//result +="</div>";
								//result +="<div class='row newLine'>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>结算重量(吨)：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].settlementWeight == null?"":data.data[1].settlementWeight)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>调拨重量(吨)：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].allotWeight == null?"":data.data[1].allotWeight)+" ></div>";
//								result +="<div class='col-lg-3'><div class='ui_text_rt col-lg-4'>车主：</div><input name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].carOwner == null?"":data.data[0].carOwner)+" ></div>";
//								result +="<div class='col-lg-3'><div class='ui_text_rt col-lg-4'>车主电话：</div><input name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].carOwnerTelephone == null?"":data.data[0].carOwnerTelephone)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>送货人：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].deliverer == null?"":data.data[1].deliverer)+"></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>送货电话：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].deliveryPhone == null?"":data.data[1].deliveryPhone)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>收货人：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].consignee == null?"":data.data[1].consignee)+"></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>收货电话：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].consigneePhone == null?"":data.data[1].consigneePhone)+" ></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>收货地址：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].consigneeAddress == null?"":data.data[1].consigneeAddress)+" ></div>";
								//result +="</div>";
								//result +="<div class='row newLine'>";
								//result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>调拨客户编号：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].customerCode == null?"":data.data[1].customerCode)+"></div>";
								//result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>调拨客户别称：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' id='detectionResult' readonly='readonly' type='text' value="+(data.data[1].customerAlias == null?"": data.data[1].customerAlias)+"></div>";
								result +="<div class='col-lg-3 newLine col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>备注：</div><input name='detectionResult' class='ui_input_txt02 col-lg-8 col-sm-8' readonly='readonly' type='text' id='detectionResult' value="+(data.data[1].remarks == null?"":data.data[1].remarks)+" ></div>";
								//result +="</div>";
								$("#tableListTwo").append(result);
							}
						}
					})
						// 画面数据初始化
						$.ajax({
							type: "post",
							url: "../../outlist/getProcessList.action",
							data:{"outWarehouseId":id},
							dataType: "json",
							async:false,
							success: function (data) {
								if(data.data.length > 0 ){
									flag += 1;
									$("#main_table1").show();
									$("div [name = outTypes1]").html("退货详情");
									var result = "";
									if (data.data[0].enterTypeMark == 3) {
										//result +="<div class='row newLine'>";
										result +="<div class='newLine col-lg-3 col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>销售合同编号：</div><input class='ui_input_txt02 col-lg-8 col-sm-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].contractNumber == null?"":data.data[0].contractNumber)+"></div>";
										result +="<div class='newLine col-lg-3 col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>是否大车称重：</div>"+(info.cartWeighType == 0?"<input type='radio' name='isHeavyCar' checked='checked' disabled='disabled' value='0'/>是"+
												 "<input type='radio' style='margin-left: 15%;margin-bottom:9px;' name='isHeavyCar' disabled='disabled' value='1'/>否</div>":"<input type='radio' name='isHeavyCar' disabled='disabled' value='0'/>是"+
												 "<input type='radio' style='margin-left: 15%;margin-bottom:9px;' name='isHeavyCar' checked='checked' disabled='disabled' value='1'/>否</div>");
//										result +="<div class='col-lg-3'><div class='ui_text_rt col-lg-4'>车牌号码：</div><input class='ui_input_txt02 col-lg-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].plateNumber == null?"":data.data[0].plateNumber)+"></div>";
//										result +="<div class='col-lg-3'><div class='ui_text_rt col-lg-4'>车主：</div><input class='ui_input_txt02 col-lg-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].carOwner == null?"":data.data[0].carOwner)+"></div>";
										result +="<div class='newLine col-lg-3 col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>数量：</div><input class='ui_input_txt02 col-lg-8 col-sm-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(info.amount == null?"":info.amount)+"></div>";
										result +="<div class='newLine col-lg-3 col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>优先级：</div><input class='ui_input_txt02 col-lg-8 col-sm-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].prioritys == null?"":data.data[0].prioritys)+"></div>";
										//result +="</div>";
										//result +="<div class='row newLine'>";
//										result +="<div class='col-lg-3'><div class='ui_text_rt col-lg-4'>车主电话：</div><input class='ui_input_txt02 col-lg-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].carOwnerTelephone == null?"":data.data[0].carOwnerTelephone)+"></div>";
										result +="<div class='newLine col-lg-3 col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>物料名称：</div><input class='ui_input_txt02 col-lg-8 col-sm-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].materielName == null?"":data.data[0].materielName)+" ></div>";
										result +="<div class='newLine col-lg-3 col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>物料型号：</div><input class='ui_input_txt02 col-lg-8 col-sm-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].materielModel == null?"":data.data[0].materielModel)+" ></div>";
										result +="<div class='newLine col-lg-3 col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>送货人：</div><input class='ui_input_txt02 col-lg-8 col-sm-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].deliveryMan == null?"":data.data[0].deliveryMan)+"></div>";
										result +="<div class='newLine col-lg-3 col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>送货电话：</div><input class='ui_input_txt02 col-lg-8 col-sm-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].deliveryManPhone == null?"":data.data[0].deliveryManPhone)+"></div>";
										//result +="</div>";
										//result +="<div class='row newLine'>";
										result +="<div class='newLine col-lg-3 col-sm-6'><div class='ui_text_rt col-lg-4 col-sm-4'>备注：</div><input class='ui_input_txt02 col-lg-8 col-sm-8' name='detectionResult'  id='detectionResult' readonly='readonly' type='text' value="+(data.data[0].remarks == null?"":data.data[0].remarks)+"></div>";
										//result +="</div>";
									}
									$("#tableList1").append(result);
									}
								}
							})
					}
				if(flag == 1){
					$('#main_table1').addClass('main_table2_top1');
				}else if(flag == 2){
					flag = 3
				}
				var validFlag = 0;
				var validFlags = 0;
				$.ajax({
					type: "post",
					url: "../../outlist/getInfoList.action",
					data:{"outWarehouseId":info.serialId},
					dataType: "json",
					async:false,
					success: function (data) {
						if(data.data.length > 0){
							validFlags = 1;
							if(flag == 0){
								$('#main_table2').addClass('main_table2_top1');
							}else if(flag == 1){
								$('#main_table2').addClass('main_table2_top2');
							}
							$('#main_table2').show();
							validFlag += 1;
						}
					}
				})
				var table = $('#tableList2').dataTable();
				table.fnDestroy();
				$("#tableList2").DataTable({
			        "paging": false,
			        "lengthChange": false,
			        "pageLength": 14,
			        "searching": false,
			        "ordering": false,
			        "info": false,
			        "sInfo": false,
			        "autoWidth": true,
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
						type : "post",
						url : "../../outlist/getInfoList.action",
						dataSrc : "data",
						data : {"outWarehouseId":info.serialId}
					},
			        "deferRender": true,
			        "columns": [
			        	{
			                "data": "id"
			            },
			            {
			        	 "data": "contractNumber"
			        }, {
			            "data": "orderNumber"
			        }, {
			            "data": "name"
			        }, {
			            "data": "client"
			        }, {
			            "data": "customerAlias"
			        }, {
			            "data": "plateNumber"
			        },
//			        {
//			            "data": "carOwner",
//			            render: function(data, type, row) {
//							data = row.carOwner + "--" + row.carOwnerTelephone;
//							return data;
//			            }
//			        },
			        {
			        	"data": "productID",
			            render: function(data, type, row) {
			            	if(row.materielName != null){
			            		data = row.materielName + "--" + row.materielModel;
			    				return data;	
			            	}else{
			            		return "";
			            	}
			            }
			        }, {
			            "data": "address"
			        }, {
			            "data": "consignee",
			            render: function(data, type, row) {
							data = row.consignee + "--" + row.consigneePhone;
							return data;
			            }
			        }, {
			            "data": "testReportNumber"
			        }, {
			            "data": "factoryTime"
			        }, {
			            "data": "expectedDeliveryDate"
			        }, {
			            "data": "amount"
			        }, {
			            "data": "isTesting",
			            render: function(data, type, row) {
							if(data == 0){
								data = "是";
							}else{
								data = "否";
							}
							return data;
			            }
			        },
			        {
			            "data": "outTypeName"
			        }, {
			            "data": "outTypeMark",
			            render: function(data, type, row) {
							if(data == 1){
								data = "是";
							}else{
								data = "否";
							}
							return data;
			            }
			        }, {
			            "data": "isEmpty",
			            render: function(data, type, row) {
							if(data == "Y"){
								data = "是";
							}else{
								data = "否";
							}
							return data;
			            }
			        }, {
			            "data": "emptyList"
			        }, {
			            "data": "cancellationCause"
			        }],
//			        "order": [[ 1, 'asc' ]],
			        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
			            $("td:first", nRow).html("<input name='radio' type='hidden' value=\""+ aData.id +"\" />"+(iDisplayIndex +1));//设置序号位于第一列，并顺次加一               	
			           // 获取所有数据
//			           infoList.push(aData);
			           return nRow;
			        },
			    });
				$.ajax({
					type: "post",
					url : "../../outlist/getRepertoryInfoList.action",
					data : {"dispatchOutWarehouseId":info.id},
					dataType: "json",
					async:false,
					success: function (data) {
						if(data.data.length > 0){
							if(flag != 3 && validFlag == 0){
								$('#main_table3').addClass('main_table2_top2');
							}else if(flag != 3 && validFlag == 1){
								$('#main_table3').addClass('main_table2_top3');
							}
							if(validFlag == 0 && validFlags == 0){
								$('#main_table3').addClass('main_table2_top2');
							}
							$('#main_table3').show();	
						}
					}
				})
				var table1 = $('#tableList3').dataTable();
				table1.fnDestroy();
				$("#tableList3").DataTable({
			        "paging": false,
			        "lengthChange": false,
			        "pageLength": 14,
			        "searching": false,
			        "ordering": false,
			        "info": false,
			        "sInfo": false,
			        "autoWidth": true,
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
						type : "post",
						url : "../../outlist/getRepertoryInfoList.action",
						dataSrc : "data",
						data : {"dispatchOutWarehouseId":info.id}
					},
			        "deferRender": true,
			        "columns": [ 
			    	{
			            "data": "id"
			        },
			        {
			        	"data":"serialID"
			        }, {
			            "data": "carName"
			        }, {
			        	"data": "materielInfoId",
			            render: function(data, type, row) {
							data = row.materielName + "--" + row.materielModel;
							return data;
			            }
			        }, {
			            "data": "client"
			        }, {
			            "data": "customerAlias"
			        }, 
			        {
			            "data": "isExamine",
			            render: function(data, type, row) {
			            	var html = "";
			            	if(data != null){
			            		if(data == 0){
			                		html = "审核通过"
			                	}else if(data == 1){
			                		html = "审核未通过"
			                	}	
			            	}else{
			            		html = "未审核";
			            	}
							return html;
			            }
			        },
			        {
			            "data": "contractNumber"
//			            ,render: function(data, type, row) {
//			             	var html = "";
//			             	if(row.contractNumber != null && row.contractNumber != ''){
//			             		html = "<span class='hetongbianhao hetong' onmouseover='mouseover("+row.id+",0)'  onmouseout ='mouseout("+row.id+",0)'>"+row.contractNumber+"</span>";
//			             		var Contract = getContractList(row.contractId);
//			             		if(Contract != null && Contract != ''){
//			             		// 获取销售合同信息
//			                     	html += "<div id='contractNumber"+row.id+"' class='fuchuang4'>"
//			         							+"<table style='border-top: 1px solid #ECECEC;'>"
//			         							+"	<tr>"
//			         							+"		<td class='hetongzhuangtai'>合同名称：</td>"
//			         							+"		<td class='hetongzhuangtai'>"+Contract[0].contractName+"</td>"
//			         							+"		<td class='hetongzhuangtai'>合同日期：</td>"
//			         							+"		<td class='hetongzhuangtai'>"+Contract[0].contractDate+"</td>"
//			         							+"	</tr>"
//			         							+"	<tr>"
//			         							+"		<td class='hetongzhuangtai'>客户名称：</td>"
//			         							+"		<td class='hetongzhuangtai'>"+Contract[0].customerName+"</td>"
//			         							+"		<td class='hetongzhuangtai'>销售公司名称：</td>"
//			         							+"		<td class='hetongzhuangtai'>"+Contract[0].salesCompanyName+"</td>"
//			         							+"	</tr>"
//			         							+"	<tr>"
//			         							+"		<td class='hetongzhuangtai'>销售数量(t)：</td>"
//			         							+"		<td class='hetongzhuangtai'>"+Contract[0].saleNumber+"</td>"
//			         							+"		<td class='hetongzhuangtai'>是否来料加工：</td>"
//			         							+"		<td class='hetongzhuangtai'>"+Contract[0].isIncoming+"</td>"
//			         							+"	</tr>"
//			         							+"</table>"
//			         							+"</div>";
//			             		}
//			             	}			             	
//			             	return html;
//			            }
			        }, {
			            "data": "orderNumber"
			        }, {
			            "data": "consignee"
			        }, {
			            "data": "consigneePhone"
			        }, {
			        	"data": "consigneeAddress"
			        }, {
			            "data": "transportBalance",
			            render: function(data, type, row) {
							if(data == 0){
								data = "我方承担运费";
							}else if(data == 1){
								data = "客户自提 ";
							}else if(data == 2){
								data = "我方垫付运费";
							}else if(data == 3){
								data = "其他";
							}
							return data;
			            }
			        }, {
			            "data": "isCarryOff",
			            render: function(data, type, row) {
							if(data == 0){
								data = "是";
							}else if(data == 1){
								data = "否";
							}
							return data;
			            }
			        }, {
			            "data": "state",
			            render: function(data, type, row) {
							if(data == 1){
								data = "是";
							}else{
								data = "否";
							}
							return data;
			            }
			        }, {
			            "data": "deliverer"
			        }, {
			            "data": "deliveryPhone"
			        }, {
			            "data": "taxRate",
			            render: function(data, type, row) {
							if(data == 0){
								data = "5";
							}else if(data == 1){
								data = "10";
							}else if(data == 2){
								data = "15";
							}else if(data == 3){
								data = "20";
							}else if(data == 4){
								data = "30";
							}
							return data;
			            }
			        },{
			            "data": "type",
			            render: function(data, type, row) {
							if(data == 0){
								data = "正常";
							}else if(data == 1){
								data = "调拨";
							}
							return data;
			            }
			        },{
			            "data": "isTesting",
			            render: function(data, type, row) {
							if(data == 0){
								data = "是";
							}else if(data == 1){
								data = "否";
							}
							return data;
			            }
			        },{
			            "data": "remarks"
			        },{
			            "data": "isQualified",
			            render: function(data,type,row){
			            	if(data == 0){
			            		data = "合格";
			            	}else if(data == 1){
			            		data = "不合格";
			            	}
			            	return data;
			            }
			        }],
					"createdRow" : function(row, data, dataIndex) {
						if(data.remarks.length > 10){//只有超长，才有td点击事件
			                $(row).children('td').eq(20).attr('onmouseover','javascript:changeShowRemarks(this);');
			            }
			            $(row).children('td').eq(20).attr('content',data.remarks);
					},
			        "columnDefs": [{
			            "targets": 20,
			            "visible": true,
			            "searchable": false,
			            "type": "date",
			            "render": function (data, type, full, meta) {
			                if (full.remarks.length  > 10) {
			                    return getPartialRemarksHtml(full.remarks);//显示部分信息
			                } else {
			                    return full.remarks;//显示原始全部信息            }
			                }
			            }
			        }],
			        "order": [[ 1, 'asc' ]],
			        "fnRowCallback" : function(nRow, aData, iDisplayIndex){
			        	var html = '';
			        	html += iDisplayIndex +1;        	
			            $("td:first", nRow).html(html);//设置序号位于第一列，并顺次加一             	
			           return nRow;
			        }
			    });
//				$('.dataTables_paginate').parent('div').addClass('col-sm-12');
			}
		});
	}
});

//form表单赋值方法
function SetFromValues(el, data)
{
    for (var p in data)
    {
        el.find(":input[name='" + p + "']").val(data[p]);
    } 
    $("#productID").val(data["materielName"]+"-"+data["materielModel"]);
}


//销售合同编号
function getContractList(id){
	var Contract = '';
	$.ajax({
		type: "post",
		url: "../../instore/getContractList.action",
		data:{"id":id},
		dataType: "json",
		async:false,
		success: function (data) {
			Contract = data;
		}
	});
	return Contract;
}

function mouseover(id,flag){
	if(flag == 0){
		//鼠标放在合同编号，显示飘窗
		$('#contractNumber'+id).show();
	}else{
		//鼠标放在合同编号，显示飘窗
		$('#orderNumber'+id).show();
	}
}

function mouseout(id,flag){
	if(flag == 0){
		//鼠标离开合同编号，飘窗消失
		$('#contractNumber'+id).hide();
	}else{
		//鼠标离开合同编号，飘窗消失
		$('#orderNumber'+id).hide();
	}
}


//出库单号
function getOutboundList(value){
	$.ajax({
		type: "post",
		url: "../../outbound/getAllOutboundList.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				// 车牌号码
				outboundList = data;
				if(outboundList != null && outboundList.length > 0){
					// 清空
					for(var i = 0; i < outboundList.length; i++){
						if (value == outboundList[i].Transports) {
							$("#outboundList").text(outboundList[i].Serial_ID+" / "+outboundList[i].MaterielInfo+" / "+outboundList[i].Transports+" / "+outboundList[i].PlateNumber);
						}
					}
				}
			}
		}
	});
}


//销售订单编号
function getOrderNumberList(){
	$.ajax({
		type: "post",
		url: "../../outbound/getOrderNumberList.action",
		data:{},
		dataType: "json",
		async:false,
		success: function (data) {
			if(data != null){
				// 销售订单编号
				orderNumberList = data;
				if(orderNumberList != null && orderNumberList.length > 0){
					// 清空
					$("#orderNumberList").empty();
					for(var i = 0; i < orderNumberList.length; i++){
						$("#orderNumberList").append("<option>"+orderNumberList[i].orderNumbers+"</option>");
					}
				}
			}
		}
	});
}


