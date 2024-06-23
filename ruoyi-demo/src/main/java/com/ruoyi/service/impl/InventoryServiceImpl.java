package com.ruoyi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.InventoryMapper;
import com.ruoyi.domain.Inventory;
import com.ruoyi.service.IInventoryService;

/**
 * kucunService业务层处理
 *
 * @author ruoyi
 * @date 2024-06-19
 */
@Service
public class InventoryServiceImpl implements IInventoryService
{
    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * 查询kucun
     *
     * @param id kucun主键
     * @return kucun
     */
    @Override
    public Inventory selectInventoryById(Long id)
    {
        return inventoryMapper.selectInventoryById(id);
    }

    /**
     * 查询kucun列表
     *
     * @param inventory kucun
     * @return kucun
     */
    @Override
    public List<Inventory> selectInventoryList(Inventory inventory)
    {
        return inventoryMapper.selectInventoryList(inventory);
    }

    /**
     * 新增kucun
     *
     * @param inventory kucun
     * @return 结果
     */
    @Override
    public int insertInventory(Inventory inventory)
    {
        return inventoryMapper.insertInventory(inventory);
    }

    /**
     * 修改kucun
     *
     * @param inventory kucun
     * @return 结果
     */
    @Override
    public int updateInventory(Inventory inventory)
    {
        return inventoryMapper.updateInventory(inventory);
    }

    /**
     * 批量删除kucun
     *
     * @param ids 需要删除的kucun主键
     * @return 结果
     */
    @Override
    public int deleteInventoryByIds(Long[] ids)
    {

        Inventory inventory = inventoryMapper.selectInventoryById(ids[0]);
        if (inventory.getTotalProduct()!=0){
            return 0;
        }

        return inventoryMapper.deleteInventoryById(ids[0]);
    }

    /**
     * 删除kucun信息
     *
     * @param id kucun主键
     * @return 结果
     */
    @Override
    public int deleteInventoryById(Long id)
    {
        return inventoryMapper.deleteInventoryById(id);
    }
}
