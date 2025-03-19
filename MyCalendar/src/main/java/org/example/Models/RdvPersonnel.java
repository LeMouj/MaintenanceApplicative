package Models;

import ValueObject.*;

public class RdvPersonnel extends Event {

    public RdvPersonnel(TitreEvenement titre, ProprietaireEvenement proprietaire,
                        DateEvenement dateDebut, DureeEvenement duree) {
        super(titre, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV : " + titre.valeur() + " à " + dateDebut.valeur();
    }
}
