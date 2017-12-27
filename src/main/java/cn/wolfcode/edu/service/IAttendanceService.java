package cn.wolfcode.edu.service;

import cn.wolfcode.edu.domain.Attendance;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface IAttendanceService {

	void deleteByPrimaryKey(Long id);

	void signIn();
	void signOut();
	void resignIn(Attendance record);

	Attendance selectByPrimaryKey(Long id);

	List<Attendance> selectAll();

	void updateByPrimaryKey(Attendance record);

	PageResult query(QueryObject qo);

}