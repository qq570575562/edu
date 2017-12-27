package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class FormalStudent {

    private Long id;

    private String name;

    private String qqNum;

    //时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enrollDate;//工作年限
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastPaymentDate;

    //数据字典
    private SystemDictionaryItem source;

    private SystemDictionaryItem foreignLangLevel;

    private SystemDictionaryItem education;

    private SystemDictionaryItem clientType;

    private SystemDictionaryItem campus;

    private SystemDictionaryItem payMode;
     //联表数据
    private ClassGrade clz;

    private Employee saleman;

    //意图表需要中间表保存(暂不做处理)
    private Object employeementIntention;

    /**
     * 下拉框数据
     */
    private boolean gander;
    //多选框
    private Byte state;
    //状态
    private boolean paidState;


    //常规字段
    private String tel;

    private String email;

    private Integer age;

    private String school;

    private String major;

    private String address;

    private String yearsOfWorking;



    private String identityNum;

    private String urgentContact;

    private String urgentTel;

    private String workingExperience;

    //金融数据

    private BigDecimal planningTuition;

    private BigDecimal discountAmount;

    private BigDecimal totalTuition;

    private BigDecimal otherCost;

    private BigDecimal otherDiscount;

    private BigDecimal discountDesc;

    private BigDecimal paidTuition;

    private BigDecimal ownTuition;

    private BigDecimal prePaidTuition;

	private Long psid ;

}