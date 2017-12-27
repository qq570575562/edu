package cn.wolfcode.edu.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Role {
	private Long id;

	private String sn;

	private String name;

	List<Permission> permissions = new ArrayList<>();

}