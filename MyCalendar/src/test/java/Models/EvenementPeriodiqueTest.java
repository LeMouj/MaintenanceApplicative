package Models;

import org.example.Models.EvenementPeriodique;
import org.example.ValueObject.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EvenementPeriodiqueTest {

    @Test
    void shouldCreateValidEvenementPeriodique() {
        EvenementPeriodique evenement = new EvenementPeriodique(
                new TitreEvenement("Répétition Chorale"),
                new ProprietaireEvenement("Jean"),
                new DateEvenement(LocalDateTime.of(2025, 3, 22, 18, 0)),
                new DureeEvenement(90),
                new FrequenceEvenement(7)
        );

        assertEquals("Événement périodique : Répétition Chorale tous les 7 jours", evenement.description());
    }

    @Test
    void shouldCalculateNextOccurrence() {
        EvenementPeriodique evenement = new EvenementPeriodique(
                new TitreEvenement("Répétition Chorale"),
                new ProprietaireEvenement("Jean"),
                new DateEvenement(LocalDateTime.of(2025, 3, 22, 18, 0)),
                new DureeEvenement(90),
                new FrequenceEvenement(7)
        );

        LocalDateTime reference = LocalDateTime.of(2025, 3, 30, 18, 0);
        LocalDateTime nextOccurrence = evenement.prochaineOccurrence(reference);

        assertEquals(LocalDateTime.of(2025, 3, 29, 18, 0), nextOccurrence);
    }
}