package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Evenements {
    private final List<Event> events;

    public Evenements() {
        this.events = new ArrayList<>();
    }

    // Ajoute un événement à la liste
    public void ajouter(Event event) {
        events.add(event);
    }

    // Retourne la liste des événements
    public List<Event> getListe() {
        return events;
    }

    // Retourne un événement par son index
    public Event get(int index) {
        return events.get(index);
    }

    // Renvoie le nombre d'événements
    public int size() {
        return events.size();
    }
}
