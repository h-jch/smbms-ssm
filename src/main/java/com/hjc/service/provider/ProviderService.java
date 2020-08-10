package com.hjc.service.provider;

import com.hjc.pojo.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> getProviderList();

    Provider getProviderById(int id);
}
