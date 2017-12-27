package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.IncomeItem;
import cn.wolfcode.edu.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncomeItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IncomeItem record);

    IncomeItem selectByPrimaryKey(Long id);

    List<IncomeItem> selectAll();

    int updateByPrimaryKey(IncomeItem record);

    int queryForCount(QueryObject qo);

    List<IncomeItem> queryForList(QueryObject qo);

    void changeState(Long id);

    void updateAuditor(@Param("id") Long id, @Param("name") String realname);
}