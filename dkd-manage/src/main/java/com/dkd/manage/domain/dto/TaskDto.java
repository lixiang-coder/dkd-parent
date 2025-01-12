package com.dkd.manage.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskDto {
   
    private Long createType;// 创建类型
    private String innerCode;// 关联设备编号
    private Long userId;// 任务执行人Id
    private Long assignorId;// 用户创建人id
    private Long productTypeId;// 工单类型
    private String desc;// 描述信息
    private List<TaskDetailsDto> details;// 工单详情(只有补货工单才涉及)
}