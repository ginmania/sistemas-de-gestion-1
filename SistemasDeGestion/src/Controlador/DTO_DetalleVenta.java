/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Interfaces.Producto;
import Interfaces.Venta;

/**
 *
 * @author duende
 */
public class DTO_DetalleVenta {
    
    private String CodProducto;
    private String NombreProducto;
    private int NroVenta;
    private int cantidad;
    private double PrecioU;
    private double Precio;
    private Producto prod;
    private Venta vta;

    public String getCodProducto() {
        return CodProducto;
    }

    public void setCodProducto(String CodProducto) {
        this.CodProducto = CodProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public int getNroVenta() {
        return NroVenta;
    }

    public void setNroVenta(int NroVenta) {
        this.NroVenta = NroVenta;
    }

    public double getPrecio() {
        return this.Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public double getPrecioU() {
        return this.PrecioU;
    }

    public void setPrecioU(double PrecioU) {
        this.PrecioU = PrecioU;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProd() {
        return this.prod;
    }

    public void setProd(Producto prod) {
        this.prod = prod;
    }

    public Venta getVta() {
        return this.vta;
    }

    public void setVta(Venta vta) {
        this.vta = vta;
    }

    public void setCodProducto(int codigoProducto) {
        this.CodProducto = String.valueOf(codigoProducto);
    }
    
    
    
}
