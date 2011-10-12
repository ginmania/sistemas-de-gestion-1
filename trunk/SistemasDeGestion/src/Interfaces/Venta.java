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
public interface Venta {
    
    public String getFechaventa();
    
    public void setFechaventa(String fechaventa);
    
    public int getNumero();
    
    public void setNumero(int numero);
    
    public double getTotal();
    
    public void setTotal(double total);
    
    public List<DetalleVenta> getDetalleVenta();

    public void setDetalleVentas(List<DetalleVenta> detalleventa);

    public void setDetalleVenta(DetalleVenta detalleventa);
    
    public Cliente getCliente();
    
    public void setCliente(Cliente cliente);

    
}
