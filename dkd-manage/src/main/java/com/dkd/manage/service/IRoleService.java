package com.dkd.manage.service;

import java.util.List;
import com.dkd.manage.domain.Role;

/**
 * 角色管理Service接口
 * 
 * @author xzy
 * @date 2025-01-07
 */
public interface IRoleService 
{
    /**
     * 查询角色管理
     * 
     * @param roleId 角色管理主键
     * @return 角色管理
     */
    public Role selectRoleByRoleId(Long roleId);

    /**
     * 查询角色管理列表
     * 
     * @param role 角色管理
     * @return 角色管理集合
     */
    public List<Role> selectRoleList(Role role);

    /**
     * 新增角色管理
     * 
     * @param role 角色管理
     * @return 结果
     */
    public int insertRole(Role role);

    /**
     * 修改角色管理
     * 
     * @param role 角色管理
     * @return 结果
     */
    public int updateRole(Role role);

    /**
     * 批量删除角色管理
     * 
     * @param roleIds 需要删除的角色管理主键集合
     * @return 结果
     */
    public int deleteRoleByRoleIds(Long[] roleIds);

    /**
     * 删除角色管理信息
     * 
     * @param roleId 角色管理主键
     * @return 结果
     */
    public int deleteRoleByRoleId(Long roleId);
}
