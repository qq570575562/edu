package cn.wolfcode.edu.service;

import cn.wolfcode.edu.domain.SystemLog;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

public interface ISystemLogService {

	void deleteByPrimaryKey(Long id);

	void insert(SystemLog record);


	void deleteAll();


	PageResult query(QueryObject qo);

}
