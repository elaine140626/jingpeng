package com.oil.dao.dispath;
import java.util.List;
import java.util.Map;
import com.oil.model.dispath.ExportmeasureotherEntity;

public interface ExportmeasureotherDao {

	// 查询其它出库单列表
	List<ExportmeasureotherEntity> getExportmeasureotherList(Map<String, Object> map);
	
	// 新增其他出库单
	int insertExportmeasureother(ExportmeasureotherEntity data);
	
	// 修改其他出库单
	int updateExportmeasureother(ExportmeasureotherEntity data);
	
	// 删除其他出库单
	int deleteExportmeasureother(Map<String, Object> map);
	
	// 客户名称检索list
	List<String> getClientList();
	
	// 车牌号码检索list
	List<String> getPlateNumberList();
	
	// 车队信息检索list
	List<String> getFleetNameList();
	
	// 送货人检索list
	List<String> getDeliveryManList();
	
	// 产品名称检索list
	List<String> getMaterielNameList();
	
	// 规格型号检索list
	List<String> getMaterielModelList();
	
	// 删除司机电子签名
	int deleteElectronicsName(Map<String, Object> map);
}
