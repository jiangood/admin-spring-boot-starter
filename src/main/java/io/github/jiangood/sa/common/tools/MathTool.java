package io.github.jiangood.sa.common.tools;

public class MathTool {


    public static String percentStr(Number n, Number total) {
        double percent = n.doubleValue() / total.doubleValue();
        String fixed2 = DecimalTool.toFixed2(percent * 100);
        return fixed2 + "%";
    }


}
