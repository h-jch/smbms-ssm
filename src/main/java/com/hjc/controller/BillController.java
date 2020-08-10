package com.hjc.controller;

import com.hjc.pojo.Bill;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    @Qualifier("billService")
    private BillService billService;

    @Autowired
    @Qualifier("providerService")
    private ProviderService providerService;

    @RequestMapping(value = "/bill.do", method = RequestMethod.GET)
    public String query(@RequestParam(value = "queryProductName", required = false) String productName, @RequestParam(value = "queryProviderId", required = false) String providerId, @RequestParam(value = "queryIsPayment", required = false) String isPayment, Model model) {
        System.out.println("bill.do======================");
        int proId = 0;
        if (providerId != null) {
            proId = Integer.parseInt(providerId);
        }
        int isPayed = 0;
        if (isPayment != null && isPayment.equals("未付款")) {
            isPayed = 1;
        } else if(isPayment != null && isPayment.equals("已付款")) {
            isPayed = 2;
        }
        List<Bill> billList = billService.getBillList(productName, proId, isPayed);
        model.addAttribute("billList", billList);
        model.addAttribute("queryProductName", productName);
        model.addAttribute("queryIsPayment", isPayment);
        model.addAttribute("proId", proId);
        /*session.setAttribute("billList", billList);
        session.setAttribute("queryProductName", productName);
        session.setAttribute("queryIsPayment", isPayment);
        session.setAttribute("proId", proId);*/

        //获取供应商列表
        List<Provider> providerList = providerService.getProviderList();
        model.addAttribute("providerList", providerList);
        return "billlist";
    }

    @RequestMapping("/billadd.do")
    public String toBillAdd() {
        return "billadd";
    }

    @RequestMapping("/add.do")
    public String billAdd(HttpSession session, Bill bill) {
        int createId = ((User) session.getAttribute(Constants.USER_SESSION)).getId();
        bill.setCreatedBy(createId);
        bill.setCreationDate(new Date());
        System.out.println(bill);

        if (billService.addBill(bill)) {
            return "redirect:/bill/bill.do";
        } else {
            return "billadd";
        }
    }

    @RequestMapping("/view.do")
    public String billView(@RequestParam("billid") int billId, Model model) {
        Bill bill = billService.getBillById(billId);
        int providerId = bill.getProviderId();
        Provider provider = providerService.getProviderById(providerId);
        bill.setProviderName(provider.getProName());
        System.out.println(bill);
        model.addAttribute("bill", bill);
        return "billview";
    }

    @RequestMapping("/modify.do")
    public String billModify(@RequestParam("billid") int billId, Model model) {
        Bill bill = billService.getBillById(billId);
        List<Provider> providerList = providerService.getProviderList();
        model.addAttribute("bill", bill);
        model.addAttribute("providerList", providerList);
        return "billmodify";
    }

    @RequestMapping("/save.do")
    public String billSave(HttpSession session, Bill bill) {
        int modifyId = ((User) session.getAttribute(Constants.USER_SESSION)).getId();
        bill.setModifyBy(modifyId);
        bill.setModifyDate(new Date());
        if (billService.updateBill(bill)) {
            return "redirect:/bill/bill.do";
        } else {
            return "billmodify";
        }
    }

    @ResponseBody
    @RequestMapping("/del.do")
    public String billDel(@RequestParam("billid") int billId) {
        String delResult;
        Bill bill = billService.getBillById(billId);
        if (bill == null) {
            delResult = "notexist";
        } else if (billService.deleteBillById(billId)) {
            delResult = "true";
        } else {
            delResult = "false";
        }
        return "{\"delResult\": \"" + delResult + "\"}";
    }
}
