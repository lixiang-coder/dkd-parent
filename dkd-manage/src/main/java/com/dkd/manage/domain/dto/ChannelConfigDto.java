package com.dkd.manage.domain.dto;

import lombok.Data;

import java.util.List;

// 售货机货道配置
@Data
public class ChannelConfigDto {

    // 售货机编号
    private String innerCode;
    // 货道配置
    private List<ChannelSkuDto> channelList;
}