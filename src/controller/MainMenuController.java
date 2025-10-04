package controller;

import enums.*;
import model.Credit;
import model.Echeance;
import model.Personne;
import repository.CreditRepository;
import repository.EcheanceRepository;
import service.*;
import view.ClientView;
import view.CreditView;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainMenuController {
    private final CreditService creditService;
    private final PaiementService paiementService;
    private final ClientView clientView;
    private final CreditView creditView;

    private final Scanner scanner = new Scanner(System.in);
    public MainMenuController(
            CreditService creditService,
            PaiementService paiementService,
            ClientView clientView,
            CreditView creditView
    ){
        this.creditService = creditService;
        this.paiementService = paiementService;
        this.clientView = clientView;
        this.creditView = creditView;
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
                case 3:
                    manageCredits();
                    break;
                case 0:
                    System.out.println("Merci d'avoir utilise le systeme. Au revoir.");
                    break;
                default:
                    System.out.println("Choix invalide, reessayez.");
            }

        } while (choice != 0);
    }

    private void printMainMenu() {
        System.out.println("\n===== Systeme de Scoring Micro-Credit =====");
        System.out.println("1. Gestion des Clients");
        System.out.println("2. Calcul du Score");
        System.out.println("3. Gestion des Credits");
        System.out.println("4. Gestion des Paiements / Historique");
        System.out.println("5. Analytics & Reporting");
        System.out.println("6. Moteur de Decision Automatique");
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


    private void manageClients() {
        int choice;
        do {
            System.out.println("\n--- Gestion des Clients ---");
            System.out.println("1. Creer un nouveau client");
            System.out.println("2. Modifier informations client");
            System.out.println("3. Consulter profil client");
            System.out.println("4. Supprimer client");
            System.out.println("5. Lister tous les clients");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");
            choice = getChoice();

            switch (choice) {
                case 1:
                    clientView.createClient();
                    break;
                case 2:
                    clientView.editClient();
                    break;
                case 3:
                    clientView.viewClientProfile();
                    break;
                case 4:
                    clientView.deleteClient();
                    break;
                case 5:
                    clientView.listAllClients();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide, reessayez.");
            }
        } while (choice != 0);
    }

    private void manageCredits() {
        int choice;
        do {
            System.out.println("\n--- Gestion des Credits ---");
            System.out.println("1. Creer un nouveau credit");
            System.out.println("2. Modifier un credit");
            System.out.println("3. Consulter un credit");
            System.out.println("4. Supprimer un credit");
            System.out.println("5. Lister tous les credits");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");
            choice = getChoice();

            switch (choice) {
                case 1:
                    creditView.createCreditView();
                    break;
                case 2:
                    creditView.editCreditView();
                    break;
                case 3:
                    creditView.viewCreditProfileView();
                    break;
                case 4:
                    creditView.deleteCreditView();
                    break;
                case 5:
                    creditView.listAllCreditsView();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide, reessayez.");
            }
        } while (choice != 0);
    }
}
