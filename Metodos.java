import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Metodos {
    private Queue<Turno> colaTurnos = new LinkedList<>();
    private Queue<Turno> atendidos = new LinkedList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();
    private ArrayList<Articulo> inventario = new ArrayList<>();
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

    public void mostrarPendientes() {
        if (colaTurnos.isEmpty()) {
            System.out.println("No hay clientes pendientes.");
        } else {
            System.out.println("Clientes pendientes en la cola:");
            for (Turno t : colaTurnos) {
                System.out.println("Turno #" + t.getNumeroTurno() + " - " + t.getCliente().getNombre());
            }
        }
    }

    public void mostrarAtendidos() {
        if (atendidos.isEmpty()) {
            System.out.println("No hay clientes atendidos todavía.");
        } else {
            System.out.println("Clientes atendidos: ");
            for (Turno t : atendidos) {
                System.out.println("Turno #" + t.getNumeroTurno() + " - " + t.getCliente().getNombre());
            }
        }
    }

    public void totalVentasDia() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            double total = 0;
            for (Venta v : ventas) {
                total += v.getTotal();
            }
            System.out.println("Total de ventas del día: $" + total);
        }
    }

    public void verEstadoGeneral() {
        System.out.println("\n===== ESTADO GENERAL DEL DÍA =====");

        mostrarPendientes();

        mostrarAtendidos();

        totalVentasDia();

        System.out.println("==================================\n");
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
            System.out.println("1. Agregar producto del inventario");
            System.out.println("2. Terminar compra");
            System.out.println("3. Salir sin comprar");
            System.out.println("4. Descartar último artículo");
            System.out.println("Seleccione: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    verInventario();
                    System.out.println("Ingrese ID del producto que desea comprar:");
                    int idProd = sc.nextInt();
                    sc.nextLine();

                    Articulo seleccionado = null;
                    for (Articulo a : inventario) {
                        if(a.getId() == idProd && a.getEstado() == 0 && a.getExistencias() > 0){
                            seleccionado = a;
                            break;
                        }
                    }

                    if ( seleccionado == null){
                        System.out.println("Producto no válido o sin existencias. ");
                    }else{
                        seleccionado.setExistencias(seleccionado.getExistencias() - 1);
                        
                        Articulo comprado = new Articulo();
                        comprado.setId(seleccionado.getId());
                        comprado.setNombre(seleccionado.getNombre());
                        comprado.setPrecioUnidad(seleccionado.getPrecioUnidad());

                        articulosCompra.add(comprado);
                        total += comprado.getPrecioUnidad();

                        System.out.println("Producto agregado. Subtotal: $" + total);
                    }
                    break;
                case 2:
                    if (articulosCompra.isEmpty()) {
                        System.out.println("No se puede registrar una venta sin articulos.");
                    } else {
                        Venta venta = new Venta();
                        venta.setId(idVenta++);
                        venta.setCliente(turno.getCliente());
                        venta.setArticulos(articulosCompra);
                        venta.setTotal(total);

                        ventas.add(venta);

                        System.out.println("Total de la comra: $" + total);
                        atendidos.offer(turno);
                    }
                    seguir = false;
                    break;
                case 3:
                    System.out.println("El cliente decidió no comprar.");
                    seguir = false;
                    break;
                case 4:
                    if (articulosCompra.isEmpty()) {
                        System.out.println("No hay artículos para descartar.");
                    } else {
                        Articulo eliminado = articulosCompra.remove(articulosCompra.size() - 1);
                        total -= eliminado.getPrecioUnidad();
                        
                        for (Articulo a : inventario) {
                            if(a.getId() == eliminado.getId()){
                                a.setExistencias(a.getExistencias() + 1);
                                break;
                            }
                        }
                        System.out.println("Se eliminó el artículo : " + eliminado.getNombre());
                        System.out.println("Nuevo subtotal: $" + total);
                    }
                    break;
                default:
                    System.out.println("Opción no válida. ");
                    break;
            }
        }
    }

    public void verVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Venta v : ventas) {
                System.out.println("Venta #" + v.getId() + " - Cliente: " + v.getCliente().getNombre());
                for (Articulo a : v.getArticulos()) {
                    System.out.println("Producto: " + a.getNombre() + " - $" + a.getPrecioUnidad());
                }
                System.out.println("Total: $" + v.getTotal());
                System.out.println("-------------------");
            }
        }
    }

    public void reporteClientesAtendidos() {
        if (atendidos.isEmpty()) {
            System.out.println("No hay clientes atendidos todavía. ");
        } else {
            System.out.println("Clientes atendidos: ");
            for (Turno t : atendidos) {
                System.out.println("- Turno #" + t.getNumeroTurno() +
                        "| Cliente: " + t.getCliente().getNombre() +
                        "(Cédula: " + t.getCliente().getCedula() + ")");
            }
        }
    }

    public void reporteVentasTotales() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas todavía.");
        } else {
            double totalGeneral = 0;
            for (Venta v : ventas) {
                totalGeneral += v.getTotal();
            }
            System.out.println("Ventas totales registradas: $" + totalGeneral);
        }
    }

    public void reporteProductosVendidos() {
        if (ventas.isEmpty()) {
            System.out.println("No hay productos vendidos todavía.");
        } else {
            System.out.println("Productos vendidos:");
            ArrayList<String> nombres = new ArrayList<>();
            ArrayList<Integer> cantidades = new ArrayList<>();

            for (Venta v : ventas) {
                for (Articulo a : v.getArticulos()) {
                    String nombre = a.getNombre();
                    int indice = nombres.indexOf(nombre);

                    if (indice == -1) {
                        nombres.add(nombre);
                        cantidades.add(1);
                    } else {
                        cantidades.set(indice, cantidades.get(indice) + 1);
                    }
                }
            }
            for (int i = 0; i < nombres.size(); i++) {
                System.out.println("-" + nombres.get(i) + ": " + cantidades.get(i) + " unidad(es)");
            }
        }
    }

    public void verInventario(){
        if(inventario.isEmpty()){
            System.out.println("El inventario está vació. ");
        } else{
            System.out.println("Inventario: ");
            for (Articulo a : inventario) {
                String estado = (a.getEstado() == 0) ? "Activo" : "Inactivo";
                System.out.println("ID: "+ a.getId()+
                "| Nombre: " + a.getNombre() + 
                "| Categoría: "+ a.getCategoria() +
                "| Existencias: " + a.getExistencias() +
                "| Precio: $" + a.getPrecioUnidad()+
                "| Estado: " + estado);
            }
        }
    }

    public void agregarArticulo(){
        Articulo articulo = new Articulo();
        articulo.setId(inventario.size() + 1);

        System.out.println("Ingrese nombre del artículo: ");
        articulo.setNombre(sc.nextLine());
        System.out.println("Ingrese categoría del artículo: ");
        articulo.setCategoria(sc.nextLine());
        System.out.println("Ingrese cantidad en existencias: ");
        articulo.setExistencias(sc.nextInt());
        System.out.println("Ingrese precio por unidad: ");
        articulo.setPrecioUnidad(sc.nextDouble());
        sc.nextLine();

        articulo.setEstado(0);
        inventario.add(articulo);
        System.out.println("Artículo agregado correctamente.");
    }

    public void darDeBajaArticulo(){
        System.out.println("Ingrese el ID del artículo a dar de baja: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Articulo a : inventario) {
            if(a.getId()== id){
                a.setEstado(1);
                System.out.println("El artículo '" + a.getNombre() + "' fue dado de baja.");
                return;
            }
        }
        System.out.println("No se encontró un artículo con ese ID.");
    }
}