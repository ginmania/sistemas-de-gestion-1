/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.DetalleVenta;
import Interfaces.Producto;
import Interfaces.Venta;

/**
 *
 * @author diego
 */
public class DetalleVentaImpl implements DetalleVenta {
    
    private Venta venta;
    private Producto producto;
    private int cantidad;    
    private double PrecioUnitario;
    private double Precio;

    public DetalleVentaImpl() {
        
    } 
    

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
      this.producto = producto;
    }

    public double getPrecioUnitario() {
        return this.PrecioUnitario;
    }
    
    public void setPrecioUnitario(double precioU){
        this.PrecioUnitario = precioU;
    }

    public double getPrecio() {
        return this.Precio;
    }
    
    public void setPrecio(double precio){
        this.Precio = precio;
    }    
    
}
