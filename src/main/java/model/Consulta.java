package model;

import java.util.Date;

public class Consulta {
    private int id;
    private Date data;
    private String horario;
    private String comentario;
    private int idAnimal;
    private int idVeterinario;
    private int idTratamento;
    private int terminado;

    public Consulta(int id, Date data, String horario, String comentario, int idAnimal, int idVeterinario, int idTratamento, int terminado) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.comentario = comentario;
        this.idAnimal = idAnimal;
        this.idVeterinario = idVeterinario;
        this.idTratamento = idTratamento;
        this.terminado = terminado;
    }

    public int getId() {
        return id;
    }
    
     public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }
    
      public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }
    
      public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getComentario() {
        return comentario;
    }
    
     public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdAnimal() {
        return idAnimal;
    }
    
     public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }
    
      public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public int getIdTratamento() {
        return idTratamento;
    }
     public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public int getTerminado() {
        return terminado;
    }
     public void setTerminado(int terminado) {
        this.terminado = terminado;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", data=" + data + ", horario=" + horario + ", comentario=" + comentario + ", idAnimal=" + idAnimal + ", idVeterinario=" + idVeterinario + ", idTratamento=" + idTratamento + ", terminado=" + terminado + '}';
    }
    
    

    
}
