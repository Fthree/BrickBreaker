package com.engine.Component.Implementations;

import com.engine.Component.Interface.IComponent;
import com.engine.Component.Type.ComponentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputComponent implements IComponent {
    ComponentType componentType;

    int up;
    int down;
    int left;
    int right;

    float upPower;
    float leftPower;
    float rightPower; //righteous
    float downPower;
}
