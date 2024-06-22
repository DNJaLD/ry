package com.ruoyi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * cangku对象 storage
 *
 * @author ruoyi
 * @date 2024-06-19
 */
public class Storage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 仓库名 */
    @Excel(name = "仓库名")
    private String storageName;

    /** 商品名 */
    @Excel(name = "商品名")
    private String productName;

    /** 商品id */
    private Long productId;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long prouctNumber;

    @Excel(name="danjia")
    private Long productPrice ;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setStorageName(String storageName)
    {
        this.storageName = storageName;
    }

    public String getStorageName()
    {
        return storageName;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setProuctNumber(Long prouctNumber)
    {
        this.prouctNumber = prouctNumber;
    }

    public Long getProuctNumber()
    {
        return prouctNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storageName", getStorageName())
            .append("productName", getProductName())
            .append("productId", getProductId())
            .append("prouctNumber", getProuctNumber())
                .append("price", getProductPrice())
            .toString();
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }
}
