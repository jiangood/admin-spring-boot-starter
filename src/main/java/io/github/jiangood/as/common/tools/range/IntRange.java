package io.github.jiangood.as.common.tools.range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class IntRange extends Range<Integer> {

    public IntRange(Integer start, Integer end) {
        super(start, end);
    }
}
