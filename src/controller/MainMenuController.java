package controller;

import service.*;

import java.util.Scanner;

public class MainMenuController {
    private final AnalyticsService analyticsService;
    private final DecisionService decisionService;
    private final IncidentService incidentService;
    private final ClientService clientService;
    private final ScoreService scoreService;
    private final CreditService creditService;
    private final PaiementService paiementService;

    private final Scanner scanner = new Scanner(System.in);
    public MainMenuController(
            AnalyticsService analyticsService,
            DecisionService decisionService,
            IncidentService incidentService,
            ClientService clientService,
            ScoreService scoreService,
            CreditService creditService,
            PaiementService paiementService
    ){
        this.analyticsService = analyticsService;
        this.decisionService = decisionService;
        this.incidentService = incidentService;
        this.clientService = clientService;
        this.scoreService = scoreService;
        this.creditService = creditService;
        this.paiementService = paiementService;
    }

    public void start() {
        int choice;

        do {
            printMainMenu();
            choice = getChoice();

            switch (choice) {
                case 1:
                    manageClients();
                    break;
                case 2:
                    calculateScore();
                    break;
                case 3:
                    manageCredits();
                    break;
                case 4:
                    managePayments();
                    break;
                case 5:
                    analytics();
                    break;
                case 6:
                    decisionEngine();
                    break;
                case 0:
                    System.out.println("Merci d’avoir utilisé le système. Au revoir.");
                    break;
                default:
                    System.out.println("Choix invalide, réessayez.");
            }

        } while (choice != 0);
    }

    private void printMainMenu() {
        System.out.println("\n===== Système de Scoring Micro-Crédit =====");
        System.out.println("1. Gestion des Clients");
        System.out.println("2. Calcul du Score");
        System.out.println("3. Gestion des Crédits");
        System.out.println("4. Gestion des Paiements / Historique");
        System.out.println("5. Analytics & Reporting");
        System.out.println("6. Moteur de Décision Automatique");
        System.out.println("0. Quitter");
        System.out.print("Votre choix: ");
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ================= MODULES =================

    private void manageClients() {
        System.out.println("\n--- Gestion des Clients ---");
        System.out.println("1. Créer un nouveau client");
        System.out.println("2. Modifier un client");
        System.out.println("3. Consulter un profil client");
        System.out.println("4. Supprimer un client");
        System.out.println("5. Lister tous les clients");
        System.out.print("Votre choix: ");
        int choice = getChoice();

        switch (choice) {
            case 1:
                clientService.createClient();
                break;
            case 2:
                clientService.updateClient();
                break;
            case 3:
                clientService.getClient();
                break;
            case 4:
                clientService.deleteClient();
                break;
            case 5:
                clientService.listClients();
                break;
            default:
                System.out.println("Choix invalide");
        }
    }

    private void calculateScore() {
        System.out.println("\n--- Calcul du Score ---");
        scoreService.calculateScore();
    }

    private void manageCredits() {
        System.out.println("\n--- Gestion des Crédits ---");
        creditService.manageCredits();
    }

    private void managePayments() {
        System.out.println("\n--- Historique des Paiements ---");
        paiementService.managePayments();
    }

    private void analytics() {
        System.out.println("\n--- Analytics & Reporting ---");
        analyticsService.runAnalytics();
    }

    private void decisionEngine() {
        System.out.println("\n--- Moteur de Décision Automatique ---");
        decisionService.decisionEngine();
    }
}
