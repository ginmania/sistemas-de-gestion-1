/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.DetalleVentaImpl;
import Implementaciones.VentaImpl;
import Interfaces.DetalleVenta;
import Interfaces.Producto;
import Interfaces.Venta;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;

/**
 *
 * @author diego
 */
public class AgenteDetalleVenta implements DetalleVenta, ObjetoPersistente {

    private String OIDDetalleVenta;
    private String OIDVenta;
    private String OIDProducto;
    private DetalleVentaImpl impl;    
    

    public DetalleVentaImpl getImpl() {
        return impl;
    }

    public void setImpl(DetalleVentaImpl impl) {
        this.impl = impl;
    }

    public String getOIDVenta() {
        return OIDVenta;
    }

    public void setOIDVenta(String OIDVenta) {
        this.OIDVenta = OIDVenta;
    }

    public AgenteDetalleVenta() {
    }

    public AgenteDetalleVenta(AgenteDetalleVenta agente) {
        this.OIDDetalleVenta = agente.OIDDetalleVenta;
        this.OIDProducto = agente.OIDProducto;
        this.OIDVenta = agente.OIDVenta;
    }

    public AgenteDetalleVenta(DetalleVentaImpl impl) {
        this.impl = impl;
    }

    public int getCantidad() {
        return impl.getCantidad();
    }

    public void setCantidad(int cantidad) {
        this.impl.setCantidad(cantidad);
    }

    public void setoid(String oid) {
        this.OIDDetalleVenta = oid;
    }

    public String getoid() {
        return OIDDetalleVenta;
    }

    public void setOIDProducto(String OIDProducto) {
        this.OIDProducto = OIDProducto;
    }

    public String getOIDProducto() {
        return OIDProducto;
    }

    public Venta getVenta() {
        if (impl.getVenta() == null) {
            setVenta((Venta) FachadaInterna.getInstancia().buscarOID(Venta.class, OIDVenta));
        }
        return impl.getVenta();

    }

    public void setVenta(Venta venta) {
        impl.setVenta(venta);
    }

    public Producto getProducto() {
        if (impl.getProducto() == null) {
            setProducto((Producto) FachadaInterna.getInstancia().buscarOID(Producto.class, OIDProducto));
        }
        return impl.getProducto();

    }

    public void setProducto(Producto producto) {
        impl.setProducto(producto);
    }

    public double getPrecioUnitario() {
        return impl.getPrecioUnitario();
    }

    public double getPrecio() {
        return impl.getPrecio();
    }    
    
    public void setCantidad(String cantidad) {
        impl.setCantidad(Integer.parseInt(cantidad));
    }

    public void setPrecioUnitario(double p) {
        impl.setPrecioUnitario(p);
    }

    public void setPrecio(double p) { 
        impl.setPrecio(p);
    }

    
}
