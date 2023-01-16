public class TestaBanco {

    public static void main(String[] args) {
        Cliente victor = new Cliente();

        victor.nome = "Victor";
        victor.cpf = "222.222.222-22";
        victor.profissao = "Programador";


        Conta contaVictor = new Conta();

        contaVictor.titular = victor;

        contaVictor.depositar(200);

    }
}
