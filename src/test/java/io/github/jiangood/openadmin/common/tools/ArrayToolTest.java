package io.github.jiangood.openadmin.common.tools;

import cn.hutool.core.lang.Pair;
import io.github.jiangood.openadmin.common.tools.range.IntRange;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayToolTest {


    @Test
    public void testFindContinuousSame_EmptyArray_ReturnsEmptyList() {
        Object[] arr = {};
        List<Pair<Object, IntRange>> result = ArrayTool.findContinuousSame(arr);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindContinuousSame_NullArray_ReturnsEmptyList() {
        Object[] arr = null;
        List<Pair<Object, IntRange>> result = ArrayTool.findContinuousSame(arr);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindContinuousSame_SingleElement() {
        Object[] arr = {"hello"};
        List<Pair<Object, IntRange>> result = ArrayTool.findContinuousSame(arr);

        assertEquals(1, result.size());

        Pair<Object, IntRange> pair = result.get(0);
        assertEquals("hello", pair.getKey());

        IntRange range = pair.getValue();
        assertEquals(0, range.getStart());
        assertEquals(0, range.getEnd());
    }

    @Test
    public void testFindContinuousSame_NoContinuousElements() {
        Object[] arr = {"a", "b", "c", "d"};
        List<Pair<Object, IntRange>> result = ArrayTool.findContinuousSame(arr);

        assertEquals(4, result.size());

        // 检查第一个元素
        Pair<Object, IntRange> pair1 = result.get(0);
        assertEquals("a", pair1.getKey());
        assertEquals(0, pair1.getValue().getStart());
        assertEquals(0, pair1.getValue().getEnd());

        // 检查第二个元素
        Pair<Object, IntRange> pair2 = result.get(1);
        assertEquals("b", pair2.getKey());
        assertEquals(1, pair2.getValue().getStart());
        assertEquals(1, pair2.getValue().getEnd());

        // 检查第三个元素
        Pair<Object, IntRange> pair3 = result.get(2);
        assertEquals("c", pair3.getKey());
        assertEquals(2, pair3.getValue().getStart());
        assertEquals(2, pair3.getValue().getEnd());

        // 检查第四个元素
        Pair<Object, IntRange> pair4 = result.get(3);
        assertEquals("d", pair4.getKey());
        assertEquals(3, pair4.getValue().getStart());
        assertEquals(3, pair4.getValue().getEnd());
    }

    @Test
    public void testFindContinuousSame_WithContinuousElements() {
        Object[] arr = {"a", "a", "b", "c", "c", "c", "d"};
        List<Pair<Object, IntRange>> result = ArrayTool.findContinuousSame(arr);

        assertEquals(4, result.size());

        // 检查第一个连续元素 "a" (位置 0-1)
        Pair<Object, IntRange> pair1 = result.get(0);
        assertEquals("a", pair1.getKey());
        assertEquals(0, pair1.getValue().getStart());
        assertEquals(1, pair1.getValue().getEnd());

        // 检查单独元素 "b" (位置 2)
        Pair<Object, IntRange> pair2 = result.get(1);
        assertEquals("b", pair2.getKey());
        assertEquals(2, pair2.getValue().getStart());
        assertEquals(2, pair2.getValue().getEnd());

        // 检查连续元素 "c" (位置 3-5)
        Pair<Object, IntRange> pair3 = result.get(2);
        assertEquals("c", pair3.getKey());
        assertEquals(3, pair3.getValue().getStart());
        assertEquals(5, pair3.getValue().getEnd());

        // 检查单独元素 "d" (位置 6)
        Pair<Object, IntRange> pair4 = result.get(3);
        assertEquals("d", pair4.getKey());
        assertEquals(6, pair4.getValue().getStart());
        assertEquals(6, pair4.getValue().getEnd());
    }

    @Test
    public void testFindContinuousSame_AllSameElements() {
        Object[] arr = {"x", "x", "x", "x"};
        List<Pair<Object, IntRange>> result = ArrayTool.findContinuousSame(arr);

        assertEquals(1, result.size());

        Pair<Object, IntRange> pair = result.get(0);
        assertEquals("x", pair.getKey());
        assertEquals(0, pair.getValue().getStart());
        assertEquals(3, pair.getValue().getEnd());
    }

    @Test
    public void testFindContinuousSame_MixedDataTypes() {
        Object[] arr = {1, 1, "hello", "hello", 2};
        List<Pair<Object, IntRange>> result = ArrayTool.findContinuousSame(arr);

        assertEquals(3, result.size());

        // 检查连续数字 1 (位置 0-1)
        Pair<Object, IntRange> pair1 = result.get(0);
        assertEquals(1, pair1.getKey());
        assertEquals(0, pair1.getValue().getStart());
        assertEquals(1, pair1.getValue().getEnd());

        // 检查连续字符串 "hello" (位置 2-3)
        Pair<Object, IntRange> pair2 = result.get(1);
        assertEquals("hello", pair2.getKey());
        assertEquals(2, pair2.getValue().getStart());
        assertEquals(3, pair2.getValue().getEnd());

        // 检查单独数字 2 (位置 4)
        Pair<Object, IntRange> pair3 = result.get(2);
        assertEquals(2, pair3.getKey());
        assertEquals(4, pair3.getValue().getStart());
        assertEquals(4, pair3.getValue().getEnd());
    }

    @Test
    public void testFindContinuousSame_WithNullElements() {
        Object[] arr = {"a", null, null, "b"};
        List<Pair<Object, IntRange>> result = ArrayTool.findContinuousSame(arr);

        assertEquals(3, result.size());

        // 检查 "a" (位置 0)
        Pair<Object, IntRange> pair1 = result.get(0);
        assertEquals("a", pair1.getKey());
        assertEquals(0, pair1.getValue().getStart());
        assertEquals(0, pair1.getValue().getEnd());

        // 检查连续 null (位置 1-2)
        Pair<Object, IntRange> pair2 = result.get(1);
        assertNull(pair2.getKey());
        assertEquals(1, pair2.getValue().getStart());
        assertEquals(2, pair2.getValue().getEnd());

        // 检查 "b" (位置 3)
        Pair<Object, IntRange> pair3 = result.get(2);
        assertEquals("b", pair3.getKey());
        assertEquals(3, pair3.getValue().getStart());
        assertEquals(3, pair3.getValue().getEnd());
    }

    @Test
    public void testFindContinuousSame_AllNullElements() {
        Object[] arr = {null, null, null};
        List<Pair<Object, IntRange>> result = ArrayTool.findContinuousSame(arr);

        assertEquals(1, result.size());

        Pair<Object, IntRange> pair = result.get(0);
        assertNull(pair.getKey());
        assertEquals(0, pair.getValue().getStart());
        assertEquals(2, pair.getValue().getEnd());
    }

    @Test
    public void testFindContinuousSame_NumbersWithDuplicates() {
        Object[] arr = {1, 1, 2, 2, 2, 3, 1, 1};
        List<Pair<Object, IntRange>> result = ArrayTool.findContinuousSame(arr);

        assertEquals(4, result.size());

        // 检查连续数字 1 (位置 0-1)
        Pair<Object, IntRange> pair1 = result.get(0);
        assertEquals(1, pair1.getKey());
        assertEquals(0, pair1.getValue().getStart());
        assertEquals(1, pair1.getValue().getEnd());

        // 检查连续数字 2 (位置 2-4)
        Pair<Object, IntRange> pair2 = result.get(1);
        assertEquals(2, pair2.getKey());
        assertEquals(2, pair2.getValue().getStart());
        assertEquals(4, pair2.getValue().getEnd());

        // 检查单独数字 3 (位置 5)
        Pair<Object, IntRange> pair3 = result.get(2);
        assertEquals(3, pair3.getKey());
        assertEquals(5, pair3.getValue().getStart());
        assertEquals(5, pair3.getValue().getEnd());

        // 检查连续数字 1 (位置 6-7)
        Pair<Object, IntRange> pair4 = result.get(3);
        assertEquals(1, pair4.getKey());
        assertEquals(6, pair4.getValue().getStart());
        assertEquals(7, pair4.getValue().getEnd());
    }


}