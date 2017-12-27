package cn.wolfcode.edu.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class FormalStudentChart {
    private String groupBy;
    private BigDecimal paidTuition;
    private BigDecimal ownTuition;
    private Long paidNumber;
}
