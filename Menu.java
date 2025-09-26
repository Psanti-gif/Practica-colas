import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Metodos m = new Metodos();

        int opcion = 0;
        boolean bandera = true;

        while (bandera) {
            System.out.println("1. Tomar turno");
            System.out.println("2. Ver turno actual ");
            System.out.println("3. Ver estado de clientes (Pendientes / Atendidos)");
            System.out.println("4. Ver inventario");
            System.out.println("5. Agregar artículo");
            System.out.println("6. Dar de baja artículo");
            System.out.println("7. Atender cliente ");
            System.out.println("8. Ver ventas ");
            System.out.println("9. Reporte: Clientes atendidos");
            System.out.println("10. Reporte: Ventas totales");
            System.out.println("11. Reporte: Productos vendidos");
            System.out.println("12. Salir");
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
                    m.verEstadoGeneral();
                    break;
                case 4:
                    m.verInventario();
                    break;
                case 5:
                    m.agregarArticulo();
                    break;

                case 6:
                    m.darDeBajaArticulo();
                    break;
                case 7:
                    m.atenderTurno();
                    break;
                case 8:
                    m.verVentas();
                    break;
                case 9:
                    m.reporteClientesAtendidos();
                    break;
                case 10:
                    m.reporteVentasTotales();
                    break;
                case 11:
                    m.reporteProductosVendidos();
                    break;
                case 12:
                    bandera = false;
                    System.out.println("Saliendo...");
                    break;
            }
        }
    }

}