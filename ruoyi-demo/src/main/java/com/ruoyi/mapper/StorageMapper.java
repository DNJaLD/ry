package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.Storage;
import org.apache.ibatis.annotations.Param;

/**
 * cangkuMapper接口
 *
 * @author ruoyi
 * @date 2024-06-19
 */
public interface StorageMapper
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
     * 删除cangku
     *
     * @param id cangku主键
     * @return 结果
     */
    public int deleteStorageById(Long id);

    /**
     * 批量删除cangku
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStorageByIds(Long[] ids);

    /**
     * @param storageName 查找是否有这个仓库
     * @return
     */
    List<Storage> selectStorageByStorageName( String storageName);


    /**
     * @param productName
     * @param stortageName
     * @return  返回具体记录数据
     */
    Storage selectStorageByName(@Param("productName") String productName,@Param("storageName") String stortageName);

    void updateStorageByAllName(Storage storage);

    void deleteStorageByAllName(@Param("productName") String productName, @Param("stortageName") String stortageName);
}
