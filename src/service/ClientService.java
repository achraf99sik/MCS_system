package service;

import model.Employe;
import model.Personne;
import model.Professionnel;
import repository.EmployeRepository;
import repository.ProfessionnelRepository;

import java.util.*;

public class ClientService {
    private final ProfessionnelRepository professionnelRepository;
    private final EmployeRepository employeRepository;
    public ClientService(
            ProfessionnelRepository professionnelRepository,
            EmployeRepository employeRepository){
        this.professionnelRepository = professionnelRepository;
        this.employeRepository = employeRepository;
    }
    public void createClient(Personne client){
        if (client instanceof Professionnel){
            professionnelRepository.create((Professionnel) client);
        }else {
            employeRepository.create((Employe) client);
        }
    }
    public void updateClient(Personne client, UUID id){
        if (client instanceof Professionnel){
            professionnelRepository.update((Professionnel) client, id);
        }else {
            employeRepository.update((Employe) client, id);
        }
    }
    public Personne getClient(UUID id){
        Professionnel professionnel = professionnelRepository.get(id);
        if (professionnel != null){
            return professionnel;
        }
        return employeRepository.get(id);
    }
    public void deleteClient(UUID id){
        professionnelRepository.delete(id);
        employeRepository.delete(id);
    }
    public List<Personne> listClients(){
        List<Personne> clients = new ArrayList<>();
        clients.addAll(employeRepository.getAll());
        clients.addAll(professionnelRepository.getAll());
        return clients;
    }
}
