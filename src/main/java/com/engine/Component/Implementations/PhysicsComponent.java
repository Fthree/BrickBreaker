package com.engine.Component.Implementations;

import com.engine.Component.Interface.IComponent;
import com.engine.Component.Type.ComponentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhysicsComponent implements IComponent {
    ComponentType componentType;

    Body body;

    float initialRotation;
    Vec2 initialPosition;
    Vec2 initialSize;
    Float density;
    Float friction;
    Float restitution;
    Shape shape;
    Vec2 upSpeed;
    BodyType bodyType;
}
