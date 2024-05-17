import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        ConexionApi conexionApi = new ConexionApi();
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;

        String menu = """
                *********************************************
                Sea Bienvenido/a Al Conversor De Monedas =]
                \n1) Dolar =>> Peso Argentino.
                2) Peso Argentino Dolar =>> Dolar.
                3) Dolar =>> Real Brasileño.
                4) Real Brasileño =>> Dolar.
                5) Dolar =>> Peso Colombiano.
                6) Peso Colombiano =>> Dolar.
                7) Salir.
                Elija Una Opcion Valida:
                *********************************************
                """;

        while (!salir) {
            System.out.println(menu);
            int option = entrada.nextInt();
            entrada.nextLine();

            switch (option) {
                case 1:
                    cambioMoneda(conexionApi, entrada, "USD", "ARS");
                    break;
                case 2:
                    cambioMoneda(conexionApi, entrada, "ARS", "USD");
                    break;
                case 3:
                    cambioMoneda(conexionApi, entrada, "USD", "BRL");
                    break;
                case 4:
                    cambioMoneda(conexionApi, entrada, "BRL", "USD");
                    break;
                case 5:
                    cambioMoneda(conexionApi, entrada, "USD", "COP");
                    break;
                case 6:
                    cambioMoneda(conexionApi, entrada, "COP", "USD");
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elija una opción válida.");
            }
        }

        System.out.println("Gracias Por Usar El Conversor De Monedas.");
        entrada.close();
    }

    private static void cambioMoneda(ConexionApi conexionApi, Scanner entrada, String deDivisa, String aLaOtraDivisa) {
        try {
            Monedas monedas = conexionApi.buscaMonedas(deDivisa);
            System.out.print("Ingrese El Monto A Convertir: ");
            double monto = entrada.nextDouble();
            entrada.nextLine();

            Double valorDivisa = monedas.conversion_rates().get(aLaOtraDivisa);

            if (valorDivisa == null) {
                System.out.println("No Se Encontró La Tasa De Conversión Para La Moneda Seleccionada.");
            } else {
                double convierteValor = monto * valorDivisa;
                System.out.printf("El Valor De %.2f %s en %s es: %.3f%n", monto, deDivisa, aLaOtraDivisa, convierteValor);
            }
        } catch (IOException | InterruptedException e) {

        }
    }
}
