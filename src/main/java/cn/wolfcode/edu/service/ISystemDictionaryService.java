package cn.wolfcode.edu.service;

import cn.wolfcode.edu.domain.SystemDictionary;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface ISystemDictionaryService {

	void deleteByPrimaryKey(Long id);

	void insert(SystemDictionary record);

	SystemDictionary selectByPrimaryKey(Long id);

	List<SystemDictionary> selectAll();

	void updateByPrimaryKey(SystemDictionary record);

	PageResult query(QueryObject qo);

}
