package model;

import java.util.Date;

public class Tratamento {
    private Date dataInicial;
    private Date dataFinal;

    public Tratamento(Date dataInicial, Date dataFinal) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public Date getDataInicial() {
        return dataInicial;
    }
    
     public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }


    public Date getDataFinal() {
        return dataFinal;
    }
   
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    
}
