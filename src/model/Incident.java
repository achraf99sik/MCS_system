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
}
