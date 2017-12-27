package cn.wolfcode.edu.service;

import cn.wolfcode.edu.domain.DesignateHistory;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.DesignateHistoryQueryObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IDesignateHistoryService {
	void deleteByPrimaryKey(Long id);

	void insert(DesignateHistory record);

	DesignateHistory selectByPrimaryKey(Long id);

	List<DesignateHistory> selectAll();

	void updateByPrimaryKey(DesignateHistory record);

	PageResult query(DesignateHistoryQueryObject qo);

	void exportXls(HttpServletResponse response)throws Exception;
}
