public class CriarConta {

    public static void main(String[] args) {
        Conta primeiraConta = new Conta();

        primeiraConta.depositar(200);
        primeiraConta.titular = new Cliente();
        primeiraConta.agencia = 1;
        primeiraConta.numero = 10;

        System.out.println(primeiraConta.getSaldo());


        Conta segundaConta = new Conta();


        segundaConta.depositar(50);
    }
}
