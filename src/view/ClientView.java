package view;

import enums.*;
import model.Employe;
import model.Personne;
import model.Professionnel;
import service.ClientService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class ClientView {
    private final Scanner scanner = new Scanner(System.in);
    private final ClientService clientService;
    public ClientView(ClientService clientService){
        this.clientService = clientService;
    }
    public void createClient() {
        System.out.println("\n--- Creer un nouveau client ---");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prenom: ");
        String prenom = scanner.nextLine();
        System.out.print("Date de naissance (YYYY-MM-DD): ");
        String dateNaissanceStr = scanner.nextLine();
        System.out.print("Ville: ");
        String ville = scanner.nextLine();
        System.out.print("Nombre d'enfants: ");
        int nombreEnfants = Integer.parseInt(scanner.nextLine());
        System.out.print("Investissement: ");
        String investissement = scanner.nextLine();
        System.out.print("Placement: ");
        String placement = scanner.nextLine();
        System.out.print("Situation familiale (CELIBATAIRE, MARIE, DIVORCE, VEUT): ");
        String situationFamilialeStr = scanner.nextLine();
        System.out.print("etes-vous un employe ou un professionnel(1. employe, 2. professionnel): ");
        Integer typeClinet = scanner.nextInt();
        switch (typeClinet) {
            case 1:
                System.out.print("votre salaire: ");
                double salaire = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("votre Anciennete: ");
                Integer anciennete = scanner.nextInt();
                scanner.nextLine();
                System.out.print("votre poste: ");
                String poste = scanner.nextLine();
                System.out.print("votre secteur (PUBLIC, GRANDE_ENTREPRISE, PME, AGRICULTURE, COMMERCE, SERVICES, CONSTRUCTION, AUTRE): ");
                String secteur = scanner.nextLine();
                System.out.print("votre type de contrat (CDI, CDD, STAGE, FREELANCE): ");
                String typeContrat = scanner.nextLine();
                Employe employe = new Employe(
                        UUID.randomUUID(),
                        nom,
                        prenom,
                        LocalDate.parse(dateNaissanceStr),
                        ville,
                        nombreEnfants,
                        investissement,
                        placement,
                        SituationFamiliale.valueOf(situationFamilialeStr.toUpperCase()),
                        LocalDateTime.now(),
                        60,
                        salaire,
                        anciennete,
                        poste,
                        TypeContrat.valueOf(typeContrat.toUpperCase()),
                        Secteur.valueOf(secteur.toUpperCase())
                );

                clientService.createClient(employe);
                break;
            case 2:
                System.out.print("votre revenu: ");
                double revenu = scanner.nextDouble();
                System.out.print("votre immatriculation fiscale: ");
                Integer immatriculationFiscale = scanner.nextInt();
                System.out.print("votre secteur d'activite(AGRICULTURE, SERVICE, COMMERCE, CONSTRUCTION): ");
                String secteurActivite = scanner.nextLine();
                System.out.print("votre activite (AVOCAT, MECANICIEN, AGRICULTEUR, COMMERCANT, MACON): ");
                String activite = scanner.nextLine();
                Professionnel professionnel = new Professionnel(
                        UUID.randomUUID(),
                        revenu,
                        immatriculationFiscale,
                        Activite.valueOf(secteurActivite.toUpperCase()).getSecteur(),
                        Activite.valueOf(activite.toUpperCase()),
                        nom,
                        prenom,
                        LocalDate.parse(dateNaissanceStr),
                        ville,
                        nombreEnfants,
                        investissement,
                        placement,
                        SituationFamiliale.valueOf(situationFamilialeStr.toUpperCase()),
                        LocalDateTime.now(),
                        60
                );
                clientService.createClient(professionnel);
                break;
            default:
                System.out.println("Choix invalide, reessayez");
        }
        System.out.println("Client cree avec succes.");
    }

    public void editClient() {
        System.out.println("\n--- Modifier informations client ---");
        System.out.print("ID du client a modifier: ");
        String clientIdStr = scanner.nextLine();

        try {
            UUID clientId = UUID.fromString(clientIdStr);
            Personne client = clientService.getClient(clientId);

            if (client == null) {
                System.out.println("Client introuvable avec cet ID.");
                return;
            }

            System.out.println("Modification du client: " + client.getNom() + " " + client.getPrenom());

            System.out.print("Nom (" + client.getNom() + "): ");
            String nom = scanner.nextLine();
            if (nom.isEmpty()) nom = client.getNom();

            System.out.print("Prenom (" + client.getPrenom() + "): ");
            String prenom = scanner.nextLine();
            if (prenom.isEmpty()) prenom = client.getPrenom();

            System.out.print("Date de naissance (" + client.getDateNaissance() + ") [YYYY-MM-DD]: ");
            String dateNaissanceStr = scanner.nextLine();
            LocalDate dateNaissance = dateNaissanceStr.isEmpty()
                    ? client.getDateNaissance()
                    : LocalDate.parse(dateNaissanceStr);

            System.out.print("Ville (" + client.getVille() + "): ");
            String ville = scanner.nextLine();
            if (ville.isEmpty()) ville = client.getVille();

            System.out.print("Nombre d'enfants (" + client.getNombreEnfants() + "): ");
            String enfantsStr = scanner.nextLine();
            int nombreEnfants = enfantsStr.isEmpty()
                    ? client.getNombreEnfants()
                    : Integer.parseInt(enfantsStr);

            System.out.print("Investissement (" + client.getInvestissement() + "): ");
            String investissement = scanner.nextLine();
            if (investissement.isEmpty()) investissement = client.getInvestissement();

            System.out.print("Placement (" + client.getPlacement() + "): ");
            String placement = scanner.nextLine();
            if (placement.isEmpty()) placement = client.getPlacement();

            System.out.print("Situation familiale (" + client.getSituationFamiliale() + "): ");
            String situationFamilialeStr = scanner.nextLine();
            SituationFamiliale situationFamiliale = situationFamilialeStr.isEmpty()
                    ? client.getSituationFamiliale()
                    : SituationFamiliale.valueOf(situationFamilialeStr.toUpperCase());

            if (client instanceof Employe) {
                Employe emp = (Employe) client;

                System.out.print("Salaire (" + emp.getSalaire() + "): ");
                String salaireStr = scanner.nextLine();
                double salaire = salaireStr.isEmpty() ? emp.getSalaire() : Double.parseDouble(salaireStr);

                System.out.print("Anciennete (" + emp.getAnciennete() + "): ");
                String ancienneteStr = scanner.nextLine();
                int anciennete = ancienneteStr.isEmpty() ? emp.getAnciennete() : Integer.parseInt(ancienneteStr);

                System.out.print("Poste (" + emp.getPoste() + "): ");
                String poste = scanner.nextLine();
                if (poste.isEmpty()) poste = emp.getPoste();

                System.out.print("Type de contrat (" + emp.getTypecontrat() + "): ");
                String typeContratStr = scanner.nextLine();
                TypeContrat typeContrat = typeContratStr.isEmpty()
                        ? emp.getTypecontrat()
                        : TypeContrat.valueOf(typeContratStr.toUpperCase());

                System.out.print("Secteur (" + emp.getSecteur() + "): ");
                String secteurStr = scanner.nextLine();
                Secteur secteur = secteurStr.isEmpty()
                        ? emp.getSecteur()
                        : Secteur.valueOf(secteurStr.toUpperCase());

                Employe updated = new Employe(
                        nom, prenom, dateNaissance, ville, nombreEnfants,
                        investissement, placement, situationFamiliale, emp.getCreatedAt(),
                        emp.getScore(), salaire, anciennete, poste, typeContrat, secteur
                );

                clientService.updateClient(updated, emp.getId());
            }

            else if (client instanceof Professionnel) {
                Professionnel pro = (Professionnel) client;

                System.out.print("Revenu (" + pro.getRevenu() + "): ");
                String revenuStr = scanner.nextLine();
                double revenu = revenuStr.isEmpty() ? pro.getRevenu() : Double.parseDouble(revenuStr);

                System.out.print("Immatriculation fiscale (" + pro.getImmatriculationFiscale() + "): ");
                String immatStr = scanner.nextLine();
                int immatriculationFiscale = immatStr.isEmpty()
                        ? pro.getImmatriculationFiscale()
                        : Integer.parseInt(immatStr);

                System.out.print("Activite (" + pro.getActivite() + "): ");
                String activiteStr = scanner.nextLine();
                Activite activite = activiteStr.isEmpty()
                        ? pro.getActivite()
                        : Activite.valueOf(activiteStr.toUpperCase());

                SecteurActivite secteurActivite = activite.getSecteur();

                Professionnel updated = new Professionnel(
                        revenu, immatriculationFiscale, secteurActivite, activite,
                        nom, prenom, dateNaissance, ville, nombreEnfants, investissement,
                        placement, situationFamiliale, pro.getCreatedAt(), pro.getScore()
                );

                clientService.updateClient(updated, pro.getId());
            }

            System.out.println("Client mis a jour avec succes.");

        } catch (IllegalArgumentException e) {
            System.out.println("Format ID invalide ou données incorrectes: " + e.getMessage());
        }
    }
    public void viewClientProfile() {
        System.out.println("\n--- Consulter profil client ---");
        System.out.print("ID du client a consulter: ");
        String clientIdStr = scanner.nextLine();
        UUID clientId = UUID.fromString(clientIdStr);
        System.out.println(clientService.getClient(clientId));
    }

    public void deleteClient() {
        System.out.println("\n--- Supprimer client ---");
        System.out.print("ID du client a supprimer: ");
        String clientIdStr = scanner.nextLine();
        UUID clientId = UUID.fromString(clientIdStr);
        clientService.deleteClient(clientId);
    }

    public void listAllClients() {
        System.out.println("\n--- Liste de tous les clients ---");
        for (Personne p : clientService.listClients()) {
            if (p instanceof Employe) {
                System.out.println((Employe) p);
            } else if (p instanceof Professionnel) {
                System.out.println((Professionnel) p);
            }
        }
    }

}
