package com.game.System;

import com.engine.Component.Implementations.PhysicsComponent;
import com.engine.Component.Implementations.TextureComponent;
import com.engine.Component.Type.ComponentType;
import com.engine.Entity.Implementations.GameEntity;
import com.engine.Entity.Interface.IEntity;
import com.engine.Graphics.Texture.Texture;
import com.engine.System.Interface.ISystem;
import com.engine.Util.EntityUtils;
import com.engine.Util.PhysicsUtils;

import java.util.ArrayList;
import java.util.List;

public class WallSystem implements ISystem {

    private static final String name = "wall";
    private static final String ballName = "ball";

    public boolean match(IEntity entity) {
        return entity.hasComponent(ComponentType.PHYSICS) && entity.hasComponent(ComponentType.WALL);
    }

    public boolean initialize() {
        List<IEntity> walls = EntityUtils.getAllEntitiesByName(name);
        if(walls.size() == 0) { return false; }
        walls.stream().forEach(wall -> {
            PhysicsUtils.setupSquareBody(wall);

            TextureComponent textureComponent = EntityUtils.getComponent(wall, ComponentType.TEXTURE);
            Texture texture = new Texture();
            textureComponent.setTexId(texture.setupTextures(textureComponent.getTextureName()));

            PhysicsComponent physicsComponent = EntityUtils.getComponent(wall, ComponentType.PHYSICS);
            physicsComponent.getBody().setUserData(name);
        });

        return true;
    }

    public void preProcess() {

    }

    public void process(IEntity entity) {
        handleCollision(entity);
    }

    public void handleCollision(IEntity entity) {
        if(EntityUtils.isEntityColliding(entity)) {
            EntityUtils.setEntityCollidedValue(entity, false);
        }
    }
}