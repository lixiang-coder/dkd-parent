package com.dkd.manage.domain.dto;

import lombok.Data;

// 某个货道对应的sku信息
@Data
public class ChannelSkuDto {
    // 售货机编号
    private String innerCode;
    // 货道编号
    private String channelCode;
    // 商品id
    private Long skuId;
}