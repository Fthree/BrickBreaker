package com.engine.Util.AI;

import com.engine.Entity.Implementations.GameEntity;

public interface State {
    void execute(GameEntity gameEntity);
}
