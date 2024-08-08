package model;

public class Animal {
    private int id;
    private String nome;
    private String idade;
    private String sexo;
    private int idEspecie;
    private int idCliente;
    private String especie;

    public Animal(int id, String nome, String idade, String sexo, int idEspecie, int idCliente, String especie) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
        this.especie = especie;
    }

    public int getId() {
        return id;
    }
    
     public void setIdAnimal(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }
    
     public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
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
   
    public String getEspecie() {
       return especie;
    }
    
    public void setEspecie(String especie) {
       this.especie = especie;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nome=" + nome + ", idade=" 
                + idade + ", sexo=" + sexo + ", idEspecie=" + idEspecie 
                + ", idCliente=" + idCliente + ", especie=" + especie + '}';
    }

   

}
