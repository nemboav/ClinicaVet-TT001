package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DAO.getConnection;


public class AnimalDAO {
    private static AnimalDAO instance;

    private AnimalDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static AnimalDAO getInstance() {
        return (instance==null?(instance = new AnimalDAO()):instance);
    }

// CRUD
    public Animal create(String nome, String end, String cep, String email, String telefone) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO animal (nome, idade, sexo) VALUES (?,?,?)");
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setInt(3, sexo);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("cliente","id"));
    }


    private Animal buildObject(ResultSet rs) {
        Animal animal = null;
        try {
            animal = new Animal(rs.getString("nome"), r, rs.getInt("idade"), rs.getInt("sexo"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animal;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Animal> animais = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                animais.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animais;
    }

    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM animal");
    }

    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM animal WHERE nome = " + lastId("cliente","id"));
    }

    // RetrieveById
    public Animal retrieveById(int id) {
        List<Animal> animais = this.retrieve("SELECT * FROM animal WHERE id = " + id);
        return (animais.isEmpty()?null:animais.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM animal WHERE nome LIKE '%" + nome + "%'");
    }

    // Updade
    public void update(Animal animal) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE animal SET nome=?, endereco=?, cep=?, email=?, telefone=? WHERE id=?");
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getIdade());
            stmt.setInt(3, animal.getSexo());
            stmt.setInt(6, cliente.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Animal animal) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM animal WHERE id = ?");
            stmt.setInt(1, animal.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    
}
