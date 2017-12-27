package cn.wolfcode.edu.service;

import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.FormalStudentChartQueryObject;

public interface IChartService {
	PageResult query(FormalStudentChartQueryObject qo);
}