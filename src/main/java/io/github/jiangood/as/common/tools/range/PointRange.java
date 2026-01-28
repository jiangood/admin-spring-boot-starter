package io.github.jiangood.as.common.tools.range;


import io.github.jiangood.as.common.Point;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PointRange extends Range<Point> {
    public PointRange(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }
}
