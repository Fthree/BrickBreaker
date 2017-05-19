package com.engine;

import com.engine.Handler.GlfwHandler;
import com.engine.Handler.SystemHandler;

import static org.lwjgl.glfw.GLFW.*;

public class Engine {
    GlfwHandler glfwHandler;
    SystemHandler systemHandler;

    public boolean isRunning() {
        return !glfwWindowShouldClose(glfwHandler.getWindow());
    }

    public void init() {
        System.out.println("Initializing engine");

        System.setProperty("org.lwjgl.librarypath", "sources\\lwjgl\\native");
        System.setProperty("org.lwjgl.util.Debug", "true");

        glfwHandler = new GlfwHandler();
        systemHandler = new SystemHandler();

        glfwHandler.initialize();
        systemHandler.InitializeEntitiesAndSystems();
        System.out.println("Finished initializing engine");
    }

    public void preProcess() {
        systemHandler.preProcess();
    }

    public void update() {
        systemHandler.update();
        glfwPollEvents();
    }

    public void render() {
        systemHandler.render();
        glfwSwapBuffers(glfwHandler.getWindow());
    }
}
