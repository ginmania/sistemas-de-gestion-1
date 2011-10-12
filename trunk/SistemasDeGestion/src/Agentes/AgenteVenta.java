/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.VentaImpl;
import Interfaces.Cliente;
import Interfaces.DetalleVenta;
import Interfaces.Venta;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;
import java.util.List;

/**
 *
 * @author diego
 */
public class AgenteVenta implements Venta, ObjetoPersistente {

    private String OIDVenta;
    private String OIDCliente;
    private List<String> detalleventas;
    private VentaImpl impl;    

    public AgenteVenta() {
    }

    public AgenteVenta(VentaImpl impl) {
        this.impl = impl;
    }

    public String getOIDCliente() {
        return OIDCliente;
    }

    public void setOIDCliente(String OIDCliente) {
        this.OIDCliente = OIDCliente;
    }

    public VentaImpl getImpl() {
        return impl;
    }

    public void setImpl(VentaImpl impl) {
        this.impl = impl;
    }

    public String getFechaventa() {
        return impl.getFechaventa();
    }

    public void setFechaventa(String fechaventa) {
        impl.setFechaventa(fechaventa);
    }

    public int getNumero() {
        return impl.getNumero();
    }

    public void setNumero(int numero) {
        impl.setNumero(numero);
    }

    public double getTotal() {
        return impl.getTotal();
    }

    public void setTotal(double total) {
        impl.setTotal(total);
    }

    public List<DetalleVenta> getDetalleVenta() {
        if (impl.getDetalleVenta() == null) {
            for (String OIDDetalleVenta : detalleventas) {
                setDetalleVenta((DetalleVenta) FachadaInterna.getInstancia().buscarOID(DetalleVenta.class, OIDDetalleVenta));
            }
        }
        return impl.getDetalleVenta();

    }

    public void setDetalleVentas(List<DetalleVenta> detalleventa) {
        impl.setDetalleVentas(detalleventa);
    }

    public void setDetalleVenta(DetalleVenta detalleventa) {
        impl.setDetalleVenta(detalleventa);
    }

    public void setoid(String oid) {
        this.OIDVenta = oid;
    }

    public String getoid() {
        return OIDVenta;
    }

    public Cliente getCliente() {
        if (impl.getCliente() == null) {
            setCliente((Cliente) FachadaInterna.getInstancia().buscarOID(Cliente.class, OIDCliente));
        }
        return impl.getCliente();
    }

    public void setCliente(Cliente cliente) {
        impl.setCliente(cliente);        
    }
}
