
import java.util.InputMismatchException;
import java.util.Scanner;

public class Metodos {

    // Declarar las variables encargadas de almacenar el ingreso mensual y el saldo
    // disponible.
    static double ingresoMensual = 0;
    public double saldoRestante = 0;

    // Configurar el ingreso mensual y los límites de gasto correspondientes a cada
    // categoría.
    public double[] configurarPresupuesto(Scanner sc, String[] categorias) {

        // Crear un arreglo para almacenar los límites de gasto de cada categoría.
        double[] limites = new double[categorias.length];

        // Solicitar y validar el ingreso mensual hasta recibir un valor correcto.
        while (true) {
            try {
                System.out.print("\nIngrese la cantidad de ingreso mensual: ");
                ingresoMensual = sc.nextDouble();

                // Comprobar que el ingreso mensual sea mayor que cero.
                if (ingresoMensual <= 0)
                    System.out.println("El ingreso mensual debe ser mayor que 0.");
                else {
                    // Inicializar el saldo restante con el ingreso mensual disponible.
                    saldoRestante = ingresoMensual;
                    break;
                }
            } catch (InputMismatchException ime) {

                // Informar cuando el dato ingresado no corresponda a un valor numérico.
                System.out.println("No ha ingresado un ingreso mensual valido, intentelo de nuevo.");

                // Limpiar el dato incorrecto del Scanner.
                sc.nextLine();
            }
        }

        // Recorrer las categorías para solicitar y validar cada límite de gasto.
        for (int i = 0; i < limites.length; i++) {

            // Repetir la solicitud hasta recibir un límite válido.
            while (true) {
                try {
                    System.out.print("\nIngrese el limite de gasto para los gastos (" + categorias[i] + "): ");
                    limites[i] = sc.nextDouble();

                    // Comprobar que el límite establecido sea mayor que cero.
                    if (limites[i] <= 0)
                        System.out.println("El limite debe ser mayor que 0.");
                    else
                        break;

                } catch (InputMismatchException ime) {

                    // Informar cuando el usuario ingrese un límite no numérico.
                    System.out.println("No ha ingresado un limite valido, intentelo de nuevo.");

                    // Limpiar el dato incorrecto del Scanner.
                    sc.nextLine();
                }
            }
        }

        // Consumir el salto de línea pendiente después de utilizar nextDouble().
        sc.nextLine();

        // Devolver el arreglo con los límites de gasto configurados.
        return limites;
    }

    // Registrar la categoría, el valor y el día correspondiente a un gasto.
    public Object[] registrarGastos(Scanner sc, String categoria) {

        // Declarar e inicializar las variables que almacenarán el día y el valor del
        // gasto.
        int diaMes = 1;
        double valor = 0;

        // Solicitar y validar el valor del gasto.
        while (true) {
            try {
                System.out.print("\nIngrese el valor del gasto: ");
                valor = sc.nextDouble();

                // Comprobar que el valor del gasto sea mayor que cero.
                if (valor <= 0)
                    System.out.println("El valor del gasto debe ser mayor que 0.");
                else
                    break;

            } catch (InputMismatchException ime) {

                // Informar cuando el valor ingresado no sea numérico.
                System.out.println("No ha ingresado un gasto valido, intentelo de nuevo.");

                // Limpiar el dato incorrecto del Scanner.
                sc.nextLine();
            }
        }

        // Solicitar y validar el día del mes en el que se realizó el gasto.
        while (true) {
            try {
                System.out.print("\nIngrese el dia del mes: ");
                diaMes = sc.nextInt();

                // Comprobar que el día se encuentre entre 1 y 31.
                if (diaMes < 1 || diaMes > 31)
                    System.out.println("El dia ingresado no esta dentro del rango comun (1 - 31).");
                else
                    break;

            } catch (InputMismatchException ime) {

                // Informar cuando el día ingresado no sea un número entero.
                System.out.println("No ha ingresado un dia valido, intentelo de nuevo.");

                // Limpiar el dato incorrecto del Scanner.
                sc.nextLine();
            }
        }

        // Consumir el salto de línea pendiente después de utilizar nextInt().
        sc.nextLine();

        // Crear un arreglo de tipo Object para reunir la categoría, el valor y el día
        // del gasto.
        Object[] gasto = { categoria, valor, diaMes };

        // Devolver la información del gasto para que pueda ser procesada en App.
        return gasto;
    }

