package com.hjc.service.provider;

import com.hjc.pojo.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> getProviderList();

    Provider getProviderById(int id);

    List<Provider> getProviderList(String proCode, String proName);

    boolean addProvider(Provider provider);

    boolean updateProvider(Provider provider);

    boolean deleteProviderById(int id);
}
