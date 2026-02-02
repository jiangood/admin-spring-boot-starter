package io.github.jiangood.openadmin.lang;

import com.google.common.collect.Multimap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * GoogleTool工具类的单元测试
 */
class GoogleToolTest {

    @Test
    void testNewMultimap() {
        // 测试创建Multimap
        Multimap<String, Integer> multimap = GoogleTool.newMultimap();
        assertNotNull(multimap);

        // 测试添加元素
        multimap.put("key1", 1);
        multimap.put("key1", 2);
        multimap.put("key2", 3);

        // 测试获取元素
        assertEquals(2, multimap.get("key1").size());
        assertEquals(1, multimap.get("key2").size());

        // 测试移除元素
        multimap.remove("key1", 1);
        assertEquals(1, multimap.get("key1").size());

        // 测试清空
        multimap.clear();
        assertEquals(0, multimap.size());
    }

    @Test
    void testImmutableList() {
        // 测试创建不可变List
        List<String> list = GoogleTool.immutableList("a", "b", "c");
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));

        // 测试不可变List是否真的不可变
        assertThrows(UnsupportedOperationException.class, () -> list.add("d"));
    }

    @Test
    void testImmutableSet() {
        // 测试创建不可变Set
        Set<String> set = GoogleTool.immutableSet("a", "b", "c");
        assertNotNull(set);
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));

        // 测试不可变Set是否真的不可变
        assertThrows(UnsupportedOperationException.class, () -> set.add("d"));
    }

    @Test
    void testImmutableMap() {
        // 测试创建不可变Map
        Map<String, Integer> map = GoogleTool.immutableMap("a", 1, "b", 2, "c", 3);
        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(Integer.valueOf(1), map.get("a"));

        // 测试不可变Map是否真的不可变
        assertThrows(UnsupportedOperationException.class, () -> map.put("d", 4));

        // 测试键值对数量为奇数的情况
        assertThrows(IllegalArgumentException.class, () -> GoogleTool.immutableMap("a", 1, "b"));
    }

    @Test
    void testNewHashMap() {
        Map<String, Integer> map = GoogleTool.newHashMap();
        assertNotNull(map);
        map.put("a", 1);
        assertEquals(1, map.size());
    }

    @Test
    void testNewLinkedHashMap() {
        Map<String, Integer> map = GoogleTool.newLinkedHashMap();
        assertNotNull(map);
        map.put("a", 1);
        assertEquals(1, map.size());
    }

    @Test
    void testNewTreeMap() {
        Map<String, Integer> map = GoogleTool.newTreeMap();
        assertNotNull(map);
        map.put("b", 2);
        map.put("a", 1);
        assertEquals(2, map.size());
        // TreeMap会自动排序
        assertEquals("a", map.keySet().iterator().next());
    }

    @Test
    void testNewHashSet() {
        Set<String> set = GoogleTool.newHashSet();
        assertNotNull(set);
        set.add("a");
        assertEquals(1, set.size());
    }

    @Test
    void testNewLinkedHashSet() {
        Set<String> set = GoogleTool.newLinkedHashSet();
        assertNotNull(set);
        set.add("a");
        assertEquals(1, set.size());
    }

    @Test
    void testNewTreeSet() {
        Set<String> set = GoogleTool.newTreeSet();
        assertNotNull(set);
        set.add("b");
        set.add("a");
        assertEquals(2, set.size());
        // TreeSet会自动排序
        assertEquals("a", set.iterator().next());
    }

    @Test
    void testNewArrayList() {
        List<String> list = GoogleTool.newArrayList();
        assertNotNull(list);
        list.add("a");
        assertEquals(1, list.size());
    }

    @Test
    void testNewLinkedList() {
        List<String> list = GoogleTool.newLinkedList();
        assertNotNull(list);
        list.add("a");
        assertEquals(1, list.size());
    }

    @Test
    void testIsNullOrEmpty() {
        assertTrue(GoogleTool.isNullOrEmpty(null));
        assertTrue(GoogleTool.isNullOrEmpty(""));
        assertFalse(GoogleTool.isNullOrEmpty("a"));
    }

    @Test
    void testRepeat() {
        assertEquals("aaa", GoogleTool.repeat("a", 3));
        assertEquals("", GoogleTool.repeat("a", 0));
    }

    @Test
    void testPadStart() {
        assertEquals("0001", GoogleTool.padStart("1", 4, '0'));
        assertEquals("1", GoogleTool.padStart("1", 1, '0'));
        assertEquals("1", GoogleTool.padStart("1", 0, '0'));
    }

    @Test
    void testPadEnd() {
        assertEquals("1000", GoogleTool.padEnd("1", 4, '0'));
        assertEquals("1", GoogleTool.padEnd("1", 1, '0'));
        assertEquals("1", GoogleTool.padEnd("1", 0, '0'));
    }

    @Test
    void testCheckNotNull() {
        String value = "test";
        assertEquals(value, GoogleTool.checkNotNull(value));
        assertThrows(NullPointerException.class, () -> GoogleTool.checkNotNull(null));
        assertThrows(NullPointerException.class, () -> GoogleTool.checkNotNull(null, "Value cannot be null"));
    }

    @Test
    void testCheckArgument() {
        // 测试条件为true的情况
        assertDoesNotThrow(() -> GoogleTool.checkArgument(true));
        assertDoesNotThrow(() -> GoogleTool.checkArgument(true, "Condition should be true"));

        // 测试条件为false的情况
        assertThrows(IllegalArgumentException.class, () -> GoogleTool.checkArgument(false));
        assertThrows(IllegalArgumentException.class, () -> GoogleTool.checkArgument(false, "Condition should be true"));
    }

    @Test
    void testIsCollectionEmpty() {
        // 测试集合
        assertTrue(GoogleTool.isCollectionEmpty(null));
        assertTrue(GoogleTool.isCollectionEmpty(List.of()));
        assertFalse(GoogleTool.isCollectionEmpty(List.of("a")));
    }

    @Test
    void testIsMapEmpty() {
        // 测试Map
        assertTrue(GoogleTool.isMapEmpty((Map<?, ?>) null));
        assertTrue(GoogleTool.isMapEmpty(Map.of()));
        assertFalse(GoogleTool.isMapEmpty(Map.of("a", 1)));
    }

    @Test
    void testIsArrayEmpty() {
        // 测试数组
        assertTrue(GoogleTool.isArrayEmpty((Object[]) null));
        assertTrue(GoogleTool.isArrayEmpty(new Object[0]));
        assertFalse(GoogleTool.isArrayEmpty(new Object[]{"a"}));
    }
}
