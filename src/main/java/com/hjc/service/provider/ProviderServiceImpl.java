package com.hjc.service.provider;

import com.hjc.dao.provider.ProviderMapper;
import com.hjc.pojo.Provider;

import java.util.List;

public class ProviderServiceImpl implements ProviderService {

    private ProviderMapper providerMapper;

    public void setProviderMapper(ProviderMapper providerMapper) {
        this.providerMapper = providerMapper;
    }

    @Override
    public List<Provider> getProviderList() {
        return providerMapper.getProviderList();
    }

    @Override
    public Provider getProviderById(int id) {
        return providerMapper.getProviderById(id);
    }
}
