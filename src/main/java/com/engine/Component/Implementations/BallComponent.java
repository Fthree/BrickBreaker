package com.engine.Component.Implementations;

import com.engine.Component.Interface.IComponent;
import com.engine.Component.Type.ComponentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BallComponent implements IComponent {
    ComponentType componentType;
    boolean activateBall;
}
