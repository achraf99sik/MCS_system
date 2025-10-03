package service;

import enums.Secteur;
import enums.SecteurActivite;
import enums.TypeContrat;
import helper.DateHelper;
import model.Employe;
import model.Personne;
import model.Professionnel;

import java.util.HashMap;

public class ScoreService {
    public static Integer initial(Personne personne){
        int score = 0;
        int age = new DateHelper().getAge(personne.getDateNaissance());
        int childrens = personne.getNombreEnfants();
        if (age >= 18 && age <= 25) {
            score += 4;
        } else if (age >= 26 && age <= 35) {
            score += 8;
        } else if (age >= 36 && age <= 55) {
            score += 10;
        } else if (age > 55) {
            score += 6;
        }
        if (childrens == 0){
            score+=2;
        } else if (childrens > 0 && childrens <= 2) {
            score++;
        }
        if (personne instanceof Employe) {
            Employe client = (Employe) personne;
            if (client.getTypecontrat() == TypeContrat.CDI && client.getSecteur() == Secteur.PUBLIC){
                score+=25;
            } else if (client.getTypecontrat() == TypeContrat.CDI && client.getSecteur() == Secteur.GRANDE_ENTREPRISE) {
                score+=15;
            } else if (client.getTypecontrat() == TypeContrat.CDI && client.getSecteur() == Secteur.PME) {
                score+=12;
            } else if (client.getTypecontrat() == TypeContrat.CDD) {
                score+=10;
            } else if (client.getTypecontrat() == TypeContrat.FREELANCE) {
                score+=12;
            }
            if (client.getAnciennete() >= 5){
                score += 5;
            } else if (client.getAnciennete() < 5 && client.getAnciennete() > 2) {
                score += 3;
            } else if (client.getAnciennete() < 2 && client.getAnciennete() > 1 ) {
                score += 1;
            }
            score += revenuScore(client.getSalaire());
        } else if (personne instanceof Professionnel) {
            Professionnel client = (Professionnel) personne;
            if (client.getSecteurActivite() == SecteurActivite.SERVICE){
                score+=12;
            } else {
                score+=18;
            }
            score += revenuScore(client.getRevenu());
            if (age >= 18 && age <= 25) {
                score += 4;
            } else if (age >= 26 && age <= 35) {
                score += 8;
            } else if (age >= 36 && age <= 55) {
                score += 10;
            } else if (age > 55) {
                score += 6;
            }
        }
        return score;
    }
    private static int revenuScore(double montant) {
        if (montant >= 10000) return 30;
        if (montant >= 8000) return 25;
        if (montant >= 5000) return 20;
        if (montant >= 3000) return 15;
        return 10;
    }
    public void calculateScore(){}
}
