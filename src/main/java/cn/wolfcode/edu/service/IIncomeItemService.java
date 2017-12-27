package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.IncomeItem;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface IIncomeItemService {

	void deleteByPrimaryKey(Long id);

	void insert(IncomeItem record);

	IncomeItem selectByPrimaryKey(Long id);

	List<IncomeItem> selectAll();

	void updateByPrimaryKey(IncomeItem record);

	PageResult query(QueryObject qo);

    void changeState(Long id);
}
