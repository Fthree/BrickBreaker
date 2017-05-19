package com.engine.Window;

import org.jbox2d.common.Vec2;
import com.engine.Store.GamePropertiesStore;

public class WindowSystem {
    GamePropertiesStore gamePropertiesStore = GamePropertiesStore.getProperties();

    Window primary;

    WindowUtil windowUtil;

    public void initializeWindows() {
        Window parent = new Window();
        parent.setDimensions(new Vec2(gamePropertiesStore.getWidth(), gamePropertiesStore.getHeight()));
        primary = parent;

        windowUtil = new WindowUtil();
    }

    public void subdividePrimary(Vec2 subdivision, WindowUtil.Direction dir) {
        windowUtil.subdivide(primary, subdivision, dir);
    }

    public void subdivideSpecificWindow(Window parent, Vec2 subdivision, WindowUtil.Direction dir) {
        windowUtil.subdivide(parent, subdivision, dir);
    }

    public Window getPrimary() { return primary; }
}
