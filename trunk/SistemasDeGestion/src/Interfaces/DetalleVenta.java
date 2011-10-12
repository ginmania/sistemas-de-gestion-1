/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author diego
 */
    public interface DetalleVenta {

    public int getCantidad();

    public void setCantidad(int cantidad);

    public Venta getVenta();

    public void setVenta(Venta venta);

    public Producto getProducto();

    public void setProducto(Producto producto);

    public double getPrecioUnitario();
    
    public void setPrecioUnitario(double p);

    public double getPrecio();    

    public void setPrecio(double p);
    
}
