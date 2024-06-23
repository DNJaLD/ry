package com.ruoyi.service;

import java.util.List;
import com.ruoyi.domain.Storage;

/**
 * cangkuService接口
 *
 * @author ruoyi
 * @date 2024-06-19
 */
public interface IStorageService
{
    /**
     * 查询cangku
     *
     * @param id cangku主键
     * @return cangku
     */
    public Storage selectStorageById(Long id);

    /**
     * 查询cangku列表
     *
     * @param storage cangku
     * @return cangku集合
     */
    public List<Storage> selectStorageList(Storage storage);

    /**
     * 新增cangku
     *
     * @param storage cangku
     * @return 结果
     */
    public int insertStorage(Storage storage);

    /**
     * 修改cangku
     *
     * @param storage cangku
     * @return 结果
     */
    public int updateStorage(Storage storage);

    /**
     * 批量删除cangku
     *
     * @param ids 需要删除的cangku主键集合
     * @return 结果
     */
    public int deleteStorageByIds(Long[] ids);

    /**
     * 删除cangku信息
     *
     * @param id cangku主键
     * @return 结果
     */
    public int deleteStorageById(Long id);

    int deleteStorageWithNoProduct(String storageName);
}
