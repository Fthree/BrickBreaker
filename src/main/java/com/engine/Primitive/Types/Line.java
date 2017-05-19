package com.engine.Primitive.Types;

import lombok.Data;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

@Data
public class Line {
    FloatBuffer vertices;

    public Line() {
        vertices = BufferUtils.createFloatBuffer(2 * 2);
        vertices.put(-1f).put(0f);
        vertices.put(1f).put(0f);
        vertices.flip();
    }
}
