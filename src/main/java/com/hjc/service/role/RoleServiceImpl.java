package com.hjc.service.role;

import com.hjc.dao.role.RoleMapper;
import com.hjc.pojo.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleMapper roleMapper;

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }
}
