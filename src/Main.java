import controller.MainMenuController;
import repository.CreditRepository;
import repository.EmployeRepository;
import repository.ProfessionnelRepository;
import service.*;
import view.ClientView;


public class Main {
    public static void main(String[] args) {
        AnalyticsService analyticsService = new AnalyticsService();
        DecisionService decisionService = new DecisionService();
        IncidentService incidentService = new IncidentService();
        ClientService clientService = new ClientService(new ProfessionnelRepository(), new EmployeRepository());
        ScoreService scoreService = new ScoreService();
        CreditService creditService = new CreditService(new CreditRepository());
        PaiementService paiementService = new PaiementService();
        ClientView clientView = new ClientView(clientService);
        MainMenuController menu = new MainMenuController(
                analyticsService,
                decisionService,
                incidentService,
                scoreService,
                creditService,
                paiementService,
                clientView
        );
        menu.start();
    }
}