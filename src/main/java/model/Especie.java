package model;

public class Especie {
    private int idEspecie;
    private String nome;

    public Especie(int id, String nome) {
        this.idEspecie = id;
        this.nome = nome;
    }

    public int getIdEspecie() {
        return idEspecie;
    }
    
     public void setIdEspecie(int id) {
        this.idEspecie = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Especie{" + "idEspecie=" + idEspecie + ", nome=" + nome + '}';
    }

}
