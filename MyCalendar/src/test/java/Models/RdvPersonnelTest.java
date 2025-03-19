package Models;

import org.example.Models.RdvPersonnel;
import org.example.ValueObject.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RdvPersonnelTest {

    @Test
    void shouldCreateValidRdvPersonnel() {
        RdvPersonnel rdv = new RdvPersonnel(
                new TitreEvenement("Dentiste"),
                new ProprietaireEvenement("Jean"),
                new DateEvenement(LocalDateTime.of(2025, 3, 20, 14, 30)),
                new DureeEvenement(30)
        );

        assertEquals("RDV : Dentiste à 2025-03-20T14:30", rdv.description());
    }
}