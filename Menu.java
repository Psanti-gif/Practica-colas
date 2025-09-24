import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Metodos m = new Metodos();

        int opcion = 0;
        boolean bandera = true;

        while (bandera) {
            System.out.println("\n--- HU00001: Registro de Clientes ---");
            System.out.println("1. Tomar turno");
            System.out.println("2. Ver turno actual (HU00001)");
            System.out.println("3. Atender cliente (HU00002)");
            System.out.println("4. Salir");
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
                    bandera = false;
                    System.out.println("Saliendo...");
                
            }
        }
    }
    
}
