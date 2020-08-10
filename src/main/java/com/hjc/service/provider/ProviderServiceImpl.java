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

    @Override
    public List<Provider> getProviderList(String proCode, String proName) {
        return providerMapper.getProviderListByKey(proCode, proName);
    }

    @Override
    public boolean addProvider(Provider provider) {
        if (providerMapper.addProvider(provider) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateProvider(Provider provider) {
        if (providerMapper.updateProvider(provider) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteProviderById(int id) {
        if (providerMapper.deleteProviderById(id) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
