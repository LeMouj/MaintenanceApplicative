package ValueObject;

import org.example.ValueObject.FrequenceEvenement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrequenceEvenementTest {

    @Test
    void shouldCreateValidFrequenceEvenement() {
        FrequenceEvenement frequence = new FrequenceEvenement(7);
        assertEquals(7, frequence.jours());
    }

    @Test
    void shouldThrowExceptionIfFrequenceIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new FrequenceEvenement(0));
    }

    @Test
    void shouldThrowExceptionIfFrequenceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new FrequenceEvenement(-1));
    }
}
