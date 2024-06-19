package com.ruoyi.service;

import java.util.List;
import com.ruoyi.domain.GetStorage;

/**
 * chukuService接口
 * 
 * @author ruoyi
 * @date 2024-06-19
 */
public interface IGetStorageService 
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
     * 批量删除chuku
     * 
     * @param ids 需要删除的chuku主键集合
     * @return 结果
     */
    public int deleteGetStorageByIds(Long[] ids);

    /**
     * 删除chuku信息
     * 
     * @param id chuku主键
     * @return 结果
     */
    public int deleteGetStorageById(Long id);
}
