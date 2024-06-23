package com.ruoyi.service.impl;

import java.util.HashSet;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
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

    @Autowired
    private SysDictDataMapper sysDictDataMapper ;


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

        List<Storage> storages = storageMapper.selectStorageByStorageName(storage.getStorageName());
        if (!storages.isEmpty()){
            return 0 ;
        }

        storage.setProductName("");

         //刷新缓存
        SysDictData sysDictData = new SysDictData() ;
        sysDictData.setDictType("stortage_name");
        sysDictData.setDictValue(storage.getStorageName());
        sysDictData.setDictLabel(storage.getStorageName());


        sysDictDataMapper.insertDictData(sysDictData);
        List<SysDictData> sysDictDatas = sysDictDataMapper.selectDictDataByType("stortage_name");
        DictUtils.removeDictCache("stortage_name");
        DictUtils.setDictCache("stortage_name",sysDictDatas);
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
        for (Long id : ids) {

        }
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

//        storageMapper.selectStorageByStorageName();

        return storageMapper.deleteStorageById(id);
    }

    @Override
    public int deleteStorageWithNoProduct(String storageName) {

        List<Storage> storages = storageMapper.selectStorageByStorageName(storageName);

        if (storages.size() >= 2) {
            //当获取的仓库记录大于1时说明有多条商品入库记录还有商品存在
            return 0;
        }
        //在新增仓库时会增加一条没有商品的记录
        else if ( storages.size() == 1) {
            storages.forEach(storage -> {
                storageMapper.deleteStorageById(storage.getId());
            });
            //刷新缓存
            int i = sysDictDataMapper.deleteDictDataByLabalAndValue(storageName,"stortage_name");
            List<SysDictData> sysDictData = sysDictDataMapper.selectDictDataByType("stortage_name");
            DictUtils.removeDictCache("stortage_name");
            DictUtils.setDictCache("stortage_name",sysDictData);

            return 1 ;
        } else {
            return -1;
        }
    }
}
