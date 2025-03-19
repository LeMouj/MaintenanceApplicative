package org.example.Service;

import org.example.Models.*;
import org.example.ValueObject.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    private final Evenements evenements;

    public CalendarManager() {
        this.evenements = new Evenements();
    }

    // Ajoute un événement à la liste d'événements
    public void ajouterEvent(Event event) {
        evenements.ajouter(event);
    }

    // Retourne la liste des événements qui se produisent dans une période donnée
    public List<Event> eventsDansPeriode(DateEvenement debut, DateEvenement fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : evenements.getListe()) {
            if (!e.getDateDebut().valeur().isBefore(debut.valeur()) && !e.getDateDebut().valeur().isAfter(fin.valeur())) {
                result.add(e);
            }
        }
        return result;
    }

    // Vérifie s'il y a un conflit entre deux événements
    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.getDateFin();
        LocalDateTime fin2 = e2.getDateFin();

        return e1.getDateDebut().valeur().isBefore(fin2) && fin1.isAfter(e2.getDateDebut().valeur());
    }

    // Affiche la description de tous les événements
    public void afficherEvenements() {
        for (Event e : evenements.getListe()) {
            System.out.println(e.description());
        }
    }
}
