import java.util.ArrayList;

public class Venta {
    private int id;
    private Cliente cliente;
    private ArrayList<Articulo> articulos = new ArrayList<>();
    private double total;
    public Venta() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }
    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    
}
