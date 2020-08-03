package com.hjc.service.user;

import com.hjc.dao.user.UserMapper;
import com.hjc.pojo.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean add(User user) {
        boolean flag = false;
        int updateRows = userMapper.add(user);
        if (updateRows > 0) {
            flag = true;
            System.out.println("add success!");
        } else {
            System.out.println("add failed!");
        }
        return flag;
    }

    @Override
    public User login(String userCode, String userPassword) {
        User user = userMapper.getLoginUser(userCode);
        if (user != null && !user.getUserPassword().equals(userPassword)) {
            user = null;
        }
        return user;
    }

    @Override
    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize) {
        return null;
    }

    @Override
    public int getUserCount(String userName, int userRole) {
        return 0;
    }

    @Override
    public User selectUserCodeExist(String userCode) {
        return null;
    }

    @Override
    public boolean deleteUserById(Integer delId) {
        return false;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public boolean modify(User user) {
        return false;
    }

    @Override
    public boolean updatePwd(int id, String pwd) {
        return false;
    }
}
