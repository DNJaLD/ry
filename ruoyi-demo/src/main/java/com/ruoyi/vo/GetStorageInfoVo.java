package com.ruoyi.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

@Data
public class GetStorageInfoVo {

    /** id */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long productNumber;

    private Long oldProductNumber;

    /** 仓库名 */
    @Excel(name = "仓库名")
    private String stortageName;

    private Long productPrice ;


    /**  记录该仓库中该商品的所有数量 */
    private Long allNumber ;

    /**  记录该仓库中该商品总价 */
    private Long money ;
}
