package com.engine.Entity.Implementations;

import com.engine.Component.Type.ComponentType;
import com.engine.Component.Interface.IComponent;
import com.engine.Entity.Interface.IEntity;
import com.engine.Util.EntityUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameEntity implements IEntity {
    List<IComponent> components;
    boolean changed;
    int id;

    public GameEntity() {
        components = new ArrayList<>();
    }

    public void subscribeComponent(IComponent component) {
        if (EntityUtils.doesComponentExist(components, component)) { return; }
        components.add(component);
    }

    public void unSubscribeComponent(IComponent component) {
        if(EntityUtils.doesComponentExist(components, component)) {
            components.remove(component);
        }
    }

    public boolean hasComponent(ComponentType type) {
        return components.stream().anyMatch(component -> component.getComponentType() == type);
    }

    public IComponent getComponent(ComponentType type) {
        return components.stream().filter(component -> component.getComponentType() == type).findAny().get();
    }
}
