package org.example.ValueObject;

public record FrequenceEvenement(int jours) {
    public FrequenceEvenement {
        if (jours <= 0) {
            throw new IllegalArgumentException("La fréquence de l'événement doit être supérieure à zéro.");
        }
    }
}
