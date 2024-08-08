package model;

import java.util.Calendar;

public class Consulta {
    private int idConsulta;
    private int idTratamento;
    private int idVeterinario;
    private int idAnimal;
    private Calendar data;
    private String horario;
    private String comentario;
    private boolean terminou;

    public Consulta(int idConsulta, int idTratamento, int idVeterinario, int idAnimal, Calendar data, String horario, String comentario, boolean terminou) {
        this.idConsulta = idConsulta;
        this.idTratamento = idTratamento;
        this.idVeterinario = idVeterinario;
        this.idAnimal = idAnimal;
        this.data = data;
        this.horario = horario;
        this.comentario = comentario;
        this.terminou = terminou;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
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

    public boolean isTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }

    @Override
    public String toString() {
        return "Consulta{\nCódigo Consulta: " + idConsulta
                + "\nid Tratamento: " + idTratamento
                + "\nid Veterinário: " + idVeterinario
                + "\nid Animal: " + idAnimal
                + "\nComentários: " + comentario
                + "\nData: " + data
                + "\nHorário: " + horario
                + "\nStatus: " + (terminou == true ? "Realizada" : "Marcada")
                + "\n}";
    }
}
