package com.engine.Entity.Factory;

import com.engine.Component.Interface.IComponent;
import com.engine.Entity.Implementations.GameEntity;

import java.util.List;

public class EntityFactory {
    public GameEntity getNewGameEntity(List<IComponent> components) {
        GameEntity entity = new GameEntity();
        components.forEach(entity::subscribeComponent);
        return entity;
    }
}
