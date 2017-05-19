package com.engine.Graphics.Uniforms;

import com.engine.Graphics.Shader.ShaderProgram;
import com.engine.Graphics.Vertex.VertexArrayObject;
import com.engine.Graphics.Vertex.VertexBufferObject;
import com.engine.Graphics.Vertex.VertexIndicesObject;
import com.engine.Math.Matrix.Matrix4f;
import com.engine.Primitive.Types.Rectangle;
import lombok.Data;
import org.joml.Matrix4d;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

@Data
public class Model {
    Matrix4d projectionMatrix;
    Matrix4d modelMatrix;
    Matrix4d viewMatrix;

    Rectangle rectangle;

    VertexArrayObject vao;

    VertexIndicesObject vio;

    public int getIndiceCount() {
        return vio.getIndiceCount();
    }

    public void bind() {
        vao.bind(); //bind vertices
        vio.bind(); //bind indices
    }

    public void setupRectVertices() {
        vao = new VertexArrayObject();
        vao.bind();

        rectangle = new Rectangle();
        FloatBuffer vertices = rectangle.getVertices();

        VertexBufferObject vbo = new VertexBufferObject();
        vbo.bind(GL_ARRAY_BUFFER);
        vbo.uploadData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        vio = new VertexIndicesObject();
        vio.bind();
        vio.uploadData(rectangle.getIndices());
    }

    public void specifyVertexAttributes(ShaderProgram program) {
        /* Specify Vertex Pointer */
        int posAttrib = program.getAttributeLocation("position");
        program.enableVertexAttribute(posAttrib);
        program.pointVertexAttribute(posAttrib, 3, 8 * Float.BYTES, 0);

        /* Specify Color Pointer */
        int colAttrib = program.getAttributeLocation("color");
        program.enableVertexAttribute(colAttrib);
        program.pointVertexAttribute(colAttrib, 3, 8 * Float.BYTES, 3 * Float.BYTES);

        /* Specify Vertex Pointer */
        int texAttrib = program.getAttributeLocation("tex");
        program.enableVertexAttribute(texAttrib);
        program.pointVertexAttribute(texAttrib, 2, 8 * Float.BYTES, 6 * Float.BYTES);
    }
}
