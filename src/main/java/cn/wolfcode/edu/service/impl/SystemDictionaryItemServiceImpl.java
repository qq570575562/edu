package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.SystemDictionaryItem;
import cn.wolfcode.edu.mapper.SystemDictionaryItemMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.ISystemDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService{
	@Autowired
	private SystemDictionaryItemMapper systemDictionaryItemMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		systemDictionaryItemMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(SystemDictionaryItem record) {
		systemDictionaryItemMapper.insert(record);
	}
	@Override
	public SystemDictionaryItem selectByPrimaryKey(Long id) {
		return systemDictionaryItemMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<SystemDictionaryItem> selectAll() {
		return systemDictionaryItemMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(SystemDictionaryItem record) {
		systemDictionaryItemMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = systemDictionaryItemMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<SystemDictionaryItem> rows = systemDictionaryItemMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

	@Override
	public List<SystemDictionaryItem> queryBySn(String sn) {

		return systemDictionaryItemMapper.queryBySn(sn);
	}

}
