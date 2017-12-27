package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.DesignateHistory;
import cn.wolfcode.edu.mapper.DesignateHistoryMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.DesignateHistoryQueryObject;
import cn.wolfcode.edu.service.IDesignateHistoryService;
import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@Service
public class DesignateHistoryServiceImpl implements IDesignateHistoryService{
	@Autowired
	private DesignateHistoryMapper designateHistoryMapper;

	@Override
	public void deleteByPrimaryKey(Long id) {
		designateHistoryMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(DesignateHistory record) {
		designateHistoryMapper.insert(record);
	}
	@Override
	public DesignateHistory selectByPrimaryKey(Long id) {
		return designateHistoryMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<DesignateHistory> selectAll() {
		return designateHistoryMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(DesignateHistory record) {
		designateHistoryMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(DesignateHistoryQueryObject qo) {
		int total = designateHistoryMapper.queryCount(qo);
		if (total==0){
		    return  new PageResult(0, Collections.emptyList());
		}
		List<DesignateHistory> rows = designateHistoryMapper.queryList(qo);
		return new PageResult(total,rows);
	}
	@Override
	public void exportXls(HttpServletResponse resp)throws  Exception {
		resp.setHeader("Content-Disposition", "attachment;filename=designatehistory.xls");
		WritableWorkbook workbook =  Workbook.createWorkbook(resp.getOutputStream());;
		WritableSheet sheet = workbook.createSheet("day01", 0);
		sheet.addCell(new Label(0,0,"姓名"));
		sheet.addCell(new Label(1,0,"电话"));
		sheet.addCell(new Label(2,0,"QQ"));
		sheet.addCell(new Label(3,0,"意向程度"));
		sheet.addCell(new Label(4,0,"原拥有者"));
		sheet.addCell(new Label(5,0,"现拥有者"));
		sheet.addCell(new Label(6,0,"意向班级"));
		sheet.addCell(new Label(7,0,"考试时间"));
		sheet.addCell(new Label(8,0,"详情信息"));
		List<DesignateHistory> dhs = designateHistoryMapper.selectAll();
		for (int i = 1; i <= dhs.size(); i++) {
			DesignateHistory dh = dhs.get(i - 1);
			sheet.addCell(new Label(0, i, dh.getName()));
			sheet.addCell(new Label(1, i, dh.getTel()));
			sheet.addCell(new Label(2, i, dh.getQq()));
			if (dh.getIntentiondegree()!=null){
				sheet.addCell(new Label(3, i, dh.getIntentiondegree().getName()));
			}
			if (dh.getSource()!=null){
				sheet.addCell(new Label(4, i, dh.getSource().getRealname()));
			}
			if (dh.getTarget()!=null){
				sheet.addCell(new Label(5, i, dh.getTarget().getRealname()));
			}
			if (dh.getClassId()!=null){
				sheet.addCell(new Label(6, i, dh.getClassId().getClassName()));
			}
			if (dh.getAssigntime()!=null){
				sheet.addCell(new DateTime(7, i, dh.getAssigntime()));
			}
			sheet.addCell(new Label(8, i, dh.getMinute()));
		}
		//输出流 写出
		workbook.write();
		//关闭流 释放资源
		workbook.close();
	}
}
