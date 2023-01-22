public class Conta {
    private int saldo;
    void deposita(int novoSaldo) {

        this.saldo += novoSaldo;
    };

    void saca(int valorASacar) {
        if(valorASacar > saldo) throw new ContaExeptionSaldo("Saldo Insuficiente");


        this.saldo -= valorASacar;
    }

    public int getSaldo() {
        return saldo;
    }


}

