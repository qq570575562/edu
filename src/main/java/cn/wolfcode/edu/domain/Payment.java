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
public class Payment {
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    private Long cost;

    private String digest;

    private Employee payer;

    private Employee brokerage;

    private SystemDictionaryItem paymode;

    private String costtype;

    private Long docnumber;

    private ClassGrade classGrade;

    private Employee auditor;

    private boolean state = false;

}