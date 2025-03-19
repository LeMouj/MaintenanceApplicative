package org.example.Models;

import org.example.ValueObject.*;

public class RdvPersonnel extends Event {

    public RdvPersonnel(IdEvenement idEvent, TitreEvenement titre, ProprietaireEvenement proprietaire,
                        DateEvenement dateDebut, DureeEvenement duree) {
        super(idEvent, titre, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV : " + titre.valeur() + " à " + dateDebut.valeur();
    }
}
