
import java.util.Scanner;

public class App {

    // Crear los objetos necesarios para utilizar los métodos y leer los datos ingresados.
    static Metodos metodos = new Metodos();
    static Scanner sc = new Scanner(System.in);

    // Declarar el arreglo que almacena los nombres de las categorías de gastos.
    static String[] categorias = {
        "Transporte", "Comida", "Estudio", "Ocio", "Otros"
    };

    // Crear una matriz de 31 filas y 5 columnas para almacenar los gastos diarios.
    // Cada fila representa un día del mes y cada columna representa una categoría.
    static double[][] gastosDiarios = new double[31][5];

    // Crear un arreglo para acumular el total gastado en cada categoría.
    static double[] gastosTotales = new double[5];

    // Declarar el arreglo que almacenará los límites de gasto configurados por el usuario.
    static double[] gastosLimites;

    // Declarar las variables encargadas de almacenar el gasto general y el ahorro acumulado.
    static double gastoTotal = 0;
    static double ahorroAcumulado = 0;

    public static void main(String[] args) throws Exception {

        // Mostrar el mensaje de bienvenida al iniciar el programa.
        System.out.println("\n===============================");
        System.out.println("BIENVENIDO AL GESTOR DE PRESUPUESTO");
        System.out.println("===============================");

        // Configurar el ingreso mensual y los límites de gasto de cada categoría.
        gastosLimites = metodos.configurarPresupuesto(sc, categorias);

        // Declarar una variable para almacenar la opción seleccionada en el menú.
        String opcion = "0";

        // Repetir el menú hasta que el usuario seleccione la opción para salir.
        do {
            System.out.println("\n===============================");
            System.out.println("MENU DE REGISTRO DE GASTOS");
            System.out.println("===============================");
            System.out.println("Ingrese una de las siguientes opciones para empezar a ingresar sus gastos:");
            System.out.println("1: Registrar gastos de transporte."
                    + "\n2: Registrar gastos de comida."
                    + "\n3: Registrar gastos de estudio."
                    + "\n4: Registrar gastos de ocio."
                    + "\n5: Registrar gastos generales (otros)."
                    + "\n6: Mostrar porcentaje gastado."
                    + "\n7: Proponer ahorro mensual."
                    + "\n8: Mostrar resumen."
                    + "\n9: Salir");

            // Leer la opción ingresada por el usuario.
            opcion = sc.nextLine();

            // Evaluar la opción seleccionada y ejecutar el procedimiento correspondiente.
            switch (opcion) {

                case "1":
                    // Registrar los gastos correspondientes a la categoría Transporte.
                    ingresarGasto(0);
                    break;

                case "2":
                    // Registrar los gastos correspondientes a la categoría Comida.
                    ingresarGasto(1);
                    break;

                case "3":
                    // Registrar los gastos correspondientes a la categoría Estudio.
                    ingresarGasto(2);
                    break;

                case "4":
                    // Registrar los gastos correspondientes a la categoría Ocio.
                    ingresarGasto(3);
                    break;

                case "5":
                    // Registrar los gastos correspondientes a la categoría Otros.
                    ingresarGasto(4);
                    break;

                case "6":
                    // Calcular y mostrar el porcentaje que representa cada categoría del gasto total.
                    metodos.calcularPorcentajes(categorias, gastosTotales, gastoTotal);
                    break;

                case "7":
                    // Solicitar una propuesta de ahorro y determinar si es alcanzable.
                    double proAhorro = metodos.proponerAhorroMensual(sc, gastoTotal);

                    // Acumular únicamente los valores de ahorro que hayan sido aceptados.
                    ahorroAcumulado += proAhorro;
                    break;

                case "8":
                    // Mostrar el resumen general de los gastos y la información financiera.
                    metodos.mostrarResumen(categorias, gastosDiarios, gastoTotal, ahorroAcumulado);
                    break;

                case "9":
                    // Finalizar la ejecución del programa.
                    System.out.println("\nGracias por usar!");
                    break;

                default:
                    // Informar cuando el usuario ingrese una opción que no existe en el menú.
                    System.out.println("Opcion ingresada no valida.");
                    break;
            }

        } while (!opcion.equals("9"));

        // Cerrar el objeto Scanner al finalizar la ejecución del programa.
        sc.close();
    }

    // Registrar un gasto y actualizar los acumuladores correspondientes a su categoría y día.
    public static void ingresarGasto(int indCategoria) {

        // Obtener la categoría, el valor y el día del gasto mediante el método registrarGastos().
        Object[] gasto = metodos.registrarGastos(sc, categorias[indCategoria]);

        // Acumular el valor del gasto en la posición correspondiente de la matriz diaria.
        // Restar 1 al día porque los índices de los arreglos comienzan desde 0.
        gastosDiarios[(int) gasto[2] - 1][indCategoria] += (double) gasto[1];

        // Acumular el valor del gasto en el total de su categoría.
        gastosTotales[indCategoria] += (double) gasto[1];

        // Comparar el gasto acumulado de la categoría con el límite establecido.
        if (gastosTotales[indCategoria] > gastosLimites[indCategoria]) {

            // Calcular el porcentaje del límite que ha sido utilizado.
            double porcentajeUsado =
                    (gastosTotales[indCategoria] / gastosLimites[indCategoria]) * 100;

            // Calcular la cantidad de dinero que supera el límite establecido.
            double exceso =
                    gastosTotales[indCategoria] - gastosLimites[indCategoria];

            // Informar que el límite de gasto de la categoría ha sido superado.
            System.out.println("\n¡Limite de (" + categorias[indCategoria] + ") superado!");
            System.out.println("Total acumulado: $" + gastosTotales[indCategoria] + ".");
            System.out.println("Porcentaje utilizado: " + porcentajeUsado + "%.");
            System.out.println("Exceso: $" + exceso + ".");
        }

        // Acumular el valor del gasto en el total general de gastos.
        gastoTotal += (double) gasto[1];

        // Descontar el valor del gasto del saldo restante disponible.
        metodos.saldoRestante -= (double) gasto[1];
    }
}