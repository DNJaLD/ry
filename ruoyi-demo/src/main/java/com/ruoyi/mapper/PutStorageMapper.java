package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.PutStorage;

/**
 * rukuMapper接口
 * 
 * @author ruoyi
 * @date 2024-06-19
 */
public interface PutStorageMapper 
{
    /**
     * 查询ruku
     * 
     * @param id ruku主键
     * @return ruku
     */
    public PutStorage selectPutStorageById(Long id);

    /**
     * 查询ruku列表
     * 
     * @param putStorage ruku
     * @return ruku集合
     */
    public List<PutStorage> selectPutStorageList(PutStorage putStorage);

    /**
     * 新增ruku
     * 
     * @param putStorage ruku
     * @return 结果
     */
    public int insertPutStorage(PutStorage putStorage);

    /**
     * 修改ruku
     * 
     * @param putStorage ruku
     * @return 结果
     */
    public int updatePutStorage(PutStorage putStorage);

    /**
     * 删除ruku
     * 
     * @param id ruku主键
     * @return 结果
     */
    public int deletePutStorageById(Long id);

    /**
     * 批量删除ruku
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePutStorageByIds(Long[] ids);
}
