package io.github.jiangood.openadmin.common.tools.range;


import io.github.jiangood.openadmin.common.Point;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PointRange extends Range<Point> {
    public PointRange(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }
}
