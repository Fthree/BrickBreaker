package com.engine.Handler;

import com.engine.Entity.Factory.EntityFactory;
import com.engine.Global.GlobalEntityHandler;
import com.engine.Store.GamePropertiesStore;
import com.engine.System.Implementations.*;
import com.engine.System.System;
import com.engine.System.Type.SystemType;
import com.engine.Util.EntityUtils;
import com.engine.Window.WindowSystem;
import com.game.System.BallSystem;
import com.game.System.BrickSystem;
import com.game.System.PaddleSystem;
import com.game.System.WallSystem;

public class SystemHandler {
    EntityFactory entityFactory;
    WindowSystem windowSystem;

    System gameSystem;

    public SystemHandler() {
        entityFactory = new EntityFactory();
        windowSystem = new WindowSystem();
        gameSystem = new System();
    }

    public void InitializeEntitiesAndSystems() {
        subscribeEntitiesFromGlobalStore();

        //Systems called during the update tick
        gameSystem.subscribeSystem(new PhysicsSystem(), SystemType.UPDATE);
        gameSystem.subscribeSystem(new InputSystem(), SystemType.UPDATE);
        gameSystem.subscribeSystem(new AISystem(), SystemType.UPDATE);
        gameSystem.subscribeSystem(new PaddleSystem(), SystemType.UPDATE);
        gameSystem.subscribeSystem(new BallSystem(), SystemType.UPDATE);
        gameSystem.subscribeSystem(new BrickSystem(), SystemType.UPDATE);
        gameSystem.subscribeSystem(new WallSystem(), SystemType.UPDATE);

        //Systems called during the render tick
        gameSystem.subscribeSystem(new RenderSystem(), SystemType.RENDER);

        //Call initialization on all systems
        gameSystem.initializeSystems();

        //Collision listener. Systems must be initialized before starting contact listener
        GamePropertiesStore.getProperties().getPhysicsWorld().setContactListener(new ContactSystem());
    }

    private void subscribeEntitiesFromGlobalStore() {
        GlobalEntityHandler.getGlobalEntityHandler().getEntities().stream().forEach(iEntity -> {
            gameSystem.subscribeEntity(iEntity);
        });
    }

    public void preProcess() {
        gameSystem.preProcess();
    }

    public void update() {
        gameSystem.handleUpdateSystems();
        updateRemovedEntities();
    }

    private void updateRemovedEntities() {
        GlobalEntityHandler.getGlobalEntityHandler().getRemovedEntities().stream().forEach(iEntity -> {
            gameSystem.unSubscribeEntity(iEntity);
        });

        EntityUtils.clearRemovedEntityGlobalStore();
    }

    public void render() {
        gameSystem.handleRenderSystems();
    }
}
