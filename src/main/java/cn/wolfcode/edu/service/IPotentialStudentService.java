package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.PotentialStudent;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.PotentialStudentQueryObject;

import java.util.List;

public interface IPotentialStudentService {
	void deleteByPrimaryKey(Long id);

	void insert(PotentialStudent record);

	PotentialStudent selectByPrimaryKey(Long id);

	List<PotentialStudent> selectAll();

	void updateByPrimaryKey(PotentialStudent record);

	PageResult query(PotentialStudentQueryObject qo);

	void changState(Long id);

	void tailnum(Long id);

	List<PotentialStudent> checkstu();

	void toPool(Long id);
}
