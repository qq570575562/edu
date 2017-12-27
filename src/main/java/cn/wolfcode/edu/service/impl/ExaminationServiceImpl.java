package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.domain.Examination;
import cn.wolfcode.edu.mapper.ExaminationMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.ExaminationQueryObject;
import cn.wolfcode.edu.service.IExaminationService;
import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@Service
public class ExaminationServiceImpl implements IExaminationService {
	@Autowired
	private ExaminationMapper examinationMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		examinationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Examination record) {
		examinationMapper.insert(record);
	}

	@Override
	public Examination selectByPrimaryKey(Long id) {
		return examinationMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Examination> selectAll() {
		return examinationMapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(Examination record) {
		examinationMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult query(ExaminationQueryObject qo) {
		int total = examinationMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<Examination> rows = examinationMapper.queryForList(qo);
		return new PageResult(total,rows);
	}
	@Override
	public void changState(Long id) {
		Examination examination = examinationMapper.selectByPrimaryKey(id);
		Subject subject = SecurityUtils.getSubject();
		Employee handler = (Employee)subject.getPrincipal();
		if (examination.isResult()) {
			examinationMapper.changState(id,null);
		}else {
			examinationMapper.changState(id,handler.getId());
		}
	}
	@Override
	public List<Long> selectPsId() {
		return examinationMapper.selectPsId();
	}
	@Override
	public void exportXls(HttpServletResponse resp) throws Exception{
		resp.setHeader("Content-Disposition", "attachment;filename=examination.xls");
		WritableWorkbook workbook =  Workbook.createWorkbook(resp.getOutputStream());;
		WritableSheet sheet = workbook.createSheet("day01", 0);
		sheet.addCell(new Label(0,0,"编码"));
		sheet.addCell(new Label(1,0,"姓名"));
		sheet.addCell(new Label(2,0,"营销人员"));
		sheet.addCell(new Label(3,0,"qq"));
		sheet.addCell(new Label(4,0,"电话"));
		sheet.addCell(new Label(5,0,"意向班级"));
		sheet.addCell(new Label(6,0,"考试时间"));
		sheet.addCell(new Label(7,0,"备注"));
		sheet.addCell(new Label(8,0,"审核人"));
		List<Examination> examinations = examinationMapper.selectAll();
		for (int i = 1; i <= examinations.size(); i++) {
			Examination examination = examinations.get(i - 1);
			if (examination.getId()!=null){
				sheet.addCell(new Label(0, i, examination.getId().toString()));
			}
			if (examination.getName()!=null){
				sheet.addCell(new Label(1, i, examination.getName()));
			}
			if (examination.getSaleman()!=null){
				sheet.addCell(new Label(2, i, examination.getSaleman().getRealname()));
			}
			if (examination.getQq()!=null){
				sheet.addCell(new Label(3, i, examination.getQq()));
			}
			if (examination.getTel()!=null){
				sheet.addCell(new Label(4, i, examination.getTel()));
			}
			if (examination.getClassId()!=null){
				sheet.addCell(new Label(5, i, examination.getClassId().getClassName()));
			}
			if (examination.getExamtime()!=null){
				sheet.addCell(new DateTime(6, i, examination.getExamtime()));
			}
			if (examination.getRemark()!=null){
				sheet.addCell(new Label(7, i, examination.getRemark()));
			}
			if (examination.getHandler()!=null){
				sheet.addCell(new Label(8, i, examination.getHandler().getRealname()));
			}
		}
		//输出流 写出
		workbook.write();
		//关闭流 释放资源
		workbook.close();
	}
}