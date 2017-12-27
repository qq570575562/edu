package cn.wolfcode.edu.service;

import cn.wolfcode.edu.domain.Runoff;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface IRunoffService {
	void deleteByPrimaryKey(Long id);

	void insert(Runoff record);

	Runoff selectByPrimaryKey(Long id);

	List<Runoff> selectAll();

	void updateByPrimaryKey(Runoff record);

	PageResult query(QueryObject qo);
}
