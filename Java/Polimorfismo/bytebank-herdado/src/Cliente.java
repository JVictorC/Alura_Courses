public class Cliente implements Autenticavel {
    private int senha;
    private AutenticacacaoUtil autenticador;

    public Cliente() {
        this.autenticador = new AutenticacacaoUtil();
    }

    @Override
    public boolean autentica(int senha) {
        return autenticador.autentica(senha);
    }

    @Override
    public void setSenha(int senha) {
        autenticador.setSenha(senha);
    }
}
