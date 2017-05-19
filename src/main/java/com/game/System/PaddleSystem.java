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
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

import java.util.concurrent.ThreadLocalRandom;

public class PaddleSystem implements ISystem {

    private static final String name = "paddle";
    private static final String ballName = "ball";

    public boolean match(IEntity entity) {
        return entity.hasComponent(ComponentType.PHYSICS) && entity.hasComponent(ComponentType.PADDLE);
    }

    public boolean initialize() {
        IEntity paddle = EntityUtils.getEntityByName(name);
        if(paddle.getComponents().size() == 0) { return false; }
        PhysicsUtils.setupSquareBody(paddle);

        TextureComponent textureComponent = EntityUtils.getComponent(paddle, ComponentType.TEXTURE);
        Texture texture = new Texture();
        textureComponent.setTexId(texture.setupTextures(textureComponent.getTextureName()));

        PhysicsComponent physicsComponent = EntityUtils.getComponent(paddle, ComponentType.PHYSICS);
        physicsComponent.getBody().setUserData(name);

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