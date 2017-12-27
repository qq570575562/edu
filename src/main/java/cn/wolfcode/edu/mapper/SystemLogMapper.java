package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.SystemLog;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface SystemLogMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SystemLog record);

	void deleteAll();

	int queryForCount(QueryObject qo);

	List<SystemLog> queryForList(QueryObject qo);
}