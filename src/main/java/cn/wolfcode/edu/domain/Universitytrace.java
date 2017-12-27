package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Universitytrace {
    private Long id;

    private String name;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date prevtrancetime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nexttrancetime;

    //联系人
    private Contact contact;

    /**
     * 数据字典
     */
    private SystemDictionaryItem importance;

    private SystemDictionaryItem wantedlevel;

    private SystemDictionaryItem subject;

    private SystemDictionaryItem college;

    private SystemDictionaryItem type;

    /**
     * 关联员工
     */
    private Employee marketer;

    private Employee tracer;

    /**
     * 常见
     */
    private Boolean tracestate;

    private Boolean customerstatus;

    private String schooltel;

    private String email;

    private Integer stunumber;

    private Integer itstunumber;

    private String telegraph;

    private String postcode;

    private Integer teachernumber;

    private String remark;

    private String introduction;

}