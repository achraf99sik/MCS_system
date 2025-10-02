package model;

import enums.TypeIncident;

import java.time.LocalDate;
import java.util.UUID;

public class Incident {
    private UUID id;
    private LocalDate dateIncident;
    private Echeance echeance;
    private int score;
    private TypeIncident typeIncident;

    public Incident(UUID id, LocalDate dateIncident, Echeance echeance, int score, TypeIncident typeIncident) {
        this.id = id;
        this.dateIncident = dateIncident;
        this.echeance = echeance;
        this.score = score;
        this.typeIncident = typeIncident;
    }

    public Incident(LocalDate dateIncident, Echeance echeance, int score, TypeIncident typeIncident) {
        this.dateIncident = dateIncident;
        this.echeance = echeance;
        this.score = score;
        this.typeIncident = typeIncident;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateIncident() {
        return dateIncident;
    }

    public void setDateIncident(LocalDate dateIncident) {
        this.dateIncident = dateIncident;
    }

    public Echeance getEcheance() {
        return echeance;
    }

    public void setEcheance(Echeance echeance) {
        this.echeance = echeance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public TypeIncident getTypeIncident() {
        return typeIncident;
    }

    public void setTypeIncident(TypeIncident typeIncident) {
        this.typeIncident = typeIncident;
    }
}