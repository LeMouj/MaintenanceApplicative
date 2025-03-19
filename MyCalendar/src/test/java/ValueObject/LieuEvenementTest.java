package ValueObject;

import org.example.ValueObject.LieuEvenement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LieuEvenementTest {

    @Test
    void shouldCreateValidLieuEvenement() {
        LieuEvenement lieu = new LieuEvenement("Salle 101");
        assertEquals("Salle 101", lieu.valeur());
    }

    @Test
    void shouldThrowExceptionIfLieuIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new LieuEvenement(""));
    }

    @Test
    void shouldThrowExceptionIfLieuIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new LieuEvenement(null));
    }
}
