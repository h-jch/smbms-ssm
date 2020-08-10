package com.hjc.dao.provider;

import com.hjc.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {
    List<Provider> getProviderList();

    Provider getProviderById(@Param("id") int id);

    List<Provider> getProviderListByKey(@Param("proCode") String proCode, @Param("proName") String proName);

    int addProvider(Provider provider);

    int updateProvider(Provider provider);

    int deleteProviderById(@Param("id") int id);
}
