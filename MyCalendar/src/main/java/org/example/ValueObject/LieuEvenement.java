package org.example.ValueObject;

public record LieuEvenement(String valeur) {
    public LieuEvenement {
        if (valeur == null || valeur.isBlank()) {
            throw new IllegalArgumentException("Le lieu de l'événement ne peut pas être vide.");
        }
    }
}
