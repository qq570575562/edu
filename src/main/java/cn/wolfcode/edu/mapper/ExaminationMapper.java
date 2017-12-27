package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Examination;
import cn.wolfcode.edu.query.ExaminationQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExaminationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Examination record);

    Examination selectByPrimaryKey(Long id);

    List<Examination> selectAll();

    int updateByPrimaryKey(Examination record);

	int queryForCount(ExaminationQueryObject qo);

	List<Examination> queryForList(ExaminationQueryObject qo);

	void changState(@Param("id") Long id,@Param("handlerId")Long handlerId);

	List<Long> selectPsId();
}