package repository;

import database.Connect;
import enums.TypeIncident;
import model.Echeance;
import model.Incident;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IncidentRepository {
    private final Connection connection;
    private final EcheanceRepository echeanceRepository; // Dependency for Echeance

    public IncidentRepository(){
        this.connection = Connect.gitInstance().getConnection();
        this.echeanceRepository = new EcheanceRepository(); // Initialize the EcheanceRepository
    }
    public void create(Incident incident){
        String sql = "INSERT INTO incident (" +
                "id, date_incident, echeance_id, score, type_incident" +
                ") VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, incident.getId());
            stmt.setDate(2, Date.valueOf(incident.getDateIncident()));
            stmt.setObject(3, incident.getEcheance().getId()); // Store Echeance ID
            stmt.setInt(4, incident.getScore());
            stmt.setString(5, incident.getTypeIncident().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public void update(Incident incident, UUID incidentId){
        String sql = "UPDATE incident set " +
                "date_incident = ?, echeance_id = ?, score = ?, type_incident = ? WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(incident.getDateIncident()));
            stmt.setObject(2, incident.getEcheance().getId());
            stmt.setInt(3, incident.getScore());
            stmt.setString(4, incident.getTypeIncident().toString());
            stmt.setObject(5, incidentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public void delete(UUID id){
        String sql = "DELETE FROM incident WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public Incident get(UUID id){
        String sql = "SELECT * FROM incident WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            try(ResultSet res = stmt.executeQuery()){
                if (res.next()){
                    UUID echeanceId = (UUID) res.getObject("echeance_id");
                    Echeance echeance = echeanceRepository.get(echeanceId); // Retrieve Echeance object
                    return new Incident(
                            (UUID) res.getObject("id"),
                            res.getDate("date_incident").toLocalDate(),
                            echeance,
                            res.getInt("score"),
                            TypeIncident.valueOf(res.getString("type_incident"))
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
        return null;
    }
    public List<Incident> getAll(){
        String sql = "SELECT * FROM incident";
        List<Incident> incidents = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                UUID echeanceId = (UUID) res.getObject("echeance_id");
                Echeance echeance = echeanceRepository.get(echeanceId);
                Incident incident =  new Incident(
                        (UUID) res.getObject("id"),
                        res.getDate("date_incident").toLocalDate(),
                        echeance,
                        res.getInt("score"),
                        TypeIncident.valueOf(res.getString("type_incident"))
                );
                incidents.add(incident);
            }
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
        return incidents;
    }
}
