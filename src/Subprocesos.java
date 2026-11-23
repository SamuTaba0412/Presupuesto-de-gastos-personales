import java.util.InputMismatchException;
import java.util.Scanner;

public class Subprocesos {
    Scanner sc = new Scanner(System.in);
    static double ingresoMensual;

    public double[] configurarPresupuesto(String[] categorias) {
        double[] limites = new double[categorias.length];

        while (true) {
            try {
                System.out.print("\nIngrese la cantidad de ingreso mensual: ");
                ingresoMensual = sc.nextDouble();

                if (ingresoMensual <= 0)
                    System.out.println("El ingreso mensual debe ser mayor que 0");
                else
                    break;
            } catch (InputMismatchException ime) {
                System.out.println("No ha ingresado un ingreso mensual valido, intentelo de nuevo.");
            }
        }

        for (int i = 0; i < limites.length; i++) {
            while (true) {
                try {
                    System.out.print("\nIngrese el limite de gasto para los gastos (" + categorias[i] + "): ");
                    limites[i] = sc.nextDouble();

                    if (limites[i] <= 0)
                        System.out.println("El limite debe ser mayor que 0");
                    else
                        break;
                } catch (InputMismatchException ime) {
                    System.out.println("No ha ingresado un limite valido, intentelo de nuevo.");
                }
            }
        }

        return limites;
    }
}
