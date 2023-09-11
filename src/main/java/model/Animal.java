package model;

public class Animal {
    private String nome;
    private int idade;
    private int sexo;

    public Animal(String nome, int idade, int sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getSexo() {
        return sexo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }
    
    
}
