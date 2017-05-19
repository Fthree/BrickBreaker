package com.engine.System.Implementations;

import com.engine.Component.Type.ComponentType;
import com.engine.Entity.Interface.IEntity;
import com.engine.System.Interface.ISystem;

public class AISystem implements ISystem  {
    public boolean match(IEntity entity) {
        return entity.hasComponent(ComponentType.PHYSICS) && entity.hasComponent(ComponentType.AI);
    }

    public boolean initialize() {

        return true;
    }

    public void preProcess() {

    }

    public void process(IEntity entity) {
    }
}
