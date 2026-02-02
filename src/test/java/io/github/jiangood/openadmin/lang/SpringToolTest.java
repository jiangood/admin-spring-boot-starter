package io.github.jiangood.openadmin.lang;

import io.github.jiangood.openadmin.BasePackage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringToolTest.TestApplication.class)
class SpringToolTest {

    @SpringBootApplication
    @ComponentScan(basePackageClasses = BasePackage.class)
    static class TestApplication {
    }

    @Test
    void testGetApplicationContext() {
        assertNotNull(SpringTool.getApplicationContext());
    }

    @Test
    void testGetBasePackageNames() {
        String[] basePackageNames = SpringTool.getBasePackageNames();
        assertNotNull(basePackageNames);
        assertTrue(basePackageNames.length > 0);
    }

    @Test
    void testGetBasePackageClasses() {
        assertNotNull(SpringTool.getBasePackageClasses());
        assertTrue(SpringTool.getBasePackageClasses().size() > 0);
    }

    @Test
    void testGetBeanByName() {
        // 测试获取存在的Bean
        assertNotNull(SpringTool.getBean("springToolTest.TestApplication"));
    }

    @Test
    void testGetBeanByClass() {
        // 测试获取存在的Bean
        assertNotNull(SpringTool.getBean(TestApplication.class));
        
        // 测试获取不存在的Bean，应该返回null
        assertNull(SpringTool.getBean(String.class));
    }

    @Test
    void testGetBeanByNameAndClass() {
        // 测试获取存在的Bean
        assertNotNull(SpringTool.getBean("springToolTest.TestApplication", TestApplication.class));
    }

    @Test
    void testGetBeansOfType() {
        assertNotNull(SpringTool.getBeansOfType(TestApplication.class));
        assertTrue(SpringTool.getBeansOfType(TestApplication.class).size() > 0);
    }

    @Test
    void testGetBeanNames() {
        assertNotNull(SpringTool.getBeanNames(TestApplication.class));
        assertTrue(SpringTool.getBeanNames(TestApplication.class).size() > 0);
    }

    @Test
    void testGetBeans() {
        assertNotNull(SpringTool.getBeans(TestApplication.class));
        assertTrue(SpringTool.getBeans(TestApplication.class).size() > 0);
    }

    @Test
    void testGetBeanNamesForType() {
        String[] beanNames = SpringTool.getBeanNamesForType(TestApplication.class);
        assertNotNull(beanNames);
        assertTrue(beanNames.length > 0);
    }

    @Test
    void testGetProperty() {
        // 测试获取存在的配置项
        assertNotNull(SpringTool.getProperty("spring.application.name"));
        
        // 测试获取不存在的配置项
        assertNull(SpringTool.getProperty("non.existent.property"));
    }

    @Test
    void testGetApplicationName() {
        assertNotNull(SpringTool.getApplicationName());
    }

    @Test
    void testGetActiveProfiles() {
        String[] activeProfiles = SpringTool.getActiveProfiles();
        assertNotNull(activeProfiles);
    }

    @Test
    void testHasProfile() {
        // 测试当前环境是否包含默认配置文件
        assertTrue(SpringTool.hasProfile("default"));
        
        // 测试当前环境是否包含不存在的配置文件
        assertFalse(SpringTool.hasProfile("non.existent.profile"));
    }

    @Test
    void testPublishEvent() {
        // 测试发布事件，应该不会抛出异常
        assertDoesNotThrow(() -> SpringTool.publishEvent(new TestEvent(this)));
    }

    @Test
    void testPublishEventAsync() {
        // 测试异步发布事件，应该不会抛出异常
        assertDoesNotThrow(() -> SpringTool.publishEventAsync(new TestEvent(this)));
    }

    @Test
    void testPublishEventWithObject() {
        // 测试发布普通对象作为事件，应该不会抛出异常
        assertDoesNotThrow(() -> SpringTool.publishEvent(new Object()));
    }

    static class TestEvent extends ApplicationEvent {
        public TestEvent(Object source) {
            super(source);
        }
    }
}
