package org.example.ValueObject;

public record DureeEvenement(int valeur) {
    public DureeEvenement {
        if (valeur <= 0) {
            throw new IllegalArgumentException("La durée de l'événement doit être supérieure à zéro.");
        }
    }
}
