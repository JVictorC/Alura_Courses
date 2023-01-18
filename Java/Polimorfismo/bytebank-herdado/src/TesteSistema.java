public class TesteSistema {
    public static void main(String[] args) {
        Gerente g = new Gerente();
        g.setSenha(2222);

        SistemaInterno i = new SistemaInterno();

        i.autentica(g);
    }
}
