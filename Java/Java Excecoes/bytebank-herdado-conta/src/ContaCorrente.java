public class ContaCorrente extends Conta implements Tributavel {

    public ContaCorrente(int agencia, int numero) {
        super(agencia, numero);
    }


    @Override
    public void saca(double valor) throws SaldoInsuficienteExeption {
        double valorSacaComTaxa = valor + 0.20;
        super.saca(valorSacaComTaxa);
    }

    @Override
    public double getValorImposto() {
        return super.getSaldo() * .01;
    }
}
