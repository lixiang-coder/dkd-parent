package com.dkd.manage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.RoleMapper;
import com.dkd.manage.domain.Role;
import com.dkd.manage.service.IRoleService;

/**
 * 角色管理Service业务层处理
 * 
 * @author xzy
 * @date 2025-01-07
 */
@Service
public class RoleServiceImpl implements IRoleService 
{
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询角色管理
     * 
     * @param roleId 角色管理主键
     * @return 角色管理
     */
    @Override
    public Role selectRoleByRoleId(Long roleId)
    {
        return roleMapper.selectRoleByRoleId(roleId);
    }

    /**
     * 查询角色管理列表
     * 
     * @param role 角色管理
     * @return 角色管理
     */
    @Override
    public List<Role> selectRoleList(Role role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 新增角色管理
     * 
     * @param role 角色管理
     * @return 结果
     */
    @Override
    public int insertRole(Role role)
    {
        return roleMapper.insertRole(role);
    }

    /**
     * 修改角色管理
     * 
     * @param role 角色管理
     * @return 结果
     */
    @Override
    public int updateRole(Role role)
    {
        return roleMapper.updateRole(role);
    }

    /**
     * 批量删除角色管理
     * 
     * @param roleIds 需要删除的角色管理主键
     * @return 结果
     */
    @Override
    public int deleteRoleByRoleIds(Long[] roleIds)
    {
        return roleMapper.deleteRoleByRoleIds(roleIds);
    }

    /**
     * 删除角色管理信息
     * 
     * @param roleId 角色管理主键
     * @return 结果
     */
    @Override
    public int deleteRoleByRoleId(Long roleId)
    {
        return roleMapper.deleteRoleByRoleId(roleId);
    }
}
