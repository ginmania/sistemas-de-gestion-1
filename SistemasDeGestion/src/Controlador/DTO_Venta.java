package Controlador;
import Interfaces.Cliente;
import Interfaces.DetalleVenta;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class DTO_Venta implements DTO{
    private Vector productos;
    private Vector IDproductos;
    private int cantidades;
    private int NroVenta;
    private Cliente cliente;
    private String fecha;
    private String ClienteNombre;
    private List<DetalleVenta> detalles;
    private double Total;

    public DTO_Venta(){
        detalles = new ArrayList<DetalleVenta>();
        
    }

    public Vector getProductosVector() {
        return productos;
    }

    public String[] getProductos(){
        String[] prods = new String[productos.size()];
        for(int i=0; i < prods.length; i++){
            prods[i] = (String)productos.get(i);
        }
        return prods;
    }

    public String getProducto(int i){
        return (String)productos.get(i);
    }

    public void setProductosVector(Vector productos) {
        this.productos = productos;
    }

    public void setProductos(String[] prods){
        for(int i=0; i < prods.length; i++){
            productos.add(prods[i]);
        }
    }

    public void setProducto(String prod){
        productos.add(prod);
    }

    public Vector getIDproductosVector() {
        return IDproductos;
    }

    public UUID[] getIDProductos(){
        UUID[] prods = new UUID[IDproductos.size()];
        for(int i=0; i < prods.length; i++){
            prods[i] = (UUID)IDproductos.get(i);
        }
        return prods;
    }

    public UUID getIDProducto(int i){
        return (UUID)IDproductos.get(i);
    }


    public void setIDproductosVector(Vector IDproductos) {
        this.IDproductos = IDproductos;
    }

    public void setIDProductos(UUID[] prods){
        for(int i=0; i < prods.length; i++){
            productos.add(prods[i]);
        }
    }

    public void setIDProducto(UUID prod){
        productos.add(prod);
    }

    public int getCantidades() {
        return cantidades;
    }
    
    public void setCantidadesVector(int cantidades) {
        this.cantidades = cantidades;
    }
    
    public int getNroVenta() {
        return this.NroVenta;
    }

    public void setNroVenta(int NroVenta) {
        this.NroVenta = NroVenta;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public void setDetalle(DetalleVenta det){
        detalles.add(det);
    }
    
    public List<DetalleVenta> getDetalle(){
        return this.detalles;
    }

    public void setDetalle(List<DetalleVenta> detalleVenta) {
        this.detalles = detalleVenta;
    }

    public void setNombreCliente(String nombre) {
        this.ClienteNombre = nombre;
    }
    
    public String getNombreCliente() {
        return this.ClienteNombre;
    }

    public void setTotal(double total) {
        this.Total= total;
    }
    
    public double getTotal(){
        return this.Total;
    }
    
}
