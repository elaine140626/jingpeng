var infoList; //出库集合

$(function(){
	
	user = getUserInfo();
	DBillNumber = getUrlParam("BillNumber");
	flag = getUrlParam("flag");
	baseUrl = getContextPath();
	
	//html 出库入库单 车牌号
	$.ajax({
		type: "post",
		url: baseUrl+'/TransportationExportmeasure/getExportMeasurePlate.action',
		async:false,
		dataType: "json",
		success: function (data) {
			var PlateList = data.data;
			$.each(PlateList, function (i, v) {
				$('<option></option>').attr("value", i).html(v.PlateNumber).appendTo($("#plateNumber"));
			})
		}
	});
	
	if(DBillNumber != null && DBillNumber != ""){
		findTranSportListByid(DBillNumber);
	}
	
	if(DBillNumber == "" || DBillNumber == null|| flag == 0){
		billNumber = getContractInfoPrefix("YS");
		updateContractInfoPrefix("YS");
		$("#billNumber").val(billNumber);
	}
	
})

function changePlateNumber(){
	
	var plateNumber = $("#plateNumber").find("option:selected").text();
	$("#carOwner").val("");
	$("#carOwnerTelephone").val("");
	$("#transportationList tbody").empty("")
	if($("#plateNumber").val() == -1){
		$("#transportationList tbody").empty("")
		$("#carOwner").val("");
		$("#carOwnerTelephone").val("");
	}else{
		$.ajax({
			type: "post",
			url: baseUrl+'/TransportationExportmeasure/getExportmeasureByPlateNumber.action',
			async:false,
			data:{plateNumber:plateNumber},
			dataType: "json",
			success: function (data) {
				infoList = data.data;
				}
			})
				if(infoList.length == 0){
					swal("提示", "已称重的车辆已经全部添加", "");
					$("#plateNumber").val('-1');
				}
				for (var i = 0; i < infoList.length; i++) {
					 $("#carOwner").val(infoList[0].carOwner);
					 $("#carOwnerTelephone").val(infoList[0].carOwnerTelephone);
					 
					 if(infoList[i].outTypeMark){
						type = 1;
					 }else{
						type = 0;
					 }
					var result="";
					var isDiaoBo // 判断是否调拨
					var isSerialType // 判断类型
					isDiaoBo = 0; //不是调拨
						 if(infoList[i].outType == 0){
							 isSerialType = "正常"
						 }else if(infoList[i].outType == 2){
							 isSerialType = "退货";
						 }else if(infoList[i].outType == 3){
							 isSerialType = "空发"
						 }else if(infoList[i].outType == 4){
							 isSerialType = "兑换"
						 }else if(infoList[i].outType == 5){
							 isSerialType = "兑换调拨"
							 isDiaoBo = 1; //是调拨
						 }else if(infoList[i].outType == 99){
							 isSerialType = "未称重 "
							 isDiaoBo = 1; //是调拨
						 }else if(infoList[i].outType == 1){
							 isSerialType = "调拨"
							 isDiaoBo = 1; //是调拨
						}
					
					 if(infoList[i].transportBalance == 1){
							infoList[i].transports = "已自提";
							infoList[i].distance = 0;
					 }
					 
					 if(infoList[i].outType != 1 && infoList[i].outType != 5 && infoList[i].outType != 99){
						 result +=["<tr name=\"rowNumber\" style=\"border-bottom: 1px solid #044599;\">",
							 " 						 	<td style=\"text-align:center;\">"+(i+1)+"</td>",
							 "						    <td style=\"text-align:center;\">",
							 "								<input  id=\"serialID"+i+"\" name=\"serialID"+i+"\"  style=\"background:#CCCCCC\" readonly=\"readonly\"  value=\""+infoList[i].SerialId+"\" />",
							 "                              <input type='hidden' id=\"type"+i+"\" name=\"type"+i+"\" value=\""+type+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "                              <input type='hidden' id=\"isD"+i+"\" name=\"isD"+i+"\" value=\""+isDiaoBo+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "                          	<input type='hidden' id=\"id"+i+"\" name=\"id"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "                              <input type='hidden' id=\"dispatchOutWarehouseId"+i+"\" name=\"dispatchOutWarehouseId"+i+"\" value=\""+infoList[i].id+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "							</td>",
							 "						    <td  style=\"text-align:center;\">",
							 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"transportParty"+i+"\" name=\"transportParty"+i+"\" readonly=\"readonly\" value=\""+user.name+"\" class=\"ui_input_txt01\" />",
							 "						    </td>",
							 "						    <td  style=\"text-align:center;\">",
							 "                                	 	<input  id=\"materielNameId"+i+"\" type='hidden' name=\"materielNameId"+i+"\" value=\""+infoList[i].materielNameId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "                                	 	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"materielName"+i+"\" name=\"materielName"+i+"\" value=\""+infoList[i].materielName+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "							</td>",
							 "						    <td  style=\"text-align:center;\">",
							 "                                	 	<input  id=\"materielModelId"+i+"\" type='hidden' name=\"materielModelId"+i+"\" value=\""+infoList[i].materielModelId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "                                	 	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"materielModel"+i+"\" name=\"materielModel"+i+"\" value=\""+infoList[i].materielModel+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "						    </td>",
							 "						    <td  style=\"text-align:center;\">",
							 "								<input type=\"text\" onchange=\"trimStr(this);\"  id=\"company"+i+"\" name=\"company"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"50\" />",
							 "							</td>",
							 "						    <td  colspan=\"2\" style=\"text-align:center;\">",
							 "                            	<table>",
							 "                                	 <tr >",
							 "                                	 	<td  style=\"text-align:center;border-right:1px solid #000;\">",
							 "                                	 		<input type=\"text\" id=\"weigh"+i+"\" name=\"weigh"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\"   value=\""+infoList[i].suttle+"\" class=\"ui_input_txt01\"/>",
							 "										</td>",
							 "                                	 	<td  style=\"text-align:center;\">",
							 "                                	 		<input type=\"text\" id=\"collectWeigh"+i+"\"  onchange=\"changecollectWeigh(this);\"   value=\""+infoList[i].amount+"\" onkeyup=\"keepNumOrDecimal(this);\" name=\"collectWeigh"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                	 	</td>",
							 "                                	 </tr>",
							 "                                </table>",
							 "                            </td>",
							 "						    <td  style=\"text-align:center;\">",
							 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"endAddress"+i+"\" value=\""+infoList[i].transports+"\" onchange=\"changeEndAddress(this);\"  name=\"endAddress"+i+"\" class=\"ui_select01\"/>",
							 "							</td>",
							 "							<td style=\"text-align:center;\">",
							 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"distance"+i+"\" value=\""+infoList[i].distance+"\"  name=\"distance"+i+"\" class=\"ui_select01\"/>",
							 "                            </td>",
							 "							<td  style=\"text-align:center;\">",
							 "								<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"freightPrice"+i+"\" name=\"freightPrice"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                            </td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"freightMoney"+i+"\" name=\"freightMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "							</td>",
							 "						    <td  style=\"text-align:center;\">",
							 "						    	<input type=\"text\" id=\"escortDays"+i+"\" onkeyup=\"day(this);\" onchange=\"changeYT(this);\" name=\"escortDays"+i+"\" value=\"\" maxlength=\"8\" class=\"ui_input_txt01\"/>",
							 "							</td>",
							 "						    <td  style=\"text-align:center;\">",
							 "						    	<input type=\"text\" id=\"escortPrice"+i+"\" onkeyup=\"checkPrice(this);\" onchange=\"changeYD(this);\" name=\"escortPrice"+i+"\" value=\"\" maxlength=\"8\" class=\"ui_input_txt01\"/>",
							 "							</td>",
							 "						    <td  style=\"text-align:center;\">",
							 "						    	<input type=\"text\" readonly=\"readonly\" id=\"escortMoney"+i+"\" name=\"escortMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"18\"/>",
							 "                            </td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"RiseLoss"+i+"\" name=\"RiseLoss"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "						    </td>",
							 "						    <td  style=\"text-align:center;\">",
							 "                                <select id=\"saleType"+i+"\"  name=\"saleType"+i+"\" class=\"ui_select01\">",
							 "								</select>",
							 "                            </td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" id=\"remarks"+i+"\" name=\"remarks"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "						    </td>",
							 "						    <td  style=\"text-align:center;\">",
							 "						    	<input type=\"file\" id=\"myBlogImage"+i+"\" name=\"myfiles\"/>",
							 "						    </td>",
							 "						    <td  style=\"text-align:center;\">",
							 "						    	<span id=\"isSerialType"+i+"\" name=\"isSerialType\">"+isSerialType,
							 "						    	</span>",
							 "						    </td>",
							 " 						</tr>"].join("");
					 }else{
						 result += [" <tr name=\"rowNumber\" style=\"border-bottom: 1px solid #044599;\">",
							 " 						 	<td  style=\"text-align:center;\">"+(i+1)+"</td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    <input type=\"hidden\" id=\"type\" name=\"type\" value=\"\" />",
							 "						    <input  id=\"serialID"+i+"\" name=\"serialID"+i+"\"  style=\"background:#CCCCCC\" readonly=\"readonly\"  value=\""+infoList[i].SerialId+"\" />",
							 "                          <input type='hidden' id=\"type"+i+"\" name=\"type"+i+"\" value=\""+type+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "                          <input type='hidden' id=\"isD"+i+"\" name=\"isD"+i+"\" value=\""+isDiaoBo+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "                          <input type='hidden' id=\"id"+i+"\" name=\"id"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "                          <input type='hidden' id=\"dispatchOutWarehouseId"+i+"\" name=\"dispatchOutWarehouseId"+i+"\" value=\""+infoList[i].id+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "						    </td>",
							 "						    <td  style=\"text-align:center;\">",
							 "						    <input type=\"text\" style=\"background:#CCCCCC\" id=\"transportParty"+i+"\" name=\"transportParty"+i+"\" readonly=\"readonly\" value=\""+user.name+"\" class=\"ui_input_txt01\" />",
							 "						    </td>",
							 "						    <td  style=\"text-align:center;\">",
							 "                                	 	<input  id=\"materielNameId"+i+"\" type='hidden' name=\"materielNameId"+i+"\" value=\""+infoList[i].materielNameId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "                                	 	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"materielName"+i+"\" name=\"materielName"+i+"\" value=\""+infoList[i].materielName+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "							</td>",
							 "						    <td  style=\"text-align:center;\">",
							 "                                	 	<input  id=\"materielModelId"+i+"\" type='hidden' name=\"materielModelId"+i+"\" value=\""+infoList[i].materielModelId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "                                	 	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"materielModel"+i+"\" name=\"materielModel"+i+"\" value=\""+infoList[i].materielModel+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "						    </td>",
							 "						    <td style=\"text-align:center;\"><input onchange=\"trimStr(this);\"  type=\"text\" id=\"company"+i+"\" name=\"company"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"50\" /></td>",
							 "						   	<td  colspan=\"2\" style=\"text-align:center;\">",
							 "						   	<table>",
							 "                                	<tr>",
							 "                                	<td style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
							 "                                	 <input type=\"text\" id=\"weigh"+i+"\" name=\"weigh"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\"  value=\""+infoList[i].suttle+"\" class=\"ui_input_txt01\"/>",
							 "									</td>",
							 "									<td style=\"text-align:center;border-bottom:1px solid #000\">",
							 "										<input type=\"text\" id=\"collectWeigh"+i+"\" value=\""+infoList[i].amount+"\" onkeyup=\"keepNumOrDecimal(this);\" name=\"collectWeigh"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "									</td>",
							 "									</tr>",
							 "										",
							 "                                     <tr>",
							 "                                     	<td  style=\"text-align:center;background-color:#044599;color:#fff;border-bottom:1px solid #000;border-right:1px solid #000\">调拨重量</td>",
							 "                                    	<td style=\"text-align:center;background-color:#044599;color:#fff;border-bottom:1px solid #000;border-right:1px solid #000\">调拨结算重量</td>",
							 "                                     </tr>",
							 "                                     <tr >",
							 "                                     	<td style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
							 "                                       	<input type=\"text\" id=\"allotWeight"+i+"\" value=\""+infoList[i].allotWeight+"\" name=\"allotWeight"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\"  onkeyup=\"keepNumOrDecimal(this);\" value=\""+infoList[i].allotWeight+"\" class=\"ui_input_txt01\" />",
							 "                                        </td>",
							 "                                       <td  style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
							 "                                       	<input type=\"text\" id=\"SettlementWeight"+i+"\" name=\"SettlementWeight"+i+"\" onkeyup=\"keepNumOrDecimal(this);\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                       </td>",
							 "                                       </tr>",
							 "                            </table>",
							 "                            </td>",
							 "                            </td>",
							 "						    <td  style=\"text-align:center;\">",
							 "								<table width=\"150\" >",
							 "                                	 <tr>",
							 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
							 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\"  id=\"endAddress"+i+"\" onchange=\"changeEndAddress(this);\" value=\""+infoList[i].transports+"\"  name=\"endAddress"+i+"\" class=\"ui_select01\">",
							 "                                	 	</td>",
							 "                                	 </tr>",
							 "                                     <tr>",
							 "                                     	<td  style=\"text-align:center;background-color:#044599;color:#fff;border-bottom:1px solid #000\">止运地</td>",
							 "                                     </tr>",
							 "                                     <tr><td style=\"text-align:center;\">",
							 "						    	<select id=\"DendAddress"+i+"\" onchange=\"changeDEndAddress(this);\" name=\"DendAddress"+i+"\" class=\"ui_select01\">",
							 "										<option value=\"-1\">请选择</option>",
							 "										</select>",
							 "                                     </td>",
							 "                                     </tr>",
							 "                                </table>",
							 "							</td>",
							 "						    <td  style=\"text-align:center;\">",
							 "								<table width=\"150\" >",
							 "                                	 <tr>",
							 "                                	 	<td  style=\"text-align:center;border-bottom:1px solid #000\">",
							 "						    	<select id=\"distance"+i+"\" name=\"distance"+i+"\" class=\"ui_select01\">",
							 "										</select>",
							 "                                	 	</td>",
							 "                                	 </tr>",
							 "                        <td  style=\"text-align:center;border-bottom:1px solid #000\">",
							 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"distance"+i+"\" value=\""+infoList[i].distance+"\" name=\"distance"+i+"\" class=\"ui_select01\"/>",
							 "                                	 </tr>",
							 "                                     <tr>",
							 "                                     	<td  style=\"text-align:center;background-color:#044599;color:#fff;border-bottom:1px solid #000\">运距</td>",
							 "                                     </tr>",
							 "                                     <tr>",
							 "                                     	<td style=\"text-align:center;\">",
							 "						    	<select id=\"Ddistance"+i+"\" name=\"Ddistance"+i+"\" class=\"ui_select01\">",
							 "										</select>",
							 "                                       </td>",
							 "                                     </tr>",
							 "                                </table>",
							 "							</td>",
							 "						    <td  style=\"text-align:center;\">",
							 "								<table>",
							 "                                	 <tr>",
							 "                                	 	<td  style=\"text-align:center;border-bottom:1px solid #000\">",
							 "                                	 		<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"freightPrice"+i+"\" name=\"freightPrice"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                	 	</td>",
							 "                                	 </tr>",
							 "                                     <tr>",
							 "                                     	<td style=\"text-align:center;background-color:#044599;color:#fff;border-bottom:1px solid #000\">超出运输单价</td>",
							 "                                     </tr>",
							 "                                     <tr>",
							 "                                     	<td style=\"text-align:center;\">",
							 "                                       		<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"DfreightPrice"+i+"\" name=\"DfreightPrice"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                        </td>",
							 "                                     </tr>",
							 "                                </table>",
							 "							</td>",
							 "						    <td  style=\"text-align:center;\">",
							 "								<table>",
							 "                                	 <tr>",
							 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
							 "                                	 		<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"freightMoney"+i+"\" name=\"freightMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                	 	</td>",
							 "                                	 </tr>",
							 "                                     <tr>",
							 "                                     	<td style=\"text-align:center;background-color:#044599;color:#fff;border-bottom:1px solid #000\">超出运费总额</td>",
							 "                                     </tr>",
							 "                                     <tr>",
							 "                                     	<td style=\"text-align:center;\">",
							 "                                       		<input type=\"text\"onkeyup=\"keepNumOrDecimal(this);\" id=\"DfreightMoney"+i+"\" name=\"DfreightMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                       </td>",
							 "                                     </tr>",
							 "                                </table>",
							 "							</td>",
							 "						    <td  style=\"text-align:center;\"><table>",
							 "                                	 <tr>",
							 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
							 "                                	 		<input type=\"text\"  id=\"escortDays"+i+"\" onkeyup=\"day(this);\" onchange=\"changeYT(this);\" name=\"escortDays"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                	 	</td>",
							 "                                	 </tr>",
							 "                                     <tr>",
							 "                                     	<td  style=\"text-align:center;background-color:#044599;color:#fff;border-bottom:1px solid #000\">押车天数</td>",
							 "                                     </tr>",
							 "                                     <tr>",
							 "                                     	<td  style=\"text-align:center;\">",
							 "                                       		<input type=\"text\" id=\"DescortDays"+i+"\" onkeyup=\"day(this);\" onchange=\"changeDYT(this);\" name=\"DescortDays"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                       </td",
							 "                                      ></tr>",
							 "                                </table>",
							 "                             </td>",
							 "                             <td  style=\"text-align:center;\"><table>",
							 "                                	 <tr>",
							 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
							 "                                	 		<input type=\"text\" id=\"escortPrice"+i+"\" onkeyup=\"checkPrice(this);\" onchange=\"changeYD(this);\" name=\"escortPrice"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                	 	</td>",
							 "                                	 </tr>",
							 "                                     <tr>",
							 "                                     	<td  style=\"text-align:center;background-color:#044599;color:#fff;border-bottom:1px solid #000\">押车单价</td>",
							 "                                     </tr>",
							 "                                     <tr>",
							 "                                     	<td style=\"text-align:center;\">",
							 "                                       		<input type=\"text\" id=\"DescortPrice"+i+"\" onkeyup=\"checkPrice(this);\" onchange=\"changeDYD(this);\" name=\"DescortPrice"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                       </td>",
							 "                                     </tr>",
							 "                                </table>",
							 "                             </td>",
							 "						    <td  style=\"text-align:center;\"><table>",
							 "                                	 <tr>",
							 "                                	 	<td  style=\"text-align:center;border-bottom:1px solid #000\">",
							 "                                	 		<input type=\"text\" readonly=\"readonly\" id=\"escortMoney"+i+"\" name=\"escortMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                	 	</td>",
							 "                                	 </tr>",
							 "                                     <tr>",
							 "                                     	<td  style=\"text-align:center;background-color:#044599;color:#fff;border-bottom:1px solid #000\">押车金额</td>",
							 "                                     </tr>",
							 "                                     <tr >",
							 "                                     	<td style=\"text-align:center;\">",
							 "                                       		<input type=\"text\" readonly=\"readonly\" id=\"DescortMoney"+i+"\" name=\"DescortMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                       </td>",
							 "                                     </tr>",
							 "                                </table>",
							 "                            </td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"RiseLoss"+i+"\" name=\"RiseLoss"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "						    </td>",
							 "                             <td  style=\"text-align:center;\">",
							 "						    	<select id=\"saleType"+i+"\"  name=\"saleType"+i+"\" class=\"ui_select01\">",
							 "										</select>",
							 "						    </td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" id=\"remarks"+i+"\" name=\"remarks"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "						    </td>",
							 "						    <td style=\"text-align:center;\"><input type=\"file\" id=\"myBlogImage"+i+"\" name=\"myfiles\"/></td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<span id=\"isSerialType"+i+"\" name=\"isSerialType\">"+isSerialType,
							 "						    	</span>",
							 "						    </td>",
							 " 						</tr>"].join("");
					 }
				}//for循环
				
	    	    $("#transportationList tbody").append(result);
	    	    var list = $("#transportationList")
	    	    $.ajax({
	    			type: "post",
	    			url: baseUrl+'/TransportationExportmeasure/getSaleType.action',
	    			async:false,
	    			dataType: "json",
	    			success: function (data) {
	    				for (var i = 0; i < list[0].rows.length; i++) {
	    					$.each(data.data, function (k, v) {
	    						$('<option></option>').attr("value", v.code).html(v.content).appendTo($("#"+("saleType"+i)));
	    					})
	    				}
	    			}
	    		});
	    	    
	    	    
	    	    for (var i = 0; i < infoList.length; i++) {
					 var RiseLossSum = $("#collectWeigh"+i).val() - $("#weigh"+ i).val() ;
					 $("#RiseLoss"+i).val(RiseLossSum);
		    		 if(infoList[i].transportBalance == 1){
		    			$("#"+("freightPrice"+i)).attr("disabled",true)
		    			$("#"+("freightPrice"+i)).val("已自提")
		    			$("#"+("freightMoney"+i)).attr("disabled",true)
		    			$("#"+("freightPrice"+i)).css("background","#CCCCCC")
		    			$("#"+("freightMoney"+i)).css("background","#CCCCCC")
		    			$("#"+("freightMoney"+i)).val("已自提")
		    		 }
		    		 if(infoList[i].outType == 1){
		    				if(infoList[i].nowTransportBalance == 1){
		    					$("#"+("DendAddress"+i)).attr("disabled",true)
		    					$("#"+("DendAddress"+i)).append("<option selected='selected' value='-2'>已自提</option>");
		    					$("#"+("Ddistance"+i)).attr("disabled",true)
		    					$("#"+("Ddistance"+i)).append("<option value=''>0</option>");
		    					
		    					$("#"+("DendAddress"+i)).css("background","#CCCCCC")
		    					$("#"+("Ddistance"+i)).css("background","#CCCCCC")
		    					
		    					$("#"+("DfreightPrice"+i)).attr("disabled",true)
		    					$("#"+("DfreightPrice"+i)).val("已自提")
		    					$("#"+("DfreightPrice"+i)).css("background","#CCCCCC")
		    					$("#"+("DfreightMoney"+i)).attr("disabled",true)
		    					$("#"+("DfreightMoney"+i)).css("background","#CCCCCC")
		    					$("#"+("DfreightMoney"+i)).val("已自提")
		    				}
		    			}
		    	    var id = infoList[i].uid
		    	    $.ajax({
	    	    		type: "post",
	    	    		url: baseUrl+'/TransportationExportmeasure/getCustomertransportsById.action',
	    	    		data:{id:id},
	    	    		async:false,
	    	    		dataType: "json",
	    	    		success: function (data) {
		    					$.each(data.data, function (k, v) {
		    						$('<option></option>').attr("value", v.Id).html(v.Transports).appendTo($("#"+("endAddress"+i)));
		    						$('<option></option>').attr("value", v.Id).html(v.Transports).appendTo($("#"+("DendAddress"+i)));
		    					})
	    	    		}
	    	    	});
		    	  }
	}
}

