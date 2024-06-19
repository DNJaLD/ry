package com.ruoyi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.StorageMapper;
import com.ruoyi.domain.Storage;
import com.ruoyi.service.IStorageService;

/**
 * cangkuService业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-19
 */
@Service
public class StorageServiceImpl implements IStorageService 
{
    @Autowired
    private StorageMapper storageMapper;

    /**
     * 查询cangku
     * 
     * @param id cangku主键
     * @return cangku
     */
    @Override
    public Storage selectStorageById(Long id)
    {
        return storageMapper.selectStorageById(id);
    }

    /**
     * 查询cangku列表
     * 
     * @param storage cangku
     * @return cangku
     */
    @Override
    public List<Storage> selectStorageList(Storage storage)
    {
        return storageMapper.selectStorageList(storage);
    }

    /**
     * 新增cangku
     * 
     * @param storage cangku
     * @return 结果
     */
    @Override
    public int insertStorage(Storage storage)
    {
        return storageMapper.insertStorage(storage);
    }

    /**
     * 修改cangku
     * 
     * @param storage cangku
     * @return 结果
     */
    @Override
    public int updateStorage(Storage storage)
    {
        return storageMapper.updateStorage(storage);
    }

    /**
     * 批量删除cangku
     * 
     * @param ids 需要删除的cangku主键
     * @return 结果
     */
    @Override
    public int deleteStorageByIds(Long[] ids)
    {
        return storageMapper.deleteStorageByIds(ids);
    }

    /**
     * 删除cangku信息
     * 
     * @param id cangku主键
     * @return 结果
     */
    @Override
    public int deleteStorageById(Long id)
    {
        return storageMapper.deleteStorageById(id);
    }
}
