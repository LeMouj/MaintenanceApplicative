package ValueObject;

import org.example.ValueObject.DureeEvenement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DureeEvenementTest {

    @Test
    void shouldCreateValidDureeEvenement() {
        DureeEvenement duree = new DureeEvenement(60);
        assertEquals(60, duree.valeur());
    }

    @Test
    void shouldThrowExceptionIfDureeIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new DureeEvenement(0));
    }

    @Test
    void shouldThrowExceptionIfDureeIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new DureeEvenement(-10));
    }
}
