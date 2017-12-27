package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.Course;
import cn.wolfcode.edu.query.CourseQueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Course record);

    Course selectByPrimaryKey(Long id);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    int queryForCount(CourseQueryObject qo);

    List<Course> queryForList(CourseQueryObject qo);

}