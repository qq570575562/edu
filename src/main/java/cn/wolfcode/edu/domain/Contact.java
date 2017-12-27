package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Contact {
    private Long id;

    private String name;

    private String duty;

    private String facukty;

    private String dept;

    private String tel;

    private String email;

    private boolean gander;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone= "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private boolean main;

    private String qq;

    private String intro;

    private Universitytrace university;


}