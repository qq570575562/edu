package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.FormalStudentChart;
import cn.wolfcode.edu.query.FormalStudentChartQueryObject;

import java.util.List;

public interface ChartMapper {
    int queryForCount(FormalStudentChartQueryObject qo);
    List<FormalStudentChart> queryForList(FormalStudentChartQueryObject qo);
}
