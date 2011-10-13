/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author duende
 */
public interface ProductoProveedor {
    public int getbaja();
    public void setbaja(int baja);
    public void setProducto(Producto p);
    public Producto getProducto();
    public void setProveedor(Proveedor pr);
    public Proveedor getProveedor();
    
}
