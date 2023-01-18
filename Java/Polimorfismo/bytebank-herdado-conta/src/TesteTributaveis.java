public class TesteTributaveis {
    public static void main(String[] args) {
        ContaCorrente cc = new ContaCorrente(222,333);
        cc.deposita(100);


        SeguroDeVida sv = new SeguroDeVida();


        CalculadorDeImposto c = new CalculadorDeImposto();


        c.registar(sv);
        c.registar(cc);

        System.out.println(c.getTotalImposto());
    }
}
