package model;

public class Veterinario {
    private int id;
    private String nome;
    private String endereco;
    private long cep;
    private String telefone;
    private String email;

    public Veterinario(int id, String nome, String endereco, long cep, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
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

    @Override
    public String toString() {
        return "Veterinario{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", cep=" + cep + ", telefone=" + telefone + ", email=" + email + '}';
    }

}
