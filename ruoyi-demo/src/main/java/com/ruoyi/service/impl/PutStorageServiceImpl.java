package com.ruoyi.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import com.ruoyi.domain.Inventory;
import com.ruoyi.domain.Product;
import com.ruoyi.domain.Storage;
import com.ruoyi.mapper.InventoryMapper;
import com.ruoyi.mapper.ProductMapper;
import com.ruoyi.mapper.StorageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mapper.PutStorageMapper;
import com.ruoyi.domain.PutStorage;
import com.ruoyi.service.IPutStorageService;

/**
 * rukuService业务层处理
 *
 * @author ruoyi
 * @date 2024-06-19
 */
@Service
public class PutStorageServiceImpl implements IPutStorageService
{
    private static final Logger log = LoggerFactory.getLogger(PutStorageServiceImpl.class);
    @Autowired
    private PutStorageMapper putStorageMapper;

    @Autowired
    private StorageMapper storageMapper ;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * 查询ruku
     *
     * @param id ruku主键
     * @return ruku
     */
    @Override
    public PutStorage selectPutStorageById(Long id)
    {
        return putStorageMapper.selectPutStorageById(id);
    }

    /**
     * 查询ruku列表
     *
     * @param putStorage ruku
     * @return ruku
     */
    @Override
    public List<PutStorage> selectPutStorageList(PutStorage putStorage)
    {
        return putStorageMapper.selectPutStorageList(putStorage);
    }

