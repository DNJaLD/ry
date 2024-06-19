package com.ruoyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * kucun对象 inventory
 * 
 * @author ruoyi
 * @date 2024-06-19
 */
public class Inventory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品名 */
    @Excel(name = "商品名")
    private String productName;

    /** 金额 */
    @Excel(name = "金额")
    private Long money;

    /** 商品库存 */
    @Excel(name = "商品库存")
    private Long totalProduct;

    /** 库存单价 */
    @Excel(name = "库存单价")
    private Long inventoryPrice;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setMoney(Long money) 
    {
        this.money = money;
    }

    public Long getMoney() 
    {
        return money;
    }
    public void setTotalProduct(Long totalProduct) 
    {
        this.totalProduct = totalProduct;
    }

    public Long getTotalProduct() 
    {
        return totalProduct;
    }
    public void setInventoryPrice(Long inventoryPrice) 
    {
        this.inventoryPrice = inventoryPrice;
    }

    public Long getInventoryPrice() 
    {
        return inventoryPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productName", getProductName())
            .append("money", getMoney())
            .append("totalProduct", getTotalProduct())
            .append("inventoryPrice", getInventoryPrice())
            .toString();
    }
}
