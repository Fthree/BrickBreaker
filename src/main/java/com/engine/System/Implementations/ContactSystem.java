package com.engine.System.Implementations;

import com.engine.Entity.Interface.IEntity;
import com.engine.Store.GamePropertiesStore;
import com.engine.Util.EntityUtils;
import com.engine.Util.PhysicsUtils;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

public class ContactSystem implements ContactListener {

    private static final String ball = "ball";
    private static final String paddle = "paddle";
    private static final String brick = "brick";

    public void beginContact (Contact contact) {
        GamePropertiesStore gamePropertiesStore = GamePropertiesStore.getProperties();

        contact.getFixtureA().getBody();

        //Fixture B is the ball
        //This will shoot the ball into the opposite direction
        if(PhysicsUtils.matchBodyName(ball, contact.getFixtureB().getBody())) {
            Body body = contact.getFixtureB().getBody();
            IEntity ball = EntityUtils.getEntityByName((String) body.getUserData());
            EntityUtils.setEntityCollidedValue(ball, true);
            EntityUtils.setEntityLocalNormal(ball, contact.getManifold().localNormal);
        }


        //Fixture A is the brick
        if(PhysicsUtils.matchBodyName(brick, contact.getFixtureA().getBody())) {
            Body body = contact.getFixtureA().getBody();
            IEntity brick = EntityUtils.getEntityByName((String)body.getUserData());
            EntityUtils.setEntityCollidedValue(brick, true);
        }

        //Fixture A is the paddle
        if(PhysicsUtils.matchBodyName(paddle, contact.getFixtureA().getBody())) {
            Body body = contact.getFixtureA().getBody();
            IEntity paddle = EntityUtils.getEntityByName((String)body.getUserData());
            EntityUtils.setEntityCollidedValue(paddle, true);
        }
    }

    public void endContact (Contact contact) {

    }

    public void preSolve (Contact contact, Manifold oldManifold){

    }

    public void postSolve (Contact contact, ContactImpulse impulse){

    }
}
