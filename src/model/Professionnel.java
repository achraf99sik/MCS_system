package model;

import enums.Activite;
import enums.SecteurActivite;
import enums.SituationFamiliale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Professionnel extends Personne{
    private double revenu;
    private Integer immatriculationFiscale;
    private SecteurActivite secteurActivite;
    private Activite activite;
    public Professionnel(
            UUID id,
            double revenu,
            Integer immatriculationFiscale,
            SecteurActivite secteurActivite,
            Activite activite,
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
        this.revenu = revenu;
        this.immatriculationFiscale = immatriculationFiscale;
        this.secteurActivite = secteurActivite;
        this.activite = activite;
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
    public Professionnel(
            double revenu,
            Integer immatriculationFiscale,
            SecteurActivite secteurActivite,
            Activite activite,
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
        this.revenu = revenu;
        this.immatriculationFiscale = immatriculationFiscale;
        this.secteurActivite = secteurActivite;
        this.activite = activite;
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

    public double getRevenu() {
        return revenu;
    }

    public void setRevenu(double revenu) {
        this.revenu = revenu;
    }

    public Integer getImmatriculationFiscale() {
        return immatriculationFiscale;
    }

    public void setImmatriculationFiscale(Integer immatriculationFiscale) {
        this.immatriculationFiscale = immatriculationFiscale;
    }

    public SecteurActivite getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(SecteurActivite secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    @Override
    public String toString() {
        return "\nProfessionnel{" +
                "\n revenu=" + revenu +
                ",\n immatriculationFiscale=" + immatriculationFiscale +
                ",\n secteurActivite=" + secteurActivite +
                ",\n activite=" + activite +
                ",\n id=" + id +
                ",\n nom='" + nom + '\'' +
                ",\n prenom='" + prenom + '\'' +
                ",\n dateNaissance=" + dateNaissance +
                ",\n ville='" + ville + '\'' +
                ",\n nombreEnfants=" + nombreEnfants +
                ",\n investissement='" + investissement + '\'' +
                ",\n placement='" + placement + '\'' +
                ",\n situationFamiliale=" + situationFamiliale +
                ",\n createdAt=" + createdAt +
                ",\n score=" + score +
                '}';
    }
}
