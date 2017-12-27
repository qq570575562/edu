package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.ClassGrade;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface IClassGradeService {

	void deleteByPrimaryKey(Long id);

	void insert(ClassGrade record);

	ClassGrade selectByPrimaryKey(Long id);

	List<ClassGrade> selectAll();

	void updateByPrimaryKey(ClassGrade record);

	PageResult query(QueryObject qo);

    List<ClassGrade> queryByClassroomId(Long id);

    void saveDis(Long classId, Long empId);

    void openClass(Long id);
}
