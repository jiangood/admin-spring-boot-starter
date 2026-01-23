
package io.github.jiangood.as.common.tools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class Array2DToolTest {

    @Test
    public void testDeepCopy() {
        Object[][] original = {
                {"A", "B", "C"},
                {"D", "E", "F"}
        };

        Object[][] copied = Array2DTool.deepCopy(original);

        // 验证复制成功
        assertNotSame(original, copied);
        assertArrayEquals(original[0], copied[0]);
        assertArrayEquals(original[1], copied[1]);

        // 验证修改不影响原数组
        copied[0][0] = "X";
        assertEquals("A", original[0][0]);
        assertEquals("X", copied[0][0]);
    }

    @Test
    public void testDeepCopyWithArrays() {
        Object[][] src = {
                {"A", "B", "C"},
                {"D", "E", "F"}
        };
        Object[][] dest = new Object[2][3];

        Array2DTool.deepCopy(src, dest);

        assertArrayEquals(src[0], dest[0]);
        assertArrayEquals(src[1], dest[1]);
    }

    @Test
    public void testSetValueAutoGrow() {
        // 测试null数组情况
        Object[][] data = null;
        data = Array2DTool.setValueAutoGrow(data, 1, 2, "test");
        assertEquals("test", data[1][2]);
        assertEquals(null, data[0][0]);

        // 测试需要扩展的情况
        data = new Object[1][1];
        data = Array2DTool.setValueAutoGrow(data, 2, 3, "expanded");
        assertEquals("expanded", data[2][3]);
        assertEquals(3, data.length);
        assertEquals(4, data[0].length);

        // 测试不需要扩展的情况
        data[1][1] = "existing";
        data = Array2DTool.setValueAutoGrow(data, 1, 1, "updated");
        assertEquals("updated", data[1][1]);
    }

    @Test
    public void testRemoveColumns() {
        Object[][] data = {
                {"A", "B", "C", "D"},
                {"E", "F", "G", "H"},
                {"I", "J", "K", "L"}
        };

        Object[][] result = Array2DTool.removeColumns(data, 1, 2);

        assertEquals(3, result.length);
        assertEquals(2, result[0].length);
        assertEquals("A", result[0][0]);
        assertEquals("D", result[0][1]);
        assertEquals("E", result[1][0]);
        assertEquals("H", result[1][1]);
        assertEquals("I", result[2][0]);
        assertEquals("L", result[2][1]);
    }

    @Test
    public void testRemoveRows() {
        Object[][] data = {
                {"A", "B"},
                {"C", "D"},
                {"E", "F"},
                {"G", "H"}
        };

        Object[][] result = Array2DTool.removeRows(data, 1, 2);

        assertEquals(2, result.length);
        assertEquals("A", result[0][0]);
        assertEquals("B", result[0][1]);
        assertEquals("G", result[1][0]);
        assertEquals("H", result[1][1]);
    }

    @Test
    public void testSetValue() {
        Object[][] data = new Object[3][3];

        Array2DTool.setValue(data, 1, 2, "testValue");
        assertEquals("testValue", data[1][2]);

        // 测试边界检查
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Array2DTool.setValue(data, 5, 0, "outOfBounds");
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            Array2DTool.setValue(data, 0, 5, "outOfBounds");
        });
    }

    @Test
    public void testSetValueByExcelCoordinate() {
        Object[][] data = new Object[3][3];

        Array2DTool.setValueByExcelCoordinate(data, "B2", "ExcelValue");
        assertEquals("ExcelValue", data[1][1]); // B2 -> [1,1]

        // 测试边界检查
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Array2DTool.setValueByExcelCoordinate(data, "Z9", "outOfBounds");
        });
    }

    @Test
    public void testSetValues() {
        Object[][] data = new Object[3][3];

        Map<String, Object> positions = new HashMap<>();
        positions.put("A1", "TopLeft");
        positions.put("C3", "BottomRight");
        positions.put("1,1", "Middle");

        Array2DTool.setValues(data, positions);

        assertEquals("TopLeft", data[0][0]);
        assertEquals("BottomRight", data[2][2]);
        assertEquals("Middle", data[1][1]);
    }

    @Test
    public void testSetRow() {
        Object[][] data = {
                {"A", "B", "C"},
                {"D", "E", "F"},
                {"G", "H", "I"}
        };

        Object[] newRow = {"X", "Y", "Z"};
        Array2DTool.setRow(data, 1, newRow);

        assertEquals("X", data[1][0]);
        assertEquals("Y", data[1][1]);
        assertEquals("Z", data[1][2]);
        assertEquals("A", data[0][0]); // 其他行不应改变
    }

    @Test
    public void testSetColumn() {
        Object[][] data = {
                {"A", "B", "C"},
                {"D", "E", "F"},
                {"G", "H", "I"}
        };

        Object[] newCol = {"X", "Y", "Z"};
        Array2DTool.setColumn(data, 1, newCol);

        assertEquals("X", data[0][1]);
        assertEquals("Y", data[1][1]);
        assertEquals("Z", data[2][1]);
        assertEquals("A", data[0][0]); // 其他列不应改变
    }

    @Test
    public void testFillRegion() {
        Object[][] data = new Object[4][4];

        Array2DTool.fillRegion(data, 1, 1, 2, 2, "Filled");

        // 区域内应被填充
        assertEquals("Filled", data[1][1]);
        assertEquals("Filled", data[1][2]);
        assertEquals("Filled", data[2][1]);
        assertEquals("Filled", data[2][2]);

        // 区域外不应改变
        assertEquals(null, data[0][0]);
        assertEquals(null, data[3][3]);
    }

    @Test
    public void testInsertColumn() {
        Object[][] original = {
                {"A", "B"},
                {"C", "D"}
        };

        Object[] newCol = {"X", "Y"};
        Object[][] result = Array2DTool.insertColumn(original, 1, newCol);

        assertEquals(2, result.length);
        assertEquals(3, result[0].length);
        assertEquals("A", result[0][0]);
        assertEquals("X", result[0][1]);
        assertEquals("B", result[0][2]);
        assertEquals("C", result[1][0]);
        assertEquals("Y", result[1][1]);
        assertEquals("D", result[1][2]);
    }

    @Test
    public void testAppendColumn() {
        Object[][] original = {
                {"A", "B"},
                {"C", "D"}
        };

        Object[] newCol = {"X", "Y"};
        Object[][] result = Array2DTool.appendColumn(original, newCol);

        assertEquals(2, result.length);
        assertEquals(3, result[0].length);
        assertEquals("A", result[0][0]);
        assertEquals("B", result[0][1]);
        assertEquals("X", result[0][2]);
        assertEquals("C", result[1][0]);
        assertEquals("D", result[1][1]);
        assertEquals("Y", result[1][2]);
    }

    @Test
    public void testInsertRow() {
        Object[][] original = {
                {"A", "B"},
                {"C", "D"}
        };

        Object[] newRow = {"X", "Y"};
        Object[][] result = Array2DTool.insertRow(original, 1, newRow);

        assertEquals(3, result.length);
        assertEquals(2, result[0].length);
        assertEquals("A", result[0][0]);
        assertEquals("B", result[0][1]);
        assertEquals("X", result[1][0]);
        assertEquals("Y", result[1][1]);
        assertEquals("C", result[2][0]);
        assertEquals("D", result[2][1]);
    }

    @Test
    public void testAppendRow() {
        Object[][] original = {
                {"A", "B"},
                {"C", "D"}
        };

        Object[] newRow = {"X", "Y"};
        Object[][] result = Array2DTool.appendRow(original, newRow);

        assertEquals(3, result.length);
        assertEquals(2, result[0].length);
        assertEquals("A", result[0][0]);
        assertEquals("B", result[0][1]);
        assertEquals("C", result[1][0]);
        assertEquals("D", result[1][1]);
        assertEquals("X", result[2][0]);
        assertEquals("Y", result[2][1]);
    }

    @Test
    public void testAppendRows() {
        Object[][] original = {
                {"A", "B"},
                {"C", "D"}
        };

        Object[][] rowsToAdd = {
                {"X", "Y"},
                {"M", "N"}
        };

        Object[][] result = Array2DTool.appendRows(original, rowsToAdd);

        assertEquals(4, result.length);
        assertEquals("A", result[0][0]);
        assertEquals("B", result[0][1]);
        assertEquals("C", result[1][0]);
        assertEquals("D", result[1][1]);
        assertEquals("X", result[2][0]);
        assertEquals("Y", result[2][1]);
        assertEquals("M", result[3][0]);
        assertEquals("N", result[3][1]);
    }

    @Test
    public void testSplitByRows() {
        Object[][] original = {
                {"A", "B"},
                {"C", "D"},
                {"E", "F"},
                {"G", "H"},
                {"I", "J"}
        };

        List<Object[][]> chunks = Array2DTool.splitByRows(original, 2);

        assertEquals(3, chunks.size());
        assertEquals(2, chunks.get(0).length);
        assertEquals(2, chunks.get(1).length);
        assertEquals(1, chunks.get(2).length);
        assertEquals("A", chunks.get(0)[0][0]);
        assertEquals("C", chunks.get(0)[1][0]);
        assertEquals("E", chunks.get(1)[0][0]);
        assertEquals("G", chunks.get(1)[1][0]);
        assertEquals("I", chunks.get(2)[0][0]);
    }

    @Test
    public void testToCoordsMap() {
        Object[][] data = {
                {"A", "B"},
                {"C", "D"}
        };

        Map<String, Object> coordsMap = Array2DTool.toCoordsMap(data);

        assertEquals("A", coordsMap.get("A1"));
        assertEquals("B", coordsMap.get("B1"));
        assertEquals("C", coordsMap.get("A2"));
        assertEquals("D", coordsMap.get("B2"));
    }

    @Test
    public void testToCoordsMapList() {
        Object[][] data = {
                {"A", "B"},
                {"C", "D"}
        };

        List<Map<String, Object>> coordsMapList = Array2DTool.toCoordsMapList(data);

        assertEquals(2, coordsMapList.size());
        assertEquals("A", coordsMapList.get(0).get("A"));
        assertEquals("B", coordsMapList.get(0).get("B"));
        assertEquals("C", coordsMapList.get(1).get("A"));
        assertEquals("D", coordsMapList.get(1).get("B"));
    }

    @Test
    public void testIndexToCoords() {
        assertEquals("A1", Array2DTool.indexToCoords(0, 0));
        assertEquals("B1", Array2DTool.indexToCoords(0, 1));
        assertEquals("A2", Array2DTool.indexToCoords(1, 0));
        assertEquals("Z1", Array2DTool.indexToCoords(0, 25));
        assertEquals("AA1", Array2DTool.indexToCoords(0, 26));
    }

    @Test
    public void testIndexToCoordsCol() {
        assertEquals("A", Array2DTool.indexToCoordsCol(0));
        assertEquals("B", Array2DTool.indexToCoordsCol(1));
        assertEquals("Z", Array2DTool.indexToCoordsCol(25));
        assertEquals("AA", Array2DTool.indexToCoordsCol(26));
        assertEquals("AB", Array2DTool.indexToCoordsCol(27));
    }

    @Test
    public void testCoordsToIndex() {
        int[] result = Array2DTool.coordsToIndex("A1");
        assertEquals(0, result[0]); // row
        assertEquals(0, result[1]); // col

        result = Array2DTool.coordsToIndex("B2");
        assertEquals(1, result[0]); // row
        assertEquals(1, result[1]); // col

        result = Array2DTool.coordsToIndex("AA1");
        assertEquals(0, result[0]); // row
        assertEquals(26, result[1]); // col
    }

    @Test
    public void testToString() {
        Object[][] data = {
                {"A", "B"},
                {"C", "D"}
        };

        String str = Array2DTool.toString(data);
        assertTrue(str.startsWith("2x2")); // 2行2列
        assertTrue(str.contains("[A, B]"));
        assertTrue(str.contains("[C, D]"));
    }

    @Test
    public void testCreate() {
        Object[][] result = Array2DTool.create(2, 3, "default");

        assertEquals(2, result.length);
        assertEquals(3, result[0].length);
        assertEquals("default", result[0][0]);
        assertEquals("default", result[1][2]);
    }

    @Test
    public void testEdgeCases() {
        // 测试边界条件和异常情况
        assertThrows(IllegalArgumentException.class, () -> {
            Array2DTool.setValueAutoGrow(new Object[1][1], -1, 0, "negative");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Array2DTool.setValueAutoGrow(new Object[1][1], 0, -1, "negative");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Array2DTool.removeColumns(new Object[2][2], 0, 3); // 删除超过可用列数
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Array2DTool.removeRows(new Object[2][2], 0, 3); // 删除超过可用行数
        });
    }
}