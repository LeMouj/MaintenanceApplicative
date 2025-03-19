package org.example.Models;

import org.example.ValueObject.*;

import java.time.LocalDateTime;

public class EvenementPeriodique extends Event {
    private final FrequenceEvenement frequence;

    public EvenementPeriodique(TitreEvenement titre, ProprietaireEvenement proprietaire,
                               DateEvenement dateDebut, DureeEvenement duree,
                               FrequenceEvenement frequence) {
        super(titre, proprietaire, dateDebut, duree);
        this.frequence = frequence;
    }

    @Override
    public String description() {
        return "Événement périodique : " + titre.valeur() + " tous les " + frequence.jours() + " jours";
    }

    public LocalDateTime prochaineOccurrence(LocalDateTime reference) {
        LocalDateTime prochaine = dateDebut.valeur();
        while (prochaine.isBefore(reference)) {
            prochaine = prochaine.plusDays(frequence.jours());
        }
        return prochaine;
    }
}
