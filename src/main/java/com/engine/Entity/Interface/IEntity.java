package com.engine.Entity.Interface;

import com.engine.Component.Type.ComponentType;
import com.engine.Component.Interface.IComponent;

import java.util.List;

public interface IEntity {
    void subscribeComponent(IComponent component);
    void unSubscribeComponent(IComponent component);
    boolean hasComponent(ComponentType type);
    IComponent getComponent(ComponentType type);
    List<IComponent> getComponents();

    boolean isChanged();
    void setChanged(boolean changed);

    int getId();
    void setId(int id);
}
