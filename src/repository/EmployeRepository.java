package repository;

import database.Connect;
import enums.Secteur;
import enums.SituationFamiliale;
import enums.TypeContrat;
import model.Employe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeRepository {
    private final Connection connection;
    public EmployeRepository(){
        this.connection = Connect.gitInstance().getConnection();
    }
    public void create(Employe employe){
        String sql = "INSERT INTO employe (" +
                "id, nom, prenom, ville, investissement, placement, date_naissance,"+
                " nombre_enfants, score, situation_familiale, created_at,"+
                " salaire, anciennete, poste, type_contrat, secteur" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, employe.getId());
            stmt.setString(2, employe.getNom());
            stmt.setString(3, employe.getPrenom());
            stmt.setString(4, employe.getVille());
            stmt.setString(5, employe.getInvestissement());
            stmt.setString(6, employe.getPlacement());
            stmt.setDate(7, Date.valueOf(employe.getDateNaissance()));
            stmt.setInt(8, employe.getNombreEnfants());
            stmt.setInt(9, employe.getScore());
            stmt.setString(10, employe.getSituationFamiliale().toString());
            stmt.setTimestamp(11, Timestamp.valueOf(employe.getCreatedAt()));
            stmt.setDouble(12, employe.getSalaire());
            stmt.setInt(13, employe.getAnciennete());
            stmt.setString(14, employe.getPoste());
            stmt.setString(15, employe.getTypecontrat().toString());
            stmt.setString(16, employe.getSecteur().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public void update(Employe employe, UUID employeId){
        String sql = "UPDATE employe set " +
                "nom = ?, prenom = ?, ville = ?, investissement = ?, placement = ?, date_naissance = ?,"+
                " nombre_enfants = ?, score = ?, situation_familiale = ?, created_at = ?,"+
                " salaire = ?, anciennete = ?, poste = ?, type_contrat = ?, secteur = ? WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employe.getNom());
            stmt.setString(2, employe.getPrenom());
            stmt.setString(3, employe.getVille());
            stmt.setString(4, employe.getInvestissement());
            stmt.setString(5, employe.getPlacement());
            stmt.setDate(6, Date.valueOf(employe.getDateNaissance()));
            stmt.setInt(7, employe.getNombreEnfants());
            stmt.setInt(8, employe.getScore());
            stmt.setString(9, employe.getSituationFamiliale().toString());
            stmt.setTimestamp(10, Timestamp.valueOf(employe.getCreatedAt()));
            stmt.setDouble(11, employe.getSalaire());
            stmt.setInt(12, employe.getAnciennete());
            stmt.setString(13, employe.getPoste());
            stmt.setString(14, employe.getTypecontrat().toString());
            stmt.setString(15, employe.getSecteur().toString());
            stmt.setObject(16, employeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public void delete(UUID id){
        String sql = "DELETE FROM employe WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public Employe get(UUID id){
        String sql = "SELECT * FROM employe WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            try(ResultSet res = stmt.executeQuery()){
                if (res.next()){
                    return new Employe(
                            (UUID) res.getObject("id"),
                            res.getString("nom"),
                            res.getString("prenom"),
                            res.getDate("date_naissance").toLocalDate(),
                            res.getString("ville"),
                            res.getInt("nombre_enfants"),
                            res.getString("investissement"),
                            res.getString("placement"),
                            SituationFamiliale.valueOf(res.getString("situation_familiale")),
                            res.getTimestamp("created_at").toLocalDateTime(),
                            res.getInt("score"),
                            res.getDouble("salaire"),
                            res.getInt("anciennete"),
                            res.getString("poste"),
                            TypeContrat.valueOf(res.getString("type_contrat")),
                            Secteur.valueOf(res.getString("secteur"))
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
        return null;
    }
    public List<Employe> getAll(){
        String sql = "SELECT * FROM employe";
        List<Employe> employes = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Employe employe =  new Employe(
                        (UUID) res.getObject("id"),
                        res.getString("nom"),
                        res.getString("prenom"),
                        res.getDate("date_naissance").toLocalDate(),
                        res.getString("ville"),
                        res.getInt("nombre_enfants"),
                        res.getString("investissement"),
                        res.getString("placement"),
                        SituationFamiliale.valueOf(res.getString("situation_familiale")),
                        res.getTimestamp("created_at").toLocalDateTime(),
                        res.getInt("score"),
                        res.getDouble("salaire"),
                        res.getInt("anciennete"),
                        res.getString("poste"),
                        TypeContrat.valueOf(res.getString("type_contrat")),
                        Secteur.valueOf(res.getString("secteur"))
                );
                employes.add(employe);
            }
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
        return employes;
    }
}
