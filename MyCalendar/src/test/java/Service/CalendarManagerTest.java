package Service;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Models.*;
import org.example.ValueObject.*;
import org.example.Service.CalendarManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

class CalendarManagerTest {

    private CalendarManager calendarManager;

    @BeforeEach
    void setUp() {
        calendarManager = new CalendarManager();
    }

    // Test l'ajout d'un événement dans le calendrier
    @Test
    void testAjouterEvent() {
        // Créez un événement RDV et ajoutez-le au manager
        TitreEvenement titreRdv = new TitreEvenement("Rendez-vous chez le médecin");
        ProprietaireEvenement proprietaireRdv = new ProprietaireEvenement("Jean Dupont");
        DateEvenement dateRdv = new DateEvenement(LocalDateTime.of(2025, 3, 20, 10, 0));
        DureeEvenement dureeRdv = new DureeEvenement(30);
        LieuEvenement lieuRdv = new LieuEvenement("Hôpital");
        ParticipantsEvenement participantsRdv = new ParticipantsEvenement("Jean, Marie");

        RdvPersonnel rdv = new RdvPersonnel(titreRdv, proprietaireRdv, dateRdv, dureeRdv);

        // Ajoutez l'événement au manager
        calendarManager.ajouterEvent(rdv);

        // Vérifie que l'événement a été ajouté correctement
        assertEquals(1, calendarManager.evenements.size());
    }

    // Test la récupération des événements dans une période donnée
    @Test
    void testEventsDansPeriode() {
        // Ajout d'événements à la période
        TitreEvenement titreRdv = new TitreEvenement("Rendez-vous chez le médecin");
        ProprietaireEvenement proprietaireRdv = new ProprietaireEvenement("Jean Dupont");
        DateEvenement dateRdv = new DateEvenement(LocalDateTime.of(2025, 3, 20, 10, 0));
        DureeEvenement dureeRdv = new DureeEvenement(30);
        RdvPersonnel rdv = new RdvPersonnel(titreRdv, proprietaireRdv, dateRdv, dureeRdv);

        TitreEvenement titreReunion = new TitreEvenement("Réunion d'équipe");
        ProprietaireEvenement proprietaireReunion = new ProprietaireEvenement("Sophie");
        DateEvenement dateReunion = new DateEvenement(LocalDateTime.of(2025, 3, 22, 14, 0));
        DureeEvenement dureeReunion = new DureeEvenement(60);
        LieuEvenement lieuReunion = new LieuEvenement("Bureau");
        ParticipantsEvenement participantsReunion = new ParticipantsEvenement("Sophie, Paul");
        Reunion reunion = new Reunion(titreReunion, proprietaireReunion, dateReunion, dureeReunion, lieuReunion, participantsReunion);

        // Ajoutez les événements au manager
        calendarManager.ajouterEvent(rdv);
        calendarManager.ajouterEvent(reunion);

        // Période à tester
        DateEvenement debut = new DateEvenement(LocalDateTime.of(2025, 3, 20, 0, 0));
        DateEvenement fin = new DateEvenement(LocalDateTime.of(2025, 3, 21, 23, 59));

        // Récupère les événements dans la période
        List<Event> eventsInRange = calendarManager.eventsDansPeriode(debut, fin);

        // Vérifie que seulement 1 événement est dans la période
        assertEquals(1, eventsInRange.size());
    }

    // Test la détection de conflits entre événements
    @Test
    void testConflit() {
        // Création des événements à tester
        TitreEvenement titreRdv = new TitreEvenement("Rendez-vous chez le médecin");
        ProprietaireEvenement proprietaireRdv = new ProprietaireEvenement("Jean Dupont");
        DateEvenement dateRdv = new DateEvenement(LocalDateTime.of(2025, 3, 20, 10, 0));
        DureeEvenement dureeRdv = new DureeEvenement(30);
        RdvPersonnel rdv = new RdvPersonnel(titreRdv, proprietaireRdv, dateRdv, dureeRdv);

        TitreEvenement titreReunion = new TitreEvenement("Réunion d'équipe");
        ProprietaireEvenement proprietaireReunion = new ProprietaireEvenement("Sophie");
        DateEvenement dateReunion = new DateEvenement(LocalDateTime.of(2025, 3, 20, 10, 15));
        DureeEvenement dureeReunion = new DureeEvenement(60);
        Reunion reunion = new Reunion(titreReunion, proprietaireReunion, dateReunion, dureeReunion, new LieuEvenement("Bureau"), new ParticipantsEvenement("Sophie, Paul"));

        // Vérifie qu'il y a un conflit entre les événements
        assertTrue(calendarManager.conflit(rdv, reunion));

        // Création d'un événement sans conflit
        TitreEvenement titreDejeuner = new TitreEvenement("Déjeuner");
        ProprietaireEvenement proprietaireDejeuner = new ProprietaireEvenement("Jean");
        DateEvenement dateDejeuner = new DateEvenement(LocalDateTime.of(2025, 3, 20, 12, 0));
        DureeEvenement dureeDejeuner = new DureeEvenement(60);
        RdvPersonnel dejeuner = new RdvPersonnel(titreDejeuner, proprietaireDejeuner, dateDejeuner, dureeDejeuner);

        // Vérifie qu'il n'y a pas de conflit entre le déjeuner et le rendez-vous
        assertFalse(calendarManager.conflit(rdv, dejeuner));
    }

    // Test l'affichage des événements
    @Test
    void testAffichageEvenements() {
        // Crée et ajoute un événement au manager
        TitreEvenement titreRdv = new TitreEvenement("Rendez-vous chez le médecin");
        ProprietaireEvenement proprietaireRdv = new ProprietaireEvenement("Jean Dupont");
        DateEvenement dateRdv = new DateEvenement(LocalDateTime.of(2025, 3, 20, 10, 0));
        DureeEvenement dureeRdv = new DureeEvenement(30);
        RdvPersonnel rdv = new RdvPersonnel(titreRdv, proprietaireRdv, dateRdv, dureeRdv);

        // Ajoute l'événement au manager
        calendarManager.ajouterEvent(rdv);

        // Vérifie que l'affichage ne lance pas d'exception
        assertDoesNotThrow(() -> calendarManager.afficherEvenements());
    }
}
