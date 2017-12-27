package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.SystemLog;
import cn.wolfcode.edu.mapper.SystemLogMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SystemLogServiceImpl implements ISystemLogService {

	@Autowired
	private SystemLogMapper systemLogMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		systemLogMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(SystemLog record) {
		systemLogMapper.insert(record);
	}
	

	@Override
	public void deleteAll() {
		systemLogMapper.deleteAll();

	}

	
	@Override
	public PageResult query(QueryObject qo) {
		int total = systemLogMapper.queryForCount(qo);
		if (total == 0) {
			return new PageResult(0, Collections.emptyList());
		}
		List<SystemLog> rows = systemLogMapper.queryForList(qo);
		return new PageResult(total, rows);
	}

}
