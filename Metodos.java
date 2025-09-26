import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Metodos {
    private Queue<Turno> colaTurnos = new LinkedList<>();
    private Queue<Turno> atendidos = new LinkedList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();
    private int contador = 1;
    private int idVenta = 1;
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
        ArrayList<Articulo> articulosCompra = new ArrayList<>();

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

                    articulosCompra.add(articulo);
                    total += articulo.getPrecioUnidad();
                    System.out.println("Producto agregado. Subtotal: $" + total);
                    break;
                case 2:
                    Venta venta = new Venta();
                    venta.setId(idVenta++);
                    venta.setCliente(turno.getCliente());
                    venta.setArticulos(articulosCompra);
                    venta.setTotal(total);

                    ventas.add(venta);

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
    public void verVentas(){
        if (ventas.isEmpty()){
            System.out.println("No hay ventas registradas.");
        } else{
            for (Venta v : ventas){
                System.out.println("Venta #" + v.getId() + " - Cliente: "+ v.getCliente().getNombre());
                for (Articulo a : v.getArticulos()) {
                    System.out.println("Producto: " + a.getNombre() + " - $" + a.getPrecioUnidad());
                }
                System.out.println("Total: $" + v.getTotal());
                System.out.println("-------------------");
            }
        }
    }

    public void reporteClientesAtendidos(){
        if(atendidos.isEmpty()){
            System.out.println("No hay clientes atendidos todavía. ");
        }else{
            System.out.println("Clientes atendidos: ");
            for (Turno t : atendidos) {
                System.out.println("- Turno #" + t.getNumeroTurno()+
                "| Cliente: "+t.getCliente().getNombre()+
                "(Cédula: " + t.getCliente().getCedula() + ")");
            }
        }
    }

    public void reporteVentasTotales(){
        if (ventas.isEmpty()){
            System.out.println("No hay ventas registradas todavía.");
        }else{
            double totalGeneral = 0;
            for (Venta v : ventas) {
                totalGeneral += v.getTotal();
            }
            System.out.println("Ventas totales registradas: $" + totalGeneral);
        }
    }
    public void reporteProductosVendidos(){
        if (ventas.isEmpty()){
            System.out.println("No hay productos vendidos todavía.");
        }else{
            System.out.println("Productos vendidos:");
            ArrayList<String> nombres = new ArrayList<>();
            ArrayList<Integer> cantidades = new ArrayList<>();

            for (Venta v : ventas) {
                for (Articulo a : v.getArticulos()) {
                    String nombre = a.getNombre();
                    int indice = nombres.indexOf(nombre);

                    if (indice == -1){
                        nombres.add(nombre);
                        cantidades.add(1);
                    }else{
                        cantidades.set(indice, cantidades.get(indice) + 1);
                    }
                }
            }
            for (int i = 0; i < nombres.size(); i++) {
                System.out.println("-" + nombres.get(i) + ": "+ cantidades.get(i) + " unidad(es)");
            }
        }
    }
}