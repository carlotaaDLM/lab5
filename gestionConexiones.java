import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class GestorConexiones {
    private ArrayList<ConexionFibra> listaFibra = new ArrayList<>();
    private ArrayList<ConexionMovil> listaMovil = new ArrayList<>();

    public void registrarConexion(Scanner sc) {
        System.out.print("Tipo de conexión (F: Fibra / M: Móvil): ");
        String tipo = sc.nextLine();
        
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Cliente: ");
        String cliente = sc.nextLine();
        System.out.print("Distrito: ");
        String distrito = sc.nextLine();
        
        try {
            System.out.print("Velocidad contratada (Mbps): ");
            int velocidad = Integer.parseInt(sc.nextLine()); //

            if (tipo.equalsIgnoreCase("F")) {
                System.out.print("Metros de cable: ");
                int metros = Integer.parseInt(sc.nextLine()); //[cite: 3]
                listaFibra.add(new ConexionFibra(codigo, cliente, distrito, velocidad, metros));
                System.out.println("Conexión registrada.");
            } else if (tipo.equalsIgnoreCase("M")) {
                System.out.print("Banda: ");
                String banda = sc.nextLine();
                listaMovil.add(new ConexionMovil(codigo, cliente, distrito, velocidad, banda));
                System.out.println("Conexión registrada.");
            } else {
                System.out.println("Tipo no válido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número entero."); //[cite: 3]
        }
    }

    public void listarConexiones() {
        for (ConexionFibra cf : listaFibra) {
            cf.mostrarFicha();
        }
        for (ConexionMovil cm : listaMovil) {
            cm.mostrarFicha();
        }
    }

    public void registrarPruebas(Scanner sc) {
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        
        ConexionFibra conexion = null;
        for (ConexionFibra cf : listaFibra) {
            if (cf.getCodigo().equals(codigo)) {
                conexion = cf;
                break;
            }
        }
        
        if (conexion == null) {
            System.out.println("Conexión no encontrada");
            return;
        }
        
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print("Medición " + (i + 1) + " (Mbps): ");
                double valor = Double.parseDouble(sc.nextLine()); //[cite: 3]
                conexion.registrarMedicion(i, valor);
            }
            
            double promedio = conexion.calcularPromedio();
            double maxima = conexion.obtenerMaxima();
            
            System.out.println("Promedio: " + promedio + " Mbps | Máxima: " + maxima + " Mbps");
            
            if (promedio >= (conexion.getVelocidadContratada() * 0.8)) {
                System.out.println("Estado: CUMPLE");
            } else {
                System.out.println("Estado: NO CUMPLE");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor ingresado incorrecto."); //[cite: 3]
        }
    }

    public void reportePorDistrito() {
        HashSet<String> distritos = new HashSet<>();
        HashMap<String, Integer> conteo = new HashMap<>();

        for (ConexionFibra cf : listaFibra) {
            String d = cf.getDistrito();
            distritos.add(d);
            conteo.put(d, conteo.getOrDefault(d, 0) + 1);
        }
        
        for (ConexionMovil cm : listaMovil) {
            String d = cm.getDistrito();
            distritos.add(d);
            conteo.put(d, conteo.getOrDefault(d, 0) + 1);
        }

        System.out.println("Distritos con cobertura: " + distritos.size());
        for (String d : distritos) {
            System.out.println(d + ": " + conteo.get(d));
        }
    }
}
