package io.github.jiangood.openadmin.common.tools;

import cn.hutool.core.lang.Pair;
import io.github.jiangood.openadmin.common.tools.range.IntRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ArrayTool {

    public static boolean containsType(Object[] arr, Class<?> clazz) {
        if (arr == null) {
            return false;
        }
        for (Object o : arr) {
            if (o != null && o.getClass() == clazz) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllType(Object[] arr, Class<?> clazz) {
        if (arr == null) {
            return false;
        }
        for (Object o : arr) {
            if (o == null || o.getClass() != clazz) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查找数组中连续相同的元素
     *
     * @return 元素及对应的range
     */
    public static List<Pair<Object, IntRange>> findContinuousSame(Object[] arr) {
        List<Pair<Object, IntRange>> list = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            return list;
        }

        int i = 0;
        while (i < arr.length) {
            Object currentElement = arr[i];
            int start = i;

            // 找到当前元素连续出现的结束位置
            while (i < arr.length && Objects.equals(arr[i], currentElement)) {
                i++;
            }

            int end = i - 1;
            if (start != end) {
                list.add(new Pair<>(currentElement, new IntRange(start, end)));
            }
        }

        return list;
    }


    public static String[] toStrArr(List<String> list) {
        if (list == null) {
            return new String[0];
        }
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            arr[i] = s;
        }
        return arr;
    }

    public static <T> int findIndex(T[] arr, Function<T, Boolean> fn) {
        for (int i = 0; i < arr.length; i++) {
            T t = arr[i];
            Boolean result = fn.apply(t);
            if (result) {
                return i;
            }
        }

        return -1;

    }


    public static <T> List<T> toList(T[] arr) {
        if (arr == null || arr.length == 0) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        for (T t : arr) {
            list.add(t);
        }
        return list;
    }
}
