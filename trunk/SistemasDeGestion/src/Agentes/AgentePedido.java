/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Implementaciones.PedidoImpl;
import Interfaces.DetallePedido;
import Interfaces.Pedido;
import Interfaces.Proveedor;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;
import java.util.List;

/**
 *
 * @author diego
 */
public class AgentePedido implements Pedido, ObjetoPersistente {

    private String OIDPedido;
    private String OIDProveedor;
    private PedidoImpl impl;
    private List<String> detallepedidos;

    public PedidoImpl getImpl() {
        return impl;
    }

    public void setImpl(PedidoImpl impl) {
        this.impl = impl;
    }

    public AgentePedido() {
    }

    public AgentePedido(PedidoImpl impl) {
        this.impl = impl;
    }

    public String getOIDProveedor() {
        return OIDProveedor;
    }

    public void setOIDProveedor(String OIDProveedor) {
        this.OIDProveedor = OIDProveedor;
    }

    public String getFechaEmision() {
        return impl.getFechaEmision();
    }

    public void setFechaEmision(String fechaemision) {
        impl.setFechaEmision(fechaemision);
    }

    public String getFechaEntrega() {
        String fecha = impl.getFechaEntrega();
        if(fecha == "null")
            fecha = "0-0-1900";                
        return fecha;
    }

    public void setFechaEntrega(String fechaentrega) {
        impl.setFechaEntrega(fechaentrega);
    }

    public List<DetallePedido> getDetallePedido() {
        if (impl.getDetallePedido() == null) {
            for (String OIDDetallePedido : detallepedidos) {
                setDetallePedido((DetallePedido) FachadaInterna.getInstancia().buscarOID(DetallePedido.class, OIDDetallePedido));
            }
        }
        return impl.getDetallePedido();

    }

    public void setDetallePedidos(List<DetallePedido> detallepedido) {
        impl.setDetallePedidos(detallepedido);
    }

    public void setDetallePedido(DetallePedido detallepedido) {
        impl.setDetallePedido(detallepedido);
    }

    public Proveedor getProveedor() {
        if (impl.getProveedor() == null) {
            setProveedor((Proveedor) FachadaInterna.getInstancia().buscarOID(Proveedor.class, OIDProveedor));
        }
        return impl.getProveedor();
    }

    public void setProveedor(Proveedor proveedor) {
        impl.setProveedor(proveedor);
    }

    public void setoid(String oid) {
        this.OIDPedido = oid;
    }

    public String getoid() {
        return OIDPedido;
    }

    public int getPendiente() {
        return impl.getPendiente();
    }

    public void setPendiente(int baja) {
        impl.setPendiente(baja);
    }

    public void setNroPedido(String Nro) {
        impl.setNroPedido(Nro);
    }

    public String getNroPedido() {
        return impl.getNroPedido();
    }
    
}
