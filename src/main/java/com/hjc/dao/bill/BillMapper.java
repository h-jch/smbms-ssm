package com.hjc.dao.bill;

import com.hjc.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {

    /**
     * 获得订单列表
     * @param productName
     * @param providerId
     * @param isPayment
     * @return
     */
    List<Bill> getBillList(@Param("productName") String productName, @Param("providerId") int providerId, @Param("isPayment") int isPayment);

    int addBill(Bill bill);

    Bill getBillById(@Param("id") int id);

    int updateBill(Bill bill);

    int deleteBillById(@Param("id") int id);
}