    // Calcular y mostrar el porcentaje que representa cada categoría respecto al
    // gasto total.
    public void calcularPorcentajes(String[] categorias, double[] gastosTotales, double gastoTotal) {

        // Comprobar que exista al menos un gasto registrado antes de realizar las
        // divisiones.
        if (gastoTotal > 0) {

            // Inicializar la variable que acumulará la suma de los porcentajes
            // individuales.
            double porcentajeTotal = 0;

            // Mostrar los encabezados de la tabla de porcentajes.
            System.out.println("\n========== PORCENTAJE DE GASTOS ==========");
            System.out.printf("%-15s %15s %15s%n", "Categoria", "Total gastado", "Porcentaje");
            System.out.println("------------------------------------------------");

            // Recorrer las categorías para calcular y presentar sus porcentajes.
            for (int i = 0; i < categorias.length; i++) {

                // Calcular la proporción del gasto de la categoría respecto al gasto total.
                double porcentaje = (gastosTotales[i] / gastoTotal) * 100;

                // Mostrar el nombre, el valor acumulado y el porcentaje de la categoría.
                System.out.printf("%-15s $%14.2f %14.2f%%%n",
                        categorias[i], gastosTotales[i], porcentaje);

                // Acumular el porcentaje calculado para obtener el porcentaje total.
                porcentajeTotal += porcentaje;
            }

            // Mostrar la separación y el resultado general de la distribución de gastos.
            System.out.println("------------------------------------------------");
            System.out.printf("%-15s $%14.2f %14.2f%%%n", "TOTAL", gastoTotal, porcentajeTotal);

        } else {

            // Informar cuando todavía no existan gastos registrados.
            System.out.println("\nTodavia no se han registrado gastos.");
        }
    }

    // Mostrar el resumen general de los gastos, el saldo disponible y los valores
    // máximos.
    public void mostrarResumen(String[] categorias, double[][] gastosDiarios,
            double gastoTotal, double ahorroAcumulado) {

        // Comprobar que existan gastos registrados antes de calcular los valores
        // máximos.
        if (gastoTotal > 0) {

            // Declarar las variables que almacenarán el día y el valor del mayor gasto
            // diario.
            int diaMayor = 0;
            double gastoDiaMayor = 0;

            // Declarar las variables que almacenarán la categoría y el valor del mayor
            // gasto.
            String categoriaMayor = "";
            double gastoCatMayor = 0;

            // Mostrar el encabezado del resumen general.
            System.out.println("\n============================== RESUMEN GENERAL ==============================");
            System.out.printf("%-8s", "DIA");

            // Recorrer las categorías para mostrar sus nombres como encabezados de las
            // columnas.
            for (int i = 0; i < categorias.length; i++) {
                System.out.printf("%15s", categorias[i].toUpperCase());
            }

            // Mostrar la columna correspondiente al total diario.
            System.out.printf("%15s%n", "TOTAL");
            System.out.println("-------------------------------------------------------------------------------");

            // Recorrer las filas de la matriz para mostrar los gastos de cada día.
            // Identificar simultáneamente el día que presenta el mayor gasto acumulado.
            for (int i = 0; i < gastosDiarios.length; i++) {

                // Inicializar el acumulador de gastos correspondiente al día actual.
                double totalDia = 0;

                // Mostrar el número del día que se está procesando.
                System.out.printf("%-8d", i + 1);

                // Recorrer las columnas de la matriz para mostrar y sumar los gastos por
                // categoría.
                for (int j = 0; j < categorias.length; j++) {

                    // Mostrar el gasto registrado en la categoría correspondiente al día actual.
                    System.out.printf("$%14.2f", gastosDiarios[i][j]);

                    // Acumular el gasto de la categoría en el total diario.
                    totalDia += gastosDiarios[i][j];
                }

                // Mostrar el total de gastos correspondiente al día actual.
                System.out.printf("$%14.2f%n", totalDia);

                // Comparar el total diario con el mayor gasto encontrado hasta el momento.
                if (totalDia > gastoDiaMayor) {

                    // Actualizar el mayor gasto diario y almacenar el número del día
                    // correspondiente.
                    gastoDiaMayor = totalDia;
                    diaMayor = i + 1;
                }
            }

            // Mostrar una separación antes de presentar la información general.
            System.out.println("-------------------------------------------------------------------------------");

            // Recorrer las columnas de la matriz para calcular el gasto total de cada
            // categoría.
            for (int j = 0; j < categorias.length; j++) {

                // Inicializar el acumulador correspondiente a la categoría actual.
                double totalCategoria = 0;

                // Recorrer todas las filas para sumar los gastos diarios de la categoría.
                for (int i = 0; i < gastosDiarios.length; i++)
                    totalCategoria += gastosDiarios[i][j];

                // Comparar el total de la categoría con el mayor gasto encontrado.
                if (totalCategoria > gastoCatMayor) {

                    // Actualizar el mayor gasto por categoría y almacenar su nombre.
                    gastoCatMayor = totalCategoria;
                    categoriaMayor = categorias[j];
                }
            }

            // Mostrar la información financiera general del usuario.
            System.out.println("\nGasto total: $" + gastoTotal + ".");
            System.out.println("Saldo restante: $" + saldoRestante + ".");

            // Comprobar si existen ahorros acumulados antes de mostrar su valor.
            if (ahorroAcumulado > 0) {
                System.out.println("Ahorro acumulado: $" + ahorroAcumulado + ".");
            }

            // Mostrar el día que presentó el mayor gasto y el valor correspondiente.
            System.out.println("\nEl dia de mayor gasto fue el dia #" + diaMayor
                    + " con un gasto de $" + gastoDiaMayor + ".");

            // Mostrar la categoría que presentó el mayor gasto y su valor acumulado.
            System.out.println("La categoria con mayor gasto fue: " + categoriaMayor
                    + " con un total de $" + gastoCatMayor + ".");

        } else {

            // Informar cuando todavía no existan gastos registrados.
            System.out.println("\nTodavia no se han registrado gastos.");
        }
    }

