package com.game.System;

import com.engine.Component.Implementations.BallComponent;
import com.engine.Component.Implementations.PhysicsComponent;
import com.engine.Component.Implementations.TextureComponent;
import com.engine.Component.Type.ComponentType;
import com.engine.Entity.Interface.IEntity;
import com.engine.Graphics.Texture.Texture;
import com.engine.Store.GamePropertiesStore;
import com.engine.Util.BallUtils;
import com.engine.System.Interface.ISystem;
import com.engine.Util.EntityUtils;
import com.game.Util.GameUtils;
import com.engine.Util.PhysicsUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.glfwGetKey;


public class BallSystem implements ISystem {

    private static final String name = "ball";
    private static final String paddleName = "paddle";

    GamePropertiesStore gamePropertiesStore;

    public boolean match(IEntity entity) {
        return entity.hasComponent(ComponentType.PHYSICS) && entity.hasComponent(ComponentType.BALL);
    }

    public boolean initialize() {
        IEntity ball = EntityUtils.getEntityByName(name);
        if(ball.getComponents().size() == 0) { return false; }
        PhysicsUtils.setupCircleBody(ball);

        TextureComponent textureComponent = EntityUtils.getComponent(ball, ComponentType.TEXTURE);
        Texture texture = new Texture();
        textureComponent.setTexId(texture.setupTextures(textureComponent.getTextureName()));

        PhysicsComponent physicsComponent = EntityUtils.getComponent(ball, ComponentType.PHYSICS);
        physicsComponent.getBody().setUserData(name);

        gamePropertiesStore = GamePropertiesStore.getProperties();

        return true;
    }

    public void preProcess() {}

    public void process(IEntity entity) {
        handlePreLaunch(entity);
        handleCollision(entity);
    }

    public void handlePreLaunch(IEntity entity) {
        BallComponent ballComponent = EntityUtils.getComponent(entity, ComponentType.BALL);
        PhysicsComponent physicsComponent = EntityUtils.getComponent(entity, ComponentType.PHYSICS);

        if(!ballComponent.isActivateBall()) {
            if(glfwGetKey(gamePropertiesStore.getGlfwWindow(), GLFW_KEY_SPACE) == 1) {
                ballComponent.setActivateBall(true);
                PhysicsUtils.applyDirectionalImpulseOnBody(physicsComponent.getBody(), physicsComponent.getUpSpeed()); //Shoot straight up
            } else {
                IEntity paddle = EntityUtils.getEntityByName(paddleName);
                GameUtils.attachBallToPaddle(entity, paddle);
            }
        }
    }

    public void handleCollision(IEntity entity) {
        if(EntityUtils.isEntityColliding(entity)) {

            Body body = EntityUtils.getEntityBody(entity);
            Vec2 localNormal = EntityUtils.getEntityLocalNormal(entity);
            Vec2 collisionVelocity = EntityUtils.getEntityVelocityBeforeCollision(entity);

            Vec2 oppositeDir = localNormal.add(collisionVelocity); //Make ball bounce

            BallUtils.setRandomDirectionOnOppositeDir(localNormal, oppositeDir);
            BallUtils.manageSpeed(body, oppositeDir);
            PhysicsUtils.applyDirectionalImpulseOnBody(body, oppositeDir);
            EntityUtils.setEntityCollidedValue(entity, false);
        }
    }
}

