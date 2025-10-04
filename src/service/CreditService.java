package service;
import model.Credit;
import repository.CreditRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreditService {
    private final CreditRepository creditRepository;
    public CreditService(CreditRepository creditRepository){
        this.creditRepository = creditRepository;
    }
    public void createCredit(Credit credit){
        creditRepository.create(credit);
    }
    public void updateCredit(Credit credit, UUID id){
        creditRepository.update(credit, id);
    }
    public Credit getCredit(UUID id){
        return creditRepository.get(id);
    }
    public void deleteCredit(UUID id){
        creditRepository.delete(id);
    }
    public List<Credit> listCredits(){
        return creditRepository.getAll();
    }
}
