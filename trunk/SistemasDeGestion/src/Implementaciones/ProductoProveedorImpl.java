/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.Producto;
import Interfaces.ProductoProveedor;
import Interfaces.Proveedor;

/**
 *
 * @author duende
 */
public class ProductoProveedorImpl implements ProductoProveedor{
    int baja;
    Producto producto;
    Proveedor proveedor;

    public ProductoProveedorImpl() {
    }
    
    
    public int getbaja() {
        return baja;
    }

    public void setbaja(int baja) {
        this.baja = baja;
    }

    public void setProducto(Producto p) {
        this.producto = p;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProveedor(Proveedor pr) {
        this.proveedor = pr;
    }

    public Proveedor getProveedor() {
        return this.proveedor;
    }
    
    
}
