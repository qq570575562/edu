package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.StudentTail;
import cn.wolfcode.edu.query.StudentTailQueryObject;

import java.util.List;

public interface StudentTailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentTail record);

    StudentTail selectByPrimaryKey(Long id);

    List<StudentTail> selectAll();

    int updateByPrimaryKey(StudentTail record);

    int queryCount(StudentTailQueryObject qo);

    List<StudentTail> queryList(StudentTailQueryObject qo);

    void changState(StudentTail studentTail);
}