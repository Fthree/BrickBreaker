package com.engine.Global;

import com.engine.Entity.Interface.IEntity;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GlobalEntityHandler {
    private static GlobalEntityHandler globalEntityHandler = new GlobalEntityHandler();

    private GlobalEntityHandler() {
    }

    public static GlobalEntityHandler getGlobalEntityHandler() {
        return globalEntityHandler;
    }

    ArrayList<IEntity> entities = new ArrayList<>();
    ArrayList<IEntity> removedEntities = new ArrayList<>();
}
