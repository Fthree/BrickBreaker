package com.engine.Util;

import com.engine.Component.Interface.IComponent;

import java.util.ArrayList;

public class  EngineUtils {
    static int current = 0;

    public static void createNextEntity(ArrayList<IComponent> components) {
        EntityUtils.createFreshEntity(current);
        EntityUtils.addComponentsToEntity(EntityUtils.getEntityById(current++), components);
    }

    public static void removeComponentFromEntity(IComponent component, Integer entityId) {
        EntityUtils.removeComponentFromEntity(EntityUtils.getEntityById(entityId), component);
    }
}
