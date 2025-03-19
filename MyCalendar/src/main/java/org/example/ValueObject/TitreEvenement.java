package org.example.ValueObject;

public record TitreEvenement(String valeur) {
    public TitreEvenement {
        if (valeur == null || valeur.isBlank()) {
            throw new IllegalArgumentException("Le titre de l'événement ne peut pas être vide.");
        }
    }
}
