/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.Catalogo;
import Interfaces.Producto;
import Interfaces.Proveedor;

/**
 *
 * @author duende
 */
public class CatalogoImpl implements Catalogo {
    
    private String fecha;
    private double precioCompra;
    private int demora;    
    //Objetos relacionados...    
    private Proveedor proveedor;    
    private Producto producto;
    private int baja;

    public CatalogoImpl(){}    

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecioCompra() {
        return this.precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getDemora() {
        return this.demora;
    }

    public void setDemora(int demora) {
        this.demora = demora;
    }

    public Proveedor getProveedor() {
        return this.proveedor;

    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;        
    }

    public Producto getProducto() {
        return this.producto;

    }

    public void setProducto(Producto producto) {
        this.producto = producto;        
    }

    public int getbaja() {
        return baja;
    }

    public void setbaja(int baja) {
        this.baja = baja;
    }
    
}
