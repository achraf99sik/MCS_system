package model;

import enums.StatutPaiement;

import java.time.LocalDate;
import java.util.UUID;

public class Echeance {
    private UUID id;
    private LocalDate dateEcheance;
    private double mensualite;
    private LocalDate dateDePaiement;
    private StatutPaiement statutPaiement;

    public Echeance(UUID id, LocalDate dateEcheance, double mensualite, LocalDate dateDePaiement, StatutPaiement statutPaiement) {
        this.id = id;
        this.dateEcheance = dateEcheance;
        this.mensualite = mensualite;
        this.dateDePaiement = dateDePaiement;
        this.statutPaiement = statutPaiement;
    }

    public Echeance(LocalDate dateEcheance, double mensualite, LocalDate dateDePaiement, StatutPaiement statutPaiement) {
        this.dateEcheance = dateEcheance;
        this.mensualite = mensualite;
        this.dateDePaiement = dateDePaiement;
        this.statutPaiement = statutPaiement;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public double getMensualite() {
        return mensualite;
    }

    public void setMensualite(double mensualite) {
        this.mensualite = mensualite;
    }

    public LocalDate getDateDePaiement() {
        return dateDePaiement;
    }

    public void setDateDePaiement(LocalDate dateDePaiement) {
        this.dateDePaiement = dateDePaiement;
    }

    public StatutPaiement getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(StatutPaiement statutPaiement) {
        this.statutPaiement = statutPaiement;
    }
}
