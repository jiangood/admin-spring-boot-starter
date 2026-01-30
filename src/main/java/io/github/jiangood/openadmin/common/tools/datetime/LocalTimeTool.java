package io.github.jiangood.openadmin.common.tools.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalTimeTool {

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


}
