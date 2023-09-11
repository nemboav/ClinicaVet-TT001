package model;

import java.util.Date;

public class Consulta {
    private Date data;
    private String relato;

    public Consulta(Date data, String relato) {
        this.data = data;
        this.relato = relato;
    }

    public Date getData() {
        return data;
    }
    
     public void setData(Date data) {
        this.data = data;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }
    
    
    
}
