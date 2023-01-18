public class ContaCorrente extends Conta implements Tributavel {

    public ContaCorrente(int agencia, int numero) {
        super(agencia, numero);
    }


    @Override
    public boolean saca(double valor) {
        double valorSacaComTaxa = valor + 0.20;
        return super.saca(valorSacaComTaxa);
    }

    @Override
    public double getValorImposto() {
        return super.getSaldo() * .01;
    }
}
