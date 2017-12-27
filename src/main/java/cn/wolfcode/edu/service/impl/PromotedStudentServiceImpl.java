package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.ClassGrade;
import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.domain.FormalStudent;
import cn.wolfcode.edu.domain.PromotedStudent;
import cn.wolfcode.edu.mapper.FormalStudentMapper;
import cn.wolfcode.edu.mapper.PromotedStudentMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IPromotedStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PromotedStudentServiceImpl implements IPromotedStudentService{

	@Autowired
	private PromotedStudentMapper promotedStudentMapper;

	@Autowired
	private FormalStudentMapper formalStudentMapper;

	@Override
	public void deleteByPrimaryKey(Long id) {
		promotedStudentMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(PromotedStudent record) {
		promotedStudentMapper.insert(record);
	}
	@Override
	public PromotedStudent selectByPrimaryKey(Long id) {
		return promotedStudentMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<PromotedStudent> selectAll() {
		return promotedStudentMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(PromotedStudent record) {
		promotedStudentMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = promotedStudentMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<PromotedStudent> rows = promotedStudentMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

	/**
	 *
	 * @param id
	 *
	 */
	@Override
	public void changeState(Long id ) {
		promotedStudentMapper.changeState(id);
	}

	/**
	 * 批量更新
	 * @param ids
	 * @param afterClassId
	 */
	@Override
	public void transStudent(Long[] ids, Long afterClassId) {

		for ( Long id : ids) {
			FormalStudent formalStudent = formalStudentMapper.selectByPrimaryKey(id);
			PromotedStudent promotedStudent = new PromotedStudent();
			promotedStudent.setAuditState(false);
			promotedStudent.setName(formalStudent.getName());
			promotedStudent.setTotalTuition(formalStudent.getTotalTuition());
			promotedStudent.setPaidTuition(formalStudent.getTotalTuition());
			promotedStudent.setPromoteOrRepeatDate(new Date());
			promotedStudent.setTel(formalStudent.getTel());
			promotedStudent.setClassBefore(formalStudent.getClz());
			ClassGrade classGrade = new ClassGrade();
			classGrade.setId(afterClassId);
			promotedStudent.setClassAfter(classGrade);
			//另外2个参数
			promotedStudent.setSaleman(formalStudent.getSaleman());
			promotedStudent.setFormalStudentId(formalStudent.getId());

			//存数据库

			promotedStudentMapper.insert(promotedStudent);
			formalStudentMapper.setClassIdById(id,afterClassId);
		}

	}
}
