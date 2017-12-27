package cn.wolfcode.edu.util;

import java.math.BigDecimal;

public class MoneyUtil {

    public static boolean compareMoney(BigDecimal money) {

        if (money.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        } else {
            return true;
        }
    }

}
