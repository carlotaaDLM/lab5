public class ConexionMovil extends Conexion { //[cite: 1]
    private String banda;

    public ConexionMovil(String codigo, String cliente, String distrito, int velocidadContratada, String banda) {
        super(codigo, cliente, distrito, velocidadContratada); // Llamada al padre[cite: 1]
        this.banda = banda;
    }

    public void mostrarFicha() {
        System.out.println("[5G]");
        super.mostrarDatos(); // Invocación del método del padre[cite: 1]
        System.out.println("Banda: " + banda);
    }
}
