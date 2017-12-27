package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.Universitytrace;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface IUniversitytraceService {

	void deleteByPrimaryKey(Long id);

	void insert(Universitytrace record);

	Universitytrace selectByPrimaryKey(Long id);

	List<Universitytrace> selectAll();

	void updateByPrimaryKey(Universitytrace record);

	PageResult query(QueryObject qo);

    void changeState(Long id);
}
