package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.Payment;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.PaymentQueryObject;
import cn.wolfcode.edu.service.IPaymentService;
import cn.wolfcode.edu.util.JsonResult;
import com.mysql.jdbc.StringUtils;
import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("payment")
public class PaymentController {
	@Autowired
	private IPaymentService paymentService;
	private Long classId;
	private Long paymodeId;
	private Date payBeginTime;
	private Date payEndTime;
	private Integer page;
	private Integer rows;
	@RequestMapping("query")
	@RequiresPermissions("payment:query")
	@PerminssionName("收款查询")
	@ResponseBody
	public PageResult query(PaymentQueryObject qo, HttpServletRequest request)throws Exception{
		System.out.println(request.getParameter("date"));
		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");
		String classIdStr = request.getParameter("classId");
		System.out.println(classIdStr);
		String paymodeIdStr = request.getParameter("paymodeId");
		String payBeginTimeStr = request.getParameter("payBeginTime");
		String payEndTimeStr = request.getParameter("payEndTime");
		if(pageStr!=null && rowsStr != null){
			this.page = Integer.parseInt(pageStr);
			this.rows = Integer.parseInt(rowsStr);
		}
		if (!StringUtils.isNullOrEmpty(classIdStr)){
			this.classId = Long.parseLong(classIdStr);
			System.out.println(this.classId);
		}
		if (!StringUtils.isNullOrEmpty(paymodeIdStr)){
			this.paymodeId = Long.parseLong(paymodeIdStr);
		}
		if (!StringUtils.isNullOrEmpty(payBeginTimeStr)){
			SimpleDateFormat dateFormat = new SimpleDateFormat();
			dateFormat.applyPattern("yyyy-MM-dd");
			Date date = dateFormat.parse(payBeginTimeStr);
			this.payBeginTime = date;
		}
		if (!StringUtils.isNullOrEmpty(payEndTimeStr)){
			SimpleDateFormat dateFormat = new SimpleDateFormat();
			dateFormat.applyPattern("yyyy-MM-dd");
			Date date = dateFormat.parse(payEndTimeStr);
			this.payEndTime = date;
		}
		PageResult pageResult = paymentService.query(qo);
		List<Payment> rows = (List<Payment>) pageResult.getRows();
		for (Payment row : rows) {
			System.out.println(row);
		}
		return pageResult;
	}

	@RequestMapping("selectAll")
	@ResponseBody
	public List<Payment> selectAll() {
		return paymentService.selectAll();
	}

	@RequestMapping("view")
	@RequiresPermissions("payment:view")
	@PerminssionName("收款菜单")
	public String view() {
		return "payment";
	}

	@RequestMapping("saveOrUpdate")
	@RequiresPermissions("payment:saveOrUpdate")
	@PerminssionName("收款更新")
	@ResponseBody
	public JsonResult saveOrUpdate(Payment payment) {
		try {
			if (payment.getId() == null) {
				paymentService.insert(payment);
			} else {
				paymentService.updateByPrimaryKey(payment);
			}
		} catch (Exception e) {
			return new JsonResult(false, "保存失败！");
		}
		return new JsonResult(true, null);
	}
	@RequestMapping("remove")
	@ResponseBody
	public JsonResult remove(Long id){
		try {
			if (id!=null){
				paymentService.deleteByPrimaryKey(id);
			}
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(false,"删除失败！");
		}
		return new JsonResult(true,null);
	}
	@RequestMapping("audit")
	@ResponseBody
	public JsonResult audit(Long id){
		try {
			if (id!=null){
				/*paymentService.audit(id);*/
			}
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(false,"审核失败!");
		}
		return new JsonResult(true,null);
	}
	@RequestMapping("export")
	public void export(HttpServletResponse response) throws  Exception {
		PaymentQueryObject qo = new PaymentQueryObject();
		qo.setClassId(classId);
		System.out.println(classId);
		qo.setPayModeId(paymodeId);
		qo.setPayBeginTime(payBeginTime);
		qo.setPayEndTime(payEndTime);
		qo.setPage(page);
		qo.setRows(rows);
		PageResult result = paymentService.query(qo);
		List<Payment> rows = (List<Payment>) result.getRows();
		for (Payment row : rows) {
			System.out.println(row);
		}
		//得先设置文件下载响应头
		response.setHeader("Content-Disposition", "attachment;filename=expenses.xls");
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet sheet = workbook.createSheet("expenses", 0);
		sheet.addCell(new Label(0, 0, "编号"));
		sheet.addCell(new Label(1, 0, "日期"));
		sheet.addCell(new Label(2, 0, "支付金额"));
		sheet.addCell(new Label(3, 0, "摘要"));
		sheet.addCell(new Label(4, 0, "支付人"));
		sheet.addCell(new Label(5, 0, "经手人"));
		sheet.addCell(new Label(6, 0, "支付方式"));
		sheet.addCell(new Label(7, 0, "花费类型"));
		sheet.addCell(new Label(8, 0, "单据号"));
		sheet.addCell(new Label(9, 0, "所属班级"));
		sheet.addCell(new Label(10, 0, "审核人"));
		sheet.addCell(new Label(11, 0, "审核状态"));
		for (int i = 0,j=1; i < rows.size(); j++,i++) {
			Payment payment = rows.get(i);
			Label id = new Label(0, j, payment.getId().toString());
			DateTime date = new DateTime(1, j, payment.getDate());
			Label cost = new Label(2, j, payment.getCost().toString());
			Label digest = new Label(3, j, payment.getDigest());
			Label payer = new Label(4, j, payment.getPayer().getRealname());
			Label brokerage = new Label(5, j, payment.getBrokerage().getRealname());
			Label paymode = new Label(6, j, payment.getPaymode().getName());
			Label costtype = new Label(7, j, payment.getCosttype());
			Label docnumber = new Label(8, j, payment.getDocnumber().toString());
			Label classname = new Label(9, j, payment.getClassGrade().getClassName());
			Label auditor = new Label(10, j, payment.getAuditor().getRealname());
			Label state;
			if (payment.isState()){
				state = new Label(11, j, "已审核");
			}else {
				state = new Label(11, j, "未审核");
			}
			sheet.addCell(id);
			sheet.addCell(date);
			sheet.addCell(cost);
			sheet.addCell(digest);
			sheet.addCell(payer);
			sheet.addCell(brokerage);
			sheet.addCell(paymode);
			sheet.addCell(costtype);
			sheet.addCell(docnumber);
			sheet.addCell(classname);
			sheet.addCell(auditor);
			sheet.addCell(state);
		}
		workbook.write();
		workbook.close();
	}

}
