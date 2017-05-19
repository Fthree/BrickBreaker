package com.engine.Events.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollisionEvent implements IEvent {
    String eventId;

    String coliderId;
    String colideeId;
}
