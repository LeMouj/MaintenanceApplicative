package ValueObject;

import org.example.ValueObject.ProprietaireEvenement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProprietaireEvenementTest {

    @Test
    void shouldCreateValidProprietaireEvenement() {
        ProprietaireEvenement proprietaire = new ProprietaireEvenement("Jean");
        assertEquals("Jean", proprietaire.valeur());
    }

    @Test
    void shouldThrowExceptionIfProprietaireIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new ProprietaireEvenement(""));
    }

    @Test
    void shouldThrowExceptionIfProprietaireIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new ProprietaireEvenement(null));
    }
}
