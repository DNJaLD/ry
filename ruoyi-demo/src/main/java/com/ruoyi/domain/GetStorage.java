package com.ruoyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * chuku对象 get_storage
 * 
 * @author ruoyi
 * @date 2024-06-20
 */
public class GetStorage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long productNumber;

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
            .append("stortageName", getStortageName())
            .toString();
    }
}
