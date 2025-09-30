package model;

import enums.StatutPaiement;

import java.time.LocalDate;

public class Echeance {
    private LocalDate dateEcheance;
    private double mensualite;
    private LocalDate dateDePaiement;
    private StatutPaiement statutPaiement;
}