    /**
     * 新增ruku
     *
     * @param putStorage ruku
     * @return 结果
     */
    @Override
    public int insertPutStorage(PutStorage putStorage)
    {

        if (!putStorage.getMoney().equals(putStorage.getPrice()*putStorage.getProductNumber())){
            System.out.println("1111111111");
            System.out.println("putStorage.getMoney()"+putStorage.getMoney());
            System.out.println("putStorage.getProductNumber()"+putStorage.getProductNumber());
            return 0 ;
        }

        //是否有这个仓库
        List<Storage> storageList = storageMapper.selectStorageByStorageName(putStorage.getStortageName());
        if (!storageList.isEmpty()){
            //有这个仓库
            //flag为true表示该仓库有这个商品反之没有
            boolean flag = storageList.stream().anyMatch(storage -> storage.getProductName().equals(putStorage.getProductName()));

            if (!flag){
                System.out.println("2222222222");
                //1、没有这个商品
                //1.1存入仓库记录
                Storage s = new Storage();
                s.setProductName(putStorage.getProductName());
                s.setStorageName(putStorage.getStortageName());
                s.setProuctNumber(putStorage.getProductNumber());
                storageMapper.insertStorage(s);
                Storage storage = storageMapper.selectStorageByName(putStorage.getProductName(),putStorage.getStortageName());

                //2存入商品信息
                Product p = new Product() ;
                p.setProductName(putStorage.getProductName());
                p.setStorageName(putStorage.getStortageName());
                p.setStorageId(storage.getId());
                productMapper.insertProduct(p);
                Product product = productMapper.selectStorageByName(putStorage.getProductName(),putStorage.getStortageName());

                //仓库中存入商品id
                s.setProductId(product.getId());
                storageMapper.updateStorageByAllName(s);

                //获取该商品库存中的数据
                Inventory info = inventoryMapper.selectInventoryByName(putStorage.getProductName());

                //判断是否有这个库存
                if (info==null){
                    Inventory i = new Inventory();
                    i.setProductName(putStorage.getProductName());
                    i.setTotalProduct(putStorage.getProductNumber());
                    i.setInventoryPrice(putStorage.getPrice());
                    i.setMoney(putStorage.getProductNumber()*putStorage.getPrice());
                    inventoryMapper.insertInventory(i);
                }else {
                    Inventory inventory = new Inventory();
                    inventory.setProductName(putStorage.getProductName());
                    inventory.setMoney(info.getMoney()+putStorage.getPrice()*putStorage.getProductNumber());
                    inventory.setTotalProduct(info.getTotalProduct()+putStorage.getProductNumber());
                    inventory.setInventoryPrice(inventory.getMoney()/inventory.getTotalProduct());
                    inventoryMapper.updateInventoryByName(inventory);
                }
                //更新该商品的库存

                return putStorageMapper.insertPutStorage(putStorage);
            }
            //有商品有仓库
            else {
                System.out.println("333333333333");
                //更新仓库
                Storage s = new Storage() ;

                //获取该商品仓库的数量
                Storage storageByName = storageMapper.selectStorageByName(putStorage.getProductName(), putStorage.getStortageName());

                s.setProuctNumber(putStorage.getProductNumber()+storageByName.getProuctNumber());

                s.setProductName(putStorage.getProductName());

                s.setStorageName(putStorage.getStortageName());

                storageMapper.updateStorageByAllName(s);
                //更新库存
                //获取该商品库存中的数据
                Inventory info = inventoryMapper.selectInventoryByName(putStorage.getProductName());

                Inventory inventory = new Inventory();

                inventory.setProductName(putStorage.getProductName());

                inventory.setMoney(info.getMoney()+putStorage.getPrice()*putStorage.getProductNumber());

                inventory.setTotalProduct(info.getTotalProduct()+putStorage.getProductNumber());

                inventory.setInventoryPrice(inventory.getMoney()/inventory.getTotalProduct());
                inventoryMapper.updateInventoryByName(inventory);

                return putStorageMapper.insertPutStorage(putStorage);
            }

        }else {

            System.out.println("4444444444444");
            //没有这个仓库
            //1存入仓库记录
            Storage s = new Storage();
            s.setProductName(putStorage.getProductName());
            s.setStorageName(putStorage.getStortageName());
            s.setProuctNumber(putStorage.getProductNumber());
            storageMapper.insertStorage(s);
            Storage storage = storageMapper.selectStorageByName(putStorage.getProductName(),putStorage.getStortageName());

            //2存入商品信息
            Product p = new Product() ;
            p.setProductName(putStorage.getProductName());
            p.setStorageName(putStorage.getStortageName());
            p.setStorageId(storage.getId());
            productMapper.insertProduct(p);
            Product product = productMapper.selectStorageByName(putStorage.getProductName(),putStorage.getStortageName());

            //仓库中存入商品id
            s.setProductId(product.getId());
            storageMapper.updateStorageByAllName(s);

            //3存入库存信息
            Inventory inventory = inventoryMapper.selectInventoryByName(putStorage.getProductName());
            if (inventory==null){
                Inventory i = new Inventory();
                i.setProductName(putStorage.getProductName());

                i.setTotalProduct(putStorage.getProductNumber());
                i.setInventoryPrice(putStorage.getPrice());
                i.setMoney(putStorage.getProductNumber()*putStorage.getPrice());
                inventoryMapper.insertInventory(i);
            }else {
                System.out.println(inventory.toString());
                Inventory i = new Inventory();
                i.setProductName(putStorage.getProductName());
                i.setMoney(inventory.getMoney()+putStorage.getPrice()*putStorage.getProductNumber());
                i.setTotalProduct(inventory.getTotalProduct()+putStorage.getProductNumber());
                i.setInventoryPrice(i.getMoney()/i.getTotalProduct());
                System.out.println(i);
                inventoryMapper.updateInventoryByName(i);
            }


            return putStorageMapper.insertPutStorage(putStorage);
        }
    }

