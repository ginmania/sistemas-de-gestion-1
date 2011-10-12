/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.DetallePedido;
import Interfaces.Pedido;
import Interfaces.Producto;

/**
 *
 * @author diego
 */
public class DetallePedidoImpl implements DetallePedido {

    private int cantidad;
    private Pedido pedido;
    private Producto producto;

    public DetallePedidoImpl() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
