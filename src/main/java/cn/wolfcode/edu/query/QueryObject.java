package cn.wolfcode.edu.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {

	private int page ;
	private int rows ;
	private String keyword ;
	public int getBeginIndex(){
		return (page-1)*rows;
	}
}
