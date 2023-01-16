public class TestaCondicional2 {
    public static void main(String[] args) {
        System.out.println("teste condicionais");


        int idade = 15;
        int quatidadePessoa = 2;
        boolean acompanhado = quatidadePessoa > 1;

        if (idade >= 18) System.out.println("Maior De Idade");
        else if (idade < 18 && acompanhado) System.out.println("Menor De Idade, mas acompanhado");
        else System.out.println("Menor de Idade");
    }
}
