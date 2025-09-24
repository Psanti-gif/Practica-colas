import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Metodos {
    private Queue<Turno> colaTurnos = new LinkedList<>();
    private Queue<Turno> atendidos = new LinkedList<>();
    private int contador = 1;
    private Scanner sc = new Scanner(System.in);

    public Turno tomarTurno() {
        Cliente cliente = new Cliente();
        System.out.println("Ingrese cédula: ");
        cliente.setCedula(sc.nextInt());
        sc.nextLine();
        System.out.println("Ingrese nombre: ");
        cliente.setNombre(sc.nextLine());

        Turno turno = new Turno();
        turno.setNumeroTurno(contador++);
        turno.setCliente(cliente);

        colaTurnos.offer(turno);
        System.out.println("Turno asignado #" + turno.getNumeroTurno() + " para " + cliente.getNombre());
        return turno;
    }

    public void verTurnoActual() {
        if (colaTurnos.isEmpty()) {
            System.out.println("No hay clientes en turno.");
        } else {
            Turno actual = colaTurnos.peek();
            System.out.println("Turno actual: " + actual.getNumeroTurno());
            System.out.println("Cliente: " + actual.getCliente().getNombre());
            System.out.println("(Cédula: " + actual.getCliente().getCedula() + ")");
        }
    }

    public void atenderTurno() {
        if (colaTurnos.isEmpty()) {
            System.out.println("No hay clientes en espera.");
            return;
        }

        Turno turno = colaTurnos.poll();
        System.out.println("Atendiendo al cliente: " + turno.getCliente().getNombre());

        double total = 0;
        boolean seguir = true;

        while (seguir) {
            System.out.println("1. Agregar producto");
            System.out.println("2. Terminar compra");
            System.out.println("3. Salir sin comprar");
            System.out.println("Seleccione: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Articulo articulo = new Articulo();
                    articulo.setId((int) (Math.random() * 1000));
                    System.out.println("Nombre producto: ");
                    articulo.setNombre(sc.nextLine());
                    System.out.println("Precio: ");
                    articulo.setPrecioUnidad(sc.nextDouble());
                    sc.nextLine();

                    total += articulo.getPrecioUnidad();
                    System.out.println("Producto agregado. Subtotal: $" + total);
                    break;
                case 2:
                    System.out.println("Total de la comra: $" + total);
                    atendidos.offer(turno);
                    seguir = false;
                    break;
                case 3:
                    System.out.println("El cliente decidió no comprar.");
                    seguir = false;
                    break;
            }
        }
    }
}