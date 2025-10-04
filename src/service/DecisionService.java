package service;

import enums.TypeIncident;
import model.Echeance;
import model.Incident;

import java.time.LocalDate;
import java.util.UUID;

public class DecisionService {
    public void decisionEngine(){}
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
