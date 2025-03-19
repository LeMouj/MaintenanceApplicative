package org.example;

import org.example.Service.CalendarManager;
import org.example.Models.*;
import org.example.ValueObject.*;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static CalendarManager calendar = new CalendarManager();
    private static Scanner scanner = new Scanner(System.in);
    private static String utilisateur = null;
    private static String[] utilisateurs = new String[99];
    private static String[] motsDePasses = new String[99];
    private static int nbUtilisateurs = 0;

    public static void main(String[] args) {
        while (true) {
            if (utilisateur == null) {
                afficherLogo();
                afficherMenuConnexion();
            }

            while (utilisateur != null) {
                afficherMenuGestionnaire();
                String choix = scanner.nextLine();
                switch (choix) {
                    case "1":
                        afficherEvenements();
                        break;
                    case "2":
                        ajouterRdvPersonnel();
                        break;
                    case "3":
                        ajouterReunion();
                        break;
                    case "4":
                        ajouterEvenementPeriodique();
                        break;
                    case "5":
                        ajouterAnniversaire();
                        break;
                    case "6":
                        deconnecterUtilisateur();
                        break;
                    case "7":
                        supprimerEvenement();
                        break;
                    default:
                        System.out.println("Option invalide.");
                }
            }
        }
    }

    // Méthodes pour les menus

    private static void afficherLogo() {
        System.out.println("  _____         _                   _                __  __");
        System.out.println(" / ____|       | |                 | |              |  \\/  |");
        System.out.println("| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
        System.out.println("| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
        System.out.println("| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
        System.out.println(" \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
        System.out.println("                                                                                   __/ |");
        System.out.println("                                                                                  |___/");
    }

    private static void afficherMenuConnexion() {
        System.out.println("1 - Se connecter");
        System.out.println("2 - Créer un compte");
        System.out.print("Choix : ");

        switch (scanner.nextLine()) {
            case "1":
                seConnecter();
                break;
            case "2":
                creerCompte();
                break;
            default:
                System.out.println("Option invalide.");
        }
    }

    private static void seConnecter() {
        System.out.print("Nom d'utilisateur: ");
        utilisateur = scanner.nextLine();

        if (utilisateur.equals("Roger")) {
            String motDePasse = scanner.nextLine();
            if (!motDePasse.equals("Chat")) {
                utilisateur = null;
            }
        } else if (utilisateur.equals("Pierre")) {
            String motDePasse = scanner.nextLine();
            if (!motDePasse.equals("KiRouhl")) {
                utilisateur = null;
            }
        } else {
            System.out.print("Mot de passe: ");
            String motDePasse = scanner.nextLine();
            for (int i = 0; i < nbUtilisateurs; i++) {
                assert utilisateurs[i] != null;
                if (utilisateurs[i].equals(utilisateur) && motsDePasses[i].equals(motDePasse)) {
                    utilisateur = utilisateurs[i];
                }
            }
        }
    }

    private static void creerCompte() {
        System.out.print("Nom d'utilisateur: ");
        utilisateur = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();
        System.out.print("Répéter mot de passe: ");
        if (scanner.nextLine().equals(motDePasse)) {
            utilisateurs[nbUtilisateurs] = utilisateur;
            motsDePasses[nbUtilisateurs] = motDePasse;
            nbUtilisateurs++;
        } else {
            System.out.println("Les mots de passes ne correspondent pas...");
            utilisateur = null;
        }
    }

    private static void afficherMenuGestionnaire() {
        System.out.println("\nBonjour, " + utilisateur);
        System.out.println("=== Menu Gestionnaire d'Événements ===");
        System.out.println("1 - Voir les événements");
        System.out.println("2 - Ajouter un rendez-vous perso");
        System.out.println("3 - Ajouter une réunion");
        System.out.println("4 - Ajouter un évènement périodique");
        System.out.println("5 - Ajouter un anniversaire");
        System.out.println("6 - Se déconnecter");
        System.out.println("7 - Supprimer un événement");
        System.out.print("Votre choix : ");
    }

    // Méthodes pour gérer les événements

    private static void afficherEvenements() {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Retour");
        System.out.print("Votre choix : ");

        String choix = scanner.nextLine();

        switch (choix) {
            case "1":
                calendar.afficherEvenements();
                break;
            case "2":
                afficherEvenementsParMois();
                break;
            case "3":
                afficherEvenementsParSemaine();
                break;
            case "4":
                afficherEvenementsParJour();
                break;
            case "5":
                break;
            default:
                System.out.println("Option invalide.");
        }
    }

    private static void afficherEvenementsParMois() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeMois = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(new DateEvenement(debutMois), new DateEvenement(finMois)));
    }

    private static void afficherEvenementsParSemaine() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeSemaine = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le numéro de semaine (1-52) : ");
        int semaine = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutSemaine = LocalDateTime.now()
                .withYear(anneeSemaine)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(new DateEvenement(debutSemaine), new DateEvenement(finSemaine)));
    }

    private static void afficherEvenementsParJour() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int moisJour = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le jour (1-31) : ");
        int jour = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(new DateEvenement(debutJour), new DateEvenement(finJour)));
    }

    private static void ajouterRdvPersonnel() {
        System.out.print("Titre de l'événement : ");
        String titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());

        TitreEvenement titreEvenement = new TitreEvenement(titre);
        ProprietaireEvenement proprietaireEvenement = new ProprietaireEvenement(utilisateur);
        DateEvenement dateEvenement = new DateEvenement(LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute));
        DureeEvenement dureeEvenement = new DureeEvenement(duree);
        IdEvenement idEvenement = new IdEvenement(UUID.randomUUID());

        Event rdvPersonnel = new RdvPersonnel(idEvenement, titreEvenement, proprietaireEvenement, dateEvenement, dureeEvenement);

        calendar.ajouterEvent(rdvPersonnel);

        System.out.println("Événement ajouté.");
    }

    private static void ajouterReunion() {
        System.out.print("Titre de l'événement : ");
        String titre2 = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree2 = Integer.parseInt(scanner.nextLine());
        System.out.println("Lieu :");
        String lieu = scanner.nextLine();

        List<String> participants = new java.util.ArrayList<>(List.of(utilisateur));

        System.out.println("Ajouter un participant ? (oui / non)");
        while (scanner.nextLine().equals("oui"))
        {
            System.out.print("Participants : " + participants);
            participants.add(scanner.nextLine());
        }

        TitreEvenement titreEvenement2 = new TitreEvenement(titre2);
        ProprietaireEvenement proprietaireEvenement2 = new ProprietaireEvenement(utilisateur);
        DateEvenement dateEvenement2 = new DateEvenement(LocalDateTime.of(annee2, moisRdv2, jourRdv2, heure2, minute2));
        DureeEvenement dureeEvenement2 = new DureeEvenement(duree2);
        LieuEvenement lieuEvenement = new LieuEvenement(lieu);
        ParticipantsEvenement participantsEvenement = new ParticipantsEvenement(participants);
        IdEvenement idEvenement2 = new IdEvenement(UUID.randomUUID());

        Event reunion = new Reunion(idEvenement2, titreEvenement2, proprietaireEvenement2, dateEvenement2, dureeEvenement2, lieuEvenement, participantsEvenement);

        calendar.ajouterEvent(reunion);

        System.out.println("Événement ajouté.");
    }

    private static void ajouterEvenementPeriodique() {
        System.out.print("Titre de l'événement : ");
        String titre3 = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee3 = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv3 = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv3 = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure3 = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute3 = Integer.parseInt(scanner.nextLine());
        System.out.print("Fréquence (en jours) : ");
        int frequence = Integer.parseInt(scanner.nextLine());

        TitreEvenement titreEvenement3 = new TitreEvenement(titre3);
        ProprietaireEvenement proprietaireEvenement3 = new ProprietaireEvenement(utilisateur);
        DateEvenement dateEvenement3 = new DateEvenement(LocalDateTime.of(annee3, moisRdv3, jourRdv3, heure3, minute3));
        DureeEvenement dureeEvenement3 = new DureeEvenement(0);
        FrequenceEvenement frequenceEvenement = new FrequenceEvenement(frequence);
        IdEvenement idEvenement3 = new IdEvenement(UUID.randomUUID());

        Event evenementPeriodique = new EvenementPeriodique(idEvenement3, titreEvenement3, proprietaireEvenement3, dateEvenement3, dureeEvenement3, frequenceEvenement);

        calendar.ajouterEvent(evenementPeriodique);

        System.out.println("Événement ajouté.");
    }

    private static void ajouterAnniversaire() {
        System.out.print("Titre de l'événement : ");
        String titreAnniversaire = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int anneeAnniversaire = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisAnniversaire = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourAnniversaire = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heureAnniversaire = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minuteAnniversaire = Integer.parseInt(scanner.nextLine());
        System.out.print("Nom de la personne célébrée : ");
        String personneAnniversaire = scanner.nextLine();
        System.out.print("Durée (en minutes) : ");
        int dureeAnniversaire = Integer.parseInt(scanner.nextLine());

        TitreEvenement titreEvenementAnniversaire = new TitreEvenement(titreAnniversaire);
        ProprietaireEvenement proprietaireEvenementAnniversaire = new ProprietaireEvenement(utilisateur);
        DateEvenement dateEvenementAnniversaire = new DateEvenement(LocalDateTime.of(anneeAnniversaire, moisAnniversaire, jourAnniversaire, heureAnniversaire, minuteAnniversaire));
        DureeEvenement dureeEvenementAnniversaire = new DureeEvenement(dureeAnniversaire);
        IdEvenement idEvenementAnniversaire = new IdEvenement(UUID.randomUUID());

        Event anniversaire = new EvenementAnniversaire(idEvenementAnniversaire, titreEvenementAnniversaire, proprietaireEvenementAnniversaire, dateEvenementAnniversaire, dureeEvenementAnniversaire, personneAnniversaire);

        calendar.ajouterEvent(anniversaire);

        System.out.println("Événement Anniversaire ajouté.");
    }

    private static void supprimerEvenement() {
        System.out.print("Entrez l'UUID de l'événement à supprimer : ");
        String uuidInput = scanner.nextLine();

        try {
            UUID uuidToDelete = UUID.fromString(uuidInput);
            IdEvenement idEvenementtoDelete = new IdEvenement(uuidToDelete);
            boolean deleted = calendar.supprimerEventParId(idEvenementtoDelete);
            if (deleted) {
                System.out.println("Événement supprimé avec succès.");
            } else {
                System.out.println("Événement introuvable avec cet UUID.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("UUID invalide.");
        }
    }

    private static void deconnecterUtilisateur() {
        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
        boolean continuer = scanner.nextLine().trim().equalsIgnoreCase("oui");
        if (!continuer) {
            utilisateur = null;
        }
    }

    private static void afficherListe(List<Event> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }
}
