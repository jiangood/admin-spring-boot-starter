package io.github.jiangood.as.common.tools.range;

import lombok.Getter;
import lombok.Setter;


/**
 * 区间，如开始日期，结束日期
 */
@Getter
@Setter
public class Range<T extends Comparable<T>> {

    T start;
    T end;

    public Range() {
    }

    public Range(T start, T end) {
        this.start = start;
        this.end = end;
    }


    public boolean isEmpty() {
        return start == null && end == null;
    }
}
