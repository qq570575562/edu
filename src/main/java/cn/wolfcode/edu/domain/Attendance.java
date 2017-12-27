package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class Attendance {

	private Long id;
	// 签到日期
	private String signInIp;
	// 签到日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date signInDay;
	// 签到时间
	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date signInTime;

	// 签退时间
	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date signOutTime;

	// 签到状态
	private Boolean signInState;

	// 签退状态
	private Boolean signOutState;

	// 补签时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date resignInDate;

	// 员工
	private Employee employee;
	// 补签人员
	private Employee supEmployee;
	// 补签事由
	private String cause;

}