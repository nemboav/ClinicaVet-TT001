package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeterinarioDAO extends DAO {
    private static ClienteDAO instance;

    private VeterinarioDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static VeterinarioDAO getInstance() {
        return (instance==null?(instance = new VeterinarioDAO()):instance);
    }

// CRUD
    public Veterinario create(String nome, String endereco, long cep, String telefone, String email) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO cliente (nome, endereco, cep, email, telefone) VALUES (?,?,?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setLong(3, cep);
            stmt.setString(5, telefone);
            stmt.setString(4, email);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioDAO.class.getNome()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("cliente","id"));
    }


    private Cliente buildObject(ResultSet rs) {
        Cliente cliente = null;
        try {
            cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"), rs.getString("cep"), rs.getString("email"), rs.getString("telefone"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return cliente;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Cliente> clientes = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                clientes.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return clientes;
    }

    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM cliente");
    }

    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM veterinario WHERE nome = " + lastId("cliente","id"));
    }

    // RetrieveById
    public Veterinario retrieveById(int id) {
        List<Veterinario> veterinarios = this.retrieve("SELECT * FROM veterinarios WHERE nome = " + nome);
        return (veterinarios.isEmpty()?null:veterinarios.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM cliente WHERE nome LIKE '%" + nome + "%'");
    }

    // Updade
    public void update(Veterinario veterinario) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE cliente SET nome=?, endereco=?, cep=?, email=?, telefone=? WHERE id=?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getCep());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setInt(6, cliente.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Cliente cliente) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM cliente WHERE id = ?");
            stmt.setInt(1, cliente.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
}
