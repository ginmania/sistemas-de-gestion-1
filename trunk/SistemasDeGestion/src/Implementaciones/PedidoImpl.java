/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.DetallePedido;
import Interfaces.Pedido;
import Interfaces.Proveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class PedidoImpl implements Pedido {
    private String NroPedido;
    private String fechaemision;
    private String fechaentrega;
    private List<DetallePedido> detallepedidos;
    private Proveedor proveedor;
    private int pendiente;
    

    public PedidoImpl() {
    }

    public String getFechaEmision() {
        return fechaemision;
    }

    public void setFechaEmision(String fechaemision) {
        this.fechaemision = fechaemision;
    }

    public String getFechaEntrega() {
        return fechaentrega;
    }

    public void setFechaEntrega(String fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public List<DetallePedido> getDetallePedido() {
        return detallepedidos;
    }

    public void setDetallePedidos(List<DetallePedido> detallepedido) {
        if (detallepedidos == null) {
            detallepedidos = new ArrayList<DetallePedido>();
        }
        detallepedidos.addAll(detallepedido);
    }

    public void setDetallePedido(DetallePedido detallepedido) {
        if (detallepedidos == null) {
            detallepedidos = new ArrayList<DetallePedido>();
        }
        detallepedidos.add(detallepedido);
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getPendiente() {
        return this.pendiente;
    }

    public void setPendiente(int baja) {
        this.pendiente = baja;
    }

    public void setNroPedido(String Nro) {
        this.NroPedido = Nro;
    }

    public String getNroPedido() {
        return this.NroPedido;
    }

}
