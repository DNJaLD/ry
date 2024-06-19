package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.Inventory;
import org.apache.ibatis.annotations.Param;

/**
 * kucunMapper接口
 *
 * @author ruoyi
 * @date 2024-06-19
 */
public interface InventoryMapper
{
    /**
     * 查询kucun
     *
     * @param id kucun主键
     * @return kucun
     */
    public Inventory selectInventoryById(Long id);

    /**
     * 查询kucun列表
     *
     * @param inventory kucun
     * @return kucun集合
     */
    public List<Inventory> selectInventoryList(Inventory inventory);

    /**
     * 新增kucun
     *
     * @param inventory kucun
     * @return 结果
     */
    public int insertInventory(Inventory inventory);

    /**
     * 修改kucun
     *
     * @param inventory kucun
     * @return 结果
     */
    public int updateInventory(Inventory inventory);

    /**
     * 删除kucun
     *
     * @param id kucun主键
     * @return 结果
     */
    public int deleteInventoryById(Long id);

    /**
     * 批量删除kucun
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInventoryByIds(Long[] ids);

    /**
     * @param productName
     * @return jieguo
     */
    Inventory selectInventoryByName(@Param("productName") String productName);

    void updateInventoryByName( Inventory inventory);
}
