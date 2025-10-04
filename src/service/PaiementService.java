package service;

import enums.StatutPaiement;
import model.Credit;
import model.Echeance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaiementService {
    public List<Echeance> generateEcheances(Credit credit) {
        List<Echeance> echeances = new ArrayList<>();
        double mensualite = credit.getMontantOctroye() / credit.getDureeEnMois();

        for (int i = 1; i <= credit.getDureeEnMois(); i++) {
            LocalDate dateEcheance = credit.getDateDeCredit().plusMonths(i);
            Echeance echeance = new Echeance(
                    UUID.randomUUID(),
                    dateEcheance,
                    mensualite,
                    null,
                    StatutPaiement.PAYE_A_TEMPS
            );
            echeances.add(echeance);
        }
        return echeances;
    }

    public void managePayments(Echeance echeance, LocalDate datePaiement) {
        echeance.setDateDePaiement(datePaiement);

        if (datePaiement.isBefore(echeance.getDateEcheance()) || datePaiement.isEqual(echeance.getDateEcheance())) {
            echeance.setStatutPaiement(StatutPaiement.PAYE_A_TEMPS);
        } else if (datePaiement.isAfter(echeance.getDateEcheance()) && datePaiement.isBefore(echeance.getDateEcheance().plusDays(30))) {
            echeance.setStatutPaiement(StatutPaiement.EN_RETARD);
        } else if (datePaiement.isAfter(echeance.getDateEcheance().plusDays(30))) {
            echeance.setStatutPaiement(StatutPaiement.IMPAYE_NON_REGLE);
        }
    }
}
