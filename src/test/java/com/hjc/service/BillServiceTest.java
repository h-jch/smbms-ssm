package com.hjc.service;

import com.hjc.pojo.Bill;
import com.hjc.service.bill.BillService;
import com.hjc.service.bill.BillServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BillServiceTest {

    private ApplicationContext context;
    private BillService billService;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        billService = context.getBean("billService", BillServiceImpl.class);
    }

    @Test
    public void testGetBillList() {
        String productName = "";
        int providerId = 1;
        int isPayment = 1;
        List<Bill> billList = billService.getBillList(productName, providerId, isPayment);
        for (Bill bill : billList) {
            System.out.println(bill);
        }
    }

    @Test
    public void testAddBill() {
        Bill bill = new Bill();
        bill.setBillCode("test");
        bill.setProductName("test");
        bill.setProductUnit("test");
        bill.setProductCount(new BigDecimal(1));
        bill.setTotalPrice(new BigDecimal(111));
        bill.setIsPayment(1);
        bill.setProviderId(1);
        bill.setCreatedBy(1);
        bill.setCreationDate(new Date());
        if (billService.addBill(bill)) {
            System.out.println(bill);
        } else {
            System.out.println("add failed");
        }
    }

    @Test
    public void testGetBillById() {
        int id = 10;
        Bill bill = billService.getBillById(id);
        System.out.println(bill);
    }
}
