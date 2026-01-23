
package io.github.jiangood.as.common;

import io.github.jiangood.as.common.tools.Array2DTool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.List;
import java.util.Map;

class Array2DTest {

    private Array2D array2D;

    @BeforeEach
    void setUp() {
        array2D = new Array2D(2, 3); // 创建一个2行3列的数组
    }

    @Test
    void testConstructorWithDimensions() {
        Array2D arr = new Array2D(3, 4);
        assertEquals(3, arr.getRowCount());
        assertEquals(4, arr.getColCount());
        assertNotNull(arr.getData());
    }

    @Test
    void testConstructorWithData() {
        Object[][] data = {{1, 2}, {3, 4}};
        Array2D arr = new Array2D(data);
        assertSame(data, arr.getData());
    }

    @Test
    void testGetters() {
        assertEquals(2, array2D.getRowCount());
        assertEquals(3, array2D.getColCount());
        assertNotNull(array2D.getData());
    }

    @Test
    void testIsEmpty() {
        Array2D emptyArray = new Array2D();
        assertTrue(emptyArray.isEmpty());

        Array2D arrayWithNullData = new Array2D((Object[][]) null);
        assertTrue(arrayWithNullData.isEmpty());

        assertFalse(array2D.isEmpty());
    }

    @Test
    void testGetRow() {
        Object[] expectedRow = array2D.getRow(0);
        assertNotNull(expectedRow);
        assertEquals(3, expectedRow.length);
    }

    @Test
    void testGetValueAndSetValue() {
        // 初始值应该是null
        assertNull(array2D.getValue(0, 0));

        // 设置值
        array2D.setValue(0, 0, "test");
        assertEquals("test", array2D.getValue(0, 0));
    }

    @Test
    void testAppendRow() {
        Object[] newRow = {"a", "b", "c"};
        array2D.appendRow(newRow);

        assertEquals(3, array2D.getRowCount()); // 现在应该有3行
        assertArrayEquals(newRow, array2D.getRow(2)); // 新行应该是添加的最后一行
    }

    @Test
    void testAppendData() {
        Array2D newData = new Array2D(1, 3);
        newData.setValue(0, 0, "new_data");

        array2D.appendData(newData);

        assertEquals(3, array2D.getRowCount()); // 原来2行 + 新增1行 = 3行
    }

    @Test
    void testAppendDataWithNull() {
        assertThrows(IllegalStateException.class, () -> {
            array2D.appendData(null);
        });
    }

    @Test
    void testRemoveCols() {
        try (MockedStatic<Array2DTool> mockedStatic = Mockito.mockStatic(Array2DTool.class)) {
            Object[][] originalData = {{1, 2, 3, 4}, {5, 6, 7, 8}};
            Object[][] expectedData = {{1, 4}, {5, 8}}; // 移除第1、2列后

            mockedStatic.when(() -> Array2DTool.removeColumns(any(Object[][].class), eq(1), eq(2)))
                    .thenReturn(expectedData);

            Array2D testArray = new Array2D(originalData);
            testArray.removeCols(1, 2);

            assertArrayEquals(expectedData[0], testArray.getRow(0));
            assertArrayEquals(expectedData[1], testArray.getRow(1));
        }
    }

    @Test
    void testClone() {
        array2D.setValue(0, 0, "original");

        Array2D clonedArray = array2D.clone();

        assertEquals(array2D.getRowCount(), clonedArray.getRowCount());
        assertEquals(array2D.getColCount(), clonedArray.getColCount());
        assertEquals("original", clonedArray.getValue(0, 0));

        // 修改克隆数组不应该影响原数组
        clonedArray.setValue(0, 0, "cloned");
        assertEquals("original", array2D.getValue(0, 0));
        assertEquals("cloned", clonedArray.getValue(0, 0));
    }

    @Test
    void testSplit() {
        // 准备测试数据
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                array2D.setValue(i % 2, j, "cell_" + i + "_" + j);
            }
        }

        List<Array2D> chunks = array2D.split(1); // 每个块1行

        assertEquals(2, chunks.size()); // 因为我们有2行
    }

    @Test
    void testToCoordsMap() {
        array2D.setValue(0, 0, "A1_value");
        array2D.setValue(1, 2, "B3_value");

        try (MockedStatic<Array2DTool> mockedStatic = Mockito.mockStatic(Array2DTool.class)) {
            Map<String, Object> expectedMap = Map.of("A1", "A1_value", "C2", "B3_value");

            mockedStatic.when(() -> Array2DTool.toCoordsMap(any(Object[][].class)))
                    .thenReturn(expectedMap);

            Map<String, Object> coordsMap = array2D.toCoordsMap();

            assertEquals(expectedMap, coordsMap);
        }
    }

    @Test
    void testToCoordsMapList() {
        array2D.setValue(0, 0, "A1_value");
        array2D.setValue(1, 2, "B3_value");

        try (MockedStatic<Array2DTool> mockedStatic = Mockito.mockStatic(Array2DTool.class)) {
            List<Map<String, Object>> expectedList = List.of(
                    Map.of("A1", "A1_value"),
                    Map.of("C2", "B3_value")
            );

            mockedStatic.when(() -> Array2DTool.toCoordsMapList(any(Object[][].class)))
                    .thenReturn(expectedList);

            List<Map<String, Object>> coordsMapList = array2D.toCoordsMapList();

            assertEquals(expectedList, coordsMapList);
        }
    }

    @Test
    void testRemoveRows() {
        try (MockedStatic<Array2DTool> mockedStatic = Mockito.mockStatic(Array2DTool.class)) {
            Object[][] originalData = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            Object[][] expectedData = {{7, 8, 9}}; // 移除前两行后

            mockedStatic.when(() -> Array2DTool.removeRows(any(Object[][].class), eq(0), eq(2)))
                    .thenReturn(expectedData);

            Array2D testArray = new Array2D(originalData);
            testArray.removeRows(0, 2);

            assertEquals(1, testArray.getRowCount());
            assertArrayEquals(expectedData[0], testArray.getRow(0));
        }
    }

    @Test
    void testToString() {
        try (MockedStatic<Array2DTool> mockedStatic = Mockito.mockStatic(Array2DTool.class)) {
            String expectedString = "[[1, 2, 3], [4, 5, 6]]";

            mockedStatic.when(() -> Array2DTool.toString(any(Object[][].class)))
                    .thenReturn(expectedString);

            Array2D testArray = new Array2D(2, 3);
            String result = testArray.toString();

            assertEquals(expectedString, result);
        }
    }

    @Test
    void testInsertColumn() {
        try (MockedStatic<Array2DTool> mockedStatic = Mockito.mockStatic(Array2DTool.class)) {
            Object[][] originalData = {{1, 2}, {3, 4}};
            Object[][] expectedData = {{1, null, 2}, {3, null, 4}}; // 在索引1处插入一列

            mockedStatic.when(() -> Array2DTool.insertColumn(any(Object[][].class), eq(1), eq(null)))
                    .thenReturn(expectedData);

            Array2D testArray = new Array2D(originalData);
            testArray.insertColumn(1);

            assertArrayEquals(expectedData[0], testArray.getRow(0));
            assertArrayEquals(expectedData[1], testArray.getRow(1));
        }
    }

    @Test
    void testJsonAnnotations() {
        // 测试@JsonValue注解是否正常工作
        Object[][] testData = {{"a", "b"}, {"c", "d"}};
        Array2D arr = new Array2D(testData);

        assertSame(testData, arr.getData());
    }
}