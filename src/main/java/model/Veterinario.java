package model;

public class Veterinario {
    private int id;
    private String nome;
    private String email;
    private String telefone;
   

    public Veterinario(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
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
        return "Veterinario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + '}';
    }
      

}
