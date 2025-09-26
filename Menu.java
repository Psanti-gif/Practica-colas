import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Metodos m = new Metodos();

        int opcion = 0;
        boolean bandera = true;

        while (bandera) {
            System.out.println("1. Tomar turno");
            System.out.println("2. Ver turno actual (HU00001)");
            System.out.println("3. Atender cliente (HU00002)");
            System.out.println("4. Ver ventas (HU00003)");
            System.out.println("5. Reporte: Clientes atendidos");
            System.out.println("6. Reporte: Ventas totales");
            System.out.println("7. Reporte: Productos vendidos");
            System.out.println("8. Salir");
            System.out.println("Seleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    m.tomarTurno();
                    break;
                case 2:
                    m.verTurnoActual();
                    break;
                case 3:
                    m.atenderTurno();
                    break;
                case 4:
                    m.verVentas();
                    break;
                case 5:
                    m.reporteClientesAtendidos();
                    break;
                case 6:
                    m.reporteVentasTotales();
                    break;
                case 7:
                    m.reporteProductosVendidos();
                    break;
                case 8:
                    bandera = false;
                    System.out.println("Saliendo...");
                    break;
            }
        }
    }
    
}
