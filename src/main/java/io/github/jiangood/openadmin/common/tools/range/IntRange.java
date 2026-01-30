package io.github.jiangood.openadmin.common.tools.range;

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
