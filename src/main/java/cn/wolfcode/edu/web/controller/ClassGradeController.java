package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.ClassGrade;
import cn.wolfcode.edu.domain.Classroom;
import cn.wolfcode.edu.domain.Course;
import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IClassGradeService;
import cn.wolfcode.edu.service.ICourseService;
import cn.wolfcode.edu.util.JsonResult;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("classGrade")
public class ClassGradeController {
	@Autowired
	private IClassGradeService classGradeService;
	@Autowired
	private ICourseService courseService;

	@RequestMapping("query")
	@RequiresPermissions("classGrade:query")
	@PerminssionName("班级查询")
	@ResponseBody
	public PageResult query(QueryObject qo) throws Exception {
		PageResult pageResult = classGradeService.query(qo);
		List<ClassGrade> rows = (List<ClassGrade>) pageResult.getRows();
		for (ClassGrade row : rows) {
			System.out.println(row);

		}
		return pageResult;
	}
	@RequestMapping("queryByClassroomId")
	@ResponseBody
	public List<ClassGrade> queryByClassroomId(Long id) throws Exception {
		List<ClassGrade> classGrade = classGradeService.queryByClassroomId(id);
		return classGrade;
	}
	@RequestMapping("selectAll")
	@ResponseBody
	public List<ClassGrade> selectAll() throws Exception {
		List<ClassGrade> classGrades = classGradeService.selectAll();
		return classGrades;
	}

	@RequestMapping("view")
	@RequiresPermissions("classGrade:view")
	@PerminssionName("班级管理菜单")
	public String view() {
		return "classGrade";
	}
	@RequestMapping("saveOrUpdate")
	@RequiresPermissions("classGrade:saveOrUpdate")
	@PerminssionName("班级信息更新")
	@ResponseBody
	public JsonResult saveOrUpdate(ClassGrade classGrade) {
		try {
			if (classGrade.getId() == null) {
				classGradeService.insert(classGrade);
			}else{
				classGrade.setState(true);
				classGradeService.updateByPrimaryKey(classGrade);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "保存失败！");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("remove")
	@RequiresPermissions("classGrade:remove")
	@PerminssionName("班级信息删除")
	@ResponseBody
	public JsonResult remove(Long id) {
		try {
			if (id != null) {
				classGradeService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "删除失败！");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("distribute")
	@RequiresPermissions("classGrade:distribute")
	@PerminssionName("班级分配")
	@ResponseBody
	public JsonResult distribute(Long id) {
		try {
			if (id != null) {
				classGradeService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "删除失败！");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("saveDis")
	@RequiresPermissions("classGrade:saveDis")
	@PerminssionName("班级分配保存")
	@ResponseBody
	public JsonResult saveDis(Long classId, Long empId) {

		try {
			classGradeService.saveDis(classId, empId);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "分配失败！");
		}
		return new JsonResult(true, null);
	}

	@RequiresPermissions("classGrade:importCourse")
	@PerminssionName("班级信息导入")
	@RequestMapping("importCourse")
	@ResponseBody
	public JsonResult importCourse(MultipartFile file) throws Exception {

		try {
			InputStream inputStream = file.getInputStream();
			// 1.获取一个制定路径的工作簿
			Workbook workbook = Workbook.getWorkbook(inputStream);
			// 2.获取要读取的此工作簿中的指定的sheet
			Sheet sheet = workbook.getSheet(0);
			// 3.获取总行数
			int rows = sheet.getRows();
			// 4.获取总列数
			int columns = sheet.getColumns();
			// 5.获取内容
			for (int i = 0; i < rows; i++) {
				Course course = new Course();
				ClassGrade classGrade = new ClassGrade();
				classGrade.setId(Long.parseLong(sheet.getCell(2, i).getContents()));
				course.setClassGrade(classGrade);
				SimpleDateFormat dateFormat = new SimpleDateFormat();
				dateFormat.applyPattern("yyyy-MM-dd");
				Date date = dateFormat.parse(sheet.getCell(0, i).getContents());
				course.setDate(date);
				course.setWeekday(sheet.getCell(1, i).getContents());
				course.setCourseName(sheet.getCell(3, i).getContents());
				Employee leader = new Employee();
				leader.setId(Long.parseLong(sheet.getCell(4, i).getContents()));
				course.setLeader(leader);
				Employee teacher = new Employee();
				teacher.setId(Long.parseLong(sheet.getCell(5, i).getContents()));
				course.setTeacher(teacher);
				Classroom classroom = new Classroom();
				classroom.setId(Long.parseLong(sheet.getCell(6, i).getContents()));
				course.setClassroom(classroom);
				course.setRemark(sheet.getCell(7, i).getContents());
				course.setState(Boolean.parseBoolean(sheet.getCell(8, i).getContents()));
				courseService.insert(course);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, "上传失败!");
		}
		return new JsonResult(true);
	}
	@RequestMapping("openClass")
	@ResponseBody
	public JsonResult openClass(Long id){
		try {
			if (id!=null){
				classGradeService.openClass(id);
			}
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(false,"操作失败！");
		}
		return new JsonResult(true,null);
	}
}
