public class Gerente extends FuncionarioAutenticavel {

    @Override
    public double getBonificacao() {
        return super.getSalario();
    }
}
