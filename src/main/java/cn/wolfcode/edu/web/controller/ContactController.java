package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Contact;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.ContactQueryObject;
import cn.wolfcode.edu.service.IContactService;
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
@RequestMapping("contact")
public class ContactController {
    @Autowired
    private IContactService contactService;

    @RequestMapping("query")
    @RequiresPermissions("contact:query")
    @PerminssionName("联系人查询")
    @ResponseBody
    public PageResult list(ContactQueryObject qo) throws Exception {
        System.out.println(1);
        PageResult pageResult = contactService.query(qo);
        System.out.println(1);
        return pageResult;
    }

	@RequestMapping("selectAll")
	@ResponseBody
	public List<Contact> selectAll() {
		System.out.println(2);
		return contactService.selectAll();
	}

	@RequestMapping("view")
	@RequiresPermissions("contact:view")
	@PerminssionName("联系人管理")
	public String view() {
		return "contact";
	}

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("contact:saveOrUpdate")
    @PerminssionName("联系人更新")
    public JsonResult saveOrUpdate(Contact contact) throws Exception{

		try {
			if (contact.getId() == null) {

				contactService.insert(contact);

            } else {
                contactService.updateByPrimaryKey(contact);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败！");
        }
        return new JsonResult(true, "保存成功");
    }

	/*
	 * @RequestMapping("changeState")
	 *
	 * @ResponseBody public JsonResult changeState(Long id) throws Exception {
	 *
	 * try { contactService.changeState(id); } catch (Exception e) { return new
	 * JsonResult(false, "失败"); } return new JsonResult(true, "成功"); }
	 */

	/**
	 * 导出表格
	 *
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportXls")
	@RequiresPermissions("contact:exportXls")
	@PerminssionName("联系人导出")
	@ResponseBody
	public JsonResult exportXls(HttpServletResponse response) throws Exception {
		// 这是文件下载的响应头
		response.setHeader("Content-Disposition", "attachment;filename=contact.xls");

		// 创建一个文件
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

		// 创建工作簿
		WritableSheet sheet = workbook.createSheet("contact", 0);

		// 创建标题行

		sheet.addCell(new Label(0, 0, "编号"));
		sheet.addCell(new Label(1, 0, "姓名"));
		sheet.addCell(new Label(2, 0, "职务"));
		sheet.addCell(new Label(3, 0, "院系"));
		sheet.addCell(new Label(4, 0, "部门"));
		sheet.addCell(new Label(5, 0, "电话"));
		sheet.addCell(new Label(6, 0, "QQ"));
		sheet.addCell(new Label(7, 0, "邮箱"));
		sheet.addCell(new Label(8, 0, "性别"));
		sheet.addCell(new Label(9, 0, "生日"));
		sheet.addCell(new Label(10, 0, "主要联系人"));
		sheet.addCell(new Label(11, 0, "大学"));
		sheet.addCell(new Label(12, 0, "简介"));

		// 获取数据库中的所有信息

		try {
			List<Contact> contacts = contactService.selectAll();
			for (int i = 0, j = 1; i < contacts.size(); i++, j++) {
				Contact contact = contacts.get(i);
				if (contact.getId() != null) {
					sheet.addCell(new Label(0, j, contact.getId().toString()));
				}
				if (contact.getName() != null) {
					sheet.addCell(new Label(1, j, contact.getName().toString()));
				}
				if (contact.getDuty() != null) {
					sheet.addCell(new Label(2, j, contact.getDuty()));
				}
				if (contact.getFacukty() != null) {
					sheet.addCell(new Label(3, j, contact.getFacukty().toString()));
				}
				if (contact.getDept() != null) {
					sheet.addCell(new Label(4, j, contact.getDept().toString()));
				}
				if (contact.getTel() != null) {
					sheet.addCell(new Label(5, j, contact.getTel().toString()));
				}
				sheet.addCell(new Label(6, j, ""));
				if (contact.getEmail() != null) {
					sheet.addCell(new Label(7, j, contact.getEmail().toString()));
				}
				if (contact.isGander()) {
					sheet.addCell(new Label(8, j, contact.isGander()+""));
				}
				if (contact.getBirthday() != null) {
					sheet.addCell(new Label(9, j, contact.getBirthday().toString()));
				}
				if (contact.isMain() ) {
					sheet.addCell(new Label(10, j, contact.isMain()+""));
				}
				if (contact.getUniversity() != null) {
					sheet.addCell(new Label(11, j, contact.getUniversity().getName()));
				}
				sheet.addCell(new Label(12, j, ""));
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
