package com.mix.controller.cement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mix.model.Cement_ProductionDetailed;
import com.mix.model.DataTablesResponseInfo;
import com.mix.service.cement.Cement_ProductionDetailedService;

@Controller
@RequestMapping("/Cement_ProductionDetailed")
public class Cement_ProductionDetailedController {

	@Autowired
	private Cement_ProductionDetailedService cement_ProductionDetailedservice;

	/**
	 * 查询水泥生产数据采集详细表
	 * 
	 * @param cement_ProductionDetailed
	 *            Cemment_ID
	 * @return
	 */
	@RequestMapping("/getCement_ProductionDetaileds.action")
	@ResponseBody
	public DataTablesResponseInfo getCement_ProductionDetaileds(Cement_ProductionDetailed cement_ProductionDetailed) {

		DataTablesResponseInfo dir = new DataTablesResponseInfo();
		System.out.println(cement_ProductionDetailed.getI_cemment_Id());

		try {
			List<Cement_ProductionDetailed> list = cement_ProductionDetailedservice.getCement_ProductionDetaileds(cement_ProductionDetailed);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setSerialNumber(i + 1);

			}
			dir.setData(list);
			return dir;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
