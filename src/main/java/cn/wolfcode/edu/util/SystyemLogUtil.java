package cn.wolfcode.edu.util;

import cn.wolfcode.edu.domain.Employee;
import cn.wolfcode.edu.domain.SystemLog;
import cn.wolfcode.edu.service.ISystemLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class SystyemLogUtil {
	@Autowired
	private ISystemLogService systemLogService;

	public void writerLog(JoinPoint joinPoint) {


		if (joinPoint.getTarget() instanceof ISystemLogService) {
			return;
		}

		SystemLog systemLog = new SystemLog();
		// 设置操作时间
		systemLog.setOptime(new Date());
		// 设置操作者
		Object principal = SecurityUtils.getSubject().getPrincipal();
		if (principal != null) {
			systemLog.setOpuser((Employee) principal);
		}
		/// 设置执行的ip
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = requestAttributes.getRequest();
			// 设置访问地址
			systemLog.setOpip(request.getRemoteAddr());
		}

		// 设置执行的方法
		String target = joinPoint.getTarget().getClass().getName();
		String method = joinPoint.getSignature().getName();
		systemLog.setFunction(target + ":" + method);

		// 设置执行的方法的参数股
		String args = null;
		try {
			args = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		systemLog.setParmas(args);

		systemLogService.insert(systemLog);
	}
}
