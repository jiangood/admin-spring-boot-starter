package io.admin.common.utils;

import org.apache.commons.lang3.StringUtils;

public class FileTypeUtils {

    public static boolean isImage(String name) {
        if (name == null) {
            return false;
        }

        return StringUtils.endsWithAny(name.toLowerCase(), ".png", ".jpg", ".git", ".jpeg");
    }

    public static boolean isOffice(String name) {
        if (name == null) {
            return false;
        }

        return StringUtils.endsWithAny(name.toLowerCase(), ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx");
    }

}
