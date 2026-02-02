package io.github.jiangood.openadmin.lang.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class AnnToolTest {

    @Test
    void testHasAnnWithExistingAnnotation() throws NoSuchFieldException {
        // 获取带有Remark注解的字段
        Field field = TestClass.class.getDeclaredField("annotatedField");
        // 测试该字段是否有Remark注解
        assertTrue(AnnTool.hasAnn(field, "Remark"));
    }

    @Test
    void testHasAnnWithNonExistingAnnotation() throws NoSuchFieldException {
        // 获取没有Remark注解的字段
        Field field = TestClass.class.getDeclaredField("nonAnnotatedField");
        // 测试该字段是否有Remark注解
        assertFalse(AnnTool.hasAnn(field, "Remark"));
    }

    @Test
    void testHasAnnWithNonExistingAnnotationName() throws NoSuchFieldException {
        // 获取带有Remark注解的字段
        Field field = TestClass.class.getDeclaredField("annotatedField");
        // 测试该字段是否有不存在的注解
        assertFalse(AnnTool.hasAnn(field, "NonExistingAnnotation"));
    }

    static class TestClass {
        @Remark("测试字段")
        private String annotatedField;

        private String nonAnnotatedField;
    }
}
