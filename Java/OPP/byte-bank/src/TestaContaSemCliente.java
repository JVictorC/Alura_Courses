public class TestaContaSemCliente {
    public static void main(String[] args) {
        Conta contaMarcela = new Conta();



/*
        Class Conta não é instancida o java lanca um erro de null
        colocando um valor default nao temos problema com isso
        contaMarcela.titular.nome = "Marcela";
        System.out.println(contaMarcela.titular.nome);
*/

        contaMarcela.titular = new Cliente();
        contaMarcela.titular.nome = "Marcela";

        System.out.println(contaMarcela.titular.nome);
    }
}
