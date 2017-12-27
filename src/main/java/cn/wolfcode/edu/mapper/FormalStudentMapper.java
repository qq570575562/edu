package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.FormalStudent;
import cn.wolfcode.edu.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FormalStudentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FormalStudent record);

    FormalStudent selectByPrimaryKey(Long id);

    List<FormalStudent> selectAll();

    int updateByPrimaryKey(FormalStudent record);

    //分页数据
    int queryForCount(QueryObject qo);

    List<FormalStudent> queryForList(QueryObject qo);

    void changeState(@Param("id")Long id, @Param("state")Byte state);

    //设置学员的
    void setpaidState(boolean b);

    //设置学员的收款的修改
    void updateIncomeItem(FormalStudent formalStudent);
    //动态获取OwnTuition
    BigDecimal selectOwnTuitionByid(Long id);

    void setClassIdById(@Param("id")Long id, @Param("afterClassId")Long afterClassId);
}