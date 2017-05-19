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
import org.jbox2d.dynamics.Body;

import java.util.List;

public class BrickSystem implements ISystem {

    private static final String name = "brick";
    private static final String ballName = "ball";

    public boolean match(IEntity entity) {
        return entity.hasComponent(ComponentType.PHYSICS) && entity.hasComponent(ComponentType.BRICK);
    }

    public boolean initialize() {
        List<IEntity> bricks = EntityUtils.getAllEntitiesByName(name);
        if(bricks.size() == 0) { return false; }

        bricks.stream().forEach(brick -> {
            PhysicsUtils.setupSquareBody(brick);

            TextureComponent textureComponent = EntityUtils.getComponent(brick, ComponentType.TEXTURE);
            Texture texture = new Texture();
            textureComponent.setTexId(texture.setupTextures(textureComponent.getTextureName()));


            PhysicsComponent physicsComponent = EntityUtils.getComponent(brick, ComponentType.PHYSICS);
            physicsComponent.getBody().setUserData(EntityUtils.getEntityName(brick));
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

            Body body = EntityUtils.getEntityBody(entity);
            body.setActive(false);

            EntityUtils.removeEntityFromGlobalStore(entity);
            EntityUtils.addToRemovedEntityGlobalStore(entity);

            EntityUtils.setEntityCollidedValue(entity, false);
        }
    }
}