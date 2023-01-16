public class TestaCondicional {
    public static void main(String[] args) {
        System.out.println("teste condicionais");


        int idade = 15;
        int quantidadePessoas = 2;

        if (idade >= 18) System.out.println("Maior De Idade");
        else if (idade < 18 && quantidadePessoas > 1) System.out.println("Menor De Idade, mas acompanhado");
        else System.out.println("Menor de Idade");
    }
}
