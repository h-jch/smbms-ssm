package com.hjc.service.role;

import com.hjc.pojo.Role;

import java.util.List;

public interface RoleService {
    /**
     * 获得Role的全部信息
     * @return
     */
    List<Role> getRoleList();
}
