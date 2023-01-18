public class AutenticacacaoUtil {
    private int senha;

    public boolean autentica(int senha) {
        return senha == this.senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
}
