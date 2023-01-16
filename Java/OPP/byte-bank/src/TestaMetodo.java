public class TestaMetodo {
    public static void main(String[] args) {
        Conta contaVictor = new Conta();
        contaVictor.titular = new Cliente();

        contaVictor.depositar(100);

        System.out.println(contaVictor.getSaldo());

        System.out.println(contaVictor.getSaldo());

        Conta contaMarcela = new Conta();

        contaVictor.transfere(100, contaMarcela);

        System.out.println(contaVictor.getSaldo());
        System.out.println(contaMarcela.getSaldo());
    }
}
