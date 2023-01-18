public class TestaGerente {
    public static void main(String[] args) {
        Gerente g1 = new Gerente();

        g1.setNome("Marocs");
        g1.setCpf("321321312312");
        g1.setSalario(6000);


        System.out.println(g1.getBonificacao());
        System.out.println(g1.getNome());

//        g1.setSenha(123);
//
//        System.out.println(g1.autentica(123));
    }
}
