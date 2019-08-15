var baseUrl = "";
var DBillNumber = "";
var MaterielinfoList; //所有物料信息
var infoList;
var type;
var user;
var billNumber;
$(function(){
	user = getUserInfo();
	DBillNumber = getUrlParam("BillNumber");
	flag = getUrlParam("flag");
	baseUrl = getContextPath();
	
	//html 出库入库单 车牌号
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportListStoragemeasure/getPlate.action',
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
		url: baseUrl+'/TranSportListStoragemeasure/getExOrStPlateNumber.action',
		async:false,
		data:{plateNumber:plateNumber},
		dataType: "json",
		success: function (data) {
			infoList = data.data;
			if(infoList.length == 0){
				swal("提示", "已称重车辆已经全部添加", "");
				$("#plateNumber").val('-1');
			}
			//$("#transportationList tr:not(:first)").empty("")
			var result="";
			var isDiaoBo // 判断是否调拨
			var isSerialType // 判断类型
			 for (var i = 0; i < infoList.length; i++) {
				 $("#carOwner").val(infoList[0].carOwner);
				 $("#carOwnerTelephone").val(infoList[0].carOwnerTelephone);
				 if(infoList[i].outTypeMark!=null){
					 if(infoList[i].outTypeMark){
						 type = 1;
					 }else{
						 type = 0;
					 }
				 }else{
					 type = 2;
				 }
				 if(infoList[i].outType != undefined){
					 if(infoList[i].outType != 1){
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
						 }
					 }else{
						 isDiaoBo = 1; //是调拨
						 isSerialType = "调拨"
					 }
				 }else{
					 isDiaoBo = null;
					 isSerialType = "入库"
						 if(infoList[i].enterTypeMark == 2){
							 isSerialType = "来料加工"
						 }else{
							 infoList[i].client =  infoList[i].supplierName
						 }
				 }
				 if(infoList[i].transportBalance == 1){
						//infoList[i].startAddress = "已自提";
						infoList[i].transports = "已自提";
						infoList[i].distance = 0;
				 }
				 
				 if(infoList[i].enterTypeMark == 2){
					 isSerialType = "来料加工"
				 }
				 
				 if(infoList[i].amount  == undefined){
					 infoList[i].amount  = 0;
				 }
				 if(infoList[i].factoryTime == undefined){
					 infoList[i].factoryTime = ""
				 }
				 
				 infoList[i].grossWeight = Number(infoList[i].grossWeight / 1000).toFixed(2)
				 infoList[i].Suttle = Number(infoList[i].Suttle / 1000).toFixed(2)
				 
				 if(infoList[i].outType == undefined || (infoList[i].outType != 1 && infoList[i].outType != 5 && infoList[i].outType != 99)){
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
						 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"factoryTime"+i+"\" name=\"factoryTime"+i+"\" readonly=\"readonly\" value=\""+infoList[i].factoryTime+"\" class=\"ui_input_txt01\" />",
						 "						    </td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"transportParty"+i+"\" name=\"transportParty"+i+"\" readonly=\"readonly\" value=\""+user.name+"\" class=\"ui_input_txt01\" />",
						 "						    </td>",
						 "						    <td  style=\"text-align:center;\">",
						 "                                	 	<input  id=\"materielNameId"+i+"\" type='hidden' name=\"materielNameId"+i+"\" value=\""+infoList[i].materielNameId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "                                	 	<input  id=\"materielModelId"+i+"\" type='hidden' name=\"materielModelId"+i+"\" value=\""+infoList[i].materielModelId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "										<textarea style=\"background:#CCCCCC;resize:none;\" readonly=\"readonly\"  draggable='false' rows='2' cols='20'  name='materielName' id='materielName"+i+"' type='text' maxlength='200'/></td>",
						 "							</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "								<input type=\"text\" onchange=\"trimStr(this);\" style=\"background:#CCCCCC\" readonly=\"readonly\"   id=\"company"+i+"\" name=\"company"+i+"\" value=\""+infoList[i].client+"\" class=\"ui_input_txt01\" maxlength=\"50\" />",
						 "							</td>",
						 "						    <td style=\"text-align:center;\">",
						 "								<input type=\"text\" value=\"\"  name='SettleDate"+i+"' id='SettleDate"+i+"' class=\"ui_input_txt01\"   onfocus=\"WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})\"  class=\"Wdate\"/>",
			  	 		 "							</td>"].join("")
						 if(isDiaoBo == null){
							 result +=[ "						    <td  style=\"text-align:center;\">",
								 "                                	 		<input type=\"text\" id=\"weigh"+i+"\" name=\"weigh"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\"   value=\""+infoList[i].grossWeight+"\" class=\"ui_input_txt01\"/>",
								 "									</td>",
								 "						    <td style=\"text-align:center;\">",
								 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"RiseLoss"+i+"\" name=\"RiseLoss"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
								 "						    </td>",
								 "                                	 <td  style=\"text-align:center;\">",
								 "                                	 		<input type=\"text\" id=\"collectWeigh"+i+"\"  onchange=\"changecollectWeigh(this);\" value=\""+infoList[i].Suttle+"\" name=\"collectWeigh"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
								 "                                	 </td>"].join("")

						 }else{
							 result +=[ "						    <td  colspan=\"2\" style=\"text-align:center;\">",
								 "                            	<table>",
								 "                                	 <tr >",
								 "                                	 	<td  style=\"text-align:center;border-right:1px solid #000;\">",
								 "                                	 		<input type=\"text\" id=\"weigh"+i+"\" name=\"weigh"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\"   value=\""+infoList[i].suttle+"\" class=\"ui_input_txt01\"/>",
								 "										</td>",
								 "                                     	<td style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
								 "                                       	<input type=\"text\" id=\"allotWeight"+i+"\" value=\""+infoList[i].allotWeight+"\" name=\"allotWeight"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\"  onkeyup=\"keepNumOrDecimal(this);\" value=\""+infoList[i].allotWeight+"\" class=\"ui_input_txt01\" />",
								 "                                        </td>",
								 "                                	 </tr>",
								 "                                </table>",
								 "                            </td>",
								 "                                	 	<td  style=\"text-align:center;\">",
								 "                                	 		<input type=\"text\" id=\"collectWeigh"+i+"\"  onchange=\"changecollectWeigh(this);\"   value=\""+ infoList[i].amount +"\" onkeyup=\"keepNumOrDecimal(this);\" name=\"collectWeigh"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
								 "                                	 	</td>",
								 "						    <td style=\"text-align:center;\">",
								 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"RiseLoss"+i+"\" name=\"RiseLoss"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
								 "						    </td>"].join("")
							 
						 }
						 if(isDiaoBo == null){
							 result +=[
								 "						    <td  style=\"text-align:center;\">",
								 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"endAddress"+i+"\" name=\"endAddress"+i+"\" readonly=\"readonly\" value=\"无\" class=\"ui_input_txt01\" />",
								 "							</td>",
								 "							<td style=\"text-align:center;\">",
								 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"distance"+i+"\" name=\"distance"+i+"\" readonly=\"readonly\" value=\"无\" class=\"ui_input_txt01\" />",
								 "                            </td>",
								 "							<td  style=\"text-align:center;\">",
								 "								<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" style=\"background:#CCCCCC\" readonly=\"readonly\" onchange=\"changefreightMoney(this);\" id=\"freightPrice"+i+"\" name=\"freightPrice"+i+"\" value=\"0\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
								 "                            </td>",
								 "						    <td style=\"text-align:center;\">",
								 "						    	<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" style=\"background:#CCCCCC\" readonly=\"readonly\" onchange=\"changefreightMoney(this);\" id=\"freightMoney"+i+"\" name=\"freightMoney"+i+"\" value=\"0\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
								 "							</td>"].join("");
						 }else{
							 result +=[
								 "						    <td  style=\"text-align:center;\">",
								 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"endAddress"+i+"\" value=\""+infoList[i].transports+"\" onchange=\"changeEndAddress(this);\"  name=\"endAddress"+i+"\" class=\"ui_select01\"/>",
								 "							</td>",
								 "							<td style=\"text-align:center;\">",
								 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"distance"+i+"\" value=\""+infoList[i].distance+"\"  name=\"distance"+i+"\" class=\"ui_select01\"/>",
								 "                            </td>",
								 "							<td  style=\"text-align:center;\">",
								 "								<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\"  onchange=\"changefreightMoney(this);\" id=\"freightPrice"+i+"\" name=\"freightPrice"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
								 "                            </td>",
								 "						    <td style=\"text-align:center;\">",
								 "						    	<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" readonly=\"readonly\"  onchange=\"changefreightMoney(this);\" id=\"freightMoney"+i+"\" name=\"freightMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
								 "							</td>"].join("");
						 }
					 	
					 	result +=[
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" id=\"escortDays"+i+"\" onkeyup=\"day(this);\" onchange=\"changeYT(this);\" name=\"escortDays"+i+"\" value=\"\" maxlength=\"8\" class=\"ui_input_txt01\"/>",
						 "							</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" id=\"escortPrice"+i+"\" onkeyup=\"checkPrice(this);\" onchange=\"changeYD(this);\" name=\"escortPrice"+i+"\" value=\"\" maxlength=\"8\" class=\"ui_input_txt01\"/>",
						 "							</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" readonly=\"readonly\" id=\"escortMoney"+i+"\" name=\"escortMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"18\"/>",
						 "                            </td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" readonly=\"readonly\"  id=\"sumMoney"+i+"\" name=\"sumMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"18\"/>",
						 "						    </td>",
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
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"factoryTime"+i+"\" name=\"factoryTime"+i+"\" readonly=\"readonly\" value=\""+infoList[i].factoryTime+"\" class=\"ui_input_txt01\" />",
						 "						    </td>",
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
						 "                                	 	<input  id=\"materielModelId"+i+"\" type='hidden' name=\"materielModelId"+i+"\" value=\""+infoList[i].materielModelId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "										<textarea style=\"background:#CCCCCC;resize:none;\" readonly=\"readonly\"  draggable='false' rows='2' cols='20'  name='materielName' id='materielName"+i+"' type='text' maxlength='200'/></td>",
						 "							</td>",
						 "						    <td style=\"text-align:center;\"><input onchange=\"trimStr(this);\" style=\"background:#CCCCCC\" readonly=\"readonly\"   type=\"text\" id=\"company"+i+"\" name=\"company"+i+"\" value=\""+infoList[i].client+"\" class=\"ui_input_txt01\" maxlength=\"50\" /></td>",
						 "						    <td style=\"text-align:center;\">",
						 "								<input type=\"text\" value=\"\"  name='SettleDate"+i+"' id='SettleDate"+i+"' class=\"ui_input_txt01\"   onfocus=\"WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})\"  class=\"Wdate\"/>",
			  	 		 "							</td>",
						 "                                	<td style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
						 "                                	 <input type=\"text\" id=\"weigh"+i+"\" name=\"weigh"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\"  value=\""+infoList[i].suttle+"\" class=\"ui_input_txt01\"/>",
						 "									</td>",
						 "                                     	<td style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
						 "                                       	<input type=\"text\" id=\"allotWeight"+i+"\" value=\""+infoList[i].allotWeight+"\" name=\"allotWeight"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\"  onkeyup=\"keepNumOrDecimal(this);\" value=\""+infoList[i].allotWeight+"\" class=\"ui_input_txt01\" />",
						 "                                        </td>",
						 "						    <td style=\"text-align:center;\">",
						 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"RiseLoss"+i+"\" name=\"RiseLoss"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "						    </td>",
						 "						   	<td  style=\"text-align:center;\">",
						 "						   	<table>",
						 "                                	<tr>",
						 "									<td style=\"text-align:center;border-bottom:1px solid #000\">",
						 "										<input type=\"text\" id=\"collectWeigh"+i+"\" onchange=\"changecollectWeigh(this);\" value=\""+infoList[i].amount+"\" onkeyup=\"keepNumOrDecimal(this);\" name=\"collectWeigh"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "									</td>",
						 "									</tr>",
						 "                                     <tr >",
						 "                                       <td  style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
						 "                                       	<input type=\"text\" id=\"SettlementWeight"+i+"\" onchange=\"changecollectWeigh(this);\"  name=\"SettlementWeight"+i+"\" placeholder=\"调拨结算重量\" onkeyup=\"keepNumOrDecimal(this);\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                       </td>",
						 "                                       </tr>",
						 "                            </table>",
						 "                            </td>"].join("")
					 //止运地是否出库
					 if(isDiaoBo == null){
						 result += [
							 "						    <td  style=\"text-align:center;\">",
							 "								<table width=\"150\" >",
							 "                                	 <tr>",
							 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
							 "						    	<select id=\"endAddress"+i+"\" onchange=\"changeEndAddress(this);\"  name=\"endAddress"+i+"\" class=\"ui_select01\">",
							 "										<option value=\"-1\">请选择</option>",
							 "										</select>",
							 "                                	 	</td>",
							 "                                	 </tr>"].join("");
					 }else{
						 result += [ "                            </td>",
							 "						    <td  style=\"text-align:center;\">",
							 "								<table width=\"150\" >",
							 "                                	 <tr>",
							 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
							 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\"  id=\"endAddress"+i+"\" onchange=\"changeEndAddress(this);\" value=\""+infoList[i].transports+"\"  name=\"endAddress"+i+"\" class=\"ui_select01\">",
							 "                                	 	</td>",
							 "                                	 </tr>"].join("");
					 }
					 
					 result += [
						 "                                     <tr><td style=\"text-align:center;\">",
						 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\"  id=\"DendAddress"+i+"\" onchange=\"changeEndAddress(this);\" value=\""+infoList[i].consigneeAddress+"\"  name=\"DendAddress"+i+"\" class=\"ui_select01\">",
						 "                                     </td>",
						 "                                     </tr>",
						 "                                </table>",
						 "							</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "								<table width=\"150\" >",
						 "                                	 <tr>"].join("");
					 if(isDiaoBo == null){
						 result += [ "                                	 	<td  style=\"text-align:center;border-bottom:1px solid #000\">",
							 "						    	<select id=\"distance"+i+"\" onchange=\"changefreightMoney(this);\"  name=\"distance"+i+"\" class=\"ui_select01\">",
							 "										</select>",
							 "                                	 	</td>",
							 "                                	 </tr>"].join("");
					 }else{
						 result += [ "                        <td  style=\"text-align:center;border-bottom:1px solid #000\">",
							 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"distance"+i+"\"  onchange=\"changefreightMoney(this);\" value=\""+infoList[i].distance+"\" name=\"distance"+i+"\" class=\"ui_select01\"/>",
							 "                                	 </tr>"].join("");
					 }
					 
					 result += [
						 "                                     <tr>",
						 "                                     	<td style=\"text-align:center;\">",
						 "                                	 		<input type=\"text\"  id=\"Ddistance"+i+"\" onchange=\"changeDfreightMoney(this);\" name=\"Ddistance"+i+"\" value=\"\" class=\"ui_input_txt01\" />",
						 "                                       </td>",
						 "                                     </tr>",
						 "                                </table>"].join("");
					 
					 result += [ "							</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "								<table>",
						 "                                	 <tr>",
						 "                                	 	<td  style=\"text-align:center;border-bottom:1px solid #000\">",
						 "                                	 		<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"freightPrice"+i+"\"  onchange=\"changefreightMoney(this);\" name=\"freightPrice"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                	 	</td>",
						 "                                	 </tr>",
						 "                                     <tr>",
						 "                                     	<td style=\"text-align:center;\">",
						 "                                       		<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"DfreightPrice"+i+"\" onchange=\"changeDfreightMoney(this);\"  name=\"DfreightPrice"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                        </td>",
						 "                                     </tr>",
						 "                                </table>",
						 "							</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "								<table>",
						 "                                	 <tr>",
						 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
						 "                                	 		<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" readonly=\"readonly\"  id=\"freightMoney"+i+"\" name=\"freightMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                	 	</td>",
						 "                                	 </tr>",
						 "                                     <tr>",
						 "                                     	<td style=\"text-align:center;\">",
						 "                                       		<input type=\"text\"onkeyup=\"keepNumOrDecimal(this);\" readonly=\"readonly\"  id=\"DfreightMoney"+i+"\" name=\"DfreightMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
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
						 "                                     <tr >",
						 "                                     	<td style=\"text-align:center;\">",
						 "                                       		<input type=\"text\" readonly=\"readonly\" id=\"DescortMoney"+i+"\" name=\"DescortMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                       </td>",
						 "                                     </tr>",
						 "                                </table>",
						 "                            </td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" readonly=\"readonly\" id=\"sumMoney"+i+"\" name=\"sumMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"18\"/>",
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
				 
				}
	    	    $("#transportationList tbody").append(result);
	    	    var list = $("#transportationList")
	    	 for (var i = 0; i < infoList.length; i++) {
					if( $("#"+("SettlementWeight"+i)).val() != undefined && $("#"+("SettlementWeight"+i)).val() != ""){
						var sum = parseFloat($("#"+("SettlementWeight"+i)).val()) + parseFloat($("#"+("collectWeigh"+i)).val()) - parseFloat($("#"+("weigh"+i)).val())
					}else{
						var sum = $("#"+("collectWeigh"+i)).val() - $("#"+("weigh"+i)).val()
					}
					
					if($("#"+("freightMoney"+i)).val()!="" && $("#"+("escortMoney"+i)).val()!=""){
						if($("#"+("DfreightMoney"+i)).val()!="" && $("#"+("DescortMoney"+i)).val()!="" && $("#"+("DescortMoney"+i)).val() != undefined){
							var sum = $("#"+("freightMoney"+i)).val()*1 + $("#"+("escortMoney"+i)).val()*1 + $("#"+("DfreightMoney"+i)).val()*1 + $("#"+("DescortMoney"+i)).val()*1;
							$("#"+("sumMoney"+i)).val(sum)
						}else{
							var sum = $("#"+("freightMoney"+i)).val()*1 + $("#"+("escortMoney"+i)).val()*1;
							$("#"+("sumMoney"+i)).val(sum)
						}
					}
				 $("#RiseLoss"+i).val(Number(sum).toFixed(2));
				 $("#materielName"+i).val(infoList[i].materielName+"/"+infoList[i].materielModel);
	    		 if(infoList[i].transportBalance == 1){
	    			$("#"+("collectWeigh"+i)).css("background","#CCCCCC")
	    			$("#"+("collectWeigh"+i)).attr("readonly","#readonly")
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
	    					$("#"+("DendAddress"+i)).css("background","#CCCCCC")
	    					$("#"+("DendAddress"+i)).val("已自提")
	    					$("#"+("Ddistance"+i)).attr("disabled",true)
	    					$("#"+("Ddistance"+i)).css("background","#CCCCCC")
	    					$("#"+("Ddistance"+i)).val(0);
	    					
	    					$("#"+("DfreightPrice"+i)).attr("disabled",true)
	    					$("#"+("DfreightPrice"+i)).val("已自提")
	    					$("#"+("DfreightPrice"+i)).css("background","#CCCCCC")
	    					$("#"+("DfreightMoney"+i)).attr("disabled",true)
	    					$("#"+("DfreightMoney"+i)).css("background","#CCCCCC")
	    					$("#"+("DfreightMoney"+i)).val("已自提")
	    				}
	    			}
	    	  }
		}
	});
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
		var endAddress = $("#"+("endAddress"+i)).val();
		var distance = $("#"+("distance"+i)).val();
		var freightPrice = $("#"+("freightPrice"+i)).val();
		var freightMoney = $("#"+("freightMoney"+i)).val();
		var escortDays = $("#"+("escortDays"+i)).val();
		var escortPrice = $("#"+("escortPrice"+i)).val();
		var escortMoney = $("#"+("escortMoney"+i)).val();
		var remarks = $("#"+("remarks"+i)).val();
		var settleDate = $("#"+("SettleDate"+i)).val();
		
		var isAdd = true;
		$.ajax({
			type: "post",
			url: baseUrl+'/TranSportListStoragemeasure/getEXById.action',
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
			if(collectWeigh==""){
				swal("操作失败", "第"+(i+1)+"条结算重量不能为空", "error");
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
		if(settleDate == ""){
			swal("操作失败", "第"+(i+1)+"条结算时间不能为空", "error");
			return;
		}
		if(isD==1){
			$("#"+("DendAddress"+i)).removeAttr("disabled");
			$("#"+("Ddistance"+i)).removeAttr("disabled");
			var allotWeight = $("#"+("allotWeight"+i)).val();
			var SettlementWeight = $("#"+("SettlementWeight"+i)).val();
			var DEndAddress = $("#"+("DEndAddress"+i)).val();
			var Ddistance = $("#"+("Ddistance"+i)).val();
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
	//验证后添加
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
		if(isD == "null"){
			var weigh =$("#"+("weigh"+i)).val();
			var collectWeigh = $("#"+("collectWeigh"+i)).val();
			var endAddress = "无";
			var distance = 0;
		}else{
			var weigh = $("#"+("weigh"+i)).val();
			var collectWeigh = $("#"+("collectWeigh"+i)).val();
			var endAddress = $("#"+("endAddress"+i)).val();
			var distance = $("#"+("distance"+i)).val();
		}
		var freightPrice = $("#"+("freightPrice"+i)).val();
		var freightMoney = $("#"+("freightMoney"+i)).val();
		var escortDays = $("#"+("escortDays"+i)).val();
		var escortPrice = $("#"+("escortPrice"+i)).val();
		var escortMoney = $("#"+("escortMoney"+i)).val();
		var remarks = $("#"+("remarks"+i)).val();
		var settleDate = $("#"+("SettleDate")+i).val();
		var myBlogImage = "myBlogImage"+i;
		var myBlogImageId = "#myBlogImage"+i;
		if(freightPrice == "已自提"){
			freightPrice = 0;
			freightMoney = 0;
		}
		var fileNames ="";
		var fileRoutes = "";
		var fileserialID = "";
		if($(myBlogImageId).val()!=""){
			
			$.ajaxFileUpload({  
		        url: baseUrl+'/fileUp/upload.action',  
		        secureuri:false,                           //是否启用安全提交,默认为false   
		        fileElementId:myBlogImage,   //文件选择框的id属性  
		        data:{"serialID":serialID},		
		        dataType:'json',                           //服务器返回的格式,选择json或者xml貌似有问题
		        success:function(fileData){            //服务器响应成功时的处理函数  
		            if(fileData.fileName!="1"){         //1表示失败
		            	fileNames = fileData.fileName;
		            	fileRoutes = fileData.uploadAddress;
		            	fileserialID = fileData.serialID;
		            	var files = {
		        				"serialID": fileserialID,
		        				"fileName":fileNames,
		        				"fileRoute":fileRoutes
		        		};
		            	$.ajax({
		            		type: "post",
		            		url: baseUrl+'/TranSportListStoragemeasure/adduploadfile.action',
		            		data:files,
		            		async:false,
		            		dataType: "json",
		            		success: function (data) {
		            			if(data.code == "success"){
		            				var LowbillNumber=$("#billNumber").val();
		            				if(isSuccess == false){
		            					var serialIDList;
		            					$.ajax({
		            				        type: "post",
		            				        url:  baseUrl+'/TranSportListStoragemeasure/getTranSportList.action',
		            				        async:false,
		            				        data:{"JbillNumber":LowbillNumber},
		            				        dataType: "json",
		            				        success: function (data) {
		            				        	serialIDList = data.data;
		            				        }
		            					});
		            					$.ajax({
		            				        type: "post",
		            				        url:  baseUrl+'/TranSportListStoragemeasure/getUploadfile.action',
		            				        async:false,
		            				        dataType: "json",
		            				        success: function (data) {
		            				        	UploadList = data.data;
		            				        }
		            					});
		            					$.ajax({
		            				        type: "post",
		            				        url:  baseUrl+'/TranSportListStoragemeasure/delTranSportList.action',
		            				        async:false,
		            				        data:{"BillNumber":LowbillNumber},
		            				        dataType: "json",
		            				        success: function (delTranSportData) {
		            				        	var isSuccess = true;
		            				        	var message = "";
		            				        	if(delTranSportData.code == 'success'){
		            				        		for (var i = 0; i < serialIDList.length; i++) {
		            					        		var serialID = serialIDList[i].serialID
		            					        		for (var w = 0; w < UploadList.length; w++) {
		            										if(UploadList[w].serialID == serialID){
		            											var filename = serialIDList[i].fileName
		            							        		$.ajax({
		            							        			type: "post",
		            							        			url: baseUrl+'/TranSportListStoragemeasure/delUploadfile.action',
		            							        			data:{"serialID":serialID},
		            							        			async:false,
		            							        			dataType: "json",
		            							        			success: function (data) {
		            							        				if(data != null){
		            							        					if(data.code == "success"){
		            							        						$.ajax({
		            							        							url : "../../fileUp/fileDelete.action",
		            							        							async : false,
		            							        							dataType:'json',
		            							        							data : {
		            							        								"filename" : filename
		            							        							},
		            							        							type : "post",
		            							        							success : function(_isSuccess) {
		            							        								if(!_isSuccess){
		            							        									isSuccess = false;
		            							        									message = "本地上传文件删除失败"
		            							        								}
		            							        							}
		            							        						});
		            							        					}
		            							        				}
		            							        			}
		            							        		});
		            										}
		            									}
		            								}
		            				        	}
		            				        }
		            					});
		            				}
		    					}else{
		    						isSuccess = false;
		    						message = "图片上传失败003，请重试"
		    						//alert("图片上传失败003，请重试");
		    					}
		            		}
		            	});
		            	
		            }
		        },  
		        error:function(data, status, e){ //服务器响应失败时的处理函数  
		            alert("图片上传失败001 文件不能大于10M，请重试")
		        }  
		        });
		}	
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
				//"saleType":saleType,
				"remarks":remarks,
				"settleDate":settleDate
		};
		$.ajax({
			type: "post",
			url: baseUrl+'/TranSportListStoragemeasure/addTranSportList.action',
			async:false,
			data:yushuList,
			dataType: "json",
			success: function (data) {
				if(data != null){
					if(data.code == "success"){
						isSuccess = true;
					}else{
						isSuccess = false;
						message = "运输单信息错误 添加失败"
					}
				}
			}
		});
		
		//如果是调拨车辆
		if(isD==1){
			var allotWeight = $("#"+("allotWeight"+i)).val();
			var SettlementWeight = $("#"+("SettlementWeight"+i)).val();
			var DEndAddress = $("#"+("DEndAddress"+i)).val();
			var Ddistance = $("#"+("Ddistance"+i)).val();
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
			var params = JSON.stringify(weiCz)
			$.ajax({
				type: "post",
				url: baseUrl+'/TranSportListStoragemeasure/updateNoWeighOut.action',
				async:false,
				data:weiCz,
				dataType: "json",
				success: function (data) {
					if(data != null){
						if(data.code == "success"){
							isSuccess = true;
						}else{
							isSuccess = false;
							message = "未称重信息错误 添加失败"
						}
					}
				}
			});
		}
		
		if(isSuccess == false){
			swal("操作失败", message, "error");
			return;
		}
	}
	
	if(isSuccess == true){
		swal({
			title : "操作成功",
			text : "",
			type : "success",
			confirmButtonText : '确定',
			cancelButtonFont : '微软雅黑',
		}, function() {
			window.parent.$.fancybox.close();
		});
	}
	
}


function findTranSportListByid(BillNumber){
	var fileList;
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportListStoragemeasure/getUploadfile.action',
		async:false,
		dataType: "json",
		success: function (data) {
			fileList = data.data;
			} 
		})
	
	$.ajax({
		type: "post",
		url: baseUrl+'/TranSportListStoragemeasure/getTranSportList.action',
		async:false,
		data:{"JbillNumber":BillNumber},
		dataType: "json",
		success: function (data) {
			infoList = data.data;
			$("#billNumber").val(infoList[0].billNumber);
			$("#plateNumber").find("option").each(function (data) {
			    var $this = $(this);
			    if($this.text() == infoList[0].plateNumber) {
			        $this.attr("selected", true);
			    }
			})
			$("#plateNumber").attr("disabled","disabled");
			$("#plateNumber").attr("background","#CCCCCC");
			$("#carOwner").val(infoList[0].carOwner);
			$("#carOwnerTelephone").val(infoList[0].carOwnerTelephone);
			$("#carOwner").attr("disabled","disabled");
			$("#carOwnerTelephone").attr("disabled","disabled");
			$("#transportationList tbody").empty("")
			var result="";
			 for (var i = 0; i < infoList.length; i++) {
				var fileSName="";
				var isHave = true;
				 for (var m = 0; m < fileList.length; m++) {
					 if(isHave){
						 if(fileList[m].serialID == infoList[i].serialID){
							 fileSName = fileList[m].fileName
							 var fileSNameShow = fileSName.substring(0, fileSName.length - 37);
							 isHave = false;
						 }else{
							 fileSName = "";
						 }
					 }
				}
				 
				 if(infoList[i].outTypeMark!=null){
					 if(infoList[i].outTypeMark){
						 type = 1;
					 }else{
						 type = 0;
					 }
				 }else{
					 type = 2;
				 }
				 if(infoList[i].outType != undefined){
					 if(infoList[i].outType != 1){
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
							 isSerialType = "未称重"
							 isDiaoBo = 1; //是调拨
						 }
					 }else{
						 isDiaoBo = 1; //是调拨
						 isSerialType = "调拨"
					 }
				 }else{
					 isDiaoBo = null;
					 isSerialType = "入库"
						 if(infoList[i].enterTypeMark == 2){
							 isSerialType = "来料加工"
						 }else{
							 infoList[i].client =  infoList[i].supplierName
						 }
				 }
				 
				 if(infoList[i].enterTypeMark == 2){
					 isSerialType = "来料加工"
				 }
				 
				 if(infoList[i].factoryTime == undefined){
					 infoList[i].factoryTime = ""
				 }
				 
				 if(infoList[i].outType == undefined || (infoList[i].outType != 1 && infoList[i].outType != 5 && infoList[i].outType != 99)){
					 result +=["<tr name=\"rowNumber\" style=\"border-bottom: 1px solid #044599;\">",
						 " 						 	<td  style=\"text-align:center;\">"+(i+1)+"</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "								<input  id=\"serialID"+i+"\" name=\"serialID"+i+"\"  style=\"background:#CCCCCC\" readonly=\"readonly\"  value=\""+infoList[i].serialID+"\" />",
						 "                              <input type='hidden' id=\"type"+i+"\" name=\"type"+i+"\" value=\""+type+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "                              <input type='hidden' id=\"isD"+i+"\" name=\"isD"+i+"\" value=\""+isDiaoBo+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "                              <input type='hidden' id=\"id"+i+"\" name=\"id"+i+"\" value=\""+infoList[i].id+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "                              <input type='hidden' id=\"dispatchOutWarehouseId"+i+"\" name=\"dispatchOutWarehouseId"+i+"\" value=\""+infoList[i].dispatchOutWarehouseId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "							</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"factoryTime"+i+"\" name=\"factoryTime"+i+"\" readonly=\"readonly\" value=\""+infoList[i].factoryTime+"\" class=\"ui_input_txt01\" />",
						 "						    </td>",
						 "						    <td style=\"text-align:center;\">",
						 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"transportParty"+i+"\" name=\"transportParty"+i+"\" readonly=\"readonly\" value=\""+user.name+"\" class=\"ui_input_txt01\" />",
						 "						    </td>",
						 "						    <td style=\"text-align:center;\">",
						 "                                	 	<input  id=\"materielNameId"+i+"\" type='hidden' name=\"materielNameId"+i+"\" value=\""+infoList[i].materielId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "                                	 	<input  id=\"materielModelId"+i+"\" type='hidden' name=\"materielModelId"+i+"\" value=\""+infoList[i].model+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "										<textarea style=\"background:#CCCCCC;resize:none;\" readonly=\"readonly\"  draggable='false' rows='2' cols='20'  name='materielName' id='materielName"+i+"' type='text' maxlength='200'/></td>",
						 "							</td>",
						 "						    <td style=\"text-align:center;\">",
						 "								<input type=\"text\" onchange=\"trimStr(this);\" style=\"background:#CCCCCC\" readonly=\"readonly\"   id=\"company"+i+"\"  name=\"company"+i+"\" value=\""+infoList[i].client+"\" class=\"ui_input_txt01\" maxlength=\"50\" />",
						 "							</td>",
						 "						    <td style=\"text-align:center;\">",
						 "								<input type=\"text\" name='SettleDate"+i+"' id='SettleDate"+i+"' value=\""+infoList[i].SettleDate+"\" class=\"ui_input_txt01\"   onfocus=\"WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})\"  class=\"Wdate\"/>",
			  	 		 "							</td>"].join("");
					 if(isDiaoBo == null){
						 result +=[ 
							 "          			<td style=\"text-align:center;border-right:1px solid #000;\">",
							 "                              <input type=\"text\" id=\"weigh"+i+"\" name=\"weigh"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\"   value=\""+infoList[i].weigh+"\" class=\"ui_input_txt01\" />",
							 "						</td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"RiseLoss"+i+"\" name=\"RiseLoss"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "						    </td>",
							 "                       <td style=\"text-align:center;\">",
							 "                              <input type=\"text\" id=\"collectWeigh"+i+"\" onchange=\"changecollectWeigh(this);\" onkeyup=\"keepNumOrDecimal(this);\" name=\"collectWeigh"+i+"\" value=\""+infoList[i].collectWeigh+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                        </td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"endAddress"+i+"\" name=\"endAddress"+i+"\" readonly=\"readonly\" value=\"无\" class=\"ui_input_txt01\" />",
							 "							</td>",
							 "							<td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"distance"+i+"\" name=\"distance"+i+"\" readonly=\"readonly\" value=\"无\" class=\"ui_input_txt01\" />",
							 "                            </td>",
							 "							<td style=\"text-align:center;\">",
							 "								<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" style=\"background:#CCCCCC\" readonly=\"readonly\"  id=\"freightPrice"+i+"\" name=\"freightPrice"+i+"\" onchange=\"changefreightMoney(this);\" value=\"0\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                            </td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" style=\"background:#CCCCCC\" readonly=\"readonly\"  id=\"freightMoney"+i+"\" name=\"freightMoney"+i+"\" value=\"0\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "							</td>"].join("");
					 }else{
						 result +=[
							 "						    <td  colspan=\"2\" style=\"text-align:center;\">",
							 "                            	<table>",
							 "                                	 <tr >",
							 "                                	 	<td style=\"text-align:center;border-right:1px solid #000;\">",
							 "                                	 		<input type=\"text\" id=\"weigh"+i+"\" name=\"weigh"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\"   value=\""+infoList[i].weigh+"\" class=\"ui_input_txt01\" />",
							 "										</td>",
							 "                                	<td style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
							 "                                       	<input type=\"text\" id=\"allotWeight"+i+"\" name=\"allotWeight"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\" value=\""+infoList[i].DAllotWeight+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "									</td>",
							 "                                	 </tr>",
							 "                                </table>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"RiseLoss"+i+"\" name=\"RiseLoss"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
							 "						    </td>",
							 "                            </td>",
							 "                                	 	<td style=\"text-align:center;\">",
							 "                                	 		<input type=\"text\" id=\"collectWeigh"+i+"\" onchange=\"changecollectWeigh(this);\" onkeyup=\"keepNumOrDecimal(this);\" name=\"collectWeigh"+i+"\" value=\""+infoList[i].collectWeigh+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                                	 	</td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"endAddress"+i+"\" onchange=\"changeEndAddress(this);\" value=\""+infoList[i].endAddress+"\" name=\"endAddress"+i+"\" class=\"ui_input_txt01\"/>",
							 "							</td>",
							 "							<td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"distance"+i+"\" onchange=\"changefreightMoney(this);\" name=\"distance"+i+"\" value=\""+infoList[i].settlementKilometre+"\" class=\"ui_input_txt01\"/>",
							 "                            </td>",
							 "							<td style=\"text-align:center;\">",
							 "								<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"freightPrice"+i+"\" name=\"freightPrice"+i+"\" onchange=\"changefreightMoney(this);\" value=\""+infoList[i].freightPrice+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "                            </td>",
							 "						    <td style=\"text-align:center;\">",
							 "						    	<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"freightMoney"+i+"\" readonly=\"readonly\"  name=\"freightMoney"+i+"\" value=\""+infoList[i].freightMoney+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
							 "							</td>"].join("");
					 }
					 ////
					 result +=[ 
						 "						    <td style=\"text-align:center;\">",
						 "						    	<input type=\"text\" id=\"escortDays"+i+"\" onkeyup=\"day(this);\" onchange=\"changeYT(this);\" name=\"escortDays"+i+"\" value=\""+infoList[i].escortDays+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "							</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" id=\"escortPrice"+i+"\" onkeyup=\"checkPrice(this);\" onchange=\"changeYD(this);\" name=\"escortPrice"+i+"\" value=\""+infoList[i].escortPrice+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "							</td>",
						 "						    <td style=\"text-align:center;\">",
						 "						    	<input type=\"text\" readonly=\"readonly\"id=\"escortMoney"+i+"\" name=\"escortMoney"+i+"\" value=\""+infoList[i].escortMoney+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                            </td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" readonly=\"readonly\" id=\"sumMoney"+i+"\" name=\"sumMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"18\"/>",
						 "						    </td>",
						 "						    <td style=\"text-align:center;\">",
						 "						    	<input type=\"text\" id=\"remarks"+i+"\" name=\"remarks"+i+"\" value=\""+infoList[i].remarks+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "						    </td>",
						 "						    <td  id=\"upfile"+i+"\" style=\"text-align:center;\">",
						 "						    <span id=\"upShow"+i+"\" style=\"display:none;text-align:center;\">",
						 "								<input type=\"file\" id=\"myBlogImage"+i+"\" name=\"myfiles\"/>",
						 "						    </span>",
						 "						    <span id=\"upSelectShow"+i+"\" style=\"display:none;text-align:center;\">",
						 "								<a id=\"fileShow"+i+"\" style=\"margin-left: 13px;\">"+fileSNameShow+"</a>",
						 "								<a type=\"button\" value=\"下载\" id=\"downloadBtn"+i+"\" onclick=\"downloadFile(this)\">",
						 "									<img src=\"../../img/common/download.png\" width=\"20\" height=\"20\" alt=\"下载\" title=\"下载\">",
						 "								</a>",
						 "								<a type=\"button\" value=\"删除\" id=\"deleteBtn"+i+"\"  onclick=\"deleteFile(this)\">",
						 "									<img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\">",
						 "								</a>",
						 "								<input type=\"hidden\" id=\"files"+i+"\" value=\""+fileSName+"\" name=\"transportRoute"+i+"\"/>",
						 "						    </span>",
						 "							</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<span id=\"isSerialType"+i+"\" name=\"isSerialType\">"+isSerialType,
						 "						    	</span>",
						 "						    </td>",
						 " 						</tr>"].join("");
				 }else{
					 result += [" <tr name=\"rowNumber\" style=\"border-bottom: 1px solid #044599;\">",
						 " 						 	<td style=\"text-align:center;\">"+(i+1)+"</td>",
						 "						    <td style=\"text-align:center;\">",
						 "						    <input type=\"hidden\" id=\"type"+i+"\" name=\"type"+i+"\" value=\""+type+"\" />",
						 "						    <input  id=\"serialID"+i+"\" name=\"serialID"+i+"\"  style=\"background:#CCCCCC\" readonly=\"readonly\"  value=\""+infoList[i].serialID+"\" />",
						 "                          <input type='hidden' id=\"type"+i+"\" name=\"type"+i+"\" value=\""+type+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "                          <input type='hidden' id=\"isD"+i+"\" name=\"isD"+i+"\" value=\""+isDiaoBo+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "                          <input type='hidden' id=\"id"+i+"\" name=\"id"+i+"\" value=\""+infoList[i].id+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "                          <input type='hidden' id=\"dispatchOutWarehouseId"+i+"\" name=\"dispatchOutWarehouseId"+i+"\" value=\""+infoList[i].dispatchOutWarehouseId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "						    </td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" style=\"background:#CCCCCC\" id=\"factoryTime"+i+"\" name=\"factoryTime"+i+"\" readonly=\"readonly\" value=\""+infoList[i].factoryTime+"\" class=\"ui_input_txt01\" />",
						 "						    </td>",
						 "						    <td style=\"text-align:center;\">",
						 "						    <input type=\"text\" style=\"background:#CCCCCC\" id=\"transportParty"+i+"\" name=\"transportParty"+i+"\" readonly=\"readonly\" value=\""+user.name+"\" class=\"ui_input_txt01\" />",
						 "						    </td>",
						 "						    <td style=\"text-align:center;\">",
						 "                                	 	<input  id=\"materielNameId"+i+"\" type='hidden' name=\"materielNameId"+i+"\" value=\""+infoList[i].materielId+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "                                	 	<input  id=\"materielModelId"+i+"\" type='hidden' name=\"materielModelId"+i+"\" value=\""+infoList[i].model+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "										<textarea style=\"background:#CCCCCC;resize:none;\" readonly=\"readonly\"  draggable='false' rows='2' cols='20'  name='materielName' id='materielName"+i+"' type='text' maxlength='200'/></td>",
						 "							</td>",
						 "						    <td style=\"text-align:center;\"><input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\"  onchange=\"trimStr(this);\" id=\"company"+i+"\" name=\"company"+i+"\" value=\""+infoList[i].client+"\" class=\"ui_input_txt01\" maxlength=\"50\" /></td>",
						 "						    <td style=\"text-align:center;\">",
						 "								<input type=\"text\" name='SettleDate"+i+"' id='SettleDate"+i+"' value=\""+infoList[i].SettleDate+"\" class=\"ui_input_txt01\"   onfocus=\"WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})\"  class=\"Wdate\"/>",
			  	 		 "							</td>",
						 "                                	<td style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
						 "                                	 <input type=\"text\" id=\"weigh"+i+"\" name=\"weigh"+i+"\"  style=\"background:#CCCCCC\" readonly=\"readonly\" value=\""+infoList[i].weigh+"\" class=\"ui_input_txt01\"/>",
						 "									</td>",
						 "                                	<td style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
						 "                                       	<input type=\"text\" id=\"allotWeight"+i+"\" name=\"allotWeight"+i+"\" style=\"background:#CCCCCC\" readonly=\"readonly\" value=\""+infoList[i].DAllotWeight+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "									</td>",
						 "						    <td style=\"text-align:center;\">",
						 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"RiseLoss"+i+"\" name=\"RiseLoss"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "						    </td>",
						 "						   	<td style=\"text-align:center;\">",
						 "						   	<table>",
						 "                                	<tr>",
						 "									<td style=\"text-align:center;border-bottom:1px solid #000\">",
						 "										<input type=\"text\" id=\"collectWeigh"+i+"\" onchange=\"changecollectWeigh(this);\" onkeyup=\"keepNumOrDecimal(this);\" name=\"collectWeigh"+i+"\" value=\""+infoList[i].collectWeigh+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "									</td>",
						 "									</tr>",
						 "										",
						 "                                     <tr >",
						 "                                       <td style=\"text-align:center;border-bottom:1px solid #000;border-right:1px solid #000\">",
						 "                                       	<input type=\"text\" id=\"SettlementWeight"+i+"\" name=\"SettlementWeight"+i+"\"  onchange=\"changecollectWeigh(this);\" onkeyup=\"keepNumOrDecimal(this);\" value=\""+infoList[i].DSettlementWeight+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                       </td>",
						 "                                       </tr>",
						 "                            </table>",
						 "                            </td>"].join("");
					 result += [
						 "						    <td  style=\"text-align:center;\">",
						 "								<table width=\"150\">",
						 "                                	 <tr>"].join("");
					 if(isDiaoBo == null){
						 result += ["                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
							 "						    	<select id=\"endAddress"+i+"\" onchange=\"changeEndAddress(this);\" name=\"endAddress"+i+"\" class=\"ui_select01\">",
							 "										<option value=\"-1\">请选择</option>",
							 "										</select>",
							 "                                	 	</td>"].join("");
					 }else{
						 result += ["                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
							 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\" id=\"endAddress"+i+"\" onchange=\"changeEndAddress(this);\" name=\"endAddress"+i+"\" value=\""+infoList[i].endAddress+"\" class=\"ui_input_txt01\">",
							 "                                	 	</td>"].join(""); 
					 }
					 result += [ "                                	 </tr>",
						 "                                     <tr><td style=\"text-align:center;\">",
						 "						    	<input type=\"text\"  style=\"background:#CCCCCC\" readonly=\"readonly\"  id=\"DendAddress"+i+"\" onchange=\"changeEndAddress(this);\" value=\""+infoList[i].consigneeAddress+"\"  name=\"DendAddress"+i+"\" class=\"ui_select01\">",
						 "                                     </td>",
						 "                                     </tr>",
						 "                                </table>",
						 "							</td>",
						 "						    <td style=\"text-align:center;\">",
						 "								<table width=\"150\" >"].join("");
					 if(isDiaoBo == null){
					 result += [  "                                	 <tr>",
						 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
						 "						    	<select id=\"distance"+i+"\" name=\"distance"+i+"\" onchange=\"changefreightMoney(this);\" class=\"ui_select01\">",
						 "										</select>",
						 "                                	 	</td>",
						 "                                	 </tr>"].join(""); 
					 }else{
						 result += [  "                                	 <tr>",
							 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
							 "						    	<input type=\"text\" style=\"background:#CCCCCC\" readonly=\"readonly\" onchange=\"changefreightMoney(this);\"  value=\""+infoList[i].settlementKilometre+"\" id=\"distance"+i+"\" name=\"distance"+i+"\" class=\"ui_input_txt01\">",
							 "                                	 	</td>",
							 "                                	 </tr>"].join(""); 
					 }
					 result += [  
						 "                                     <tr>",
						 "                                     	<td style=\"text-align:center;\">",
						 "                                	 		<input type=\"text\"  id=\"Ddistance"+i+"\" onchange=\"changeDfreightMoney(this);\" name=\"Ddistance"+i+"\" value=\""+infoList[i].Ddistance+"\" class=\"ui_input_txt01\" />",
						 "                                       </td>",
						 "                                     </tr>",
						 "                                </table>",
						 "							</td>",
						 "						    <td style=\"text-align:center;\">",
						 "								<table>",
						 "                                	 <tr>",
						 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
						 "                                	 		<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"freightPrice"+i+"\" onchange=\"changefreightMoney(this);\" name=\"freightPrice"+i+"\" value=\""+infoList[i].freightPrice+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                	 	</td>",
						 "                                	 </tr>",
						 "                                     <tr>",
						 "                                     	<td style=\"text-align:center;\">",
						 "                                       		<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" id=\"DfreightPrice"+i+"\" onchange=\"changeDfreightMoney(this);\"  name=\"DfreightPrice"+i+"\" value=\""+infoList[i].DFreightPrice+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                        </td>",
						 "                                     </tr>",
						 "                                </table>",
						 "							</td>",
						 "						    <td style=\"text-align:center;\">",
						 "								<table>",
						 "                                	 <tr>",
						 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
						 "                                	 		<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" readonly=\"readonly\"  id=\"freightMoney"+i+"\" name=\"freightMoney"+i+"\" value=\""+infoList[i].freightMoney+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                	 	</td>",
						 "                                	 </tr>",
						 "                                     <tr>",
						 "                                     	<td style=\"text-align:center;\">",
						 "                                       		<input type=\"text\" onkeyup=\"keepNumOrDecimal(this);\" readonly=\"readonly\"  id=\"DfreightMoney"+i+"\" name=\"DfreightMoney"+i+"\" value=\""+infoList[i].DFreightMoney+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                       </td>",
						 "                                     </tr>",
						 "                                </table>",
						 "							</td>",
						 "						    <td style=\"text-align:center;\"><table>",
						 "                                	 <tr>",
						 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
						 "                                	 		<input type=\"text\"  id=\"escortDays"+i+"\" onkeyup=\"day(this);\" onchange=\"changeYT(this);\" name=\"escortDays"+i+"\" value=\""+infoList[i].escortDays+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                	 	</td>",
						 "                                	 </tr>",
						 "                                     <tr>",
						 "                                     	<td style=\"text-align:center;\">",
						 "                                       		<input type=\"text\" id=\"DescortDays"+i+"\" onkeyup=\"day(this);\" onchange=\"changeDYT(this);\" name=\"DescortDays"+i+"\" value=\""+infoList[i].DEscortDays+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                       </td",
						 "                                      ></tr>",
						 "                                </table>",
						 "                             </td>",
						 "                             <td style=\"text-align:center;\"><table>",
						 "                                	 <tr>",
						 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
						 "                                	 		<input type=\"text\" id=\"escortPrice"+i+"\" onkeyup=\"checkPrice(this);\" onchange=\"changeYD(this);\" name=\"escortPrice"+i+"\" value=\""+infoList[i].escortPrice+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                	 	</td>",
						 "                                	 </tr>",
						 "                                     <tr>",
						 "                                     	<td style=\"text-align:center;\">",
						 "                                       		<input type=\"text\" id=\"DescortPrice"+i+"\" onkeyup=\"checkPrice(this);\" onchange=\"changeDYD(this);\" name=\"DescortPrice"+i+"\" value=\""+infoList[i].DEscortPrice+"\" class=\"ui_input_txt01\" maxlength=\"8\"/>",
						 "                                       </td>",
						 "                                     </tr>",
						 "                                </table>",
						 "                             </td>",
						 "						    <td style=\"text-align:center;\"><table>",
						 "                                	 <tr>",
						 "                                	 	<td style=\"text-align:center;border-bottom:1px solid #000\">",
						 "                                	 		<input type=\"text\" readonly=\"readonly\" id=\"escortMoney"+i+"\" name=\"escortMoney"+i+"\" value=\""+infoList[i].escortMoney+"\" class=\"ui_input_txt01\"/>",
						 "                                	 	</td>",
						 "                                	 </tr>",
						 "                                     <tr >",
						 "                                     	<td style=\"text-align:center;\">",
						 "                                       		<input type=\"text\" readonly=\"readonly\" id=\"DescortMoney"+i+"\" name=\"DescortMoney"+i+"\" value=\""+infoList[i].DEscortMoney+"\" class=\"ui_input_txt01\"/>",
						 "                                       </td>",
						 "                                     </tr>",
						 "                                </table>",
						 "                            </td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<input type=\"text\" readonly=\"readonly\" id=\"sumMoney"+i+"\" name=\"sumMoney"+i+"\" value=\"\" class=\"ui_input_txt01\" maxlength=\"18\"/>",
						 "						    </td>",
						 "						    <td style=\"text-align:center;\">",
						 "						    	<input type=\"text\" id=\"remarks"+i+"\" name=\"remarks"+i+"\" value=\""+infoList[i].remarks+"\" class=\"ui_input_txt01\" maxlength=\"200\"/>",
						 "						    </td>",
						 "						    <td id=\"upfile"+i+"\" style=\"text-align:center;\">",
						 "						    <span id=\"upShow"+i+"\" style=\"display:none;text-align:center;\">",
						 "								<input type=\"file\" id=\"myBlogImage"+i+"\" name=\"myfiles\"/>",
						 "						    </span>",
						 "						    <span id=\"upSelectShow"+i+"\" style=\"display:none;text-align:center;\">",
						 "								<a id=\"fileShow"+i+"\" style=\"margin-left: 13px;\">"+fileSNameShow+"</a>",
						 "								<a type=\"button\" value=\"下载\" id=\"downloadBtn"+i+"\" onclick=\"downloadFile(this)\">",
						 "									<img src=\"../../img/common/download.png\" width=\"20\" height=\"20\" alt=\"下载\" title=\"下载\">",
						 "								</a>",
						 "								<a type=\"button\" value=\"删除\" id=\"deleteBtn"+i+"\"  onclick=\"deleteFile(this)\">",
						 "									<img src=\"../../img/common/del.png\" width=\"20\" height=\"20\" alt=\"删除\" title=\"删除\">",
						 "								</a>",
						 "								<input type=\"hidden\" id=\"files"+i+"\" value=\""+fileSName+"\" name=\"transportRoute"+i+"\"/>",
						 "						    </span>",
						 "							</td>",
						 "						    <td  style=\"text-align:center;\">",
						 "						    	<span id=\"isSerialType"+i+"\" name=\"isSerialType\">"+isSerialType,
						 "						    	</span>",
						 "						    </td>",
						 " 						</tr>"].join("");
				 }
				 
		    	    
		    	    
				}
	    	    $("#transportationList tbody").append(result);
		}
	});
	
	
    var list = $("#transportationList tbody")
   for (var i = 0; i < infoList.length; i++) {
			if( $("#"+("SettlementWeight"+i)).val() != undefined && $("#"+("SettlementWeight"+i)).val() != ""){
				var sum = parseFloat($("#"+("SettlementWeight"+i)).val()) + parseFloat($("#"+("collectWeigh"+i)).val()) - parseFloat($("#"+("weigh"+i)).val())
			}else{
				var sum = $("#"+("collectWeigh"+i)).val() - $("#"+("weigh"+i)).val()
			}
			if($("#"+("freightMoney"+i)).val()!="" && $("#"+("escortMoney"+i)).val()!=""){
				if($("#"+("DfreightMoney"+i)).val()!="" && $("#"+("DescortMoney"+i)).val()!="" && $("#"+("DescortMoney"+i)).val() != undefined){
					var sum = $("#"+("freightMoney"+i)).val()*1 + $("#"+("escortMoney"+i)).val()*1 + $("#"+("DfreightMoney"+i)).val()*1 + $("#"+("DescortMoney"+i)).val()*1;
					$("#"+("sumMoney"+i)).val(sum)
				}else{
					var sum = $("#"+("freightMoney"+i)).val()*1 + $("#"+("escortMoney"+i)).val()*1;
					$("#"+("sumMoney"+i)).val(sum)
				}
			}
		 $("#RiseLoss"+i).val(Number(sum).toFixed(2));
		 $("#materielName"+i).val(infoList[i].materielName+"/"+infoList[i].materielModel);
	}
    
	for (var i = 0; i < infoList.length; i++) {
		if($("#"+("files"+i)).val()==""||$("#"+("files"+i)).val()==null){
			$("#"+("upShow"+i)).css('display','block'); 
		}else{
			$("#"+("upSelectShow"+i)).css('display','block'); 
		}
	}
    
 for (var i = 0; i < infoList.length; i++) {
	 if(infoList[i].transportBalance == 1){
			$("#"+("collectWeigh"+i)).css("background","#CCCCCC")
			$("#"+("collectWeigh"+i)).attr("readonly","#readonly")
			$("#"+("freightPrice"+i)).attr("disabled",true)
			$("#"+("freightPrice"+i)).val("已自提")
			$("#"+("freightMoney"+i)).attr("disabled",true)
			$("#"+("freightPrice"+i)).css("background","#CCCCCC")
			$("#"+("freightMoney"+i)).css("background","#CCCCCC")
			$("#"+("freightMoney"+i)).val("已自提")
		 }
	 if(infoList[i].outType == 1 || infoList[i].outType == 5 || infoList[i].outType == 99){
			if(infoList[i].nowTransportBalance == 1){
				$("#"+("DendAddress"+i)).attr("disabled",true)
				$("#"+("DendAddress"+i)).append("<option selected='selected' value=''>已自提</option>");
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
	 if(infoList[i].outType == undefined){
		 var id = infoList[i].ruid
	 }else{
		 var id = infoList[i].chuid
	 }
    $.ajax({
		type: "post",
		url: baseUrl+'/TranSportListStoragemeasure/getTransportsById.action',
		data:{id:id},
		async:false,
		dataType: "json",
		success: function (data) {
			if(infoList[i].DendAddress != ""){
				$.each(data.data, function (k, v) {
					$('<option></option>').attr("value", v.Id).html(v.Transports).appendTo($("#"+("endAddress"+i)));
					$('<option></option>').attr("value", v.Id).html(v.Distance).appendTo($("#"+("distance"+i)));
					$('<option></option>').attr("value", v.Id).html(v.Transports).appendTo($("#"+("DendAddress"+i)));
					$('<option></option>').attr("value", v.Id).html(v.Distance).appendTo($("#"+("Ddistance"+i)));
				})
			}else{
				$.each(data.data, function (k, v) {
					$('<option></option>').attr("value", v.Id).html(v.Transports).appendTo($("#"+("endAddress"+i)));
					$('<option></option>').attr("value", v.Id).html(v.Distance).appendTo($("#"+("distance"+i)));
				})
			}
		}
	});
  }
	
	 for (var i = 0; i < infoList.length; i++) {
		 if(infoList[i].outType == undefined){
	    	 $("#"+("endAddress"+i)).find("option").each(function (data) {
	    		    var $this = $(this);
	    		    if($this.text() == infoList[i].endAddress) {
	    		        $this.attr("selected", true);
	    		    }
	    	 })
	    	 $("#"+("distance"+i)).find("option").each(function (data) {
	    		    var $this = $(this);
	    		    if($this.text() == infoList[i].settlementKilometre) {
	    		        $this.attr("selected", true);
	    		    }
	    	 })
		 }else if(infoList[i].outType !=1 && infoList[i].outType !=5 && infoList[i].outType != 99){
			 $("#"+("endAddress"+i)).find("option").each(function (data) {
	    		    var $this = $(this);
	    		    if($this.text() == infoList[i].endAddress) {
	    		        $this.attr("selected", true);
	    		    }
	    	 })
	    	 $("#"+("distance"+i)).find("option").each(function (data) {
	    		    var $this = $(this);
	    		    if($this.text() == infoList[i].settlementKilometre) {
	    		        $this.attr("selected", true);
	    		    }
	    	 })
		 }else{
			 $("#"+("endAddress"+i)).find("option").each(function (data) {
	    		    var $this = $(this);
	    		    if($this.text() == infoList[i].endAddress) {
	    		        $this.attr("selected", true);
	    		    }
	    	 })
	    	 $("#"+("distance"+i)).find("option").each(function (data) {
	    		    var $this = $(this);
	    		    if($this.text() == infoList[i].settlementKilometre) {
	    		        $this.attr("selected", true);
	    		    }
	    	 })
	    	 $("#"+("DendAddress"+i)).find("option").each(function (data) {
	    		    var $this = $(this);
	    		    if($this.text() == infoList[i].DEndAddress) {
	    		        $this.attr("selected", true);
	    		    }
	    	 })
	    	 $("#"+("Ddistance"+i)).find("option").each(function (data) {
	    		    var $this = $(this);
	    		    if($this.text() == infoList[i].DDistance) {
	    		        $this.attr("selected", true);
	    		    }
	    	 })
		 }
		}
}


function changeEndAddress(e){
	var thisValue = e.value;
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	  $.ajax({
			type: "post",
			url: baseUrl+'/TranSportListStoragemeasure/getTransportsById.action',
			data:{"kid":thisValue},
			async:false,
			dataType: "json",
			success: function (data) {
				$("#"+("distance"+row)).empty("")
					$.each(data.data, function (k, v) {
						$('<option></option>').attr("value", v.Id).html(v.Distance).appendTo($("#"+("distance"+row)));
					})
			}
})
}
function changeDEndAddress(e){
	var thisValue = e.value;
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	  $.ajax({
			type: "post",
			url: baseUrl+'/TranSportListStoragemeasure/getTransportsById.action',
			data:{"kid":thisValue},
			async:false,
			dataType: "json",
			success: function (data) {
				$("#"+("Ddistance"+row)).empty("")
					$.each(data.data, function (k, v) {
						$('<option></option>').attr("value", v.Id).html(v.Distance).appendTo($("#"+("Ddistance"+row)));
					})
			}
})
}

function changecollectWeigh(e){
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	if($("#"+("collectWeigh"+row)).val()!=""){
		var thisNumber = e.value;
		if( $("#"+("SettlementWeight"+row)).val() != undefined && $("#"+("SettlementWeight"+row)).val() != ""){
			var sum = parseFloat($("#"+("SettlementWeight"+row)).val()) + parseFloat($("#"+("collectWeigh"+row)).val()) - parseFloat($("#"+("weigh"+row)).val())
		}else{
			var sum = parseFloat($("#"+("collectWeigh"+row)).val()) - parseFloat($("#"+("weigh"+row)).val())
		}
		$("#"+("RiseLoss"+row)).val(Number(sum).toFixed(2))
	}
}
function changefreightMoney(e){
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	if($("#"+("distance"+row)).val()!="" && $("#"+("freightPrice"+row)).val()!=""){
		var thisNumber = e.value;
		var sum = $("#"+("distance"+row)).val() * $("#"+("freightPrice"+row)).val();
		$("#"+("freightMoney"+row)).val(sum)
	}
	
	if($("#"+("freightMoney"+row)).val()!="" && $("#"+("escortMoney"+row)).val()!=""){
		if($("#"+("DfreightMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val() != undefined){
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1 + $("#"+("DfreightMoney"+row)).val()*1 + $("#"+("DescortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}else{
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}
	}
}
function changeDfreightMoney(e){
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	if($("#"+("Ddistance"+row)).val()!="" && $("#"+("DfreightPrice"+row)).val()!=""){
		var thisNumber = e.value;
		var sum = $("#"+("Ddistance"+row)).val() * $("#"+("DfreightPrice"+row)).val();
		$("#"+("DfreightMoney"+row)).val(sum)
	}
	
	if($("#"+("freightMoney"+row)).val()!="" && $("#"+("escortMoney"+row)).val()!=""){
		if($("#"+("DfreightMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val() != undefined){
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1 + $("#"+("DfreightMoney"+row)).val()*1 + $("#"+("DescortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}else{
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}
	}
}


function changeYT(e){
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	if($("#"+("escortPrice"+row)).val()!=""){
		var thisNumber = e.value;
		var sum = thisNumber * $("#"+("escortPrice"+row)).val();
		$("#"+("escortMoney"+row)).val(sum)
	}
	
	if($("#"+("freightMoney"+row)).val()!="" && $("#"+("escortMoney"+row)).val()!=""){
		if($("#"+("DfreightMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val() != undefined){
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1 + $("#"+("DfreightMoney"+row)).val()*1 + $("#"+("DescortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}else{
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}
	}
}

function changeYD(e){
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	if($("#"+("escortDays"+row)).val()!=""){
		var thisNumber = e.value;
		var sum = thisNumber * $("#"+("escortDays"+row)).val();
		$("#"+("escortMoney"+row)).val(sum)
	}
	if($("#"+("freightMoney"+row)).val()!="" && $("#"+("escortMoney"+row)).val()!=""){
		if($("#"+("DfreightMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val() != undefined){
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1 + $("#"+("DfreightMoney"+row)).val()*1 + $("#"+("DescortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}else{
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}
	}
	
}

function changeDYT(e){
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	if($("#"+("DescortPrice"+row)).val()!=""){
		var thisNumber = e.value;
		var sum = thisNumber * $("#"+("DescortPrice"+row)).val();
		$("#"+("DescortMoney"+row)).val(sum)
	}
	if($("#"+("freightMoney"+row)).val()!="" && $("#"+("escortMoney"+row)).val()!=""){
		if($("#"+("DfreightMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val() != undefined){
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1 + $("#"+("DfreightMoney"+row)).val()*1 + $("#"+("DescortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}else{
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}
	}
}

function changeDYD(e){
	var thisId = e.id;
	var row = parseInt(thisId.replace(/[^0-9]/ig,""))
	if($("#"+("DescortDays"+row)).val()!=""){
		var thisNumber = e.value;
		var sum = thisNumber * $("#"+("DescortDays"+row)).val();
		$("#"+("DescortMoney"+row)).val(sum)
	}
	if($("#"+("freightMoney"+row)).val()!="" && $("#"+("escortMoney"+row)).val()!=""){
		if($("#"+("DfreightMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val()!="" && $("#"+("DescortMoney"+row)).val() != undefined){
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1 + $("#"+("DfreightMoney"+row)).val()*1 + $("#"+("DescortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}else{
			var sum = $("#"+("freightMoney"+row)).val()*1 + $("#"+("escortMoney"+row)).val()*1;
			$("#"+("sumMoney"+row)).val(sum)
		}
	}
}

function downloadFile(e){
	var okdown = false;
	var fileId = e.id 
	var row = parseInt(fileId.replace(/[^0-9]/ig,""))
	  $.ajax({
			type: "post",
			url: baseUrl+'/TranSportListStoragemeasure/isHaveUpload.action',
			data:{"fileName":$("#"+("files"+row)).val()},
			async:false,
			dataType: "json",
			success: function (data) {
				if(data.code == "success"){
					okdown = true;
				}
			}
	  })
	if(okdown){
		window.location.href = "../../fileUp/fileDownload.action?filename=" +  $("#"+("files"+row)).val();
	}else{
		swal("操作失败", "文件已被删除", "error");
		return;
	}
}

function deleteFile (e) {
	var okdown = false;
	var fileId = e.id
	var row = parseInt(fileId.replace(/[^0-9]/ig,""))
	var serialID = $("#"+("serialID"+row)).val()
	swal({
		title: "确定要删除吗？",
		text:"删除后将无法恢复，请谨慎操作！",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#AEDEF4',
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		cancelButtonFont: '微软雅黑',
	}, function(){
		
		  $.ajax({
				type: "post",
				url: baseUrl+'/TranSportListStoragemeasure/isHaveUpload.action',
				data:{"fileName":$("#"+("files"+row)).val()},
				async:false,
				dataType: "json",
				success: function (data) {
					if(data.code == "success"){
						okdown = true;
					}
				}
		  })
		if(okdown){ 
		$.ajax({
			type: "post",
			url: baseUrl+'/TranSportListStoragemeasure/delUploadfile.action',
			data:{"serialID":serialID},
			async:false,
			dataType: "json",
			success: function (data) {
				if(data != null){
					if(data.code == "success"){
						$.ajax({
							url : "../../fileUp/fileDelete.action",
							async : false,
							dataType:'json',
							data : {
								"filename" : $("#"+("files"+row)).val()
							},
							type : "post",
							success : function(_info) {
								setTimeout(function(){swal({
									title: "操作成功",
									type: "success",
									cancelButtonText: '确定',
									cancelButtonFont: '微软雅黑',
								},
								function(){
									$("#"+("files"+row)).val("");
									$("#"+("upShow"+row)).css('display','block'); 
									$("#"+("upSelectShow"+row)).css('display','none'); 
								}); },200);
							}
						});
					}else{
						 swal("操作失败", "", "error");
						 return;
					}
				}
			}
		})
	   }else{
		   setTimeout(function(){swal({
				title: "操作失败,文件已被删除",
				type: "error",
				cancelButtonText: '确定',
				cancelButtonFont: '微软雅黑',
			}); },200);
	   }
	});
}
function trimStr(str){
	
	var valTr = str.value.replace(/(^\s*)|(\s*$)/g,"");
	str.value = valTr	
}