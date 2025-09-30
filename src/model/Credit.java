package model;

import enums.DecisionCredit;
import enums.TypeCredit;

import java.time.LocalDate;
import java.time.Month;

public class Credit {
    private LocalDate dateDeCredit;
    private double montantDemande;
    private double montantOctroye;
    private double tauxInteret;
    private int dureeEnMois;
    private TypeCredit typecredit;
    private DecisionCredit decision;

}
