package controller;

import enums.*;
import model.Credit;
import model.Echeance;
import model.Personne;
import repository.CreditRepository;
import repository.EcheanceRepository;
import service.*;
import view.ClientView;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainMenuController {
    private final AnalyticsService analyticsService;
    private final DecisionService decisionService;
    private final IncidentService incidentService;
    private final ScoreService scoreService;
    private final CreditService creditService;
    private final PaiementService paiementService;
    private final ClientView clientView;
    private final ClientService clientService;

    private final Scanner scanner = new Scanner(System.in);
    public MainMenuController(
            AnalyticsService analyticsService,
            DecisionService decisionService,
            IncidentService incidentService,
            ScoreService scoreService,
            CreditService creditService,
            PaiementService paiementService,
            ClientView clientView,
            ClientService clientService
    ){
        this.analyticsService = analyticsService;
        this.decisionService = decisionService;
        this.incidentService = incidentService;
        this.scoreService = scoreService;
        this.creditService = creditService;
        this.paiementService = paiementService;
        this.clientView = clientView;
        this.clientService = clientService;
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
    private void calculateScore() {
        int choice;
        do {
            System.out.println("\n--- Calcul du Score ---");
            System.out.println("1. Calculer le score d'un client");
            System.out.println("2. Calculer la capacite d'emprunt");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");
            choice = getChoice();

            switch (choice) {
                case 1:
                    calculateScoreView();
                    break;
                case 2:
                    calculateLoanCapacityView();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide, reessayez.");
            }
        } while (choice != 0);
    }

    private void calculateScoreView() {
        System.out.println("\n--- Calculer le score d'un client ---");
        System.out.print("ID du client: ");
        String clientIdStr = scanner.nextLine();
        System.out.println("Fonctionnalite non implementee.");
    }

    private void calculateLoanCapacityView() {
        System.out.println("\n--- Calculer la capacite d'emprunt ---");
        System.out.print("ID du client: ");
        String clientIdStr = scanner.nextLine();
        System.out.println("Fonctionnalite non implementee.");
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
                    createCreditView();
                    break;
                case 2:
                    editCreditView();
                    break;
                case 3:
                    viewCreditProfileView();
                    break;
                case 4:
                    deleteCreditView();
                    break;
                case 5:
                    listAllCreditsView();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide, reessayez.");
            }
        } while (choice != 0);
    }

    private void createCreditView() {
        System.out.println("\n--- Creer un nouveau credit ---");
        System.out.print("ID du client: ");
        String clientIdStr = scanner.nextLine();
        System.out.print("Date de credit (YYYY-MM-DD): ");
        String dateCreditStr = scanner.nextLine();
        System.out.print("Montant demande: ");
        double montantDemande = Double.parseDouble(scanner.nextLine());
        System.out.print("Montant octroye: ");
        double montantOctroye = Double.parseDouble(scanner.nextLine());
        System.out.print("Taux d'interet: ");
        double tauxInteret = Double.parseDouble(scanner.nextLine());
        System.out.print("Duree en mois: ");
        int dureeEnMois = Integer.parseInt(scanner.nextLine());
        System.out.print("Type de credit (CONSOMMATION, IMMOBILIER, AUTO): ");
        String typeCreditStr = scanner.nextLine();

        try {
            UUID clientId = UUID.fromString(clientIdStr);
            Personne client = clientService.getClient(clientId);
            TypeCredit typeCredit = TypeCredit.valueOf(typeCreditStr.toUpperCase());
            DecisionCredit decisionCredit = decisionService.decisionEngine(client);

            creditService.createCredit(new Credit(
                    UUID.randomUUID(),
                    LocalDate.parse(dateCreditStr),
                    montantDemande,
                    montantOctroye,
                    tauxInteret,
                    dureeEnMois,
                    typeCredit,
                    decisionCredit), clientId
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur de format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Une erreur inattendue est survenue: " + e.getMessage());
        }
    }

    private void editCreditView() {
        System.out.println("\n--- Modifier un credit ---");
        System.out.print("ID du credit a modifier: ");
        String creditIdStr = scanner.nextLine();

        try {
            UUID creditId = UUID.fromString(creditIdStr);
            Credit existingCredit = creditService.getCredit(creditId);

            if (existingCredit != null) {
                System.out.println("Credit actuel: " + existingCredit);

                System.out.print("Nouvelle date de credit (YYYY-MM-DD, laisser vide pour ne pas changer): ");
                String newDateCreditStr = scanner.nextLine();
                if (!newDateCreditStr.isEmpty()) {
                    existingCredit.setDateDeCredit(LocalDate.parse(newDateCreditStr));
                }

                System.out.print("Nouveau montant demande (laisser vide pour ne pas changer): ");
                String newMontantDemandeStr = scanner.nextLine();
                if (!newMontantDemandeStr.isEmpty()) {
                    existingCredit.setMontantDemande(Double.parseDouble(newMontantDemandeStr));
                }

                System.out.print("Nouveau montant octroye (laisser vide pour ne pas changer): ");
                String newMontantOctroyeStr = scanner.nextLine();
                if (!newMontantOctroyeStr.isEmpty()) {
                    existingCredit.setMontantOctroye(Double.parseDouble(newMontantOctroyeStr));
                }

                System.out.print("Nouveau taux d'interet (laisser vide pour ne pas changer): ");
                String newTauxInteretStr = scanner.nextLine();
                if (!newTauxInteretStr.isEmpty()) {
                    existingCredit.setTauxInteret(Double.parseDouble(newTauxInteretStr));
                }

                System.out.print("Nouvelle duree en mois (laisser vide pour ne pas changer): ");
                String newDureeEnMoisStr = scanner.nextLine();
                if (!newDureeEnMoisStr.isEmpty()) {
                    existingCredit.setDureeEnMois(Integer.parseInt(newDureeEnMoisStr));
                }

                System.out.print("Nouveau type de credit (CONSOMMATION, IMMOBILIER, AUTO, laisser vide pour ne pas changer): ");
                String newTypeCreditStr = scanner.nextLine();
                if (!newTypeCreditStr.isEmpty()) {
                    existingCredit.setTypecredit(TypeCredit.valueOf(newTypeCreditStr.toUpperCase()));
                }

                System.out.print("Nouvelle decision (ACCORDIMMEDIAT, ETUDEMANUELLE, REFUS_AUTOMATIQUE, laisser vide pour ne pas changer): ");
                String newDecisionCreditStr = scanner.nextLine();
                if (!newDecisionCreditStr.isEmpty()) {
                    existingCredit.setDecision(DecisionCredit.valueOf(newDecisionCreditStr.toUpperCase()));
                }
                creditService.updateCredit(existingCredit, existingCredit.getId());
            } else {
                System.out.println("Credit avec l'ID " + creditIdStr + " non trouve.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur de format d'entree: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Une erreur inattendue est survenue: " + e.getMessage());
        }
    }

    private void viewCreditProfileView() {
        System.out.println("\n--- Consulter un credit ---");
        System.out.print("ID du credit a consulter: ");
        String creditIdStr = scanner.nextLine();

        try {
            UUID creditId = UUID.fromString(creditIdStr);
            Credit credit = creditService.getCredit(creditId);

            if (credit != null) {
                System.out.println("\nDetails du credit:");
                System.out.println("ID: " + credit.getId());
                System.out.println("Date Credit: " + credit.getDateDeCredit());
                System.out.println("Montant Demande: " + credit.getMontantDemande());
                System.out.println("Montant Octroye: " + credit.getMontantOctroye());
                System.out.println("Taux Interet: " + credit.getTauxInteret());
                System.out.println("Duree en Mois: " + credit.getDureeEnMois());
                System.out.println("Type Credit: " + credit.getTypecredit());
                System.out.println("Decision Credit: " + credit.getDecision());
            } else {
                System.out.println("Credit avec l'ID " + creditIdStr + " non trouve.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur de format d'ID de credit: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Une erreur inattendue est survenue: " + e.getMessage());
        }
    }

    private void deleteCreditView() {
        System.out.println("\n--- Supprimer un credit ---");
        System.out.print("ID du credit a supprimer: ");
        String creditIdStr = scanner.nextLine();

        try {
            UUID creditId = UUID.fromString(creditIdStr);
            creditService.deleteCredit(creditId);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur de format d'ID de credit: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Une erreur inattendue est survenue: " + e.getMessage());
        }
    }

    private void listAllCreditsView() {
        System.out.println("\n--- Liste de tous les credits ---");
        List<Credit> credits = creditService.listCredits();

        if (credits.isEmpty()) {
            System.out.println("Aucun credit trouve.");
        } else {
            for (Credit credit : credits) {
                System.out.println("------------------------------------");
                System.out.println("ID: " + credit.getId());
                System.out.println("Date Credit: " + credit.getDateDeCredit());
                System.out.println("Montant Demande: " + credit.getMontantDemande());
                System.out.println("Montant Octroye: " + credit.getMontantOctroye());
                System.out.println("Taux Interet: " + credit.getTauxInteret());
                System.out.println("Duree en Mois: " + credit.getDureeEnMois());
                System.out.println("Type Credit: " + credit.getTypecredit());
                System.out.println("Decision Credit: " + credit.getDecision());
            }
            System.out.println("------------------------------------");
        }
    }

    private void managePayments() {
        int choice;
        do {
            System.out.println("\n--- Historique des Paiements ---");
            System.out.println("1. Generer les echeances");
            System.out.println("2. Classer les paiements");
            System.out.println("3. Gerer les regularisations d'impayes");
            System.out.println("4. Appliquer les penalites/bonus");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");
            choice = getChoice();

            switch (choice) {
                case 1:
                    generateEcheancesView();
                    break;
                case 2:
                    classifyPaymentView();
                    break;
                case 3:
                    manageUnpaidRegularizationsView();
                    break;
                case 4:
                    applyPenaltiesBonusesView();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide, reessayez.");
            }
        } while (choice != 0);
    }

    private void generateEcheancesView() {
        System.out.println("\n--- Generer les echeances ---");
        System.out.print("ID du credit: ");
        String creditIdStr = scanner.nextLine();
        Credit credit = creditService.getCredit(UUID.fromString(creditIdStr));
        List<Echeance> echeances= paiementService.generateEcheances(credit);
        echeances.forEach(echeance -> new EcheanceRepository().create(echeance));
    }

    private void classifyPaymentView() {
        System.out.println("\n--- Classer les paiements ---");
        System.out.print("ID de l'echeance: ");
        String echeanceIdStr = scanner.nextLine();
        System.out.print("Statut du paiement (PAYEATEMPS, ENRETARD, PAYEENRETARD, IMPAYENONREGLE, IMPAYEREGLE): ");
        String statutPaiementStr = scanner.nextLine();
        System.out.println("Fonctionnalite non implementee.");
    }

    private void manageUnpaidRegularizationsView() {
        System.out.println("\n--- Gerer les regularisations d'impayes ---");
        System.out.print("ID de l'incident: ");
        String incidentIdStr = scanner.nextLine();
        System.out.println("Fonctionnalite non implementee.");
    }

    private void applyPenaltiesBonusesView() {
        System.out.println("\n--- Appliquer les penalites/bonus ---");
        System.out.print("ID du client: ");
        String clientIdStr = scanner.nextLine();
        System.out.println("Fonctionnalite non implementee.");
    }

    private void analytics() {
        int choice;
        do {
            System.out.println("\n--- Analytics & Reporting ---");
            System.out.println("1. Recherche clients eligibles credit immobilier");
            System.out.println("2. Clients a risque necessitant suivi et accompagnement");
            System.out.println("3. Tri par score, revenus, anciennete");
            System.out.println("4. Repartition par type d'emploi");
            System.out.println("5. Usage Business: campagne publicitaire credit de consommation");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");
            choice = getChoice();

            switch (choice) {
                case 1:
                    searchEligibleClientsView();
                    break;
                case 2:
                    listRiskyClientsView();
                    break;
                case 3:
                    sortClientsView();
                    break;
                case 4:
                    distributionByEmploymentTypeView();
                    break;
                case 5:
                    consumerCreditCampaignView();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide, reessayez.");
            }
        } while (choice != 0);
    }

    private void searchEligibleClientsView() {
        System.out.println("\n--- Recherche clients eligibles credit immobilier ---");
        System.out.println("Fonctionnalite non implementee.");
    }

    private void listRiskyClientsView() {
        System.out.println("\n--- Clients a risque necessitant suivi et accompagnement ---");
        System.out.println("Fonctionnalite non implementee.");
    }

    private void sortClientsView() {
        System.out.println("\n--- Tri par score, revenus, anciennete ---");
        System.out.println("Fonctionnalite non implementee.");
    }

    private void distributionByEmploymentTypeView() {
        System.out.println("\n--- Repartition par type d'emploi ---");
        System.out.println("Fonctionnalite non implementee.");
    }

    private void consumerCreditCampaignView() {
        System.out.println("\n--- Usage Business: campagne publicitaire credit de consommation ---");
        System.out.println("Fonctionnalite non implementee.");
    }

    private void decisionEngine() {
        int choice;
        do {
            System.out.println("\n--- Moteur de Decision Automatique ---");
            System.out.println("1. Approuver un credit");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");
            choice = getChoice();

            switch (choice) {
                case 1:
                    approveCreditView();
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide, reessayez.");
            }
        } while (choice != 0);
    }

    private void approveCreditView() {
        System.out.println("\n--- Approuver un credit ---");
        System.out.print("ID du credit a approuver: ");
        String creditIdStr = scanner.nextLine();
        System.out.println("Fonctionnalite non implementee.");
    }
}
