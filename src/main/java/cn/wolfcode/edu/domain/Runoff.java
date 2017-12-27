package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Runoff {
    private Long id;

    private String name;//姓名

    private String tel;//电话

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date livedate;//流失时间

    private boolean state;//是否退款

    private boolean classId;//流失班级

    private BigDecimal backMoney;//退款金额

    private String cause;//流失原因

	private Long fsid ;

	//关联其他表
	private Employee sale;//营销人员

	private boolean audit;//审核状态

	private Employee auditor;//审核人
}