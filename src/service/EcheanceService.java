package service;

import model.Echeance;
import repository.EcheanceRepository;

import java.util.List;
import java.util.UUID;

public class EcheanceService {
    private final EcheanceRepository echeanceRepository;

    public EcheanceService(EcheanceRepository echeanceRepository) {
        this.echeanceRepository = echeanceRepository;
    }

    public void createEcheance(Echeance echeance) {
        echeanceRepository.create(echeance);
    }

    public Echeance getEcheance(UUID id) {
        return echeanceRepository.get(id);
    }

    public void updateEcheance(Echeance echeance, UUID id) {
        echeanceRepository.update(echeance, id);
    }

    public void deleteEcheance(UUID id) {
        echeanceRepository.delete(id);
    }

    public List<Echeance> getAllEcheances() {
        return echeanceRepository.getAll();
    }
}
