package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Department;
import cn.wolfcode.edu.query.QueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

	/**
	 * @return 返回所有对象
	 */
	List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    int queryForCount(QueryObject qo);

    List<Department> queryForList(QueryObject qo);

    void changeState(Long id);
}