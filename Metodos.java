import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Metodos {
    Queue<Turno> colaTurnos = new LinkedList<>();
    int contador = 1;
    Scanner sc = new Scanner(System.in);

    public Turno tomarTurno(){
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

    public void verTurnoActual(){
        if(colaTurnos.isEmpty()){
            System.out.println("No hay clientes en turno.");
        } else{
            Turno actual = colaTurnos.peek();
            System.out.println("Turno actual: " + actual.getNumeroTurno());
            System.out.println("Cliente: " + actual.getCliente().getNombre());
            System.out.println("(Cédula: " + actual.getCliente().getCedula() + ")");
        }
    }
}
