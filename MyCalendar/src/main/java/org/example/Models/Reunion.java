package org.example.Models;

import org.example.ValueObject.*;

public class Reunion extends Event {
    private final LieuEvenement lieu;
    private final ParticipantsEvenement participants;

    public Reunion(IdEvenement idEvent, TitreEvenement titre, ProprietaireEvenement proprietaire,
                   DateEvenement dateDebut, DureeEvenement duree,
                   LieuEvenement lieu, ParticipantsEvenement participants) {
        super(idEvent, titre, proprietaire, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + titre.valeur() + " à " + lieu.valeur() + " avec " + String.join(", ", participants.valeurs());
    }
}
