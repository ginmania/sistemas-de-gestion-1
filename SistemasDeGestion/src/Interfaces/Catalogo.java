/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author duende
 */
public interface Catalogo {
    public String getFecha();

    public void setFecha(String fecha);

    public double getPrecioCompra();

    public void setPrecioCompra(double precioCompra);

    public int getDemora();

    public void setDemora(int demora);

    public Proveedor getProveedor();

    public void setProveedor(Proveedor proveedor) ;

    public Producto getProducto();

    public void setProducto(Producto producto);
    
}
