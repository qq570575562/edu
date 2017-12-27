package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.SystemDictionaryItem;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface ISystemDictionaryItemService {

	void deleteByPrimaryKey(Long id);

	void insert(SystemDictionaryItem record);

	SystemDictionaryItem selectByPrimaryKey(Long id);

	List<SystemDictionaryItem> selectAll();

	void updateByPrimaryKey(SystemDictionaryItem record);

	PageResult query(QueryObject qo);

	List<SystemDictionaryItem> queryBySn(String sn);
}
