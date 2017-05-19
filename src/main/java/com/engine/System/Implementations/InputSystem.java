package com.engine.System.Implementations;

import com.engine.Component.Implementations.InputComponent;
import com.engine.Component.Implementations.PhysicsComponent;
import com.engine.Component.Type.ComponentType;
import com.engine.Entity.Interface.IEntity;
import com.engine.Util.PhysicsUtils;
import org.jbox2d.common.Vec2;
import com.engine.Store.GamePropertiesStore;
import com.engine.System.Interface.ISystem;
import com.engine.Util.EntityUtils;

import static org.lwjgl.glfw.GLFW.glfwGetKey;

public class InputSystem implements ISystem {

    GamePropertiesStore gamePropertiesStore;

    public boolean match(IEntity entity) {
        return entity.hasComponent(ComponentType.PHYSICS) && entity.hasComponent(ComponentType.INPUT);
    }

    public boolean initialize() {

        gamePropertiesStore = GamePropertiesStore.getProperties();

        return true;
    }

    public void preProcess() {

    }

    public void process(IEntity entity) {
        PhysicsComponent phyc = EntityUtils.getComponent(entity, ComponentType.PHYSICS);
        InputComponent ic = EntityUtils.getComponent(entity, ComponentType.INPUT);

        if(ic.getUp() != 0 && glfwGetKey(gamePropertiesStore.getGlfwWindow(), ic.getUp()) == 1) {
            PhysicsUtils.applyDirectionalImpulseOnBody(phyc.getBody(), new Vec2(0, ic.getUpPower()));
        }
        if(ic.getDown() != 0 && glfwGetKey(gamePropertiesStore.getGlfwWindow(), ic.getDown()) == 1) {
            PhysicsUtils.applyDirectionalImpulseOnBody(phyc.getBody(), new Vec2(0, -ic.getDownPower()));
        }

        if(ic.getRight() != 0 && glfwGetKey(gamePropertiesStore.getGlfwWindow(), ic.getRight()) == 1) {
            PhysicsUtils.applyDirectionalImpulseOnBody(phyc.getBody(), new Vec2(ic.getRightPower(), 0f));
        }
        if(ic.getLeft() != 0 && glfwGetKey(gamePropertiesStore.getGlfwWindow(), ic.getLeft()) == 1) {
            PhysicsUtils.applyDirectionalImpulseOnBody(phyc.getBody(), new Vec2(-ic.getLeftPower(), 0f));
        }

        entity.setChanged(true);
    }
}
