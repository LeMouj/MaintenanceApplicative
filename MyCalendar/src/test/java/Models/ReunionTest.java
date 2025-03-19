package Models;

import org.example.ValueObject.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReunionTest {

    @Test
    void shouldCreateValidReunion() {
        Reunion reunion = new Reunion(
                new TitreEvenement("Point Hebdo"),
                new ProprietaireEvenement("Jean"),
                new DateEvenement(LocalDateTime.of(2025, 3, 21, 10, 0)),
                new DureeEvenement(60),
                new LieuEvenement("Salle A"),
                new ParticipantsEvenement(List.of("Jean", "Alice", "Bob"))
        );

        assertEquals("Réunion : Point Hebdo à Salle A avec Jean, Alice, Bob", reunion.description());
    }
}