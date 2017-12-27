package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Department;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.DepartmentQueryObject;
import cn.wolfcode.edu.service.IDepartmentService;
import cn.wolfcode.edu.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("query")
    @RequiresPermissions("department:query")
    @PerminssionName("部门查询")
    @ResponseBody
    public PageResult list(DepartmentQueryObject qo) throws Exception {
        PageResult pageResult = departmentService.query(qo);
        return pageResult;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Department> selectAll() {
        return  departmentService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions("department:view")
    @PerminssionName("部门管理")
    public String view() {
        return "department";
    }

    @RequestMapping("saveOrUpdate")
    @RequiresPermissions("department:saveOrUpdate")
    @PerminssionName("部门更新")
    @ResponseBody
    public JsonResult saveOrUpdate(Department department) throws Exception{

        try {
            if (department.getId() == null) {
                //默认状态为正常
                department.setState(true);
                departmentService.insert(department);

            } else {
                departmentService.updateByPrimaryKey(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败！");
        }
        return new JsonResult(true, "成功");
    }

    @RequestMapping("changeState")
    @RequiresPermissions("department:changeState")
    @PerminssionName("部门销毁或恢复")
    @ResponseBody
    public JsonResult changeState(Long id) throws Exception {

        try {
            departmentService.changeState(id);
        } catch (Exception e) {
            return new JsonResult(false, "失败");
        }
        return new JsonResult(true, "成功");
    }

}
