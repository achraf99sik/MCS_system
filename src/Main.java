import controller.MainMenuController;
import enums.Activite;
import enums.SituationFamiliale;
import model.Professionnel;
import repository.ProfessionnelRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        //MainMenuController menu = new MainMenuController();
        //menu.start();
        ProfessionnelRepository pro = new ProfessionnelRepository();
        UUID id = UUID.randomUUID();
        Professionnel obj = new Professionnel(id, 9, 4, Activite.AGRICULTEUR.getSecteur(), Activite.AGRICULTEUR, "Jean", "Dupont", LocalDate.of(1985, 5, 12), "Paris", 2, "Immobilier", "Assurance vie", SituationFamiliale.MARIE,LocalDateTime.now(), 75);
        Professionnel obj1 = new Professionnel(100, 90, Activite.AVOCAT.getSecteur(), Activite.AVOCAT, "jon", "Dupont", LocalDate.of(1995, 5, 12), "Paris", 2, "Immobilier", "Assurance vie", SituationFamiliale.MARIE,LocalDateTime.now(), 75);
        pro.create(obj);
        pro.update(obj1, id);
        System.out.println(pro.get(id));
        pro.getAll().forEach(System.out::println);
    }
}