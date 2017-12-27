package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.DesignateHistory;
import cn.wolfcode.edu.query.DesignateHistoryQueryObject;

import java.util.List;

public interface DesignateHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DesignateHistory record);

    DesignateHistory selectByPrimaryKey(Long id);

    List<DesignateHistory> selectAll();

    int updateByPrimaryKey(DesignateHistory record);

    int queryCount(DesignateHistoryQueryObject qo);

    List<DesignateHistory> queryList(DesignateHistoryQueryObject qo);
}