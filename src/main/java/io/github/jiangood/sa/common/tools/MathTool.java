package io.github.jiangood.sa.common.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathTool {


    public static String percentStr(Number n, Number total) {
        return percentStr(n, total, 2);
    }

    public static String percentStr(Number n, Number total,int decimalPlaces) {
        double percent = n.doubleValue() / total.doubleValue();
        double v = percent * 100;

        return NumberTool.formatNumber(v, decimalPlaces);
    }



    public static void main(String[] args) {
        System.out.println(percentStr(1, 5));
    }
}
