package ValueObject;

import org.example.ValueObject.TitreEvenement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitreEvenementTest {

    @Test
    void shouldCreateValidTitreEvenement() {
        TitreEvenement titre = new TitreEvenement("Réunion");
        assertEquals("Réunion", titre.valeur());
    }

    @Test
    void shouldThrowExceptionIfTitreIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new TitreEvenement(""));
    }

    @Test
    void shouldThrowExceptionIfTitreIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new TitreEvenement(null));
    }
}
