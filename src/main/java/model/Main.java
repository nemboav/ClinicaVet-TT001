package model;


public class Main {

    public static void main(String[] args) {
        System.out.println("Teste Vet");
        ClienteDAO.getInstance().create("Plinio", "Campinas", "02134", "prvilela@unicamp.br", "707070-70");
        Cliente cliente = ClienteDAO.getInstance().retrieveById(1);
        System.out.println(cliente.getNome());
    }
}
