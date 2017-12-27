package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.PotentialStudent;
import cn.wolfcode.edu.query.PotentialStudentQueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PotentialStudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PotentialStudent record);

    PotentialStudent selectByPrimaryKey(Long id);

    int queryCount(PotentialStudentQueryObject qo);

	List<PotentialStudent> selectAll();

    List<PotentialStudent> queryList(PotentialStudentQueryObject qo);

    int updateByPrimaryKey(PotentialStudent record);

    void changState(Long id);

    void tailnum(Long id);

	List<PotentialStudent> checkstu();
}