function add(){
	
	var isSuccess;
	var message;
	$("#plateNumber").removeAttr("disabled")
	var rowlenth = $("tr[name='rowNumber']").length
	var data = formToJson($("#submitForm"));
	data.billNumber=$("#billNumber").val();
	var yushuList; //保存到运输单表集合
	var weiCz; //保存到未称重表集合
	if($("#plateNumber").val() == -1){
		swal("操作失败", "请输入车牌号", "error");
		return;
	}
	var plateNumber = $("#plateNumber").find("option:selected").text();
	var carOwner = $("#carOwner").val();
	var carOwnerTelephone = $("#carOwnerTelephone").val();
	//验证
	for (var i = 0; i < rowlenth; i++) {
		$("#"+("endAddress"+i)).removeAttr("disabled")
		$("#"+("distance"+i)).removeAttr("disabled")
		var serialID = $("#"+("serialID"+i)).val();
		var id =  $("#"+("id"+i)).val();
		var dispatchOutWarehouseId = $("#"+("dispatchOutWarehouseId"+i)).val();
		var type = $("#"+("type"+i)).val();
		var isD = $("#"+("isD"+i)).val();
		var transportParty = $("#"+("transportParty"+i)).val();
		var materielNameId = $("#"+("materielNameId"+i)).val();
		var materielModelId = $("#"+("materielModelId"+i)).val();
		var company = $("#"+("company"+i)).val();
		var weigh = $("#"+("weigh"+i)).val();
		var collectWeigh = $("#"+("collectWeigh"+i)).val();
		var endAddress = $("#"+("endAddress"+i)).find("option:selected").text();
		var distance = $("#"+("distance"+i)).find("option:selected").text();
		var freightPrice = $("#"+("freightPrice"+i)).val();
		var freightMoney = $("#"+("freightMoney"+i)).val();
		var escortDays = $("#"+("escortDays"+i)).val();
		var escortPrice = $("#"+("escortPrice"+i)).val();
		var escortMoney = $("#"+("escortMoney"+i)).val();
		var saleType = $("#"+("saleType"+i)).val();
		var remarks = $("#"+("remarks"+i)).val();
		
		var isAdd = true;
		$.ajax({
			type: "post",
			url: baseUrl+'/TranSportList/getEXById.action',
			async:false,
			data: {"serialID":serialID},
			dataType: "json",
			success: function (data) {
				var Exlist = data.data;
				if (Exlist.length==0){
					isAdd = false;
				}
			}
		})
		if(isAdd == false){
			swal("操作失败", "第"+(i+1)+"条出库单已在别处删除,不可添加", "info");
			return;
		}
		if(company==""){
			swal("操作失败", "采购单位不能为空", "error");
			return;
		}
		if(isD!=null){
			if(parseFloat(collectWeigh)>parseFloat(weigh)){
				swal("操作失败", "第"+(i+1)+"条结算重量大于出厂重量", "error");
				return;
			}
			if(collectWeigh==""){
				swal("操作失败", "第"+(i+1)+"条结算重量不能为空", "error");
				return;
			}
			if($("#"+("endAddress"+i)).val()==-1 ||$("#"+("endAddress"+i)).val()==""){
				swal("操作失败", "第"+(i+1)+"请选择止运地点", "error");
				return;
			}
		}
		if(freightPrice == "已自提"){
			freightPrice = 0;
			freightMoney = 0;
		}else{
			if(freightPrice==""){
				swal("操作失败", "第"+(i+1)+"条运费单价不能为空", "error");
				return;
			}
			if(freightMoney==""){
				swal("操作失败", "第"+(i+1)+"条运费总额不能为空", "error");
				return;
			}
			if(parseFloat(freightMoney)<parseFloat(freightPrice)){
				swal("操作失败", "第"+(i+1)+"条运费金额小于单价", "error");
				return;
			}
		}
		if(escortDays==""){
			swal("操作失败", "第"+(i+1)+"条押车天数不能为空", "error");
			return;
		}
		if(escortPrice==""){
			swal("操作失败", "第"+(i+1)+"条押车单价不能为空", "error");
			return;
		}
		if(escortMoney==""){
			swal("操作失败", "第"+(i+1)+"条押车金额不能为空", "error");
			return;
		}
		if(isD==1){
			$("#"+("DendAddress"+i)).removeAttr("disabled");
			$("#"+("Ddistance"+i)).removeAttr("disabled");
			var allotWeight = $("#"+("allotWeight"+i)).val();
			var SettlementWeight = $("#"+("SettlementWeight"+i)).val();
			var DEndAddress = $("#"+("DendAddress"+i)).find("option:selected").text();
			var Ddistance = $("#"+("Ddistance"+i)).find("option:selected").text();
			var DfreightPrice = $("#"+("DfreightPrice"+i)).val();
			var DfreightMoney = $("#"+("DfreightMoney"+i)).val();
			var DescortDays = $("#"+("DescortDays"+i)).val();
			var DescortPrice = $("#"+("DescortPrice"+i)).val();
			var DescortMoney = $("#"+("DescortMoney"+i)).val();
			if(allotWeight==""){
				swal("操作失败", "第"+(i+1)+"条调拨重量不能为空", "error");
				return;
			}
			if(SettlementWeight==""){
				swal("操作失败", "第"+(i+1)+"条调拨结算重量不能为空", "error");
				return;
			}
			if($("#"+("DendAddress"+i)).val()==-1 ||$("#"+("DendAddress"+i)).val()==""){
				swal("操作失败", "请选择第"+(i+1)+"条调拨止运地点", "error");
				return;
			}
			if(DfreightPrice == "已自提"){
				DfreightPrice = 0;
				DfreightMoney = 0;
			}else{
				if(DfreightPrice==""){
					swal("操作失败", "第"+(i+1)+"条调拨运费单价不能为空", "error");
					return;
				}
				if(DfreightMoney==""){
					swal("操作失败", "第"+(i+1)+"条调拨运费金额不能为空", "error");
					return;
				}
				if(parseFloat(DfreightMoney)<parseFloat(DfreightPrice)){
					swal("操作失败", "第"+(i+1)+"条调拨运费金额不能小于单价", "error");
					return;
				}
			}
			if(DescortDays==""){
				swal("操作失败", "第"+(i+1)+"条调拨押车天数不能为空", "error");
				return;
			}
			if(DescortPrice==""){
				swal("操作失败", "第"+(i+1)+"条调拨押车单价不能为空", "error");
				return;
			}
			if(DescortMoney==""){
				swal("操作失败", "第"+(i+1)+"条调拨押车金额不能为空", "error");
				return;
			}
		}
	}
	
	for (var i = 0; i < rowlenth; i++) {
		var serialID = $("#"+("serialID"+i)).val();
		var id =  $("#"+("id"+i)).val();
		var dispatchOutWarehouseId = $("#"+("dispatchOutWarehouseId"+i)).val();
		var type = $("#"+("type"+i)).val();
		var isD = $("#"+("isD"+i)).val();
		var transportParty = $("#"+("transportParty"+i)).val();
		var materielNameId = $("#"+("materielNameId"+i)).val();
		var materielModelId = $("#"+("materielModelId"+i)).val();
		var company = $("#"+("company"+i)).val();
		var weigh = $("#"+("weigh"+i)).val();
		var collectWeigh = $("#"+("collectWeigh"+i)).val();
		var endAddress = $("#"+("endAddress"+i)).val();
		var distance = $("#"+("distance"+i)).val();
		var freightPrice = $("#"+("freightPrice"+i)).val();
		var freightMoney = $("#"+("freightMoney"+i)).val();
		var escortDays = $("#"+("escortDays"+i)).val();
		var escortPrice = $("#"+("escortPrice"+i)).val();
		var escortMoney = $("#"+("escortMoney"+i)).val();
		var saleType = $("#"+("saleType"+i)).val();
		var remarks = $("#"+("remarks"+i)).val();
		var myBlogImage = "myBlogImage"+i;
		var myBlogImageId = "#myBlogImage"+i;
		if(freightPrice == "已自提"){
			freightPrice = 0;
			freightMoney = 0;
		}
		var fileNames ="";
		var fileRoutes = "";
		var fileserialID = "";
		
		yushuList = {
				"dispatchOutWarehouseId":dispatchOutWarehouseId,
				"id":id,
				"billNumber":data.billNumber,
				"plateNumber":plateNumber,
				"serialID":serialID,
				"type":type,
				"carOwner":carOwner,
				"carOwnerTelephone":carOwnerTelephone,
				"transportParty":transportParty,
				"materielId":materielNameId,
				"model":materielModelId,
				"company":company,
				"weigh":weigh,
				"collectWeigh":collectWeigh,
				//"startAddress":startAddress,
				"endAddress":endAddress,
				"settlementKilometre":distance,
				"freightPrice":freightPrice,
				"freightMoney":freightMoney,
				"escortDays":escortDays,
				"escortPrice":escortPrice,
				"escortMoney":escortMoney,
				"saleType":saleType,
				"remarks":remarks
		};
		if(isD==1){
			var allotWeight = $("#"+("allotWeight"+i)).val();
			var SettlementWeight = $("#"+("SettlementWeight"+i)).val();
			//var DStartAddress = $("#"+("DStartAddress"+i)).val();
			//var DStartAddress = $("#"+("DStartAddress"+i)).find("option:selected").text();
			var DEndAddress = $("#"+("DendAddress"+i)).find("option:selected").text();
			var Ddistance = $("#"+("Ddistance"+i)).find("option:selected").text();
			var DfreightPrice = $("#"+("DfreightPrice"+i)).val();
			var DfreightMoney = $("#"+("DfreightMoney"+i)).val();
			var DescortDays = $("#"+("DescortDays"+i)).val();
			var DescortPrice = $("#"+("DescortPrice"+i)).val();
			var DescortMoney = $("#"+("DescortMoney"+i)).val();
			if(DfreightPrice == "已自提"){
				DfreightPrice = 0;
				DfreightMoney = 0;
			}
			weiCz = {
					"dispatchOutWarehouseId":dispatchOutWarehouseId,
					"billNumber":data.billNumber,
					"serialID":serialID,
					"plateNumber":plateNumber,
					"allotWeight":allotWeight,
					"settlementWeight":SettlementWeight,
					//"startAddress":DStartAddress,
					"endAddress":DEndAddress,
					"distance":Ddistance,
					"freightPrice":DfreightPrice,
					"freightMoney":DfreightMoney,
					"escortDays":DescortDays,
					"escortPrice":DescortPrice,
					"escortMoney":DescortMoney
			}
			
			$.ajaxFileUpload({  
		        url:'../../Purchase/upload.action',
		        secureuri:false,                           //是否启用安全提交,默认为false   
		        fileElementId:'myBlogImage',   //文件选择框的id属性  
		        data:{
		        	"transportlistInfo":yushuList,
		        	"noweighoutwarehouseInfo":weiCz,
		        	"serialID":serialID,
		        	"flag":flag
		        	},		
		        dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
		        success:function(data){            //服务器响应成功时的处理函数  
					if(data != null){
						if(data.code == "success"){
							swal({
				 				title: data.message,
				 				text: "",
				 				type: data.code,
				 				confirmButtonText: '确定',
				 				cancelButtonFont: '微软雅黑',
				 			},
				 			function (){
				 				updateContractInfoPrefix("CGHT");
				 				location.href='purchase.html';
				 			});
						}else{
							swal("操作失败", "失败", "error");
							return;
						}
					}
		        },  
		        error:function(data, status, e){ //服务器响应失败时的处理函数  
		        	swal("操作失败", "未知错误", "error");
					return;
		        }  
		        });
		}
		
		
		}
}