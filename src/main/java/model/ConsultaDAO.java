package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Importe java.sql.Date
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.options;


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
    public Consulta create(Calendar data, String horario, String comentario, int idAnimal, int idVeterinario, int idTratamento, int terminado) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("INSERT INTO consulta (data, horario, comentario, id_animal, id_veterinario, id_tratamento, terminado) VALUES (?, ?, ?, ?, ?, ?, ?)");
            
             Timestamp timestamp = new Timestamp(data.getTimeInMillis());
            
            stmt.setTimestamp(1, new Timestamp(data.getTimeInMillis()), Calendar.getInstance());
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
            Calendar dt = Calendar.getInstance();
            boolean terminou = rs.getInt("terminado") == 1;
            dt.setTime(rs.getDate("data"));// Utilize o getDate para obter uma data

            consulta = new Consulta(
                rs.getInt("id"),
                rs.getInt("id_tratamento"),
                rs.getInt("id_veterinario"),
                rs.getInt("id_animal"),
                dt,
                rs.getString("horario"),
                rs.getString("comentario"),
                rs.getBoolean("terminado")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    // Generic Retriever
    /*public List<Consulta> retrieve(String query) {
        List<Consulta> consultas = new ArrayList<>();
        ResultSet rs = getResultSet();
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }*/
    
     // Generic Retriever
    public List<Consulta> retrieve(String query) {
        List<Consulta> consultas = new ArrayList<>();
        ResultSet rs = getResultSet(query);

        try {
            if (rs != null) {
                while (rs.next()) {
                    consultas.add(buildObject(rs));
                }
            } else {
                System.err.println("ResultSet is null. Unable to retrieve data.");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            // Certifique-se de fechar o ResultSet no bloco finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            }
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
    
    //RetrieveByAnimalId
    public Consulta retrieveByAnimalId(int idAnimal) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM "
                + "consulta WHERE id_animal = " + idAnimal);
        return (consultas.isEmpty() ? null : consultas.get(0));
    }
    
    public Consulta retrieveByVeterinarioId(int idVeterinario) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM "
                + "consulta WHERE id_veterinario = " + idVeterinario);
        return (consultas.isEmpty() ? null : consultas.get(0));
    }
    
    public List retrieveByStatus(int id, options.ConsultasOptions opt) {
        return this.retrieve(
                "SELECT * FROM consulta WHERE id_tratemento = " + id + " AND terminado = "
                + (opt == options.ConsultasOptions.marcada ? 0 : 1));
    }
     

    // RetrieveBySimilarName
    public List<Consulta> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM consulta WHERE nome LIKE '%" + nome + "%'");
    }
    
    public List retrieveAllByTratamentoId(int idTratamento) {
        return this.retrieve("SELECT * FROM cinsulta WHERE id_tratamento = " + idTratamento);
    }
    
    public List retrieveAllByAnimalId(int idAnimal) {
        return this.retrieve("SELECT * FROM "
                + "consulta WHERE id_animal = " + idAnimal);
    }

    public List retrieveAllByVeterinarioId(int idVeterinario) {
        return this.retrieve("SELECT * FROM "
                + "consulta WHERE id_veterinario = " + idVeterinario);

    }
    
    public void update(Consulta consulta) {
        PreparedStatement pstm;

        try {
            pstm = ConsultaDAO.getConnection().prepareStatement("UPDATE consulta "
                    + "SET data=?, horario = ?, comentario = ?, "
                    + "id_animal = ?, id_veterinario = ?,"
                    + "id_tratamento, terminado = ? WHERE id = ?");
            pstm.setString(1, "" + consulta.getData());
            pstm.setString(2, consulta.getHorario());
            pstm.setString(3, consulta.getComentario());
            pstm.setInt(4, consulta.getIdAnimal());
            pstm.setInt(5, consulta.getIdVeterinario());
            pstm.setInt(6, consulta.getIdTratamento());
            pstm.setInt(7, (consulta.isTerminou() ? 1 : 0));
            pstm.setInt(8, consulta.getIdConsulta());

            executeUpdate(pstm);

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    //Delete
    public void delete(Consulta consulta) {
        PreparedStatement stmt;
        try {
            stmt = getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getIdConsulta());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void terminoConsulta(Consulta consulta) {
        PreparedStatement pstm;
        try {
            pstm = ConsultaDAO.getConnection().prepareStatement("UPDATE consulta SET terminado WHERE id=?");
            pstm.setInt(1, 1);
            pstm.setInt(2, consulta.getIdConsulta());
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
