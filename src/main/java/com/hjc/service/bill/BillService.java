package com.hjc.service.bill;

import com.hjc.pojo.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getBillList(String productName, int providerId, int isPayment);

    boolean addBill(Bill bill);

    Bill getBillById(int id);

    boolean updateBill(Bill bill);

    boolean deleteBillById(int id);
}
