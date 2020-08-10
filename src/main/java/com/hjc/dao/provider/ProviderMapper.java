package com.hjc.dao.provider;

import com.hjc.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {
    List<Provider> getProviderList();

    Provider getProviderById(@Param("id") int id);
}
