package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExameDAO extends DAO {
    private static ExameDAO instance;

    private ExameDAO() {
        getConnection();
        createTable();
    }

     public static ExameDAO getInstance() {
        return (instance == null ? (instance = new ExameDAO()) : instance);
    }

// CRUD
    public Exame create(int id, String descricao, int idConsulta) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("INSERT INTO exame (id, descricao, id_consulta) VALUES (?,?,?)");
            stmt.setInt(1, id);
            stmt.setString(2, descricao);
            stmt.setInt(3, idConsulta);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ExameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("exame", "id"));
    }


    private Exame buildObject(ResultSet rs) {
        Exame exame = null;
        try {
            exame = new Exame(rs.getInt("id"), rs.getString("descricao"), rs.getInt("idConsulta"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return exame;
    }

    // Generic Retriever
     public List<Exame> retrieve(String query) {
        List<Exame> exames = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                exames.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return exames;
    }

    // RetrieveAll
    public List<Exame> retrieveAll() {
        return this.retrieve("SELECT * FROM exame");
    }

    // RetrieveLast
    public List<Exame> retrieveLast() {
        return this.retrieve("SELECT * FROM exame WHERE id = " + lastId("exame", "id"));
    }

    // RetrieveById
    public Exame retrieveById(int id) {
        List<Exame> exames = this.retrieve("SELECT * FROM exame WHERE id = " + id);
        return (exames.isEmpty() ? null : exames.get(0));
    }

    // RetrieveBySimilarName
    public List<Exame> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM exame WHERE nome LIKE '%" + nome + "%'");
    }

    // Updade
    public void update(Exame exame) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("UPDATE exame SET descricao=?, id_consulta=? WHERE id=?");
            stmt.setString(1, exame.getDescricao());
            stmt.setInt(2, exame.getIdConsulta());
            stmt.setInt(3, exame.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Exame exame) {
        PreparedStatement stmt;
        try {
            stmt = getConnection().prepareStatement("DELETE FROM exame WHERE id = ?");
            stmt.setInt(1, exame.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
}
