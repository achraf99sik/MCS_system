import controller.MainMenuController;
import repository.CreditRepository;
import repository.EmployeRepository;
import repository.ProfessionnelRepository;
import service.*;
import view.ClientView;
import view.CreditView;


public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService(new ProfessionnelRepository(), new EmployeRepository());
        CreditService creditService = new CreditService(new CreditRepository());
        PaiementService paiementService = new PaiementService();
        ClientView clientView = new ClientView(clientService);
        CreditView creditView = new CreditView(new DecisionService(),creditService,clientService);
        MainMenuController menu = new MainMenuController(
                creditService,
                paiementService,
                clientView,
                creditView
        );
        menu.start();
    }
}