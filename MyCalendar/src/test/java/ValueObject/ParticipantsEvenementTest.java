package ValueObject;

import org.example.ValueObject.ParticipantsEvenement;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantsEvenementTest {

    @Test
    void shouldCreateValidParticipantsEvenement() {
        ParticipantsEvenement participants = new ParticipantsEvenement(List.of("Alice", "Bob"));
        assertEquals(List.of("Alice", "Bob"), participants.valeurs());
    }

    @Test
    void shouldThrowExceptionIfParticipantsIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new ParticipantsEvenement(List.of()));
    }

    @Test
    void shouldThrowExceptionIfParticipantsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new ParticipantsEvenement(null));
    }
}
