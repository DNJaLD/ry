package com.ruoyi.service;

import java.util.List;
import com.ruoyi.domain.Product;

/**
 * shangpinService接口
 * 
 * @author ruoyi
 * @date 2024-06-19
 */
public interface IProductService 
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
     * 批量删除shangpin
     * 
     * @param ids 需要删除的shangpin主键集合
     * @return 结果
     */
    public int deleteProductByIds(Long[] ids);

    /**
     * 删除shangpin信息
     * 
     * @param id shangpin主键
     * @return 结果
     */
    public int deleteProductById(Long id);
}
