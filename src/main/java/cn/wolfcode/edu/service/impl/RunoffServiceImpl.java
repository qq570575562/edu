package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Runoff;
import cn.wolfcode.edu.mapper.RunoffMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IRunoffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RunoffServiceImpl implements IRunoffService{

	@Autowired
	private RunoffMapper runoffMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		runoffMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(Runoff record) {
		runoffMapper.insert(record);
	}
	@Override
	public Runoff selectByPrimaryKey(Long id) {
		return runoffMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Runoff> selectAll() {
		return runoffMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(Runoff record) {
		runoffMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = runoffMapper.queryCount(qo);
		if (total==0){
		    new PageResult(0, Collections.emptyList());
		}
		List<Runoff> rows = runoffMapper.queryList(qo);
		return new PageResult(total,rows);
	}
}
