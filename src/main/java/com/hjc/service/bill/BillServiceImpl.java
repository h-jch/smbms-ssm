package com.hjc.service.bill;

import com.hjc.dao.bill.BillMapper;
import com.hjc.dao.bill.BillMapperImpl;
import com.hjc.pojo.Bill;

import java.util.List;

public class BillServiceImpl implements BillService {

    private BillMapper billMapper;

    public void setBillMapper(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    @Override
    public List<Bill> getBillList(String productName, int providerId, int isPayment) {
        return billMapper.getBillList(productName, providerId, isPayment);
    }

    @Override
    public boolean addBill(Bill bill) {
        if (billMapper.addBill(bill) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Bill getBillById(int id) {
        return billMapper.getBillById(id);
    }

    @Override
    public boolean updateBill(Bill bill) {
        if (billMapper.updateBill(bill) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteBillById(int id) {
        if (billMapper.deleteBillById(id) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getCountByProId(int proId) {
        return billMapper.getCountByProId(proId);
    }
}
