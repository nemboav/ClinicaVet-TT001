package model;

public class Animal {
    private int id;
    private String nome;
    private int idade;
    private int sexo;
    private int idEspecie;
    private int idCliente;

    public Animal(int id, String nome, int idade, int sexo, int idEspecie, int idCliente) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }
    
     public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }
    
     public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getSexo() {
        return sexo;
    }
    
    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getIdEspecie() {
        return idEspecie;
    }
    
      public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getIdCliente() {
        return idCliente;
    }

   public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + ", idEspecie=" + idEspecie + ", idCliente=" + idCliente + '}';
    }

}
