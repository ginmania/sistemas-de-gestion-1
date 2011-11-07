/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author diego
 */
public interface DetallePedido {

    public int getCantidad();

    public void setCantidad(int cantidad);

    public Pedido getPedido();

    public void setPedido(Pedido pedido);

    public Producto getProducto();

    public void setProducto(Producto producto);
    
    public void setBaja(int b);
    
    public int getBaja();
}
