package com.engine.Store;

import lombok.Data;
import org.jbox2d.dynamics.World;

@Data
public class GamePropertiesStore {
    private static GamePropertiesStore gamePropertiesStore = new GamePropertiesStore();

    private GamePropertiesStore() {
    }

    public static GamePropertiesStore getProperties() {
        return gamePropertiesStore;
    }

    int width = 640;
    int height = 480;
    float screenScale = 15f;
    long glfwWindow;
    World physicsWorld;
}
