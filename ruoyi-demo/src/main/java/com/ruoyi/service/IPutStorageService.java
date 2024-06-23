package com.ruoyi.service;

import java.util.List;

import com.ruoyi.domain.GetStorage;
import com.ruoyi.domain.PutStorage;
import com.ruoyi.domain.Storage;
import com.ruoyi.vo.GetStorageInfoVo;

/**
 * rukuService接口
 * 
 * @author ruoyi
 * @date 2024-06-19
 */
public interface IPutStorageService 
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
     * 批量删除ruku
     * 
     * @param ids 需要删除的ruku主键集合
     * @return 结果
     */
    public int deletePutStorageByIds(Long[] ids);

    /**
     * 删除ruku信息
     * 
     * @param id ruku主键
     * @return 结果
     */
    public int deletePutStorageById(Long id);

    GetStorageInfoVo selectPutStorageByAllName(GetStorage getStorage);
}
