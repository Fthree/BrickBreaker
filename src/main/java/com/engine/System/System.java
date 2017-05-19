package com.engine.System;

import com.engine.Entity.Interface.IEntity;
import com.engine.System.Interface.ISystem;
import com.engine.System.Type.SystemType;

import java.util.ArrayList;
import java.util.List;

public class System {
    List<IEntity> entities;

    List<ISystem> updateSystems;
    List<ISystem> renderSystems;

    public System() {
        entities = new ArrayList<>();
        renderSystems = new ArrayList<>();
        updateSystems = new ArrayList<>();
    }

    public void initializeSystems() {
        renderSystems.forEach(ISystem::initialize);
        updateSystems.forEach(ISystem::initialize);
    }

    public void subscribeEntity(IEntity entity) {
        if (entities.stream().anyMatch(ent -> ent == entity)) { return; }
        entities.add(entity);
    }

    public void unSubscribeEntity(IEntity entity) {
        if (entities.stream().anyMatch(ent -> ent == entity)) {
            entities.remove(entity);
        }
    }

    public void subscribeSystem(ISystem system, SystemType type) {
        if(type == SystemType.UPDATE) {
            if (updateSystems.stream().anyMatch(sys -> sys == system)) { return; }
            updateSystems.add(system);
        } else if (type == SystemType.RENDER) {
            if (renderSystems.stream().anyMatch(sys -> sys == system)) { return; }
            renderSystems.add(system);
        }
    }

    public void unSubscribeSystem(ISystem system, SystemType type) {
        if(type == SystemType.UPDATE) {
            if (updateSystems.stream().anyMatch(sys -> sys == system))
                updateSystems.remove(system);
        } else if (type == SystemType.RENDER) {
            if (renderSystems.stream().anyMatch(sys -> sys == system))
                renderSystems.remove(system);
        }
    }

    public void preProcess() {
        updateSystems.forEach(ISystem::preProcess);
        renderSystems.forEach(ISystem::preProcess);
    }

    public void handleUpdateSystems() {
        entities.forEach(entity -> {
            updateSystems.forEach(system -> {
                if(system.match(entity)) {
                    system.process(entity);
                }
            });
        });
    }

    public void handleRenderSystems() {
        entities.forEach(entity -> {
            renderSystems.forEach(system -> {
                if(system.match(entity)) {
                    system.process(entity);
                }
            });
        });
    }

    public List<IEntity> getEntities() {
        return entities;
    }
}
