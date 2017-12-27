package cn.wolfcode.edu.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ControllerExceptionHandler {
	/**
	 * 没有权限操作的界面
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public void unauthorizedHandler(HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {

		ResponseBody annotation = handlerMethod.getMethod().getAnnotation(ResponseBody.class);

		if (annotation != null) {
			response.getWriter().print(new ObjectMapper().writeValueAsString(new JsonResult(false, "没有操作权限")));
		}else{
		    
		    response.sendRedirect("/static/commons/unauthorized.jsp");
        }
	}
	
	
}
