package view;

import enums.DecisionCredit;
import enums.TypeCredit;
import model.Credit;
import model.Personne;
import service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CreditView {
    private final DecisionService decisionService;
    private final CreditService creditService;
    private final ClientService clientService;

    private final Scanner scanner = new Scanner(System.in);
    public CreditView(
            DecisionService decisionService,
            CreditService creditService,
            ClientService clientService
    ){
        this.decisionService = decisionService;
        this.creditService = creditService;
        this.clientService = clientService;
    }
    public void createCreditView() {
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

    public void editCreditView() {
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

    public void viewCreditProfileView() {
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

    public void deleteCreditView() {
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

    public void listAllCreditsView() {
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
}
