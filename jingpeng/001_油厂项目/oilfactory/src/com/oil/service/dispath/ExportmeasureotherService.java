package com.oil.service.dispath;
import java.util.List;
import java.util.Map;
import com.oil.model.dispath.ExportmeasureotherEntity;

public interface ExportmeasureotherService {
	// 查询其它出库单列表
	List<ExportmeasureotherEntity> getExportmeasureotherList(Map<String, Object> map);
	
	// 新增其他出库单
	int insertExportmeasureother(ExportmeasureotherEntity data);
	
	// 修改其他出库单
	int updateExportmeasureother(ExportmeasureotherEntity data);
	
	// 删除其他出库单
	int deleteExportmeasureother(Map<String, Object> map);
	
	// 页面检索list
    Map<String, Object> getDataList();
    
    // 删除司机电子签名
  	int deleteElectronicsName(Map<String, Object> param);
}
