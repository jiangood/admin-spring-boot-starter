package io.github.jiangood.as.common;

import io.github.jiangood.as.common.tools.Array2DTool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 坐标类
 * 用于表示和处理二维坐标系统中的位置信息
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Array2DCoords {

    private int row;
    private int col;

}
