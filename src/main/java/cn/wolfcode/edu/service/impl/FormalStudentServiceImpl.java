package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.FormalStudent;
import cn.wolfcode.edu.domain.Runoff;
import cn.wolfcode.edu.mapper.FormalStudentMapper;
import cn.wolfcode.edu.mapper.RunoffMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IFormalStudentService;
import cn.wolfcode.edu.service.IPotentialStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.font.FontManagerNativeLibrary;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class FormalStudentServiceImpl implements IFormalStudentService{

	@Autowired
	private FormalStudentMapper formalStudentMapper;
	@Autowired
	private IPotentialStudentService potentialStudentService;
	@Autowired
	private RunoffMapper runoffMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		formalStudentMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(FormalStudent record) {
		formalStudentMapper.insert(record);
		record.setState(Byte.valueOf("1"));
		potentialStudentService.changState(record.getPsid());
	}
	@Override
	public FormalStudent selectByPrimaryKey(Long id) {
		return formalStudentMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<FormalStudent> selectAll() {
		return formalStudentMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(FormalStudent record) {
		formalStudentMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = formalStudentMapper.queryForCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<FormalStudent> rows = formalStudentMapper.queryForList(qo);
		return new PageResult(total,rows);
	}

	/**
	 * 修改状态
	 * @param id
	 * @param statVal
	 */
	@Override
	public void changeState(Long id, Byte statVal) {
		formalStudentMapper.changeState(id,statVal);
	}

	/**
	 * 动态获取所欠金额
	 * @param id
	 * @return
	 */
	@Override
	public BigDecimal selectOwnTuitionByid(Long id) {
		return formalStudentMapper.selectOwnTuitionByid(id);
	}

	/**
	 * 学员流失
	 * @param formalStuId
	 * @param runoff
	 */
	@Override
	public void leavingStudent(Long formalStuId, Runoff runoff) {

		//默认字段
		runoff.setAudit(false);
		runoff.setLivedate(new Date());

		//设置插入
		runoffMapper.insert(runoff);
		//设置学员为流失中
		formalStudentMapper.changeState(formalStuId,Byte.valueOf("3"));
	}


}
