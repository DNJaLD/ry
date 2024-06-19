package com.ruoyi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.GetStorageMapper;
import com.ruoyi.domain.GetStorage;
import com.ruoyi.service.IGetStorageService;

/**
 * chukuService业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-19
 */
@Service
public class GetStorageServiceImpl implements IGetStorageService 
{
    @Autowired
    private GetStorageMapper getStorageMapper;

    /**
     * 查询chuku
     * 
     * @param id chuku主键
     * @return chuku
     */
    @Override
    public GetStorage selectGetStorageById(Long id)
    {
        return getStorageMapper.selectGetStorageById(id);
    }

    /**
     * 查询chuku列表
     * 
     * @param getStorage chuku
     * @return chuku
     */
    @Override
    public List<GetStorage> selectGetStorageList(GetStorage getStorage)
    {
        return getStorageMapper.selectGetStorageList(getStorage);
    }

    /**
     * 新增chuku
     * 
     * @param getStorage chuku
     * @return 结果
     */
    @Override
    public int insertGetStorage(GetStorage getStorage)
    {
        return getStorageMapper.insertGetStorage(getStorage);
    }

    /**
     * 修改chuku
     * 
     * @param getStorage chuku
     * @return 结果
     */
    @Override
    public int updateGetStorage(GetStorage getStorage)
    {
        return getStorageMapper.updateGetStorage(getStorage);
    }

    /**
     * 批量删除chuku
     * 
     * @param ids 需要删除的chuku主键
     * @return 结果
     */
    @Override
    public int deleteGetStorageByIds(Long[] ids)
    {
        return getStorageMapper.deleteGetStorageByIds(ids);
    }

    /**
     * 删除chuku信息
     * 
     * @param id chuku主键
     * @return 结果
     */
    @Override
    public int deleteGetStorageById(Long id)
    {
        return getStorageMapper.deleteGetStorageById(id);
    }
}
