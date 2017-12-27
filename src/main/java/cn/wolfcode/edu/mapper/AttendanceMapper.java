package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Attendance;
import cn.wolfcode.edu.query.QueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface AttendanceMapper {

	int deleteByPrimaryKey(Long id);

	int insert(Attendance record);

	Attendance selectByPrimaryKey(Long id);

	List<Attendance> selectAll();

	int updateByPrimaryKey(Attendance record);

	int queryForCount(QueryObject qo);

	List<Attendance> queryForList(QueryObject qo);

	Attendance checkSignIn(@Param("employeeId") Long id, @Param("signDate") Date date);
	
	Long checkSignInByMonth(@Param("employeeId") Long id, @Param("month") Date month);

	void signOut(Attendance record);
}