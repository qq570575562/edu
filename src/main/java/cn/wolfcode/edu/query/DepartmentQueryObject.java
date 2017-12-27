package cn.wolfcode.edu.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentQueryObject extends QueryObject {
    private int page = 1;
    private int rows = 10;
}
