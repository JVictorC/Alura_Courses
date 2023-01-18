public class SistemaInterno {
    private int senha = 2222;

    public void autentica(Autenticavel g) {
        boolean autenticou = g.autentica(this.senha);

        if(autenticou) System.out.println("Pode Entrar no Sistema");

         else System.out.println("Nao pode entrar no sistema");
    }
}
