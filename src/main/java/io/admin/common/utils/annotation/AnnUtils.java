package io.admin.common.utils.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnUtils {

    public static boolean hasAnn(Field field, String annName) {
        Annotation[] anns = field.getAnnotations();
        for (Annotation ann : anns) {
            if (ann.annotationType().getSimpleName().equals(annName)) {
                return true;
            }
        }
        return false;
    }


}
