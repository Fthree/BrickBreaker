package com.engine.Window;

import org.jbox2d.common.Vec2;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Window {
    Window parent;
    Pair<Window, Window> children;
    Vec2 subdivision;
    Vec2 dimensions;
}
