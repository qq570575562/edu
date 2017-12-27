package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
public class Salary {
	private Long id;

	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date month;

	private BigDecimal salary;

	private Integer workDay;

	// 迟到次数
	private Integer afterNumber;

	// 早退次数
	private Integer beforeNumber;

	// 补签次数
	private Integer resignNumber;

	// 结算工资
	private BigDecimal nowSalary;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date paytime;

	private Employee employee;
}