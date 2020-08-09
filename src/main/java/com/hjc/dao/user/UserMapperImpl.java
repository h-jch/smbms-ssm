package com.hjc.dao.user;

import com.hjc.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserMapperImpl implements UserMapper {

    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public int add(User user) {
        return sqlSession.getMapper(UserMapper.class).add(user);
    }

    @Override
    public User getLoginUser(String userCode) {
        return sqlSession.getMapper(UserMapper.class).getLoginUser(userCode);
    }

    @Override
    public List<User> getUserList(String userName, int userRole, int startNo, int pageSize) {
        return sqlSession.getMapper(UserMapper.class).getUserList(userName, userRole, startNo, pageSize);
    }

    @Override
    public int getUserCount(String userName, int userRole) {
        return sqlSession.getMapper(UserMapper.class).getUserCount(userName, userRole);
    }

    @Override
    public int deleteUserById(int delId) {
        return sqlSession.getMapper(UserMapper.class).deleteUserById(delId);
    }

    @Override
    public User getUserById(int id) {
        return sqlSession.getMapper(UserMapper.class).getUserById(id);
    }

    @Override
    public int modify(User user) {
        return sqlSession.getMapper(UserMapper.class).modify(user);
    }

    @Override
    public int updatePwd(int id, String pwd) {
        return sqlSession.getMapper(UserMapper.class).updatePwd(id, pwd);
    }
}
