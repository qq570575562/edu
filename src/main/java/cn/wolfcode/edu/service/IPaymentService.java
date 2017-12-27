package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.Payment;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.PaymentQueryObject;

import java.util.List;

public interface IPaymentService {

	void deleteByPrimaryKey(Long id);

	void insert(Payment record);

	Payment selectByPrimaryKey(Long id);

	List<Payment> selectAll();

	void updateByPrimaryKey(Payment record);

	PageResult query(PaymentQueryObject qo);

    void audit(Long id);
}
