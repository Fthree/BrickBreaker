package com.engine.Math.Util;

import org.joml.Matrix4d;

public class MathUtil {
    public static Matrix4d lookAt(float fov, float zFar, float zNear, float width, float height) {
        Matrix4d projectionMatrix = new Matrix4d();

        float aspectRatio = width / height;

        float y_scale = coTangent(degreesToRadians(fov / 2f));
        float x_scale = y_scale / aspectRatio;
        float frustrum_length = zFar - zNear;

        projectionMatrix.m00(x_scale);
        projectionMatrix.m11(y_scale);
        projectionMatrix.m22(-((zFar + zNear) / frustrum_length));
        projectionMatrix.m23(-1);
        projectionMatrix.m30(-((2 * zNear * zFar) / frustrum_length));
        projectionMatrix.m33(0.0f);

        return projectionMatrix;
    }

    private static float coTangent(float angle) {
        return (float)(1f / Math.tan(angle));
    }

    public static float degreesToRadians(float degrees) {
        return degrees * (float)(Math.PI / 180d);
    }
}
