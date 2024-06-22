package com.ruoyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * shangpin对象 product
 * 
 * @author ruoyi
 * @date 2024-06-19
 */
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 仓库id */
    @Excel(name = "仓库id")
    private Long storageId;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String storageName;



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
    public void setStorageId(Long storageId) 
    {
        this.storageId = storageId;
    }

    public Long getStorageId() 
    {
        return storageId;
    }
    public void setStorageName(String storageName) 
    {
        this.storageName = storageName;
    }

    public String getStorageName() 
    {
        return storageName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productName", getProductName())
            .append("storageId", getStorageId())
            .append("storageName", getStorageName())

            .toString();
    }
}
