package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.PromotedStudent;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface IPromotedStudentService {

	void deleteByPrimaryKey(Long id);

	void insert(PromotedStudent record);

	PromotedStudent selectByPrimaryKey(Long id);

	List<PromotedStudent> selectAll();

	void updateByPrimaryKey(PromotedStudent record);

	PageResult query(QueryObject qo);

    void changeState(Long id);

    void transStudent(Long[] ids, Long afterClassId);
}
