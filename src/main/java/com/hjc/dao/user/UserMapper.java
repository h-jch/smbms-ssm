package com.hjc.dao.user;

import com.hjc.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 添加用户
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 通过userCode获得user
     * @param userCode
     * @return
     */
    User getLoginUser(@Param("userCode") String userCode);

    /**
     * 条件查询userList
     * @param userName
     * @param userRole
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    List<User> getUserList(@Param("userName") String userName, @Param("userRole") int userRole, @Param("startNo") int startNo, @Param("pageSize") int pageSize);

    /**
     * 条件查询用户数目
     * @param userName
     * @param userRole
     * @return
     */
    int getUserCount(@Param("userName") String userName, @Param("userRole") int userRole);

    /**
     * 通过id删除用户
     * @param delId
     * @return
     */
    int deleteUserById(Integer delId);

    /**
     * 通过id获得用户
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int modify(User user);

    /**
     * 修改用户密码
     * @param id
     * @param pwd
     * @return
     */
    int updatePwd(int id, String pwd);
}
