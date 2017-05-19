package com.engine.Util;

import com.engine.Component.Implementations.PhysicsComponent;
import com.engine.Component.Implementations.TransformComponent;
import com.engine.Component.Type.ComponentType;
import com.engine.Entity.Interface.IEntity;
import com.engine.Math.Vector.Vector3f;
import com.engine.Math.Vector.Vector4f;
import com.engine.Store.GamePropertiesStore;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PhysicsUtils {
    public static boolean matchBodyName(String name, Body body) {
        return ((String)body.getUserData()).contains(name);
    }

    public static void applyDirectionalImpulseOnBody(Body body, Vec2 directionalPower) {
        body.applyLinearImpulse(directionalPower.mul(body.getInertia()), body.getPosition());
    }

    public static void applyDirectionalForceOnBody(Body body, Vec2 directionalForce) {
        body.applyForceToCenter(directionalForce.mul(body.getInertia()));
    }

    public static TransformComponent updateEntityTransform(IEntity entity) {
        PhysicsComponent physicsComponent = EntityUtils.getComponent(entity, ComponentType.PHYSICS);

        Body body = physicsComponent.getBody();
        if(body == null) {
            return TransformComponent.builder()
                    .position(new Vector3f())
                    .rotation(new Vector4f())
                    .size(new Vector3f())
                    .build();
        } else {
            return TransformComponent.builder()
                    .position(new Vector3f(body.getPosition().x, body.getPosition().y, 0f))
                    .rotation(new Vector4f(0f, 0f, 1f, (float)radiansToDegrees(body.getAngle())))
                    .size(new Vector3f(physicsComponent.getInitialSize().x * 2, physicsComponent.getInitialSize().y * 2, 0f))
                    .build();
        }
    }

    public static double degreesToRadians(double degrees) {
        return degrees*Math.PI/180;
    }

    public static double radiansToDegrees(double radians) {
        return radians*180/Math.PI;
    }

    public static void setupSquareBody(IEntity entity) {
        PhysicsComponent phyc = EntityUtils.getComponent(entity, ComponentType.PHYSICS);

        ((PolygonShape)phyc.getShape()).setAsBox(phyc.getInitialSize().x, phyc.getInitialSize().y);

        BodyDef bodyDef = setupBodyDef(phyc);
        setupBody(bodyDef, phyc);
    }

    public static void setupCircleBody(IEntity entity) {
        PhysicsComponent phyc = EntityUtils.getComponent(entity, ComponentType.PHYSICS);

        ((CircleShape)phyc.getShape()).m_p.set(0f, 0f);
        ((CircleShape)phyc.getShape()).m_radius = phyc.getInitialSize().x;

        BodyDef bodyDef = setupBodyDef(phyc);
        setupBody(bodyDef, phyc);

        phyc.getBody().setAngularDamping(1f);
    }

    private static BodyDef setupBodyDef(PhysicsComponent phyc) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = phyc.getBodyType();
        bodyDef.position.set(phyc.getInitialPosition());
        bodyDef.angle = (float) PhysicsUtils.degreesToRadians(phyc.getInitialRotation());
        bodyDef.angularDamping = 100f;

        return bodyDef;
    }

    private static FixtureDef setupFixtureDef(PhysicsComponent phyc) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = phyc.getShape();
        fixtureDef.density = phyc.getDensity();
        fixtureDef.restitution = phyc.getRestitution();
        fixtureDef.friction = 0.0f;

        return fixtureDef;
    }

    private static void setupBody(BodyDef bodyDef, PhysicsComponent phyc) {
        GamePropertiesStore gamePropertiesStore = GamePropertiesStore.getProperties();
        World world = gamePropertiesStore.getPhysicsWorld();
        phyc.setBody(world.createBody(bodyDef));

        FixtureDef fixtureDef = setupFixtureDef(phyc);
        phyc.getBody().createFixture(fixtureDef);

        phyc.getBody().setLinearDamping(phyc.getFriction());
    }
}
