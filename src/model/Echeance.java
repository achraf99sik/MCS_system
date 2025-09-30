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
}
