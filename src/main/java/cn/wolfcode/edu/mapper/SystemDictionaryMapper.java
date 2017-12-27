package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.SystemDictionary;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface SystemDictionaryMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SystemDictionary record);

	SystemDictionary selectByPrimaryKey(Long id);

	List<SystemDictionary> selectAll();

	int updateByPrimaryKey(SystemDictionary record);

	int queryForCount(QueryObject qo);

	List<SystemDictionary> queryForList(QueryObject qo);
}