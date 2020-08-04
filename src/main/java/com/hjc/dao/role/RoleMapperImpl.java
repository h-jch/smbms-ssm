package com.hjc.dao.role;

import com.hjc.pojo.Role;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RoleMapperImpl implements RoleMapper {

    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Role> getRoleList() {
        return sqlSession.getMapper(RoleMapper.class).getRoleList();
    }
}
