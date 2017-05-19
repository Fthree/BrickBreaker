package com.engine.Component.Type;

public enum ComponentType {
    TRANSFORM(1 << 0),
    INPUT(1 << 1),
    TEXTURE(1 << 2),
    WINDOW(1 << 3),
    AI(1 << 4),
    PHYSICS(1 << 5),
    ID(1 << 6),
    RIGIDBODY(1 << 7),
    BALL(1 << 8),
    PADDLE(1 << 9),
    BRICK(1 << 10),
    WALL(1 << 11);

    public int value;

    ComponentType(int value) {
        this.value = value;
    }
}
