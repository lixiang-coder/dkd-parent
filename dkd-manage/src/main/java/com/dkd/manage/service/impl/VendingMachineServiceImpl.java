package com.dkd.manage.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import com.dkd.common.constant.DkdContants;
import com.dkd.common.utils.DateUtils;
import com.dkd.common.utils.uuid.UUIDUtils;
import com.dkd.manage.domain.Channel;
import com.dkd.manage.domain.Node;
import com.dkd.manage.domain.VmType;
import com.dkd.manage.mapper.ChannelMapper;
import com.dkd.manage.mapper.NodeMapper;
import com.dkd.manage.mapper.VmTypeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.VendingMachineMapper;
import com.dkd.manage.domain.VendingMachine;
import com.dkd.manage.service.IVendingMachineService;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NamingEnumeration;

/**
 * 设备管理Service业务层处理
 * 
 * @author xzy
 * @date 2025-01-08
 */
@Service
public class VendingMachineServiceImpl implements IVendingMachineService 
{
    @Autowired
    private VendingMachineMapper vendingMachineMapper;

    @Autowired
    private VmTypeMapper vmTypeMapper;

    @Autowired
    private NodeMapper nodeMapper;

    @Autowired
    private ChannelMapper channelMapper;

    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    @Override
    public VendingMachine selectVendingMachineById(Long id)
    {
        return vendingMachineMapper.selectVendingMachineById(id);
    }

    /**
     * 查询设备管理列表
     * 
     * @param vendingMachine 设备管理
     * @return 设备管理
     */
    @Override
    public List<VendingMachine> selectVendingMachineList(VendingMachine vendingMachine)
    {
        return vendingMachineMapper.selectVendingMachineList(vendingMachine);
    }

    /**
     * 新增设备管理
     * 
     * @param vendingMachine 设备管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertVendingMachine(VendingMachine vendingMachine)
    {
        // 1.新增设备
        // 1.1 生成8位编码，补充设备编号
        vendingMachine.setInnerCode(UUIDUtils.getUUID());
        // 1.2 补充设备容量（从设备类型表中查出）
        VmType vmType = vmTypeMapper.selectVmTypeById(vendingMachine.getVmTypeId());
        vendingMachine.setChannelMaxCapacity(vmType.getChannelMaxCapacity());
        // 1.3 查询点位表，补充详细地址、商圈类型、区域id、合作商id
        Node node = nodeMapper.selectNodeById(vendingMachine.getNodeId());
        BeanUtils.copyProperties(node, vendingMachine,"id");
        vendingMachine.setAddr(node.getAddress()); //注意这个字段名不一致，故手动设置
        // 1.4 设备状态
        vendingMachine.setVmStatus(DkdContants.VM_STATUS_NODEPLOY); //未投放

        // 1.5 创建、修改时间
        vendingMachine.setCreateTime(DateUtils.getNowDate());
        vendingMachine.setUpdateTime(DateUtils.getNowDate());

        // 1.6 保存到数据库中
        int result = vendingMachineMapper.insertVendingMachine(vendingMachine);

        // 2.新增货道
        List<Channel> channelList = new ArrayList<>();
        for (int i = 0; i < vmType.getVmRow(); i++) {
            for (int l = 0; l < vmType.getVmCol(); l++) {
                // 货道编号 1-1、1-2、1-3...
                Channel channel = new Channel();
                // 2.1 封装channel对象
                channel.setChannelCode(i + "-" + l); //设置货道编号
                channel.setVmId(vendingMachine.getId()); //设置货道机id
                channel.setInnerCode(vendingMachine.getInnerCode()); //设置货道机编号
                channel.setMaxCapacity(vmType.getChannelMaxCapacity()); //设置货道最大容量
                channel.setCreateTime(DateUtils.getNowDate());
                channel.setUpdateTime(DateUtils.getNowDate());

                channelList.add(channel);
            }
        }
        // 2.2批量新增
        channelMapper.insertChannelBatch(channelList);

        return result;
    }

    /**
     * 修改设备管理
     * 
     * @param vendingMachine 设备管理
     * @return 结果
     */
    @Override
    public int updateVendingMachine(VendingMachine vendingMachine)
    {
        // 查询点位表，补充 区域、点位、合作商等信息
        Node node = nodeMapper.selectNodeById(vendingMachine.getNodeId());
        BeanUtil.copyProperties(node, vendingMachine, "id");// 商圈类型、区域、合作商
        vendingMachine.setAddr(node.getAddress());// 设备地址
        vendingMachine.setUpdateTime(DateUtils.getNowDate());// 更新时间
        return vendingMachineMapper.updateVendingMachine(vendingMachine);
    }

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineByIds(Long[] ids)
    {
        return vendingMachineMapper.deleteVendingMachineByIds(ids);
    }

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteVendingMachineById(Long id)
    {
        return vendingMachineMapper.deleteVendingMachineById(id);
    }
}
