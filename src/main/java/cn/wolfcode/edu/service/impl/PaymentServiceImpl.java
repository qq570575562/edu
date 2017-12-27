package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.domain.Payment;
import cn.wolfcode.edu.mapper.PaymentMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.PaymentQueryObject;
import cn.wolfcode.edu.service.IPaymentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PaymentServiceImpl implements IPaymentService{

	@Autowired
	private PaymentMapper paymentMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		paymentMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(Payment record) {
		paymentMapper.insert(record);
	}
	@Override
	public Payment selectByPrimaryKey(Long id) {
		return paymentMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Payment> selectAll() {
		return paymentMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(Payment record) {
		paymentMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult query(PaymentQueryObject qo) {
		int total = paymentMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<Payment> rows = paymentMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

	@Override
	public void audit(Long id) {
		Subject subject = SecurityUtils.getSubject();
		Employee principal = (Employee) subject.getPrincipal();
		paymentMapper.audit(principal.getId(),id);
	}
}
