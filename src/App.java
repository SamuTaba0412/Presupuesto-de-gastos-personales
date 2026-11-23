public class App {
    public static void main(String[] args) throws Exception {
        Subprocesos subp = new Subprocesos();
        
        String[] categorias = {"Transporte", "Comida", "Estudio", "Ocio", "Otros"};
        
        System.out.println("\n===============================");
        System.out.println("BIENVENIDO AL GESTOR DE PRESUPUESTO");
        System.out.println("===============================");
        double[] limites = subp.configurarPresupuesto(categorias);

        // for (int i = 0; i < categorias.length; i++){
        //     System.out.println(categorias[i] + ": " + limites[i] + "$.");
        // }
    }
}
