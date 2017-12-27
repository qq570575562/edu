package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.FormalStudent;
import cn.wolfcode.edu.domain.Runoff;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface IFormalStudentService {

	void deleteByPrimaryKey(Long id);

	void insert(FormalStudent record);

	FormalStudent selectByPrimaryKey(Long id);

	List<FormalStudent> selectAll();

	void updateByPrimaryKey(FormalStudent record);

	PageResult query(QueryObject qo);

	void changeState(Long id, Byte statVal);

	BigDecimal selectOwnTuitionByid(Long id);
	//学员流失
	void leavingStudent(Long formalStuId, Runoff runoff);
}
