package cn.wolfcode.edu.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department {

    private Long id;

    private String sn;

    private String name;
    //部门经理
    private Employee manager ;
    //上级部门
    private Department parent ;
    //状态
    private boolean state;

}