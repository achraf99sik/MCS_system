package repository;

import database.Connect;
import enums.StatutPaiement;
import model.Echeance;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EcheanceRepository {
    private final Connection connection;
    public EcheanceRepository(){
        this.connection = Connect.gitInstance().getConnection();
    }
    public void create(Echeance echeance){
        String sql = "INSERT INTO echeance (" +
                "id, date_echeance, mensualite, date_de_paiement, statut_paiement" +
                ") VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, echeance.getId());
            stmt.setDate(2, Date.valueOf(echeance.getDateEcheance()));
            stmt.setDouble(3, echeance.getMensualite());
            stmt.setDate(4, Date.valueOf(echeance.getDateDePaiement()));
            stmt.setString(5, echeance.getStatutPaiement().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public void update(Echeance echeance, UUID echeanceId){
        String sql = "UPDATE echeance set " +
                "date_echeance = ?, mensualite = ?, date_de_paiement = ?, statut_paiement = ? WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(echeance.getDateEcheance()));
            stmt.setDouble(2, echeance.getMensualite());
            stmt.setDate(3, Date.valueOf(echeance.getDateDePaiement()));
            stmt.setString(4, echeance.getStatutPaiement().toString());
            stmt.setObject(5, echeanceId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public void delete(UUID id){
        String sql = "DELETE FROM echeance WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public Echeance get(UUID id){
        String sql = "SELECT * FROM echeance WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            try(ResultSet res = stmt.executeQuery()){
                if (res.next()){
                    return new Echeance(
                            (UUID) res.getObject("id"),
                            res.getDate("date_echeance").toLocalDate(),
                            res.getDouble("mensualite"),
                            res.getDate("date_de_paiement").toLocalDate(),
                            StatutPaiement.valueOf(res.getString("statut_paiement"))
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
        return null;
    }
    public List<Echeance> getAll(){
        String sql = "SELECT * FROM echeance";
        List<Echeance> echeances = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Echeance echeance =  new Echeance(
                        (UUID) res.getObject("id"),
                        res.getDate("date_echeance").toLocalDate(),
                        res.getDouble("mensualite"),
                        res.getDate("date_de_paiement").toLocalDate(),
                        StatutPaiement.valueOf(res.getString("statut_paiement"))
                );
                echeances.add(echeance);
            }
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
        return echeances;
    }
}
