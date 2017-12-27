package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.SystemDictionaryItem;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    int updateByPrimaryKey(SystemDictionaryItem record);

    int queryForCount(QueryObject qo);

    List<SystemDictionaryItem> queryForList(QueryObject qo);

    List<SystemDictionaryItem> queryBySn(String sn);
}