/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Interfaces.DetallePedido;
import Interfaces.Pedido;
import Interfaces.Producto;
import Persistencia.FachadaInterna;
import Persistencia.ObjetoPersistente;

/**
 *
 * @author diego
 */
public class AgenteDetallePedido implements DetallePedido, ObjetoPersistente {

    private String OIDDetallePedido;
    private String OIDPedido;
    private String OIDProducto;
    private DetallePedido impl;

    public AgenteDetallePedido() {
    }

    public AgenteDetallePedido(AgenteDetallePedido agente) {
        this.OIDDetallePedido = agente.OIDDetallePedido;
        this.OIDPedido = agente.OIDPedido;
        this.OIDProducto = agente.OIDProducto;
    }

    public DetallePedido getImpl() {
        return impl;
    }

    public void setImpl(DetallePedido impl) {
        this.impl = impl;

    }

    public String getOIDPedido() {
        return OIDPedido;
    }

    public void setOIDPedido(String OIDPedido) {
        this.OIDPedido = OIDPedido;
    }

    public String getOIDProducto() {
        return OIDProducto;
    }

    public void setOIDProducto(String OIDProducto) {
        this.OIDProducto = OIDProducto;
    }

    public int getCantidad() {
        return impl.getCantidad();
    }

    public void setCantidad(int cantidad) {
        impl.setCantidad(cantidad);
    }

    public Pedido getPedido() {
         if (impl.getPedido() == null) {
            setPedido((Pedido) FachadaInterna.getInstancia().buscarOID(Pedido.class, OIDPedido));
        }
        return impl.getPedido();
    }

    public void setPedido(Pedido pedido) {
         impl.setPedido(pedido);
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

    public void setoid(String oid) {
        this.OIDDetallePedido = oid;
    }

    public String getoid() {
        return OIDDetallePedido;
    }
}
