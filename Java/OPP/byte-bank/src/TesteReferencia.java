public class TesteReferencia {
    public static void main(String[] args) {
        Conta primeiraConta = new Conta();

        primeiraConta.depositar(300);

        System.out.println("Saldo Da Primeira Conta: " + primeiraConta.getSaldo());

        // Ref toda vez que o primeira conta mudar o da segunda muda ou se mudar o da segunda muda o da primeira
        Conta segundaConta = primeiraConta;


        System.out.println(segundaConta.getSaldo());

        primeiraConta.depositar(100);

        System.out.println(segundaConta.getSaldo());

        System.out.println(primeiraConta);
        System.out.println(segundaConta);

    }
}
