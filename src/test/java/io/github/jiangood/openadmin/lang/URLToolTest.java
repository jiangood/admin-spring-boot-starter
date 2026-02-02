package io.github.jiangood.openadmin.lang;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class URLToolTest {

    @Test
    void testRemoveQueryString() {
        assertEquals("https://example.com", URLTool.removeQueryString("https://example.com"));
        assertEquals("https://example.com", URLTool.removeQueryString("https://example.com?name=test"));
        assertEquals("https://example.com/path", URLTool.removeQueryString("https://example.com/path?name=test&age=18"));
    }

    @Test
    void testGetParams() {
        // 测试空URL
        Map<String, String> emptyMap = URLTool.getParams("");
        assertTrue(emptyMap.isEmpty());

        // 测试没有查询参数的URL
        Map<String, String> noParamsMap = URLTool.getParams("https://example.com");
        assertTrue(noParamsMap.isEmpty());

        // 测试有查询参数的URL
        Map<String, String> paramsMap = URLTool.getParams("https://example.com?name=test&age=18");
        assertEquals(2, paramsMap.size());
        assertEquals("test", paramsMap.get("name"));
        assertEquals("18", paramsMap.get("age"));

        // 测试直接传入查询字符串
        Map<String, String> queryMap = URLTool.getParams("name=test&age=18");
        assertEquals(2, queryMap.size());
        assertEquals("test", queryMap.get("name"));
        assertEquals("18", queryMap.get("age"));
    }

    @Test
    void testGetParam() {
        assertEquals("test", URLTool.getParam("https://example.com?name=test&age=18", "name"));
        assertEquals("18", URLTool.getParam("https://example.com?name=test&age=18", "age"));
        assertNull(URLTool.getParam("https://example.com?name=test", "age"));
        assertNull(URLTool.getParam("https://example.com", "name"));
    }

    @Test
    void testAppendParam() {
        assertEquals("https://example.com?name=test", URLTool.appendParam("https://example.com", "name", "test"));
        assertEquals("https://example.com?name=test&age=18", URLTool.appendParam("https://example.com?name=test", "age", 18));
    }

    @Test
    void testAppendPath() {
        assertEquals("https://example.com/list/detail", URLTool.appendPath("https://example.com/list", "/detail"));
        assertEquals("https://example.com/list/detail?name=abc", URLTool.appendPath("https://example.com/list?name=abc", "/detail"));
    }

    @Test
    void testGetBaseUrl() {
        assertEquals("https://baidu.com", URLTool.getBaseUrl("https://baidu.com/a/b?id=1123"));
        assertEquals("http://example.com", URLTool.getBaseUrl("http://example.com"));
        assertEquals("https://example.com", URLTool.getBaseUrl("https://example.com?name=test"));
    }

    @Test
    void testGetBaseUrlEndIndex() {
        assertEquals(18, URLTool.getBaseUrlEndIndex("https://baidu.com/a/b?id=1123"));
        assertEquals(19, URLTool.getBaseUrlEndIndex("http://example.com"));
        assertEquals(19, URLTool.getBaseUrlEndIndex("http://example.com?name=test"));
    }

    @Test
    void testHasBaseUrl() {
        assertTrue(URLTool.hasBaseUrl("https://example.com"));
        assertTrue(URLTool.hasBaseUrl("http://example.com"));
        assertTrue(URLTool.hasBaseUrl("//example.com"));
        assertTrue(URLTool.hasBaseUrl("ws://example.com"));
        assertFalse(URLTool.hasBaseUrl("/path"));
        assertFalse(URLTool.hasBaseUrl("path?name=test"));
    }

    @Test
    void testGetPath() {
        assertEquals("/a/b?id=1123", URLTool.getPath("https://baidu.com/a/b?id=1123"));
        assertEquals("", URLTool.getPath("https://example.com"));
        assertEquals("?name=test", URLTool.getPath("https://example.com?name=test"));
    }
}
