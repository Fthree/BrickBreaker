package com.engine.Graphics.Vertex;

import lombok.Data;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;

@Data
public class VertexIndicesObject {
    int vboIndicesId;
    int indiceCount;

    public VertexIndicesObject() {
        vboIndicesId = glGenBuffers();
    }

    public void uploadData(ByteBuffer indices) {
        indiceCount = indices.capacity();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
    }

    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboIndicesId);
    }
}
