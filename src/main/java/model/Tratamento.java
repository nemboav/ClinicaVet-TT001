package model;

import java.util.Date;

public class Tratamento {
    private int idTratamento;
    private int idAnimal;
    private String nome;
    private String dataInicial;
    private String dataFinal;
    private boolean terminou;

    public Tratamento(int idTratamento, int idAnimal, String nome, String dataInicial, String dataFinal, boolean terminou) {
        this.idTratamento = idTratamento;
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.terminou = terminou;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }

    

    @Override
    public String toString() {
        return "Tratamento{" + "idTratamento=" + idTratamento 
                + ", idAnimal=" + idAnimal + ", nome=" + nome 
                + ", dataInicial=" + dataInicial + ", dataFinal=" 
                + dataFinal + ", terminou=" + terminou + '}';
    }

    
}
