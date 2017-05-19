package com.engine.Component.Implementations;

import com.engine.Component.Interface.IComponent;
import com.engine.Component.Type.ComponentType;
import com.engine.Math.Vector.Vector3f;
import com.engine.Math.Vector.Vector4f;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransformComponent implements IComponent {
    ComponentType componentType;

    Vector3f position;
    Vector4f rotation;
    Vector3f size;
}
