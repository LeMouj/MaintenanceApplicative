package ValueObject;

import org.example.ValueObject.DateEvenement;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateEvenementTest {

    @Test
    void shouldCreateValidDateEvenement() {
        LocalDateTime date = LocalDateTime.of(2025, 3, 20, 10, 0);
        DateEvenement dateEvenement = new DateEvenement(date);
        assertEquals(date, dateEvenement.valeur());
    }

    @Test
    void shouldThrowExceptionIfDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new DateEvenement(null));
    }
}