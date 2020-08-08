package com.hjc.service.user;

import com.hjc.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * 用户登录
     * @param userCode
     * @param userPassword
     * @return
     */
    User login(String userCode, String userPassword);

    /**
     * 条件查询userList
     * @param userName
     * @param userRole
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize);

    /**
     * 条件查询用户数目
     * @param userName
     * @param userRole
     * @return
     */
    int getUserCount(String userName, int userRole);

    /**
     * 根据userCode查询出User
     * @param userCode
     * @return
     */
    User getUserByUserCode(String userCode);

    /**
     * 根据ID删除user
     * @param delId
     * @return
     */
    boolean deleteUserById(Integer delId);

    /**
     * 根据ID查找user
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    boolean modify(User user);

    /**
     * 根据userId修改密码
     * @param id
     * @param pwd
     * @return
     */
    boolean updatePwd(int id, String pwd);
}
