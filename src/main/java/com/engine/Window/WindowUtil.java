package com.engine.Window;

import org.jbox2d.common.Vec2;
import javafx.util.Pair;

public class WindowUtil {
    public enum Direction {
        HORRIZONTAL,
        VERTICAL
    }

    public Pair<Window, Window> subdivide(Window parent, Vec2 subdivision, Direction dir) {
        parent.setSubdivision(subdivision);

        Window A = new Window();
        Window B = new Window();

        if(dir == Direction.HORRIZONTAL) {
            A.setDimensions(new Vec2(parent.getDimensions().x, subdivision.y));
            B.setDimensions(new Vec2(parent.getDimensions().x, parent.getDimensions().y - subdivision.y));
        } else if (dir == Direction.VERTICAL) {
            A.setDimensions(new Vec2(subdivision.x, parent.getDimensions().y));
            B.setDimensions(new Vec2(parent.getDimensions().x - subdivision.x, parent.getDimensions().y));
        }

        parent.setChildren(new Pair<>(A, B));

        return parent.getChildren();
    }
}
