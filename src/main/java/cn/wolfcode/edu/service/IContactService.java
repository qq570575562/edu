package cn.wolfcode.edu.service;


import cn.wolfcode.edu.domain.Contact;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface IContactService {

	void deleteByPrimaryKey(Long id);

	void insert(Contact record);

	Contact selectByPrimaryKey(Long id);

	List<Contact> selectAll();

	void updateByPrimaryKey(Contact record);

	PageResult query(QueryObject qo);

   // void changeState(Long id);

}
