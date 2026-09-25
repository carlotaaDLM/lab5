public class Conexion {
    private String codigo;
    private String cliente;
    private String distrito;
    private int velocidadContratada;
    private double[] mediciones;

    public Conexion(String codigo, String cliente, String distrito, int velocidadContratada) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.distrito = distrito;
        this.velocidadContratada = velocidadContratada;
        this.mediciones = new double[3];
    }

    public String getCodigo() { return codigo; }
    public String getDistrito() { return distrito; }
    public int getVelocidadContratada() { return velocidadContratada; }

    public void mostrarDatos() {
        System.out.println("Código: " + codigo + " | Cliente: " + cliente + " | Distrito: " + distrito + " | " + velocidadContratada + " Mbps");
    }

    public void registrarMedicion(int posicion, double valor) {
        this.mediciones[posicion] = valor;
    }

    public double calcularPromedio() {
        double suma = 0;
        for (double medicion : mediciones) {
            suma += medicion;
        }
        return suma / mediciones.length;
    }

    public double obtenerMaxima() {
        double maxima = mediciones[0];
        for (int i = 1; i < mediciones.length; i++) {
            if (mediciones[i] > maxima) {
                maxima = mediciones[i];
            }
        }
        return maxima;
    }
}
