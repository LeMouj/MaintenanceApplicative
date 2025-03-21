package org.example.Models;

import org.example.ValueObject.*;

public class EvenementAnniversaire extends Event {
    private final String personne;

    public EvenementAnniversaire(IdEvenement idEvent, TitreEvenement titre, ProprietaireEvenement proprietaire,
                                 DateEvenement dateDebut, DureeEvenement duree, String personne) {
        super(idEvent, titre, proprietaire, dateDebut, duree);
        this.personne = personne;
    }

    @Override
    public String description() {
        return "Anniversaire : " + titre + " - " + proprietaire + " - " + dateDebut + " - Personne : " + personne;
    }
}

