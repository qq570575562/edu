package cn.wolfcode.edu.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SystemDictionaryItem {

    private Long id;

    private String name;

    private String intro;

    private SystemDictionary systemDictionary;


}