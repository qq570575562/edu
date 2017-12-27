package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.Examination;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.ExaminationQueryObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IExaminationService {

	void deleteByPrimaryKey(Long id);

	void insert(Examination record);

	Examination selectByPrimaryKey(Long id);

	List<Examination> selectAll();

	void updateByPrimaryKey(Examination record);

	PageResult query(ExaminationQueryObject qo);

	void changState(Long id);

	List<Long> selectPsId();

	void exportXls(HttpServletResponse resp) throws Exception;
}

