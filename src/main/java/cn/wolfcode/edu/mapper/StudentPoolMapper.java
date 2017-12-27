package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.PotentialStudent;
import cn.wolfcode.edu.domain.StudentPool;
import cn.wolfcode.edu.query.StudentPoolQueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPoolMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PotentialStudent record);

    StudentPool selectByPrimaryKey(Long id);

    int queryCount(StudentPoolQueryObject qo);

	List<StudentPool> selectAll();

    List<StudentPool> queryList(StudentPoolQueryObject qo);

    int updateByPrimaryKey(StudentPool record);

    void changState(Long id);
}