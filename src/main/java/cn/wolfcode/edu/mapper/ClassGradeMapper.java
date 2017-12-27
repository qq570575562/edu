package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.ClassGrade;
import cn.wolfcode.edu.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassGradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassGrade record);

    ClassGrade selectByPrimaryKey(Long id);

    List<ClassGrade> selectAll();

    int updateByPrimaryKey(ClassGrade record);

    int queryForCount(QueryObject  qo);

    List<ClassGrade> queryForList(QueryObject qo);

    List<ClassGrade> queryByClassroomId(Long id);

    void saveDis(@Param("classId")Long classId, @Param("empId") Long empId);

    void openClass(Long id);
}