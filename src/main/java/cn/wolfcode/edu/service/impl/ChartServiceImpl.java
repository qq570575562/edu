package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.FormalStudentChart;
import cn.wolfcode.edu.mapper.ChartMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.FormalStudentChartQueryObject;
import cn.wolfcode.edu.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChartServiceImpl implements IChartService {
    @Autowired
    private ChartMapper chartMapper;
    @Override
    public PageResult query(FormalStudentChartQueryObject qo) {
        int total = chartMapper.queryForCount(qo);
        if (total == 0){
            return new PageResult(0, Collections.emptyList());
        }
        List<FormalStudentChart> rows = chartMapper.queryForList(qo);
        return new PageResult(total,rows);
    }
}
