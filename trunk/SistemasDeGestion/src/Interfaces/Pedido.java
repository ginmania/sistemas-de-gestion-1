/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;

/**
 *
 * @author diego
 */
public interface Pedido {

    public String getFechaEmision();

    public void setFechaEmision(String fechaemision);

    public String getFechaEntrega();

    public void setFechaEntrega(String fechaentrega);

    public List<DetallePedido> getDetallePedido();

    public void setDetallePedidos(List<DetallePedido> detallepedido);

    public void setDetallePedido(DetallePedido detallepedido);

    public Proveedor getProveedor();

    public void setProveedor(Proveedor proveedor);
}
