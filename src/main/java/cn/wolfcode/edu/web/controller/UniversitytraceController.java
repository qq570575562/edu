package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Universitytrace;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.UniversitytraceQueryObject;
import cn.wolfcode.edu.service.IUniversitytraceService;
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
@RequestMapping("universitytrace")
public class UniversitytraceController {
	@Autowired
	private IUniversitytraceService universitytraceService;

	@RequestMapping("query")
	@RequiresPermissions("universitytrace:query")
	@PerminssionName("大客户查询")
	@ResponseBody
	public PageResult list(UniversitytraceQueryObject qo) throws Exception {
		PageResult pageResult = universitytraceService.query(qo);
		return pageResult;
	}

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Universitytrace> selectAll() {
        return  universitytraceService.selectAll();
    }

	@RequestMapping("view")
	@RequiresPermissions("universitytrace:view")
	@PerminssionName("大客户管理")
	public String view() {
		return "universitytrace";
	}

	@RequestMapping("saveOrUpdate")
	@RequiresPermissions("universitytrace:saveOrUpdate")
	@PerminssionName("大客户信息更新")
	@ResponseBody
	public JsonResult saveOrUpdate(Universitytrace universitytrace) throws Exception {

        try {
            if (universitytrace.getId() == null) {
                //默认状态为正常

                universitytraceService.insert(universitytrace);

            } else {
                universitytraceService.updateByPrimaryKey(universitytrace);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败！");
        }
        return new JsonResult(true, null);
    }

	@RequestMapping("changeState")
    @RequiresPermissions("universitytrace:changeState")
    @PerminssionName("大客户状态设置")
	@ResponseBody
	public JsonResult changeState(Long id) throws Exception {

        try {
            universitytraceService.changeState(id);
        } catch (Exception e) {
            return new JsonResult(false, "失败");
        }
        return new JsonResult(true, "成功");
    }
	@RequiresPermissions("universitytrace:delete")
	@PerminssionName("大客户删除")
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) throws Exception {

		try {
			universitytraceService.deleteByPrimaryKey(id);
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
	@RequiresPermissions(" universitytrace:exportXls")
	@PerminssionName("大客户导出")
	@ResponseBody
	public JsonResult exportXls(HttpServletResponse response) throws Exception {
		// 这是文件下载的响应头
		response.setHeader("Content-Disposition", "attachment;filename=universitytrace.xls");

		// 创建一个文件
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

		// 创建工作簿
		WritableSheet sheet = workbook.createSheet("universitytrace", 0);

		// 创建标题行

		sheet.addCell(new Label(0, 0, "编号"));
		sheet.addCell(new Label(1, 0, "学校"));
		sheet.addCell(new Label(2, 0, "地址"));
		sheet.addCell(new Label(3, 0, "重要性"));
		sheet.addCell(new Label(4, 0, "意向度"));
		sheet.addCell(new Label(5, 0, "学校电话"));
		sheet.addCell(new Label(6, 0, "意向学科"));
		sheet.addCell(new Label(7, 0, "联系人"));
		sheet.addCell(new Label(8, 0, "销售人员"));
		sheet.addCell(new Label(9, 0, "跟进人员"));
		sheet.addCell(new Label(10, 0, "上次跟进时间"));
		sheet.addCell(new Label(11, 0, "下次跟进时间"));
		sheet.addCell(new Label(12, 0, "跟进状态"));
		sheet.addCell(new Label(13, 0, "客户状态"));

		// 获取数据库中的所有信息
		
		try {
			List<Universitytrace> universitytraces = universitytraceService.selectAll();
			for (int i = 0, j = 1; i < universitytraces.size(); i++, j++) {
				Universitytrace universitytrace = universitytraces.get(i);
				if (universitytrace.getId() != null) {
					sheet.addCell(new Label(0, j, universitytrace.getId().toString()));
				}
				if (universitytrace.getName() != null) {
					sheet.addCell(new Label(1, j, universitytrace.getName().toString()));
				}
				if (universitytrace.getAddress() != null) {
					sheet.addCell(new Label(2, j, universitytrace.getAddress()));
				}
				if (universitytrace.getImportance() != null) {
					sheet.addCell(new Label(3, j,universitytrace.getImportance().toString()));
				}
				if (universitytrace.getWantedlevel() != null) {
					sheet.addCell(new Label(4, j, universitytrace.getWantedlevel().toString()));
				}
				if (universitytrace.getSchooltel() != null) {
					sheet.addCell(new Label(5, j, universitytrace.getSchooltel().toString()));
				}
				if (universitytrace.getSubject() != null) {
					sheet.addCell(new Label(6, j, universitytrace.getSubject().toString()));
				}
				if (universitytrace.getContact() != null) {
					sheet.addCell(new Label(7, j, universitytrace.getContact().getName().toString()));
				}
				if (universitytrace.getMarketer() != null) {
					sheet.addCell(new Label(8, j, universitytrace.getMarketer().toString()));
				}
				if (universitytrace.getTracer() != null) {
					sheet.addCell(new Label(9, j, universitytrace.getTracer().getRealname().toString()));
				}
				if (universitytrace.getPrevtrancetime() != null) {
					sheet.addCell(new Label(10, j, universitytrace.getPrevtrancetime().toLocaleString()));
				}
				if (universitytrace.getNexttrancetime() != null) {
					sheet.addCell(new Label(11, j, universitytrace.getNexttrancetime().toLocaleString()));
				}
				if (universitytrace.getTracestate() != null) {
					sheet.addCell(new Label(12, j, universitytrace.getTracestate().toString()));
				}
				if (universitytrace.getCustomerstatus() != null) {
					sheet.addCell(new Label(13, j, universitytrace.getCustomerstatus().toString()));
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
