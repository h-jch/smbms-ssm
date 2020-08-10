package com.hjc.dao.provider;

import com.hjc.pojo.Provider;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProviderMapperImpl implements ProviderMapper {

    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Provider> getProviderList() {
        return sqlSession.getMapper(ProviderMapper.class).getProviderList();
    }

    @Override
    public Provider getProviderById(int id) {
        return sqlSession.getMapper(ProviderMapper.class).getProviderById(id);
    }
}
