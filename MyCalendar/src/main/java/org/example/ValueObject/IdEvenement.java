package org.example.ValueObject;

import java.io.Serializable;

public record IdEvenement(String id) implements Serializable {
    public IdEvenement {
        if (id == null) {
            throw new IllegalArgumentException("L'identifiant de l'événement ne peut pas être nul.");
        }
    }
}
