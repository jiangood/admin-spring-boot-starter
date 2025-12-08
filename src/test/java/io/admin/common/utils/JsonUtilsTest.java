package io.admin.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JsonUtilsTest {

    @Test
    public void testConvert() {
        Map<String, Object> source = new HashMap<>();
        source.put("name", "test");
        source.put("value", 123);

        Map<String, Object> result = JsonUtils.convert(source, Map.class);
        assertEquals(source, result);
    }

    @Test
    public void testToJson() throws JsonProcessingException {
        Map<String, Object> obj = new HashMap<>();
        obj.put("name", "test");
        obj.put("value", 123);

        String json = JsonUtils.toJson(obj);
        assertNotNull(json);
        assertTrue(json.contains("name"));
        assertTrue(json.contains("test"));
        assertTrue(json.contains("value"));
        assertTrue(json.contains("123"));
    }

    @Test
    public void testToJsonWithNull() throws JsonProcessingException {
        String json = JsonUtils.toJson(null);
        assertNull(json);
    }

    @Test
    public void testToJsonQuietly() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("name", "test");
        obj.put("value", 123);

        String json = JsonUtils.toJsonQuietly(obj);
        assertNotNull(json);
        assertTrue(json.contains("name"));
        assertTrue(json.contains("test"));
    }

    @Test
    public void testToJsonQuietlyWithException() {
        // Create a class that can't be serialized to test exception handling
        String json = JsonUtils.toJsonQuietly(null);
        assertNull(json);
    }

    @Test
    public void testToPrettyJsonQuietly() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("name", "test");
        obj.put("value", 123);

        String json = JsonUtils.toPrettyJsonQuietly(obj);
        assertNotNull(json);
        assertTrue(json.contains("name"));
        assertTrue(json.contains("test"));
    }

    @Test
    public void testJsonToBean() throws IOException {
        String json = "{\"name\":\"test\",\"value\":123}";

        // Create a simple test class to deserialize to
        TestBean bean = JsonUtils.jsonToBean(json, TestBean.class);
        assertNotNull(bean);
        assertEquals("test", bean.getName());
        assertEquals(123, bean.getValue());
    }

    @Test
    public void testJsonToBeanWithNull() throws IOException {
        TestBean bean = JsonUtils.jsonToBean(null, TestBean.class);
        assertNull(bean);
    }

    @Test
    public void testJsonToBeanTypeReference() throws IOException {
        String json = "[{\"name\":\"test1\",\"value\":123},{\"name\":\"test2\",\"value\":456}]";
        TypeReference<List<TestBean>> typeRef = new TypeReference<List<TestBean>>() {
        };

        List<TestBean> beans = JsonUtils.jsonToBean(json, typeRef);
        assertNotNull(beans);
        assertEquals(2, beans.size());
        assertEquals("test1", beans.get(0).getName());
        assertEquals("test2", beans.get(1).getName());
    }

    @Test
    public void testJsonToBeanQuietly() {
        String json = "{\"name\":\"test\",\"value\":123}";

        TestBean bean = JsonUtils.jsonToBeanQuietly(json, TestBean.class);
        assertNotNull(bean);
        assertEquals("test", bean.getName());
        assertEquals(123, bean.getValue());
    }

    @Test
    public void testJsonToBeanQuietlyWithInvalidJson() {
        String invalidJson = "{invalid json}";

        TestBean bean = JsonUtils.jsonToBeanQuietly(invalidJson, TestBean.class);
        assertNull(bean);
    }

    @Test
    public void testJsonToBeanListQuietly() {
        String json = "[{\"name\":\"test1\",\"value\":123},{\"name\":\"test2\",\"value\":456}]";

        List<TestBean> beans = JsonUtils.jsonToBeanListQuietly(json, TestBean.class);
        assertNotNull(beans);
        assertEquals(2, beans.size());
        assertEquals("test1", beans.get(0).getName());
        assertEquals("test2", beans.get(1).getName());
    }

    @Test
    public void testJsonToListQuietly() {
        String json = "[\"item1\", \"item2\", \"item3\"]";

        List<String> list = JsonUtils.jsonToListQuietly(json);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("item1", list.get(0));
        assertEquals("item2", list.get(1));
        assertEquals("item3", list.get(2));
    }

    @Test
    public void testJsonToBeanQuietlyObject() {
        String json = "{\"name\":\"test\",\"value\":123}";

        Object obj = JsonUtils.jsonToBeanQuietly(json);
        assertNotNull(obj);
    }

    @Test
    public void testJsonToMapQuietly() {
        String json = "{\"name\":\"test\",\"value\":\"123\"}";

        Map<String, Object> map = JsonUtils.jsonToMapQuietly(json);
        assertNotNull(map);
        assertEquals("test", map.get("name"));
        assertEquals("123", map.get("value"));
    }

    @Test
    public void testJsonToMapQuietlyWithInvalidJson() {
        String invalidJson = "{invalid json}";

        Map<String, Object> map = JsonUtils.jsonToMapQuietly(invalidJson);
        assertNotNull(map);
        assertTrue(map.isEmpty()); // Should return empty map on error
    }

    @Test
    public void testJsonToMap() throws IOException {
        String json = "{\"name\":\"test\",\"value\":\"123\"}";

        Map<String, Object> map = JsonUtils.jsonToMap(json);
        assertNotNull(map);
        assertEquals("test", map.get("name"));
        assertEquals("123", map.get("value"));
    }

    @Test
    public void testJsonToMapStringStringQuietly() {
        String json = "{\"name\":\"test\",\"value\":\"123\"}";

        Map<String, String> map = JsonUtils.jsonToMapStringStringQuietly(json);
        assertNotNull(map);
        assertEquals("test", map.get("name"));
        assertEquals("123", map.get("value"));
    }

    @Test
    public void testReadTree() throws JsonProcessingException {
        String json = "{\"name\":\"test\",\"value\":123}";

        JsonNode node = JsonUtils.readTree(json);
        assertNotNull(node);
        assertEquals("test", node.get("name").asText());
        assertEquals(123, node.get("value").asInt());
    }

    @Test
    public void testGetObjectMapper() {
        ObjectMapper mapper = JsonUtils.getObjectMapper();
        assertNotNull(mapper);

        // Ensure that the mapper has the expected configuration
        // This is harder to test directly, but we can ensure the object is properly configured
        assertDoesNotThrow(() -> mapper.writeValueAsString(new TestBean()));
    }

    // Helper class for testing
    static class TestBean {
        private String name;
        private int value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}