package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Classroom;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface ClassroomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Classroom record);

    Classroom selectByPrimaryKey(Long id);

    List<Classroom> selectAll();

    int updateByPrimaryKey(Classroom record);

    int queryForCount(QueryObject qo);

    List<Classroom> queryForList(QueryObject qo);

    void changeState(Long id);
}