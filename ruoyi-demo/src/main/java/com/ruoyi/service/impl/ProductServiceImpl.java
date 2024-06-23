package com.ruoyi.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.domain.Inventory;
import com.ruoyi.domain.Storage;
import com.ruoyi.mapper.InventoryMapper;
import com.ruoyi.mapper.StorageMapper;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.ProductMapper;
import com.ruoyi.domain.Product;
import com.ruoyi.service.IProductService;

/**
 * shangpinService业务层处理
 *
 * @author ruoyi
 * @date 2024-06-19
 */
@Service
public class ProductServiceImpl implements IProductService
{
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private InventoryMapper inventoryMapper ;

    @Autowired
    private SysDictDataMapper sysDictDataMapper ;

    @Autowired
    private StorageMapper storageMapper ;


    /**
     * 查询shangpin
     *
     * @param id shangpin主键
     * @return shangpin
     */
    @Override
    public Product selectProductById(Long id)
    {
        return productMapper.selectProductById(id);
    }

    /**
     * 查询shangpin列表
     *
     * @param product shangpin
     * @return shangpin
     */
    @Override
    public List<Product> selectProductList(Product product)
    {
        return productMapper.selectProductList(product);
    }

    /**
     * 新增shangpin
     *
     * @param product shangpin
     * @return 结果
     */
    @Override
    public int insertProduct(Product product)
    {

        //存入商品
//        productMapper.insertProduct(product);

        //判断是否该仓库是否有该商品，有则什么也不敢，无则创建该仓库存改商品的记录
        Storage storageByName = storageMapper.selectStorageByName(product.getProductName(), product.getStorageName());
        if (storageByName != null){
            return 0 ;
        }

//        bug缓存有仓库名但实际没仓库

        //更新仓库
        Storage storage = new Storage();
        storage.setProductName(product.getProductName());
        storage.setStorageName(product.getStorageName());
        storage.setProuctNumber(0L);
        storage.setProductPrice(0L);
        storageMapper.insertStorage(storage);

//        productMapper.sel
//        if ()
        //存入字典数据
        SysDictData sysDictData = new SysDictData() ;
        sysDictData.setDictLabel(product.getProductName());
        sysDictData.setDictValue(product.getProductName());
        sysDictData.setDictType("product_name");
        sysDictDataMapper.insertDictData(sysDictData) ;

        //刷新缓存
        List<SysDictData> sysDictDatas = sysDictDataMapper.selectDictDataByType("product_name");
        DictUtils.removeDictCache("product_name");
        DictUtils.setDictCache("product_name",sysDictDatas);


        Inventory inventory1 = inventoryMapper.selectInventoryByName(product.getProductName());
        if (inventory1!=null){
            return 1 ;
        }
        //创建库存
        Inventory inventory = new Inventory() ;
        inventory.setProductName(product.getProductName());
        inventory.setMoney(0L);
        inventory.setTotalProduct(0L);
        inventory.setInventoryPrice(0L);
        inventoryMapper.insertInventory(inventory);

        Storage storageGetId = storageMapper.selectStorageByName(storage.getProductName(), storage.getStorageName());
        product.setStorageId(storageGetId.getId());
        return productMapper.insertProduct(product);
    }

    /**
     * 修改shangpin
     *
     * @param product shangpin
     * @return 结果
     */
    @Override
    public int updateProduct(Product product)
    {
        return productMapper.updateProduct(product);
    }

    /**
     * 批量删除shangpin
     *
     * @param ids 需要删除的shangpin主键
     * @return 结果
     */
    @Override
    public int deleteProductByIds(Long[] ids)
    {
        System.out.println("ids");
        return 1;
//        return productMapper.deleteProductByIds(ids);
    }

    /**
     * 删除shangpin信息
     *
     * @param id shangpin主键
     * @return 结果
     */
    @Override
    public int deleteProductById(Long id)
    {
        System.out.println("id");

        Product product = productMapper.selectProductById(id);
        Inventory inventory = inventoryMapper.selectInventoryByName(product.getProductName());

        if (inventory == null){
            return productMapper.deleteProductById(id);
        }else if (inventory.getTotalProduct()!=0){
            return 0 ;
        }
        else {
            return productMapper.deleteProductById(id);
        }
    }
}
