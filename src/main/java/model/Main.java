package model;


public class Main {

    public static void main(String[] args) {
        System.out.println("Teste ClinicaVet");
        ClienteDAO.getInstance().create("Natalia", "Guarulhos", "02134", "n12345@unicamp.br", "11111-1111");
        Cliente cliente = ClienteDAO.getInstance().retrieveById(1);
        System.out.println(cliente.getNome());
    }
}
