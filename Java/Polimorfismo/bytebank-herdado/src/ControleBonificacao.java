public class ControleBonificacao {

    private double soma;

    public double getSoma() {
        return soma;
    }

    public void registra(Funcionario g) {
        double boni = g.getBonificacao();

        this.soma += boni;
    }
}
