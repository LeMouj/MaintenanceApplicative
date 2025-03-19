package Utils;

import java.util.List;

public record ParticipantsEvenement(List<String> valeurs) {
    public ParticipantsEvenement {
        if (valeurs == null || valeurs.isEmpty()) {
            throw new IllegalArgumentException("La liste des participants ne peut pas être vide.");
        }
    }
}
