package com.photoTree.dto;

/**
 * @author: hailong
 * @date: 2022/6/3
 */
public class FcOrderDto {

    /**
     * 商品成交价格
     */
    private String usdtPrice;

    /**
     * 商品类型
     */
    private Integer type;

    public String getUsdtPrice() {
        return usdtPrice;
    }

    public void setUsdtPrice(String usdtPrice) {
        this.usdtPrice = usdtPrice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
