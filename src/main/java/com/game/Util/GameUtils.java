package com.game.Util;

import com.engine.Component.Implementations.*;
import com.engine.Component.Interface.IComponent;
import com.engine.Component.Type.ComponentType;
import com.engine.Entity.Interface.IEntity;
import com.engine.EntityModel.EntityModel;
import com.engine.Util.EngineUtils;
import com.engine.Util.EntityUtils;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class GameUtils {
    public static void attachBallToPaddle(IEntity ball, IEntity paddle) {
        PhysicsComponent ballPhyc = EntityUtils.getComponent(ball, ComponentType.PHYSICS);
        Body ballBody = ballPhyc.getBody();
        PhysicsComponent paddlePhyc = EntityUtils.getComponent(paddle, ComponentType.PHYSICS);
        Body paddleBody = paddlePhyc.getBody();

        ballBody.setTransform(new Vec2(paddleBody.getPosition().x, 1f + paddleBody.getPosition().y + (ballPhyc.getInitialSize().y + paddlePhyc.getInitialSize().y)), ballBody.getAngle());
    }

    public static void createBricks(int numBricks, Vec2 size, Vec2 initialPosition) {
        ArrayList<IComponent> components;

        int realIndex = 0;
        for(int i = 0; i != numBricks / 2; i++) {
            for(int j = 0; j != Math.floor(numBricks / 1.5); j++) {

                final Vec2 newPositionOffset = new Vec2(i, j);
                final String name = "brick" + realIndex;

                components = new ArrayList<IComponent>() {{
                    add(TransformComponent.builder()
                            .componentType(ComponentType.TRANSFORM)
                            .build());
                    add(PhysicsComponent.builder()
                            .componentType(ComponentType.PHYSICS)
                            .bodyType(BodyType.STATIC)
                            .initialPosition(new Vec2(initialPosition.x + ((size.x * 2) * newPositionOffset.y), initialPosition.y - ((size.y * 2) * newPositionOffset.x)))
                            .initialSize(new Vec2(size.x, size.y))
                            .initialRotation(0f)
                            .friction(3f)
                            .restitution(10f)
                            .density(1f)
                            .shape(new PolygonShape())
                            .build());
                    add(RigidbodyComponent.builder()
                            .componentType(ComponentType.RIGIDBODY)
                            .isColliding(false)
                            .build());
                    add(IdComponent.builder()
                            .componentType(ComponentType.ID)
                            .entityModel(EntityModel.BRICK)
                            .name(name)
                            .build());
                    add(TextureComponent.builder()
                            .componentType(ComponentType.TEXTURE)
                            .textureName("brick.png")
                            .build());
                    add(BrickComponent.builder()
                            .componentType(ComponentType.BRICK)
                            .build());
                }};


                EngineUtils.createNextEntity(components);

                realIndex++;
            }
        }
    }

    public static void createPaddle(Vec2 initialPosition, Vec2 initialSize, float movementPower) {
        EngineUtils.createNextEntity(new ArrayList<IComponent>() {{
            add(TransformComponent.builder()
                    .componentType(ComponentType.TRANSFORM)
                    .build());
            add(PhysicsComponent.builder()
                    .componentType(ComponentType.PHYSICS)
                    .bodyType(BodyType.DYNAMIC)
                    .initialPosition(initialPosition)
                    .initialSize(initialSize)
                    .initialRotation(0f)
                    .friction(3f)
                    .restitution(0f)
                    .density(10000f)
                    .shape(new PolygonShape())
                    .build());
            add(RigidbodyComponent.builder()
                    .componentType(ComponentType.RIGIDBODY)
                    .isColliding(false)
                    .build());
            add(IdComponent.builder()
                    .componentType(ComponentType.ID)
                    .entityModel(EntityModel.PLAYER)
                    .name("paddle")
                    .build());
            add(TextureComponent.builder()
                    .componentType(ComponentType.TEXTURE)
                    .textureName("paddle.png")
                    .build());
            add(InputComponent.builder()
                    .componentType(ComponentType.INPUT)
                    .left(GLFW_KEY_A)
                    .right(GLFW_KEY_D)
                    .leftPower(movementPower)
                    .rightPower(movementPower)
                    .build());
            add(PaddleComponent.builder()
                    .componentType(ComponentType.PADDLE)
                    .build());
        }});
    }

    public static void createBall() {
        EngineUtils.createNextEntity(new ArrayList<IComponent>() {{
            add(TransformComponent.builder()
                    .componentType(ComponentType.TRANSFORM)
                    .build());
            add(PhysicsComponent.builder()
                    .componentType(ComponentType.PHYSICS)
                    .bodyType(BodyType.DYNAMIC)
                    .initialPosition(new Vec2(-0.5f , -0.5f))
                    .initialSize(new Vec2(0.5f, 0.5f))
                    .initialRotation(0f)
                    .friction(0f)
                    .restitution(0f)
                    .upSpeed(new Vec2(0f, 1.25f))
                    .density(1f)
                    .shape(new CircleShape())
                    .build());
            add(RigidbodyComponent.builder()
                    .componentType(ComponentType.RIGIDBODY)
                    .isColliding(false)
                    .velocity(new Vec2(0.0f, 0.0f))
                    .build());
            add(IdComponent.builder()
                    .componentType(ComponentType.ID)
                    .entityModel(EntityModel.BALL)
                    .name("ball")
                    .build());
            add(TextureComponent.builder()
                    .componentType(ComponentType.TEXTURE)
                    .textureName("ball.png")
                    .build());
            add(BallComponent.builder()
                    .componentType(ComponentType.BALL)
                    .activateBall(false)
                    .build());
        }});
    }

    public static void createWalls() {
        createWall(WallPositions.LEFT);
        createWall(WallPositions.TOP);
        createWall(WallPositions.RIGHT);
        createWall(WallPositions.BOTTOM);
    }

    public enum WallPositions {
        TOP, RIGHT, LEFT, BOTTOM
    }

    public static void createWall(WallPositions positions) {
        Vec2 initialPosition = new Vec2();
        Vec2 initialSize = new Vec2();
        switch (positions) {
            case TOP:
                initialPosition = new Vec2(-10f, 15f); //s
                initialSize = new Vec2(35f, 0.5f); //s
                break;
            case RIGHT:
                initialPosition = new Vec2(20f, -15f);
                initialSize = new Vec2(0.5f, 30f);
                break;
            case LEFT:
                initialPosition = new Vec2(-20f, -15f);//s
                initialSize = new Vec2(0.5f, 30f);//s
                break;
            case BOTTOM:
                initialPosition = new Vec2(-10f, -15f);//us
                initialSize = new Vec2(30f, 0.5f);//us
                break;
        }

        final Vec2 setPosition = initialPosition;
        final Vec2 setSize = initialSize;

        EngineUtils.createNextEntity(new ArrayList<IComponent>() {{
            add(TransformComponent.builder()
                    .componentType(ComponentType.TRANSFORM)
                    .build());
            add(PhysicsComponent.builder()
                    .componentType(ComponentType.PHYSICS)
                    .bodyType(BodyType.STATIC)
                    .initialPosition(setPosition)
                    .initialSize(setSize)
                    .initialRotation(0f)
                    .friction(0f)
                    .restitution(0f)
                    .density(10000f)
                    .shape(new PolygonShape())
                    .build());
            add(IdComponent.builder()
                    .componentType(ComponentType.ID)
                    .entityModel(EntityModel.WALL)
                    .name("wall")
                    .build());
            add(TextureComponent.builder()
                    .componentType(ComponentType.TEXTURE)
                    .textureName("wall.png")
                    .build());
            add(RigidbodyComponent.builder()
                    .componentType(ComponentType.RIGIDBODY)
                    .isColliding(false)
                    .build());
            add(WallComponent.builder()
                    .componentType(ComponentType.WALL)
                    .build());
        }});
    }
}
