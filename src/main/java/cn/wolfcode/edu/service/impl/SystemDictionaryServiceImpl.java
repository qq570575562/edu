package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.SystemDictionary;
import cn.wolfcode.edu.mapper.SystemDictionaryMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService{
	@Autowired
	private SystemDictionaryMapper systemDictionaryMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		systemDictionaryMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(SystemDictionary record) {
		systemDictionaryMapper.insert(record);
	}
	@Override
	public SystemDictionary selectByPrimaryKey(Long id) {
		return systemDictionaryMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<SystemDictionary> selectAll() {
		return systemDictionaryMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(SystemDictionary record) {
		systemDictionaryMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = systemDictionaryMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<SystemDictionary> rows = systemDictionaryMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

	

}
