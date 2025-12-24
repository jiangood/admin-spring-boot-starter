package io.github.jiangood.sa.common.tools;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PageTool {

    public static <T, R> Page<R> convert(Page<T> page, PageItemConverter<T, R> converter) {
        List<R> resultList = page.getContent().stream()
                .map(converter::convert)
                .toList();
        return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
    }

    public interface PageItemConverter<T, R> {
        R convert(T t);
    }
}
