package com.ruoyi.service.impl;

import java.util.List;

import com.ruoyi.domain.Inventory;
import com.ruoyi.domain.Storage;
import com.ruoyi.mapper.InventoryMapper;
import com.ruoyi.mapper.StorageMapper;
import com.ruoyi.vo.GetStorageInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.GetStorageMapper;
import com.ruoyi.domain.GetStorage;
import com.ruoyi.service.IGetStorageService;

/**
 * chukuService业务层处理
 *
 * @author ruoyi
 * @date 2024-06-20
 */
@Service
public class GetStorageServiceImpl implements IGetStorageService
{
    @Autowired
    private GetStorageMapper getStorageMapper;

    @Autowired
    private StorageMapper storageMapper;

    @Autowired
    private InventoryMapper inventoryMapper;
    /**
     * 查询chuku
     *
     * @param id chuku主键
     * @return chuku
     */
    @Override
    public GetStorageInfoVo selectGetStorageById(Long id)
    {

        //通过id获取这条出库记录
        GetStorage getStorage = getStorageMapper.selectGetStorageById(id);
        GetStorageInfoVo getStorageInfoVo = new GetStorageInfoVo();
        BeanUtils.copyProperties(getStorage, getStorageInfoVo);

        //在仓库表中获取该仓库中该商品的单价个数量
        Storage storageByName = storageMapper.selectStorageByName(getStorage.getProductName(), getStorage.getStortageName());

        System.out.println(storageByName);
        getStorageInfoVo.setProductPrice(storageByName.getProductPrice());
        getStorageInfoVo.setAllNumber(storageByName.getProuctNumber());
        getStorageInfoVo.setMoney(storageByName.getProductPrice()*storageByName.getProuctNumber());

        getStorageInfoVo.setOldProductNumber(getStorage.getProductNumber());

        System.out.println("返回的结果是======\t"+getStorageInfoVo);
        return getStorageInfoVo;
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
     * @param getStorageInfoVo chuku
     * @return 结果
     */
    @Override
    public int updateGetStorage(GetStorageInfoVo getStorageInfoVo)
    {
        GetStorage getStorage = new GetStorage();
        BeanUtils.copyProperties(getStorageInfoVo, getStorage);
        //判断受否超过总数量
        if (getStorageInfoVo.getAllNumber()<=getStorageInfoVo.getProductNumber()){
            return 0 ;
        }
        //更新仓库仓库
        Storage storage =  storageMapper.selectStorageByName(getStorageInfoVo.getProductName(),getStorageInfoVo.getStortageName());

        storage.setProuctNumber(
                storage.getProuctNumber()
                        +getStorageInfoVo.getOldProductNumber()
                        -getStorageInfoVo.getProductNumber()
        );
        storageMapper.updateStorage(storage);

        //更新库存
        Inventory inventory = inventoryMapper.selectInventoryByName(getStorageInfoVo.getProductName());
        inventory.setTotalProduct(
                inventory.getTotalProduct()
                        +getStorageInfoVo.getOldProductNumber()
                        -getStorageInfoVo.getProductNumber()
        );
        inventory.setMoney(
                inventory.getMoney()
                +getStorageInfoVo.getOldProductNumber()*storage.getProductPrice()
                -getStorageInfoVo.getProductNumber()*storage.getProductPrice()
        );
        inventory.setInventoryPrice(
                inventory.getMoney()/inventory.getTotalProduct()
        );

        inventoryMapper.updateInventory(inventory);


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
