package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@Setter
@Getter

public class PromotedStudent {

    private Long id;

    private Boolean auditState;

    private String name;

    private BigDecimal totalTuition;

    private BigDecimal toPaidTuition;

    private BigDecimal paidTuition;

    //转学时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date promoteOrRepeatDate;

    private String tel;

    //连接班级
    private ClassGrade classBefore;
    private ClassGrade classAfter;
    //连接员工
    private Employee saleman;
    private Employee audit;
    private Long  formalStudentId;

}