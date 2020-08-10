package com.hjc.dao.bill;

import com.hjc.pojo.Bill;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BillMapperImpl implements BillMapper{

    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Bill> getBillList(String productName, int providerId, int isPayment) {
        return sqlSession.getMapper(BillMapper.class).getBillList(productName, providerId, isPayment);
    }

    @Override
    public int addBill(Bill bill) {
        return sqlSession.getMapper(BillMapper.class).addBill(bill);
    }

    @Override
    public Bill getBillById(int id) {
        return sqlSession.getMapper(BillMapper.class).getBillById(id);
    }

    @Override
    public int updateBill(Bill bill) {
        return sqlSession.getMapper(BillMapper.class).updateBill(bill);
    }

    @Override
    public int deleteBillById(int id) {
        return sqlSession.getMapper(BillMapper.class).deleteBillById(id);
    }
}
