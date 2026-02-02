package io.github.jiangood.openadmin.lang.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class RemarkToolTest {

    @Test
    void testGetRemarkWithField() throws NoSuchFieldException {
        // 测试获取带有Remark注解的字段的注解值
        Field field = TestClass.class.getDeclaredField("annotatedField");
        assertEquals("测试字段", RemarkTool.getRemark(field));

        // 测试获取没有Remark注解的字段的注解值
        Field nonAnnotatedField = TestClass.class.getDeclaredField("nonAnnotatedField");
        assertNull(RemarkTool.getRemark(nonAnnotatedField));

        // 测试获取null字段的注解值
        assertNull(RemarkTool.getRemark((Field) null));
    }

    @Test
    void testGetRemarkWithClass() {
        // 测试获取带有Remark注解的类的注解值
        assertEquals("测试类", RemarkTool.getRemark(TestClass.class));

        // 测试获取没有Remark注解的类的注解值
        assertNull(RemarkTool.getRemark(Object.class));

        // 测试获取null类的注解值
        assertNull(RemarkTool.getRemark((Class<?>) null));
    }

    @Test
    void testGetRemarkWithEnum() {
        // 测试获取带有Remark注解的枚举值的注解值
        assertEquals("男", RemarkTool.getRemark(TestEnum.MALE));

        // 测试获取null枚举值的注解值
        assertNull(RemarkTool.getRemark((TestEnum) null));
    }

    @Test
    void testGetRemarkWithMethod() throws NoSuchMethodException {
        // 测试获取方法的Remark注解值（由于Remark注解不支持方法，所以应该返回null）
        Method method = TestClass.class.getDeclaredMethod("annotatedMethod");
        assertNull(RemarkTool.getRemark(method));

        // 测试获取null方法的注解值
        assertNull(RemarkTool.getRemark((Method) null));
    }

    @Remark("测试类")
    static class TestClass {
        @Remark("测试字段")
        private String annotatedField;

        private String nonAnnotatedField;

        public void annotatedMethod() {
        }

        public void nonAnnotatedMethod() {
        }
    }

    enum TestEnum {
        @Remark("男")
        MALE,
        @Remark("女")
        FEMALE,
        // 没有Remark注解的枚举值
        NO_REMARK
    }
}
