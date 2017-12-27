package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Contact;
import cn.wolfcode.edu.query.QueryObject;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Contact record);

    Contact selectByPrimaryKey(Long id);

    List<Contact> selectAll();

    int updateByPrimaryKey(Contact record);

    int queryForCount(QueryObject qo);

    List<Contact> queryForList(QueryObject qo);

}