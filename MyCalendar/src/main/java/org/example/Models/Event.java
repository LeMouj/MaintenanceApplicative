package org.example.Models;

import org.example.ValueObject.*;

import java.time.LocalDateTime;

public abstract class Event {
    protected final TitreEvenement titre;
    protected final ProprietaireEvenement proprietaire;
    protected final DateEvenement dateDebut;
    protected final DureeEvenement duree;

    public Event(TitreEvenement titre, ProprietaireEvenement proprietaire,
                 DateEvenement dateDebut, DureeEvenement duree) {
        this.titre = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    public LocalDateTime getDateFin() {
        return dateDebut.valeur().plusMinutes(duree.valeur());
    }

    public DateEvenement getDateDebut() {return dateDebut;}

    public abstract String description();
}
