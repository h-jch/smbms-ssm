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

    @Override
    public List<Provider> getProviderListByKey(String proCode, String proName) {
        return sqlSession.getMapper(ProviderMapper.class).getProviderListByKey(proCode, proName);
    }

    @Override
    public int addProvider(Provider provider) {
        return sqlSession.getMapper(ProviderMapper.class).addProvider(provider);
    }

    @Override
    public int updateProvider(Provider provider) {
        return sqlSession.getMapper(ProviderMapper.class).updateProvider(provider);
    }

    @Override
    public int deleteProviderById(int id) {
        return sqlSession.getMapper(ProviderMapper.class).deleteProviderById(id);
    }
}
