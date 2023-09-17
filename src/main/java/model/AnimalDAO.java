package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimalDAO extends DAO {
    private static AnimalDAO instance;

    private AnimalDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static AnimalDAO getInstance() {
        return (instance == null ? (instance = new AnimalDAO()) : instance);
    }

    // CRUD
    public Animal create(String nome, int idade, int sexo, int idEspecie, int idCliente) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO animal (nome, idade, sexo, id_especie, id_cliente) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.setInt(3, sexo);
            stmt.setInt(4, idEspecie);
            stmt.setInt(5, idCliente);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("animal", "id"));
    }

    private Animal buildObject(ResultSet rs) {
        Animal animal = null;
        try {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            int idade = rs.getInt("idade");
            int sexo = rs.getInt("sexo");
            int idEspecie = rs.getInt("id_especie");
            int idCliente = rs.getInt("id_cliente");
            animal = new Animal(id, nome, idade, sexo, idEspecie, idCliente);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animal;
    }

    // Generic Retriever
    public List<Animal> retrieve(String query) {
        List<Animal> animais = new ArrayList<>();
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
    public List<Animal> retrieveAll() {
        return this.retrieve("SELECT * FROM animal");
    }

    // RetrieveLast
    public List<Animal> retrieveLast() {
        return this.retrieve("SELECT * FROM animal WHERE id = " + lastId("animal", "id"));
    }

    // RetrieveById
    public Animal retrieveById(int id) {
        List<Animal> animais = this.retrieve("SELECT * FROM animal WHERE id = " + id);
        return (animais.isEmpty() ? null : animais.get(0));
    }

    // RetrieveBySimilarName
    public List<Animal> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM animal WHERE nome LIKE '%" + nome + "%'");
    }

    // Update
    public void update(Animal animal) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE animal SET nome=?, idade=?, sexo=?, id_especie=?, id_cliente=? WHERE id=?");
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getIdade());
            stmt.setInt(3, animal.getSexo());
            stmt.setInt(4, animal.getIdEspecie());
            stmt.setInt(5, animal.getIdCliente());
            stmt.setInt(6, animal.getId());
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
