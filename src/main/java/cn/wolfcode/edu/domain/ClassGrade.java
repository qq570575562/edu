package cn.wolfcode.edu.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClassGrade {

    private Long id;

    private String className;

    private Long studentCount;

    private SystemDictionaryItem systemDictionaryItem;

    private boolean state;

    private Classroom classroom;

    private Employee leader;
}