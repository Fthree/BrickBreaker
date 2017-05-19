package com.engine.Util;

import com.engine.Entity.Factory.EntityFactory;
import com.engine.Entity.Interface.IEntity;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.lwjgl.system.CallbackI;

import java.util.concurrent.ThreadLocalRandom;

public class BallUtils {
    public static void setRandomDirectionOnOppositeDir(Vec2 localNormal, Vec2 oppositeDir) {
        if(localNormal.x == 0) {
            oppositeDir.x = oppositeDir.x + (float) ThreadLocalRandom.current().nextDouble(-0.5f, 0.5f);
        } else if (localNormal.x > 0) {
            oppositeDir.y = oppositeDir.y + (float)ThreadLocalRandom.current().nextDouble(-0.5f, 0f);
        } else if (localNormal.x < 0) {
            oppositeDir.y = oppositeDir.y + (float)ThreadLocalRandom.current().nextDouble(0f, 0.5f);
        } if(localNormal.y > 0) {
            oppositeDir.x = localNormal.x; //Don't make any changes to direction when bouncing up
        }
    }

    public static void manageSpeed(Body body, Vec2 oppositeDir) {
        if((body.getLinearVelocity().x < 0.1f && body.getLinearVelocity().y < 0.1f) && (body.getLinearVelocity().x > -0.1f && body.getLinearVelocity().y > -0.1f)) {
            oppositeDir.mul(2f);
        }
    }
}
