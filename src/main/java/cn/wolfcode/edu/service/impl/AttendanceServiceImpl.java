package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Attendance;
import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.mapper.AttendanceMapper;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IAttendanceService;
import org.apache.shiro.SecurityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	private AttendanceMapper attendanceMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		attendanceMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 签到操作
	 */
	@Override
	public void signIn() {
		Attendance attendance = new Attendance();
		Calendar workIntime = Calendar.getInstance();
		workIntime.set(workIntime.get(Calendar.YEAR), workIntime.get(Calendar.MONTH), workIntime.get(Calendar.DATE), 8,
				0, 0);

		// 获取当前登陆对象
		Employee Employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		attendance.setEmployee(Employee);
		// 获取用户签到时间
		// 获取当前日期
		// Date date = DateUtil.formatDate(new Date());
		// 判断是否签到过
		Attendance result = attendanceMapper.checkSignIn(Employee.getId(), new Date());
		if (result == null) {
			// 如果为空则设置为当前时间
			attendance.setSignInDay(new Date());
			attendance.setSignInTime(new Date());
		} else {
			// 如果不为空 提示用户已签到
			throw new RuntimeException("今天已签到,请勿重复签到");
		}
		// 设置签到ip
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = requestAttributes.getRequest();
			// 设置访问地址
			attendance.setSignInIp(request.getRemoteAddr());
		}

		// 设置用户是否迟到
		Calendar now = Calendar.getInstance();
		attendance.setSignInState(now.before(workIntime));
		attendanceMapper.insert(attendance);
	}
	/**
	 * 签退操作
	 */
	@Override
	public void signOut() {
		// 设置签退时间
		Calendar workOutime = Calendar.getInstance();
		workOutime.set(workOutime.get(Calendar.YEAR), workOutime.get(Calendar.MONTH), workOutime.get(Calendar.DATE), 17,
				0, 0);
		// 获取当前登陆对象
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		// 获取当前日期
		// Date date = DateUtil.formatDate(new Date());
		// 判断是否签到过
		Attendance result = attendanceMapper.checkSignIn(employee.getId(), new Date());

		if (result != null) {
			if (result.getSignOutTime() == null) {
				// 设置签退时间
				result.setSignOutTime(new Date());
				// 设置用户是否早退
				result.setSignOutState(Calendar.getInstance().after(workOutime));
				attendanceMapper.signOut(result);
			} else {
				// 如果不为空 提示用户已签到
				throw new RuntimeException("今天已签退,请勿重复签退");
			}
		} else {
			Attendance attendance = new Attendance();
			attendance.setEmployee(employee);
			attendance.setSignInDay(new Date());
			attendance.setSignOutTime(new Date());
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			if (requestAttributes != null) {
				HttpServletRequest request = requestAttributes.getRequest();
				// 设置访问地址
				attendance.setSignInIp(request.getRemoteAddr());
			}
			// 设置用户是否早退
			attendance.setSignOutState(Calendar.getInstance().after(workOutime));
			attendanceMapper.insert(attendance);
		}
	}

	/**
	 * 补签操作
	 * 
	 * @param record
	 */
	@Override
	public void resignIn(Attendance record) {
		// 获取当前登陆对象
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();

		Attendance attendance = attendanceMapper.checkSignIn(record.getEmployee().getId(), record.getSignInDay());

		// 判断是否已签到
		if (attendance == null) {
			record.setResignInDate(new Date());
			record.setSignInState(true);
			record.setSupEmployee(employee);
			attendanceMapper.insert(record);
		} else {
			throw new RuntimeException("该工作日已签到,不需要补签");
		}

	}

	@Override
	public Attendance selectByPrimaryKey(Long id) {
		return attendanceMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Attendance> selectAll() {
		return attendanceMapper.selectAll();
	}
	@Override
	public void updateByPrimaryKey(Attendance record) {
		attendanceMapper.updateByPrimaryKey(record);
	}
	@Override
	public PageResult query(QueryObject qo) {
		int total = attendanceMapper.queryForCount(qo);
		if (total == 0) {
			return new PageResult(0, Collections.emptyList());
		}
		List<Attendance> rows = attendanceMapper.queryForList(qo);
		return new PageResult(total, rows);
	}

	@Test
	public void test1() {
		// 设置上班时间
		Calendar worktime = Calendar.getInstance();
		worktime.set(worktime.get(Calendar.YEAR), worktime.get(Calendar.MONTH), worktime.get(Calendar.DATE), 8, 0, 0);
		// 获取当前时间
		Calendar now = Calendar.getInstance();

		// 比较是否迟到
		System.out.println(now.before(worktime));
		System.out.println(now.after(worktime));
		System.out.println(new Date());
	}
}
