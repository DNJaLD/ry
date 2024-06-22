package com.ruoyi.mapper;

import java.util.List;
import com.ruoyi.domain.Product;
import org.apache.ibatis.annotations.Param;

/**
 * shangpinMapper接口
 *
 * @author ruoyi
 * @date 2024-06-19
 */
public interface ProductMapper
{
    /**
     * 查询shangpin
     *
     * @param id shangpin主键
     * @return shangpin
     */
    public Product selectProductById(Long id);

    /**
     * 查询shangpin列表
     *
     * @param product shangpin
     * @return shangpin集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增shangpin
     *
     * @param product shangpin
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改shangpin
     *
     * @param product shangpin
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 删除shangpin
     *
     * @param id shangpin主键
     * @return 结果
     */
    public int deleteProductById(Long id);

    /**
     * 批量删除shangpin
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductByIds(Long[] ids);

    /**
     * @param productName
     * @param stortageName
     * @return 结果
     */
    Product selectStorageByName(@Param("productName") String productName, @Param("stortageName") String stortageName);

}
