package com.engine.Component.Implementations;

import com.engine.Component.Interface.IComponent;
import com.engine.Component.Type.ComponentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RigidbodyComponent implements IComponent {
    ComponentType componentType;

    boolean isColliding;
    Vec2 localNormal;
    Vec2 velocity;
}
