package com.engine.Graphics;

import com.engine.Graphics.Shader.Shader;
import com.engine.Graphics.Shader.ShaderProgram;
import com.engine.Graphics.Uniforms.Model;
import com.engine.Math.Matrix.Matrix4f;
import com.engine.Store.GamePropertiesStore;
import com.engine.Util.RenderUtils;
import com.engine.Math.Vector.Vector3f;
import com.engine.Math.Vector.Vector4f;
import lombok.Data;
import org.lwjgl.opengl.GL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

@Data
public class Renderer {

    private Shader vertexShader;
    private Shader fragmentShader;
    private ShaderProgram program;

    private Model model;

    public void enter(File vertex, File fragment) throws FileNotFoundException {
        GL.createCapabilities();
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f); //White

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glEnable(GL_ALPHA_TEST);

        /* Generate model vertices with bound vao and vbo */
        model = new Model();
        model.setupRectVertices();

        /* Load shaders */
        CharSequence vertexSource = new Scanner(vertex).useDelimiter("\\Z").next();
        CharSequence fragmentSource = new Scanner(fragment).useDelimiter("\\Z").next();
        vertexShader = new Shader(GL_VERTEX_SHADER, vertexSource);
        fragmentShader = new Shader(GL_FRAGMENT_SHADER, fragmentSource);

        /* Create shader program */
        program = new ShaderProgram();
        program.attachShader(vertexShader);
        program.attachShader(fragmentShader);
        program.link();
        program.use();

        model.specifyVertexAttributes(program);
        setupMatrices(program);
    }

    public void setupMatrices(ShaderProgram program) {
        program.setUniform("view", new Matrix4f());
        program.setUniform("model", new Matrix4f());

        float ratio = RenderUtils.getWindowRatio();
        float screenScale = GamePropertiesStore.getProperties().getScreenScale();
        program.setUniform("projection", RenderUtils.getScreenProjectionOrtho(ratio, screenScale)); //This is an orthographic
    }

    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT);
    }

    public void render(Vector3f position, Vector4f rotation, Vector3f size, int texId) {
        model.bind();
        program.use();

        Matrix4f modelMat = Matrix4f
                .translate(position.x, position.y, position.z)
                .multiply(Matrix4f
                        .rotate(rotation.w, rotation.x, rotation.y, rotation.z)
                        .multiply(Matrix4f
                                .scale(size.x, size.y, size.z)));

        program.setUniform("model", modelMat);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texId);

        glDrawElements(GL_TRIANGLES, model.getIndiceCount(), GL_UNSIGNED_BYTE, 0);
    }

    public void exit() {
        vertexShader.delete();
        fragmentShader.delete();
        program.delete();
    }
}
