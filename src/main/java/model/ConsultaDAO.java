package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Importe java.sql.Date
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;

    private ConsultaDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ConsultaDAO getInstance() {
        return (instance == null ? (instance = new ConsultaDAO()) : instance);
    }

    // CRUD
    public Consulta create(Date data, String horario, String comentario, int idAnimal, int idVeterinario, int idTratamento, int terminado) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("INSERT INTO consulta (data, horario, comentario, id_animal, id_vet, id_tratamento, terminado) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setDate(1, data);
            stmt.setString(2, horario);
            stmt.setString(3, comentario);
            stmt.setInt(4, idAnimal);
            stmt.setInt(5, idVeterinario);
            stmt.setInt(6, idTratamento);
            stmt.setInt(7, terminado);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta", "id"));
    }

    private Consulta buildObject(ResultSet rs) {
        Consulta consulta = null;
        try {
            Date data = rs.getDate("data"); // Utilize o getDate para obter uma data

            consulta = new Consulta(
                rs.getInt("id"),
                data,
                rs.getString("horario"),
                rs.getString("comentario"),
                rs.getInt("id_animal"),
                rs.getInt("id_vet"),
                rs.getInt("id_tratamento"),
                rs.getInt("terminado")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    // Generic Retriever
    public List<Consulta> retrieve(String query) {
        List<Consulta> consultas = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }

    // RetrieveAll
    public List<Consulta> retrieveAll() {
        return this.retrieve("SELECT * FROM consulta");
    }

    // RetrieveLast
    public List<Consulta> retrieveLast() {
        return this.retrieve("SELECT * FROM consulta WHERE id = " + lastId("consulta", "id"));
    }

    // RetrieveById
    public Consulta retrieveById(int id) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consultas.isEmpty() ? null : consultas.get(0));
    }

    // RetrieveBySimilarName
    public List<Consulta> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM consulta WHERE nome LIKE '%" + nome + "%'");
    }

    // Update
    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("UPDATE consulta SET data=?, horario=?, comentario=?, id_animal=?, id_vet=?, id_tratamento=?, terminado=? WHERE id=?");
            stmt.setString(1, consulta.getData().toString());
            stmt.setString(2, consulta.getHorario());
            stmt.setString(3, consulta.getComentario());
            stmt.setInt(4, consulta.getIdAnimal());
            stmt.setInt(5, consulta.getIdVeterinario());
            stmt.setInt(6, consulta.getIdTratamento());
            stmt.setInt(7, consulta.getTerminado());
            stmt.setInt(8, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Consulta consulta) {
        PreparedStatement stmt;
        try {
            stmt = getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
