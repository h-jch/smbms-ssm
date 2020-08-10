package com.hjc.controller;

import com.alibaba.fastjson.JSON;
import com.hjc.pojo.Provider;
import com.hjc.pojo.User;
import com.hjc.service.bill.BillService;
import com.hjc.service.provider.ProviderService;
import com.hjc.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    @Qualifier("providerService")
    private ProviderService providerService;

    @Autowired
    @Qualifier("billService")
    private BillService billService;

    @ResponseBody
    @RequestMapping("/getAjaxProviderList.do")
    public String getAjaxProviderList() {
        List<Provider> providerList = providerService.getProviderList();
        return JSON.toJSONString(providerList);
    }

    @RequestMapping("provider.do")
    public String query(@RequestParam(value = "queryProCode", required = false) String proCode, @RequestParam(value = "queryProName", required = false) String proName, Model model) {
        List<Provider> providerList = providerService.getProviderList(proCode, proName);
        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProCode", proCode);
        model.addAttribute("queryProName", proName);
        return "providerlist";
    }

    @RequestMapping("/provideradd.do")
    public String toProviderAdd() {
        return "provideradd";
    }

    @RequestMapping("/add.do")
    public String providerAdd(HttpSession session, Provider provider) {
        int createId = ((User) session.getAttribute(Constants.USER_SESSION)).getId();
        provider.setCreatedBy(createId);
        provider.setCreationDate(new Date());
        if (providerService.addProvider(provider)) {
            return "redirect:/provider/provider.do";
        } else {
            return "provideradd";
        }
    }

    @RequestMapping("/view.do")
    public String providerView(@RequestParam("proid") int proId, Model model) {
        Provider provider = providerService.getProviderById(proId);
        model.addAttribute("provider", provider);
        return "providerview";
    }

    @RequestMapping("/modify.do")
    public String providerModify(@RequestParam("proid") int proId, Model model) {
        Provider provider = providerService.getProviderById(proId);
        model.addAttribute("provider", provider);
        return "providermodify";
    }

    @RequestMapping("/save.do")
    public String providerSave(Provider provider) {
        System.out.println(provider);
        if (providerService.updateProvider(provider)) {
            return "redirect:/provider/provider.do";
        } else {
            return "providermodify";
        }
    }

    @ResponseBody
    @RequestMapping("/del.do")
    public String providerDel(@RequestParam("proid") int proId) {
        String delResult;
        Provider provider = providerService.getProviderById(proId);
        //获得有proId的订单数量
        int count = billService.getCountByProId(proId);
        if (provider == null) {
            delResult = "notexist";
        } else if (count > 0) {
            delResult = String.valueOf(count);
        } else if (providerService.deleteProviderById(proId)) {
            delResult = "true";
        } else {
            delResult = "false";
        }
        return "{\"delResult\": \"" + delResult + "\"}";
    }
}
