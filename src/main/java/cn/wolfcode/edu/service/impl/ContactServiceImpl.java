package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Contact;
import cn.wolfcode.edu.mapper.ContactMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ContactServiceImpl implements IContactService{

	@Autowired
	private ContactMapper contactMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		contactMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(Contact record) {
		contactMapper.insert(record);
	}
	@Override
	public Contact selectByPrimaryKey(Long id) {
		return contactMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Contact> selectAll() {
		return contactMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(Contact record) {
		contactMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = contactMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<Contact> rows = contactMapper.queryForList(qo);
		return new PageResult(total,rows);
	}


}
