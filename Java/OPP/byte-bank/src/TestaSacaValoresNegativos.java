public class TestaSacaValoresNegativos {
    public static void main(String[] args) {
        Conta conta = new Conta();

        conta.depositar(100);

        conta.saca(200);

        conta.depositar(200);

        System.out.println(conta.getSaldo());
    }
}
