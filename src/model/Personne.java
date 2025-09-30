package model;

import enums.SituationFamiliale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

abstract class Personne {
    private UUID id;
    private String nom;
    private String prenom;
    private LocalDate datedenaissance;
    private String ville;
    private int nombreEnfants;
    private String investissement;
    private String placement;
    private SituationFamiliale situationFamiliale;
    private LocalDateTime createdAt;
    private int score;
}
