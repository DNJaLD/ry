package com.ruoyi.service.impl;

import java.util.List;
import java.util.Objects;

import com.ruoyi.domain.Inventory;
import com.ruoyi.domain.PutStorage;
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

        System.out.println("操作中+++++++++++++++++++++\t++++++++++++++++++++++");
        Storage storage = storageMapper.selectStorageByName(getStorage.getProductName(), getStorage.getStortageName());

        if (getStorage.getProductNumber()>storage.getProuctNumber()){
            //输入的数量大于仓库数量了
            return 0;
        } else if (Objects.equals(getStorage.getProductNumber(), storage.getProuctNumber())) {
            //如果这张出库单取出了所有的商品那么就删除仓库中这个商品的记录
            storageMapper.deleteStorageById(storage.getId());
        }

        storage.setProuctNumber(storage.getProuctNumber() - getStorage.getProductNumber());
        storageMapper.updateStorage(storage);

        Inventory inventory = inventoryMapper.selectInventoryByName(getStorage.getProductName());

        inventory.setTotalProduct(inventory.getTotalProduct() - getStorage.getProductNumber());
        inventory.setMoney(inventory.getMoney() - storage.getProductPrice()*getStorage.getProductNumber());
        inventory.setInventoryPrice(inventory.getMoney() / inventory.getTotalProduct());

        inventoryMapper.updateInventory(inventory);

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
        Storage storage = null ;
        //判断受否超过总数量
        if (getStorageInfoVo.getAllNumber()<=getStorageInfoVo.getProductNumber()){
            return 0 ;
        }else if (getStorageInfoVo.getAllNumber().equals(getStorageInfoVo.getProductNumber())){
            storageMapper.deleteStorageByAllName(getStorageInfoVo.getProductName(),getStorageInfoVo.getStortageName());
        }else {
            //更新仓库仓库
             storage =  storageMapper.selectStorageByName(getStorageInfoVo.getProductName(),getStorageInfoVo.getStortageName());

            storage.setProuctNumber(
                    storage.getProuctNumber()
                            +getStorageInfoVo.getOldProductNumber()
                            -getStorageInfoVo.getProductNumber()
            );
            storageMapper.updateStorage(storage);

        }

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

        for (Long id : ids) {
            GetStorage getStorage = getStorageMapper.selectGetStorageById(id);

            String DProductName = getStorage.getProductName();
            String DStortageName = getStorage.getStortageName();
//            Long DPrice = getStorage.getPrice();
//            Long DMoney = getStorage.getMoney();
            Long DProductNumber = getStorage.getProductNumber();


            //跟新仓库
            Storage storage = storageMapper.selectStorageByName(DProductName, DStortageName);

            if (storage.getProuctNumber()-getStorage.getProductNumber()<0){
                return 0 ;
            }

            storage.setProuctNumber(getStorage.getProductNumber());

            storageMapper.updateStorage(storage);


            Long productPrice = storage.getProductPrice();

            storage.setProuctNumber(storage.getProuctNumber()+DProductNumber);

            //更新库存
            Inventory inventory = inventoryMapper.selectInventoryByName(DProductName);

            inventory.setTotalProduct(inventory.getTotalProduct()+DProductNumber);
            inventory.setMoney(inventory.getMoney()+productPrice*DProductNumber);
            inventory.setInventoryPrice(inventory.getMoney() / inventory.getTotalProduct());

            inventoryMapper.updateInventory(inventory);

            //删除出库单

            getStorageMapper.deleteGetStorageById(id);
        }

        return 1 ;
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

    @Override
    public GetStorageInfoVo selectGetStorageByAllName(GetStorage getStorage) {
        Storage storage = storageMapper.selectStorageByName(getStorage.getProductName(), getStorage.getStortageName());

        GetStorageInfoVo getStorageInfoVo = new GetStorageInfoVo() ;
        getStorageInfoVo.setProductName(getStorage.getProductName());
        getStorageInfoVo.setStortageName(getStorage.getStortageName());
        getStorageInfoVo.setOldProductNumber(getStorage.getProductNumber());

        getStorageInfoVo.setAllNumber(storage.getProuctNumber());
        getStorageInfoVo.setProductPrice(storage.getProductPrice());
        getStorageInfoVo.setMoney(storage.getProuctNumber()*storage.getProductPrice());
        return getStorageInfoVo;
    }
}
