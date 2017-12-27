package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.domain.FormalStudent;
import cn.wolfcode.edu.domain.IncomeItem;
import cn.wolfcode.edu.mapper.FormalStudentMapper;
import cn.wolfcode.edu.mapper.IncomeItemMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IIncomeItemService;
import cn.wolfcode.edu.util.MoneyUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class IncomeItemServiceImpl implements IIncomeItemService{

	@Autowired
	private IncomeItemMapper incomeItemMapper;
	@Autowired
	private FormalStudentMapper formalStudentMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		incomeItemMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(IncomeItem record) {

		//处理名字
		FormalStudent formalStudent = formalStudentMapper.selectByPrimaryKey(record.getFormalstudentId());
		record.setName(formalStudent.getName());
		//处理收款问题
		if(!MoneyUtil.compareMoney(record.getInMoney())){
			throw new RuntimeException("收款金额异常");
		}

		//1.  OwnTuition = OwnTuition -inMoney
		formalStudent.setOwnTuition(formalStudent.getOwnTuition().subtract(record.getInMoney()));
		//2.已交学费, PaidTuition = TotalTuition - OwnTuition
		formalStudent.setPaidTuition(formalStudent.getTotalTuition().subtract(formalStudent.getOwnTuition()));
		//  @如果学费交齐了,或者变多了,要修改支付状态为交付
		if(formalStudent.getTotalTuition().compareTo(formalStudent.getPaidTuition()) <= 0){
			formalStudentMapper.setpaidState(true);
		}
		//3.修改最后一次修改时间
		formalStudent.setLastPaymentDate(new Date());
		//更新2个位置
		formalStudentMapper.updateIncomeItem(formalStudent);
		incomeItemMapper.insert(record);

	}
	@Override
	public IncomeItem selectByPrimaryKey(Long id) {
		return incomeItemMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<IncomeItem> selectAll() {
		return incomeItemMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(IncomeItem record) {

		//获得学生

		System.out.println("================================="+record.getFormalstudentId());
		FormalStudent formalStudent = formalStudentMapper.selectByPrimaryKey(record.getFormalstudentId());


		//1.OwnTuition
		//更新数据,拿到之前的数据,计算差距
		IncomeItem incomeItem = incomeItemMapper.selectByPrimaryKey(record.getId());

		//差距
		BigDecimal subInMoney = record.getInMoney().subtract(incomeItem.getInMoney());
		formalStudent.setOwnTuition(formalStudent.getOwnTuition().subtract(subInMoney));

		//2.已交学费, PaidTuition = TotalTuition - OwnTuition
		formalStudent.setPaidTuition(formalStudent.getTotalTuition().subtract(formalStudent.getOwnTuition()));

		//3.修改最后一次修改时间
		formalStudent.setLastPaymentDate(new Date());

		formalStudentMapper.updateIncomeItem(formalStudent);
		incomeItemMapper.updateByPrimaryKey(record);

	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = incomeItemMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<IncomeItem> rows = incomeItemMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

	/**
	 * 修改状态
	 * @param id
	 */
	@Override
	public void changeState(Long id) {

		incomeItemMapper.changeState(id);
		//更新审核人
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		incomeItemMapper.updateAuditor(id,employee.getRealname());
	}
}
