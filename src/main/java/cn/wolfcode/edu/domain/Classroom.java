package cn.wolfcode.edu.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Classroom {
    private Long id;

    private String name;

    private String address;

    private Integer seatCount;

    private boolean state = true;

    private String slogan;


}