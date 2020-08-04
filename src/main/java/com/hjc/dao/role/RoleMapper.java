package com.hjc.dao.role;

import com.hjc.pojo.Role;

import java.util.List;

public interface RoleMapper {
    /**
     * 获得Role的全部数据
     * @return
     */
    List<Role> getRoleList();
}
