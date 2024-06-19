package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.GetStorage;

/**
 * chukuMapper接口
 * 
 * @author ruoyi
 * @date 2024-06-19
 */
public interface GetStorageMapper 
{
    /**
     * 查询chuku
     * 
     * @param id chuku主键
     * @return chuku
     */
    public GetStorage selectGetStorageById(Long id);

    /**
     * 查询chuku列表
     * 
     * @param getStorage chuku
     * @return chuku集合
     */
    public List<GetStorage> selectGetStorageList(GetStorage getStorage);

    /**
     * 新增chuku
     * 
     * @param getStorage chuku
     * @return 结果
     */
    public int insertGetStorage(GetStorage getStorage);

    /**
     * 修改chuku
     * 
     * @param getStorage chuku
     * @return 结果
     */
    public int updateGetStorage(GetStorage getStorage);

    /**
     * 删除chuku
     * 
     * @param id chuku主键
     * @return 结果
     */
    public int deleteGetStorageById(Long id);

    /**
     * 批量删除chuku
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGetStorageByIds(Long[] ids);
}
