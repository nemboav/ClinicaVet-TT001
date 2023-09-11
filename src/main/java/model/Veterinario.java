package model;

public class Veterinario {
    private String nome;
    private String endereco;
    private long cep;
    private String telefone;
    private String email;

    public Veterinario(String nome, String endereco, long cep, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public long getCep() {
        return cep;
    }
    
     public void setCep(long cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }
    
      public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 
    
}
