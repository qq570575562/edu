package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.domain.Salary;
import cn.wolfcode.edu.mapper.AttendanceMapper;
import cn.wolfcode.edu.mapper.EmployeeMapper;
import cn.wolfcode.edu.mapper.SalaryMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SalaryServiceImpl implements ISalaryService {

	@Autowired
	private SalaryMapper salaryMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private AttendanceMapper attendanceMapper;

	@Override
	public void deleteByPrimaryKey(Long id) {
		salaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Salary record) {
		salaryMapper.insert(record);
	}

	@Override
	public Salary selectByPrimaryKey(Long id) {
		return salaryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Salary> selectAll() {

		return salaryMapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(Salary record) {

		salaryMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult query(QueryObject qo) {
		int total = salaryMapper.queryForCount(qo);
		if (total == 0) {
			return new PageResult(0, Collections.emptyList());
		}
		List<Salary> rows = salaryMapper.queryForList(qo);
		return new PageResult(total, rows);
	}

	/**
	 * 修改薪资
	 */
	@Override
	public void updateSalary(Salary record) {

		salaryMapper.updateSalary(record);
	}
	/**
	 * 核算出勤
	 */
	@Override
	public void updateForRow(Salary record) {
		System.out.println(record);
		salaryMapper.updateForRow(record);
	}
	/**
	 * 核算出勤
	 */
	@Override
	public void reloadAll() {
		// 获取全部员工的id
		List<Employee> employees = employeeMapper.selectAll();

		// 根据员工的id和日期查询考勤情况
		for (Employee employee : employees) {
			// 判断本月是否结算

			// 判断是否签到
			Long attendances = attendanceMapper.checkSignInByMonth(employee.getId(), new Date());
			if (attendances > 0) {
				Salary ret = salaryMapper.selectByMonth(employee.getId(), new Date());
				if (ret != null) {
					if (ret.getPaytime() != null || ret.getNowSalary() == null) {
						continue;
					} else {
						salaryMapper.updateForRow(ret);
					}
				} else {
					Salary salary = new Salary();
					salary.setEmployee(employee);
					salary.setMonth(new Date());

					salary.setSalary(employee.getSalary());
					salaryMapper.insertSalary(salary);
				}
			}
		}
	}

	/**
	 * 计算工资
	 * 
	 * @param record
	 */
	@Override
	public void adjustSalary(Salary record) {
		// 迟到移除罚50 早退异常罚 100
		Integer afterNumber = record.getAfterNumber();
		Integer beforeNumber = record.getBeforeNumber();

		Integer penalty = afterNumber * 50 + beforeNumber * 100;

		// 获取工资
		BigDecimal newSalaey = record.getSalary().subtract(new BigDecimal(penalty));
		// 设置
		record.setNowSalary(newSalaey);

		salaryMapper.updateByPrimaryKey(record);

	}

	@Override
	public void payOff(Salary record) {
		record.setPaytime(new Date());
		if (record.getNowSalary() != null) {
			salaryMapper.updateByPrimaryKey(record);
		} else {
			throw new RuntimeException("薪资还未核算,请核算后在操作");
		}
	}

}