    /**
     * 修改ruku
     *
     * @param newPutStorage ruku
     * @return 结果
     */
    @Override
    public int updatePutStorage(PutStorage newPutStorage)
    {

        //可以修改的有 商品数量 、单价

        //获取以前的入库单数据
        PutStorage oldPutStorage = putStorageMapper.selectPutStorageById(newPutStorage.getId());

        Long newPrice = newPutStorage.getPrice();
        Long newNumber = newPutStorage.getProductNumber();
        Long newMoney = newPrice*newNumber;

        Long oldPrice = oldPutStorage.getPrice();
        Long oldNumber = oldPutStorage.getProductNumber();
        Long oldMoney = oldPutStorage.getMoney();

        String productName = newPutStorage.getProductName();
        String storageName = newPutStorage.getStortageName();

        System.out.println( oldPutStorage);

        Storage oldStorage = storageMapper.selectStorageByName(productName, storageName);


        Inventory inventory = inventoryMapper.selectInventoryByName(productName);

        //判断有那些属性修改了
        //1.没改东西
            if(Objects.equals(oldPrice,newPrice) && oldNumber.equals(newNumber)){
                return 1 ;
            }
            //两个都改了
            else if (!Objects.equals(oldPrice,newPrice) && !oldNumber.equals(newNumber)){


                //更具productName和stortageName更新仓库
                Storage s = new Storage() ;
                s.setStorageName(storageName);
                s.setProductName(productName);
                s.setProuctNumber((newNumber-oldNumber)+oldStorage.getProuctNumber());
                storageMapper.updateStorageByAllName(s);

                //更具productName更新库存

                Inventory i = new Inventory();;
                Long FNumber = newNumber - oldNumber ;
                Long FMoney =  newMoney - oldMoney ;
                //计算单价
                Long FPrice = (FMoney + inventory.getMoney()) / (FNumber + inventory.getTotalProduct());
                i.setInventoryPrice(FPrice);
                i.setTotalProduct(inventory.getTotalProduct()+FNumber);
                i.setMoney(FMoney+inventory.getMoney());
                i.setProductName(productName);
                inventoryMapper.updateInventoryByName(i);

                //更新入库单
                newPutStorage.setMoney(newPrice*newNumber);
                return putStorageMapper.updatePutStorage(newPutStorage);

            }
            //改了单价
            else if (!Objects.equals(oldPrice,newPrice) && oldNumber.equals(newNumber)) {

                //更具productName更新库存

                Inventory i = new Inventory();
                Long FNumber = oldNumber ;
                Long FMoney =  newMoney - oldMoney ;
                //计算单价
                Long FPrice = (FMoney + inventory.getMoney()) / (FNumber + inventory.getTotalProduct());
                i.setInventoryPrice(FPrice);
//                i.setTotalProduct(inventory.getTotalProduct()+FNumber);
                i.setMoney(inventory.getMoney()+FMoney);
                i.setProductName(productName);
                inventoryMapper.updateInventoryByName(i);


                newPutStorage.setMoney(newPrice*newNumber);
                return putStorageMapper.updatePutStorage(newPutStorage);
            }
            //改了数量
            else if (Objects.equals(oldPrice,newPrice) && !oldNumber.equals(newNumber)) {

                //更具productName和stortageName更新仓库
                Storage s = new Storage() ;
                s.setStorageName(storageName);
                s.setProductName(productName);
                s.setProuctNumber((newNumber-oldNumber)+oldStorage.getProuctNumber());
                storageMapper.updateStorageByAllName(s);

                //更具productName更新库存
                Inventory i = new Inventory();;
                Long FNumber = newNumber - oldNumber ;
                Long FMoney =  newMoney - oldMoney ;
                //计算单价
                Long FPrice = (FMoney + inventory.getMoney()) / (FNumber + inventory.getTotalProduct());
                i.setInventoryPrice(FPrice);
                i.setTotalProduct(inventory.getTotalProduct()+FNumber);

                i.setMoney(FMoney+inventory.getMoney());
                i.setProductName(productName);
                inventoryMapper.updateInventoryByName(i);
                return putStorageMapper.updatePutStorage(newPutStorage);
            }
            else {
                return 0 ;
            }

//        return putStorageMapper.updatePutStorage(putStorage);
    }


    /**
     * 批量删除ruku
     *
     * @param ids 需要删除的ruku主键
     * @return 结果
     */
    @Override
    public int deletePutStorageByIds(Long[] ids)
    {
        return putStorageMapper.deletePutStorageByIds(ids);
    }

    /**
     * 删除ruku信息
     *
     * @param id ruku主键
     * @return 结果
     */
    @Override
    public int deletePutStorageById(Long id)
    {
        PutStorage putStorage = putStorageMapper.selectPutStorageById(id);

        String DProductName = putStorage.getProductName();
        String DStortageName = putStorage.getStortageName();
        Long DPrice = putStorage.getPrice();
        Long DMoney = putStorage.getMoney();
        Long DProductNumber = putStorage.getProductNumber();

        //跟新商品
        Product product = productMapper.selectStorageByName(DProductName, DStortageName);
        Product p = new Product();
        //跟新仓库

        //更新库存

        //删除入库单


        return 0 ;
//        return putStorageMapper.deletePutStorageById(id);
    }
}
