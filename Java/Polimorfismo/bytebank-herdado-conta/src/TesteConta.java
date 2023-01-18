public class TesteConta {
    public static void main(String[] args) {
        ContaCorrente cc = new ContaCorrente(111,111);
        ContaPoupanca cp = new ContaPoupanca(222,222);
        cc.deposita(100);

        cp.deposita(200);

        cc.transfere(10, cp);

        System.out.println(cp.getSaldo());
        System.out.println(cc.getSaldo());
    }
}
