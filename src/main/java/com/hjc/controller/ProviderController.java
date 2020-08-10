package com.hjc.controller;

import com.alibaba.fastjson.JSON;
import com.hjc.pojo.Provider;
import com.hjc.service.provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    @Qualifier("providerService")
    private ProviderService providerService;

    @ResponseBody
    @RequestMapping("/getAjaxProviderList.do")
    public String getAjaxProviderList() {
        List<Provider> providerList = providerService.getProviderList();
        return JSON.toJSONString(providerList);
    }

}
