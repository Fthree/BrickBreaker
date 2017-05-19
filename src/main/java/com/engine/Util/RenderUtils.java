package com.engine.Util;

import com.engine.Math.Matrix.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.IntBuffer;

public class RenderUtils {
    public static float getWindowRatio() {
        /* Get width and height for calculating the ratio */
        long window = GLFW.glfwGetCurrentContext();
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetFramebufferSize(window, width, height);
        return (width.get()) / ((float)height.get());
    }

    public static Matrix4f getScreenProjectionOrtho(float ratio, float screenScale) {
        return Matrix4f.orthographic(-ratio * screenScale, ratio * screenScale, -1 * screenScale, screenScale, -1f, 1f);
    }
}
