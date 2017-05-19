package com.engine.Primitive.Types;

import lombok.Data;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

@Data
public class Rectangle {
    //float[] vertices;

    ByteBuffer indices;
    FloatBuffer vertices;

    public Rectangle() {
        vertices = BufferUtils.createFloatBuffer(4 * 8);
        vertices.put(-0.5f).put(-0.5f).put(0f) .put(1f).put(0f).put(0f) .put(0).put(1);
        vertices.put(0.5f).put(-0.5f).put(0f)  .put(0f).put(1f).put(0f) .put(1).put(1);
        vertices.put(-0.5f).put(0.5f).put(0f)  .put(0f).put(0f).put(1f) .put(0).put(0);
        vertices.put(0.5f).put(0.5f).put(0f)   .put(0f).put(0f).put(1f) .put(1).put(0);
        vertices.flip();

        indices = BufferUtils.createByteBuffer(2 * 3);
        indices.put((byte)0).put((byte)1).put((byte)2);
        indices.put((byte)1).put((byte)2).put((byte)3);
        indices.flip();
    }
}
