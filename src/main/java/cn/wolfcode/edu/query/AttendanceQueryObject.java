package cn.wolfcode.edu.query;

import cn.wolfcode.edu.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class AttendanceQueryObject extends QueryObject {

	// 签到开始时间 日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate;

	// 签到结束日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	public Date getEndDate() {
		return DateUtil.getEndDate(this.endDate);
	}
}
