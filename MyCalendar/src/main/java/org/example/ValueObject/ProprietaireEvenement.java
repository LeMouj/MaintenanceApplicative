package org.example.ValueObject;

public record ProprietaireEvenement(String valeur) {
    public ProprietaireEvenement {
        if (valeur == null || valeur.isBlank()) {
            throw new IllegalArgumentException("Le propriétaire de l'événement ne peut pas être vide.");
        }
    }
}