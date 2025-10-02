package repository;

import database.Connect;
import enums.DecisionCredit;
import enums.TypeCredit;
import model.Credit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreditRepository {
    private final Connection connection;
    public CreditRepository(){
        this.connection = Connect.gitInstance().getConnection();
    }
    public void create(Credit credit){
        String sql = "INSERT INTO credit (" +
                "id, date_de_credit, montant_demande, montant_octroye, taux_interet, duree_en_mois, type_credit, decision" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, credit.getId());
            stmt.setDate(2, Date.valueOf(credit.getDateDeCredit()));
            stmt.setDouble(3, credit.getMontantDemande());
            stmt.setDouble(4, credit.getMontantOctroye());
            stmt.setDouble(5, credit.getTauxInteret());
            stmt.setInt(6, credit.getDureeEnMois());
            stmt.setString(7, credit.getTypecredit().toString());
            stmt.setString(8, credit.getDecision().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public void update(Credit credit, UUID creditId){
        String sql = "UPDATE credit set " +
                "date_de_credit = ?, montant_demande = ?, montant_octroye = ?, taux_interet = ?, duree_en_mois = ?, type_credit = ?, decision = ? WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(credit.getDateDeCredit()));
            stmt.setDouble(2, credit.getMontantDemande());
            stmt.setDouble(3, credit.getMontantOctroye());
            stmt.setDouble(4, credit.getTauxInteret());
            stmt.setInt(5, credit.getDureeEnMois());
            stmt.setString(6, credit.getTypecredit().toString());
            stmt.setString(7, credit.getDecision().toString());
            stmt.setObject(8, creditId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public void delete(UUID id){
        String sql = "DELETE FROM credit WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public Credit get(UUID id){
        String sql = "SELECT * FROM credit WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            try(ResultSet res = stmt.executeQuery()){
                if (res.next()){
                    return new Credit(
                            (UUID) res.getObject("id"),
                            res.getDate("date_de_credit").toLocalDate(),
                            res.getDouble("montant_demande"),
                            res.getDouble("montant_octroye"),
                            res.getDouble("taux_interet"),
                            res.getInt("duree_en_mois"),
                            TypeCredit.valueOf(res.getString("type_credit")),
                            DecisionCredit.valueOf(res.getString("decision"))
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
        return null;
    }
    public List<Credit> getAll(){
        String sql = "SELECT * FROM credit";
        List<Credit> credits = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Credit credit =  new Credit(
                        (UUID) res.getObject("id"),
                        res.getDate("date_de_credit").toLocalDate(),
                        res.getDouble("montant_demande"),
                        res.getDouble("montant_octroye"),
                        res.getDouble("taux_interet"),
                        res.getInt("duree_en_mois"),
                        TypeCredit.valueOf(res.getString("type_credit")),
                        DecisionCredit.valueOf(res.getString("decision"))
                );
                credits.add(credit);
            }
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
        return credits;
    }
}
