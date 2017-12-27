package cn.wolfcode.edu.domain;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Employee {
	private Long id;

	private String username;

	private String realname;

	private String password;

	private String tel;

	private String email;

	private Department dept;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inputTime;

	private BigDecimal salary;

	private boolean state;

	private boolean admin;

	List<Role> roles = new ArrayList<>();
	// 头像路径
	private String imagePath;

	public String getImageSmall() {

		if (StringUtils.isEmpty(imagePath)) {
			return "";
		} else {
			int index = imagePath.lastIndexOf(".");
			return imagePath.substring(0, index) + "_small" + imagePath.substring(index);
		}
	}
}