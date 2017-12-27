package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Course;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.CourseQueryObject;
import cn.wolfcode.edu.service.ICourseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

	@RequestMapping("query")
	@RequiresPermissions("course:query")
	@PerminssionName("课程查询")
	@ResponseBody
	public PageResult query(CourseQueryObject qo) throws Exception {
		PageResult pageResult = courseService.query(qo);
		// Date date = qo.getDate();
		// if (date!=null){
		// SimpleDateFormat dateFormat = new SimpleDateFormat();
		// dateFormat.applyPattern("yyyy-MM-dd");
		// String format = dateFormat.format(date);
		// Date newDate = dateFormat.parse(format);
		// System.out.println(newDate.toString());
		//// System.out.println(format);
		// }
		return pageResult;
	}

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Course> selectAll() {
        return  courseService.selectAll();
    }

	@RequestMapping("view")
	@RequiresPermissions("course:query")
	@PerminssionName("课程管理")
	public String view() {
		return "course";
	}

}
