package service;

import enums.DecisionCredit;
import enums.TypeIncident;
import model.Echeance;
import model.Incident;
import model.Personne;

import java.time.LocalDate;
import java.util.UUID;

public class DecisionService {
    public DecisionCredit decisionEngine(Personne client){
        Integer score = client.getScore();
        if (score >= 80){
            return DecisionCredit.ACCORD_IMMEDIAT;
        } else if (score >= 60) {
            return DecisionCredit.ETUDE_MANUELLE;
        }else {
            return DecisionCredit.REFUS_AUTOMATIQUE;
        }
    }
    public int applyIncidentImpact(Incident incident, int currentScore) {
        switch (incident.getTypeIncident()) {
            case IMPAYE_NON_REGLE:
                return currentScore - 10;
            case IMPAYE_REGLE:
                return currentScore - 5;
            case EN_RETARD:
                return currentScore - 3;
            case PAYE_EN_RETARD:
                return currentScore + 3;
            case PAYE_A_TEMPS:
                return currentScore + 10;
            default:
                return currentScore;
        }
    }

    public Incident createIncident(Echeance echeance, TypeIncident typeIncident) {
        return new Incident(
                UUID.randomUUID(),
                LocalDate.now(),
                echeance,
                3,
                typeIncident
        );
    }
}
