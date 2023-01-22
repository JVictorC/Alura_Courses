public class Fluxo {

    public static void main(String[] args) {
        System.out.println("Ini do main");
        metodo1();
        System.out.println("Fim do main");
    }

    private static void metodo1() {
        System.out.println("Ini do metodo1");
        try {
            metodo2();

        } catch (NullPointerException | ArithmeticException e) {
            System.out.println(e);
        } catch (ContaExeptionSaldo e) {

            System.out.println(e.getMessage());
        }
        System.out.println("fim do metodo1");
    }

    private static void metodo2() {
        System.out.println("Ini do metodo2");

        ArithmeticException exeption = new ArithmeticException();

        Conta c = new Conta();

        c.deposita(100);

        c.saca(101);

        System.out.println(c.getSaldo());



        System.out.println("Fim do metodo2");
    }
}