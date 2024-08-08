package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import model.*;
import view.options;


public class TratamentoDAO extends DAO {
    private static TratamentoDAO instance;

    private TratamentoDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static TratamentoDAO getInstance() {
        return (instance == null ? (instance = new TratamentoDAO()) : instance);
    }

    // CRUD
    public Tratamento create(int idAnimal, String nome, String dataInicial, String dataFinal, boolean terminado) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO tratamento (id_animal, nome, dataInicial, dataFinal, terminado) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, idAnimal);
            stmt.setString(2, nome);
            stmt.setString(3, dataInicial);
            stmt.setString(4, dataFinal);
            stmt.setBoolean(5, terminado);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveTratamentoById(lastId("tratamento", "id"));
    }

    private Tratamento buildObject(ResultSet rs) {
        Tratamento tratamento = null;
        try {
            boolean terminou = rs.getInt("terminado") == 1;

            tratamento = new Tratamento(rs.getInt("id"), rs.getInt("id_animal"),
                    rs.getString("nome"), rs.getString("dataInicial"), rs.getString("dataFinal"), terminou);

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return tratamento;
        /*Tratamento tratamento = null;
        try {
            tratamento = new Tratamento(rs.getInt("id"),rs.getInt ("id_animal"), rs.getString("nome"), rs.getString("dataInicial"), rs.getString("dataFinal"), rs.getBoolean("terminou"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamento;*/
    }

    // Generic Retriever
       public List retrieve(String sql) {
        List<Tratamento> tratamentos = new ArrayList();
        ResultSet rs = getResultSet(sql);

        try {
            while (rs.next()) {
                tratamentos.add(buildObject(rs));
            }

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return tratamentos;
    }


    // RetrieveAll
    public List<Tratamento> retrieveAll() {
        return this.retrieve("SELECT * FROM tratamento");
    }
    
     public Tratamento retrieveFirstByAnimalId(int id) {
        List<Tratamento> tratamentos = this.retrieve("SELECT * FROM "
                + "tratamento WHERE id_animal = " + id);
        return (tratamentos.isEmpty() ? null : tratamentos.get(0));
    }
     
    public Tratamento retrieveById(int id) {
        List<Tratamento> tratamentos = this.retrieve("SELECT * FROM "
                + "tratamento WHERE id = " + id);
        return (tratamentos.isEmpty() ? null : tratamentos.get(0));
    }

    public List retrieveByStatus(int id, options.TratamentoOptions opcao) {
        return this.retrieve(
                "SELECT * FROM tratamento WHERE id_animal = " + id + " AND terminado = "
                + (opcao == options.TratamentoOptions.andamento ? 0 : 1));
    }

    // RetrieveLast
    public List<Tratamento> retrieveLast() {
        return this.retrieve("SELECT * FROM tratamento WHERE id = " + lastId("tratamento", "id"));
    }

    // RetrieveById
    public Tratamento retrieveTratamentoById(int id) {
        List<Tratamento> veterinarios = this.retrieve("SELECT * FROM tratamento WHERE id = " + id);
        return (veterinarios.isEmpty() ? null : veterinarios.get(0));
    }

    // RetrieveBySimilarName
    public List<Tratamento> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM tratamento WHERE nome LIKE '%" + nome + "%'");
    }
    
    public List retrieveAllByAnimalId(int id) {
        return this.retrieve("SELECT * FROM tratamento where id_animal = " + id);
    }

    // Update
    public void update(Tratamento tratamento) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE tratamento SET id_animal=?, nome=?, dataInicial=?, dataFinal=?, terminado=? WHERE id=?");
            stmt.setInt(1, tratamento.getIdAnimal());
            stmt.setString(2, tratamento.getNome());
            stmt.setString(3, tratamento.getDataInicial());
            stmt.setString(4, tratamento.getDataFinal());
            stmt.setBoolean(5, tratamento.isTerminou());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Tratamento tratamento) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM tratamento WHERE id = ?");
            stmt.setInt(1, tratamento.getIdTratamento());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void fimTratamento(Tratamento tratamento) {
        PreparedStatement pstm;
        
        try {
            Calendar dt = Calendar.getInstance();
            SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyyy");
            String dataFinal = simp.format(dt.getTimeInMillis());
            
            pstm = TratamentoDAO.getConnection().prepareStatement("UPDATE tratamento SET id_animal=?, nome=?, dataInicial=?, dataFinal=?, terminado=? WHERE id=?");
            pstm.setInt(1, tratamento.getIdAnimal());
            pstm.setString(2, tratamento.getNome());
            pstm.setString(3, "" + tratamento.getDataInicial());
            pstm.setString(4, dataFinal);
            pstm.setInt(5, 1);
            pstm.setInt(6, tratamento.getIdTratamento());
            
            executeUpdate(pstm);
            
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        
    }
}
