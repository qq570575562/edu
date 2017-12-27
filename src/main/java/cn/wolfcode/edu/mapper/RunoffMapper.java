package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Runoff;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface RunoffMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Runoff record);

    Runoff selectByPrimaryKey(Long id);

    List<Runoff> selectAll();

    int updateByPrimaryKey(Runoff record);

    int queryCount(QueryObject qo);

    List<Runoff> queryList(QueryObject qo);
}