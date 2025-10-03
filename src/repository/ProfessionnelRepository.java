package repository;
import database.Connect;
import enums.Activite;
import enums.SecteurActivite;
import enums.SituationFamiliale;
import model.Professionnel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfessionnelRepository {
    private final Connection connection;
    public ProfessionnelRepository(){
        this.connection = Connect.gitInstance().getConnection();
    }
    public void create(Professionnel professionnel){
        String sql = "INSERT INTO professionnel (" +
                "id, nom, prenom, ville, investissement, placement, date_naissance,"+
                " nombre_enfants, score, situation_familiale, created_at, activite,"+
                " immatriculation_fiscale, revenu, secteur_activite" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, professionnel.getId());
            stmt.setString(2, professionnel.getNom());
            stmt.setString(3, professionnel.getPrenom());
            stmt.setString(4, professionnel.getVille());
            stmt.setBoolean(5, professionnel.getInvestissement());
            stmt.setBoolean(6, professionnel.getPlacement());
            stmt.setDate(7, Date.valueOf(professionnel.getDateNaissance()));
            stmt.setInt(8, professionnel.getNombreEnfants());
            stmt.setInt(9, professionnel.getScore());
            stmt.setString(10, professionnel.getSituationFamiliale().toString());
            stmt.setTimestamp(11, Timestamp.valueOf(professionnel.getCreatedAt()));
            stmt.setString(12, professionnel.getActivite().toString());
            stmt.setInt(13, professionnel.getImmatriculationFiscale());
            stmt.setDouble(14, professionnel.getRevenu());
            stmt.setString(15, professionnel.getSecteurActivite().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public void update(Professionnel professionnel, UUID ClientId){
        String sql = "UPDATE professionnel set " +
                "nom = ?, prenom = ?, ville = ?, investissement = ?, placement = ?, date_naissance = ?,"+
                " nombre_enfants = ?, score = ?, situation_familiale = ?, created_at = ?, activite = ?,"+
                " immatriculation_fiscale = ?, revenu = ?, secteur_activite = ? WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, professionnel.getNom());
            stmt.setString(2, professionnel.getPrenom());
            stmt.setString(3, professionnel.getVille());
            stmt.setBoolean(4, professionnel.getInvestissement());
            stmt.setBoolean(5, professionnel.getPlacement());
            stmt.setDate(6, Date.valueOf(professionnel.getDateNaissance()));
            stmt.setInt(7, professionnel.getNombreEnfants());
            stmt.setInt(8, professionnel.getScore());
            stmt.setString(9, professionnel.getSituationFamiliale().toString());
            stmt.setTimestamp(10, Timestamp.valueOf(professionnel.getCreatedAt()));
            stmt.setString(11, professionnel.getActivite().toString());
            stmt.setInt(12, professionnel.getImmatriculationFiscale());
            stmt.setDouble(13, professionnel.getRevenu());
            stmt.setString(14, professionnel.getSecteurActivite().toString());
            stmt.setObject(15, ClientId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public void delete(UUID id){
        String sql = "DELETE FROM professionnel WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
    }
    public Professionnel get(UUID id){
        String sql = "SELECT * FROM professionnel WHERE id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            try(ResultSet res = stmt.executeQuery()){
                if (res.next()){
                    return new Professionnel(
                            (UUID) res.getObject("id"),
                            res.getDouble("revenu"),
                            res.getInt("immatriculation_fiscale"),
                            SecteurActivite.valueOf(res.getString("secteur_activite")),
                            Activite.valueOf(res.getString("activite")),
                            res.getString("nom"),
                            res.getString("prenom"),
                            res.getDate("date_naissance").toLocalDate(),
                            res.getString("ville"),
                            res.getInt("nombre_enfants"),
                            res.getBoolean("investissement"),
                            res.getBoolean("placement"),
                            SituationFamiliale.valueOf(res.getString("situation_familiale")),
                            res.getTimestamp("created_at").toLocalDateTime(),
                            res.getInt("score")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
        return null;
    }
    public List<Professionnel> getAll(){
        String sql = "SELECT * FROM professionnel";
        List<Professionnel> professionnels = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Professionnel professionnel =  new Professionnel(
                        (UUID) res.getObject("id"),
                        res.getDouble("revenu"),
                        res.getInt("immatriculation_fiscale"),
                        SecteurActivite.valueOf(res.getString("secteur_activite")),
                        Activite.valueOf(res.getString("activite")),
                        res.getString("nom"),
                        res.getString("prenom"),
                        res.getDate("date_naissance").toLocalDate(),
                        res.getString("ville"),
                        res.getInt("nombre_enfants"),
                        res.getBoolean("investissement"),
                        res.getBoolean("placement"),
                        SituationFamiliale.valueOf(res.getString("situation_familiale")),
                        res.getTimestamp("created_at").toLocalDateTime(),
                        res.getInt("score")
                );
                professionnels.add(professionnel);
            }
        } catch (SQLException e) {
            System.out.println("Database error: "+ e.getMessage());
        }
        return professionnels;
    }
}
