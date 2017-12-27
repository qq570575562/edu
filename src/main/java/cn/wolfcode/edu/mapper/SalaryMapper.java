package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Salary;
import cn.wolfcode.edu.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SalaryMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Salary record);
	int insertSalary(Salary record);

	Salary selectByPrimaryKey(Long id);

	List<Salary> selectAll();
	Salary selectByMonth(@Param("employeeId") Long employeeId, @Param("month") Date month);

	int updateByPrimaryKey(Salary record);
	int queryForCount(QueryObject qo);

	List<Salary> queryForList(QueryObject qo);

	void updateSalary(Salary record);
	void updateForRow(Salary record);
}