package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Universitytrace;
import cn.wolfcode.edu.query.QueryObject;
import java.util.List;

public interface UniversitytraceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Universitytrace record);

    Universitytrace selectByPrimaryKey(Long id);

    List<Universitytrace> selectAll();

    int updateByPrimaryKey(Universitytrace record);

    int queryForCount(QueryObject qo);

    List<Universitytrace> queryForList(QueryObject qo);

    void changeState(Long id);
}