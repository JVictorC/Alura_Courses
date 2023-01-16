public class Conta {
    private double saldo;
    int agencia;
    int numero;
    Cliente titular;

    double getSaldo() { return this.saldo; }

    public void depositar(double valorDeEntrada) {
        saldo += valorDeEntrada;
    }

    public boolean saca(double valor) {

        if(saldo >=  valor) {
            saldo -= valor;

            return true;
        }

        return false;
    }


    public boolean transfere(double valor, Conta destino ) {
        boolean foiPossivelSacar = saca(valor);

        if(!foiPossivelSacar) return false;

        destino.depositar(valor);


        return true;
    }

}