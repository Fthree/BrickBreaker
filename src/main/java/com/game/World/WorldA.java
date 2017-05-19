package com.game.World;

import com.game.Util.GameUtils;
import org.jbox2d.common.Vec2;

public class WorldA implements IWorld {
    public void create() {

        GameUtils.createPaddle(new Vec2(0.0f, -10.0f), new Vec2(3f, 0.35f), 0.5f); //Initial position, initial size, power

        GameUtils.createBall();

        GameUtils.createBricks(20, new Vec2(1.0f, 0.5f), new Vec2(-14f, 10f)); //num, size, initial position

        GameUtils.createWalls();
    }
}
