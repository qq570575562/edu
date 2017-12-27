package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.PromotedStudent;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.PromotedStudentQueryObject;
import cn.wolfcode.edu.service.IPromotedStudentService;
import cn.wolfcode.edu.util.JsonResult;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("promotedStudent")
public class PromotedStudentController {
    @Autowired
    private IPromotedStudentService promotedStudentService;

    @RequestMapping("query")
    @ResponseBody
    public PageResult list(PromotedStudentQueryObject qo) throws Exception {
        PageResult pageResult = promotedStudentService.query(qo);
        return pageResult;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public List<PromotedStudent> selectAll() {
        return  promotedStudentService.selectAll();
    }

    @RequiresPermissions("promotedStudent:view")
    @PerminssionName("学员升班留级菜单")
    @RequestMapping("view")
    public String view() {
        return "promotedStudent";
    }

    @RequiresPermissions("promotedStudent:saveOrUpdate")
    @PerminssionName("学员升班留级编辑")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(PromotedStudent promotedStudent) throws Exception{

        try {
            if (promotedStudent.getId() == null) {

                promotedStudentService.insert(promotedStudent);

            } else {
                promotedStudentService.updateByPrimaryKey(promotedStudent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败！");
        }
        return new JsonResult(true, null);
    }

    @RequiresPermissions("promotedStudent:changeAuditState")
    @PerminssionName("学员升班留级审核")
    @RequestMapping("changeAuditState")
    @ResponseBody
    public JsonResult changeState(Long id) throws Exception {

        try {
            promotedStudentService.changeState(id);
        } catch (Exception e) {
            return new JsonResult(false, "失败");
        }
        return new JsonResult(true, "成功");
    }

    @RequestMapping("transStudent")
    @ResponseBody
    public JsonResult transStudent(Long afterClassId,Long[] ids) throws Exception {

        try {
            promotedStudentService.transStudent(ids,afterClassId);
        } catch (Exception e) {
            return new JsonResult(false, "失败");
        }
        return new JsonResult(true, "成功");
    }
    /**
     * 导出表格
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("exportXls")
    @RequiresPermissions("promotedStudent:exportXls")
    @PerminssionName("大客户导出")
    @ResponseBody
    public JsonResult exportXls(HttpServletResponse response) throws Exception {
        // 这是文件下载的响应头
        response.setHeader("Content-Disposition", "attachment;filename=promotedStudent.xls");

        // 创建一个文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

        // 创建工作簿
        WritableSheet sheet = workbook.createSheet("promotedStudent", 0);

        // 创建标题行

        sheet.addCell(new Label(0, 0, "姓名"));
        sheet.addCell(new Label(1, 0, "总学费"));
        sheet.addCell(new Label(2, 0, "需缴学费"));
        sheet.addCell(new Label(3, 0, "已交学费"));
        sheet.addCell(new Label(4, 0, "升班留级时间"));
        sheet.addCell(new Label(5, 0, "电话"));
        sheet.addCell(new Label(6, 0, "之前班级"));
        sheet.addCell(new Label(7, 0, "之后班级"));
        sheet.addCell(new Label(8, 0, "营销人员"));
        sheet.addCell(new Label(9, 0, "审核状态"));

        // 获取数据库中的所有信息

        try {
            List<PromotedStudent> promotedStudents = promotedStudentService.selectAll();
            for (int i = 0, j = 1; i < promotedStudents.size(); i++, j++) {
                PromotedStudent promotedStudent = promotedStudents.get(i);
                if (promotedStudent.getName() != null) {
                    sheet.addCell(new Label(0, j, promotedStudent.getName().toString()));
                }
                if (promotedStudent.getTotalTuition() != null) {
                    sheet.addCell(new Label(1, j, promotedStudent.getTotalTuition().toString()));
                }
                if (promotedStudent.getToPaidTuition() != null) {
                    sheet.addCell(new Label(2, j, promotedStudent.getToPaidTuition().toString()));
                }
                if (promotedStudent.getPaidTuition() != null) {
                    sheet.addCell(new Label(3, j, promotedStudent.getPaidTuition().toString()));
                }
                if (promotedStudent.getPromoteOrRepeatDate() != null) {
                    sheet.addCell(new Label(4, j, promotedStudent.getPromoteOrRepeatDate().toString()));
                }
                
                if (promotedStudent.getTel() != null) {
                    sheet.addCell(new Label(5, j, promotedStudent.getTel().toString()));
                }
                if (promotedStudent.getClassBefore() != null) {
                    sheet.addCell(new Label(6, j, promotedStudent.getClassBefore().toString()));
                }
                if (promotedStudent.getClassAfter() != null) {
                    sheet.addCell(new Label(7, j, promotedStudent.getClassAfter().toString()));
                }
                if (promotedStudent.getSaleman() != null) {
                    sheet.addCell(new Label(8, j, promotedStudent.getSaleman().getRealname().toString()));
                }
                if (promotedStudent.getAuditState() != null) {
                    sheet.addCell(new Label(9, j, promotedStudent.getAuditState().toString()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "导出出错,请检查数据是否正常");
        }

        workbook.write();

        workbook.close();

        return new JsonResult(true);
    }
    
}
