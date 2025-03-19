package org.example;

import org.example.Models.Event;
import org.example.Service.CalendarManager;
import org.example.Models.*;
import org.example.ValueObject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Main {

    private static final CalendarManager calendar = new CalendarManager();
    private static String utilisateur = null;
    private static final String[] utilisateurs = new String[99];
    private static final String[] motsDePasses = new String[99];
    private static int nbUtilisateurs = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestionnaire d'Événements");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            JPanel panel = new JPanel();
            panel.setLayout(new CardLayout());

            // Créer les panneaux pour chaque partie
            JPanel loginPanel = createLoginPanel(panel, frame);
            JPanel menuPanel = createMenuPanel(panel);

            panel.add(loginPanel, "Login");
            panel.add(menuPanel, "Menu");

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    // Panneau de connexion
    private static JPanel createLoginPanel(JPanel mainPanel, JFrame frame) {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bienvenue ! Connectez-vous ou créez un compte.", SwingConstants.CENTER);
        loginPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Mot de passe:");
        JPasswordField passwordField = new JPasswordField();

        fieldsPanel.add(usernameLabel);
        fieldsPanel.add(usernameField);
        fieldsPanel.add(passwordLabel);
        fieldsPanel.add(passwordField);

        loginPanel.add(fieldsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (seConnecter(username, password)) {
                utilisateur = username;
                ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Menu");
            } else {
                JOptionPane.showMessageDialog(frame, "Nom d'utilisateur ou mot de passe incorrect.");
            }
        });

        JButton createAccountButton = new JButton("Créer un compte");
        createAccountButton.addActionListener(e -> {
            // Fonctionnalité pour la création de compte (similaire à la méthode 'creerCompte' de ton code)
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (creerCompte(username, password)) {
                JOptionPane.showMessageDialog(frame, "Compte créé avec succès !");
            } else {
                JOptionPane.showMessageDialog(frame, "Erreur lors de la création du compte.");
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(createAccountButton);

        loginPanel.add(buttonPanel, BorderLayout.SOUTH);

        return loginPanel;
    }

    // Panneau principal (après connexion)
    private static JPanel createMenuPanel(JPanel mainPanel) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bonjour, " + utilisateur, SwingConstants.CENTER);
        menuPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));

        JButton viewEventsButton = new JButton("Voir les événements");
        viewEventsButton.addActionListener(e -> afficherEvenements());
        JButton addPersonalRdvButton = new JButton("Ajouter un rendez-vous personnel");
        addPersonalRdvButton.addActionListener(e -> ajouterRdvPersonnel());
        JButton addMeetingButton = new JButton("Ajouter une réunion");
        addMeetingButton.addActionListener(e -> ajouterReunion());
        JButton addPeriodicEventButton = new JButton("Ajouter un événement périodique");
        addPeriodicEventButton.addActionListener(e -> ajouterEvenementPeriodique());
        JButton addBirthdayButton = new JButton("Ajouter un anniversaire");
        addBirthdayButton.addActionListener(e -> ajouterAnniversaire());
        JButton logoutButton = new JButton("Se déconnecter");
        logoutButton.addActionListener(e -> {
            utilisateur = null;
            ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Login");
        });

        buttonPanel.add(viewEventsButton);
        buttonPanel.add(addPersonalRdvButton);
        buttonPanel.add(addMeetingButton);
        buttonPanel.add(addPeriodicEventButton);
        buttonPanel.add(addBirthdayButton);
        buttonPanel.add(logoutButton);

        menuPanel.add(buttonPanel, BorderLayout.CENTER);

        return menuPanel;
    }

    // Méthodes de gestion des événements

    private static boolean seConnecter(String username, String password) {
        // Logique de connexion
        if ("Roger".equals(username) && "Chat".equals(password)) {
            return true;
        } else if ("Pierre".equals(username) && "KiRouhl".equals(password)) {
            return true;
        }
        // Logique pour vérifier les utilisateurs créés (si nécessaire)
        for (int i = 0; i < nbUtilisateurs; i++) {
            if (utilisateurs[i].equals(username) && motsDePasses[i].equals(password)) {
                return true;
            }
        }
        return false;
    }

    private static boolean creerCompte(String username, String password) {
        // Logique pour créer un compte
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            utilisateurs[nbUtilisateurs] = username;
            motsDePasses[nbUtilisateurs] = password;
            nbUtilisateurs++;
            return true;
        }
        return false;
    }

    private static void afficherEvenements() {
        JFrame frame = new JFrame("Visualisation d'Événements");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("=== Menu de visualisation d'Événements ===", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        String[] options = {
            "1 - Afficher TOUS les événements",
            "2 - Afficher les événements d'un MOIS précis",
            "3 - Afficher les événements d'une SEMAINE précise",
            "4 - Afficher les événements d'un JOUR précis",
            "5 - Retour"
        };
        JList<String> list = new JList<>(options);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        JButton selectButton = new JButton("Sélectionner");
        selectButton.addActionListener(e -> {
            String choix = list.getSelectedValue().substring(0, 1);
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
                    frame.dispose();
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Option invalide.");
            }
        });
        panel.add(selectButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void afficherEvenementsParMois() {
        JFrame frame = new JFrame("Afficher les événements par mois");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel anneeLabel = new JLabel("Entrez l'année (AAAA) :");
        JTextField anneeField = new JTextField();
        JLabel moisLabel = new JLabel("Entrez le mois (1-12) :");
        JTextField moisField = new JTextField();

        panel.add(anneeLabel);
        panel.add(anneeField);
        panel.add(moisLabel);
        panel.add(moisField);

        JButton submitButton = new JButton("Soumettre");
        submitButton.addActionListener(e -> {
            int anneeMois = Integer.parseInt(anneeField.getText());
            int mois = Integer.parseInt(moisField.getText());

            LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
            LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

            afficherListe(calendar.eventsDansPeriode(new DateEvenement(debutMois), new DateEvenement(finMois)));
            frame.dispose();
        });

        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void afficherEvenementsParSemaine() {
        JFrame frame = new JFrame("Afficher les événements par semaine");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel anneeLabel = new JLabel("Entrez l'année (AAAA) :");
        JTextField anneeField = new JTextField();
        JLabel semaineLabel = new JLabel("Entrez le numéro de semaine (1-52) :");
        JTextField semaineField = new JTextField();

        panel.add(anneeLabel);
        panel.add(anneeField);
        panel.add(semaineLabel);
        panel.add(semaineField);

        JButton submitButton = new JButton("Soumettre");
        submitButton.addActionListener(e -> {
            int anneeSemaine = Integer.parseInt(anneeField.getText());
            int semaine = Integer.parseInt(semaineField.getText());

            LocalDateTime debutSemaine = LocalDateTime.now()
                    .withYear(anneeSemaine)
                    .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                    .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                    .withHour(0).withMinute(0);
            LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

            afficherListe(calendar.eventsDansPeriode(new DateEvenement(debutSemaine), new DateEvenement(finSemaine)));
            frame.dispose();
        });

        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void afficherEvenementsParJour() {
        JFrame frame = new JFrame("Afficher les événements par jour");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel anneeLabel = new JLabel("Entrez l'année (AAAA) :");
        JTextField anneeField = new JTextField();
        JLabel moisLabel = new JLabel("Entrez le mois (1-12) :");
        JTextField moisField = new JTextField();
        JLabel jourLabel = new JLabel("Entrez le jour (1-31) :");
        JTextField jourField = new JTextField();

        panel.add(anneeLabel);
        panel.add(anneeField);
        panel.add(moisLabel);
        panel.add(moisField);
        panel.add(jourLabel);
        panel.add(jourField);

        JButton submitButton = new JButton("Soumettre");
        submitButton.addActionListener(e -> {
            int anneeJour = Integer.parseInt(anneeField.getText());
            int moisJour = Integer.parseInt(moisField.getText());
            int jour = Integer.parseInt(jourField.getText());

            LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
            LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

            afficherListe(calendar.eventsDansPeriode(new DateEvenement(debutJour), new DateEvenement(finJour)));
            frame.dispose();
        });

        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void afficherListe(List<Event> events) {
        JFrame frame = new JFrame("Liste des événements");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Liste des événements", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        DefaultListModel<String> model = new DefaultListModel<>();
        events.forEach(e -> model.addElement(e.description()));
        JList<String> list = new JList<>(model);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void ajouterRdvPersonnel() {
        JFrame frame = new JFrame("Ajouter un rendez-vous personnel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 400);

        JPanel panel = new JPanel(new GridLayout(8, 2));

        panel.add(new JLabel("Titre de l'événement :"));
        JTextField titreField = new JTextField();
        panel.add(titreField);

        panel.add(new JLabel("Année (AAAA) :"));
        JTextField anneeField = new JTextField();
        panel.add(anneeField);

        panel.add(new JLabel("Mois (1-12) :"));
        JTextField moisField = new JTextField();
        panel.add(moisField);

        panel.add(new JLabel("Jour (1-31) :"));
        JTextField jourField = new JTextField();
        panel.add(jourField);

        panel.add(new JLabel("Heure début (0-23) :"));
        JTextField heureField = new JTextField();
        panel.add(heureField);

        panel.add(new JLabel("Minute début (0-59) :"));
        JTextField minuteField = new JTextField();
        panel.add(minuteField);

        panel.add(new JLabel("Durée (en minutes) :"));
        JTextField dureeField = new JTextField();
        panel.add(dureeField);

        JButton submitButton = new JButton("Ajouter");
        submitButton.addActionListener(e -> {
            String titre = titreField.getText();
            int annee = Integer.parseInt(anneeField.getText());
            int mois = Integer.parseInt(moisField.getText());
            int jour = Integer.parseInt(jourField.getText());
            int heure = Integer.parseInt(heureField.getText());
            int minute = Integer.parseInt(minuteField.getText());
            int duree = Integer.parseInt(dureeField.getText());

            TitreEvenement titreEvenement = new TitreEvenement(titre);
            ProprietaireEvenement proprietaireEvenement = new ProprietaireEvenement(utilisateur);
            DateEvenement dateEvenement = new DateEvenement(LocalDateTime.of(annee, mois, jour, heure, minute));
            DureeEvenement dureeEvenement = new DureeEvenement(duree);
            IdEvenement idEvenement = new IdEvenement(UUID.randomUUID());

            Event rdvPersonnel = new RdvPersonnel(idEvenement, titreEvenement, proprietaireEvenement, dateEvenement, dureeEvenement);

            calendar.ajouterEvent(rdvPersonnel);

            JOptionPane.showMessageDialog(frame, "Événement ajouté.");
            frame.dispose();
        });

        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }private static void ajouterReunion() {
        JFrame frame = new JFrame("Ajouter une réunion");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 400);

        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Titre de l'événement :"));
        JTextField titreField = new JTextField();
        panel.add(titreField);

        panel.add(new JLabel("Année (AAAA) :"));
        JTextField anneeField = new JTextField();
        panel.add(anneeField);

        panel.add(new JLabel("Mois (1-12) :"));
        JTextField moisField = new JTextField();
        panel.add(moisField);

        panel.add(new JLabel("Jour (1-31) :"));
        JTextField jourField = new JTextField();
        panel.add(jourField);

        panel.add(new JLabel("Heure début (0-23) :"));
        JTextField heureField = new JTextField();
        panel.add(heureField);

        panel.add(new JLabel("Minute début (0-59) :"));
        JTextField minuteField = new JTextField();
        panel.add(minuteField);

        panel.add(new JLabel("Durée (en minutes) :"));
        JTextField dureeField = new JTextField();
        panel.add(dureeField);

        panel.add(new JLabel("Lieu :"));
        JTextField lieuField = new JTextField();
        panel.add(lieuField);

        panel.add(new JLabel("Participants :"));
        JTextField participantsField = new JTextField();
        panel.add(participantsField);

        JButton submitButton = new JButton("Ajouter");
        submitButton.addActionListener(e -> {
            String titre = titreField.getText();
            int annee = Integer.parseInt(anneeField.getText());
            int mois = Integer.parseInt(moisField.getText());
            int jour = Integer.parseInt(jourField.getText());
            int heure = Integer.parseInt(heureField.getText());
            int minute = Integer.parseInt(minuteField.getText());
            int duree = Integer.parseInt(dureeField.getText());
            String lieu = lieuField.getText();
            List<String> participants = List.of(participantsField.getText().split(","));

            TitreEvenement titreEvenement = new TitreEvenement(titre);
            ProprietaireEvenement proprietaireEvenement = new ProprietaireEvenement(utilisateur);
            DateEvenement dateEvenement = new DateEvenement(LocalDateTime.of(annee, mois, jour, heure, minute));
            DureeEvenement dureeEvenement = new DureeEvenement(duree);
            LieuEvenement lieuEvenement = new LieuEvenement(lieu);
            ParticipantsEvenement participantsEvenement = new ParticipantsEvenement(participants);
            IdEvenement idEvenement = new IdEvenement(UUID.randomUUID());

            Event reunion = new Reunion(idEvenement, titreEvenement, proprietaireEvenement, dateEvenement, dureeEvenement, lieuEvenement, participantsEvenement);

            calendar.ajouterEvent(reunion);

            JOptionPane.showMessageDialog(frame, "Événement ajouté.");
            frame.dispose();
        });

        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }private static void ajouterEvenementPeriodique() {
        JFrame frame = new JFrame("Ajouter un événement périodique");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 400);

        JPanel panel = new JPanel(new GridLayout(8, 2));

        panel.add(new JLabel("Titre de l'événement :"));
        JTextField titreField = new JTextField();
        panel.add(titreField);

        panel.add(new JLabel("Année (AAAA) :"));
        JTextField anneeField = new JTextField();
        panel.add(anneeField);

        panel.add(new JLabel("Mois (1-12) :"));
        JTextField moisField = new JTextField();
        panel.add(moisField);

        panel.add(new JLabel("Jour (1-31) :"));
        JTextField jourField = new JTextField();
        panel.add(jourField);

        panel.add(new JLabel("Heure début (0-23) :"));
        JTextField heureField = new JTextField();
        panel.add(heureField);

        panel.add(new JLabel("Minute début (0-59) :"));
        JTextField minuteField = new JTextField();
        panel.add(minuteField);

        panel.add(new JLabel("Fréquence (en jours) :"));
        JTextField frequenceField = new JTextField();
        panel.add(frequenceField);

        JButton submitButton = new JButton("Ajouter");
        submitButton.addActionListener(e -> {
            String titre = titreField.getText();
            int annee = Integer.parseInt(anneeField.getText());
            int mois = Integer.parseInt(moisField.getText());
            int jour = Integer.parseInt(jourField.getText());
            int heure = Integer.parseInt(heureField.getText());
            int minute = Integer.parseInt(minuteField.getText());
            int frequence = Integer.parseInt(frequenceField.getText());

            TitreEvenement titreEvenement = new TitreEvenement(titre);
            ProprietaireEvenement proprietaireEvenement = new ProprietaireEvenement(utilisateur);
            DateEvenement dateEvenement = new DateEvenement(LocalDateTime.of(annee, mois, jour, heure, minute));
            DureeEvenement dureeEvenement = new DureeEvenement(0);
            FrequenceEvenement frequenceEvenement = new FrequenceEvenement(frequence);
            IdEvenement idEvenement = new IdEvenement(UUID.randomUUID());

            Event evenementPeriodique = new EvenementPeriodique(idEvenement, titreEvenement, proprietaireEvenement, dateEvenement, dureeEvenement, frequenceEvenement);

            calendar.ajouterEvent(evenementPeriodique);

            JOptionPane.showMessageDialog(frame, "Événement ajouté.");
            frame.dispose();
        });

        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }private static void ajouterAnniversaire() {
        JFrame frame = new JFrame("Ajouter un anniversaire");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 400);

        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Titre de l'événement :"));
        JTextField titreField = new JTextField();
        panel.add(titreField);

        panel.add(new JLabel("Année (AAAA) :"));
        JTextField anneeField = new JTextField();
        panel.add(anneeField);

        panel.add(new JLabel("Mois (1-12) :"));
        JTextField moisField = new JTextField();
        panel.add(moisField);

        panel.add(new JLabel("Jour (1-31) :"));
        JTextField jourField = new JTextField();
        panel.add(jourField);

        panel.add(new JLabel("Heure début (0-23) :"));
        JTextField heureField = new JTextField();
        panel.add(heureField);

        panel.add(new JLabel("Minute début (0-59) :"));
        JTextField minuteField = new JTextField();
        panel.add(minuteField);

        panel.add(new JLabel("Nom de la personne célébrée :"));
        JTextField personneField = new JTextField();
        panel.add(personneField);

        panel.add(new JLabel("Durée (en minutes) :"));
        JTextField dureeField = new JTextField();
        panel.add(dureeField);

        JButton submitButton = new JButton("Ajouter");
        submitButton.addActionListener(e -> {
            String titre = titreField.getText();
            int annee = Integer.parseInt(anneeField.getText());
            int mois = Integer.parseInt(moisField.getText());
            int jour = Integer.parseInt(jourField.getText());
            int heure = Integer.parseInt(heureField.getText());
            int minute = Integer.parseInt(minuteField.getText());
            String personne = personneField.getText();
            int duree = Integer.parseInt(dureeField.getText());

            TitreEvenement titreEvenement = new TitreEvenement(titre);
            ProprietaireEvenement proprietaireEvenement = new ProprietaireEvenement(utilisateur);
            DateEvenement dateEvenement = new DateEvenement(LocalDateTime.of(annee, mois, jour, heure, minute));
            DureeEvenement dureeEvenement = new DureeEvenement(duree);
            IdEvenement idEvenement = new IdEvenement(UUID.randomUUID());

            Event anniversaire = new EvenementAnniversaire(idEvenement, titreEvenement, proprietaireEvenement, dateEvenement, dureeEvenement, personne);

            calendar.ajouterEvent(anniversaire);

            JOptionPane.showMessageDialog(frame, "Événement Anniversaire ajouté.");
            frame.dispose();
        });

        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }private static void supprimerEvenement() {
        JFrame frame = new JFrame("Supprimer un événement");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        panel.add(new JLabel("Entrez l'UUID de l'événement à supprimer :"));
        JTextField uuidField = new JTextField();
        panel.add(uuidField);

        JButton submitButton = new JButton("Supprimer");
        submitButton.addActionListener(e -> {
            String uuidInput = uuidField.getText();

            try {
                UUID uuidToDelete = UUID.fromString(uuidInput);
                IdEvenement idEvenementtoDelete = new IdEvenement(uuidToDelete);
                boolean deleted = calendar.supprimerEventParId(idEvenementtoDelete);
                if (deleted) {
                    JOptionPane.showMessageDialog(frame, "Événement supprimé avec succès.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Événement introuvable avec cet UUID.");
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, "UUID invalide.");
            }
            frame.dispose();
        });

        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}