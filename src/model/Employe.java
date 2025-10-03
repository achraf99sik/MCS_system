package model;

import enums.Secteur;
import enums.SituationFamiliale;
import enums.TypeContrat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Employe extends Personne{
    private double salaire;
    private Integer Anciennete;
    private String poste;
    private TypeContrat typecontrat;
    private Secteur secteur;

    public Employe(
            UUID id,
            String nom,
            String prenom,
            LocalDate dateNaissance,
            String ville,
            Integer nombreEnfants,
            boolean investissement,
            boolean placement,
            SituationFamiliale situationFamiliale,
            LocalDateTime createdAt,
            Integer score,
            double salaire,
            Integer anciennete,
            String poste,
            TypeContrat typecontrat,
            Secteur secteur
    ) {
        super(id, nom, prenom, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale, createdAt, score);
        this.salaire = salaire;
        this.Anciennete = anciennete;
        this.poste = poste;
        this.typecontrat = typecontrat;
        this.secteur = secteur;
    }

    public Employe(
            String nom,
            String prenom,
            java.time.LocalDate dateNaissance,
            String ville,
            Integer nombreEnfants,
            boolean investissement,
            boolean placement,
            enums.SituationFamiliale situationFamiliale,
            java.time.LocalDateTime createdAt,
            Integer score,
            double salaire,
            Integer anciennete,
            String poste,
            TypeContrat typecontrat,
            Secteur secteur
    ) {
        super(nom, prenom, dateNaissance, ville, nombreEnfants, investissement, placement, situationFamiliale, createdAt, score);
        this.salaire = salaire;
        this.Anciennete = anciennete;
        this.poste = poste;
        this.typecontrat = typecontrat;
        this.secteur = secteur;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public int getAnciennete() {
        return Anciennete;
    }

    public void setAnciennete(int anciennete) {
        Anciennete = anciennete;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public TypeContrat getTypecontrat() {
        return typecontrat;
    }

    public void setTypecontrat(TypeContrat typecontrat) {
        this.typecontrat = typecontrat;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "\n nom= " + nom +
                ",\n salaire= " + salaire +
                ",\n Anciennete= " + Anciennete +
                ",\n poste= " + poste +
                ",\n typecontrat= " + typecontrat +
                ",\n secteur= " + secteur +
                ",\n id= " + id +
                ",\n prenom= " + prenom +
                ",\n dateNaissance= " + dateNaissance +
                ",\n ville= " + ville +
                ",\n nombreEnfants= " + nombreEnfants +
                ",\n investissement= " + investissement +
                ",\n placement= " + placement +
                ",\n situationFamiliale= " + situationFamiliale +
                ",\n createdAt= " + createdAt +
                ",\n score= " + score +
                "\n}";
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }
}
