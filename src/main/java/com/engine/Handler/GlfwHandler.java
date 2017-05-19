package com.engine.Handler;

import com.engine.Store.GamePropertiesStore;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GlfwHandler {
    private long window;

    public void initialize() {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GamePropertiesStore gps = GamePropertiesStore.getProperties();

        int width = gps.getWidth();
        int height = gps.getHeight();

        setupWindow(width, height);

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(
                window,
                (vidMode.width() - height) / 2,
                (vidMode.height() - width) / 2
        );

        glfwMakeContextCurrent(window);

        glfwSwapInterval(1);

        glfwShowWindow(window);
    }

    public void setupWindow(int width, int height) {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        GamePropertiesStore gps = GamePropertiesStore.getProperties();

        window = glfwCreateWindow(width, height, "Ruined", NULL, NULL);

        gps.setGlfwWindow(window);

        if(window == NULL) {
            throw new RuntimeException("Failed to create GLFW window");
        }
    }

    public void handleFinally() {
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void cleanup() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
    }

    public long getWindow() {
        return window;
    }
}
