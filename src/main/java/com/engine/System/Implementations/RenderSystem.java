package com.engine.System.Implementations;


import com.engine.Component.Implementations.TextureComponent;
import com.engine.Component.Implementations.TransformComponent;
import com.engine.Component.Type.ComponentType;
import com.engine.Entity.Interface.IEntity;
import com.engine.Graphics.Renderer;
import com.engine.System.Interface.ISystem;
import com.engine.Util.EntityUtils;
import com.engine.Util.PhysicsUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class RenderSystem implements ISystem {

    Renderer renderer;

    public boolean match(IEntity entity) {
        return entity.hasComponent(ComponentType.TRANSFORM) && entity.hasComponent(ComponentType.TEXTURE);
    }

    public boolean initialize() {
        renderer = new Renderer();
        ClassLoader classLoader = getClass().getClassLoader();

        try {
            renderer.enter(new File(classLoader.getResource("vertex").getFile()), new File(classLoader.getResource("fragment").getFile()));
        } catch (FileNotFoundException fnf) {
            java.lang.System.out.println(fnf.toString());
        }

        return true;
    }

    public void preProcess() {
        renderer.clearScreen();
    }

    public void process(IEntity entity) {
        TransformComponent tfc = PhysicsUtils.updateEntityTransform(entity);
        TextureComponent textureComponent = EntityUtils.getComponent(entity, ComponentType.TEXTURE);
        renderer.render(tfc.getPosition(), tfc.getRotation(), tfc.getSize(), textureComponent.getTexId());
    }
}
