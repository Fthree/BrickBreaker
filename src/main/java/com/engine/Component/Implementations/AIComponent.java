package com.engine.Component.Implementations;

import com.engine.Component.Interface.IComponent;
import com.engine.Component.Type.ComponentType;
import org.jbox2d.common.Vec2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIComponent implements IComponent {
    ComponentType componentType;
    Vec2 direction;
}
