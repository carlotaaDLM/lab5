public class ConexionFibra extends Conexion { //[cite: 1]
    private int metrosCable;

    public ConexionFibra(String codigo, String cliente, String distrito, int velocidadContratada, int metrosCable) {
        super(codigo, cliente, distrito, velocidadContratada); // Llamada al padre[cite: 1]
        this.metrosCable = metrosCable;
    }

    public void mostrarFicha() {
        System.out.println("[FIBRA]");
        super.mostrarDatos(); // Invocación del método del padre[cite: 1]
        System.out.println("Metros de cable: " + metrosCable);
    }
}
