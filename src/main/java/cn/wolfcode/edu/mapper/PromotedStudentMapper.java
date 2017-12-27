package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.PromotedStudent;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface PromotedStudentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PromotedStudent record);

    PromotedStudent selectByPrimaryKey(Long id);

    List<PromotedStudent> selectAll();

    int updateByPrimaryKey(PromotedStudent record);

    int queryForCount(QueryObject qo);

    List<PromotedStudent> queryForList(QueryObject qo);

    void changeState( Long id);
}