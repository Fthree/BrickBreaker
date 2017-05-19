package com.engine.Util;

import com.engine.Component.Implementations.IdComponent;
import com.engine.Component.Implementations.PhysicsComponent;
import com.engine.Component.Implementations.RigidbodyComponent;
import com.engine.Component.Interface.IComponent;
import com.engine.Component.Type.ComponentType;
import com.engine.Entity.Implementations.GameEntity;
import com.engine.Entity.Interface.IEntity;
import com.engine.Global.GlobalEntityHandler;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EntityUtils {
    public static boolean doesComponentExist(List<IComponent> components, IComponent matcher) {
        return components.stream().anyMatch(comp -> comp == matcher);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getComponent(IEntity gameEntity, ComponentType componentType) {
        return ((T)gameEntity.getComponent(componentType));
    }

    public static void addComponentsToEntity(IEntity gameEntity, ArrayList<IComponent> components) {
        components.forEach(gameEntity::subscribeComponent);
    }

    public static void removeComponentFromEntity(IEntity gameEntity, IComponent component) {
        gameEntity.subscribeComponent(component);
    }

    public static IEntity getEntityById(Integer id) {
        return GlobalEntityHandler.getGlobalEntityHandler().getEntities().stream().filter(iEntity -> iEntity.getId() == id).findFirst().orElse(new GameEntity());
    }

    public static IEntity getEntityByName(String name) {
        return GlobalEntityHandler.getGlobalEntityHandler().getEntities().stream()
                .filter(iEntity -> ((IdComponent)getComponent(iEntity, ComponentType.ID)).getName().contains(name))
                .findFirst().orElse(new GameEntity());
    }

    public static String getEntityName(IEntity entity) {
        return ((IdComponent)getComponent(entity, ComponentType.ID)).getName();
    }

    public static List<IEntity> getAllEntitiesByName(String name) {
        return GlobalEntityHandler.getGlobalEntityHandler().getEntities().stream()
                .filter(iEntity -> ((IdComponent)getComponent(iEntity, ComponentType.ID)).getName().contains(name))
                .collect(Collectors.toList());
    }

    public static void createFreshEntity(Integer id) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(id);
        GlobalEntityHandler.getGlobalEntityHandler().getEntities().add(gameEntity);
    }

    public static Body getEntityBody(IEntity entity) {
        return ((PhysicsComponent)entity.getComponent(ComponentType.PHYSICS)).getBody();
    }

    public static boolean doesEntityNamesMatch(IEntity A, IEntity B) {
        return getEntityName(A).equals(getEntityName(B));
    }

    public static void removeEntityFromGlobalStore(IEntity entity) {
        GlobalEntityHandler.getGlobalEntityHandler().getEntities().removeIf(globalEntity -> doesEntityNamesMatch(entity, globalEntity));
    }

    public static void addEntityToGlobalStore(IEntity entity) {
        GlobalEntityHandler.getGlobalEntityHandler().getEntities().add(entity);
    }

    public static void addToRemovedEntityGlobalStore(IEntity entity) {
        GlobalEntityHandler.getGlobalEntityHandler().getRemovedEntities().add(entity);
    }

    public static void clearRemovedEntityGlobalStore() {
        GlobalEntityHandler.getGlobalEntityHandler().getRemovedEntities().clear();
    }

    public static void setEntityCollidedValue(IEntity entity, boolean value) {
        ((RigidbodyComponent)getComponent(entity, ComponentType.RIGIDBODY)).setColliding(value);
    }

    public static boolean isEntityColliding(IEntity entity) {
        return ((RigidbodyComponent)getComponent(entity, ComponentType.RIGIDBODY)).isColliding();
    }

    public static void setEntityLocalNormal(IEntity entity, Vec2 normal) {
        ((RigidbodyComponent)getComponent(entity, ComponentType.RIGIDBODY)).setLocalNormal(normal);
    }

    public static Vec2 getEntityLocalNormal(IEntity entity) {
        return ((RigidbodyComponent)getComponent(entity, ComponentType.RIGIDBODY)).getLocalNormal();
    }

    public static void setEntityVelocityBeforeCollision(IEntity entity, Vec2 velocity) {
        ((RigidbodyComponent)getComponent(entity, ComponentType.RIGIDBODY)).setVelocity(velocity);
    }

    public static Vec2 getEntityVelocityBeforeCollision(IEntity entity) {
        return ((RigidbodyComponent)getComponent(entity, ComponentType.RIGIDBODY)).getVelocity();
    }

    public static boolean isEntityNamed(IEntity entity, String name) {
        return getEntityName(entity).contains(name);
    }
}
