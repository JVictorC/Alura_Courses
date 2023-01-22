public class CalculadorDeImposto {
    private double totalImposto;

    void registar(Tributavel t) {
        double valor = t.getValorImposto();

        this.totalImposto += valor;
    }

    public double getTotalImposto() {
        return totalImposto;
    }

}
