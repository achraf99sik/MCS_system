package model;

import enums.DecisionCredit;
import enums.TypeCredit;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

public class Credit {
    private UUID id;
    private LocalDate dateDeCredit;
    private double montantDemande;
    private double montantOctroye;
    private double tauxInteret;
    private int dureeEnMois;
    private TypeCredit typecredit;
    private DecisionCredit decision;

    public Credit(UUID id, LocalDate dateDeCredit, double montantDemande, double montantOctroye, double tauxInteret, int dureeEnMois, TypeCredit typecredit, DecisionCredit decision) {
        this.id = id;
        this.dateDeCredit = dateDeCredit;
        this.montantDemande = montantDemande;
        this.montantOctroye = montantOctroye;
        this.tauxInteret = tauxInteret;
        this.dureeEnMois = dureeEnMois;
        this.typecredit = typecredit;
        this.decision = decision;
    }

    public Credit(LocalDate dateDeCredit, double montantDemande, double montantOctroye, double tauxInteret, int dureeEnMois, TypeCredit typecredit, DecisionCredit decision) {
        this.dateDeCredit = dateDeCredit;
        this.montantDemande = montantDemande;
        this.montantOctroye = montantOctroye;
        this.tauxInteret = tauxInteret;
        this.dureeEnMois = dureeEnMois;
        this.typecredit = typecredit;
        this.decision = decision;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateDeCredit() {
        return dateDeCredit;
    }

    public void setDateDeCredit(LocalDate dateDeCredit) {
        this.dateDeCredit = dateDeCredit;
    }

    public double getMontantDemande() {
        return montantDemande;
    }

    public void setMontantDemande(double montantDemande) {
        this.montantDemande = montantDemande;
    }

    public double getMontantOctroye() {
        return montantOctroye;
    }

    public void setMontantOctroye(double montantOctroye) {
        this.montantOctroye = montantOctroye;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public int getDureeEnMois() {
        return dureeEnMois;
    }

    public void setDureeEnMois(int dureeEnMois) {
        this.dureeEnMois = dureeEnMois;
    }

    public TypeCredit getTypecredit() {
        return typecredit;
    }

    public void setTypecredit(TypeCredit typecredit) {
        this.typecredit = typecredit;
    }

    public DecisionCredit getDecision() {
        return decision;
    }

    public void setDecision(DecisionCredit decision) {
        this.decision = decision;
    }
}
