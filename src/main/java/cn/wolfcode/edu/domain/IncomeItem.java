package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class IncomeItem {
    private Long id;

    private String name;

    private String className;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;

    private BigDecimal inMoney;

    private BigDecimal ownTuition;

    //数据字典
    private SystemDictionaryItem payMode;

    private String paee;

    private String info;

    private String saleman;

    private boolean status;

    private String auditor;

    private Long formalstudentId;

}