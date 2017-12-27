package cn.wolfcode.edu.service.impl;


import cn.wolfcode.edu.domain.PotentialStudent;
import cn.wolfcode.edu.mapper.PotentialStudentMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.PotentialStudentQueryObject;
import cn.wolfcode.edu.service.IPotentialStudentService;
import cn.wolfcode.edu.service.IStudentPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PotentialStudentServiceImpl implements IPotentialStudentService {
	@Autowired
	private PotentialStudentMapper mapper;
	@Autowired
	private IStudentPoolService studentPoolService;
	@Override
	public void deleteByPrimaryKey(Long id) {
		PotentialStudent potentialStudent = mapper.selectByPrimaryKey(id);
		studentPoolService.insert(potentialStudent);
		mapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insert(PotentialStudent record) {
		record.setTailnum(0);
		mapper.insert(record);
	}
	@Override
	public PotentialStudent selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PotentialStudent> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(PotentialStudent record) {
		mapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(PotentialStudentQueryObject qo) {
		int total = mapper.queryCount(qo);
		if (total==0){
		    return new PageResult(0, Collections.emptyList());
		}
		List<PotentialStudent> rows = mapper.queryList(qo);
		return new PageResult(total,rows);
	}
	@Override
	public void changState(Long id) {
		mapper.changState(id);
	}
	@Override
	public void tailnum(Long id) {
		mapper.tailnum(id);
	}
	@Override
	public List<PotentialStudent> checkstu() {
		return mapper.checkstu();
	}
	@Override
	public void toPool(Long id) {
		PotentialStudent ps = mapper.selectByPrimaryKey(id);
		ps.setSale(null);
		studentPoolService.insert(ps);
		mapper.deleteByPrimaryKey(id);
	}
}

