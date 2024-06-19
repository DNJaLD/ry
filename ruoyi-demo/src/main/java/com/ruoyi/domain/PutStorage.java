package com.ruoyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ruku对象 put_storage
 * 
 * @author ruoyi
 * @date 2024-06-19
 */
public class PutStorage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品名 */
    @Excel(name = "商品名")
    private String productName;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long productNumber;

    /** 单价 */
    @Excel(name = "单价")
    private Long price;

    /** 金额 */
    @Excel(name = "金额")
    private Long money;

    /** 仓库名 */
    @Excel(name = "仓库名")
    private String stortageName;

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
    public void setProductNumber(Long productNumber) 
    {
        this.productNumber = productNumber;
    }

    public Long getProductNumber() 
    {
        return productNumber;
    }
    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }
    public void setMoney(Long money) 
    {
        this.money = money;
    }

    public Long getMoney() 
    {
        return money;
    }
    public void setStortageName(String stortageName) 
    {
        this.stortageName = stortageName;
    }

    public String getStortageName() 
    {
        return stortageName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productName", getProductName())
            .append("productNumber", getProductNumber())
            .append("price", getPrice())
            .append("money", getMoney())
            .append("stortageName", getStortageName())
            .toString();
    }
}
