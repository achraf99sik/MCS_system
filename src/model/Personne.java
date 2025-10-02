package model;

import enums.Activite;
import enums.SecteurActivite;
import enums.SituationFamiliale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

abstract public class Personne {
    protected UUID id;
    protected String nom;
    protected String prenom;
    protected LocalDate dateNaissance;
    protected String ville;
    protected Integer nombreEnfants;
    protected String investissement;
    protected String placement;
    protected SituationFamiliale situationFamiliale;
    protected LocalDateTime createdAt;
    protected Integer score;
    public Personne(
            UUID id,
            String nom,
            String prenom,
            LocalDate dateNaissance,
            String ville,
            Integer nombreEnfants,
            String investissement,
            String placement,
            SituationFamiliale situationFamiliale,
            LocalDateTime createdAt,
            Integer score
    ){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.nombreEnfants = nombreEnfants;
        this.investissement = investissement;
        this.placement = placement;
        this.situationFamiliale = situationFamiliale;
        this.createdAt = createdAt;
        this.score = score;
    }
    public Personne(
            String nom,
            String prenom,
            LocalDate dateNaissance,
            String ville,
            Integer nombreEnfants,
            String investissement,
            String placement,
            SituationFamiliale situationFamiliale,
            LocalDateTime createdAt,
            Integer score
    ){
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.nombreEnfants = nombreEnfants;
        this.investissement = investissement;
        this.placement = placement;
        this.situationFamiliale = situationFamiliale;
        this.createdAt = createdAt;
        this.score = score;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getNombreEnfants() {
        return nombreEnfants;
    }

    public void setNombreEnfants(Integer nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }

    public String getInvestissement() {
        return investissement;
    }

    public void setInvestissement(String investissement) {
        this.investissement = investissement;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public SituationFamiliale getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(SituationFamiliale situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
