package com.engine.System.Implementations;

import com.engine.Component.Type.ComponentType;
import com.engine.Entity.Interface.IEntity;
import com.engine.Store.GamePropertiesStore;
import com.engine.System.Interface.ISystem;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class PhysicsSystem implements ISystem {

    public boolean match(IEntity entity) {
        return entity.hasComponent(ComponentType.TRANSFORM) && entity.hasComponent(ComponentType.PHYSICS);
    }

    public boolean initialize() {
        Vec2 gravity = new Vec2(0f, 0f);
        World world = new World(gravity);
        GamePropertiesStore.getProperties().setPhysicsWorld(world);

        return true;
    }

    public void preProcess() {
    }

    public void process(IEntity entity) {
        World world = GamePropertiesStore.getProperties().getPhysicsWorld();
        world.step(1.0f/60.0f, 6, 2);
    }

}
