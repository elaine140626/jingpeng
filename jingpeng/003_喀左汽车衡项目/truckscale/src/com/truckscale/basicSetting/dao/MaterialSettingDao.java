package com.truckscale.basicSetting.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.truckscale.basicSetting.model.MaterialSettingEntity;
@Repository
public interface MaterialSettingDao {

	// 获取材料名称List信息
	List<MaterialSettingEntity> getMaterialSettingList(Map<String, Object> map);
	
	// 判断是否存在相同材料名称和编号
	List<MaterialSettingEntity> getCount(MaterialSettingEntity param);
	
	// 新增材料名称表
	int insertMaterialSetting(MaterialSettingEntity param);
	
	// 修改材料名称表
	int updateMaterialSetting(MaterialSettingEntity param);
	
	// 删除材料名称表
	int deleteMaterialSetting(MaterialSettingEntity param);

	MaterialSettingEntity getMaterialSettingNumber();

	Map<String, Object> getFeeddetailedcompany(MaterialSettingEntity param);
}
