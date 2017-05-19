package com.engine.System.Interface;

import com.engine.Entity.Interface.IEntity;

public interface ISystem {
    boolean initialize();
    boolean match(IEntity entity);
    void process(IEntity entity);
    void preProcess();
}
