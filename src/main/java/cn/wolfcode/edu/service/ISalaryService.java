package cn.wolfcode.edu.service;

import cn.wolfcode.edu.domain.Salary;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface ISalaryService {

	void deleteByPrimaryKey(Long id);

	void insert(Salary record);

	Salary selectByPrimaryKey(Long id);

	List<Salary> selectAll();

	void updateByPrimaryKey(Salary record);

	PageResult query(QueryObject qo);

	// void loadSalary();

	void updateSalary(Salary record);

	/**
	 * 加载
	 * 
	 * @param record
	 */
	void updateForRow(Salary record);
	/**
	 * 加载全部员工的考勤情况
	 * 
	 */
	void reloadAll();

	/**
	 * 计算工资
	 * 
	 * @param record
	 */
	void adjustSalary(Salary record);

	/**
	 * 结算工资
	 * 
	 * @param record
	 */
	void payOff(Salary record);

}
