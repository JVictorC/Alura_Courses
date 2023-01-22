public class TestaConexao {
    public static void main(String[] args) {

        try(Conexao c = new Conexao()) {
            c.leDados();
        } catch (IllegalStateException e) {
            System.out.println("Aconteceu Um Erro");
        }



        Conexao c = null;

//        try {
//            c = new Conexao();
//            c.leDados();
//        } catch (IllegalStateException e) {
//            System.out.println("Aconteceu Um Erro");
//        } finally {
//           if(c != null) {
//               c.close();
//           }
//        }
    }
}