    // Evaluar una propuesta de ahorro mensual y determinar si puede realizarse.
    public double proponerAhorroMensual(Scanner sc, double gastoTotal) {

        // Declarar la variable que almacenará la cantidad de dinero propuesta para
        // ahorrar.
        double proAhorro = 0;

        // Solicitar y validar la propuesta de ahorro hasta recibir un valor correcto.
        while (true) {
            try {
                System.out.print("\nIngrese su propuesta de ahorro: ");
                proAhorro = sc.nextDouble();

                // Comprobar que la propuesta de ahorro sea mayor que cero.
                if (proAhorro <= 0)
                    System.out.println("El valor de la propuesta de ahorro debe ser mayor que 0.");
                else
                    break;

            } catch (InputMismatchException ime) {

                // Informar cuando el dato ingresado no corresponda a un valor numérico.
                System.out.println("No ha ingresado una propuesta de ahorro valida, intentelo de nuevo.");

                // Limpiar el dato incorrecto del Scanner.
                sc.nextLine();
            }
        }

        // Consumir el salto de línea pendiente después de utilizar nextDouble().
        sc.nextLine();

        // Mostrar los datos financieros utilizados para evaluar la propuesta.
        System.out.println("\nRESULTADO DE SU PROPUESTA DE AHORRO: "
                + "\nIngreso mensual: $" + ingresoMensual
                + "\nGastos acumulados: $" + gastoTotal
                + "\nSaldo restante: $" + saldoRestante
                + "\n\nAhorro mensual propuesto: $" + proAhorro);

        // Comparar la propuesta con el saldo disponible para determinar si es
        // alcanzable.
        if (saldoRestante >= proAhorro) {

            // Descontar el ahorro propuesto del saldo restante disponible.
            saldoRestante -= proAhorro;

            // Informar que la propuesta puede realizarse y mostrar el saldo actualizado.
            System.out.println("\n¡El ahorro es alcanzable!"
                    + "\nSaldo restante despues del ahorro: $" + saldoRestante + ". ");

            // Devolver el valor del ahorro aceptado para acumularlo en App.
            return proAhorro;

        } else {

            // Informar que la propuesta supera el dinero disponible.
            System.out.println("\n¡El ahorro no es alcanzable!");

            // Devolver cero para indicar que no se realizó ningún ahorro.
            return 0;
        }
    }
}